import java.util.ArrayList;

public class Coordinate {

    static String alphabet = "ABCDEFGHIJ";

    private String name;
    private String alignment; //horizontal or vertical
    private String position; //position on the board (middle, edge, corner)

    private int letter;
    private int num;

    private ArrayList<String> possibleDirections;
    private ArrayList<String> neighbors;

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
        possibleDirections = findDirections(this.letter, this.num);
    }

    public Coordinate(int letter, int num){
        name = getName();
        alignment = "unknown";
        this.letter = letter;
        this.num = num;
        possibleDirections = findDirections(this.letter, this.num);
    }

    public String findName(int letter, int num){
        return alphabet.substring(letter, letter + 1) + Integer.toString(num);
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

    //A = 0
    public int getLetter(){
        return letter;
    }

    //Returns the actual number NOT the index
    public int getNum(){
        return num;
    }

    public String getPosition(){
        return position;
    }

    public int[] nameToIntegers(){
        int[] i = new int[2];

        i[0] = alphabet.indexOf(this.name.substring(0,1));
        i[1] = Integer.parseInt(this.name.substring(1));

        return i;
    }

    public ArrayList<String> findDirections(int letter, int num){
        possibleDirections = new ArrayList<String>();
        neighbors = new ArrayList<String>();

//        System.out.println("findDirections name: "+getName());
//        System.out.println("findDirections letter: "+letter);
//        System.out.println("findDirections num: "+num);

        if(letter != 0 && letter != 9 && num != 1 && num != 10){
            //System.out.println("Coordinate is in the middle");
            position = "middle";
            possibleDirections.add("up");
            possibleDirections.add("down");
            possibleDirections.add("left");
            possibleDirections.add("right");

            neighbors.add(findName(letter-1, num)); //Up
            neighbors.add(findName(letter+1,num)); //Down
            neighbors.add(findName(letter,num-1)); //Left
            neighbors.add(findName(letter,num+1)); //Right

        }else if(letter != num - 1 && Math.abs(letter - (num-1)) != 9){
            //System.out.println("Coordinate on the edge but not a corner");

            if(letter == 0){
                position = "top";
                possibleDirections.add("down");
                possibleDirections.add("left");
                possibleDirections.add("right");

                neighbors.add(findName(letter+1,num)); //Down
                neighbors.add(findName(letter,num-1)); //Left
                neighbors.add(findName(letter,num+1)); //Right
            }else if(letter == 9){
                position = "bottom";
                possibleDirections.add("up");
                possibleDirections.add("left");
                possibleDirections.add("right");

                neighbors.add(findName(letter-1, num)); //Up
                neighbors.add(findName(letter,num-1)); //Left
                neighbors.add(findName(letter,num+1)); //Right
            }else if(num == 1){
                position = "left";
                possibleDirections.add("up");
                possibleDirections.add("down");
                possibleDirections.add("right");

                neighbors.add(findName(letter-1, num)); //Up
                neighbors.add(findName(letter+1,num)); //Down
                neighbors.add(findName(letter,num+1)); //Right
            }else if(num == 10){
                position = "right";
                possibleDirections.add("up");
                possibleDirections.add("down");
                possibleDirections.add("left");

                neighbors.add(findName(letter-1, num)); //Up
                neighbors.add(findName(letter+1,num)); //Down
                neighbors.add(findName(letter,num-1)); //Left
            }
        }else{
            //System.out.println("Coordinate is a corner");

            if(letter == 0 && num == 1){
                alignment = "top left";
                possibleDirections.add("down");
                possibleDirections.add("right");

                neighbors.add(findName(letter+1,num)); //Down
                neighbors.add(findName(letter,num+1)); //Right
            }else if(letter == 0 && num == 10){
                alignment = "top right";
                possibleDirections.add("down");
                possibleDirections.add("left");

                neighbors.add(findName(letter+1,num)); //Down
                neighbors.add(findName(letter,num-1)); //Left
            }else if(letter == 9 && num == 1){
                alignment = "bottom left";
                possibleDirections.add("up");
                possibleDirections.add("right");

                neighbors.add(findName(letter-1, num)); //Up
                neighbors.add(findName(letter,num+1)); //Right
            }else if(letter == 9 && num == 10){
                alignment = "bottom right";
                possibleDirections.add("up");
                possibleDirections.add("left");

                neighbors.add(findName(letter-1, num)); //Up
                neighbors.add(findName(letter,num-1)); //Left
            }
        }

        return possibleDirections;
    }

    public ArrayList<String> getDirections(){
        return possibleDirections;
    }

    public void removeDirection(String direction){
        possibleDirections.remove(direction);
        System.out.println("Direction removed: "+direction);
        System.out.println(possibleDirections);
    }

    public ArrayList<String> getNeighbors(){
        return neighbors;
    }
}
