package main.days;

import main.AOCRiddle;
import main.util.Direction;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Day01 extends AOCRiddle {

    private int x,y;
    private Direction direction;
    private static final char RIGHT = 'R';
    private static final int START_COORDINATE_X = 0;
    private static final int START_COORDINATE_Y = 0;
    private static final Point FOUND_COORDINATE = new Point(Integer.MAX_VALUE,Integer.MAX_VALUE);

    public Day01(String in, int part) {
        super(in.replaceAll(" ",""), part);
    }

    protected String solve1(){
        String[] ins = getInput().split(",");

        x = START_COORDINATE_X;
        y = START_COORDINATE_Y;
        direction = Direction.NORTH;
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
        direction = Direction.NORTH;
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

    private Set<Point> updateCoordinates(Direction direction, int distance, Set<Point> coordinates) {
        for(int i = 1; i<=distance;i++) {
            switch (direction) {
                case NORTH -> y += 1;
                case EAST -> x += 1;
                case SOUTH -> y -= 1;
                case WEST -> x -= 1;
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

    private void updateCoordinates(Direction direction, int distance) {
        switch (direction) {
            case NORTH -> y += distance;
            case EAST -> x += distance;
            case SOUTH -> y -= distance;
            case WEST -> x -= distance;
        }
    }


    private Direction getNewDirection(Direction dir, boolean right){
        if(dir == Direction.NORTH && right || dir == Direction.SOUTH && !right){
            return Direction.EAST;
        }else if(dir == Direction.EAST && right || dir == Direction.WEST && !right){
            return Direction.SOUTH;
        }else if(dir == Direction.SOUTH && right || dir == Direction.NORTH && !right){
            return Direction.WEST;
        }else{
            return Direction.NORTH;
        }
    }
}
