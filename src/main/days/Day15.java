package main.days;

import main.AOCRiddle;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15 extends AOCRiddle {
    private List<String> discs;
    public Day15(String in, int part) {
        super(in, part);
        parse();
    }

    @Override
    protected String solve1() {
        //BigInteger result = chineseRemainder(discs);
        return otherSolve(discs).toString();
    }

    @Override
    protected String solve2() {
        String number = Integer.toString(discs.size()+1);
        String size = "11";
        String time = "0";
        String pos = "0";
        discs.add(number + ","+ size + "," + time + "," + pos);
        return otherSolve(discs).toString();
    }

    private BigInteger otherSolve(List<String> discs) {
        int len = discs.size();
        BigInteger[] m = getMs(discs);
        BigInteger[] a = getAs(discs);

        boolean running = true;
        BigInteger i = BigInteger.ZERO;
        while(running){

            for(int j = 0; j<len; j++){
                BigInteger temp = i.add(a[j]);
                if(temp.mod(m[j]).compareTo(BigInteger.ZERO) != 0){
                    break;
                }
                if(j == len-1){
                    running = false;
                    break;
                }
            }
            i = i.add(BigInteger.ONE);
        }
        return i.subtract(BigInteger.ONE);
    }

    /*
    private BigInteger chineseRemainder(List<String> discs) {
        int len = discs.size();
        BigInteger[] m = getMs(discs);
        BigInteger[] a = getAs(discs);

        BigInteger bigM = getBigM(m);
        BigInteger[] M = getMxs(m,bigM);

        BigInteger x = BigInteger.ZERO;
        BigInteger[] u = new BigInteger[len];

        for(int i = 0; i<len; i++){
            u[i] = M[i].modInverse(m[i]);

            x = x.add(u[i].multiply(a[i]).multiply(m[i]));
        }
        return x.mod(bigM);
    }
    */


    private BigInteger[] getAs(List<String> discs) {
        int len = discs.size();
        BigInteger[] result = new BigInteger[len];
        int i = 0;
        for(String s : discs){
            List<Integer> temp = new ArrayList<>();
            for(String n : s.split(",")){
                temp.add(Integer.parseInt(n));
            }
            result[i] = new BigInteger(String.valueOf(temp.get(0) + temp.get(3)));
            ++i;
        }
        return result;
    }

    private BigInteger[] getMs(List<String> discs) {
        int len = discs.size();
        BigInteger[] result = new BigInteger[len];
        int i = 0;
        for(String s : discs){
            List<Integer> temp = new ArrayList<>();
            for(String n : s.split(",")){
                temp.add(Integer.parseInt(n));
            }
            result[i] = new BigInteger(String.valueOf(temp.get(1)));
            ++i;
        }
        return result;
    }

    private BigInteger[] getMxs(BigInteger[] m, BigInteger bigM) {
        BigInteger[] result = new BigInteger[m.length];
        for(int i= 0; i<m.length; i++){
            result[i] = bigM.divide(m[i]);
        }
        return result;
    }

    private BigInteger getBigM(BigInteger[] m) {
        BigInteger result = BigInteger.ONE;
        for(BigInteger i : m){
            result = result.multiply(i);
        }
        return result;
    }


    private void parse(){
        discs = new ArrayList<>();
        Pattern p = Pattern.compile("\\d+");

        for(String s : getInput().split("\n")){
            StringBuilder sb = new StringBuilder();
            Matcher m = p.matcher(s);
            while(m.find()) {
                sb.append(m.group());
                sb.append(",");
            }
            discs.add(sb.toString());
        }
    }
}
