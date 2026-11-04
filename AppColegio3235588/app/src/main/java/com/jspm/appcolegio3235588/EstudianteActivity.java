package com.jspm.appcolegio3235588;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class EstudianteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_estudiante);

        // Generar menu
        MaterialToolbar toolbar = findViewById(R.id.ToolbarEstudiante);
        toolbar.inflateMenu(R.menu.menu);

        // Evaluar opcion
        toolbar.setOnMenuItemClickListener(item -> {
            int opcion = item.getItemId();

            if (opcion == R.id.MnGuardar){
                Toast.makeText(this,"Guardar estudiante",
                        Toast.LENGTH_SHORT).show();
            } else if (opcion == R.id.MnActualizar) {
                Toast.makeText(this,"Actualizar estudiante",
                        Toast.LENGTH_SHORT).show();
            } else if (opcion == R.id.MnEliminar) {
                Toast.makeText(this,"Eliminar estudiante",
                        Toast.LENGTH_SHORT).show();
            } else if (opcion == R.id.MnRegresar) {
                finish();
            }

            return true;
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}