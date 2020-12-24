package main.day16;

import main.AOCRiddle;

public class DragonChecksum extends AOCRiddle {
    public DragonChecksum(String in, int part) {
        super(in, part);
    }

    private static final int LENGTH1 = 272;
    private static final int LENGTH2 = 35651584;

    @Override
    protected String solve1() {
        return run(LENGTH1);
    }

    private String run(int length1) {
        String data = getInput();
        while(data.length() < length1){
            data = dragonCurve(data);
        }
        data = data.substring(0, length1);
        String checkSum = data;
        while (checkSum.length() % 2 == 0){
            checkSum = checkSum(checkSum);
        }

        return checkSum;
    }

    @Override
    protected String solve2() {
        return run(LENGTH2);
    }


    private String dragonCurve(String data){
        String b = reverse(data);
        StringBuilder sb = new StringBuilder();
        sb.append(data);
        sb.append(0);
        sb.append(b);
        return sb.toString();
    }

    private String reverse(String data) {
        StringBuilder sb = new StringBuilder();
        for(int i = data.length()-1; i > -1; i--){
            sb.append(reverseChar(data.charAt(i)));
        }
        return sb.toString();
    }

    private char reverseChar(char c) {
        if(c == '0'){
            return '1';
        }else {
            return '0';
        }
    }

    private String checkSum(String checksum){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<checksum.length(); i+=2){
            if(checksum.charAt(i) == checksum.charAt(i+1)){
                sb.append(1);
            }else{
                sb.append(0);
            }
        }
        return sb.toString();
    }


}
