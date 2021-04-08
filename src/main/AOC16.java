package main;

import main.days.Day01;
import main.days.Day10;
import main.days.Day11;
import main.days.Day12;
import main.days.Day13;
import main.days.Day14;
import main.days.Day15;
import main.days.Day16;
import main.days.Day18;
import main.days.Day19;
import main.days.Day02;
import main.days.Day20;
import main.days.Day21;
import main.days.Day22;
import main.days.Day23;
import main.days.Day24;
import main.days.Day25;
import main.days.Day03;
import main.days.Day04;
import main.days.Day05;
import main.days.Day06;
import main.days.Day07;
import main.days.Day08;
import main.days.Day09;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class AOC16 {

    public static void main(String[] args) {
        int exercise = 0;
        int part = 0;
        boolean testing = false;
        if(args.length >3 || args.length<2) {
            usage();
        }else {
            try {
                exercise = Integer.parseInt(args[0]);
                part = Integer.parseInt(args[1]);
                if(args.length==3) {
                    testing = Boolean.parseBoolean(args[2]);
                }
                if(exercise>25 || exercise<1 || part < 1 || part > 2){
                    usage();
                }
            } catch (Exception e) {
                e.printStackTrace();
                usage();
            }
        }
        doExercise(exercise,part,testing);
    }

    private static void usage(){
        System.out.println("useage: exercise part testing");
        System.out.println("useage:  [0-25]  [1,2]  [1,0]?");
        System.exit(-1);
    }

    private static void doExercise(int exercise, int part, boolean test){
        String in = "";
        try {
            in = readFile((test ? "test" : "input" + exercise) + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AOCRiddle riddle = getRiddle(exercise,part,in);
        System.out.println(riddle.solve());


    }

    private static AOCRiddle getRiddle(int exercise, int part, String in) {
        return switch(exercise){
            case 1 -> new Day01(in,part);
            case 2 -> new Day02(in,part);
            case 3 -> new Day03(in,part);
            case 4 -> new Day04(in,part);
            case 5 -> new Day05(in,part);
            case 6 -> new Day06(in,part);
            case 7 -> new Day07(in,part);
            case 8 -> new Day08(in,part);
            case 9 -> new Day09(in,part);
            case 10 -> new Day10(in,part);
            case 11 -> new Day11(in,part);
            case 12 -> new Day12(in,part);
            case 13 -> new Day13(in,part);
            case 14 -> new Day14(in,part);
            case 15 -> new Day15(in,part);
            case 16 -> new Day16(in,part);
            case 17 -> null;//new TooMuch(in,part);
            case 18 -> new Day18(in,part);
            case 19 -> new Day19(in,part);
            case 20 -> new Day20(in,part);
            case 21 -> new Day21(in,part);
            case 22 -> new Day22(in,part);
            case 23 -> new Day23(in,part);
            case 24 -> new Day24(in,part);
            case 25 -> new Day25(in,part);
            default ->  null;
        };
    }

    private static String readFile(String fileName) throws IOException {
        File file = new File(
                Objects.requireNonNull(AOC16.class.getClassLoader().getResource(fileName)).getFile()
        );
        return Files.readString(file.toPath());
    }

}
