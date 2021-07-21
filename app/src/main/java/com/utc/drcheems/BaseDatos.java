package com.utc.drcheems;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
/*
 * Autores: Vanesa Quishpe, Angel Tapia, Alex Vaca
 * Creado: 16/07/2021
 * Editado: 16/07/2021
 * Descripción: base de datos
 *
 */

public class BaseDatos extends SQLiteOpenHelper {
    //definiendo el nombre de la bdd
    private static final String nombreBdd = "bdd_dr_cheems";
    // definiendo la version de la bdd
    private static final int versionBdd = 1;

    // T A B L A S
    private static final String tablaUsuario = "CREATE TABLE usuario(" +
            "id_usu INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "apellido_usu TEXT, " +
            "nombre_usu TEXT, " +
            "correo_usu TEXT, " +
            "clave_usu TEXT, " +
            "wsp_usu TEXT );";

    private static final String tablaCliente = " CREATE TABLE cliente(" +
            "id_cli INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "ci_cli TEXT," +
            "apellido_cli TEXT," +
            "nombre_cli TEXT," +
            "wsp_cli TEXT," +
            "dir_dom_cli TEXT," +
            "fk_id_usu INTEGER," +
            "FOREIGN KEY (fk_id_usu) REFERENCES usuario (id_usu));";

    private static final String tablaMascota = " CREATE TABLE mascota(" +
            "id_mas INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nick_mas TEXT," +
            "nombre_mas TEXT," +
            "tipo_mas TEXT," +
            "fecha_nac_mas DATE," +
            "rasgos_mas TEXT," +
            "fk_id_cli INTEGER," +
            "FOREIGN KEY (fk_id_cli) REFERENCES cliente (id_cli));";

    private static final String tablaCita = " CREATE TABLE cite(" +
            "id_cit INTEGER PRIMARY KEY AUTOINCREMENT," +
            "fecha_cit DATETIME," +
            "servicio_cit TEXT," +
            "solucion_cit TEXT," +
            "fk_id_mas INTEGER," +
            "FOREIGN KEY (fk_id_mas) REFERENCES mascota(id_mas));";
    // Semillas
    private static final String addUsuario = "INSERT INTO usuario (apellido_usu, nombre_usu, correo_usu, clave_usu, wsp_usu) " +
            "VALUES ('Duran','Roger','admin@correo.com','clave1234','0987654321')";

    private static final String addCliente = "INSERT INTO cliente (ci_cli, apellido_cli, nombre_cli, wsp_cli,dir_dom_cli, fk_id_usu) " +
            "VALUES ('1718191415','Rooney','Billy','0912345678','Quito',1)";

    private static final String addMascota = "INSERT INTO mascota (nick_mas, nombre_mas, tipo_mas, fecha_nac_mas, rasgos_mas, fk_id_cli) " +
            "VALUES ('1415-chester','chester','canino','2021-01-01','Mestizo color cafe',1)";

