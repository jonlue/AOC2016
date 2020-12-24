package main.day23;

import main.AOCRiddle;
import main.util.AsembunnyInterpreter;

public class SafeCracking extends AOCRiddle {
    public SafeCracking(String in, int part) {
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
