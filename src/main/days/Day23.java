package main.days;

import main.AOCRiddle;
import main.util.AsembunnyInterpreter;

public class Day23 extends AOCRiddle {
    public Day23(String in, int part) {
        super(in, part);
    }

    @Override
    protected String solve1() {
        AsembunnyInterpreter asembunnyInterpreter = new AsembunnyInterpreter(getInput(),"a",7);
        return asembunnyInterpreter.run();
    }

    @Override
    protected String solve2() {
        AsembunnyInterpreter asembunnyInterpreter = new AsembunnyInterpreter(getInput(),"a",12);
        return asembunnyInterpreter.run();
    }
}
