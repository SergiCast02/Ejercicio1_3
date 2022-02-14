package com.example.ejercicio1_3.db;

public class DDL {
    //Base de Datos
    public static final String DATABASE_NOMBRE = "base";

    //Tablas
    public static final String TABLE_PERSONA = "t_persona";
    //Campos
    public static final String persona_id = "id";
    public static final String persona_nombre = "nombre";
    public static final String persona_apellidos = "apellidos";
    public static final String persona_edad = "edad";
    public static final String persona_correo = "correo";
    public static final String persona_direccion = "direccion";

    //Transacciones DDL
    public static final String createTablePersonas = "CREATE TABLE " + TABLE_PERSONA + "(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT NOT NULL, " +
            "apellidos TEXT NOT NULL, " +
            "edad TEXT NOT NULL, " +
            "correo TEXT, " +
            "direccion TEXT)";

    public static final String dropTablePersonas = "DROP TABLE IF EXIST" + TABLE_PERSONA;
}