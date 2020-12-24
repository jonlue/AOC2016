package main.util;

import java.util.HashMap;
import java.util.Map;

public class AsembunnyInterpreter {

    private static final String CPY = "cpy";
    private static final String INC = "inc";
    private static final String DEC = "dec";
    private static final String JNZ = "jnz";
    private static final String TGL = "tgl";
    private static final String OUT = "out";

    private static final String A = "a";
    private static final String B = "b";
    private static final String C = "c";
    private static final String D = "d";

    private static final int MAX_CLOCK = 10;

    private Map<String,Integer> registers;

    private String[] instructions;
    private final String input;
    private boolean clock = false;

    public AsembunnyInterpreter(String input){
        init();
        this.input = input;
    }

    public AsembunnyInterpreter(String input, boolean clock){
        init();
        this.input = input;
        this.clock = clock;
    }
    public AsembunnyInterpreter(String input, String register, int startValue){
        init();
        this.input = input;
        registers.put(register,startValue);
    }

    public void setRegister(String register, int value){
        registers.put(register,value);
    }

    private void init() {
        registers=new HashMap<>();
        registers.put(A,0);
        registers.put(B,0);
        registers.put(C,0);
        registers.put(D,0);
    }

    public String run(){
        int countClock = 0;
        StringBuilder clockString = new StringBuilder();
        int i = 0;
        instructions = input.split("\n");
        while (i < instructions.length) {
            String[] s = instructions[i].split(" ");
            switch(s[0]) {
                case CPY:
                    saveValue(s[2],getValue(s[1]));
                    ++i;
                    break;
                case INC:
                    saveValue(s[1],getValue(s[1])+1);
                    ++i;
                    break;
                case DEC:
                    saveValue(s[1],getValue(s[1])-1);
                    ++i;
                    break;
                case JNZ:
                    if(getValue(s[1]) != 0){
                        i += getValue(s[2]);
                    }else{
                        ++i;
                    }
                    break;
                case TGL:
                    toggle(i+getValue(s[1]));
                    ++i;
                    break;
                case OUT:
                    if(countClock == MAX_CLOCK){
                        return clockString.toString();
                    }
                    clockString.append(getValue(s[1]));
                    ++i;
                    ++countClock;
                    break;
                default:
                    System.out.println(":(");
                    return "DNF";
            }
        }
        return Integer.toString(getValue(A));
    }

    private void toggle(int i) {
        if(i < 0 || i >= instructions.length){
            return;
        }
        String ins = instructions[i];
        String command = ins.split(" ")[0];
        switch (command){
            case(INC):
                instructions[i] = "dec" + ins.substring(3);
                break;
            case(DEC): case (TGL):
                instructions[i] = "inc" + ins.substring(3);
                break;
            case(CPY):
                instructions[i] = "jnz" + ins.substring(3);
                break;
            case(JNZ):
                instructions[i] = "cpy" + ins.substring(3);
                break;
        }
    }



    private int getValue(String reg){
        if(registers.containsKey(reg)){
            return registers.get(reg);
        }else{
            return Integer.parseInt(reg);
        }
    }

    private void saveValue(String register,int value){
        if(registers.put(register,value) == null){
            registers.remove(register);
        }
    }
}
