package main.days;

import main.AOCRiddle;

import java.util.ArrayList;
import java.util.List;

public class Day07 extends AOCRiddle {
    public Day07(String in, int part) {
        super(in,part);
    }

    @Override
    protected String solve1() {
        int sum = 0;
        for(String s : getInput().split("\n")){

            List<String> before = getBefore(s);
            List<String> between = getBetween(s);
            if(supportsTLS(before,between)){
                sum += 1;
            }
        }
        System.out.print("Count of IPV7 supporting TLS: ");
        return Integer.toString(sum);
    }

    @Override
    protected String solve2() {
        int sum = 0;
        for(String s : getInput().split("\n")){
            List<String> outSequence = getSequence(getBefore(s));
            List<String> inSequence = getSequence(getBetween(s));

            if(containsOppositeSequence(outSequence,inSequence)){
                sum+=1;
            }
        }

        System.out.print("Count of IPV7 supporting SSL: ");
        return Integer.toString(sum);
    }

    private boolean containsOppositeSequence(List<String> outSequence, List<String> inSequence) {
        for(String o : outSequence){
            for(String i : inSequence){
                if(o.charAt(0) == i.charAt(1) && o.charAt(1) == i.charAt(0)){
                    return true;
                }
            }
        }
        return false;
    }

    private List<String> getSequence(List<String> between) {
        List<String> sequences = new ArrayList<>();

        for(String s : between){
            for(int i = 0; i<s.length()-2; i++){
                if(s.charAt(i) == s.charAt(i+2) && s.charAt(i) != s.charAt(i+1)){
                    sequences.add(s.substring(i,i+3));
                }
            }
        }
        return sequences;
    }

    private List<String> getBetween(String ip) {
        List<String> betweens = new ArrayList<>();
        for(String s : ip.split("]")){
            if(s.contains("[")){
                s=s.substring(s.indexOf("[")+1);
                betweens.add(s);
            }
        }
        return betweens;
    }

    private List<String> getBefore(String ip) {
        List<String> befores = new ArrayList<>();
        for(String s : ip.split("\\[")){
            if(s.contains("]")){
                s=s.substring(s.indexOf("]")+1);
            }
            befores.add(s);
        }
        return befores;
    }

    private boolean supportsTLS(List<String> before, List<String> between) {
        boolean out = false;
        boolean in = false;
        for(String s : before){
            if(containsSequence(s)){
                out = true;
                break;
            }
        }
        for(String s : between){
            if(containsSequence(s)){
                in = true;
                break;
            }
        }
        return out && !in;
    }

    private boolean containsSequence(String sequence){
        for(int i = 0; i < sequence.length()-3;i++){
            if(sequence.charAt(i) == sequence.charAt(i+3) && sequence.charAt(i+1) == sequence.charAt(i+2) && sequence.charAt(i) != sequence.charAt(i+1)){
                return true;
            }
        }
        return false;
    }

}
