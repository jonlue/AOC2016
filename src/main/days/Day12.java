package main.days;

import main.AOCRiddle;
import main.util.AsembunnyInterpreter;

public class Day12 extends AOCRiddle {
    public Day12(String in, int part) {
        super(in, part);
    }

    @Override
    protected String solve1() {
        AsembunnyInterpreter asembunnyInterpreter = new AsembunnyInterpreter(getInput());
        return asembunnyInterpreter.run();
    }

    @Override
    protected String solve2() {
        AsembunnyInterpreter asembunnyInterpreter = new AsembunnyInterpreter(getInput(),"c",1);
        return asembunnyInterpreter.run();
    }


}
