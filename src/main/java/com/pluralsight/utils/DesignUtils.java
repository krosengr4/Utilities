package com.pluralsight.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DesignUtils {

    //region colors
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String BACKGROUND_RESET = "\\u001B[0m";
    public static final String BLACK_BACKGROUND = "\\u001B[40m";
    public static final String RED_BACKGROUND = "\\u001B[41m";
    public static final String GREEN_BACKGROUND = "\\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\\u001B[43m";
    public static final String BLUE_BACKGROUND = "\\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\\u001B[45m";
    public static final String CYAN_BACKGROUND = "\\u001B[46m";
    public static final String WHITE_BACKGROUND = "\\u001B[47m";
    //endregion

    //region symbols and emojis
    public static String spade = "♠";
    public static String club = "♣";
    public static String heart = "❤";
    public static String diamond = "♢";

    public static String smileyFace = "\uD83D\uDE00";
    public static String thumbsUp = "\uD83D\uDC4D";
    public static String redHeart = "\u2764\uFE0F";
    public static String cowboy = "\uD83E\uDD20";
    public static String ticket = "\uD83C\uDF9F\uFE0F";
    public static String train = "\uD83D\uDE84";
    public static String fire = "\uD83D\uDD25";
    public static String meat = "\uD83E\uDD69";
    public static String bread = "\uD83C\uDF5E";
    public static String sandwich = "\uD83E\uDD6A";
    public static String drink = "\uD83E\uDD64";
    public static String cheese = "\uD83E\uDDC0";
    public static String chips = "\uD83E\uDD6B";

    public static String baseball = "⚾";
    public static String football = "\uD83C\uDFC8";
    public static String basketball = "\uD83C\uDFC0";
    public static String soccerball = "⚽";
    public static String volleyball = "\uD83C\uDFD0";
    public static String hockeyNet = "\uD83E\uDD45";
    //endregion

    public DesignUtils(){}

    //region designing lines for output
    public static void designLine(int numberOfLines, boolean isNewLine) {
        StringBuilder underLine = new StringBuilder("=");
        underLine.append("=".repeat(Math.max(0, numberOfLines)));
        if (isNewLine) {
            underLine.append("\n");
        }
        System.out.println(underLine);
    }

    public static void printDivider(String divider, int repeat) {
        System.out.println(divider.repeat(repeat));
    }
    //endregion

    //region changes the color of a string
    public static String makeRed(String message) {
        return (RED + message + RESET);
    }

    public static String makeGreen(String message) {
        return (GREEN + message + RESET);
    }

    public static String makeYellow(String message) {
        return (YELLOW + message + RESET);
    }

    public static String makeBlue(String message) {
        return (BLUE + message + RESET);
    }

    public static String makePurple(String message) {
        return (PURPLE + message + RESET);
    }
    //endregion

    //Specifies how many digits after the decimal in a double
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void playSound(String sound) {

        String filePath = "";

        switch (sound) {
            case "success" -> filePath = "TheDevDeli/src/sounds/owin31.wav";
            case "shutDown" -> filePath = "TheDevDeli/src/sounds/winxpshutdown.wav";
            case "wompWomp" -> filePath = "TheDevDeli/src/sounds/womp-womp.wav";
            default -> System.err.println("ERROR! Argument passed in is not an option!");
        }

        File audioFile = new File(filePath);

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            Object lock = new Object();

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    synchronized (lock) {
                        lock.notify();
                    }
                }
            });

            clip.start();
            synchronized (lock) {
                lock.wait();
            }

        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException | InterruptedException e) {
            e.printStackTrace(); }

    }

}