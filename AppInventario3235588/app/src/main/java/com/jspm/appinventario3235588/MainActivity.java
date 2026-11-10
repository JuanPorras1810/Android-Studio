package com.jspm.appinventario3235588;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metodos
    public void IrAmbiente(View view){
        Intent Ambiente = new Intent(this, AmbienteActivity.class);
        startActivity(Ambiente);
    }

    public void IrElemento(View view){
        Intent Elemento = new Intent(this, ElementoActivity.class);
        startActivity(Elemento);
    }

    public void IrInstructor(View view){
        Intent Instructor = new Intent(this, InstructorActivity.class);
        startActivity(Instructor);
    }

    public void IrNovedades(View view){
        Intent Novedad = new Intent(this, NovedadesActivity.class);
        startActivity(Novedad);
    }
}