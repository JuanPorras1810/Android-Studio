package com.jspm.usospinner3235588;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ejercicio1Activity extends AppCompatActivity {

    // Declaracion de variables
    private EditText TxtCantidadRopa;
    private Spinner SpTipoServicio;
    private  EditText TxtSubtotal;
    private  EditText TxtDescuento;
    private  EditText TxtRecargo;
    private  EditText TxtTotal;

    public int CantidadRopa;
    public double Subtotal;
    public double Descuento;
    public double Recargo;
    public double Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio1);

        // Enlace con el activity

        TxtCantidadRopa = findViewById(R.id.TxtCantRopaEjer1);
        TxtSubtotal = findViewById(R.id.TxtSubtEjer1);
        TxtDescuento = findViewById(R.id.TxtDescEjer1);
        TxtRecargo = findViewById(R.id.TxtRecaEjer1);
        TxtTotal = findViewById(R.id.TxtTotalEjer1);
        SpTipoServicio = findViewById(R.id.SpTipServEjer1);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metodos
    public void Capturar() {
        if (TxtCantidadRopa.getText().toString().isEmpty()) {
            TxtCantidadRopa.setError("Ingrese la cantidad de ropa");
        } else {
            CantidadRopa = Integer.parseInt(TxtCantidadRopa.getText().toString());
        }
    }

    public void Calcular(View view){
        Capturar();

        if (CantidadRopa < 0 || CantidadRopa > 20 ) {
            TxtCantidadRopa.setError("Error, solo permite un maximo de 20 kg");
            return;
        }

        int Seleccion = SpTipoServicio.getSelectedItemPosition();

        if (Seleccion == 1){
            Subtotal = CantidadRopa * 4000;
            if (CantidadRopa > 8) {
                Descuento = Subtotal * 0.10;
            }
        } else if (Seleccion == 2) {
            Subtotal = CantidadRopa * 7000;
            if (CantidadRopa > 8) {
                Descuento = Subtotal * 0.10;
            }
        } else if (Seleccion == 3) {
            Subtotal = CantidadRopa * 12000;
            if (CantidadRopa > 5) {
                Recargo = Subtotal * 0.15;
            }
        }else {
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