package main.util;

public class VaultRoom {

    public String code;
    public final int x;
    public final int y;
    public final int distance;

    public VaultRoom(String code, int x, int y,int distance) {
        this.code = code;
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}
