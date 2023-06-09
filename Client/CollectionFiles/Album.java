package CollectionFiles;

public class Album {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private float sales; //Значение поля должно быть больше 0
    public Album(){}
    public Album(String name, float sales){
        this.name = name;
        this.sales = sales;
    }
    public String getName(){
        return this.name;
     }
    public float getSales(){
        return this.sales;
     }

    public void setName(String name) {
        this.name = name;
    }

    public void setSales(float sales) {
        this.sales = sales;
    }
    public String toString(){
        return name;
    }
}
