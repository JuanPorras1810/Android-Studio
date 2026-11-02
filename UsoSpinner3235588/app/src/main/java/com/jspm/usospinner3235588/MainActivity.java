package com.jspm.usospinner3235588;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Metodos

    public void IrEjercicio1(View view){
        Intent Ejercicio1 = new Intent(MainActivity.this,Ejercicio1Activity.class);
        startActivity(Ejercicio1);
    }

    public void IrEjercicio2(View view){
        Intent Ejercicio2 = new Intent(MainActivity.this,Ejercicio2Activity.class);
        startActivity(Ejercicio2);
    }

    public void IrEjercicio3(View view){
        Intent Ejercicio3 = new Intent(MainActivity.this,Ejercicio3Activity.class);
        startActivity(Ejercicio3);
    }

    public void IrEjercicio4(View view){
        Intent Ejercicio4 = new Intent(MainActivity.this,Ejercicio4Activity.class);
        startActivity(Ejercicio4);
    }

    public void IrEjercicio5(View view){
        Intent Ejercicio5 = new Intent(MainActivity.this,Ejercicio5Activity.class);
        startActivity(Ejercicio5);
    }

    public void IrEjercicio6(View view){
        Intent Ejercicio6 = new Intent(MainActivity.this,Ejercicio6Activity.class);
        startActivity(Ejercicio6);
    }

    public void IrEjercicio7(View view){
        Intent Ejercicio7 = new Intent(MainActivity.this,Ejercicio7Activity.class);
        startActivity(Ejercicio7);
    }

    public void IrEjercicio8(View view){
        Intent Ejercicio8 = new Intent(MainActivity.this,Ejercicio8Activity.class);
        startActivity(Ejercicio8);
    }
}