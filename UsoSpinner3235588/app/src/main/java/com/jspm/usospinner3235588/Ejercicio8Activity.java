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

public class Ejercicio8Activity extends AppCompatActivity {

    // Declaracion de variables
    private EditText TxtPeso;
    private Spinner SpDestino;
    private  EditText TxtSubtotal;
    private  EditText TxtDescuento;
    private  EditText TxtRecargo;
    private  EditText TxtTotal;

    public int Peso;
    public double Subtotal;
    public double Descuento;
    public double Recargo;
    public double Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio8);

        // Enlace con el activity
        TxtPeso = findViewById(R.id.TxtPesoEjer8);
        TxtSubtotal = findViewById(R.id.TxtSubtEjer8);
        TxtDescuento = findViewById(R.id.TxtDescEjer8);
        TxtRecargo = findViewById(R.id.TxtRecaEjer8);
        TxtTotal = findViewById(R.id.TxtTotalEjer8);
        SpDestino = findViewById(R.id.SpDestEjer8);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metodos

    public void Capturar(){
        if (TxtPeso.getText().toString().isEmpty()){
            TxtPeso.setError("Ingrese el peso del paquete");
        } else {
            Peso = Integer.parseInt(TxtPeso.getText().toString());
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
    }

    public void Calcular(View view){
        Limpiar();
        Capturar();

        if (Peso > 50 ){
            TxtPeso.setError("Error, solo permite un maximo de 50 Kg");
            return;
        } else if (Peso <= 0) {
            TxtPeso.setError("Error, Debe ser un peso mayor a cero");
            return;
        }


        int Seleccion = SpDestino.getSelectedItemPosition();

        if (Seleccion == 1){
            Subtotal = Peso * 5000;
            if (Peso > 10){
                Descuento = Subtotal * 0.08;
            }
        } else if (Seleccion == 2) {
            Subtotal = Peso * 9000;
            if (Peso > 10){
                Descuento = Subtotal * 0.08;
            }
        } else if (Seleccion == 3) {
            Subtotal = Peso * 25000;
            if (Peso > 10){
                Recargo = Subtotal * 0.12;
            }
        }else {
            Toast.makeText(this,
                    "Debe seleccionar un tipo de menu",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Total = Subtotal - Descuento + Recargo;

        TxtSubtotal.setText(String.format("$%s", Subtotal));
        TxtDescuento.setText(String.format("$%s", Descuento));
        TxtRecargo.setText(String.format("$%s", Recargo));
        TxtTotal.setText(String.format("$%s", Total));
    }

    public void Regresar(View view){finish();}
}