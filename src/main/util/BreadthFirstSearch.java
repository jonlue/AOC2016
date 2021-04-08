package main.util;

import java.util.*;

public class BreadthFirstSearch {

    private Position start;
    private Position goal;
    private final boolean[][] maze;
    private int width;
    private int height;

    /**
     * True -> spaces where you can walk
     * TODO check to add a function where neighbors are checked extra for
     ***/
    public BreadthFirstSearch(Position start, Position goal, boolean[][] maze){
        this.start = start;
        this.goal = goal;
        this.maze = maze;
        setSize();
    }

    private void setSize() {
        this.height = maze.length-1;
        this.width = maze[0].length-1;
    }

    public BreadthFirstSearch(boolean[][] maze){
        this.maze = maze;
        setSize();
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public void setGoal(Position goal) {
        this.goal = goal;
    }

    public int run(){
        Queue<Position> toVisit = new ArrayDeque<>();
        Set<Position> visited = new HashSet<>();
        toVisit.add(start);
        visited.add(start);

        while (!toVisit.isEmpty()) {
            Position pos = toVisit.poll();
            if (pos.equals(goal)) {
                return pos.distance;
            }

            for (Position p : getNeighbors(pos)) {
                if (!visited.contains(p)) {
                    visited.add(p);
                    toVisit.add(p);
                }
            }
        }
        return -1;
    }

    private List<Position> getNeighbors(Position pos) {
        // edges are all walls therefore no check if out of bounds
        List<Position> neighbors = new ArrayList<>();
        if (pos.x < width && maze[pos.y][pos.x + 1]) {
            neighbors.add(new Position(pos.x + 1, pos.y, pos.distance + 1));
        }
        if (pos.x > 0 && maze[pos.y][pos.x - 1]) {
            neighbors.add(new Position(pos.x - 1, pos.y, pos.distance + 1));
        }
        if (pos.y < height && maze[pos.y + 1][pos.x]) {
            neighbors.add(new Position(pos.x, pos.y + 1, pos.distance + 1));
        }
        if (pos.y > 0 && maze[pos.y - 1][pos.x]) {
            neighbors.add(new Position(pos.x, pos.y - 1, pos.distance + 1));
        }
        return neighbors;
    }
}
