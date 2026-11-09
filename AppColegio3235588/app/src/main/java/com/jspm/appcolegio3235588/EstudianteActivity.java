package com.jspm.appcolegio3235588;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jspm.appcolegio3235588.modelo.ClsEstudiante;

import java.util.ArrayList;

public class EstudianteActivity extends AppCompatActivity {

    // Declaracion de variables
    private EditText TxtDocumento;
    private EditText TxtNombre;
    private EditText TxtApellido;
    private EditText TxtDireccion;
    private EditText TxtTelefono;
    private DatabaseReference RefEstudiantes;

    // Manejo de la lista
    private ListView DatosEstudiante;
    private ArrayList<ClsEstudiante> Datos;
    private ArrayAdapter<String> Adaptador;
    private ArrayList<String> Textos;
    private ClsEstudiante Seleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_estudiante);

        // Generar menu
        MaterialToolbar toolbar = findViewById(R.id.ToolbarEstudiante);
        toolbar.inflateMenu(R.menu.menu);

        // Evaluar opcion
        toolbar.setOnMenuItemClickListener(item -> {
            int opcion = item.getItemId();

            if (opcion == R.id.MnGuardar){
                Guardar();
            } else if (opcion == R.id.MnActualizar) {
                Actualizar();
            } else if (opcion == R.id.MnEliminar) {
                Eliminar();
            } else if (opcion == R.id.MnRegresar) {
                finish();
            }
            return true;
        });

        // Enlace con el activity
        TxtDocumento = findViewById(R.id.TxtDocEstu);
        TxtNombre = findViewById(R.id.TxtNomEstu);
        TxtApellido = findViewById(R.id.TxtApeEstu);
        TxtDireccion = findViewById(R.id.TxtDirEstu);
        TxtTelefono = findViewById(R.id.TxtTelEstu);
        DatosEstudiante = findViewById(R.id.DatosEstudiante);

        // Firebase
        RefEstudiantes = FirebaseDatabase.getInstance().getReference("Estudiante");

        Datos = new ArrayList<>();
        Textos = new ArrayList<>();
        Listar();
        DatosEstudiante.setOnItemClickListener((parent, view, position, id) -> {
            Seleccionar(position);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // Metodos

    private boolean Validar() {
        if (TxtDocumento.getText().toString().isEmpty()) {
            TxtDocumento.setError("Ingrese documento");
            return false;
        } else if (TxtNombre.getText().toString().isEmpty()) {
            TxtNombre.setError("Ingrese nombre");
            return false;
        } else if (TxtApellido.getText().toString().isEmpty()) {
            TxtApellido.setError("Ingrese apellido");
            return false;
        } else if (TxtDireccion.getText().toString().isEmpty()) {
            TxtDireccion.setError("Ingrese direccion");
            return false;
        } else if (TxtTelefono.getText().toString().isEmpty()) {
            TxtTelefono.setError("Ingrese telefono");
            return false;
        }
        return true;
    }

    private void Limpiar(){
        TxtDocumento.setText("");
        TxtNombre.setText("");
        TxtApellido.setText("");
        TxtDireccion.setText("");
        TxtTelefono.setText("");
        Seleccionado = null;
    }

    private void Guardar() {
        if (!Validar()) return;

        String Id = RefEstudiantes.push().getKey();

        ClsEstudiante e = new ClsEstudiante(
                Id,
                TxtDocumento.getText().toString().trim(),
                TxtNombre.getText().toString().trim(),
                TxtApellido.getText().toString().trim(),
                TxtDireccion.getText().toString().trim(),
                TxtTelefono.getText().toString().trim()
        );
        e.setIdEstudiante(Id);

        RefEstudiantes.child(Id).setValue(e)
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, "Estudiante guardado",
                            Toast.LENGTH_SHORT).show();
                    Limpiar();
                })
                .addOnFailureListener(error -> {
                    Toast.makeText(this, "Error al guardar",
                            Toast.LENGTH_SHORT).show();
                });
    }

    private void Listar(){
        RefEstudiantes.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Datos.clear();
                        Textos.clear();

                        for (DataSnapshot item : snapshot.getChildren()){
                                ClsEstudiante e = item.getValue(ClsEstudiante.class);
                                if (e != null){
                                    Datos.add(e);
                                    Textos.add(e.getDocumentoEstudiante() + " - " + e.getNombreEstudiante() + " - " + e.getApellidoEstudiante() + " - " + e.getDireccionEstudiante() + " - " + e.getTelefonoEstudiante() );
                                }
                        }

                        Adaptador = new ArrayAdapter<>(EstudianteActivity.this, android.R.layout.simple_list_item_1,Textos);
                        DatosEstudiante.setAdapter(Adaptador);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EstudianteActivity.this,
                                "Error: " + error.getMessage(),
                                Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }

    private void Seleccionar(int Position){
        Seleccionado = Datos.get(Position);

        TxtDocumento.setText(Seleccionado.getDocumentoEstudiante());
        TxtNombre.setText(Seleccionado.getNombreEstudiante());
        TxtApellido.setText(Seleccionado.getApellidoEstudiante());
        TxtDireccion.setText(Seleccionado.getDireccionEstudiante());
        TxtTelefono.setText(Seleccionado.getTelefonoEstudiante());

        Toast.makeText(this, "Estudiante seleccionado",
                Toast.LENGTH_SHORT).show();
    }

    private void Actualizar(){
        if (Seleccionado == null){
            Toast.makeText(this, "Seleccione un estudiante",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Validar()) return;

        ClsEstudiante e = new ClsEstudiante(
                Seleccionado.getIdEstudiante(),
                TxtDocumento.getText().toString().trim(),
                TxtNombre.getText().toString().trim(),
                TxtApellido.getText().toString().trim(),
                TxtDireccion.getText().toString().trim(),
                TxtTelefono.getText().toString().trim()
        );

        RefEstudiantes.child(Seleccionado.getIdEstudiante()).setValue(e)
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, "Estudiante actualizado",
                            Toast.LENGTH_SHORT).show();
                    Limpiar();
                    Seleccionado = null;
                }).addOnFailureListener(error -> {
                    Toast.makeText(this, "No fue posible actulizar",
                            Toast.LENGTH_SHORT).show();
                });
    }

    private void Eliminar() {
        if (Seleccionado == null) {
            Toast.makeText(this, "Seleccione un estudiante",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        RefEstudiantes.child(Seleccionado.getIdEstudiante()).removeValue().addOnSuccessListener(v -> {
            Toast.makeText(this, "Estudiante eliminado",
                    Toast.LENGTH_SHORT).show();
            Limpiar();
            Seleccionado = null;
        }).addOnFailureListener(error -> {
            Toast.makeText(this, "No fue posible eliminar",
                    Toast.LENGTH_SHORT).show();
        });
    }


}