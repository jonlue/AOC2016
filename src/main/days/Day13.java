package main.days;

import main.AOCRiddle;
import main.util.Position;

import java.util.*;
import java.util.List;

public class Day13 extends AOCRiddle {

    private final int favouriteNumber;

    private static final Position START = new Position(1, 1, 0);
    private static final Position GOAL = new Position(31, 39, 0);
    private final Queue<Position> toVisit;
    private final Set<Position> visited;

    public Day13(String in, int part) {
        super(in, part);
        favouriteNumber = Integer.parseInt(getInput());

        toVisit = new ArrayDeque<>();
        visited = new HashSet<>();
        toVisit.add(START);
        visited.add(START);
    }

    @Override
    protected String solve1() {
        return Integer.toString(findWay());
    }

    @Override
    protected String solve2() {
        return String.valueOf(pointsWithDistance(50));
    }

    private int findWay() {
        while (!toVisit.isEmpty()) {
            Position pos = toVisit.poll();
            if (pos.x == GOAL.x && pos.y == GOAL.y) {
                return pos.distance;
            }

            for (Position p : getNeighbors(pos)) {
                if (!visited.contains(p)) {
                    visited.add(p);
                    if (isOpen(p.x, p.y)) {
                        toVisit.add(p);
                    }
                }
            }
        }
        return -1;
    }

    // 1663 too high
    private int pointsWithDistance(int dist) {
        Set<Position> possible = new HashSet<>();
        possible.add(START);

        while (!toVisit.isEmpty()) {
            Position pos = toVisit.poll();
            if (pos.distance == dist) {
                continue;
            }

            for (Position p : getNeighbors(pos)) {
                if (!visited.contains(p)) {
                    visited.add(p);
                    if (isOpen(p.x, p.y)) {
                        toVisit.add(p);
                        possible.add(p);
                    }
                }
            }
        }
        return possible.size();
    }

    private List<Position> getNeighbors(Position pos) {
        List<Position> neighbors = new ArrayList<>();
        neighbors.add(new Position(pos.x + 1, pos.y, pos.distance + 1));
        if (pos.x > 0) {
            neighbors.add(new Position(pos.x - 1, pos.y, pos.distance + 1));
        }
        neighbors.add(new Position(pos.x, pos.y + 1, pos.distance + 1));
        if (pos.y > 0) {
            neighbors.add(new Position(pos.x, pos.y - 1, pos.distance + 1));
        }
        return neighbors;
    }

    private boolean isOpen(int x, int y) {
        int number = calculateFormula(x, y);
        number += favouriteNumber;
        String binary = Integer.toBinaryString(number);
        return (binary.chars().filter(ch -> ch == '1').count()) % 2 == 0;
    }

    private int calculateFormula(int x, int y) {
        return x * x + 3 * x + 2 * x * y + y + y * y;
    }

}
