package main.day12;

import main.AOCRiddle;
import main.util.AsembunnyInterpreter;

public class LeonardosMonorail extends AOCRiddle {
    public LeonardosMonorail(String in, int part) {
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
