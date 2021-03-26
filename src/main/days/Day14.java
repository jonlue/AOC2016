package main.days;

import main.AOCRiddle;
import main.util.HexStringEncoding;

import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Day14 extends AOCRiddle {
    public Day14(String in, int part) {
        super(in, part);
    }

    private static final String ALGORITHM = "MD5";
    private static final char NOT_FOUND = ' ';
    private static final int NUM_KEYS = 64;
    private static final int MAX_DIFFERENCE = 1000;
    private static MessageDigest MD = null;
    private final String SALT = getInput();

    @Override
    protected String solve1() {
        return run(1);
    }

    @Override
    protected String solve2() {
        return run(2017);
    }

    private String run(int timesHash){
        List<Integer> keyIndex = new ArrayList<>();

        List<String> hashes = new LinkedList<>();
        for(int i = 0; i < MAX_DIFFERENCE; ++i){
            hashes.add(generateKey(i,timesHash));
        }

        int index = 0;
        while(keyIndex.size()<NUM_KEYS){
            for(int i = 0; i <hashes.size(); i++){
                if(keyIndex.size()>= NUM_KEYS){
                    break;
                }
                index++;
                String key = hashes.get(i);
                hashes.set(i,generateKey(index + MAX_DIFFERENCE,timesHash));
                char c = checkRepeating3(key);
                if(c != NOT_FOUND) {
                    for(String s : hashes) {
                        char c5 = checkRepeating5(s);
                        if (c == c5) {
                            keyIndex.add(index);
                            break;
                        }
                    }
                }
            }
        }
        return Long.toString(keyIndex.get(keyIndex.size()-1));
    }

    private char checkRepeating3(String key) {

        for(int i = 0 ; i<key.length()-2; ++i){
            if(key.charAt(i) == key.charAt(i+1) && key.charAt(i) == key.charAt(i+2)){
                return key.charAt(i);
            }
        }
        return NOT_FOUND;
    }

    private char checkRepeating5(String key) {
        for(int i = 0 ; i<key.length()-4; ++i){
            if(key.charAt(i) == key.charAt(i+1) && key.charAt(i) == key.charAt(i+2) && key.charAt(i) == key.charAt(i+3) &&key.charAt(i) == key.charAt(i+4)){
                return key.charAt(i);
            }
        }
        return NOT_FOUND;
    }

    private String generateKey(long suffix,int times){
        times = times -1;
        if(MD == null){
            try {
                MD = MessageDigest.getInstance(ALGORITHM);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        byte[] byteKey = MD.digest((SALT + suffix).getBytes());
        byteKey = HexStringEncoding.encode(byteKey,false, ByteOrder.BIG_ENDIAN).getBytes();

        String hash = null;
        for(int i = 0; i<times; i++){
            byteKey = MD.digest(byteKey);
            hash = HexStringEncoding.encode(byteKey,false, ByteOrder.BIG_ENDIAN);
            byteKey = hash.getBytes();
        }
        return hash;
    }

    /* SLOW VARIANT
    keyIndex = new ArrayList<>();
        int count = 0;

        while(keyIndex.size()<NUM_KEYS){
            String key = generateKey(count,1);
            char c = checkRepeating3(key);
            if(c != NOT_FOUND){
                for(long i = count+1; i< count+1001; ++i){
                    key = generateKey(i,1);

                    char c5 = checkRepeating5(key);
                    if(c == c5 && c5 != NOT_FOUND){
                        keyIndex.add(count);
                        break;
                    }
                }
            }
            ++count;
        }
        return Long.toString(keyIndex.get(keyIndex.size()-1));
     */
}
