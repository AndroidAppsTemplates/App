package com.example.virux.app;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

        //Log.i("CLICK", "El usuario ingreso el texto: " + nombreEditText.getText().toString());
        String saludo = getString(R.string.bienvenido);

        if (nombreEditText.getText().length() < 3) {
            nombreEditText.setError(getString(R.string.error_nombre));
        }else if(false){
            //Email
        }else if (false){
            //Telefono
        }
        else{
            Snackbar.make(v,
                    saludo+" "+nombreEditText.getText().toString()+" "+
                            telefonoEditText.getText().toString()+" "+
                            emailEditText.getText().toString(),
                    Snackbar.LENGTH_SHORT)
                    .show();

        }


    }
}
