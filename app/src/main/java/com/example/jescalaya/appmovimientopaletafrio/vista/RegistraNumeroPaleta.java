package com.example.jescalaya.appmovimientopaletafrio.vista;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jescalaya.appmovimientopaletafrio.R;
import com.example.jescalaya.appmovimientopaletafrio.controlador.C_MovimientoPaleta;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegistraNumeroPaleta extends AppCompatActivity {


    private Button btnRegistrarPaletaNumero;
    private EditText txtPaletaNumero;
    private String remonteId = "";
    private CheckBox chkEsPucho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_numero_paleta);

        btnRegistrarPaletaNumero = findViewById(R.id.btnRegistrarPaletaNumero);
        txtPaletaNumero = findViewById(R.id.txtPaletaNumero);
        chkEsPucho = findViewById(R.id.chkEsRemonte);

        txtPaletaNumero.setSingleLine(false);
        txtPaletaNumero.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);

        chkEsPucho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creaIdRemonte();
            }
        });

        btnRegistrarPaletaNumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!chkEsPucho.isChecked()) {
                    C_MovimientoPaleta movimientoPaleta = new C_MovimientoPaleta();
                    if (movimientoPaleta.validaRegistroPaleta(RegistraNumeroPaleta.this, txtPaletaNumero.getText().toString().trim(), 4).equals(txtPaletaNumero.getText().toString().trim())) {
                        Toast.makeText(RegistraNumeroPaleta.this, "Paleta duplicada\n¡Por favor verifique!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (VariableGeneral.tunelId == 0) {
                            Toast.makeText(RegistraNumeroPaleta.this, "Error de busqueda de Ubicación", Toast.LENGTH_SHORT).show();
                        } else {
                            movimientoPaleta.insertarMovimientoPaleta(RegistraNumeroPaleta.this, 132, VariableGeneral.tunelId, txtPaletaNumero.getText().toString().trim(), txtPaletaNumero.getText().toString().trim(), 0, fechaHora("dd/MM/yyyy HH:mm:ss"), fechaHora("dd/MM/yyyy HH:mm:ss"), "01/01/1990", 4, txtPaletaNumero.getText().toString().trim(), fechaHora("dd/MM/yyyy HH:mm:ss"));
                            Toast.makeText(RegistraNumeroPaleta.this, "¡Paleta Salvada!", Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegistraNumeroPaleta.this);
                            builder.setTitle("Registro de paleta");
                            builder.setMessage("¿Desea seguir registrando paletas?");
                            builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //RegistraPaleta.this.finish();
                                    txtPaletaNumero.setText("");
                                    txtPaletaNumero.setFocusable(true);
                                }
                            });
                            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(RegistraNumeroPaleta.this, RegistraPaleta.class);
                                    startActivity(intent);
                                    RegistraNumeroPaleta.this.finish();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }
                } else {

                    C_MovimientoPaleta movimientoPaleta = new C_MovimientoPaleta();
                    if (movimientoPaleta.validaRegistroPaleta(RegistraNumeroPaleta.this, txtPaletaNumero.getText().toString().trim(), 4).equals(txtPaletaNumero.getText().toString().trim())) {
                        Toast.makeText(RegistraNumeroPaleta.this, "Paleta duplicada\n ¡Por favor verifique!", Toast.LENGTH_SHORT).show();
                    } else {
                        movimientoPaleta.insertarMovimientoPaleta(RegistraNumeroPaleta.this, 132, VariableGeneral.tunelId, txtPaletaNumero.getText().toString().trim(), txtPaletaNumero.getText().toString().trim(), 0, fechaHora("dd/MM/yyyy HH:mm:ss"), fechaHora("dd/MM/yyyy HH:mm:ss"), "01/01/1990", 4, remonteId, fechaHora("dd/MM/yyyy HH:mm:ss"));
                        Toast.makeText(RegistraNumeroPaleta.this, "¡Pucho Salvado!", Toast.LENGTH_SHORT).show();
                        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(RegistraNumeroPaleta.this);
                        builder.setTitle("Registro de puchos");
                        builder.setMessage("¿Desea seguir registrando puchos?");
                        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //RegistraPaleta.this.finish();
                                txtPaletaNumero.setText("");
                                chkEsPucho.setChecked(true);

                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(RegistraNumeroPaleta.this, RegistraPaleta.class);
                                startActivity(intent);
                                RegistraNumeroPaleta.this.finish();
                            }
                        });
                        android.support.v7.app.AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }




                }


                //Insertar paletas

            }
        });
    }

    private static String fechaHora(String formato) {
        Calendar Cal = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat(formato);
        return (df.format(Cal.getInstance().getTime()).toString());
    }

    void creaIdRemonte() {
        String[] dataQr = fechaHora("yy/MM/dd").split("\\/");
        String hora = fechaHora("HH");
        String minuto = fechaHora("mm");
        String segundo = fechaHora("ss");
        remonteId = "RM" + dataQr[0] + dataQr[1] + dataQr[2] + hora + minuto + segundo;
    }


}
