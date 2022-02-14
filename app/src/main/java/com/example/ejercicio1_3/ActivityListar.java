package com.example.ejercicio1_3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ejercicio1_3.db.DDL;
import com.example.ejercicio1_3.db.DbHelper;
import com.example.ejercicio1_3.db.tables.personas;

import java.util.ArrayList;
import java.util.EventListener;

public class ActivityListar extends AppCompatActivity {

    DbHelper conexion;
    ListView lista;
    ArrayList <personas> listaPersona;
    ArrayList <String> ArregloPersona;
    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        lista = (ListView) findViewById(R.id.listar);

        conexion = new DbHelper(this, DDL.DATABASE_NOMBRE,null,1);

        obtenerlistaPersona();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ArregloPersona);
        lista.setAdapter(adp);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ObtenerPersona(i);
            }
        });

        regresar = (Button) findViewById(R.id.btnRegresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void ObtenerPersona(int id) {
        personas persona = listaPersona.get(id);

        Intent i = new Intent(getApplicationContext(),ActivityPersona.class);

        i.putExtra("codigo", persona.getId()+"");
        i.putExtra("nombre", persona.getNombre());
        i.putExtra("apellidos", persona.getApellidos());
        i.putExtra("edad", persona.getEdad()+"");
        i.putExtra("correo", persona.getCorreo());
        i.putExtra("direccion", persona.getDireccion());

        startActivity(i);
    }

    private void obtenerlistaPersona() {
        //conexion a la BD modo lectura
        SQLiteDatabase db = conexion.getReadableDatabase();

        //clase empleados
        personas list_person = null;

        //inicializar array empleados con la clase
        listaPersona = new ArrayList<personas>();

        //consulta BD directa
        Cursor cursor = db.rawQuery("SELECT * FROM "+ DDL.TABLE_PERSONA, null);

        //RECORRER LA TABLA MOVIENDONOS SOBRE EL CURSOR
        while (cursor.moveToNext())
        {
            list_person = new personas();
            list_person.setId(cursor.getInt(0));
            list_person.setNombres(cursor.getString(1));
            list_person.setApellidos(cursor.getString(2));
            list_person.setEdad(cursor.getString(3));
            list_person.setCorreo(cursor.getString(4));
            list_person.setDireccion(cursor.getString(5));
            listaPersona.add(list_person);
        }
        cursor.close();
        //metodo para llenar lista
        llenarlista();
    }
    private void llenarlista()
    {
        ArregloPersona = new ArrayList<String>();

        for (int i=0; i<listaPersona.size();i++)
        {
            ArregloPersona.add(listaPersona.get(i).getId()+" | "+
                    listaPersona.get(i).getNombre()+" "+
                    listaPersona.get(i).getApellidos()+"\n"+
                    listaPersona.get(i).getEdad()+" años\n"+
                    listaPersona.get(i).getCorreo()+"\n"+
                    listaPersona.get(i).getDireccion());

        }
    }
}