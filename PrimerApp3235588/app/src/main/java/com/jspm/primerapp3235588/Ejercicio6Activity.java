package com.jspm.primerapp3235588;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ejercicio6Activity extends AppCompatActivity {

    // Declaracion de variables
    private EditText TxtCantidad;
    private EditText TxtPrecio;
    private EditText TxtDescuento;
    private EditText TxtTotal;

    public int Cantidad;
    public double Precio;
    public double Descuento;
    public double Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio6);

        // Enlace con el activity

        TxtCantidad = findViewById(R.id.TxtCantiEjer6);
        TxtPrecio = findViewById(R.id.TxtPrecEjer6);
        TxtDescuento = findViewById(R.id.TxtDescEjer6);
        TxtTotal = findViewById(R.id.TxtTotalEjer6);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metodos

    public void Capturar(){
        if (TxtCantidad.getText().toString().isEmpty()) {
            TxtCantidad.setError("Ingrese la cantidad");
        }else {
            Cantidad = Integer.parseInt(TxtCantidad.getText().toString());
        }
    }

    public void Calcular(View view){
        Capturar();
        Precio = Cantidad * 1100000;

        if (Cantidad < 5) {
            Descuento = Precio * 0.10;
        } else if (Cantidad >= 5 && Cantidad < 10) {
            Descuento = Precio * 0.20;
        } else if (Cantidad >= 10) {
            Descuento = Precio * 0.40;
        }else {
            Descuento = 0;
        }

        Total = Precio - Descuento;

        TxtPrecio.setText(String.format("$%s", Precio));
        TxtDescuento.setText(String.format("$%s", Descuento));
        TxtTotal.setText(String.format("$%s", Total));
    }

    public void Regresar(View view){finish();}
}