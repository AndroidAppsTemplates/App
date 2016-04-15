package com.example.virux.app;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.editTextNombre)
    EditText nombreEditText;


    @Bind(R.id.editTextTelefono)
    EditText telefonoEditText;

    @Bind(R.id.editTextEmail)
    EditText emailEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    public void clicarButton(View v) {


        if (validar()) {
            //Creamos el usuario y cargamos los valores
            Usuario u = new Usuario();
            u.setNombre(nombreEditText.getText().toString());
            u.setEmail(emailEditText.getText().toString());
            u.setTelefono(telefonoEditText.getText().toString());

            //Creamos el DAO y guardamos el usuario
            UsuarioDAO udao = new UsuarioDAO(getApplicationContext());
            udao.guardar(u);


            //Limpiar los campos
            nombreEditText.setText(null);
            emailEditText.setText(null);
            telefonoEditText.setText(null);

            //Avisamos al usuario que los datos han sido guardados
            // Usar multiidioma para el mensaje
            Snackbar.make(v, getString(R.string.user_save),
                    Snackbar.LENGTH_SHORT)
                    .show();

            List<Usuario> usuarios = udao.obtenerTodos();
            Log.d("USUARIOS",usuarios.toString());

        }
    }

    private boolean validar(){
        boolean isValid = true;
        if (nombreEditText.getText().length() < 3) {

            nombreEditText.setError(getString(R.string.error_nombre));
            isValid = false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText()).matches()) {
            emailEditText.setError(getString(R.string.email_error));
            isValid = false;
        }

        if (!Patterns.PHONE.matcher(telefonoEditText.getText()).matches()) {
            telefonoEditText.setError(getString(R.string.telefono_error));
            isValid = false;
        }
        return isValid;
    }
}
