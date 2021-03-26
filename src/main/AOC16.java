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
        doExecrise(exercise,part,testing);
    }

    private static void usage(){
        System.out.println("useage: exercise part testing");
        System.out.println("useage:  [0-25]  [1,2]  [1,0]?");
        System.exit(-1);
    }

    private static void doExecrise(int exercise, int part,boolean test){
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
        AOCRiddle riddle = null;
        switch(exercise){
            case(1):
                riddle= new Day01(in,part);
                break;
            case(2):
                riddle = new Day02(in,part);
                break;
            case(3):
                riddle = new Day03(in,part);
                break;
            case(4):
                riddle = new Day04(in,part);
                break;
            case(5):
                riddle = new Day05(in,part);
                break;
            case(6):
                riddle = new Day06(in,part);
                break;
            case(7):
                riddle = new Day07(in,part);
                break;
            case(8):
                riddle = new Day08(in,part);
                break;
            case(9):
                riddle = new Day09(in,part);
                break;
            case(10):
                riddle = new Day10(in,part);
                break;
            case(11):
                riddle = new Day11(in,part);
                break;
            case(12):
                riddle = new Day12(in,part);
                break;
            case(13):
                riddle = new Day13(in,part);
                break;
            case(14):
                riddle = new Day14(in,part);
                break;
            case(15):
                riddle = new Day15(in,part);
                break;
            case(16):
                riddle = new Day16(in,part);
                break;
            case(17):
                //riddle = new TooMuch(in,part);
                break;
            case(18):
                riddle = new Day18(in,part);
                break;
            case(19):
                riddle = new Day19(in,part);
                break;
            case(20):
                riddle = new Day20(in,part);
                break;
            case(21):
                riddle = new Day21(in,part);
                break;
            case(22):
                riddle = new Day22(in,part);
                break;
            case(23):
                riddle = new Day23(in,part);
                break;
            case(24):
                riddle = new Day24(in,part);
                break;
            case(25):
                riddle = new Day25(in,part);
                break;
            default:
        }
        return riddle;
    }

    private static String readFile(String fileName) throws IOException {
        File file = new File(
                Objects.requireNonNull(AOC16.class.getClassLoader().getResource(fileName)).getFile()
        );
        return Files.readString(file.toPath());
    }

}
