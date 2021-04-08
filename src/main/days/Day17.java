package main.days;

import main.AOCRiddle;
import main.util.HexStringEncoding;
import main.util.Position;
import main.util.VaultRoom;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Day17 extends AOCRiddle {
    public Day17(String in, int part) {
        super(in, part);
    }

    @Override
    protected String solve1() {
        return findShortestWay();
    }

    @Override
    protected String solve2() {
        return String.valueOf(findLongestWay());
    }

    private String findShortestWay() {
        Queue<VaultRoom> q = new ArrayDeque<>();
        q.add(new VaultRoom(getInput(), 0, 0, 0));

        while (!q.isEmpty()) {
            VaultRoom room = q.poll();
            if (room.x == 3 && room.y == 3) {
                return room.code.substring(getInput().length());
            }

            q.addAll(getNeighboringRooms(room));
        }
        return "";
    }

    private int findLongestWay() {
        int max = 0;
        Queue<VaultRoom> q = new ArrayDeque<>();
        q.add(new VaultRoom(getInput(), 0, 0, 0));

        while (!q.isEmpty()) {
            VaultRoom room = q.poll();
            if (room.x == 3 && room.y == 3) {
                max = Math.max(room.distance, max);
                continue;
            }

            q.addAll(getNeighboringRooms(room));
        }
        return max;
    }


    private List<VaultRoom> getNeighboringRooms(VaultRoom vaultRoom) {
        List<VaultRoom> neighbors = new ArrayList<>();
        boolean[] open = isOpen(vaultRoom.code);
        if (vaultRoom.y > 0 && open[0]) {
            neighbors.add(new VaultRoom(vaultRoom.code + "U", vaultRoom.x, vaultRoom.y - 1, vaultRoom.distance + 1));
        }
        if (vaultRoom.y < 3 && open[1]) {
            neighbors.add(new VaultRoom(vaultRoom.code + "D", vaultRoom.x, vaultRoom.y + 1, vaultRoom.distance + 1));
        }
        if (vaultRoom.x > 0 && open[2]) {
            neighbors.add(new VaultRoom(vaultRoom.code + "L", vaultRoom.x - 1, vaultRoom.y, vaultRoom.distance + 1));
        }
        if (vaultRoom.x < 3 && open[3]) {
            neighbors.add(new VaultRoom(vaultRoom.code + "R", vaultRoom.x + 1, vaultRoom.y, vaultRoom.distance + 1));
        }

        return neighbors;
    }


    public boolean[] isOpen(String code) {
        boolean[] openDoors = new boolean[4];
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(code.getBytes());
            String s = HexStringEncoding.encode(b,false, ByteOrder.BIG_ENDIAN);
            for (int i = 0; i < 4; i++) {
                openDoors[i] = s.charAt(i) >= 'b' && s.charAt(i) <= 'f';
            }
            md.reset();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return openDoors;
    }
}
