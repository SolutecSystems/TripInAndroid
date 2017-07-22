package solutecsystem.com.tripinapp.Pojo;

/**
 * Created by Shadowns on 29/12/2016.
 */

public class Subcategorias {
    private String Nombre, imagen;
    private int id;

    public Subcategorias(String nombre, String imagen) {
        Nombre = nombre;
        this.imagen = imagen;
    }

    public Subcategorias() {

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
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
