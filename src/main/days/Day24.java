package main.days;

import main.AOCRiddle;
import main.util.Position;

import java.util.*;
import java.util.List;

public class Day24 extends AOCRiddle {

    private Map<Integer, Position> goals;
    private boolean[][] maze;
    private int numberGoals;
    private final int[][] graph;

    public Day24(String in, int part) {
        super(in, part);
        init();
        graph = getDistancesGraph();
    }

    @Override
    protected String solve1() {
        return String.valueOf(findShortestPath(false));
    }


    @Override
    protected String solve2() {
        return String.valueOf(findShortestPath(true));
    }

    // dijkstra
    private int findShortestPath(boolean part2) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < numberGoals; i++) {
            if (i == 0) continue;
            Set<Integer> visited = new HashSet<>();
            visited.add(0);
            min = Math.min(min, findShortestPath(graph, i, visited, graph[0][i], part2));
        }
        return min;
    }

    private int findShortestPath(int[][] graph, int start, Set<Integer> alreadyVisited, int distance, boolean part2) {
        boolean last = true;
        int min = Integer.MAX_VALUE;
        alreadyVisited.add(start);
        for (int i = 0; i < numberGoals; i++) {
            if (i == start || alreadyVisited.contains(i)) continue;
            last = false;
            Set<Integer> visited = new HashSet<>(alreadyVisited);
            min = Math.min(min, findShortestPath(graph, i, visited, distance + graph[start][i], part2));
        }
        if (last){
            if(part2){
                return distance + graph[start][0];
            }
            return distance;
        }
        return min;
    }


    private int[][] getDistancesGraph() {
        int[][] graph = new int[numberGoals][numberGoals];
        // TODO improve by doing a BFS from start to all goals instead of each separately
        for (int i = 0; i < numberGoals - 1; i++) {
            for (int j = i + 1; j < numberGoals; j++) {
                int distance = BFS(goals.get(i), goals.get(j));
                graph[i][j] = distance;
                graph[j][i] = distance;
            }
        }
        return graph;
    }

    private int BFS(Position start, Position goal) {
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
        if (maze[pos.y][pos.x + 1]) {
            neighbors.add(new Position(pos.x + 1, pos.y, pos.distance + 1));
        }
        if (maze[pos.y][pos.x - 1]) {
            neighbors.add(new Position(pos.x - 1, pos.y, pos.distance + 1));
        }
        if (maze[pos.y + 1][pos.x]) {
            neighbors.add(new Position(pos.x, pos.y + 1, pos.distance + 1));
        }
        if (maze[pos.y - 1][pos.x]) {
            neighbors.add(new Position(pos.x, pos.y - 1, pos.distance + 1));
        }
        return neighbors;
    }

    private void init() {
        goals = new HashMap<>();
        String[] lines = getInput().split("\n");
        maze = new boolean[lines.length][lines[0].length()];
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                if (Character.isDigit(lines[y].charAt(x))) {
                    goals.put(Character.getNumericValue(lines[y].charAt(x)), new Position(x, y, 0));
                }
                maze[y][x] = lines[y].charAt(x) != '#';
            }
        }
        numberGoals = goals.size();
    }
}
