package com.jspm.primerapp3235588;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ejercicio3Activity extends AppCompatActivity {

    // Declaracion de variables
    private EditText TxtNombre;
    private EditText TxtSalarioXhora;
    private EditText TxtHoras;
    private EditText TxtSalarioNeto;

    public String Nombre;
    public double SalarioXhora;
    public int Horas;
    public double SalarioNeto;
    public int HorasExtras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio3);

        // Enlace con el activity
        TxtNombre = findViewById(R.id.TxtNomEjer3);
        TxtSalarioXhora = findViewById(R.id.TxtSalXhoraEjer3);
        TxtHoras = findViewById(R.id.TxtHorasEjer3);
        TxtSalarioNeto = findViewById(R.id.TxtSalNetoEjer3);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metodos
    public void Capturar(){
        if (TxtNombre.getText().toString().isEmpty()){
            TxtNombre.setError("Ingrese el nombre del empleado");
        } else if (TxtSalarioXhora.getText().toString().isEmpty()) {
            TxtSalarioXhora.setError("Ingrese el salario x hora");
        } else if (TxtHoras.getText().toString().isEmpty()) {
            TxtHoras.setError("Ingrese las horas trabajadas");
        }else {
            Nombre = TxtNombre.getText().toString();
            SalarioXhora = Double.parseDouble(TxtSalarioXhora.getText().toString());
            Horas = Integer.parseInt(TxtHoras.getText().toString());
        }

    }

    public void CalcularSalario(View view){
        Capturar();

        if (Horas>48){
            HorasExtras = Horas - 48;
            SalarioNeto = (48 * SalarioXhora) + (HorasExtras * SalarioXhora * 1.25);
        }else {
            SalarioNeto = SalarioXhora * Horas;
        }

        TxtSalarioNeto.setText(String.format("$%s", SalarioNeto));
    }

    public void Regresar(View view){finish();}
}