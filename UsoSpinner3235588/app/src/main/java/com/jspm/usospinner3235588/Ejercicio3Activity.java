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

public class Ejercicio3Activity extends AppCompatActivity {

    // Declaracion de variables
    private EditText TxtDias;
    private Spinner SpVehiculo;
    private  EditText TxtTarifa;
    private  EditText TxtSubtotal;
    private  EditText TxtDescuento;
    private  EditText TxtTotal;

    public int Dias;
    public double Tarifa;
    public double Subtotal;
    public double Descuento;
    public double Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio3);

        // Enlace con el activity
        TxtDias = findViewById(R.id.TxtNumDiasEjer3);
        TxtTarifa = findViewById(R.id.TxtTariEjer3);
        TxtSubtotal = findViewById(R.id.TxtSubtEjer3);
        TxtDescuento = findViewById(R.id.TxtDescEjer3);
        TxtTotal = findViewById(R.id.TxtTotalEjer3);
        SpVehiculo = findViewById(R.id.SpVehiEjer3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metodos

    public void Capturar(){
        if (TxtDias.getText().toString().isEmpty()){
            TxtDias.setError("Ingrese la cantidad de dias de alquiler");
        } else {
            Dias = Integer.parseInt(TxtDias.getText().toString());
        }
    }

    public void Limpiar(){
        TxtTarifa.setText(null);
        TxtSubtotal.setText(null);
        TxtDescuento.setText(null);
        TxtTotal.setText(null);
        Tarifa = 0;
        Subtotal = 0;
        Descuento = 0;
        Total = 0;
    }

    public void Calcular(View view){
        Limpiar();
        Capturar();

        if ( Dias > 30 ){
            TxtDias.setError("Error, solo permite un maximo de 30 noches");
            return;
        } else if (Dias <= 0) {
            TxtDias.setError("Error, Debe ser un numero mayor que cero");
            return;
        }


        int Seleccion = SpVehiculo.getSelectedItemPosition();

        if (Seleccion == 1){
            Tarifa = 70000;
            Subtotal = Dias * 70000;
            if (Dias > 7){
                Descuento = Subtotal * 0.10;
            }
        } else if (Seleccion == 2) {
            Tarifa = 140000;
            Subtotal = Dias * 140000;
            if (Dias > 7){
                Descuento = Subtotal * 0.10;
            }
        } else if (Seleccion == 3) {
            Tarifa = 220000;
            Subtotal = Dias * 220000;
            if (Dias > 10){
                Descuento = Subtotal * 0.15;
            }
        }else {
            Toast.makeText(this,
                    "Debe seleccionar un tipo de vehiculo",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Total = Subtotal - Descuento;

        TxtTarifa.setText(String.valueOf(Tarifa));
        TxtSubtotal.setText(String.format("$%s", Subtotal));
        TxtDescuento.setText(String.format("$%s", Descuento));
        TxtTotal.setText(String.format("$%s", Total));
    }

    public void Regresar(View view){finish();}

}