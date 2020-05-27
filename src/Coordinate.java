import java.util.ArrayList;

public class Coordinate {

    static String alphabet = "ABCDEFGHIJ";

    private String name;
    private String alignment;
    private int letter;
    private int num;
    private ArrayList<String> possibleDirections;

    public Coordinate(){
        name = "A1";
        alignment = "unknown";
    }

    public Coordinate(String name){
        this.name = name;
        alignment = "unknown";

        int[] i = nameToIntegers();
        this.letter = i[0];
        this.num = i[1];
    }

    public Coordinate(int letter, int num){
        this.name = getName();
        this.letter = letter;
        this.num = num;
    }

    public String getName(){
        return alphabet.substring(this.letter, this.letter + 1) + Integer.toString(this.num);
    }

    public String getAlignment(){
        return alignment;
    }

    public void setAlignment(String alignment){
        this.alignment = alignment;
    }

    public int getLetter(){
        return letter;
    }

    public int getNum(){
        return num;
    }

    public int[] nameToIntegers(){
        int[] i = new int[2];

        i[0] = alphabet.indexOf(this.name.substring(0,1));
        i[1] = Integer.parseInt(this.name.substring(1));

        return i;
    }

    public ArrayList<String> getDirections(int letter, int num){
        possibleDirections = new ArrayList<String>();

        if(letter != 0 && letter != 9 && num != 0 && num != 9){
            System.out.println("Coordinate is in the middle");

            possibleDirections.add("up");
            possibleDirections.add("down");
            possibleDirections.add("left");
            possibleDirections.add("right");
        }else if(letter != num && Math.abs(letter - num) != 9){
            System.out.println("Coordinate on the edge but not a corner");

            if(num == 0){

                possibleDirections.add("up");
                possibleDirections.add("right");
                possibleDirections.add("down");
            }
            if(num == 9){
                possibleDirections.add("up");
                possibleDirections.add("left");
                possibleDirections.add("down");
            }
            if(letter == 0){
                possibleDirections.add("left");
                possibleDirections.add("right");
                possibleDirections.add("down");
            }
            if(letter == 9){
                possibleDirections.add("left");
                possibleDirections.add("right");
                possibleDirections.add("up");
            }
        }else{
            System.out.println("Coordinate is a corner");

            if(letter == 0 && num == 0){
                possibleDirections.add("right");
                possibleDirections.add("down");
            }
            if(letter == 0 && num == 9){
                possibleDirections.add("left");
                possibleDirections.add("down");
            }
            if(letter == 9 && num == 0){
                possibleDirections.add("up");
                possibleDirections.add("right");
            }
            if(letter == 9 && num == 9){
                possibleDirections.add("up");
                possibleDirections.add("left");
            }
        }

        return possibleDirections;
    }

    public void removeDirection(String direction){
        possibleDirections.remove(direction);
        System.out.println("Direction removed: "+direction);
        System.out.println(possibleDirections);
    }

}
