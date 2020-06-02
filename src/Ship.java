import java.util.ArrayList;

public class Ship {
    private String alignment;
    private ArrayList<String> coordinates;

    public Ship(){
        alignment = "unknown";
    }

    public Ship(String alignment, ArrayList<String> coordinates){
        this.alignment = alignment;
        this.coordinates = coordinates;
    }

    public ArrayList<String> getCoordinates(){
        return coordinates;
    }

    public String getAlignment(){
        return alignment;
    }

    public void setAlignment(String a){
        alignment = a;
    }

    public boolean sunk(){
        if(coordinates.size() == 0){
            return true;
        }
        return false;
    }

}
