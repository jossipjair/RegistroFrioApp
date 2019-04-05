package com.example.jescalaya.appmovimientopaletafrio.vista;

import android.os.Parcel;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class VariableGeneral {

    public static int idUbicacionGasificado = 0;
    //IP de Servidor
    public static String ipServidor = "http://192.168.2.50:83/Api/"; //Servidor de Producción PDC
    public static int Sucursal_conf = 3;
    public static boolean estadoConexion = false;
    public static String macAdress;
    public static int privilegio = 0;
    public static int tunelId = 0;
    public static String tunelDescripcion = "";
    //public static String ipServidor = "http://172.16.0.19:19626/Api/"; //Servidor prueba 1 ADR
    //public static String ipServidor = "http://192.168.43.203:19626/Api/"; //Servidor prueba 2 RED EXTERNA
}
