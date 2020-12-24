package main.day3;

import main.AOCRiddle;

import java.util.ArrayList;
import java.util.List;

public class ThreeSideSquares extends AOCRiddle {

    private static final int NUMBER_OF_SIDES = 3;

    public ThreeSideSquares(String in, int part) {
        super(in, part);
    }

    @Override
    protected String solve1() {
        int trianglesCount = 0;
        for(String s : getInput().split("\n")){
            int[] sides = new int[NUMBER_OF_SIDES];
            int i = 0;
            for(String t : s.split(" ")){
                if(t.equals("")) continue;
                sides[i] = Integer.parseInt(t);
                i++;
            }

            if (isTriangle(sides[0],sides[1],sides[2])) {
                trianglesCount++;
            }

        }
        System.out.print("Number of Triangles: ");
        return Integer.toString(trianglesCount);
    }

    private boolean isTriangle(int a, int b, int c) {
        //Sum of two sides have to be larger than third
        return (a + b > c &&
                a + c > b &&
                b + c > a
                );
    }

    @Override
    protected String solve2() {
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        List<Integer> third = new ArrayList<>();
        for(String s : getInput().split("\n")){
            int i = 0;
            for(String t : s.split(" ")){
                if(t.equals("")) continue;
                if(i == 0) first.add(Integer.parseInt(t));
                if(i == 1) second.add(Integer.parseInt(t));
                if(i == 2) third.add(Integer.parseInt(t));
                i++;
            }
        }
        TriangleCounter one = new TriangleCounter(first);
        TriangleCounter two = new TriangleCounter(second);
        TriangleCounter three = new TriangleCounter(third);
        one.start();
        two.start();
        three.start();
        try {
            one.join();
            two.join();
            three.join();
        }catch (Exception e){
            e.printStackTrace();
        }

        int sum = one.getCount() + two.getCount() + three.getCount();

        System.out.print("Real number of Triangles: ");
        return Integer.toString(sum);
    }
}
