package com.example.jescalaya.appmovimientopaletafrio.entidad;

public class E_UbicacionPaleta {

    private int ubiPal_Id;
    private int suc_Id;
    private int proOpe_Id;
    private int est_Id;
    private String ubiPal_Descripcion;
    private String ubiPal_Prefijo;
    private String ubiPal_Codigo;

    public E_UbicacionPaleta(int ubiPal_Id, int suc_Id, int proOpe_Id, int est_Id, String ubiPal_Descripcion, String ubiPal_Prefijo, String ubiPal_Codigo) {
        this.ubiPal_Id = ubiPal_Id;
        this.suc_Id = suc_Id;
        this.proOpe_Id = proOpe_Id;
        this.est_Id = est_Id;
        this.ubiPal_Descripcion = ubiPal_Descripcion;
        this.ubiPal_Prefijo = ubiPal_Prefijo;
        this.ubiPal_Codigo = ubiPal_Codigo;
    }

    public E_UbicacionPaleta(int ubiPal_Id, String ubiPal_Descripcion) {
        this.ubiPal_Id = ubiPal_Id;
        this.ubiPal_Descripcion = ubiPal_Descripcion;
    }

    public int getUbiPal_Id() {
        return ubiPal_Id;
    }

    public void setUbiPal_Id(int ubiPal_Id) {
        this.ubiPal_Id = ubiPal_Id;
    }

    public int getSuc_Id() {
        return suc_Id;
    }

    public void setSuc_Id(int suc_Id) {
        this.suc_Id = suc_Id;
    }

    public int getProOpe_Id() {
        return proOpe_Id;
    }

    public void setProOpe_Id(int proOpe_Id) {
        this.proOpe_Id = proOpe_Id;
    }

    public int getEst_Id() {
        return est_Id;
    }

    public void setEst_Id(int est_Id) {
        this.est_Id = est_Id;
    }

    public String getUbiPal_Descripcion() {
        return ubiPal_Descripcion;
    }

    public void setUbiPal_Descripcion(String ubiPal_Descripcion) {
        this.ubiPal_Descripcion = ubiPal_Descripcion;
    }

    public String getUbiPal_Prefijo() {
        return ubiPal_Prefijo;
    }

    public void setUbiPal_Prefijo(String ubiPal_Prefijo) {
        this.ubiPal_Prefijo = ubiPal_Prefijo;
    }

    public String getUbiPal_Codigo() {
        return ubiPal_Codigo;
    }

    public void setUbiPal_Codigo(String ubiPal_Codigo) {
        this.ubiPal_Codigo = ubiPal_Codigo;
    }

    @Override
    public String toString(){
        return getUbiPal_Descripcion();
    }
}
