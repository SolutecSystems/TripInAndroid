package solutecsystem.com.tripinapp.Pojo;

/**
 * Created by Shadowns on 30/01/2017.
 */

public class Tags {

     private String Nombre;
     private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre(int position) {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }
}
