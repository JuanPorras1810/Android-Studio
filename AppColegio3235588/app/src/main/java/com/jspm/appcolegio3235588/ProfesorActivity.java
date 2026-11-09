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
import com.jspm.appcolegio3235588.modelo.ClsProfesor;

import java.util.ArrayList;

public class ProfesorActivity extends AppCompatActivity {

    // Declaracion de variables
    private EditText TxtDocumento;
    private EditText TxtNombre;
    private EditText TxtApellido;
    private EditText TxtDireccion;
    private EditText TxtTelefono;
    private EditText TxtEmail;
    private EditText TxtTitulo;
    private DatabaseReference RefProfesores;

    // Manejo de listas
    private ListView DatosProfesor;
    private ArrayList<ClsProfesor> Datos;
    private ArrayAdapter<String> Adaptador;
    private ArrayList<String> Textos;
    private ClsProfesor Seleccionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_profesor);

        // Generar menu
        MaterialToolbar toolbar = findViewById(R.id.ToolbarProfesor);
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
        TxtDocumento = findViewById(R.id.TxtDocProf);
        TxtNombre = findViewById(R.id.TxtNomProf);
        TxtApellido = findViewById(R.id.TxtApeProf);
        TxtDireccion = findViewById(R.id.TxtDirProf);
        TxtTelefono = findViewById(R.id.TxtTelProf);
        TxtEmail = findViewById(R.id.TxtEmaProf);
        TxtTitulo = findViewById(R.id.TxtTituProf);
        DatosProfesor = findViewById(R.id.DatosProfesor);

        // Firebase
        RefProfesores = FirebaseDatabase.getInstance().getReference("Profesor");

        Datos = new ArrayList<>();
        Textos = new ArrayList<>();

        Listar();
        DatosProfesor.setOnItemClickListener((parent, view, position, id) -> {
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
        } else if (TxtEmail.getText().toString().isEmpty()) {
            TxtEmail.setError("Ingrese email");
            return false;
        }else if (TxtTitulo.getText().toString().isEmpty()) {
            TxtTitulo.setError("Ingrese titulo");
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
        TxtEmail.setText("");
        TxtTitulo.setText("");
        Seleccionado = null;
    }

    private void Guardar() {
        if (!Validar()) return;

        String Id = RefProfesores.push().getKey();

        ClsProfesor e = new ClsProfesor(
                Id,
                TxtDocumento.getText().toString().trim(),
                TxtNombre.getText().toString().trim(),
                TxtApellido.getText().toString().trim(),
                TxtDireccion.getText().toString().trim(),
                TxtTelefono.getText().toString().trim(),
                TxtEmail.getText().toString().trim(),
                TxtTitulo.getText().toString().trim()
        );
        e.setIdProfesor(Id);

        RefProfesores.child(Id).setValue(e)
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, "Profesor guardado",
                            Toast.LENGTH_SHORT).show();
                    Limpiar();
                })
                .addOnFailureListener(error -> {
                    Toast.makeText(this, "Error al guardar",
                            Toast.LENGTH_SHORT).show();
                });
    }

    private void Listar(){
        RefProfesores.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Datos.clear();
                        Textos.clear();

                        for (DataSnapshot item : snapshot.getChildren()){
                            ClsProfesor e = item.getValue(ClsProfesor.class);
                            if (e != null){
                                Datos.add(e);
                                Textos.add(e.getDocumentoProfesor() + " - " + e.getNombreProfesor() + " - " + e.getApellidoProfesor() + " - " + e.getDireccionProfesor() + " - " + e.getTelefonoProfesor() + " - " + e.getEmailProfesor() + " - " + e.getTituloProfesor());
                            }
                        }

                        Adaptador = new ArrayAdapter<>(ProfesorActivity.this, android.R.layout.simple_list_item_1,Textos);
                        DatosProfesor.setAdapter(Adaptador);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ProfesorActivity.this,
                                "Error: " + error.getMessage(),
                                Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }

    private void Seleccionar(int Position){
        Seleccionado = Datos.get(Position);

        TxtDocumento.setText(Seleccionado.getDocumentoProfesor());
        TxtNombre.setText(Seleccionado.getNombreProfesor());
        TxtApellido.setText(Seleccionado.getApellidoProfesor());
        TxtDireccion.setText(Seleccionado.getDireccionProfesor());
        TxtTelefono.setText(Seleccionado.getTelefonoProfesor());
        TxtEmail.setText(Seleccionado.getEmailProfesor());
        TxtTitulo.setText(Seleccionado.getTituloProfesor());

        Toast.makeText(this, "Profesor seleccionado",
                Toast.LENGTH_SHORT).show();
    }

    private void Actualizar(){
        if (Seleccionado == null){
            Toast.makeText(this, "Seleccione un profesor",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Validar()) return;

        ClsProfesor e = new ClsProfesor(
                Seleccionado.getIdProfesor(),
                TxtDocumento.getText().toString().trim(),
                TxtNombre.getText().toString().trim(),
                TxtApellido.getText().toString().trim(),
                TxtDireccion.getText().toString().trim(),
                TxtTelefono.getText().toString().trim(),
                TxtEmail.getText().toString().trim(),
                TxtTitulo.getText().toString().trim()
        );

        RefProfesores.child(Seleccionado.getIdProfesor()).setValue(e)
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, "Profesor actualizado",
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
            Toast.makeText(this, "Seleccione un profesor",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        RefProfesores.child(Seleccionado.getIdProfesor()).removeValue().addOnSuccessListener(v -> {
            Toast.makeText(this, "Profesor eliminado",
                    Toast.LENGTH_SHORT).show();
            Limpiar();
            Seleccionado = null;
        }).addOnFailureListener(error -> {
            Toast.makeText(this, "No fue posible eliminar",
                    Toast.LENGTH_SHORT).show();
        });
    }
}