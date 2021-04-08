package main.days;

import main.AOCRiddle;
import main.util.BreadthFirstSearch;
import main.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day22 extends AOCRiddle {

    private static final Pattern pattern = Pattern.compile("\\d+");

    private List<List<Integer>> nodes;
    private boolean[][] grid;
    private int width;
    private int height;
    private Position start;

    public Day22(String in, int part) {
        super(in, part);
    }

    @Override
    protected String solve1() {
        init();
        int pairs = 0;
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (i == j) {
                    continue;
                }
                List<Integer> a = nodes.get(i);
                List<Integer> b = nodes.get(j);
                int used = a.get(3);
                int available = b.get(4);

                if (used != 0) {
                    if (used <= available) {
                        pairs++;
                    }
                }
            }
        }

        return Integer.toString(pairs);
    }

    @Override
    protected String solve2() {
        initGrid();
        BreadthFirstSearch bfs = new BreadthFirstSearch(start, new Position(width-1, 0,0),grid);

        // distance to get to needed file + 5 steps per step the file moves across
        // width-1 for not moving it out and again -1 because with moving there we save one round
        return String.valueOf(bfs.run() + (5 * (width-2)));
    }

    private void initGrid() {
        getSize();
        grid = new boolean[height][width];
        String[] lines = getInput().split("\n");
        for (int i = 2; i < lines.length; ++i) {
            Matcher m = pattern.matcher(lines[i]);
            List<Integer> t = new ArrayList<>();
            while (m.find()) {
                t.add(Integer.parseInt(m.group()));
            }

            if (t.get(3) == 0) {
                start = new Position(t.get(0),t.get(1),0);
            }
            grid[t.get(1)][t.get(0)] = t.get(2) < 100;
        }
    }

    private void getSize() {
        String[] t = getInput().split("\n");
        Matcher m = pattern.matcher(t[t.length - 1]);
        List<Integer> temp = new ArrayList<>();
        while(m.find()){
            temp.add(Integer.parseInt(m.group()));
        }
        width = temp.get(0) + 1;
        height = temp.get(1) + 1;
    }

    private void init() {
        nodes = new ArrayList<>();

        String[] lines = getInput().split("\n");
        for (int i = 2; i < lines.length; ++i) {
            Matcher m = pattern.matcher(lines[i]);

            List<Integer> values = new ArrayList<>();

            while (m.find()) {
                values.add(Integer.parseInt(m.group()));
            }
            nodes.add(values);
        }

    }

}
