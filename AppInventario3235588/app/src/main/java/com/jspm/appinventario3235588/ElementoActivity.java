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
import com.jspm.appinventario3235588.modelo.ClsAmbiente;
import com.jspm.appinventario3235588.modelo.ClsElemento;

import java.util.ArrayList;

public class ElementoActivity extends AppCompatActivity {
    // Declaracion de variables
    private EditText TxtCodigo;
    private Spinner SpAmbiente;
    private Spinner SpTipo;
    private Spinner SpDescripcion;
    private EditText TxtAtributos;
    private EditText TxtValor;
    private DatabaseReference RefElemento;
    private DatabaseReference RefAmbiente;

    // Manejo de listas
    private ListView DatosElemento;
    private ArrayList<ClsElemento> Datos;
    private ArrayAdapter<String> Adaptador;
    private ArrayList<String> Textos;
    private ClsElemento Seleccionado;

    // Lista para el Spinner
    private ArrayList<String> TextosAmbientes;
    private ArrayAdapter<String> AdaptadorAmbiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_elemento);

        // Generar menu
        MaterialToolbar Toolbar = findViewById(R.id.ToolbarElemento);
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
        TxtCodigo = findViewById(R.id.TxtCodEle);
        SpAmbiente = findViewById(R.id.SpAmbEle);
        SpTipo = findViewById(R.id.SpTipoEle);
        SpDescripcion = findViewById(R.id.SpDesEle);
        TxtAtributos = findViewById(R.id.TxtAtrEle);
        TxtValor = findViewById(R.id.TxtValEle);
        DatosElemento = findViewById(R.id.DatosElemento);

        // Firebase
        RefElemento = FirebaseDatabase.getInstance().getReference("Elemento");
        RefAmbiente = FirebaseDatabase.getInstance().getReference("Ambiente");

        Datos = new ArrayList<>();
        Textos = new ArrayList<>();
        TextosAmbientes = new ArrayList<>();

        CargarSpinnerAmbientes();
        Listar();

        DatosElemento.setOnItemClickListener((parent, view, position, id) -> {
            Seleccionar(position);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metodos
    private void CargarSpinnerAmbientes() {
        RefAmbiente.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TextosAmbientes.clear();
                TextosAmbientes.add("Seleccione...");

                for (DataSnapshot Item : snapshot.getChildren()) {
                    ClsAmbiente amb = Item.getValue(ClsAmbiente.class);
                    if (amb != null) {
                        TextosAmbientes.add(amb.getNumeroIdAmbiente());
                    }
                }

                AdaptadorAmbiente = new ArrayAdapter<>(ElementoActivity.this,
                        android.R.layout.simple_spinner_item, TextosAmbientes);

                AdaptadorAmbiente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                SpAmbiente.setAdapter(AdaptadorAmbiente);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ElementoActivity.this, "Error: " + error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean Validar() {
        if (TxtCodigo.getText().toString().isEmpty()) {
            TxtCodigo.setError("Ingrese codigo");
            return false;
        } else if (SpAmbiente.getSelectedItemPosition() <= 0) {
            Toast.makeText(this, "Seleccione un ambiente", Toast.LENGTH_SHORT).show();
            return false;
        } else if (SpTipo.getSelectedItemPosition() <= 0) {
            Toast.makeText(this, "Seleccione un tipo", Toast.LENGTH_SHORT).show();
            return false;
        } else if (SpDescripcion.getSelectedItemPosition() <= 0) {
            Toast.makeText(this, "Seleccione una descripcion", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TxtAtributos.getText().toString().isEmpty()) {
            TxtAtributos.setError("Ingrese atributos");
            return false;
        } else if (TxtValor.getText().toString().isEmpty()) {
            TxtValor.setError("Ingrese valor");
            return false;
        }
        return true;
    }

    private void Limpiar() {
        TxtCodigo.setText("");
        SpAmbiente.setSelection(0);
        SpTipo.setSelection(0);
        SpDescripcion.setSelection(0);
        TxtAtributos.setText("");
        TxtValor.setText("");
        Seleccionado = null;
    }

    private void Guardar() {
        if (!Validar()) return;

        String Id = RefElemento.push().getKey();

        ClsElemento e = new ClsElemento(
                Id,
                TxtCodigo.getText().toString().trim(),
                SpAmbiente.getSelectedItem().toString(),
                SpTipo.getSelectedItem().toString(),
                SpDescripcion.getSelectedItem().toString(),
                TxtAtributos.getText().toString().trim(),
                TxtValor.getText().toString().trim()
        );
        e.setIdElemento(Id);

        RefElemento.child(Id).setValue(e).addOnSuccessListener(v -> {
            Toast.makeText(this, "Elemento guardado",
                    Toast.LENGTH_SHORT).show();
            Limpiar();
        }).addOnFailureListener(error -> {
            Toast.makeText(this, "Error al guardar",
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void Listar() {
        RefElemento.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Datos.clear();
                Textos.clear();

                for (DataSnapshot Item : snapshot.getChildren()) {
                    ClsElemento e = Item.getValue(ClsElemento.class);

                    if (e != null) {
                        Datos.add(e);
                        Textos.add(e.getCodigoElemento() + " - " + e.getIdAmbienteElemento() + " - " + e.getTipoElemento() + " - " + e.getDescripcionElemento());
                    }
                }

                Adaptador = new ArrayAdapter<>(ElementoActivity.this,
                        android.R.layout.simple_list_item_1, Textos);
                DatosElemento.setAdapter(Adaptador);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ElementoActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Seleccionar(int position) {
        Seleccionado = Datos.get(position);

        TxtCodigo.setText(Seleccionado.getCodigoElemento());
        TxtAtributos.setText(Seleccionado.getAtributosElemento());
        TxtValor.setText(Seleccionado.getValorElemento());

        for (int i = 0; i < SpAmbiente.getCount(); i++) {
            if (SpAmbiente.getItemAtPosition(i).toString().equals(Seleccionado.getIdAmbienteElemento())) {
                SpAmbiente.setSelection(i);
                break;
            }
        }

        for (int i = 0; i < SpTipo.getCount(); i++) {
            if (SpTipo.getItemAtPosition(i).toString().equals(Seleccionado.getTipoElemento())) {
                SpTipo.setSelection(i);
                break;
            }
        }

        for (int i = 0; i < SpDescripcion.getCount(); i++) {
            if (SpDescripcion.getItemAtPosition(i).toString().equals(Seleccionado.getDescripcionElemento())) {
                SpDescripcion.setSelection(i);
                break;
            }
        }

        Toast.makeText(this, "Elemento seleccionado",
                Toast.LENGTH_SHORT).show();
    }

    private void Actualizar() {
        if (Seleccionado == null) {
            Toast.makeText(this, "Seleccione un elemento",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Validar()) return;

        ClsElemento e = new ClsElemento(
                Seleccionado.getIdElemento(),
                TxtCodigo.getText().toString().trim(),
                SpAmbiente.getSelectedItem().toString(),
                SpTipo.getSelectedItem().toString(),
                SpDescripcion.getSelectedItem().toString(),
                TxtAtributos.getText().toString().trim(),
                TxtValor.getText().toString().trim()
        );

        RefElemento.child(Seleccionado.getIdElemento()).setValue(e)
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, "Elemento actualizado",
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
            Toast.makeText(this, "Seleccione un elemento",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        RefElemento.child(Seleccionado.getIdElemento()).removeValue()
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, "Elemento eliminado",
                            Toast.LENGTH_SHORT).show();
                    Limpiar();
                    Seleccionado = null;
                }).addOnFailureListener(error -> {
                    Toast.makeText(this, "No fue posible eliminar",
                            Toast.LENGTH_SHORT).show();
                });
    }
}