    private static final String addCita = "INSERT INTO cita (fecha_cit, servicio_cit, solucion_cit, fk_id_mas) " +
            "VALUES ('2021-07-16 16:00:00','Revision general','',1)";


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
        try {
            db.execSQL(addUsuario);
            db.execSQL(addCliente);
            db.execSQL(addMascota);
            db.execSQL(addCita);

        } catch (Exception ex) {
            System.out.println("No se inserto semillas: " + ex);

        }
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
        try {
            db.execSQL(addUsuario);
            db.execSQL(addCliente);
            db.execSQL(addMascota);
            db.execSQL(addCita);

        } catch (Exception ex) {
            System.out.println("No se inserto semillas: " + ex);

        }
    }


    // G E S T I O N  DE  U S U A R I O S
    // Create
    public boolean agregarUsuario(String apellido, String nombre, String correo, String clave, String wsp) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            String newUsuario = "INSERT INTO usuario (apellido_usu, nombre_usu, correo_usu, clave_usu, wsp_usu) " +
                    "VALUES ('" + apellido + "','" + nombre + "','" + correo + "','" + clave + "','" + wsp + "')";
            db.execSQL(newUsuario);
            db.close();
            return true;
        }
        return false;
    }

    // Read
    public Cursor iniciarSesionUsuario(String correo, String clave) {
        SQLiteDatabase db = getReadableDatabase(); //Llamando a la base de datos
        String sql = "SELECT * FROM usuario " +
                "WHERE correo_usu = '" + correo + "' AND clave_usu = '" + clave + "'";
        Cursor usuario = db.rawQuery(sql, null);
        if (usuario.moveToFirst()) {//verificando que el objeto usuario tenga resultados
            return usuario; //retornamos datos encontrados
        } else {
            //Nose encuentra el usuario ..Porque no eexiste el email y congtrase{a
            return null;
        }

    }

    public Cursor obtenerUsuario(String id) {
        SQLiteDatabase db = getReadableDatabase(); //Llamando a la base de datos
        String sql = "SELECT * FROM usuario " +
                "WHERE id_usu = '" + id + "'";
        Cursor usuario = db.rawQuery(sql, null);
        if (usuario.moveToFirst()) {//verificando que el objeto usuario tenga resultados
            return usuario; //retornamos datos encontrados
        } else {
            //Nose encuentra el usuario ..Porque no eexiste el email y congtrase{a
            return null;
        }

    }

    // Update
    // Actualizar datos
    public boolean actualizarUsuario(String id, String apellido, String nombre, String correo, String wsp) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            String updateUsuario = "UPDATE usuario " +
                    "SET apellido_usu = '" + apellido + "'," +
                    " nombre_usu = '" + nombre + "' ," +
                    " correo_usu = '" + correo + "' ," +
                    " wsp_usu    = '" + wsp + "' " +
                    "WHERE id_usu = '" + id + "'";
            db.execSQL(updateUsuario);
            db.close();
            return true;
        }
        return false;
    }

    // Actualizar Contraseña
    public boolean actualizarClaveUsuario(String id, String clave) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            String updateUsuario = "UPDATE usuario " +
                    "SET clave_usu = '" + clave + "' " +
                    "WHERE id_usu = '" + id + "'";
            db.execSQL(updateUsuario);
            db.close();
            return true;
        }
        return false;
    }
    // Delete [Sin usar]

    //================================================================
    // G E S T I O N  D E  C L I E N T E S
    // Create
    public boolean agregarCliente(String ci, String apellido, String nombre, String wsp, String dir, String id_usu) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            String newCliente = "INSERT INTO cliente (ci_cli, apellido_cli, nombre_cli, wsp_cli, dir_dom_cli, fk_id_usu) " +
                    "VALUES ('" + ci + "','" + apellido + "','" + nombre + "','" + wsp + "','" + dir + "','" + id_usu + "')";
            db.execSQL(newCliente);
            db.close();
            return true;
        }
        return false;
    }

    // Read
    public Cursor listarClientes(String id_usu) {
        SQLiteDatabase db = getReadableDatabase(); //Llamando a la base de datos
        String sql = "SELECT * FROM cliente " +
                "WHERE fk_id_usu = '" + id_usu + "'";
        Cursor clientes = db.rawQuery(sql, null);
        if (clientes.moveToFirst()) {//verificando que el objeto usuario tenga resultados
            return clientes; //retornamos datos encontrados
        } else {
            //Nose encuentra el usuario ..Porque no eexiste el email y congtrase{a
            return null;
        }

    }

    public Cursor verClientes(String id) {
        SQLiteDatabase db = getReadableDatabase(); //Llamando a la base de datos
        String sql = "SELECT * FROM cliente " +
                "WHERE id_cli = '" + id + "'";
        Cursor cliente = db.rawQuery(sql, null);
        if (cliente.moveToFirst()) {//verificando que el objeto usuario tenga resultados
            return cliente; //retornamos datos encontrados
        } else {
            //Nose encuentra el usuario ..Porque no eexiste el email y congtrase{a
            return null;
        }

    }

    public Cursor buscarCliente(String id_usu, String criterio) {
        SQLiteDatabase db = getReadableDatabase(); //Llamando a la base de datos
        String sql = "SELECT * FROM cliente " +
                "WHERE fk_id_usu = '" + id_usu + "' " +
                "AND apellido_cli LIKE  '%" + criterio + "%' " +
                "OR nombre_cli LIKE '%" + criterio + "%'" +
                "OR ci_cli LIKE '%" + criterio + "%'";
        Cursor cliente = db.rawQuery(sql, null);
        if (cliente.moveToFirst()) {//verificando que el objeto usuario tenga resultados
            return cliente; //retornamos datos encontrados
        } else {
            return null;
        }
    }

    // Update
    public boolean actualizarCliente(String id, String ci, String apellido, String nombre, String wsp, String dir) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            String updateCliente = "UPDATE cliente " +
                    "SET ci_cli = '" + ci + "'," +
                    " apellido_cli = '" + apellido + "' ," +
                    " nombre_cli = '" + nombre + "' ," +
                    " wsp_cli = '" + wsp + "' ," +
                    " dir_dom_cli    = '" + dir + "' " +
                    "WHERE id_cli = '" + id + "'";
            db.execSQL(updateCliente);
            db.close();
            return true;
        }
        return false;
    }

    // Delete
    public boolean eliminarCliente(String id) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            String deleteCliente = "DELETE FROM cliente WHERE id_cli = '" + id + "' ";
            db.execSQL(deleteCliente);
            db.close();
            return true;
        }
        return false;
    }

    //================================================================
    // G E S T I O N  D E  M A S C O T A S
    // Create
    public boolean agregarMascota(String nick, String nombre, String tipo, String fecha_nac, String rasgos, String id_cli) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            String newMascota = "INSERT INTO mascota (nick_mas, nombre_mas, tipo_mas, fecha_nac_mas, rasgos_mas, fk_id_cli) " +
                    "VALUES ('" + nick + "','" + nombre + "','" + tipo + "','" + fecha_nac + "','" + rasgos + "','" + id_cli + "')";
            db.execSQL(newMascota);
            db.close();
            return true;
        }
        return false;
    }

    // Read
    public Cursor listarMascotas(String id_cli) {
        SQLiteDatabase db = getReadableDatabase(); //Llamando a la base de datos
        String sql = "SELECT * FROM mascota " +
                "WHERE fk_id_cli = '" + id_cli + "'";
        Cursor mascotas = db.rawQuery(sql, null);
        if (mascotas.moveToFirst()) {//verificando que el objeto usuario tenga resultados
            return mascotas; //retornamos datos encontrados
        } else {
            //Nose encuentra el usuario ..Porque no eexiste el email y congtrase{a
            return null;
        }

    }

    public Cursor verMascota(String id) {
        SQLiteDatabase db = getReadableDatabase(); //Llamando a la base de datos
        String sql = "SELECT * FROM mascota " +
                "WHERE id_mas = '" + id + "'";
        Cursor mascotas = db.rawQuery(sql, null);
        if (mascotas.moveToFirst()) {//verificando que el objeto usuario tenga resultados
            return mascotas; //retornamos datos encontrados
        } else {
            //Nose encuentra el usuario ..Porque no eexiste el email y congtrase{a
            return null;
        }

    }

    public Cursor buscarMascota(String id_cli, String criterio) {
        SQLiteDatabase db = getReadableDatabase(); //Llamando a la base de datos
        String sql = "SELECT * FROM mascota " +
                "WHERE fk_id_cli = '" + id_cli + "' " +
                "AND nombre_mas LIKE  '%" + criterio + "%' " +
                "OR tipo_mas LIKE '%" + criterio + "%'" +
                "OR rasgos_mas LIKE '%" + criterio + "%'";
        Cursor mascotas = db.rawQuery(sql, null);
        if (mascotas.moveToFirst()) {//verificando que el objeto usuario tenga resultados
            return mascotas; //retornamos datos encontrados
        } else {
            return null;
        }
    }

    // Update
    public boolean actualizarMascota(String id, String nick, String nombre, String tipo, String fecha_nac, String rasgos) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            String updateMascota = "UPDATE mascota " +
                    "SET nick_mas = '" + nick + "'," +
                    " nombre_mas = '" + nombre + "' ," +
                    " tipo_mas = '" + tipo + "' ," +
                    " fecha_nac_mas = '" + fecha_nac + "' ," +
                    " rasgos_mas    = '" + rasgos + "' " +
                    "WHERE id_mas = '" + id + "'";
            db.execSQL(updateMascota);
            db.close();
            return true;
        }
        return false;
    }

    // Delete
    public boolean eliminarMascota(String id) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            // Eliminar todas las mascotas de este cliente!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            String deleteMascota = "DELETE FROM mascota WHERE id_mas = '" + id + "' ";
            db.execSQL(deleteMascota);
            db.close();
            return true;
        }
        return false;
    }

    //================================================================
    // G E S T I O N  D E  C I T A S
    // Create
    public boolean agregarCita(String fecha, String servicio, String fk_mas) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            String newCita = "INSERT INTO cita (fecha_cit, servicio_cit, solucion_cit, fk_id_mas) " +
                    "VALUES ('" + fecha + "','" + servicio + "','','" + fk_mas + "')";
            db.execSQL(newCita);
            db.close();
            return true;
        }
        return false;
    }

    // Read
    //     Listar Citas por mascota
    public Cursor listarCitaMascota(String id_mas) {
        SQLiteDatabase db = getReadableDatabase(); //Llamando a la base de datos
        String sql = "SELECT * FROM cita " +
                "WHERE fk_id_mas = '" + id_mas + "'";
        Cursor citas = db.rawQuery(sql, null);
        if (citas.moveToFirst()) {//verificando que el objeto usuario tenga resultados
            return citas; //retornamos datos encontrados
        } else {
            //Nose encuentra el usuario ..Porque no eexiste el email y congtrase{a
            return null;
        }

    }

    //     Listar citas por dia
    public Cursor listarCitaHoy(String id_usu, String fecha) {
        SQLiteDatabase db = getReadableDatabase(); //Llamando a la base de datos
        String sql = "SELECT cita.* FROM cita " +
                "INNER JOIN mascota ON mascota.id_mas = cita.fk_id_mas " +
                "INNER JOIN cliente ON cliente.id_cli = mascota.fk_id_cli " +
                "INNER JOIN usuario ON usuario.id_usu = cliente.fk_id_usu " +
                "WHERE usuario.id_usu = '" + id_usu + "' " +
                "AND cita.fecha_cit LIKE '%" + fecha + "%' ";
        Cursor citas = db.rawQuery(sql, null);
        if (citas.moveToFirst()) {//verificando que el objeto usuario tenga resultados
            return citas; //retornamos datos encontrados
        } else {
            //Nose encuentra el usuario ..Porque no eexiste el email y congtrase{a
            return null;
        }

    }

    // Update
    public boolean actualizarCita(String id, String fecha, String servicio) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            String updateCita = "UPDATE cita " +
                    "SET fecha_cit = '" + fecha + "'," +
                    " servicio_cit = '" + servicio + "' ," +
                    "WHERE id_cit = '" + id + "'";
            db.execSQL(updateCita);
            db.close();
            return true;
        }
        return false;
    }

    public boolean solucionCita(String id, String solucion) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            String updateCita = "UPDATE cita " +
                    "SET solucion_cit = '" + solucion + "'," +
                    "WHERE id_cit = '" + id + "'";
            db.execSQL(updateCita);
            db.close();
            return true;
        }
        return false;
    }

    // Delete
    public boolean eliminarCita(String id) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            // Eliminar todas las mascotas de este cliente!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            String deleteCita = "DELETE FROM cita WHERE id_cit = '" + id + "' ";
            db.execSQL(deleteCita);
            db.close();
            return true;
        }
        return false;
    }

//================================================================
    // ME T O D O S    E X T R A
}
