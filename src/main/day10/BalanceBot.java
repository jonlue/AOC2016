package main.day10;

import main.AOCRiddle;

import java.util.*;

public class BalanceBot extends AOCRiddle {

    private List<String> initials = new ArrayList<>();
    private Map<String,List<String>> instructions = new HashMap<>();
    private static final int COMPARE_VALUE_HIGHER = 61;
    private static final int COMPARE_VALUE_LOWER = 17;

    public BalanceBot(String in, int part) {
        super(in,part);
    }

    @Override
    protected String solve1() {
        return run(true);
    }

    private String run(boolean firstPart){
        setUp();
        Map<String,Integer> bots = new HashMap<>();
        Map<String,Integer> outputs = new HashMap<>();

        for(int i = 0; i < 250;++i ){
            bots.put(Integer.toString(i),-1);
        }
        Stack<String> botsWithTwo = new Stack<>();
        Stack<Integer> secondValues = new Stack<>();
        String botWithTwo = "";

        for(String s : initials){
            String bot = s.split(",")[1];
            int value = Integer.parseInt(s.split(",")[0]);
            if(hasValue(bots,bot)){
                botsWithTwo.push(bot);
                secondValues.push(value);
            }else {
                bots.put(bot, value);
            }
        }

        while(!botsWithTwo.empty()) {
            botWithTwo = botsWithTwo.pop();
            int secondValue = secondValues.pop();
            int firstValue = bots.get(botWithTwo);
            if(firstValue==-1){
                return "the end";
            }
            int lower = Integer.min(firstValue,secondValue);
            int higher = Integer.max(firstValue,secondValue);
            if (firstPart && higher == COMPARE_VALUE_HIGHER && lower == COMPARE_VALUE_LOWER) {
                return botWithTwo;
            }

            String newBot1 = instructions.get(botWithTwo).get(0);
            String newBot2 = instructions.get(botWithTwo).get(1);

            if(newBot1.equals(botWithTwo) || newBot2.equals(botWithTwo)){
                System.out.println(":(");
            }

            if(newBot1.startsWith("o")){
                outputs.put(newBot1.substring(1),lower);
                newBot1=null;
            }
            if(newBot2.startsWith("o")){
                outputs.put(newBot2.substring(1),higher);
                newBot2=null;
            }


            if(isNotOutput(newBot1) && hasValue(bots,newBot1) && isNotOutput(newBot2) && hasValue(bots,newBot2)){
                botsWithTwo.push(newBot1);
                secondValues.push(lower);
                botsWithTwo.push(newBot2);
                secondValues.push(higher);
            }else{
                if(isNotOutput(newBot1) && hasValue(bots,newBot1)){
                    botsWithTwo.push(newBot1);
                    secondValues.push(lower);
                    if(isNotOutput(newBot2) && !hasValue(bots,newBot2)){
                        bots.put(newBot2,higher);
                    }
                }else if(isNotOutput(newBot2) && hasValue(bots, newBot2)){
                    botsWithTwo.push(newBot2);
                    secondValues.push(higher);
                    if(isNotOutput(newBot1) && !hasValue(bots, newBot1)){
                        bots.put(newBot1,lower);
                    }
                } else{
                    bots.put(newBot2,higher);
                    bots.put(newBot1,lower);
                }
            }
            bots.put(botWithTwo,-1);

        }

        return multiplyOutputs(outputs);
    }

    private String multiplyOutputs(Map<String, Integer> map) {
        return Integer.toString(map.get("0")*map.get("1")*map.get("2"));
    }

    private boolean isNotOutput(String bot){
        return (bot != null);
    }
    private boolean hasValue(Map<String,Integer> bots, String bot){
        return (bots.get(bot) != -1);
    }

    @Override
    protected String solve2() {
        return run(false);
    }

    private void setUp() {
        String[] ins = getInput().split("\n");
        for(String s : ins){
            if(s.startsWith("v")){
                String t = s.replaceAll("value ","").replaceAll(" goes to bot ",",");
                initials.add(t);
                //instructions.remove(s);
            }else{
                s = s.replaceAll(" and high to bot ",",").replaceAll(" gives low to bot ",",").replaceAll("bot ","");
                s = s.replaceAll(" gives low to output ",",o").replaceAll(" and high to output ", ",o");
                String[] bots = s.split(",");
                List<String> temp = new ArrayList<>();
                temp.add(bots[1]);
                temp.add(bots[2]);
                instructions.put(bots[0],temp);
            }
        }
    }


}
