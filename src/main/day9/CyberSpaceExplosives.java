package main.day9;

import main.AOCRiddle;

public class CyberSpaceExplosives extends AOCRiddle {

    private String message;

    public CyberSpaceExplosives(String in, int part) {
        super(in,part);
    }

    @Override
    protected String solve1() {

        message = decompress(getInput());

        String t = message.replace(" ","").replaceAll("\r","");
        return "Length of 1 x decompressed message: " + t.length();
    }

    @Override
    protected String solve2() {
        long len = countLength(getInput(),true);
        return "Length of completely decompressed message: " + len;
    }



    private String decompress(String copy){
        System.out.println("test");
        StringBuilder stringBuilder = new StringBuilder();
        int lastIndex = 0;
        int i = copy.indexOf('(');
        while (i!=-1) {
            stringBuilder.append(copy, lastIndex, i);
            lastIndex = copy.indexOf(')',i) + 1;
            String[] temp = copy.substring(i+1,lastIndex-1).split("x");
            int end = lastIndex+Integer.parseInt(temp[0]);
            for(int j = 0; j < Integer.parseInt(temp[1]); j++) {
                stringBuilder.append(copy, lastIndex, end);
            }
            i = copy.indexOf('(',end-1);
            lastIndex = end;
        }
        stringBuilder.append(copy,lastIndex,copy.length());
        return stringBuilder.toString();
    }

    private long countLength(String in, boolean partTwo){
        long sum = 0;

        for(int i = 0; i< in.length(); i++){
            if(in.charAt(i) == '('){
                int bracketClose = in.indexOf(')',i);
                int length = Integer.parseInt(in.substring(i+1, bracketClose).split("x")[0]);
                int times = Integer.parseInt(in.substring(i+1, bracketClose).split("x")[1]);

                String stringIn = in.substring(bracketClose + 1, bracketClose + 1 + length);
                if(stringIn.contains("(") && partTwo){
                    sum += times* countLength(stringIn, partTwo);
                }else {
                    sum += length * times;
                }
                i = bracketClose + length;
            }else {
                sum++;
            }
        }
        return sum;
    }
}