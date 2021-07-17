package com.utc.drcheems;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {
    //definiendo el nombre de la bdd
    private static final String nombreBdd = "bdd_dr_cheems";
    // definiendo la version de la bdd
    private static final int versionBdd = 1;

    // T A B L A S
    private static final String tablaUsuario = "CREATE TABLE usuario(" +
            "id_usu integer PRIMARY KEY AUTOINCREMENT, " +
            "apellido_usu TEXT, " +
            "nombre_usu TEXT, " +
            "correo_usu TEXT, " +
            "clave_usu TEXT, " +
            "wsp_usu TEXT )";

    private static final String tablaCliente = " CREATE TABLE cliente(" +
            "id_cli INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "ci_cli TEXT," +
            "apellido_cli TEXT," +
            "nombre_cli TEXT," +
            "wsp_cli TEXT," +
            "dir_dom_cli TEXT," +
            "fk_id_usu INTEGER," +
            "FOREIGN KEY (fk_id_usu) REFERENCES usuario (id_usu))";

    private static final String tablaMascota = " CREATE TABLE mascota(" +
            "id_mas INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nick_mas TEXT," +
            "nombre_mas TEXT," +
            "tipo_mas TEXT," +
            "fecha_nac_mas DATE," +
            "rasgos_mas TEXT," +
            "fk_id_cli INTEGER," +
            "FOREIGN KEY (fk_id_cli) REFERENCES cliente (id_cli))";

    private static final String tablaCita = " CREATE TABLE cite(" +
            "id_cit INTEGER PRIMARY KEY AUTOINCREMENT," +
            "fecha_cit DATE," +
            "servicio_cit TEXT," +
            "solucion_cit TEXT," +
            "fk_id_mas INTEGER," +
            "FOREIGN KEY (fk_id_mas) REFERENCES mascota(id_mas))";

    //CONSTRUCTOR
    public BaseDatos(Context contexto) {
        super(contexto, nombreBdd, null, versionBdd);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear Tablas
        db.execSQL(tablaUsuario);
        db.execSQL(tablaCliente);
        db.execSQL(tablaMascota);
        db.execSQL(tablaCita);
        semillas(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar Tablas
        db.execSQL("DROP TABLE IF EXISTS cita");
        db.execSQL("DROP TABLE IF EXISTS mascota");
        db.execSQL("DROP TABLE IF EXISTS cliente");
        db.execSQL("DROP TABLE IF EXISTS usuario");
        // Vuelvo a crear las tablas
        db.execSQL(tablaUsuario);
        db.execSQL(tablaCliente);
        db.execSQL(tablaMascota);
        db.execSQL(tablaCita);
        semillas(db);
    }

    // Semillas
    public void semillas(SQLiteDatabase db) {
        // Preparar semillas
        String addUsuario = "INSERT INTO usuario (apellido_usu, nombre_usu, correo_usu, clave_usu, wsp_usu) " +
                "VALUES ('Duran','Roger','admin@correo.com','clave1234','0987654321')";

        String addCliente = "INSERT INTO cliente (ci_cli, apellido_cli, nombre_cli, wsp_cli,dir_dom_cli, fk_id_usu) " +
                "VALUES ('1718191415','Rooney','Billy','0912345678','Quito',1)";

        String addMascota = "INSERT INTO mascota (nick_mas, nombre_mas, tipo_mas, fecha_nac_mas, rasgos_mas, fk_id_cli) " +
                "VALUES ('1415-chester','chester','canino','2021-01-01','Mestizo color cafe',1)";

        String addCita = "INSERT INTO cita (fecha_cit, servicio_cit, solucion_cit, fk_id_mas) " +
                "VALUES ('2021-07-16','Revision general','',1)";


        // Ejecutar semillas
        db.execSQL(addUsuario);
        db.execSQL(addCliente);
        db.execSQL(addMascota);
        db.execSQL(addCita);
    }
}
