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

public class Ejercicio5Activity extends AppCompatActivity {

    // Declaracion de variables
    private EditText TxtMetros;
    private Spinner SpPintura;
    private  EditText TxtSubtotal;
    private  EditText TxtDescuento;
    private  EditText TxtRecargo;
    private  EditText TxtTotal;

    public double Metros;
    public double Subtotal;
    public double Descuento;
    public double Recargo;
    public double Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio5);

        // Enlace con el activity
        TxtMetros = findViewById(R.id.TxtMetrEjer5);
        TxtSubtotal = findViewById(R.id.TxtSubtEjer5);
        TxtDescuento = findViewById(R.id.TxtDescEjer5);
        TxtRecargo = findViewById(R.id.TxtRecaEjer5);
        TxtTotal = findViewById(R.id.TxtTotalEjer5);
        SpPintura = findViewById(R.id.SpPintEjer5);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metodos

    public void Capturar(){
        if (TxtMetros.getText().toString().isEmpty()){
            TxtMetros.setError("Ingrese la cantidad de metros cuadrados");
        } else {
            Metros = Double.parseDouble(TxtMetros.getText().toString());
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

        if (Metros > 500 ){
            TxtMetros.setError("Error, solo permite un maximo de 500 metros");
            return;
        } else if (Metros <= 0) {
            TxtMetros.setError("Error, Debe ser una cantidad mayor que cero");
            return;
        }


        int Seleccion = SpPintura.getSelectedItemPosition();

        if (Seleccion == 1){
            Subtotal = Metros * 12000;
            if (Metros > 80){
                Descuento = Subtotal * 0.10;
            }
        } else if (Seleccion == 2) {
            Subtotal = Metros * 18000;
            if (Metros > 80){
                Descuento = Subtotal * 0.10;
            }
        } else if (Seleccion == 3) {
            Subtotal = Metros * 27000;
            if (Metros > 100){
                Descuento = Subtotal * 0.15;
            }
        }else {
            Toast.makeText(this,
                    "Debe seleccionar un tipo de pintura",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (Metros < 20){
            Recargo = 40000;
        }
        

        Total = Subtotal - Descuento + Recargo;

        TxtSubtotal.setText(String.format("$%s", Subtotal));
        TxtDescuento.setText(String.format("$%s", Descuento));
        TxtRecargo.setText(String.format("$%s", Recargo));
        TxtTotal.setText(String.format("$%s", Total));
    }

    public void Regresar(View view){finish();}
}