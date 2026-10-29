package com.jspm.primerapp3235588;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_index);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void IrCalculadora(View view){
        Intent Calculadora = new Intent(IndexActivity.this,CalculadoraActivity.class);
        startActivity(Calculadora);
    }

    public void IrEjercicio1(View view){
        Intent Ejercicio1 = new Intent(IndexActivity.this,Ejercicio1Activity.class);
        startActivity(Ejercicio1);
    }

    public void IrEjercicio2(View view){
        Intent Ejercicio2 = new Intent(IndexActivity.this,Ejercicio2Activity.class);
        startActivity(Ejercicio2);
    }

    public void IrEjercicio3(View view){
        Intent Ejercicio3 = new Intent(IndexActivity.this,Ejercicio3Activity.class);
        startActivity(Ejercicio3);
    }

    public void IrEjercicio4(View view){
        Intent Ejercicio4 = new Intent(IndexActivity.this,Ejercicio4Activity.class);
        startActivity(Ejercicio4);
    }

}