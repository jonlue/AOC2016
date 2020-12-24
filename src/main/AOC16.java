package main;

import main.day1.TaxiCab;
import main.day10.BalanceBot;
import main.day11.RTGs;
import main.day12.LeonardosMonorail;
import main.day13.MazeOfTwistyLittleCubicles;
import main.day14.OneTimePad;
import main.day15.TimingIsEverything;
import main.day16.DragonChecksum;
import main.day18.LikeARouge;
import main.day19.AnElephantNamedJoseph;
import main.day2.BathroomSecurity;
import main.day20.FirewallRules;
import main.day21.ScrambledLettersAndHash;
import main.day22.GridComputing;
import main.day23.SafeCracking;
import main.day24.AirDuctSpelunking;
import main.day25.ClockSignal;
import main.day3.ThreeSideSquares;
import main.day4.ObscuritySecurity;
import main.day5.GameOfChess;
import main.day6.SignalsAndNoise;
import main.day7.IPV7;
import main.day8.TwoFactorAuthentication;
import main.day9.CyberSpaceExplosives;

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
                riddle= new TaxiCab(in,part);
                break;
            case(2):
                riddle = new BathroomSecurity(in,part);
                break;
            case(3):
                riddle = new ThreeSideSquares(in,part);
                break;
            case(4):
                riddle = new ObscuritySecurity(in,part);
                break;
            case(5):
                riddle = new GameOfChess(in,part);
                break;
            case(6):
                riddle = new SignalsAndNoise(in,part);
                break;
            case(7):
                riddle = new IPV7(in,part);
                break;
            case(8):
                riddle = new TwoFactorAuthentication(in,part);
                break;
            case(9):
                riddle = new CyberSpaceExplosives(in,part);
                break;
            case(10):
                riddle = new BalanceBot(in,part);
                break;
            case(11):
                riddle = new RTGs(in,part);
                break;
            case(12):
                riddle = new LeonardosMonorail(in,part);
                break;
            case(13):
                riddle = new MazeOfTwistyLittleCubicles(in,part);
                break;
            case(14):
                riddle = new OneTimePad(in,part);
                break;
            case(15):
                riddle = new TimingIsEverything(in,part);
                break;
            case(16):
                riddle = new DragonChecksum(in,part);
                break;
            case(17):
                //riddle = new TooMuch(in,part);
                break;
            case(18):
                riddle = new LikeARouge(in,part);
                break;
            case(19):
                riddle = new AnElephantNamedJoseph(in,part);
                break;
            case(20):
                riddle = new FirewallRules(in,part);
                break;
            case(21):
                riddle = new ScrambledLettersAndHash(in,part);
                break;
            case(22):
                riddle = new GridComputing(in,part);
                break;
            case(23):
                riddle = new SafeCracking(in,part);
                break;
            case(24):
                riddle = new AirDuctSpelunking(in,part);
                break;
            case(25):
                riddle = new ClockSignal(in,part);
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
