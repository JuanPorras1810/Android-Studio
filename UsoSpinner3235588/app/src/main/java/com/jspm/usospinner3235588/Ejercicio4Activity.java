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

public class Ejercicio4Activity extends AppCompatActivity {

    // Declaracion de variables
    private EditText TxtHoras;
    private Spinner SpModalidad;
    private  EditText TxtSubtotal;
    private  EditText TxtDescuento;
    private  EditText TxtRecargo;
    private  EditText TxtTotal;

    public int Horas;
    public double Subtotal;
    public double Descuento;
    public double Recargo;
    public double Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio4);

        // Enlace con el activity
        TxtHoras = findViewById(R.id.TxtNumHorEjer4);
        TxtSubtotal = findViewById(R.id.TxtSubtEjer4);
        TxtDescuento = findViewById(R.id.TxtDescEjer4);
        TxtRecargo = findViewById(R.id.TxtRecaEjer4);
        TxtTotal = findViewById(R.id.TxtTotalEjer4);
        SpModalidad = findViewById(R.id.SpModEjer4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metodos

    public void Capturar(){
        if (TxtHoras.getText().toString().isEmpty()){
            TxtHoras.setError("Ingrese la cantidad de horas de formacion");
        } else {
            Horas = Integer.parseInt(TxtHoras.getText().toString());
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

        if ( Horas > 120 ){
            TxtHoras.setError("Error, solo permite un maximo de 120 horas");
            return;
        } else if (Horas < 10) {
            TxtHoras.setError("Error, Debe ser mas de 10 horas");
            return;
        }


        int Seleccion = SpModalidad.getSelectedItemPosition();

        if (Seleccion == 1){
            Subtotal = Horas * 15000;
            if (Horas > 40){
                Descuento = Subtotal * 0.12;
            }
        } else if (Seleccion == 2) {
            Subtotal = Horas * 25000;
            if (Horas > 60){
                Descuento = Subtotal * 0.08;
            } else if (Horas < 20) {
                Recargo = Subtotal * 0.05;
            }
        } else if (Seleccion == 3) {
            Subtotal = Horas * 20000;
            if (Horas > 40){
                Descuento = Subtotal * 0.12;
            }
        }else {
            Toast.makeText(this,
                    "Debe seleccionar una modalidad",
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