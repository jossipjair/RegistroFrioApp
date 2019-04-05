package com.example.jescalaya.appmovimientopaletafrio.vista;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.example.jescalaya.appmovimientopaletafrio.R;
import com.example.jescalaya.appmovimientopaletafrio.controlador.C_UbicacionPaleta;
import com.example.jescalaya.appmovimientopaletafrio.modelo.SincronizaMovimientoPaleta;

public class MenuPrincipal extends AppCompatActivity {

    private Button btnSincronizaTablas;
    private Button btnRegistraPaletaMenu;
    private Button btnRegistraPuchos;
    private Button btnReporteTunel;
    private Button btnSindronizaEnvio;
    private Button btnSalir;
    //private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        //vibrator = (Vibrator) getSystemService(MenuPrincipal.VIBRATOR_SERVICE);


        btnSincronizaTablas = findViewById(R.id.btnSincronizaTablas);
        btnRegistraPaletaMenu = findViewById(R.id.btnMenuRegistraPaleta);
        btnRegistraPuchos = findViewById(R.id.btnMenuRegistraPuchos);
        btnReporteTunel = findViewById(R.id.btnReporteTunel);
        btnSindronizaEnvio = findViewById(R.id.btnSincronizaEnvio);
        btnSalir = findViewById(R.id.btnSalirMenuFrio);

       /* if(VariableGeneral.privilegio == 1){
            btnSincronizaTablas.setEnabled(true );
        }else{
            btnSincronizaTablas.setEnabled(false);
        }*/


        btnSincronizaTablas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                C_UbicacionPaleta c_ubicacionPaleta = new C_UbicacionPaleta();
                c_ubicacionPaleta.sincronizaUbicacionPaleta(MenuPrincipal.this);
            }
        });

        btnRegistraPaletaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this, RegistraPaleta.class);
                startActivity(intent);
            }
        });

        btnRegistraPuchos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this, RegistraPucho.class);
                startActivity(intent);
            }
        });

        btnReporteTunel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSindronizaEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                C_UbicacionPaleta c_ubicacion = new C_UbicacionPaleta();
                String ubicaciones[] = c_ubicacion.muestraUbicacion(MenuPrincipal.this, VariableGeneral.Sucursal_conf); //solo Planta Don Carlos
                try {
                    if(VariableGeneral.estadoConexion){
                        SincronizaMovimientoPaleta sinc_MovimientoPaleta = new SincronizaMovimientoPaleta();
                        int ubica = 1;
                        for (int i = 0; i < ubicaciones.length; i++){
                            ubica = Integer.parseInt(ubicaciones[i]);
                            sinc_MovimientoPaleta.recorreListaSincronizaMovimientoPaleta(MenuPrincipal.this, ubica);
                        }
                    }else{
                        //long[] patron ={10, 1,2}; //dormir / vibraciones
                        //vibrator.vibrate(patron ,1);
                        Toast.makeText(MenuPrincipal.this, "¡Sin conexión a la red!\nVerifique conexión o reinicie el dispositivo", Toast.LENGTH_SHORT).show();
                    }
                        exportaExcel();
                } catch (Exception ee) {
                    Toast.makeText(MenuPrincipal.this, "Error\n" + ee.getMessage(), Toast.LENGTH_SHORT).show();
                    //long[] patron ={10, 1,2}; //dormir / vibraciones
                    //vibrator.vibrate(patron ,1);
                }
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                salirApp();
            }
        });
    }

    @Override
    public void onBackPressed() {
        salirApp();
    }

    void salirApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuPrincipal.this);
        builder.setTitle("Mensaje de confirmación");
        builder.setMessage("¿Desea cerrar la aplicación?");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                MenuPrincipal.this.finishAffinity();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    void exportaExcel() {
        SQLiteToExcel sqLiteToExcel = new SQLiteToExcel(MenuPrincipal.this, "BD_Frio_DR", "/sdcard/Download/");
        sqLiteToExcel.exportAllTables("informacion.xls", new SQLiteToExcel.ExportListener() {

            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(String filePath) {

                if(VariableGeneral.estadoConexion){
                    Toast.makeText(MenuPrincipal.this, "¡Sincronizado!", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MenuPrincipal.this, "¡Sin conexión a la red!", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(MenuPrincipal.this, "Exportado a Excel", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(MenuPrincipal.this, "Error al exportar\n" + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
