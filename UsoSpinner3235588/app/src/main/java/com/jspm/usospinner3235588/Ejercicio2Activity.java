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

public class Ejercicio2Activity extends AppCompatActivity {

    // Declaracion de variables
    private EditText TxtCantidadNoches;
    private Spinner SpTipoHabitacion;
    private  EditText TxtHabitacion;
    private  EditText TxtSubtotal;
    private  EditText TxtDescuento;
    private  EditText TxtTotal;

    public int CantidadNoches;
    public String Habitacion;
    public double Subtotal;
    public double Descuento;
    public double Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio2);

        // Enlace con el activity
        TxtCantidadNoches = findViewById(R.id.TxtCantNochEjer2);
        TxtHabitacion = findViewById(R.id.TxtNumHabiEjer2);
        TxtSubtotal = findViewById(R.id.TxtSubtEjer2);
        TxtDescuento = findViewById(R.id.TxtDescEjer2);
        TxtTotal = findViewById(R.id.TxtTotalEjer2);
        SpTipoHabitacion = findViewById(R.id.SpHabiEjer2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // Metodos

    public void Capturar(){
        if (TxtCantidadNoches.getText().toString().isEmpty()){
            TxtCantidadNoches.setError("Ingrese la cantidad de noches");
        } else {
            CantidadNoches = Integer.parseInt(TxtCantidadNoches.getText().toString());
        }
    }

    public void Limpiar(){
        TxtHabitacion.setText(null);
        TxtSubtotal.setText(null);
        TxtDescuento.setText(null);
        TxtTotal.setText(null);
        Habitacion = null;
        Subtotal = 0;
        Descuento = 0;
        Total = 0;
    }

    public void Calcular(View view){
        Limpiar();
        Capturar();

        if ( CantidadNoches <= 0 || CantidadNoches > 30 ){
            TxtCantidadNoches.setError("Error, solo permite un maximo de 30 noches");
            return;
        }


        int Seleccion = SpTipoHabitacion.getSelectedItemPosition();

        if (Seleccion == 1){
            Subtotal = CantidadNoches * 90000;
            if (CantidadNoches > 5){
                Descuento = Subtotal * 0.08;
            }
        } else if (Seleccion == 2) {
            Subtotal = CantidadNoches * 150000;
            if (CantidadNoches > 5){
                Descuento = Subtotal * 0.08;
            }
        } else if (Seleccion == 3) {
            Subtotal = CantidadNoches * 230000;
            if (CantidadNoches > 7){
                Descuento = Subtotal * 0.12;
            }
        }else {
            Toast.makeText(this,
                    "Debe seleccionar un tipo de habitacion",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Total = Subtotal - Descuento;
        Habitacion = SpTipoHabitacion.getSelectedItem().toString();

        TxtHabitacion.setText(Habitacion);
        TxtSubtotal.setText(String.format("$%s", Subtotal));
        TxtDescuento.setText(String.format("$%s", Descuento));
        TxtTotal.setText(String.format("$%s", Total));
    }

    public void Regresar(View view){finish();}
}