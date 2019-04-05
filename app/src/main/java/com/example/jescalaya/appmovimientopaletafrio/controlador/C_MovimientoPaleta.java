package com.example.jescalaya.appmovimientopaletafrio.controlador;

import android.content.Context;
import android.widget.GridView;

import com.example.jescalaya.appmovimientopaletafrio.modelo.M_MovimientoPaleta;

import org.apache.poi.hssf.record.RefModeRecord;

public class C_MovimientoPaleta {

    public void insertarMovimientoPaleta(Context context, int var_Id, int ubiPal_Id, String regPal_Id, String regPal_Numero, int regPal_Jabas, String movPal_FechaHorIniMov, String movPal_FechaHorRegLog, String movPal_FechaHorCieGas, int proOpe_Id, String remonte_Id ,String horaSalidaLimpieza){
        M_MovimientoPaleta movimientoPaleta = new M_MovimientoPaleta();
        movimientoPaleta.insertarMovimientoPaleta(context, var_Id, ubiPal_Id, regPal_Id, regPal_Numero, regPal_Jabas,  movPal_FechaHorIniMov, movPal_FechaHorRegLog, movPal_FechaHorCieGas, proOpe_Id, remonte_Id ,horaSalidaLimpieza);
    }

    public int mostrarPaletas(Context context, GridView dgv, int ubiPal_Id){
        M_MovimientoPaleta movimientoPaleta = new M_MovimientoPaleta();
        return movimientoPaleta.llenarGridMovimiento(context, dgv, ubiPal_Id);
    }

    public int mostrarPaletasPuchos(Context context, GridView dgv, int ubiPal_Id, String remonte_Id){
        M_MovimientoPaleta movimientoPaleta = new M_MovimientoPaleta();
        return movimientoPaleta.llenarGridMovimientoPuchos(context, dgv, ubiPal_Id, remonte_Id);
    }

    public String validaRegistroPaleta(Context context, String regPal_Numero, int proOpe_Id){
        M_MovimientoPaleta movimientoPaleta = new M_MovimientoPaleta();
        return movimientoPaleta.validaPaletaLibre(context, regPal_Numero, proOpe_Id);
    }

    public void iniciaEnfriamiento(Context context, String fechaInicioEnfriado, int ubiPal_id){
        M_MovimientoPaleta movimientoPaleta = new M_MovimientoPaleta();
        movimientoPaleta.cierraTunelEnfriamiento(context, fechaInicioEnfriado, ubiPal_id);
    }

    public void eliminarPaleta(Context context, String regPal_Numero){
        M_MovimientoPaleta movimientoPaleta = new M_MovimientoPaleta();
        movimientoPaleta.eliminarPaletaGasificado(context,regPal_Numero);
    }


}
