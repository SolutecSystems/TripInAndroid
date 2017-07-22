package solutecsystem.com.tripinapp.Pojo;

import java.util.ArrayList;

/**
 * Created by Shadowns on 06/12/2016.
 */

public class Empresas {


    private String nombre,licencia, historia, comentarios, imagen, fb, twt, insta, gg , horarioweek, horarioweekend,  telefono, direccion, portada;
    private String Telefono2, email;
    private int id, calificación;
    private String latitud, longitud;
    private String etiquetas, url;

    public String getTelefono2() {
        return Telefono2;
    }

    public void setTelefono2(String telefono2) {
        Telefono2 = telefono2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getImagen(int position) {
        return imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCalificación() {
        return calificación;
    }

    public void setCalificación(int calificación) {
        this.calificación = calificación;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getTwt() {
        return twt;
    }

    public void setTwt(String twt) {
        this.twt = twt;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public String getHorarioweek() {
        return horarioweek;
    }

    public void setHorarioweek(String horarioweek) {
        this.horarioweek = horarioweek;
    }

    public String getHorarioweekend() {
        return horarioweekend;
    }

    public void setHorarioweekend(String horarioweekend) {
        this.horarioweekend = horarioweekend;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
