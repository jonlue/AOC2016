package main.day4;

import main.AOCRiddle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObscuritySecurity extends AOCRiddle {

    private static final CharSequence SEARCHED_WORD = "northpole";
    private Map<String,Integer> realRooms;

    public ObscuritySecurity(String in, int part) {
        super(in, part);
    }

    @Override
    protected String solve1() {
        realRooms = new HashMap<>();

        for(String s : getInput().split("\n")) {
            if(isRealRoom(s)){
                realRooms.put(s,getID(s));
            }
        }

        return "Sum of real room sector IDs: " + sumRoomId(realRooms);
    }

    @Override
    protected String solve2() {
        solve1();
        int answer = -1;
        String room = "-";
        Map<String, Integer> realRoomNames = new HashMap<>();
        for(String s : realRooms.keySet()){
            String temp = decryptName(s,realRooms.get(s));
            realRoomNames.put(temp,realRooms.get(s));
            if(temp.contains(SEARCHED_WORD)){
                answer = realRooms.get(s);
                room=temp;
                break;
            }
        }
        System.out.print("Found Room: " + room + " ");
        return Integer.toString(answer);
    }

    private String decryptName(String roomName, Integer roomId) {
        StringBuilder sb = new StringBuilder();
        roomName = roomName.replaceAll("\\d", "");
        roomName = roomName.substring(0, roomName.indexOf("[")-1);
        roomId = roomId % 26;
        for (char c : roomName.toCharArray()) {
            if (c == '-') {
                sb.append(" ");
            } else {
                char t = (char) (c + roomId);
                if (t > 'z') {
                    t -= 26;
                }
                sb.append(t);
            }
        }
        return sb.toString();
    }

    private String sumRoomId(Map<String, Integer> rooms) {
        int sum = 0;
        for(String s : rooms.keySet()){
            sum+= rooms.get(s);
        }
        return Integer.toString(sum);
    }

    private boolean isRealRoom(String roomName) {
        String checkSum = roomName.split("\\[")[1].replaceAll("]","");
        String temp = generateCheckSum(countChars(roomName));
        return checkSum.equals(temp);
    }

    private String generateCheckSum(List<List<String>> chars) {
        StringBuilder checkSum = new StringBuilder();
        ArrayList<String> temp;
        while(checkSum.length() != 5){
            int most = 0;
            String mostChar = "";
            for(List<String> l : chars){
                if(Integer.parseInt(l.get(1)) > most){
                    most = Integer.parseInt(l.get(1));
                    mostChar = l.get(0);
                }
            }
            temp = new ArrayList<>();
            temp.add(mostChar);
            temp.add(Integer.toString(most));
            chars.remove(temp);
            checkSum.append(mostChar);
        }
        return checkSum.toString();

    }

    private List<List<String>> countChars(String roomName) {
        List<List<String>> chars = new ArrayList<>();
        char c = 'a';
        for(char i = 0; i<26; i++){
            char finalC = c;
            int count = (int) roomName.chars().filter(ch -> ch == finalC).count();
            if(count!= 0){
                ArrayList<String> temp = new ArrayList<>();
                temp.add(Character.toString(c));
                temp.add(Integer.toString(count));
                chars.add(temp);
            }
            c++;
        }
        return chars;
    }

    private Integer getID(String roomName) {
        return Integer.parseInt(roomName.replaceAll("\\D+",""));
    }

}
