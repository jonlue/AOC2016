package main.day19;

import main.AOCRiddle;

import java.util.*;

public class AnElephantNamedJoseph extends AOCRiddle {
    public AnElephantNamedJoseph(String in, int part) {
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
        do{
            halfElves = new ArrayList<>();
            for(int i = 0; i < elves.size(); i += 2){
                halfElves.add(elves.get(i));
            }
            if(elves.size()%2 == 1){
                halfElves.remove(0);
            }

            elves = halfElves;

        }while(halfElves.size()!=1);

        return Integer.toString(elves.get(0));
    }

    @Override
    protected String solve2() {
        initP2();

        int numElves = numberElves;
        boolean even = numElves % 2 == 0;
        while(numElves != 1){
            if(even){
                second.removeFirst();
                even = false;
            }else{
                first.removeLast();
                even = true;
            }

            second.addLast(first.removeFirst());
            first.addLast(second.removeFirst());

            --numElves;
        }
        int result;
        if(first.size() ==0){
            result=second.get(0);

        }else{
            result = first.get(0);
        }
        return String.valueOf(result);
    }

    private void init() {
        elves = new ArrayList<>();
        for(int i = 1; i < numberElves+1; i++){
            elves.add(i);
        }
    }

    private void initP2(){
        first = new LinkedList<>();
        second = new LinkedList<>();
        for(int i = 1; i<numberElves+1; ++i){
            if(i <= Math.ceil((double)numberElves / 2)){
                first.add(i);
            }else{
                second.add(i);
            }
        }
    }



    private void rebekka(){
        int nElf = 6;
        List<Integer> elfies = new LinkedList<>();//Arrays.asList(new int[5]);
        elfies.add(1);
        elfies.add(2);
        elfies.add(3);
        elfies.add(4);
        elfies.add(5);

        while (elfies.size()>1) {
            List<Integer> firstHalf = new ArrayList<>(elfies.subList(0,elfies.size()/2));
            elfies.removeAll(new ArrayList<>(firstHalf));
            elfies.remove(0);
            elfies.addAll(firstHalf);

        }
    }
}


/*
0    5        [1,2,3,4,5]
1    4    2    [1,2,4,5]
2    3    3    [1,2,4]
0    2    0    [2,4]
    1    1    [2]

0    6        [1,2,3,4,5,6]
1    5    3    [1,2,3,5,6]
2    4    3    [1,2,3,6]
2    3    0    [2,3,6]
0    2    0    [3,6]
    1    1    [3]

0    7        [1,2,3,4,5,6,7]
1    6    3   [1,2,3,5,6,7]
2    5    4   [1,2,3,5,7]
3    4    4   [1,2,3,5]
3    3    1   [1,3,5]
0    2    1   [1,5]
1    1        [5]
 */

