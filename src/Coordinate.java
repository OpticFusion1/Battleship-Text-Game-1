import java.util.ArrayList;

public class Coordinate {

    private String name;
    private String alignment;
    private ArrayList<String> directions;

    public Coordinate(){
        name = "A1";
        alignment = "unknown";
    }

    public Coordinate(String name){
        this.name = name;
        alignment = "unknown";
    }

    public String getName(){
        return name;
    }

    public String getAlignment(){
        return alignment;
    }

    public void setAlignment(String alignment){
        this.alignment = alignment;
    }

    public ArrayList<String> getDirections(int letter, int num){



        return directions;
    }
}
