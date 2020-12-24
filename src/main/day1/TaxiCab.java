package main.day1;

import main.AOCRiddle;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class TaxiCab extends AOCRiddle {

    private int x,y;
    private  DIRECTION direction;
    private static final char RIGHT = 'R';
    private static final int START_COORDINATE_X = 0;
    private static final int START_COORDINATE_Y = 0;
    private static final Point FOUND_COORDINATE = new Point(Integer.MAX_VALUE,Integer.MAX_VALUE);

    public TaxiCab(String in, int part) {
        super(in.replaceAll(" ",""), part);
    }

    protected String solve1(){
        String[] ins = getInput().split(",");

        x = START_COORDINATE_X;
        y = START_COORDINATE_Y;
        direction = DIRECTION.NORTH;
        int distance;

        for(String s : ins){
            direction = getNewDirection(direction,s.charAt(0) == RIGHT);
            distance = Integer.parseInt(s.substring(1));
            updateCoordinates(direction,distance);
        }

        System.out.print("Distance from 0,0: ");
        return Integer.toString(Math.abs(x+y));
    }

    protected String solve2(){
        x = START_COORDINATE_X;
        y = START_COORDINATE_Y;
        direction =DIRECTION.NORTH;
        int distance;

        Set<Point> coordinates = new HashSet<>();

        for(String s : getInput().split(",")){
            direction = getNewDirection(direction,s.charAt(0) == RIGHT);
            distance = Integer.parseInt(s.substring(1));
            coordinates.addAll(updateCoordinates(direction,distance,coordinates));
            if(coordinates.contains(FOUND_COORDINATE)){
                break;
            }
        }
        System.out.println("First twice visited location: " + x + "," + y);
        System.out.print("Distance: ");
        return Integer.toString(Math.abs(x+y));
    }

    private Set<Point> updateCoordinates(DIRECTION direction, int distance, Set<Point> coordinates) {
        for(int i = 1; i<=distance;i++) {
            switch (direction) {
                case NORTH:
                    y += 1;
                    break;
                case EAST:
                    x += 1;
                    break;
                case SOUTH:
                    y -= 1;
                    break;
                case WEST:
                    x -= 1;
                    break;
            }
            Point temp = new Point(x,y);
            if(coordinates.contains(temp)){
                coordinates.add(FOUND_COORDINATE);
                break;
            }
            coordinates.add(temp);
        }
        return coordinates;
    }

    private void updateCoordinates(DIRECTION direction, int distance) {
        switch (direction){
            case NORTH:
                y += distance;
                break;
            case EAST:
                x += distance;
                break;
            case SOUTH:
                y -= distance;
                break;
            case WEST:
                x -= distance;
                break;
        }
    }


    private DIRECTION getNewDirection(DIRECTION dir, boolean right){
        if(dir == DIRECTION.NORTH && right || dir == DIRECTION.SOUTH && !right){
            return DIRECTION.EAST;
        }else if(dir == DIRECTION.EAST && right || dir == DIRECTION.WEST && !right){
            return DIRECTION.SOUTH;
        }else if(dir == DIRECTION.SOUTH && right || dir == DIRECTION.NORTH && !right){
            return DIRECTION.WEST;
        }else{
            return DIRECTION.NORTH;
        }
    }
    enum DIRECTION {NORTH,EAST,SOUTH,WEST}
}
