package com.example.jescalaya.appmovimientopaletafrio.vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jescalaya.appmovimientopaletafrio.R;
import com.example.jescalaya.appmovimientopaletafrio.controlador.C_MovimientoPaleta;
import com.example.jescalaya.appmovimientopaletafrio.controlador.C_UbicacionPaleta;
import com.example.jescalaya.appmovimientopaletafrio.modelo.M_MovimientoPaleta;
import com.example.jescalaya.appmovimientopaletafrio.modelo.SincronizaMovimientoPaleta;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegistraPaleta extends AppCompatActivity {

    private EditText txtUbicacionId;
    private EditText txtNumeroPaleta;
    private Button btnRegistraPaleta;
    private Button btnBuscaUbicacion;
    private Button btnBuscaPaleta;
    private GridView dgvRegistrosPaletas;
    private TextView lblCantidadPaletas;
    private Button btnIniciaEnfriamiento;
    private int ubicacion = 0;
    private Button btnIniciarEnfriamiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_paleta);

        txtUbicacionId = findViewById(R.id.txtUbicacionFrio);
        txtNumeroPaleta = findViewById(R.id.txtNumPaleta);
        dgvRegistrosPaletas = findViewById(R.id.dgvPaletasFrio);
        lblCantidadPaletas = findViewById(R.id.lblCantidadPaleta);
        btnIniciaEnfriamiento = findViewById(R.id.btnIniciarEnfriado);
        btnBuscaUbicacion = findViewById(R.id.btnBuscaUbicacion);
        btnBuscaPaleta = findViewById(R.id.btnBuscaPaleta);
        btnIniciaEnfriamiento = findViewById(R.id.btnIniciarEnfriado);

        try {
            txtUbicacionId.setText(VariableGeneral.tunelDescripcion);
            llenarGridView();
        } catch (Exception e) {

        }

        btnIniciaEnfriamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegistraPaleta.this);
                builder.setTitle("Registro de paleta");
                builder.setMessage("¿Desea iniciar enfriamiento de paletas?");
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        C_MovimientoPaleta c_movimientoPaleta = new C_MovimientoPaleta();
                        c_movimientoPaleta.iniciaEnfriamiento(RegistraPaleta.this, fechaHora("dd/MM/yyyy HH:mm:ss"), VariableGeneral.tunelId);

                       // SincronizaMovimientoPaleta sincronizaMovimientoPaleta = new SincronizaMovimientoPaleta();
                        C_UbicacionPaleta c_ubicacion = new C_UbicacionPaleta();
                        String ubicaciones[] = c_ubicacion.muestraUbicacion(RegistraPaleta.this, VariableGeneral.Sucursal_conf); //solo Planta Don Carlos
                        try {

                            SincronizaMovimientoPaleta sinc_MovimientoPaleta = new SincronizaMovimientoPaleta();
                            int ubica = 1;
                            for (int j = 0; j < ubicaciones.length; j++){
                                ubica = Integer.parseInt(ubicaciones[j]);
                                sinc_MovimientoPaleta.recorreListaSincronizaMovimientoPaleta(RegistraPaleta.this, ubica);
                            }



                        } catch (Exception ee) {
                            Toast.makeText(RegistraPaleta.this, "Error\n" + ee.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        llenarGridView();


                        //Toast.makeText(RegistraPaleta.this, "ID: " + String.valueOf(VariableGeneral.tunelId), Toast.LENGTH_SHORT).show();
                        Toast.makeText(RegistraPaleta.this, "Se ha iniciado el proceso de enfríamiento de Túnel " + VariableGeneral.tunelDescripcion, Toast.LENGTH_SHORT).show();
                        Toast.makeText(RegistraPaleta.this, "¡Por favor Sincronice!", Toast.LENGTH_SHORT).show();
                        //
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        dgvRegistrosPaletas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view,final int i, long l) {
                if (i % 2 == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistraPaleta.this);
                    builder.setMessage("¿Desea eliminar paleta " + adapterView.getItemAtPosition(i).toString() + "?");
                    builder.setTitle("Eliminar Registro de Túnel");
                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int j) {
                            C_MovimientoPaleta c_movimientoPaleta = new C_MovimientoPaleta();
                            String paleta = adapterView.getItemAtPosition(i).toString();
                            c_movimientoPaleta.eliminarPaleta(RegistraPaleta.this, paleta);
                            llenarGridView();
                            Toast.makeText(RegistraPaleta.this, "Paleta " + paleta + " eliminada", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int j) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                return true;
            }
        });

        //Valida el 'ENTER' para la lectura QR
        txtUbicacionId.setSingleLine(false);
        txtUbicacionId.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);
        txtNumeroPaleta.setSingleLine(false);
        txtNumeroPaleta.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);

        btnBuscaUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistraPaleta.this, BuscaUbicacionTunel.class);
                startActivity(intent);
            }
        });

        btnBuscaPaleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistraPaleta.this, RegistraNumeroPaleta.class);
                startActivity(intent);
            }
        });
    }

    void llenarGridView() {
        C_MovimientoPaleta c_movimientoPaleta = new C_MovimientoPaleta();
        lblCantidadPaletas.setText(String.valueOf(c_movimientoPaleta.mostrarPaletas(RegistraPaleta.this, dgvRegistrosPaletas, VariableGeneral.tunelId)));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RegistraPaleta.this, MenuPrincipal.class);
        startActivity(intent);
        RegistraPaleta.this.finish();
    }

    private static String fechaHora(String formato) {
        Calendar Cal = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat(formato);
        return (df.format(Cal.getInstance().getTime()).toString());
    }

}
