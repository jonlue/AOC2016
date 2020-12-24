package main.day2;

import main.AOCRiddle;

import java.util.Arrays;

public class BathroomSecurity extends AOCRiddle {

    private static final char UP = 'U';
    private static final char RIGHT = 'R';
    private static final char DOWN = 'D';
    private static final char LEFT = 'L';
    private static final String REGEX_REPLACE_REAPEATING = "((.)\\2{2})\\2+";
    private static final int STARTING_POS_X = 1;
    private static final int STARTING_POS_Y = 1;
    private static final int KEYPAD_SIZE = 3;

    public BathroomSecurity(String in, int part) {
        super(in, part);
    }

    @Override
    protected String solve1() {
        setInput(getInput().replaceAll(REGEX_REPLACE_REAPEATING,""));
        int[][] keypad = initKeypad();
        int x = STARTING_POS_X;
        int y = STARTING_POS_Y;
        String answer = "";

        for(String s : getInput().split("\n")){
            for(char c : s.toCharArray()){
                switch (c){
                    case(UP):
                        y = Math.max(y-1,0);
                        break;
                    case(RIGHT):
                        x = Math.min(x+1,KEYPAD_SIZE-1);
                        break;
                    case(DOWN):
                        y = Math.min(y+1, KEYPAD_SIZE-1);
                        break;
                    case (LEFT):
                        x = Math.max(x-1,0);
                        break;
                }
            }
            answer+= keypad[y][x];
        }
        System.out.print("Bathroom code: ");
        return answer;
    }

    @Override
    protected String solve2() {
        int number = 5;
        String answer = "";

        for(String s : getInput().split("\n")){
            for(char c : s.toCharArray()){
                switch (c){
                    case(UP):
                        switch(number){
                            case(3): number = 1;break;
                            case 6: number = 2; break;
                            case 7: number = 3; break;
                            case 8: number = 4; break;
                            case 10: number = 6; break;
                            case 11: number = 7; break;
                            case 12: number = 8; break;
                            case 13: number = 11; break;
                        }
                        break;
                    case(RIGHT):
                        switch(number){
                            case 2: number = 3;break;
                            case 3: number = 4; break;
                            case 5: number = 6; break;
                            case 6: number = 7; break;
                            case 7: number = 8; break;
                            case 8: number = 9; break;
                            case 10: number = 11; break;
                            case 11: number = 12; break;
                        }
                        break;
                    case(DOWN):
                        switch(number){
                            case 2: number = 6;break;
                            case 3: number = 7; break;
                            case 4: number = 8; break;
                            case 6: number = 10; break;
                            case 7: number = 11; break;
                            case 8: number = 12; break;
                            case 1: number = 3; break;
                            case 11: number = 13; break;
                        }
                        break;
                    case (LEFT):
                        switch(number){
                            case 4: number = 3;break;
                            case 3: number = 2; break;
                            case 9: number = 8; break;
                            case 6: number = 5; break;
                            case 7: number = 6; break;
                            case 8: number = 7; break;
                            case 12: number = 11; break;
                            case 11: number = 10; break;
                        }
                        break;
                }
            }
            switch (number){
                case 10: answer += "A"; break;
                case 11: answer += "B"; break;
                case 12: answer += "C"; break;
                case 13: answer += "D"; break;
                default: answer+= number;
            }
        }
        System.out.print("Real Bathroom code: ");
        return answer;
    }

    private int[][] initKeypad() {
        int[][] pad = new int[KEYPAD_SIZE][KEYPAD_SIZE];
        int count = 1;
        for(int i = 0; i<KEYPAD_SIZE;i++){
            for(int j = 0; j < KEYPAD_SIZE; j++){
                pad[i][j] = count;
                count++;
            }
        }

        return pad;
    }


}
