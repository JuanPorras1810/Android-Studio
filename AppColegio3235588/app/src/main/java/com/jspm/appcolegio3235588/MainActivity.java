package com.jspm.appcolegio3235588;

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

    // Metodos

    public void IrEstudiante(View view){
        Intent Estudiante = new Intent(this, EstudianteActivity.class);
        startActivity(Estudiante);
    }

    public void IrProfesor(View view){
        Intent Profesor = new Intent(this, ProfesorActivity.class);
        startActivity(Profesor);
    }

    public void IrMateria(View view){
        Intent Materia = new Intent(this, MateriaActivity.class);
        startActivity(Materia);
    }
    public void IrAcudiente(View view){
        Intent Acudiente = new Intent(this, AcudienteActivity.class);
        startActivity(Acudiente);
    }


}