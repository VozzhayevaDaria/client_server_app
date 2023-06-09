package CollectionFiles;

import java.util.ArrayList;
import java.util.List;

public class Coordinates {
    private Double x;
    private float y; //Поле не может быть null

    public ArrayList getCoordinates(){
        List list = new ArrayList(2);
        list.add(x);
        list.add(y);
        return (ArrayList) list;
    }

    public Coordinates(){
    }
    public Coordinates(Double x, float y){
        this.x = x;
        this.y = y;
    }

    public Double getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    public String toString(){
        return "(" + x.toString() + "; " + Double.valueOf(y).toString() + ")";
    }

}
