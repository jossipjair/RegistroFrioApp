package com.example.jescalaya.appmovimientopaletafrio.entidad;

public class E_MovimientoPaleta {
        private int movPal_Id;
        private int var_Id;
        private int ubiPal_Id;
        private int regPal_Id;
        private int regPal_Numero;
        private String movPal_FechaHorIniMov;
        private String movPal_FechaHorRegLog;
        private String movPal_FechaHorCieGas;
        private int est_Id;

    public E_MovimientoPaleta(int movPal_Id, int var_Id, int ubiPal_Id, int regPal_Id, int regPal_Numero, String movPal_FechaHorIniMov, String movPal_FechaHorRegLog, String movPal_FechaHorCieGas) {
        this.movPal_Id = movPal_Id;
        this.var_Id = var_Id;
        this.ubiPal_Id = ubiPal_Id;
        this.regPal_Id = regPal_Id;
        this.regPal_Numero = regPal_Numero;
        this.movPal_FechaHorIniMov = movPal_FechaHorIniMov;
        this.movPal_FechaHorRegLog = movPal_FechaHorRegLog;
        this.movPal_FechaHorCieGas = movPal_FechaHorCieGas;
    }

    public int getMovPal_Id() {
        return movPal_Id;
    }

    public void setMovPal_Id(int movPal_Id) {
        this.movPal_Id = movPal_Id;
    }

    public int getVar_Id() {
        return var_Id;
    }

    public void setVar_Id(int var_Id) {
        this.var_Id = var_Id;
    }

    public int getUbiPal_Id() {
        return ubiPal_Id;
    }

    public void setUbiPal_Id(int ubiPal_Id) {
        this.ubiPal_Id = ubiPal_Id;
    }

    public int getRegPal_Id() {
        return regPal_Id;
    }

    public void setRegPal_Id(int regPal_Id) {
        this.regPal_Id = regPal_Id;
    }

    public int getRegPal_Numero() {
        return regPal_Numero;
    }

    public void setRegPal_Numero(int regPal_Numero) {
        this.regPal_Numero = regPal_Numero;
    }

    public String getMovPal_FechaHorIniMov() {
        return movPal_FechaHorIniMov;
    }


    public int getEst_Id() {
        return est_Id;
    }

    public void setEst_Id(int est_Id) {
        this.est_Id = est_Id;
    }

    public void setMovPal_FechaHorIniMov(String movPal_FechaHorIniMov) {
        this.movPal_FechaHorIniMov = movPal_FechaHorIniMov;
    }

    public String getMovPal_FechaHorRegLog() {
        return movPal_FechaHorRegLog;
    }

    public void setMovPal_FechaHorRegLog(String movPal_FechaHorRegLog) {
        this.movPal_FechaHorRegLog = movPal_FechaHorRegLog;
    }

    public String getMovPal_FechaHorCieGas() {
        return movPal_FechaHorCieGas;
    }

    public void setMovPal_FechaHorCieGas(String movPal_FechaHorCieGas) {
        this.movPal_FechaHorCieGas = movPal_FechaHorCieGas;
    }
}
