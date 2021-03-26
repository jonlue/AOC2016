package main.days;

import main.AOCRiddle;

import java.util.*;

public class Day19 extends AOCRiddle {
    public Day19(String in, int part) {
        super(in, part);
        numberElves = Integer.parseInt(getInput());
    }

    private final int numberElves;
    private List<Integer> elves;
    private LinkedList<Integer> first;
    private LinkedList<Integer> second;


    @Override
    protected String solve1() {

        init();
        List<Integer> halfElves;
        do {
            halfElves = new ArrayList<>();
            for (int i = 0; i < elves.size(); i += 2) {
                halfElves.add(elves.get(i));
            }
            if (elves.size() % 2 == 1) {
                halfElves.remove(0);
            }

            elves = halfElves;

        } while (halfElves.size() != 1);

        return Integer.toString(elves.get(0));
    }

    @Override
    protected String solve2() {
        initP2();

        int numElves = numberElves;
        boolean even = numElves % 2 == 0;
        while (numElves != 1) {
            if (even) {
                second.removeFirst();
                even = false;
            } else {
                first.removeLast();
                even = true;
            }

            second.addLast(first.removeFirst());
            first.addLast(second.removeFirst());

            --numElves;
        }
        int result;
        if (first.size() == 0) {
            result = second.get(0);

        } else {
            result = first.get(0);
        }
        return String.valueOf(result);
    }

    private void init() {
        elves = new ArrayList<>();
        for (int i = 1; i < numberElves + 1; i++) {
            elves.add(i);
        }
    }

    private void initP2() {
        first = new LinkedList<>();
        second = new LinkedList<>();
        for (int i = 1; i < numberElves + 1; ++i) {
            if (i <= Math.ceil((double) numberElves / 2)) {
                first.add(i);
            } else {
                second.add(i);
            }
        }
    }
}