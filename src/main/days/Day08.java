package main.days;

import main.AOCRiddle;

import java.util.Arrays;

public class Day08 extends AOCRiddle {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 6;
    private static final String RECTANGLE = "rect ";
    private static final String ROTATE = "rotate ";
    private static final String ROW = "row y=";
    private static final String COLUMN = "column x=";
    private static final String ON = "#";
    private String[][] display;



    public Day08(String in, int part) {
        super(in,part);
    }

    @Override
    protected String solve1() {

        display = initDisplay();
        for(String s :getInput().split("\n")){
            if(s.startsWith(RECTANGLE)){
                s = s.replaceAll(RECTANGLE, "");
                String[] t = s.split("x");
                int x = Integer.parseInt(t[0]);
                int y = Integer.parseInt(t[1]);

                for(int i = 0; i < x; i++){
                    for(int j = 0; j<y; j++){
                        display[j][i] = ON;
                    }
                }

            }else if(s.startsWith(ROTATE)){
                s = s.replaceAll(ROTATE,"");
                if(s.startsWith(ROW)){
                    s = s.replaceAll(ROW,"").replaceAll(" ","");
                    String[] t = s.split("by");
                    int row = Integer.parseInt(t[0]);
                    int times = Integer.parseInt(t[1]) % WIDTH;

                    String[] copy = Arrays.copyOf(display[row],WIDTH);
                    for(int i = 0; i < WIDTH; i++){
                        if(i<times) {
                            copy[i] = display[row][WIDTH - times + i];
                        }else{
                            copy[i] = display[row][i-times];
                        }
                    }

                    display[row] = Arrays.copyOf(copy,WIDTH);

                }else if(s.startsWith(COLUMN)){
                    s = s.replaceAll(COLUMN,"").replaceAll(" ","");
                    String[] t = s.split("by");
                    int column = Integer.parseInt(t[0]);
                    int times = Integer.parseInt(t[1]) % HEIGHT;

                    String[] copy = getCopyOfColumn(display,column);
                    for(int i = 0; i < HEIGHT; i++){
                        if(i<times) {
                            copy[i] = display[HEIGHT - times + i][column];
                        }else{
                            copy[i] = display[i-times][column];
                        }
                    }

                    insertCopyOfColumn(display, copy, column);
                }
            }
        }
        return "Number of LED's lit: " + countLights(display);
    }

    @Override
    protected String solve2() {
        solve1();
        printDisplay(display);
        return "";
    }

    private String[][] initDisplay() {
        String[][] d =new String[HEIGHT][WIDTH];
        for(int i = 0; i< HEIGHT; i++){
            for(int j = 0; j< WIDTH; j++){
                d[i][j] = " ";
            }
        }
        return d;
    }



    private int countLights(String[][] display) {
        int sum = 0;
        for(int i = 0; i<HEIGHT; i++){
            for (int j = 0; j< WIDTH;j++){
                if(display[i][j].equals(ON)){
                    sum+=1;
                }
            }
        }
        return sum;
    }

    private void printDisplay(String[][] display) {
        for(int i = 0; i<HEIGHT;i++){
            for (int j = 0; j<WIDTH;j++){
                System.out.print(display[i][j]);
            }
            System.out.println();
        }
    }

    private void insertCopyOfColumn(String[][] display, String[] copy, int column) {
        for(int i = 0; i< HEIGHT;i++){
            display[i][column] = copy[i];
        }
    }

    private String[] getCopyOfColumn(String[][] display, int column) {
        String[] t = new String[HEIGHT];
        for(int i = 0; i< HEIGHT; i++) {
            t[i] = display[i][column];
        }
        return t;
    }

}
