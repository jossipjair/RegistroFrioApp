package com.example.jescalaya.appmovimientopaletafrio.controlador;

import android.content.Context;

import com.example.jescalaya.appmovimientopaletafrio.modelo.M_UbicacionPaleta;

public class C_UbicacionPaleta {

    public void sincronizaUbicacionPaleta(Context context){
        M_UbicacionPaleta ubicacionPaleta = new M_UbicacionPaleta();
        ubicacionPaleta.insertarCalibreLocalToService(context);
    }

    public int buscarIdUbicacion(Context context, String prefijo){
        M_UbicacionPaleta ubicacionPaleta =  new M_UbicacionPaleta();
        return ubicacionPaleta.buscarIdUbicacion(context, prefijo);
    }

    public String[] muestraUbicacion(Context context, int suc_Id) {
        M_UbicacionPaleta ubicacion = new M_UbicacionPaleta();
        return ubicacion.mostrarLineas(context, suc_Id);
    }


}
