package com.jspm.appcolegio3235588;

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
import com.jspm.appcolegio3235588.modelo.ClsAcudiente;
import com.jspm.appcolegio3235588.modelo.ClsMateria;

import java.util.ArrayList;

public class MateriaActivity extends AppCompatActivity {
    // Declaracion de variables
    private EditText TxtDocumento;
    private EditText TxtNombre;
    private Spinner SpGrado;
    private DatabaseReference RefMateria;

    // Manejo de listas
    private ListView DatosMateria;
    private ArrayList<ClsMateria> Datos;
    private ArrayAdapter<String> Adaptador;
    private ArrayList<String> Textos;
    private ClsMateria Seleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_materia);

        // Generar menu
        MaterialToolbar Toolbar = findViewById(R.id.ToolbarMateria);
        Toolbar.inflateMenu(R.menu.menu);

        // Evaluar opcion
        Toolbar.setOnMenuItemClickListener(Item -> {
            int Opcion = Item.getItemId();

            if (Opcion == R.id.MnGuardar){
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
        TxtDocumento = findViewById(R.id.TxtDocMat);
        TxtNombre = findViewById(R.id.TxtNomMat);
        SpGrado = findViewById(R.id.SpGradoMat);
        DatosMateria = findViewById(R.id.DatosMateria);

        // Firebase
        RefMateria = FirebaseDatabase.getInstance().getReference("Materia");

        Datos = new ArrayList<>();
        Textos = new ArrayList<>();

        Listar();

        DatosMateria.setOnItemClickListener((parent, view, position, id) -> {
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
        } else if (SpGrado.getSelectedItemPosition() <= 0 || SpGrado.getSelectedItemPosition() > 5) {
            Toast.makeText(this,
                    "Selecciona un grado",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void Limpiar(){
        TxtDocumento.setText("");
        TxtNombre.setText("");
        SpGrado.setSelection(0);
        Seleccionado = null;
    }

    private void Guardar(){
        if (!Validar()) return;

        String Id = RefMateria.push().getKey();

        ClsMateria m = new ClsMateria(
                Id,
                TxtDocumento.getText().toString().trim(),
                TxtNombre.getText().toString().trim(),
                SpGrado.getSelectedItem().toString()
        );
        m.setIdMateria(Id);

        RefMateria.child(Id).setValue(m).addOnSuccessListener(v -> {
            Toast.makeText(this, "Materia guardada",
                    Toast.LENGTH_SHORT).show();
            Limpiar();
        }).addOnFailureListener(error ->{
            Toast.makeText(this, "Error al guardar",
                    Toast.LENGTH_SHORT).show();
        });

    }

    private void Listar(){
        RefMateria.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Datos.clear();
                        Textos.clear();

                        for (DataSnapshot Item: snapshot.getChildren()){
                            ClsMateria a = Item.getValue(ClsMateria.class);

                            if (a != null) {
                                Datos.add(a);
                                Textos.add(a.getDocumentoMateria() + " - " + a.getNombreMateria() + " - " + a.getGradoMateria() );
                            }
                        }

                        Adaptador = new ArrayAdapter<>(MateriaActivity.this,
                                android.R.layout.simple_list_item_1, Textos);
                        DatosMateria.setAdapter(Adaptador);
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MateriaActivity.this,
                                "Error: " + error.getMessage(),
                                Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }

    private void Seleccionar(int position) {
        Seleccionado = Datos.get(position);

        TxtDocumento.setText(Seleccionado.getDocumentoMateria());
        TxtNombre.setText(Seleccionado.getNombreMateria());
        for (int i = 0; i < SpGrado.getCount(); i++) {
            if (SpGrado.getItemAtPosition(i).toString().equals(Seleccionado.getGradoMateria())) {
                SpGrado.setSelection(i);
                break;
            }
            Toast.makeText(this, "Materia seleccionada",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void Actualizar(){
        if (Seleccionado == null){
            Toast.makeText(this, "Seleccione una materia",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Validar()) return;

        ClsMateria m = new ClsMateria(
                Seleccionado.getIdMateria(),
                TxtDocumento.getText().toString().trim(),
                TxtNombre.getText().toString().trim(),
                SpGrado.getSelectedItem().toString()
        );

        RefMateria.child(Seleccionado.getIdMateria()).setValue(m)
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, "Materia actualizada",
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
            Toast.makeText(this, "Seleccione una materia",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        RefMateria.child(Seleccionado.getIdMateria()).removeValue()
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, "Materia eliminada",
                            Toast.LENGTH_SHORT).show();
                    Limpiar();
                    Seleccionado = null;
                }).addOnFailureListener(error -> {
                    Toast.makeText(this, "No fue posible eliminar",
                            Toast.LENGTH_SHORT).show();
                });
    }
}