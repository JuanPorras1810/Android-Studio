package com.jspm.primerapp3235588;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ejercicio7Activity extends AppCompatActivity {

    //Declaracion de variables

    private EditText TxtNumero1;
    private EditText TxtNumero2;
    private EditText TxtResultado;
    private Spinner SpOperaciones;

    public int Numero1;
    public int Numero2;
    public int Resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio7);

        //Enlace con el activity
        TxtNumero1 = findViewById(R.id.TxtNum1Ejer7);
        TxtNumero2 = findViewById(R.id.TxtNum2Ejer7);
        TxtResultado = findViewById(R.id.TxtResulEjer7);
        SpOperaciones = findViewById(R.id.SpOperaEjer7);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Metodos

    public void Capturar(){
        if (TxtNumero1.getText().toString().isEmpty()){
            TxtNumero1.setError("Ingrese el numero 1");
        }
        else if (TxtNumero2.getText().toString().isEmpty()) {
            TxtNumero2.setError("Ingrese el numero 2");
        }  else {
            Numero1 = Integer.parseInt(TxtNumero1.getText().toString());
            Numero2 = Integer.parseInt(TxtNumero2.getText().toString());
        }
    }

    public void Calcular(View view){
        Capturar();

        int Seleccion = SpOperaciones.getSelectedItemPosition();

        if (Seleccion == 1){
            Resultado = Numero1 + Numero2;
            TxtResultado.setText(String.valueOf(Resultado));
        } else if (Seleccion == 2) {
            Resultado = Numero1 - Numero2;
            TxtResultado.setText(String.valueOf(Resultado));
        } else if (Seleccion == 3) {
            Resultado = Numero1 * Numero2;
            TxtResultado.setText(String.valueOf(Resultado));
        } else if (Seleccion == 4) {
            Resultado = Numero1 / Numero2;
            TxtResultado.setText(String.valueOf(Resultado));
        }else {
            TxtResultado.setError("No elegiste una operacion");
        }
    }

    public void Regresar(View view){finish();}
}