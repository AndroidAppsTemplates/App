package com.example.virux.app;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by virux on 14/04/16.
 */
public class UsuarioDAO {

    public UsuarioDAO(Context context){
        DBA.init(context);
    }

    public void guardar(Usuario u){
        try {
            DBA.getUsuarioDAO().create(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> obtenerTodos(){
        try {
           return DBA.getUsuarioDAO().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public Usuario obtenerPorId(int id){

        try {
            return DBA.getUsuarioDAO().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    public void eliminar(int id){
        try {
            DBA.getUsuarioDAO().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(Usuario u){
        try {
            DBA.getUsuarioDAO().update(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
