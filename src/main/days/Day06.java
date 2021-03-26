package main.days;

import main.AOCRiddle;

public class Day06 extends AOCRiddle {
    public Day06(String in, int part) {
        super(in,part);
    }

    @Override
    protected String solve1() {
        return removeNoise(false);
    }

    private int[] getCounts(int lenList, String letter) {
        int[] count = new int[lenList];
        int j = 0;
        for(char c : letter.toCharArray()){
            count[j] = (int) letter.chars().filter(ch -> ch == c).count();
            j++;
        }
        return count;
    }

    private String removeNoise(boolean part2){
        StringBuilder message = new StringBuilder();
        int lenWord = getInput().split("\n")[0].length();
        int lenList = getInput().split("\n").length;
        String letter;
        for(int i = 0; i < lenWord; i++){
            letter = getLetters(i);
            int[] count = getCounts(lenList, letter);
            if(part2){
                message.append(letter.charAt(getMin(count)));
            }else {
                message.append(letter.charAt(getMax(count)));
            }
        }
        System.out.println("Message without noise: ");
        return message.toString();
    }


    @Override
    protected String solve2() {
        System.out.println("Message without noise in modified repetition code: ");
        return removeNoise(true);
    }

    private String getLetters(int i) {
        StringBuilder letter;
        letter = new StringBuilder();
        for (String s : getInput().split("\n")) {
            letter.append(s.charAt(i));
        }
        return letter.toString();
    }

    private int getMax(int[] count) {
        int max = 0;
        int pos = 0;
        for(int i = 0; i< count.length; i++){
            if(count[i]>max){
                max = count[i];
                pos = i;
            }
        }
        return pos;
    }



    private int getMin(int[] count) {
        int pos = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i< count.length; i++){
            if(count[i]<min){
                min = count[i];
                pos = i;
            }
        }
        return pos;
    }
}
