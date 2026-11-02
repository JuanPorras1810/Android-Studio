package com.jspm.primerapp3235588;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ejercicio1Activity extends AppCompatActivity {
    //Declaracion de variables
    private EditText TxtPersona1;
    private EditText TxtPersona2;
    private EditText TxtPersona3;
    private EditText TxtTotal;
    private EditText TxtPorcePerso1; // Porsentaje invertido por la persona 1
    private EditText TxtPorcePerso2; // Porsentaje invertido por la persona 2
    private EditText TxtPorcePerso3; // Porsentaje invertido por la persona 3


    public double Persona1;
    public double Persona2;
    public double Persona3;
    public double TotalInversion;
    public double PorcePerso1; // Porsentaje invertido por la persona 1
    public double PorcePerso2; // Porsentaje invertido por la persona 2
    public double PorcePerso3; // Porsentaje invertido por la persona 3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio1);

        //Enlace con el activity
        TxtPersona1 = findViewById(R.id.TxtPerso1Ejer1);
        TxtPersona2 = findViewById(R.id.TxtPerso2Ejer1);
        TxtPersona3 = findViewById(R.id.TxtPerso3Ejer1);
        TxtTotal = findViewById(R.id.TxtTotalEjer1);
        TxtPorcePerso1 = findViewById(R.id.TxtInv1Ejer1);
        TxtPorcePerso2 = findViewById(R.id.TxtInv2Ejer1);
        TxtPorcePerso3 = findViewById(R.id.TxtInv3Ejer1);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Metodos

    public void Capturar(){
        if (TxtPersona1.getText().toString().isEmpty()) {
            TxtPersona1.setError("Ingrese la inversion de la persona 1");
        } else if (TxtPersona2.getText().toString().isEmpty()) {
            TxtPersona2.setError("Ingrese la inversion de la persona 2");
        } else if (TxtPersona3.getText().toString().isEmpty()) {
            TxtPersona3.setError("Ingrese la inversion de la persona 3");
        }else {
            Persona1 = Double.parseDouble(TxtPersona1.getText().toString());
            Persona2 = Double.parseDouble(TxtPersona2.getText().toString());
            Persona3 = Double.parseDouble(TxtPersona3.getText().toString());
        }

    }
    public void Calcular(View view){
        Capturar();
        TotalInversion = Persona1 + Persona2 + Persona3;

        PorcePerso1 = (Persona1 / TotalInversion) * 100;
        PorcePerso2 = (Persona2 / TotalInversion) * 100;
        PorcePerso3 = (Persona3 / TotalInversion) * 100;

        TxtTotal.setText(String.valueOf(TotalInversion));
        TxtPorcePerso1.setText(String.valueOf(PorcePerso1 + "%"));
        TxtPorcePerso2.setText(String.valueOf(PorcePerso2 + "%"));
        TxtPorcePerso3.setText(String.valueOf(PorcePerso3 + "%"));
    }

    public void Regresar(View view){finish();}

}