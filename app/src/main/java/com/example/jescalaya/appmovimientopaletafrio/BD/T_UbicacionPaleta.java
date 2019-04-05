package com.example.jescalaya.appmovimientopaletafrio.BD;

public class T_UbicacionPaleta {

    private static final String TABLA = "UbicacionPaleta";

    //CAMPOS DE TABLA
    private static final String UBIPAL_ID = "UbiPal_Id";
    private static final String SUC_ID= "Suc_Id";
    private static final String PROOPE_ID= "ProOpe_Id";
    private static final String EST_ID= "Est_Id";
    private static final String UBIPAL_DESCRIPCION= "UbiPal_Descripcion";
    private static final String UBIPAL_PREFIJO= "UbiPal_PreFijo";
    private static final String UBIPAL_CODIGO= "UbiPal_Codigo";

    //CREACION DE TABLA
    public static  final String CREATE_UBICACION_PALETA = "CREATE TABLE " + TABLA + "("
            + UBIPAL_ID + " INTEGER NOT NULL PRIMARY KEY, "
            + SUC_ID + " INTEGER NOT NULL, "
            + PROOPE_ID + " INTEGER NOT NULL, "
            + EST_ID + " INTEGER NOT NULL, "
            + UBIPAL_DESCRIPCION + " TEXT NOT NULL, "
            + UBIPAL_PREFIJO + " TEXT NOT NULL, "
            + UBIPAL_CODIGO + " TEXT NOT NULL)" ;


    //ELIMINACION DE TABLA
    public static  final String DROP_UBICACION_PALETA = "DROP TABLE IF EXISTS " + TABLA + ";";

    public static String INSERT_UBICACION_PALETA(int UbiPal_Id, int Suc_Id, int ProOpe_Id, int Est_Id, String UbiPal_Descripcion, String UbiPal_PreFijo, String UbiPal_Codigo){
        return "INSERT INTO "
                + TABLA + "(" + UBIPAL_ID + "," + SUC_ID  +  ","
                + PROOPE_ID + ","+ EST_ID+ "," +  UBIPAL_DESCRIPCION + "," + UBIPAL_PREFIJO + "," + UBIPAL_CODIGO +") VALUES("
                + UbiPal_Id +"," + Suc_Id + "," + ProOpe_Id + "," + Est_Id + ",'" + UbiPal_Descripcion + "','" + UbiPal_PreFijo + "','" + UbiPal_Codigo + "');";
    }

    public static String UPDATE_UBICACION_PALETA(int UbiPal_Id, int Suc_Id, int ProOpe_Id, int Est_Id, String UbiPal_Descripcion, String UbiPal_PreFijo, String UbiPal_Codigo){
        return "UPDATE " + TABLA + " SET " + SUC_ID + "=" +Suc_Id +", " + PROOPE_ID + "=" + ProOpe_Id +","
                + EST_ID + "=" + Est_Id + "," + UBIPAL_DESCRIPCION + "='" + UbiPal_Descripcion + "',"
                + UBIPAL_PREFIJO + "='" + UbiPal_PreFijo + "'," + UBIPAL_CODIGO + "='" + UbiPal_Codigo +"' WHERE " + UBIPAL_ID + "=" + UbiPal_Id + ";";
    }


    public static String MOSTRAR_UBICACION_PALETA(int ubiPal_Id){
        return "SELECT " + UBIPAL_PREFIJO + " FROM " + TABLA + " WHERE " + UBIPAL_ID + "=" + ubiPal_Id +";";
    }

    public static String MOSTRAR_BALANZA(int sucursal){
        return "SELECT UbiPal_Id FROM UbicacionPaleta WHERE Suc_Id = " + sucursal + " AND ProOpe_Id = 3";
    }

    public static String MOSTRAR_ID_UBICACION(String ubicacionPreFijo){
        return "SELECT UbiPal_Id FROM UbicacionPaleta WHERE UbiPal_PreFijo = '" + ubicacionPreFijo + "';" ;
    }

    public static String MOSTRAR_LINEAS(int sucursal){
        return "SELECT UbiPal_Id FROM UbicacionPaleta WHERE Suc_Id = " + sucursal + ";";
    }

    public static String MOSTRAR_LINEAS_REPROCESO(int suc_Id, int proOpe_Id){
        return "SELECT UbiPal_Id, UbiPal_Descripcion FROM UbicacionPaleta WHERE Suc_Id = " + suc_Id + " AND ProOpe_Id = " + proOpe_Id + ";";
    }


}
