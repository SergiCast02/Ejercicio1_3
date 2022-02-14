package com.example.ejercicio1_3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio1_3.db.DDL;
import com.example.ejercicio1_3.db.DbHelper;

public class ActivityPersona extends AppCompatActivity {
    EditText nombres, apellidos, edad, correo, direccion, codigo;
    Button botonActualizar, botonEliminar, botonRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);

        codigo = (EditText) findViewById(R.id.txtCodigo);
        nombres = (EditText) findViewById(R.id.eptxtnombre);
        apellidos = (EditText) findViewById(R.id.eptxtapellidos);
        edad = (EditText) findViewById(R.id.eptxtedad);
        correo = (EditText) findViewById(R.id.eptxtcorreo);
        direccion = (EditText) findViewById(R.id.eptxtdireccion);

        botonActualizar = (Button) findViewById(R.id.btnActualizar);
        botonEliminar = (Button) findViewById(R.id.btnEliminar);
        botonRegresar = (Button) findViewById(R.id.btnRegresar) ;

        codigo.setText(getIntent().getStringExtra("codigo"));
        nombres.setText(getIntent().getStringExtra("nombre"));
        apellidos.setText(getIntent().getStringExtra("apellidos"));
        edad.setText(getIntent().getStringExtra("edad"));
        correo.setText(getIntent().getStringExtra("correo"));
        direccion.setText(getIntent().getStringExtra("direccion"));

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizarPersona();


            }
        });

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarPersona();
            }
        });

        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ActivityListar.class);
                startActivity(i);
            }
        });
    }

    private void actualizarPersona() {
        DbHelper conexion = new DbHelper(this, DDL.DATABASE_NOMBRE, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String obtenerCodigo = codigo.getText().toString();

        ContentValues valores = new ContentValues();

        valores.put(DDL.persona_nombre, nombres.getText().toString());
        valores.put(DDL.persona_apellidos, apellidos.getText().toString());
        valores.put(DDL.persona_edad, edad.getText().toString());
        valores.put(DDL.persona_correo, correo.getText().toString());
        valores.put(DDL.persona_direccion, direccion.getText().toString());

        db.update(DDL.TABLE_PERSONA, valores , DDL.persona_id +" = "+obtenerCodigo, null);
        db.close();

        Intent intent = new Intent(getApplicationContext(),ActivityListar.class);
        startActivity(intent);


    }

    private void eliminarPersona() {
        DbHelper conexion = new DbHelper(this, DDL.DATABASE_NOMBRE, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String obtenerCodigo = codigo.getText().toString();

        db.delete(DDL.TABLE_PERSONA,DDL.persona_id +" = "+ obtenerCodigo, null);

        Toast.makeText(getApplicationContext(), "Registro eliminado con exito, Codigo " + obtenerCodigo,Toast.LENGTH_LONG).show();
        db.close();

        //REGRESAR AL MENU PRINCIPAL
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);

    }


}