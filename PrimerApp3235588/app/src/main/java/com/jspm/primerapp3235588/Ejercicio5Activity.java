package com.jspm.primerapp3235588;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ejercicio5Activity extends AppCompatActivity {

    // Declaracion de variables
    private EditText TxtEdad;
    private EditText TxtAntiguedad;
    private TextView TxtClasificacion;

    public int Edad;
    public int Antiguedad;
    public String Clasificacion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio5);

        // Enlace con el activity
        TxtEdad = findViewById(R.id.TxtEdadEjer5);
        TxtAntiguedad = findViewById(R.id.TxtAntiEjer5);
        TxtClasificacion = findViewById(R.id.TxtClasiEjer5);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metodos

    public void Capturar(){
        if (TxtEdad.getText().toString().isEmpty()){
            TxtEdad.setError("Ingrese la edad");
        } else if (TxtAntiguedad.getText().toString().isEmpty()) {
            TxtAntiguedad.setError("Ingrese los años de antigüedad");
        }else {
            Edad = Integer.parseInt(TxtEdad.getText().toString());
            Antiguedad = Integer.parseInt(TxtAntiguedad.getText().toString());
        }
    }

    public void Clasificar(View view){
        Capturar();
        if (Edad >= 60 && Antiguedad < 25) {
            TxtClasificacion.setText("Jubilacion por edad");
        } else if (Edad < 60 && Antiguedad >= 25) {
            TxtClasificacion.setText("Jubilacion por antigüedad joven");
        } else if (Edad >= 60 && Antiguedad >= 25) {
            TxtClasificacion.setText("Jubilacion por antigüedad adulta");
        } else {
            TxtClasificacion.setText("No cumple los requisitos");
        }
    }

    public void Regresar(View view){finish();}

}