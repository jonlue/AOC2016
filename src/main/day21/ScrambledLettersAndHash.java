package main.day21;

import main.AOCRiddle;

public class ScrambledLettersAndHash extends AOCRiddle {
    public ScrambledLettersAndHash(String in, int part) {
        super(in, part);
    }

    private String password = "abcdefgh";

    @Override
    protected String solve1() {
        for(String instruction : getInput().split("\n")){
            String[] part = instruction.split(" ");
            switch (part[0]){
                case "swap":
                    if(part[1].equals("position")){
                        swap(Integer.parseInt(part[2]),Integer.parseInt(part[5]));
                    }else{
                        swap(password.indexOf(part[2]),password.indexOf(part[5]));
                    }
                    break;
                case "rotate":
                    if(part[1].equals("left") || part[1].equals("right")){
                        boolean right = part[1].equals("right");
                        int steps = Integer.parseInt(part[2]);
                        rotate(right,steps);
                    }else{
                        int step = password.indexOf(part[6]);
                        if(step >= 4){
                            ++step;
                        }
                        ++step;
                        rotate(true,step);
                    }
                    break;
                case "reverse":
                    reverse(Integer.parseInt(part[2]),Integer.parseInt(part[4]));
                    break;
                case "move":
                    move(Integer.parseInt(part[2]),Integer.parseInt(part[5]));
                    break;
                default:
                    System.out.println(":(");
            }
        }
        return password;
    }

    private void move(int x, int y) {
        char atX = password.charAt(x);
        password = password.substring(0,x) + password.substring(x+1);
        password = password.substring(0,y) + atX + password.substring(y);
    }

    private void reverse(int x, int y) {
        String toReverse = password.substring(x,y+1);
        StringBuilder reversed = new StringBuilder();
        for(int i = toReverse.length()-1; i > -1; --i){
            reversed.append(toReverse.charAt(i));
        }
        password = password.substring(0,x) + reversed.toString() + password.substring(y+1);
    }

    private void rotate(boolean right, int steps) {
        for(int i = 0; i < steps; ++i) {
            StringBuilder rotatedPassword = new StringBuilder();
            if (right) {
                rotatedPassword.append(password.charAt(password.length() - 1));
                rotatedPassword.append(password, 0, password.length() - 1);
            } else {
                rotatedPassword.append(password.substring(1));
                rotatedPassword.append(password.charAt(0));
            }
            password = rotatedPassword.toString();
        }
    }

    private void swap(int x, int y) {
        char posX = password.charAt(x);
        password = password.substring(0,x) + password.charAt(y) + password.substring(x+1);
        password = password.substring(0,y) + posX + password.substring(y+1);
    }

    @Override
    protected String solve2() {
        password = "fbgdceah";
        String[] instructions = getInput().split("\n");
        for(int i = instructions.length-1; i > -1; --i){
            String[] part = instructions[i].split(" ");
            switch (part[0]){
                case "swap":
                    if(part[1].equals("position")){
                        swap(Integer.parseInt(part[2]),Integer.parseInt(part[5]));
                    }else{
                        swap(password.indexOf(part[2]),password.indexOf(part[5]));
                    }
                    break;
                case "rotate":
                    if(part[1].equals("left") || part[1].equals("right")){
                        boolean right = !part[1].equals("right");
                        int steps = Integer.parseInt(part[2]);
                        rotate(right,steps);
                    }else{
                        String originalPWScramble = password;
                        int newSteps = -1;
                        do{
                            password = originalPWScramble;
                            ++newSteps;
                            //rotate left to see if it produces the right output
                            rotate(false,newSteps);
                            //scramble the part again
                            int step = password.indexOf(part[6]);
                            if(step >= 4){
                                ++step;
                            }
                            ++step;
                            rotate(true, step);

                            //check if the right password was guessed
                        }while(!originalPWScramble.equals(password));

                        //descramble the password
                        rotate(false, newSteps);
                    }
                    break;
                case "reverse":
                    reverse(Integer.parseInt(part[2]),Integer.parseInt(part[4]));
                    break;
                case "move":
                    move(Integer.parseInt(part[5]),Integer.parseInt(part[2]));
                    break;
                default:
                    System.out.println(":(");
            }
        }
        return password;
    }
}
