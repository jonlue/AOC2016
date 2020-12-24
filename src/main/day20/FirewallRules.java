package main.day20;

import main.AOCRiddle;

import java.util.*;
import java.util.stream.Collectors;

public class FirewallRules extends AOCRiddle {
    public FirewallRules(String in, int part) {
        super(in, part);
    }

    private static final long MAX_IP = 4294967295L;
    private Map<Long,Long> ranges;
    private List<Long> startRanges;

    @Override
    protected String solve1() {
        init();
        long smallest = 0;
        Collections.sort(startRanges);

        for(Long l : startRanges){
            if(smallest < l){
                break;
            }
            smallest = ranges.get(l) + 1;
        }

        return Long.toString(smallest);
    }

    @Override
    protected String solve2() {
        init();
        Collections.sort(startRanges);
        Map<Long,Long> betterBlacklist = new HashMap<>();
        long allowedIPS = 0;
        long smallest = 0;

        long end = 0;
        for(long first : startRanges){
            if(first < end){
                continue;
            }
            long newEnd = ranges.get(first);
            for(long second : startRanges){
                if(first==second){
                    continue;
                }
                long secondEnd = ranges.get(second);
                if(second-1<=newEnd){
                    newEnd = Math.max(secondEnd,newEnd);
                }else{
                    break;
                }
            }
            betterBlacklist.put(first,newEnd);
            end = newEnd;
        }
        startRanges = new ArrayList<>(betterBlacklist.keySet());
        Collections.sort(startRanges);
        for(Long l : startRanges){
            while(smallest < l){
                ++allowedIPS;
                ++smallest;
            }
            smallest = betterBlacklist.get(l) + 1;
        }
        while(smallest<MAX_IP){
            ++allowedIPS;
            ++smallest;
        }

        return Long.toString(allowedIPS);
    }

    private void init() {
        ranges = new HashMap<>();
        startRanges = new ArrayList<>();

        for(String s : getInput().split("\n")){
            Long from = Long.valueOf(s.split("-")[0]);
            Long to = Long.valueOf(s.split("-")[1]);
            ranges.put(from,to);
            startRanges.add(from);
        }
    }



}
