package com.jspm.primerapp3235588;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ejercicio2Activity extends AppCompatActivity {

    //Declaracion de variables

    private EditText TxtNombreEmpleado;
    private EditText TxtSalarioXhora;
    private EditText TxtHoras;
    private TextView TxtEmpleado;
    private TextView TxtSalarioBruto;
    private TextView TxtRetencion;
    private TextView TxtSalarioNeto;

    public String NombreEmpleado;
    public double SalarioXhora;
    public int Horas;
    public double SalarioBruto;
    public double Retencion;
    public double SalarioNeto;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio2);

        //Enlace con el activity

        TxtNombreEmpleado = findViewById(R.id.TxtNomEjer2);
        TxtSalarioXhora = findViewById(R.id.TxtSalXhoraEjer2);
        TxtHoras = findViewById(R.id.TxtHoraEjer2);
        TxtEmpleado = findViewById(R.id.TxtEmpEjer2);
        TxtSalarioBruto = findViewById(R.id.TxtSalBrutEjer2);
        TxtRetencion = findViewById(R.id.TxtRetenEjer2);
        TxtSalarioNeto = findViewById(R.id.TxtSalNetoEjer2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Metodos
    public void Capturar(){
        if (TxtNombreEmpleado.getText().toString().isEmpty()) {
            TxtNombreEmpleado.setError("Ingrese el nombre del empleado");
        } else if (TxtSalarioXhora.getText().toString().isEmpty()) {
            TxtSalarioXhora.setError("Ingrese el salario x hora");
        } else if (TxtHoras.getText().toString().isEmpty()) {
            TxtHoras.setError("Ingrese las horas trabajadas");
        }else{
            NombreEmpleado = TxtNombreEmpleado.getText().toString();
            SalarioXhora = Double.parseDouble(TxtSalarioXhora.getText().toString());
            Horas = Integer.parseInt(TxtHoras.getText().toString());
        }
    }


    public void DetallePago(View view){
        Capturar();
        TxtEmpleado.setText(NombreEmpleado);
        SalarioBruto = SalarioXhora * Horas;
        TxtSalarioBruto.setText(String.format("$%s", SalarioBruto));

        if (SalarioBruto>300000){
            Retencion = SalarioBruto * 0.08;
            SalarioNeto = SalarioBruto - Retencion;
            TxtRetencion.setText(String.format("$%s", Retencion));
            TxtSalarioNeto.setText(String.format("$%s", SalarioNeto));
        }else if (SalarioBruto>250501){
            Retencion = SalarioBruto * 0.05;
            SalarioNeto = SalarioBruto - Retencion;
            TxtRetencion.setText(String.format("$%s", Retencion));
            TxtSalarioNeto.setText(String.format("$%s", SalarioNeto));
        }else {
            Retencion = 0;
            SalarioNeto = SalarioBruto;
            TxtRetencion.setText(String.format("$%s", Retencion));
            TxtSalarioNeto.setText(String.format("$%s", SalarioNeto));
        }

    }

    public void Regresar(View view){finish();}
}