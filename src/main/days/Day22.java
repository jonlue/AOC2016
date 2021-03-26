package main.days;

import main.AOCRiddle;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day22 extends AOCRiddle {
    public Day22(String in, int part) {
        super(in, part);
    }

    private List<List<Integer>> node;

    @Override
    protected String solve1() {
        init();
        int pairs = 0;
        for(int i = 0; i < node.size(); i++){
            for(int j = 0; j < node.size(); j++){
                if(i == j){
                    continue;
                }
                List<Integer> a = node.get(i);
                List<Integer> b = node.get(j);
                int used = a.get(3);
                int available = b.get(4);

                if(used != 0){
                    if(used <= available){
                        pairs++;
                    }
                }
            }
        }

        return Integer.toString(pairs);
    }

    private void init() {
        node = new ArrayList<>();
        Pattern p = Pattern.compile("\\d+");
        String[] lines = getInput().split("\n");
        for(int i = 0; i < lines.length; ++i) {
            if (i == 0 || i == 1) {
                continue;
            }
            Matcher m = p.matcher(lines[i]);

            List<Integer> values = new ArrayList<>();

            while (m.find()) {
                values.add(Integer.parseInt(m.group()));
            }
            node.add(values);
        }

    }

    @Override
    protected String solve2() {
        return null;
    }
}
