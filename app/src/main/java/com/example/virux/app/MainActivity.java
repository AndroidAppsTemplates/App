package com.example.virux.app;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.editTextNombre)
    EditText nombreEditText;


    @Bind(R.id.editTextTelefono)
    EditText telefonoEditText;

    @Bind(R.id.editTextEmail)
    EditText emailEditText;


    UsuarioDAO usuarioDAO;

    @Bind(R.id.listViewUsuarios)
    ListView usuariosListView;

    ArrayAdapter<String> adapter;

    List<String> usuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        usuarioDAO = new UsuarioDAO(getApplicationContext());
        usuarios = usuariosToString(usuarioDAO.obtenerTodos());

        adapter = new ArrayAdapter<String>( this,
                                            android.R.layout.simple_list_item_1,
                                            android.R.id.text1,
                                            usuarios);

        usuariosListView.setAdapter(adapter);


    }

    public void clicarButton(View v) {


        if (validar()) {
            //Creamos el usuario y cargamos los valores
            Usuario u = new Usuario();
            u.setNombre(nombreEditText.getText().toString());
            u.setEmail(emailEditText.getText().toString());
            u.setTelefono(telefonoEditText.getText().toString());

            //guardamos el usuario
            usuarioDAO.guardar(u);


            //Limpiar los campos
            nombreEditText.setText(null);
            emailEditText.setText(null);
            telefonoEditText.setText(null);

            //Avisamos al usuario que los datos han sido guardados
            // Usar multiidioma para el mensaje
            Snackbar.make(v, getString(R.string.user_save),
                    Snackbar.LENGTH_SHORT)
                    .show();


            //TODO actualizar listView
            usuarios.add(u.toString());
            adapter.notifyDataSetChanged();

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

    private List<String> usuariosToString(List<Usuario> usuarios){
        List<String> lista = new ArrayList<String>();

        for (Usuario u : usuarios){
            lista.add(u.toString());
        }

        return lista;
    }

    @OnItemClick(R.id.listViewUsuarios)
    public void clickItemUsuario(int position){
            String u = usuarios.get(position);
            Snackbar.make(usuariosListView, u,Snackbar.LENGTH_LONG).show();
    }

    @OnItemLongClick(R.id.listViewUsuarios)
    public boolean longClickItemUsuario(int position){
            Snackbar.make(usuariosListView,"Long click "+position,Snackbar.LENGTH_LONG).show();
            usuarios.remove(position);
            adapter.notifyDataSetChanged();
            return true;
    }

}








