package com.example.jescalaya.appmovimientopaletafrio.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;
import com.example.jescalaya.appmovimientopaletafrio.BD.LocalBD;
import com.example.jescalaya.appmovimientopaletafrio.BD.T_MovimientoPaleta;

import java.util.ArrayList;

public class M_MovimientoPaleta {

    private LocalBD localBD;
    private SQLiteDatabase sqLiteDatabase;

    public void insertarMovimientoPaleta(Context context, int var_Id, int ubiPal_Id, String regPal_Id, String regPal_Numero, int regPal_Jabas, String movPal_FechaHorIniMov, String movPal_FechaHorRegLog, String movPal_FechaHorCieGas, int proOpe_Id, String remonte_Id, String horaSalidaLimpieza) {

        localBD = new LocalBD(context);
        sqLiteDatabase = localBD.getWritableDatabase();

        try {
            sqLiteDatabase.execSQL(T_MovimientoPaleta.INSERT_MOVIMIENTO_PALETA(var_Id, ubiPal_Id, regPal_Id, regPal_Numero, regPal_Jabas, movPal_FechaHorIniMov, movPal_FechaHorRegLog, movPal_FechaHorCieGas, 1, proOpe_Id, remonte_Id, horaSalidaLimpieza));
        } catch (SQLiteException ex) {
        } finally {
            sqLiteDatabase.close();
        }
    }


    public void actualizaSincronizacion(Context context, int movPaL_Id) {
        localBD = new LocalBD(context);
        sqLiteDatabase = localBD.getWritableDatabase();

        try {
            sqLiteDatabase.execSQL(T_MovimientoPaleta.ACTUALIZA_REGISTROS_SINCRONIZADOSS(movPaL_Id));
        } catch (Exception e) {

        } finally {
            sqLiteDatabase.close();
        }
    }

    public void cierraTunelEnfriamiento(Context context, String fechaInicioEnfriado, int ubiPal_id) {
        localBD = new LocalBD(context);
        sqLiteDatabase = localBD.getWritableDatabase();

        try {
            sqLiteDatabase.execSQL(T_MovimientoPaleta.CIERRA_BATCH_TUNEL_PALETA(fechaInicioEnfriado, ubiPal_id));
        } catch (Exception e) {

        } finally {
            sqLiteDatabase.close();
        }
    }

    public int llenarGridMovimiento(Context context, GridView gridView, int idUbi_Pal) {
        Cursor registros = null;
        int paleta = 0;
        try {
            ArrayList<String> lista = new ArrayList<>();

            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = null;
            registros = sqLiteDatabase.rawQuery(T_MovimientoPaleta.MOSTRAR_REGISTROS(idUbi_Pal), null);
            if (registros.moveToFirst()) {
                do {
                    lista.add(registros.getString(0));
                    lista.add(registros.getString(1));
                    paleta +=1;
                } while (registros.moveToNext());
            }
            ArrayAdapter adapter;
            adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, lista);
            gridView.setAdapter(adapter);
        } finally {
            if (registros != null) {
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return paleta;
    }


    public int llenarGridMovimientoPuchos(Context context, GridView gridView, int idUbi_Pal, String remonte_Id) {
        Cursor registros = null;
        int paleta = 0;
        try {
            ArrayList<String> lista = new ArrayList<>();

            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = null;
            registros = sqLiteDatabase.rawQuery(T_MovimientoPaleta.MOSTRAR_REGISTROS_PUCHOS(idUbi_Pal, remonte_Id), null);
            if (registros.moveToFirst()) {
                do {
                    lista.add(registros.getString(0));
                    lista.add(registros.getString(1));
                    paleta +=1;
                } while (registros.moveToNext());
            }
            ArrayAdapter adapter;
            adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, lista);
            gridView.setAdapter(adapter);
        } finally {
            if (registros != null) {
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return paleta;
    }

    public void eliminarPaletaGasificado(Context context, String regPal_Numero) {
        localBD = new LocalBD(context);
        sqLiteDatabase = localBD.getWritableDatabase();
        try {
            sqLiteDatabase.execSQL(T_MovimientoPaleta.DELETE_MOVIMIENTO_PALETA(regPal_Numero));
        } catch (SQLiteException e) {
            Toast.makeText(context, "Error al eliminar Paleta", Toast.LENGTH_SHORT).show();
        } finally {
            sqLiteDatabase.close();
        }
    }

    public String validaPaletaLibre(Context context, String regPal_Numero, int proOpe_Id) {

        String paleta = "";
        Cursor registros = null;
        try {
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_MovimientoPaleta.BUSCA_PALETA_LIBRE(regPal_Numero, proOpe_Id), null);
            if (registros.moveToFirst()) {
                do {
                    paleta = registros.getString(0);
                } while (registros.moveToNext());
            }
        } finally {
            if (registros != null) {
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return paleta;
    }




}
