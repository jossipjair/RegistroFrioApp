package com.example.jescalaya.appmovimientopaletafrio.BD;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class T_MovimientoPaleta {

    private static final String TABLA = "MovimientoPaleta";

    //CAMPOS DE TABLA
    private static final String MOVILPAL_ID = "MovPal_Id";
    private static final String VAR_ID = "Var_Id";
    private static final String UBIPAL_ID = "UbiPal_Id";
    private static final String REGPAL_ID = "RegPal_Id";
    private static final String REGPAL_NUMERO = "RegPal_Numero";
    private static final String REGPAL_JABAS = "RegPal_Jabas";  //Enviar en 0
    private static final String MOVPAL_FECHAHORINIMOV = "MovPal_FechaHorIniMov";
    private static final String MOVPAL_FECHAHORREGLOG = "MovPal_FechaHorRegLog";
    private static final String MOVPAL_FECHAHORCIEGAS = "MovPal_FechaHorCieGas"; //Enviar la fecha del log
    private static final String EST_ID = "Est_Id"; //siempre 1 = activo
    private static final String PROOPE_ID = "ProOpe_Id";//Proceso 4
    private static final String ES_SINCRONIZADO = "EsSincronizado";
    private static final String REMONTE_ID = "MovPal_RemonteId"; //Identificador para el conjunto de paletas
    private static final String MOVPAL_HORASALPAL = "MovPal_HoraSalPal";

    //CREACION DE TABLA
    public static final String CREATE_MOVIMIENTO_PALETA = "CREATE TABLE " + TABLA + "("
            + MOVILPAL_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + VAR_ID + " INTEGER NOT NULL, "
            + UBIPAL_ID + " INTEGER NOT NULL, "
            + REGPAL_ID + " TEXT NOT NULL, "
            + REGPAL_NUMERO + " TEXT NOT NULL, "
            + REGPAL_JABAS + " INTEGER NOT NULL, "
            + MOVPAL_FECHAHORINIMOV + " TEXT NOT NULL, "
            + MOVPAL_FECHAHORREGLOG + " TEXT NOT NULL, "
            + MOVPAL_FECHAHORCIEGAS + " TEXT NOT NULL, "
            + EST_ID + " INTEGER NOT NULL, "
            + PROOPE_ID + " INTEGER NOT NULL,"
            + ES_SINCRONIZADO + " INTEGER NOT NULL,"
            + REMONTE_ID + " TEXT NOT NULL,"
            + MOVPAL_HORASALPAL + " TEXT NOT NULL, CONSTRAINT unk_RegPalNumeroMP UNIQUE(RegPal_Numero));";

    //ELIMINACION DE TABLA
    public static final String DROP_MOVIMIENTO_PALETA = "DROP TABLE IF EXISTS " + TABLA + ";";

    public static String INSERT_MOVIMIENTO_PALETA(int Var_Id, int UbiPal_Id, String RegPal_Id, String RegPal_Numero, int regPal_Jabas, String MovPal_FechaHorIniMov, String MovPal_FechaHorRegLog, String MovPal_FechaHorCieGas, int estado, int proOpe_Id, String remonte_Id, String horaSalidaLimpieza) {
        return "INSERT INTO "
                + TABLA + "(" + VAR_ID + ","
                + UBIPAL_ID + "," + REGPAL_ID + "," + REGPAL_NUMERO + "," + REGPAL_JABAS + "," + MOVPAL_FECHAHORINIMOV
                + "," + MOVPAL_FECHAHORREGLOG + "," + MOVPAL_FECHAHORCIEGAS + "," + EST_ID + "," + PROOPE_ID + "," + ES_SINCRONIZADO + "," + REMONTE_ID + "," + MOVPAL_HORASALPAL + ") VALUES("
                + Var_Id + "," + UbiPal_Id + ",'" + RegPal_Id + "','" + RegPal_Numero + "'," + regPal_Jabas + ",'" + MovPal_FechaHorIniMov + "','" + fechaHoraSolo() + "','" + MovPal_FechaHorCieGas + "'," + 1 + "," + proOpe_Id + "," + 0 + ",'" + remonte_Id + "','" + horaSalidaLimpieza + "');";
    }

    public static String UPDATE_MOVIMIENTO_PALETA(int MovPal_Id, int Var_Id, int UbiPal_Id, int RegPal_Id, int RegPal_Numero, String MovPal_FechaHorIniMov, String MovPal_FechaHorRegLog, String MovPal_FechaHorCieGas) {
        return "UPDATE " + TABLA + " SET " + VAR_ID + "=" + Var_Id + ", " + UBIPAL_ID + "=" + UbiPal_Id + ","
                + REGPAL_ID + "=" + RegPal_Id + "," + REGPAL_NUMERO + "=" + RegPal_Numero + ","
                + MOVPAL_FECHAHORINIMOV + "='" + MovPal_FechaHorIniMov + "'," + MOVPAL_FECHAHORREGLOG + "='" + MovPal_FechaHorRegLog + "'," + MOVPAL_FECHAHORCIEGAS + "='" + MovPal_FechaHorCieGas + "' WHERE " + MOVILPAL_ID + "=" + MovPal_Id + ";";
    }

    public static String DELETE_MOVIMIENTO_PALETA(String regpalNumero) {
        return "DELETE FROM " + TABLA + " WHERE " + REGPAL_NUMERO + "='" + regpalNumero + "';";
    }


    public static String BUSCA_PALETA_LIBRE(String numPaleta, int proOpe_Id) {
        return "SELECT " + REGPAL_NUMERO + " FROM " + TABLA + " WHERE " + REGPAL_NUMERO + "='" + numPaleta + "' AND " + EST_ID + "=" + 1 + " AND " + PROOPE_ID + "=" + proOpe_Id + ";";
    }

    public static String SINCRONIZA_CON_BD_CENTRAL(int ubiPal_Id) {
        return "SELECT Var_Id, UbiPal_Id, RegPal_Id, RegPal_Numero, MovPal_FechaHorIniMov, MovPal_FechaHorRegLog, MovPal_FechaHorCieGas, Est_Id, ProOpe_Id, MovPal_Id, MovPal_HoraSalPal, RegPal_Jabas, MovPal_RemonteId FROM MovimientoPaleta  WHERE MovPal_FechaHorCieGas != '01/01/1990' AND Est_Id = 1 AND UbiPal_Id =" + ubiPal_Id + ";";
    }

    public static String ACTUALIZA_REGISTROS_SINCRONIZADOSS(int movPal_Id) {
        return "UPDATE MovimientoPaleta SET EsSincronizado = 1 WHERE MovPal_Id = " + movPal_Id + ";";
    }

    public static String MOSTRAR_REGISTROS(int ubiPal_Id) {
        return "SELECT RegPal_Numero, MovPal_FechaHorIniMov FROM MovimientoPaleta WHERE ProOpe_Id = 4 AND MovPal_FechaHorCieGas = '01/01/1990' AND UbiPal_Id =" + ubiPal_Id + ";";
    }

    public static String MOSTRAR_REGISTROS_PUCHOS(int ubiPal_Id, String remonte_Id) {
        return "SELECT RegPal_Numero, MovPal_FechaHorIniMov FROM MovimientoPaleta WHERE ProOpe_Id = 4  AND UbiPal_Id =" + ubiPal_Id + " AND MovPal_RemonteId = '" + remonte_Id +"';";
    }

    public static String CIERRA_BATCH_TUNEL_PALETA(String fechaCierre, int ubiPal_Id){
        return "UPDATE MovimientoPaleta SET MovPal_FechaHorCieGas = '"+ fechaCierre + "' WHERE UbiPal_Id= " + ubiPal_Id + " AND MovPal_FechaHorCieGas = '01/01/1990';";
    }

    public static String CIERRA_BATCH_TUNEL_PUCHO(String fechaCierre, int ubiPal_Id){
        return "UPDATE MovimientoPaleta SET MovPal_FechaHorCieGas = '"+ fechaCierre + "' WHERE UbiPal= " + ubiPal_Id + " AND MovPal_RemonteId != 1;";
    }

    private static String fechaHora() {
        Calendar Cal = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return (df.format(Cal.getInstance().getTime()).toString());
    }

    private static String fechaHoraSolo() {
        Calendar Cal = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return (df.format(Cal.getInstance().getTime()).toString());
    }
}
