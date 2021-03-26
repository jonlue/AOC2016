package main.days;

import main.AOCRiddle;

public class Day02 extends AOCRiddle {

    private static final char UP = 'U';
    private static final char RIGHT = 'R';
    private static final char DOWN = 'D';
    private static final char LEFT = 'L';
    private static final String REGEX_REPLACE_REPEATING = "((.)\\2{2})\\2+";
    private static final int STARTING_POS_X = 1;
    private static final int STARTING_POS_Y = 1;
    private static final int KEYPAD_SIZE = 3;

    public Day02(String in, int part) {
        super(in, part);
    }

    @Override
    protected String solve1() {
        setInput(getInput().replaceAll(REGEX_REPLACE_REPEATING,""));
        int[][] keypad = initKeypad();
        int x = STARTING_POS_X;
        int y = STARTING_POS_Y;
        StringBuilder answer = new StringBuilder();

        for(String s : getInput().split("\n")){
            for(char c : s.toCharArray()){
                switch (c) {
                    case (UP) -> y = Math.max(y - 1, 0);
                    case (RIGHT) -> x = Math.min(x + 1, KEYPAD_SIZE - 1);
                    case (DOWN) -> y = Math.min(y + 1, KEYPAD_SIZE - 1);
                    case (LEFT) -> x = Math.max(x - 1, 0);
                }
            }
            answer.append(keypad[y][x]);
        }
        System.out.print("Bathroom code: ");
        return answer.toString();
    }

    @Override
    protected String solve2() {
        int number = 5;
        StringBuilder answer = new StringBuilder();

        for(String s : getInput().split("\n")){
            for(char c : s.toCharArray()){
                switch (c) {
                    case (UP) -> number = switch (number) {
                        case 3 -> 1;
                        case 6 -> 2;
                        case 7 -> 3;
                        case 8 -> 4;
                        case 10 -> 6;
                        case 11 -> 7;
                        case 12 -> 8;
                        case 13 -> 11;
                        default -> number;
                    };
                    case (RIGHT) -> number = switch (number) {
                        case 2 -> 3;
                        case 3 -> 4;
                        case 5 -> 6;
                        case 6 -> 7;
                        case 7 -> 8;
                        case 8 -> 9;
                        case 10 -> 11;
                        case 11 -> 12;
                        default -> number;
                    };
                    case (DOWN) -> number = switch (number) {
                        case 2 -> 6;
                        case 3 -> 7;
                        case 4 -> 8;
                        case 6 -> 10;
                        case 7 -> 11;
                        case 8 -> 12;
                        case 1 -> 3;
                        case 11 -> 13;
                        default -> number;
                    };
                    case (LEFT) -> number = switch (number) {
                        case 4 -> 3;
                        case 3 -> 2;
                        case 9 -> 8;
                        case 6 -> 5;
                        case 7 -> 6;
                        case 8 -> 7;
                        case 12 -> 11;
                        case 11 -> 10;
                        default -> number;
                    };
                }
            }
            switch (number) {
                case 10 -> answer.append("A");
                case 11 -> answer.append("B");
                case 12 -> answer.append("C");
                case 13 -> answer.append("D");
                default -> answer.append(number);
            }
        }
        System.out.print("Real Bathroom code: ");
        return answer.toString();
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
