package com.example.jescalaya.appmovimientopaletafrio.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalBD extends SQLiteOpenHelper {

    private static final String baseDatosLocal = "BD_Frio_DR";
    private static final int versionBD = 2;

    public LocalBD(Context context) {
        super(context, baseDatosLocal, null, versionBD);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(T_UbicacionPaleta.CREATE_UBICACION_PALETA);
        db.execSQL(T_MovimientoPaleta.CREATE_MOVIMIENTO_PALETA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(T_UbicacionPaleta.DROP_UBICACION_PALETA);
        db.execSQL(T_MovimientoPaleta.DROP_MOVIMIENTO_PALETA);
        onCreate(db);
    }
}
