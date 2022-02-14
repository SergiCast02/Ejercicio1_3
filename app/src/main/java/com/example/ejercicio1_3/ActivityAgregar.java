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

public class ActivityAgregar extends AppCompatActivity {

    EditText editText1, editText2, editText3, editText4, editText5;
    Button btn_salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        // Inicialización
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        btn_salvar = (Button) findViewById(R.id.btnSalvar);

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val1 = editText1.getText().toString();
                String val2 = editText2.getText().toString();
                String val3 = editText3.getText().toString();
                String val4 = editText4.getText().toString();
                String val5 = editText5.getText().toString();

                if(val1.isEmpty() || val2.isEmpty() || val3.isEmpty() || val4.isEmpty() || val5.isEmpty()){
                    Toast.makeText(ActivityAgregar.this,"Debes completar todos los campos!", Toast.LENGTH_SHORT).show();
                }else if (val4.contains("@") == false || val4.contains(".") == false) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Debe introducir un correo electrónico válido!", Toast.LENGTH_SHORT);
                    toast1.show();
                }else{
                    agregarPersonas();
                }


            }
        });
    }

    private void agregarPersonas() {

        DbHelper conexion = new DbHelper(this, DDL.DATABASE_NOMBRE, null, 1);

        SQLiteDatabase db = conexion.getWritableDatabase();


        ContentValues valores = new ContentValues();

        valores.put(DDL.persona_nombre, editText1.getText().toString());
        valores.put(DDL.persona_apellidos, editText2.getText().toString());
        valores.put(DDL.persona_edad, editText3.getText().toString());
        valores.put(DDL.persona_correo, editText4.getText().toString());
        valores.put(DDL.persona_direccion, editText5.getText().toString());

        Long resultado = db.insert(DDL.TABLE_PERSONA, DDL.persona_id, valores);

        Toast.makeText(getApplicationContext(), "Registro ingreso con exito, Codigo " + resultado.toString(),Toast.LENGTH_LONG).show();

        db.close();

        limpiar();

        //Volver al Menú
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    private void limpiar() {
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        editText5.setText("");
    }
}