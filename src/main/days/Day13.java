package main.days;

import main.AOCRiddle;

public class Day13 extends AOCRiddle {

    private final int favouriteNumber;

    private static final int X_START = 1;
    private static final int Y_START = 1;
    private static final int X_GOAL = 31;
    private static final int Y_GOAL = 39;

    public Day13(String in, int part) {
        super(in, part);
        favouriteNumber = Integer.parseInt(getInput());
    }

    @Override
    protected String solve1() {
        printMap();
        int step = 0;
        int x = X_START;
        int y = Y_START;
        System.out.println("implement search algo");
        return Integer.toString(step);
    }

    private boolean isOpen(int x,int y){
        int number = calculateFormula(x,y);
        number += favouriteNumber;
        String binary = Integer.toBinaryString(number);
        return (binary.chars().filter(ch -> ch == '1').count()) % 2 == 0;
    }
    private int calculateFormula(int x, int y){
        return x*x + 3*x + 2*x*y + y + y*y;
    }

    private void printMap(){
        StringBuilder map = new StringBuilder();
        for(int y = 0; y < Y_GOAL+1; ++y){
            for(int x = 0; x < X_GOAL+1; ++x){
                if(x == X_START && y == Y_START){
                    map.append("S");
                }else if(x == X_GOAL && y == Y_GOAL){
                    map.append("G");
                }
                if(isOpen(x,y)){
                    map.append(".");
                }else{
                    map.append("#");
                }
            }
            map.append("\n");
        }
        System.out.println(map.toString());
    }


    @Override
    protected String solve2() {
        return null;
    }
}
