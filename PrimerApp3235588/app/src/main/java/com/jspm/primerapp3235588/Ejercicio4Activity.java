package com.jspm.primerapp3235588;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ejercicio4Activity extends AppCompatActivity {

    // Declaracion de variables

    private EditText TxtMonto;
    private EditText TxtCuota;

    public double Monto;
    public double Cuota;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio4);

        // Enlace con el activity
        TxtMonto = findViewById(R.id.TxtMontoEjer4);
        TxtCuota = findViewById(R.id.TxtCuotaEjer4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Metodos

    public void Capturar(){
        if (TxtMonto.getText().toString().isEmpty()){
            TxtMonto.setError("Ingrese el monto de la fianza");
        } else {
            Monto = Double.parseDouble(TxtMonto.getText().toString());
        }
    }

    public void CalcularCuota(View view){
        Capturar();

        if (Monto > 50000){
            Cuota = Monto * 0.02;
            TxtCuota.setText(String.format("$%s", Cuota));
        } else if (Monto < 50000 || Monto > 0) {
            Cuota = Monto * 0.03;
            TxtCuota.setText(String.format("$%s", Cuota));
        }else
        {
            TxtMonto.setError("El monto no es valido");
        }
    }

    public void Regresar(View view){finish();}
}