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

public class Ejercicio7Activity extends AppCompatActivity {

    // Declaracion de variables
    private EditText TxtAsistentes;
    private Spinner SpMenu;
    private  EditText TxtSubtotal;
    private  EditText TxtDescuento;
    private  EditText TxtRecargo;
    private  EditText TxtTotal;

    public int Asistentes;
    public double Subtotal;
    public double Descuento;
    public double Recargo;
    public double Total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio7);

        // Enlace con el activity
        TxtAsistentes = findViewById(R.id.TxtAsisEjer7);
        TxtSubtotal = findViewById(R.id.TxtSubtEjer7);
        TxtDescuento = findViewById(R.id.TxtDescEjer7);
        TxtRecargo = findViewById(R.id.TxtRecaEjer7);
        TxtTotal = findViewById(R.id.TxtTotalEjer7);
        SpMenu = findViewById(R.id.SpMenuEjer7);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metodos

    public void Capturar(){
        if (TxtAsistentes.getText().toString().isEmpty()){
            TxtAsistentes.setError("Ingrese la cantidad de asistentes");
        } else {
            Asistentes = Integer.parseInt(TxtAsistentes.getText().toString());
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

        if (Asistentes > 300 ){
            TxtAsistentes.setError("Error, solo permite un maximo de 300 asistentes");
            return;
        } else if (Asistentes < 10) {
            TxtAsistentes.setError("Error, Debe haber mas de 10 asistentes");
            return;
        }


        int Seleccion = SpMenu.getSelectedItemPosition();

        if (Seleccion == 1){
            Subtotal = Asistentes * 18000;
            if (Asistentes > 50){
                Descuento = Subtotal * 0.10;
            }
        } else if (Seleccion == 2) {
            Subtotal = Asistentes * 28000;
            if (Asistentes > 50){
                Descuento = Subtotal * 0.10;
            }
        } else if (Seleccion == 3) {
            Subtotal = Asistentes * 42000;
            if (Asistentes > 80){
                Descuento = Subtotal * 0.15;
            }
        }else {
            Toast.makeText(this,
                    "Debe seleccionar un tipo de menu",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (Asistentes < 15){
            Recargo = 100000;
        }


        Total = Subtotal - Descuento + Recargo;

        TxtSubtotal.setText(String.format("$%s", Subtotal));
        TxtDescuento.setText(String.format("$%s", Descuento));
        TxtRecargo.setText(String.format("$%s", Recargo));
        TxtTotal.setText(String.format("$%s", Total));
    }

    public void Regresar(View view){finish();}
}