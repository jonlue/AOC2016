package main.util;

import java.util.List;

public class TriangleCounter extends Thread {

    private final List<Integer> list;
    private int count;

    public TriangleCounter(List<Integer> list) {
        this.list =list;
        this.count = 0;
    }

    @Override
    public void run() {
        for(int i = 0; i < list.size(); i += 3){
            if(isTriangle(list.get(i),list.get(i+1),list.get(i+2))){
                count++;
            }
        }
    }

    private boolean isTriangle(int a, int b, int c) {
        return (a + b > c && a + c > b && b + c > a);
    }

    public int getCount(){
        return count;
    }
}
