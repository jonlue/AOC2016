package main.day6;

import main.AOCRiddle;

public class SignalsAndNoise extends AOCRiddle {
    public SignalsAndNoise(String in, int part) {
        super(in,part);
    }

    @Override
    protected String solve1() {
        StringBuilder message = new StringBuilder();
        int lenWord = getInput().split("\n")[0].length();
        int lenList = getInput().split("\n").length;
        StringBuilder letter;
        for(int i = 0; i < lenWord; i++){
            letter = new StringBuilder();
            int j = 0;
            for(String s: getInput().split("\n")){
                letter.append(s.charAt(i));
                j++;
            }

            int[] count = new int[lenList];
            j = 0;
            for(char c : letter.toString().toCharArray()){
                count[j] = (int) letter.chars().filter(ch -> ch == c).count();
                j++;
            }

            message.append(letter.charAt(getMax(count)));
        }
        System.out.println("Message without noise: ");
        return message.toString();
    }

    @Override
    protected String solve2() {
        StringBuilder message = new StringBuilder();
        int lenWord = getInput().split("\n")[0].length();
        int lenList = getInput().split("\n").length;
        StringBuilder letter;
        for(int i = 0; i < lenWord; i++){
            letter = new StringBuilder();
            int j = 0;
            for(String s: getInput().split("\n")){
                letter.append(s.charAt(i));
                j++;
            }

            int[] count = new int[lenList];
            j = 0;
            for(char c : letter.toString().toCharArray()){
                count[j] = (int) letter.toString().chars().filter(ch -> ch == c).count();
                j++;
            }

            message.append(letter.charAt(getMin(count)));
        }
        System.out.println("Message without noise in modified repetition code: ");
        return message.toString();
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
