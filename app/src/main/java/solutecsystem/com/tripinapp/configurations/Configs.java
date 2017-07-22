package solutecsystem.com.tripinapp.configurations;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import solutecsystem.com.tripinapp.Pojo.Empresas;

/**
 * Created by Shadowns on 27/12/2016.
 */

public class Configs {



    public static AppCompatActivity activity;
    public static int select = 0;
    public static int param = 0;
    public static int emp = 0;
    public static int idCity = 0;
    public static int stre = 0;
    public static String LAT = "0";
    public static String LONG = "0";
    public static String NOMBRE = "0";
    public static String URL = "0";
    public  static int list = 0;
    public  static int alac = 0;


    public static ArrayList<Empresas> arrStre = new ArrayList<Empresas>();
    public static ArrayList<Empresas> arrgps = new ArrayList<Empresas>();


    public static String URL_Categories = "http://worldtripin.com/web/app_dev.php/tr1p1ng17/movil/categoria";
    public static final String URL_SUBCATEGORIES = "http://worldtripin.com/web/app_dev.php/tr1p1ng17/movil/subCategoria";
    public static final String URL_STORES = "http://worldtripin.com/web/app_dev.php/tr1p1ng17/movil/establecimiento";
    public static final String URL_SAVECOMMENT = "http://worldtripin.com/app_dev.php/tr1p1ng17/movil/guardarComentario";
    public static final String URL_STORESEARCH = "http://worldtripin.com/app_dev.php/tr1p1ng17/movil/mapa";
    public static String URL_GALERIA = "http://worldtripin.com/web/app_dev.php/tr1p1ng17/movil/galeria";
    public static String URL_CITY = "http://worldtripin.com/web/app_dev.php/tr1p1ng17/movil/ciudad";
    public static String URL_DATOS = "http://worldtripin.com/web/app_dev.php/tr1p1ng17/movil/guardarIndicadores";
    public static String URL_MAPS = "http://worldtripin.com/web/app_dev.php/tr1p1ng17/movil/mapaSubCategoria";

    /**
     * Datos de descarga en los Tabs
     */
    public static final String TAG_ID = "id";
    public static final String TAG_tit = "nombre";
    public static final String TAG_titing = "nombreIngl";
    public static final String TAG_IMAG = "imagen";
    public static String idiom;

    /**
     * Datos de descarga para los establecimientos
     **/
     public static final String TAG_HORARIOLU = "horarioLu";
     public static final String TAG_HORARIOMA = "horarioMa";
     public static final String TAG_HORARIOMI = "horarioMi";
     public static final String TAG_HORARIOJU = "horarioLu";
     public static final String TAG_HORARIOVI = "horarioVi";
     public static final String TAG_HORARIOSA = "horarioSa";
     public static final String TAG_HORARIODO = "horarioDo";
     public static final String TAG_DIR = "direccion";
     public static final String TAG_FACE = "facebook";
     public static final String TAG_TW = "twitter";
     public static final String TAG_IN = "instagram";
     public static final String TAG_GG = "google";
     public static final String TAG_LICENCIA = "tipoLicencia";
     public static final String TAG_HISTORY = "historia";
     public static final String TAG_HISTORYING = "historiaIngles";
     public static final String TAG_LAT = "latitud";
     public static final String TAG_LON = "longitud";
     public static final String TAG_TEL = "telefono";
     public static final String TAG_PORTADA = "imagenPortada";
     public static final String TAG_ETIING= "etiquetaIng";
     public static final String TAG_TELEFONO2= "telefono2";
     public static final String TAG_MAIL= "correo";
     public static final String TAG_URL = "url";

    /**
     * Datos a Pedir de las ciudades.
     */

    public static final String TAG_CITY = "ciudad";
    public static final String TAG_ESTATE = "departamento";
    public static final String TAG_COUNTRY = "pais";

    public static int getList() {
        return list;
    }

    public static void setList(int list) {
        Configs.list = list;
    }

    public static int getAlac() {
        return alac;
    }

    public static void setAlac(int alac) {
        Configs.alac = alac;
    }

    public static String getIdiom() {
        return idiom;
    }

    public static void setIdiom(String idiom) {
        Configs.idiom = idiom;
    }

    public static ArrayList<Empresas> getArrStre() {
        return arrStre;
    }

    public static void setArrStre(ArrayList<Empresas> arrStre) {
        Configs.arrStre = arrStre;
    }

    public static ArrayList<Empresas> getArrgps() {
        return arrgps;
    }

    public static void setArrgps(ArrayList<Empresas> arrgps) {
        Configs.arrgps = arrgps;
    }

    public static String getNOMBRE() {
        return NOMBRE;
    }

    public static void setNOMBRE(String NOMBRE) {
        Configs.NOMBRE = NOMBRE;
    }

    public static int getSelect() {
        return select;
    }

    public static void setSelect(int select) {
        Configs.select = select;
    }

    public static int getParam() {
        return param;
    }

    public static void setParam(int param) {
        Configs.param = param;
    }

    public static int getEmp() {
        return emp;
    }

    public static void setEmp(int emp) {
        Configs.emp = emp;
    }

    public static int getStre() {
        return stre;
    }

    public static void setStre(int stre) {
        Configs.stre = stre;
    }

    public static String getLAT() {
        return LAT;
    }

    public static void setLAT(String LAT) {
        Configs.LAT = LAT;
    }

    public static String getLONG() {
        return LONG;
    }

    public static void setLONG(String LONG) {
        Configs.LONG = LONG;
    }

    public static int getIdCity() {
        return idCity;
    }

    public static void setIdCity(int idCity) {
        Configs.idCity = idCity;
    }

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        Configs.URL = URL;
    }

    public static void saveNumListaConf(AppCompatActivity context, int num) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("listaconf", num);
        editor.apply();
    }
    public static int getumListaConf(AppCompatActivity context) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getInt("listaconf", -1);
    }

}
