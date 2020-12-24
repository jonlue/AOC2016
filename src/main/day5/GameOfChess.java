package main.day5;

import main.AOCRiddle;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GameOfChess extends AOCRiddle {

    private MessageDigest messageDigest;

    public GameOfChess(String in, int part) {
        super(in, part);
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String solve1() {
        long i = 0;
        StringBuilder password = new StringBuilder();
        while(password.length()!=8){
            messageDigest.update((getInput()+i).getBytes());
            byte[] temp = messageDigest.digest();
            if(startsWith5Zeros(temp)){
                password.append(String.format("%x",temp[2]));
            }
            messageDigest.reset();
            i++;
        }
        System.out.print("password: ");
        return password.toString();
    }

    @Override
    protected String solve2() {
        long i = 0;
        boolean running = true;
        int count = 0;
        StringBuilder password = new StringBuilder("        ");
        while(running){
            messageDigest.update((getInput()+i).getBytes());
            byte[] temp = messageDigest.digest();
            if(startsWith5Zeros(temp)){
                int pos = temp[2]<0?-temp[2]:temp[2];
                if(pos<8 && password.charAt(pos) == ' ') {
                    count++;
                    String t = String.format("%02x", temp[3]);
                    password.setCharAt(pos, t.charAt(0));
                    if (count == 8) {
                        running = false;
                    }
                }
            }
            messageDigest.reset();
            i++;
        }
        System.out.print("Second password: ");
        return password.toString();
    }

    private boolean startsWith5Zeros(byte[] digest) {
        return (digest[0] == 0 && digest[1] == 0 && digest[2] <= 0x0F && digest[2] >=0);
    }


}
