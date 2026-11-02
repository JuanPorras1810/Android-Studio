package com.jspm.usospinner3235588;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ejercicio6Activity extends AppCompatActivity {

    // Declaracion de variables
    private EditText TxtDistancia;
    private Spinner SpTransporte;
    private  EditText TxtSubtotal;
    private  EditText TxtDescuento;
    private  EditText TxtRecargo;
    private  EditText TxtTotal;

    public double Distancia;
    public double Subtotal;
    public double Descuento;
    public double Recargo;
    public double Total;
    public double Tarifa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio6);

        // Enlace con el activity
        TxtDistancia = findViewById(R.id.TxtDistEjer6);
        TxtSubtotal = findViewById(R.id.TxtSubtEjer6);
        TxtDescuento = findViewById(R.id.TxtDescEjer6);
        TxtRecargo = findViewById(R.id.TxtRecaEjer6);
        TxtTotal = findViewById(R.id.TxtTotalEjer6);
        SpTransporte = findViewById(R.id.SpTranEjer6);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    // Metodos

    public void Capturar(){
        if (TxtDistancia.getText().toString().isEmpty()){
            TxtDistancia.setError("Ingrese la distancia recorrida");
        } else {
            Distancia = Double.parseDouble(TxtDistancia.getText().toString());
        }
    }

    public void Limpiar(){
        TxtSubtotal.setText(null);
        TxtDescuento.setText(null);
        TxtRecargo.setText(null);
        TxtTotal.setText(null);
        Subtotal = 0;
        Descuento = 0;
        Recargo = 0;
        Total = 0;
        Tarifa = 0;
    }

    public void Calcular(View view){
        Limpiar();
        Capturar();

        if (Distancia > 500 ){
            TxtDistancia.setError("Error, solo permite un maximo de 500 Km");
            return;
        } else if (Distancia <= 0) {
            TxtDistancia.setError("Error, Debe ser una cantidad mayor que cero");
            return;
        }


        int Seleccion = SpTransporte.getSelectedItemPosition();

        if (Seleccion == 1){
            Subtotal = Distancia * 1500;
            if (Distancia > 50){
                Descuento = Subtotal * 0.08;
            }
        } else if (Seleccion == 2) {
            Subtotal = Distancia * 2800;
            if (Distancia > 50){
                Descuento = Subtotal * 0.08;
            }
        } else if (Seleccion == 3) {
            Subtotal = Distancia * 4500;
            if (Distancia > 100){
                Recargo = Subtotal * 0.10;
            }
        }else {
            Toast.makeText(this,
                    "Debe seleccionar un tipo de transporte",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (Distancia < 5){
            Recargo = Recargo + 12000;
        }


        Total = Subtotal - Descuento + Recargo;

        TxtSubtotal.setText(String.format("$%s", Subtotal));
        TxtDescuento.setText(String.format("$%s", Descuento));
        TxtRecargo.setText(String.format("$%s", Recargo));
        TxtTotal.setText(String.format("$%s", Total));
    }

    public void Regresar(View view){finish();}
}