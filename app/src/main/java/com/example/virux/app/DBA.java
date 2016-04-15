package com.example.virux.app;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.oss.datahelper.DataBaseManager;

import java.sql.SQLException;

/**
 * Created by virux on 14/04/16.
 */
public class DBA {

    private static final String DB_NAME = "agenda.sqlite";
    private static final int DB_VERSION = 1;

    public static void init(Context context){
        DataBaseManager.init(context,DB_NAME,DB_VERSION);

        ConnectionSource source = DataBaseManager.getInstance().getHelper().getConnectionSource();


            try {
                TableUtils.createTableIfNotExists(source,Usuario.class);

                //Demas tables
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    public static Dao<Usuario, Integer> getUsuarioDAO(){
        try {
            return DataBaseManager.getInstance().getHelper().getDao(Usuario.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}