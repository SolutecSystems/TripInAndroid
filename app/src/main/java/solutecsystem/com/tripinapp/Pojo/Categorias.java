package solutecsystem.com.tripinapp.Pojo;

/**
 * Created by Shadowns on 06/12/2016.
 */

public class Categorias {
    private String Nombre, imagen, Nombre_ing;
    private int id;

    public Categorias(String nombre, String imagen) {
        Nombre = nombre;
        this.imagen = imagen;
    }

    public Categorias() {

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNombre_ing() {
        return Nombre_ing;
    }

    public void setNombre_ing(String nombre_ing) {
        Nombre_ing = nombre_ing;
    }

    public String getImagen(int position) {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
