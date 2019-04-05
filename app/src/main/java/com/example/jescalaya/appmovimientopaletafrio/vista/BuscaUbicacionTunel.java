package com.example.jescalaya.appmovimientopaletafrio.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jescalaya.appmovimientopaletafrio.R;
import com.example.jescalaya.appmovimientopaletafrio.controlador.C_UbicacionPaleta;
import com.example.jescalaya.appmovimientopaletafrio.modelo.SincronizaMovimientoPaleta;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class BuscaUbicacionTunel extends AppCompatActivity {

    private Button btnTunelUno;
    private Button btnTunelDos;
    private Button btnTunelTres;
    private Button btnTunelCuatro;
    private Button btnTunelCinco;
    private Button btnTunelSeis;
    private TextView lblTituloUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_ubicacion_tunel);

        btnTunelUno = findViewById(R.id.btnTunelUno);
        btnTunelDos = findViewById(R.id.btnTunelDos);
        btnTunelTres = findViewById(R.id.btnTunelTres);
        btnTunelCuatro = findViewById(R.id.btnTunelCuatro);
        btnTunelCinco = findViewById(R.id.btnTunelCinco);
        btnTunelSeis = findViewById(R.id.btnTunelSeis);
        lblTituloUbicacion = findViewById(R.id.lblTituloUbicacionTunel);

        if (VariableGeneral.Sucursal_conf == 2) {
            btnTunelCinco.setEnabled(false);
            btnTunelSeis.setEnabled(false);
            lblTituloUbicacion.setText("TÚNEL ENFRIAMIENTO PYA");

        } else if (VariableGeneral.Sucursal_conf == 3) {
            btnTunelCinco.setEnabled(true);
            btnTunelSeis.setEnabled(true);
            lblTituloUbicacion.setText("TÚNEL ENFRIAMIENTO PDC");
        }

        btnTunelUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VariableGeneral.Sucursal_conf == 3) {
                    VariableGeneral.tunelId = 27;
                    VariableGeneral.tunelDescripcion = "T1PDC";
                } else if (VariableGeneral.Sucursal_conf == 2) {
                    VariableGeneral.tunelId = 33;
                    VariableGeneral.tunelDescripcion = "T1PYA";
                }
                Intent intent = new Intent(BuscaUbicacionTunel.this, RegistraPaleta.class);
                startActivity(intent);
                BuscaUbicacionTunel.this.finish();
            }
        });

        btnTunelDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VariableGeneral.Sucursal_conf == 3) {
                    VariableGeneral.tunelId = 28;
                    VariableGeneral.tunelDescripcion = "T2PDC";
                } else if (VariableGeneral.Sucursal_conf == 2) {
                    VariableGeneral.tunelId = 34;
                    VariableGeneral.tunelDescripcion = "T2PYA";
                }

                Intent intent = new Intent(BuscaUbicacionTunel.this, RegistraPaleta.class);
                startActivity(intent);
                BuscaUbicacionTunel.this.finish();
            }
        });

        btnTunelTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VariableGeneral.Sucursal_conf == 3) {
                    VariableGeneral.tunelId = 29;
                    VariableGeneral.tunelDescripcion = "T3PDC";
                } else if (VariableGeneral.Sucursal_conf == 2) {
                    VariableGeneral.tunelId = 35;
                    VariableGeneral.tunelDescripcion = "T3PYA";
                }
                Intent intent = new Intent(BuscaUbicacionTunel.this, RegistraPaleta.class);
                startActivity(intent);
                BuscaUbicacionTunel.this.finish();
            }
        });


        btnTunelCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VariableGeneral.Sucursal_conf == 3) {
                    VariableGeneral.tunelId = 30;
                    VariableGeneral.tunelDescripcion = "T4PDC";
                } else if (VariableGeneral.Sucursal_conf == 2) {
                    VariableGeneral.tunelId = 36;
                    VariableGeneral.tunelDescripcion = "T4PYA";
                }
                Intent intent = new Intent(BuscaUbicacionTunel.this, RegistraPaleta.class);
                startActivity(intent);
                BuscaUbicacionTunel.this.finish();
            }
        });

        btnTunelCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VariableGeneral.Sucursal_conf == 3) {
                    VariableGeneral.tunelId = 31;
                    VariableGeneral.tunelDescripcion = "T5PDC";
                } else if (VariableGeneral.Sucursal_conf == 2) {
                    VariableGeneral.tunelId = 0;
                    VariableGeneral.tunelDescripcion = "T5PYA";
                }
                Intent intent = new Intent(BuscaUbicacionTunel.this, RegistraPaleta.class);
                startActivity(intent);
                BuscaUbicacionTunel.this.finish();
            }
        });

        btnTunelSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VariableGeneral.Sucursal_conf == 3) {
                    VariableGeneral.tunelId = 32;
                    VariableGeneral.tunelDescripcion = "T6PDC";
                } else if (VariableGeneral.Sucursal_conf == 2) {
                    VariableGeneral.tunelId = 0;
                    VariableGeneral.tunelDescripcion = "T6PYA";
                }
                Intent intent = new Intent(BuscaUbicacionTunel.this, RegistraPaleta.class);
                startActivity(intent);
                BuscaUbicacionTunel.this.finish();
            }
        });


    }

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


    void sincronizaAsincronox2Horas() {

        final Runnable run = new Runnable() {
            @Override
            public void run() {
                SincronizaMovimientoPaleta sincronizaMovimientoPaleta = new SincronizaMovimientoPaleta();
                C_UbicacionPaleta c_ubicacion = new C_UbicacionPaleta();
                String ubicaciones[] = c_ubicacion.muestraUbicacion(BuscaUbicacionTunel.this, VariableGeneral.Sucursal_conf); //solo Planta Don Carlos
                try {
                    SincronizaMovimientoPaleta sinc_MovimientoPaleta = new SincronizaMovimientoPaleta();
                    int ubica = 1;
                    for (int i = 0; i < ubicaciones.length; i++) {
                        ubica = Integer.parseInt(ubicaciones[i]);
                        sinc_MovimientoPaleta.recorreListaSincronizaMovimientoPaleta(BuscaUbicacionTunel.this, ubica);
                    }
                } catch (Exception ee) {
                    Toast.makeText(BuscaUbicacionTunel.this, "Error\n" + ee.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        final ScheduledFuture<?> futuro = scheduler.scheduleAtFixedRate(run, 1, 1, TimeUnit.HOURS);


    }

}
