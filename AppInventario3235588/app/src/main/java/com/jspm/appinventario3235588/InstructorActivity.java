package com.jspm.appinventario3235588;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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
import com.jspm.appinventario3235588.modelo.ClsInstructor;

import java.util.ArrayList;

public class InstructorActivity extends AppCompatActivity {

    // Declaracion de variables
    private EditText TxtDocumento;
    private EditText TxtNombre;
    private EditText TxtApellidos;
    private EditText TxtEmail;
    private EditText TxtCelular;
    private Spinner SpRed;
    private DatabaseReference RefInstructor;

    // Manejo de listas
    private ListView DatosInstructor;
    private ArrayList<ClsInstructor> Datos;
    private ArrayAdapter<String> Adaptador;
    private ArrayList<String> Textos;
    private ClsInstructor Seleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_instructor);

        // Generar menu
        MaterialToolbar Toolbar = findViewById(R.id.ToolbarInstructor);
        Toolbar.inflateMenu(R.menu.menu);

        // Evaluar opcion
        Toolbar.setOnMenuItemClickListener(Item -> {
            int Opcion = Item.getItemId();

            if (Opcion == R.id.MnGuardar) {
                Guardar();
            } else if (Opcion == R.id.MnActualizar) {
                Actualizar();
            } else if (Opcion == R.id.MnEliminar) {
                Eliminar();
            } else if (Opcion == R.id.MnRegresar) {
                finish();
            }
            return true;
        });

        // Enlace con el activity
        TxtDocumento = findViewById(R.id.TxtDocIns);
        TxtNombre = findViewById(R.id.TxtNomIns);
        TxtApellidos = findViewById(R.id.TxtApeIns);
        TxtEmail = findViewById(R.id.TxtEmaIns);
        TxtCelular = findViewById(R.id.TxtCelIns);
        SpRed = findViewById(R.id.SpRedIns);
        DatosInstructor = findViewById(R.id.DatosInstructor);

        // Firebase
        RefInstructor = FirebaseDatabase.getInstance().getReference("Instructor");

        Datos = new ArrayList<>();
        Textos = new ArrayList<>();

        Listar();

        DatosInstructor.setOnItemClickListener((parent, view, position, id) -> {
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
        } else if (TxtApellidos.getText().toString().isEmpty()) {
            TxtApellidos.setError("Ingrese apellidos");
            return false;
        } else if (TxtEmail.getText().toString().isEmpty()) {
            TxtEmail.setError("Ingrese email");
            return false;
        } else if (TxtCelular.getText().toString().isEmpty()) {
            TxtCelular.setError("Ingrese celular");
            return false;
        } else if (SpRed.getSelectedItemPosition() <= 0 || SpRed.getSelectedItemPosition() > 9) {
            Toast.makeText(this, "Seleccione una red",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void Limpiar() {
        TxtDocumento.setText("");
        TxtNombre.setText("");
        TxtApellidos.setText("");
        TxtEmail.setText("");
        TxtCelular.setText("");
        SpRed.setSelection(0);
        Seleccionado = null;
    }

    private void Guardar() {
        if (!Validar()) return;

        String Id = RefInstructor.push().getKey();

        ClsInstructor ins = new ClsInstructor(
                Id,
                TxtDocumento.getText().toString().trim(),
                TxtNombre.getText().toString().trim(),
                TxtApellidos.getText().toString().trim(),
                TxtEmail.getText().toString().trim(),
                TxtCelular.getText().toString().trim(),
                SpRed.getSelectedItem().toString()
        );
        ins.setIdInstructor(Id);

        RefInstructor.child(Id).setValue(ins).addOnSuccessListener(v -> {
            Toast.makeText(this, "Instructor guardado",
                    Toast.LENGTH_SHORT).show();
            Limpiar();
        }).addOnFailureListener(error -> {
            Toast.makeText(this, "Error al guardar",
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void Listar() {
        RefInstructor.addValueEventListener(new ValueEventListener()  {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Datos.clear();
                Textos.clear();

                for (DataSnapshot Item : snapshot.getChildren()) {
                    ClsInstructor ins = Item.getValue(ClsInstructor.class);

                    if (ins != null) {
                        Datos.add(ins);
                        Textos.add(ins.getDocumentoInstructor() + " - " + ins.getNombreInstructor() + " " + ins.getApellidosInstructor() + " - " + ins.getEmailInstructor());
                    }
                }

                Adaptador = new ArrayAdapter<>(InstructorActivity.this,
                        android.R.layout.simple_list_item_1, Textos);
                DatosInstructor.setAdapter(Adaptador);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(InstructorActivity.this, "Error: " + error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Seleccionar(int position) {
        Seleccionado = Datos.get(position);

        TxtDocumento.setText(Seleccionado.getDocumentoInstructor());
        TxtNombre.setText(Seleccionado.getNombreInstructor());
        TxtApellidos.setText(Seleccionado.getApellidosInstructor());
        TxtEmail.setText(Seleccionado.getEmailInstructor());
        TxtCelular.setText(Seleccionado.getCelularInstructor());

        for (int i = 0; i < SpRed.getCount(); i++) {
            if (SpRed.getItemAtPosition(i).toString().equals(Seleccionado.getRedInstructor())) {
                SpRed.setSelection(i);
                break;
            }
        }

        Toast.makeText(this, "Instructor seleccionado",
                Toast.LENGTH_SHORT).show();
    }

    private void Actualizar() {
        if (Seleccionado == null) {
            Toast.makeText(this, "Seleccione un instructor",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Validar()) return;

        ClsInstructor ins = new ClsInstructor(
                Seleccionado.getIdInstructor(),
                TxtDocumento.getText().toString().trim(),
                TxtNombre.getText().toString().trim(),
                TxtApellidos.getText().toString().trim(),
                TxtEmail.getText().toString().trim(),
                TxtCelular.getText().toString().trim(),
                SpRed.getSelectedItem().toString()
        );

        RefInstructor.child(Seleccionado.getIdInstructor()).setValue(ins)
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, "Instructor actualizado",
                            Toast.LENGTH_SHORT).show();
                    Limpiar();
                    Seleccionado = null;
                }).addOnFailureListener(error -> {
                    Toast.makeText(this, "No fue posible actualizar",
                            Toast.LENGTH_SHORT).show();
                });
    }

    private void Eliminar() {
        if (Seleccionado == null) {
            Toast.makeText(this, "Seleccione un instructor",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        RefInstructor.child(Seleccionado.getIdInstructor()).removeValue()
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, "Instructor eliminado",
                            Toast.LENGTH_SHORT).show();
                    Limpiar();
                    Seleccionado = null;
                }).addOnFailureListener(error -> {
                    Toast.makeText(this, "No fue posible eliminar",
                            Toast.LENGTH_SHORT).show();
                });
    }
}