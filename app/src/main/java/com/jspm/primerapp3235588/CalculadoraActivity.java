package com.jspm.primerapp3235588;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculadoraActivity extends AppCompatActivity {

    //Declaracion de variables

    private EditText TxtNumero1;
    private EditText TxtNumero2;
    private EditText TxtResultado;

    public int numero1;
    public int numero2;
    public int resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculadora);

        TxtNumero1 = findViewById(R.id.TxtNum1Calc);
        TxtNumero2 = findViewById(R.id.TxtNum2Calc);
        TxtResultado = findViewById(R.id.TxtResulCalc);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void capturar(){
        if (TxtNumero1.getText().toString().isEmpty()){
            TxtNumero1.setError("Ingrese el numero 1");
        }
        else if (TxtNumero2.getText().toString().isEmpty()) {
            TxtNumero2.setError("Ingrese el numero 2");
        }
        else {
            numero1 = Integer.parseInt(TxtNumero1.getText().toString());
            numero2 = Integer.parseInt(TxtNumero2.getText().toString());
        }
    }

    public void Sumar(View view){
        capturar();
        resultado = numero1 + numero2;
        TxtResultado.setText(String.valueOf(resultado));
    }

    public void Restar(View view){
        capturar();
        resultado = numero1 - numero2;
        TxtResultado.setText(String.valueOf(resultado));
    }

    public void Multiplicar(View view){
        capturar();
        resultado = numero1 * numero2;
        TxtResultado.setText(String.valueOf(resultado));
    }

    public void Dividir(View view){
        capturar();
        resultado = numero1 / numero2;
        TxtResultado.setText(String.valueOf(resultado));
    }
}