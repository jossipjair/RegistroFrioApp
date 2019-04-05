package com.example.jescalaya.appmovimientopaletafrio.vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jescalaya.appmovimientopaletafrio.R;
import com.example.jescalaya.appmovimientopaletafrio.controlador.C_MovimientoPaleta;
import com.example.jescalaya.appmovimientopaletafrio.controlador.C_UbicacionPaleta;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegistraPucho extends AppCompatActivity {

    private EditText txtUbicacionIdPucho;
    private EditText txtNumeroPaletaPucho;
    private Button btnRegistraPaletaPucho;
    private Button btnCerrarPaletaPucho;
    private GridView dgvRegistrosPaletasPucho;
    private TextView lblCantidadPaletasPucho;
    private int ubicacion = 1;
    private String nombreUbicacion;
    private String remonteId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_pucho);

        txtUbicacionIdPucho = findViewById(R.id.txtUbicacionFrio);
        txtNumeroPaletaPucho = findViewById(R.id.txtNumPaleta);
        btnRegistraPaletaPucho = findViewById(R.id.btnRegistraPaletaFrioPucho);
        dgvRegistrosPaletasPucho = findViewById(R.id.dgvPaletasFrioPucho);
        lblCantidadPaletasPucho = findViewById(R.id.lblCantidadPaletaPucho);
        btnCerrarPaletaPucho = findViewById(R.id.btnCerrarPaletaPucho);
        creaIdRemonte();
        //Valida el 'ENTER' para la lectura QR
        txtUbicacionIdPucho.setSingleLine(false);
        txtUbicacionIdPucho.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);
        txtNumeroPaletaPucho.setSingleLine(false);
        txtNumeroPaletaPucho.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);
        btnRegistraPaletaPucho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RegistraPaleta.this.finish();

                nombreUbicacion = txtUbicacionIdPucho.getText().toString();
                buscarUbicacionId();

                C_MovimientoPaleta movimientoPaleta = new C_MovimientoPaleta();
                if (movimientoPaleta.validaRegistroPaleta(RegistraPucho.this, txtNumeroPaletaPucho.getText().toString().trim(), 4).equals(txtNumeroPaletaPucho.getText().toString().trim())) {
                    Toast.makeText(RegistraPucho.this, "Paleta duplicada\n ¡Por favor verifique!", Toast.LENGTH_SHORT).show();
                } else {
                    movimientoPaleta.insertarMovimientoPaleta(RegistraPucho.this, 132, VariableGeneral.tunelId, txtNumeroPaletaPucho.getText().toString().trim(), txtNumeroPaletaPucho.getText().toString().trim(), 0, fechaHora("dd/MM/yyyy HH:mm:ss"), fechaHora("dd/MM/yyyy HH:mm:ss"), fechaHora("dd/MM/yyyy HH:mm:ss"), 4, remonteId, fechaHora("dd/MM/yyyy HH:mm:ss"));
                    llenarGridView();
                    Toast.makeText(RegistraPucho.this, "¡Pucho Salvado!", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistraPucho.this);
                    builder.setTitle("Registro de puchos");
                    builder.setMessage("¿Desea seguir registrando puchos?");
                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //RegistraPaleta.this.finish();
                            txtUbicacionIdPucho.setText(nombreUbicacion);
                            txtNumeroPaletaPucho.setText("");
                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(RegistraPucho.this, MenuPrincipal.class);
                            startActivity(intent);
                            RegistraPucho.this.finish();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
        btnCerrarPaletaPucho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarRemonte();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cerrarRemonte();
    }

    void creaIdRemonte() {
        String[] dataQr = fechaHora("yy/MM/dd").split("\\/");
        String hora = fechaHora("HH");
        String minuto = fechaHora("mm");
        String segundo = fechaHora("ss");
        remonteId = "RM" +  dataQr[0] + dataQr[1] + dataQr[2] + hora + minuto + segundo;
    }

    void buscarUbicacionId() {
        C_UbicacionPaleta c_ubicacionPaleta = new C_UbicacionPaleta();
        ubicacion = c_ubicacionPaleta.buscarIdUbicacion(RegistraPucho.this, this.txtUbicacionIdPucho.getText().toString().trim());
    }

    void llenarGridView() {
        C_MovimientoPaleta c_movimientoPaleta = new C_MovimientoPaleta();
        lblCantidadPaletasPucho.setText(String.valueOf(c_movimientoPaleta.mostrarPaletasPuchos(RegistraPucho.this, dgvRegistrosPaletasPucho, ubicacion, remonteId)));
    }

    private static String fechaHora(String formato) {
        Calendar Cal = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat(formato);
        return (df.format(Cal.getInstance().getTime()).toString());
    }

    void cerrarRemonte() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegistraPucho.this);
        builder.setTitle("Registro de paleta");
        builder.setMessage("¿Desea cerrar puchos?");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(RegistraPucho.this, MenuPrincipal.class);
                startActivity(intent);
                RegistraPucho.this.finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //RegistraPaleta.this.finish();
                txtUbicacionIdPucho.setText(nombreUbicacion);
                txtNumeroPaletaPucho.setText("");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
