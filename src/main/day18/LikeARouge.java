package main.day18;

import main.AOCRiddle;


public class LikeARouge extends AOCRiddle {
    public LikeARouge(String in, int part) {
        super(in, part);
    }

    private boolean[][] trappedRoom;
    private static final int FLOOR_1 = 40;
    private static final int FLOOR_2 = 400000;

    @Override
    protected String solve1() {
        parse(FLOOR_1);
        return run();
    }

    @Override
    protected String solve2() {
        parse(FLOOR_2);
        return run();
    }

    private String run() {
        for(int i = 1; i < trappedRoom.length; ++i){
            for(int j = 0; j< trappedRoom[i].length; j++){
                if(j == 0){
                    trappedRoom[i][j] = trappedRoom[i - 1][j + 1];
                }else if(j == trappedRoom[i].length-1){
                    trappedRoom[i][j] = trappedRoom[i - 1][j - 1];
                }else{
                    trappedRoom[i][j] = trappedRoom[i-1][j-1] ^ trappedRoom[i-1][j+1];
                }
            }
        }
        int saveTiles = countSaveTiles();
        return Integer.toString(saveTiles);
    }

    private int countSaveTiles() {
        int result = 0;
        for(boolean[] f :trappedRoom){
            for(boolean b : f){
                if(!b){
                    ++result;
                }
            }
        }
        return result;
    }

    private void parse(int rows) {
        String in = getInput();
        trappedRoom = new boolean[rows][in.length()];
        for(int i = 0; i< trappedRoom[0].length; ++i){
            trappedRoom[0][i] = in.charAt(i) == '^';
        }
    }


}
