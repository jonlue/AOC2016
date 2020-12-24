package main.day25;

import main.AOCRiddle;
import main.util.AsembunnyInterpreter;

public class ClockSignal extends AOCRiddle {
    public ClockSignal(String in, int part) {
        super(in, part);
    }

    private static final String CLOCK = "0101010101";

    @Override
    protected String solve1() {
        String producedClock = "";
        int count = -1;//360000;
        while(!producedClock.equals(CLOCK)){
            ++count;
            AsembunnyInterpreter asembunnyInterpreter = new AsembunnyInterpreter(getInput(),true);
            asembunnyInterpreter.setRegister("a",count);
            producedClock = asembunnyInterpreter.run();
        }

        return Integer.toString(count);
    }

    @Override
    protected String solve2() {
        return null;
    }
}
