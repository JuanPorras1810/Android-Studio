package com.jspm.appinventario3235588;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

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
import com.jspm.appinventario3235588.modelo.ClsAmbiente;

import java.util.ArrayList;

public class AmbienteActivity extends AppCompatActivity {
    // Declaracion de variables
    private EditText TxtId;
    private Spinner SpPiso;
    private EditText TxtDescripcion;
    private DatabaseReference RefAmbiente;

    // Manejo de listas
    private ListView DatosAmbiente;
    private ArrayList<ClsAmbiente> Datos;
    private ArrayAdapter<String> Adaptador;
    private ArrayList<String> Textos;
    private ClsAmbiente Seleccionado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_ambiente);

        // Generar menu
        MaterialToolbar Toolbar = findViewById(R.id.ToolbarAmbiente);
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
        TxtId = findViewById(R.id.TxtIdAmb);
        SpPiso = findViewById(R.id.SpPisoAmb);
        TxtDescripcion = findViewById(R.id.TxtDesAmb);
        DatosAmbiente = findViewById(R.id.DatosAmbiente);

        // Firebase
        RefAmbiente = FirebaseDatabase.getInstance().getReference("Ambiente");

        Datos = new ArrayList<>();
        Textos = new ArrayList<>();

        Listar();

        DatosAmbiente.setOnItemClickListener((parent, view, position, id) -> {
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
        if (TxtId.getText().toString().isEmpty()) {
            TxtId.setError("Ingrese id");
            return false;
        } else if (SpPiso.getSelectedItemPosition() <= 0 || SpPiso.getSelectedItemPosition() > 5) {
            Toast.makeText(this, "Selecciona un piso",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (TxtDescripcion.getText().toString().isEmpty()) {
            TxtDescripcion.setError("Ingrese descripcion");
            return false;
        }
        return true;
    }

    private void Limpiar() {
        TxtId.setText("");
        SpPiso.setSelection(0);
        TxtDescripcion.setText("");
        Seleccionado = null;
    }

    private void Guardar() {
        if (!Validar()) return;

        String Id = RefAmbiente.push().getKey();

        ClsAmbiente a = new ClsAmbiente(
                Id,
                TxtId.getText().toString().trim(),
                SpPiso.getSelectedItem().toString(),
                TxtDescripcion.getText().toString().trim()
        );

        RefAmbiente.child(Id).setValue(a).addOnSuccessListener(v -> {
            Toast.makeText(this, "Ambiente guardado",
                    Toast.LENGTH_SHORT).show();
            Limpiar();
        }).addOnFailureListener(error -> {
            Toast.makeText(this, "Error al guardar",
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void Listar() {
        RefAmbiente.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Datos.clear();
                        Textos.clear();

                        for (DataSnapshot Item : snapshot.getChildren()) {
                            ClsAmbiente a = Item.getValue(ClsAmbiente.class);

                            if (a != null) {
                                Datos.add(a);
                            Textos.add(a.getNumeroIdAmbiente() + " - " + a.getPisoAmbiente() + " - " + a.getDescripcionAmbiente());
                            }
                        }

                        Adaptador = new ArrayAdapter<>(AmbienteActivity.this,
                                android.R.layout.simple_list_item_1, Textos);
                        DatosAmbiente.setAdapter(Adaptador);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AmbienteActivity.this,
                                "Error: " + error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void Seleccionar(int position) {
        Seleccionado = Datos.get(position);

        TxtId.setText(Seleccionado.getNumeroIdAmbiente());
        TxtDescripcion.setText(Seleccionado.getDescripcionAmbiente());

        for (int i = 0; i < SpPiso.getCount(); i++) {
            if (SpPiso.getItemAtPosition(i).toString().equals(Seleccionado.getPisoAmbiente())) {
                SpPiso.setSelection(i);
                break;
            }
        }

        Toast.makeText(this, "Ambiente seleccionado",
                Toast.LENGTH_SHORT).show();
    }
    private void Actualizar() {
        if (Seleccionado == null) {
            Toast.makeText(this, "Seleccione un ambiente",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Validar()) return;

        ClsAmbiente a = new ClsAmbiente(
                Seleccionado.getIdAmbiente(),
                TxtId.getText().toString().trim(),
                SpPiso.getSelectedItem().toString(),
                TxtDescripcion.getText().toString().trim()
        );

        RefAmbiente.child(Seleccionado.getIdAmbiente()).setValue(a)
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, "Ambiente actualizado",
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
            Toast.makeText(this, "Seleccione un ambiente",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        RefAmbiente.child(Seleccionado.getIdAmbiente()).removeValue()
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, "Ambiente eliminado",
                            Toast.LENGTH_SHORT).show();
                    Limpiar();
                    Seleccionado = null;
                }).addOnFailureListener(error -> {
                    Toast.makeText(this, "No fue posible eliminar",
                            Toast.LENGTH_SHORT).show();
                });
    }
}