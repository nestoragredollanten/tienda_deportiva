package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utilidad {

    public static String convertir_fecha(java.util.Date fecha) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        String f = sd.format(fecha);
        return f;
    }
    public static String convertir_fecha(java.sql.Date fecha) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        String f = sd.format(fecha);
        return f;
    }
    public static java.util.Date convertir_fecha(String fecha) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sd.parse(fecha);
        } catch (ParseException ex) {
            return null;
        }
    }
    public static java.util.Date convertir_hora(String hora) {
        SimpleDateFormat sd = new SimpleDateFormat("HH:mm");
        try {
            return sd.parse(hora);
        } catch (ParseException ex) {
            return null;
        }
    }
    public static String convertir_hora(java.sql.Date fecha) {
        SimpleDateFormat sd = new SimpleDateFormat("HH:mm");
        String f = sd.format(fecha);
        return f;
    }
    public static java.util.Date convertir_hora_fecha(String horafecha) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return sd.parse(horafecha);
        } catch (ParseException ex) {
            return null;
        }
    }
    public static String convertir_hora_fecha(java.sql.Date fecha) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String f = sd.format(fecha);
        return f;
    }

    public static final String PERSISTENCE = "Tienda_DeportivaPU";
}
