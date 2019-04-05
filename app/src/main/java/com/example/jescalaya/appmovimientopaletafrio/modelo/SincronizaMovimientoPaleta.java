package com.example.jescalaya.appmovimientopaletafrio.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.jescalaya.appmovimientopaletafrio.BD.LocalBD;
import com.example.jescalaya.appmovimientopaletafrio.BD.T_MovimientoPaleta;
import com.example.jescalaya.appmovimientopaletafrio.vista.VariableGeneral;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class SincronizaMovimientoPaleta {

    private LocalBD localBD;
    private SQLiteDatabase sqLiteDatabase;

    //Campos Movimiento
    String varId = "";
    String ubiPal_Id = "";
    String regPal_Id = "";
    String regPal_Numero = "";
    String movPal_FechaHorIniMov = "";
    String movPal_FechaHorRegLog = "";
    String movPal_FechaHorCieGas = "";
    String estado = "";
    String procesoOperativo = "";
    String movPal_IdLocal = "";
    String movPal_HoraSalPal = "";
    String mac = VariableGeneral.macAdress;
    String movPal_FechaHoraSalidPaleta = "";
    String regPal_Jabas = "0";
    String movPal_RemonteId = "1";

    public void recorreListaSincronizaMovimientoPaleta(final Context context, int ubi) {

        Cursor registros = null;
        try {
            localBD = new LocalBD(context);
            sqLiteDatabase = localBD.getWritableDatabase();
            registros = sqLiteDatabase.rawQuery(T_MovimientoPaleta.SINCRONIZA_CON_BD_CENTRAL(ubi), null);
            if (registros.moveToFirst()) {
                do {
                    varId = registros.getString(0);
                    ubiPal_Id = registros.getString(1);
                    regPal_Id = registros.getString(2);
                    regPal_Numero = registros.getString(3);
                    movPal_FechaHorIniMov = registros.getString(4);
                    movPal_FechaHorRegLog = registros.getString(5);
                    movPal_FechaHorCieGas = registros.getString(6);
                    estado = registros.getString(7);
                    procesoOperativo = registros.getString(8);
                    movPal_IdLocal = registros.getString(9);
                    movPal_HoraSalPal = registros.getString(10);
                    movPal_FechaHoraSalidPaleta = movPal_HoraSalPal;
                    regPal_Jabas = registros.getString(11);
                    movPal_RemonteId = registros.getString(12);
                    try {
                        Thread.sleep(0);
                        sincronizaMovimientosToServer(context, varId, ubiPal_Id, regPal_Id, regPal_Numero, movPal_FechaHorIniMov, movPal_FechaHorRegLog, movPal_FechaHorCieGas, estado, procesoOperativo, movPal_IdLocal, mac, movPal_FechaHoraSalidPaleta, regPal_Jabas, movPal_RemonteId);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (registros.moveToNext());
            }
        } finally {
            if (registros != null) {
                registros.close();
                sqLiteDatabase.close();
            }
        }
    }

    public void sincronizaMovimientosToServer(final Context context, final String variedad, final String ubicacion, final String registroId,
                                              final String registroNumero, final String fechaInicioMovimiento, final String fechaRegistroMovimiento,
                                              final String fechaCierreMovimiento, final String estadoMovimiento, final String proceso,
                                              final String idMovimientoLocal, final String macAdress, final String horaSalidaLimpieza, final String jaba, final String MovPal_RemonteId) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, VariableGeneral.ipServidor + "MovimientoPaleta/", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                VariableGeneral.estadoConexion = true;
                if (response.equals("true")) {
                    M_MovimientoPaleta movimientoPaleta = new M_MovimientoPaleta();
                    movimientoPaleta.actualizaSincronizacion(context, Integer.parseInt(idMovimientoLocal));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error\n¡Sin conexión a la red!", Toast.LENGTH_SHORT).show();
                VariableGeneral.estadoConexion = false;
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Var_Id", variedad);
                params.put("UbiPal_Id", ubicacion);
                params.put("RegPal_Id", registroId);
                params.put("RegPal_Numero", registroNumero);
                params.put("MovPal_FechaHorIniMov", fechaInicioMovimiento);
                params.put("MovPal_FechaHorRegLog", fechaRegistroMovimiento);
                params.put("MovPal_FechaHorCieGas", fechaCierreMovimiento);
                params.put("Est_Id", estadoMovimiento);
                params.put("ProOpe_Id", proceso);
                params.put("MovPal_IdLocal", idMovimientoLocal);
                params.put("MovPal_MacAdress", "EQUIPO");
                params.put("MovPal_HoraSalPal", horaSalidaLimpieza);
                params.put("Regpal_Jabas", jaba);
                params.put("MovPal_RemonteId", MovPal_RemonteId);
                return params;
            }
        };
        VolleyInstance.getInstanceVolley(context).addToRequestQueue(stringRequest);
    }

    private static String fechaHoraSolo() {
        Calendar Cal = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return (df.format(Cal.getInstance().getTime()).toString());
    }


}
