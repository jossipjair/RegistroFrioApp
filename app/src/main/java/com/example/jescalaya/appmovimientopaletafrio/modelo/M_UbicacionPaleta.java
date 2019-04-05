package com.example.jescalaya.appmovimientopaletafrio.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.jescalaya.appmovimientopaletafrio.BD.LocalBD;
import com.example.jescalaya.appmovimientopaletafrio.BD.T_UbicacionPaleta;
import com.example.jescalaya.appmovimientopaletafrio.vista.VariableGeneral;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class M_UbicacionPaleta {

    private LocalBD localBD;
    private SQLiteDatabase sqLiteDatabase;
    private int ubiPal_Id;
    private int suc_Id;
    private int proOpe_id;
    private int est_Id;
    private String ubiPal_Descripcion;
    private String ubiPal_PreFijo;
    private String ubiPal_Codigo;


    public void insertarCalibreLocalToService(final Context context) {
        localBD = new LocalBD(context);
        sqLiteDatabase = localBD.getWritableDatabase();
        //RequestQueue queue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, VariableGeneral.ipServidor + "Ubicacion", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            sqLiteDatabase.execSQL(T_UbicacionPaleta.DROP_UBICACION_PALETA);
                            sqLiteDatabase.execSQL(T_UbicacionPaleta.CREATE_UBICACION_PALETA);
                            int tamañoResponse = Integer.parseInt(String.valueOf(response.length())) - 1;
                            for (int i = 0; i <= tamañoResponse; i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                ubiPal_Id = jsonObject.getInt("ubiPal_Id");
                                suc_Id = jsonObject.getInt("suc_Id");
                                proOpe_id = jsonObject.getInt("proOpe_Id");
                                est_Id = jsonObject.getInt("est_Id");
                                ubiPal_Descripcion = jsonObject.getString("ubiPal_Descripcion");
                                ubiPal_PreFijo = jsonObject.getString("ubiPal_PreFijo");
                                ubiPal_Codigo = jsonObject.getString("ubiPal_Codigo");
                                sqLiteDatabase.execSQL(T_UbicacionPaleta.INSERT_UBICACION_PALETA(ubiPal_Id, suc_Id, proOpe_id, est_Id, ubiPal_Descripcion, ubiPal_PreFijo, ubiPal_Codigo));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Error\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } finally {
                            sqLiteDatabase.close();
                        }
                        Toast.makeText(context, "¡Tabla Ubicación Sincronizada!", Toast.LENGTH_SHORT).show();
                        VariableGeneral.estadoConexion = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error\n¡Sin conexión a la red!", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Error Ubicación conexión JSON fallida", Toast.LENGTH_SHORT).show();

            }
        });
        VolleyInstance.getInstanceVolley(context).addToRequestQueue(jsonArrayRequest);
    }

    //MOSTRAR_ID_UBICACION
    public int buscarIdUbicacion(Context context, String prefijoUbicacion) {
        int idUbica = 0;
        Cursor registros = null;
        try {
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_UbicacionPaleta.MOSTRAR_ID_UBICACION(prefijoUbicacion), null);
            if (registros.moveToFirst()) {
                do {
                    idUbica = registros.getInt(0);
                } while (registros.moveToNext());
            }
        } finally {
            if (registros != null) {
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return idUbica;


    }

    public String[] mostrarLineas(Context context, int suc_Id) {
        String lin = "";
        Cursor registros = null;
        String[] lineas;
        try {
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_UbicacionPaleta.MOSTRAR_LINEAS(suc_Id), null);
            lineas = new String[registros.getCount()];
            int contador = 0;
            if (registros.moveToFirst()) {
                do {
                    lin = registros.getString(0);
                    lineas[contador] = lin;
                    contador++;
                } while (registros.moveToNext());
            }

        } finally {
            if (registros != null) {
                registros.close();
                sqLiteDatabase.close();
            }
        }
        return lineas;
    }

}
