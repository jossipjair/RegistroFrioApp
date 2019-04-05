package com.example.jescalaya.appmovimientopaletafrio.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jescalaya.appmovimientopaletafrio.R;

public class Login extends AppCompatActivity {

    private ImageView logo;
    private EditText txtUsuario;
    private EditText txtPassword;
    private Button btnIngresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logo = findViewById(R.id.imgLogin);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPasword);
        btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtUsuario.getText().toString().equals("FRIO") && txtPassword.getText().toString().equals("1234")){
                    VariableGeneral.privilegio = 0;
                    Intent intent = new Intent(Login.this, MenuPrincipal.class);
                    startActivity(intent);
                    Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else if(txtUsuario.getText().toString().equals("ADMIN") && txtPassword.getText().toString().equals("jossip180218")){
                    VariableGeneral.privilegio = 1;
                    Intent intent = new Intent(Login.this, MenuPrincipal.class);
                    startActivity(intent);
                }


            }
        });

    }

}
