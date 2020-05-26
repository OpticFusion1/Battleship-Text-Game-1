import java.util.ArrayList;

public class Ship {
    private String direction;
    private ArrayList<String> coordinates;

    public Ship(String direction, ArrayList<String> coordinates){
        this.direction = direction;
        this.coordinates = coordinates;
    }

    public ArrayList<String> getCoordinates(){
        return coordinates;
    }

    public boolean sunk(){
        if(coordinates.size() == 0){
            return true;
        }
        return false;
    }

}
