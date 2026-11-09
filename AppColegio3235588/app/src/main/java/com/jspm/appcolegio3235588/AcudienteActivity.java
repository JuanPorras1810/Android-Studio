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
import com.jspm.appcolegio3235588.modelo.ClsAcudiente;

import java.util.ArrayList;

public class AcudienteActivity extends AppCompatActivity {
    // Declaracion de variables
    private EditText TxtDocumento;
    private EditText TxtNombre;
    private EditText TxtApellido;
    private EditText TxtDireccion;
    private EditText TxtTelefono;
    private EditText TxtEmail;
    private DatabaseReference RefAcudiente;

    // Manejo de listas
    private ListView DatosAcudiente;
    private ArrayList<ClsAcudiente> Datos;
    private ArrayAdapter<String> Adaptador;
    private ArrayList<String> Textos;
    private ClsAcudiente Seleccionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_acudiente);

        // Generar menu
        MaterialToolbar Toolbar = findViewById(R.id.ToolbarAcudiente);
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
        TxtDocumento = findViewById(R.id.TxtDocAcu);
        TxtNombre = findViewById(R.id.TxtNomAcu);
        TxtApellido = findViewById(R.id.TxtApeAcu);
        TxtDireccion = findViewById(R.id.TxtDirAcu);
        TxtTelefono = findViewById(R.id.TxtTelAcu);
        TxtEmail= findViewById(R.id.TxtEmaAcu);
        DatosAcudiente = findViewById(R.id.DatosAcudiente);

        // Firebase
        RefAcudiente = FirebaseDatabase.getInstance().getReference("Acudiente");

        Datos = new ArrayList<>();
        Textos = new ArrayList<>();

        Listar();

        DatosAcudiente.setOnItemClickListener((parent, view, position, id) -> {
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
        Seleccionado = null;
    }

    private void Guardar(){
        if (!Validar()) return;

        String Id = RefAcudiente.push().getKey();

        ClsAcudiente a = new ClsAcudiente(
                Id,
                TxtDocumento.getText().toString().trim(),
                TxtNombre.getText().toString().trim(),
                TxtApellido.getText().toString().trim(),
                TxtDireccion.getText().toString().trim(),
                TxtTelefono.getText().toString().trim(),
                TxtEmail.getText().toString().trim()
        );
        a.setIdAcudiente(Id);

        RefAcudiente.child(Id).setValue(a).addOnSuccessListener(v -> {
            Toast.makeText(this, "Acudiente guardado",
                    Toast.LENGTH_SHORT).show();
            Limpiar();
        }).addOnFailureListener(error ->{
            Toast.makeText(this, "Error al guardar",
                    Toast.LENGTH_SHORT).show();
        });

    }

    private void Listar(){
        RefAcudiente.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Datos.clear();
                        Textos.clear();

                        for (DataSnapshot Item: snapshot.getChildren()){
                            ClsAcudiente a = Item.getValue(ClsAcudiente.class);

                            if (a != null) {
                                Datos.add(a);
                                Textos.add(a.getDocumentoAcudiente() + " - " + a.getNombreAcudiente() + " - " + a.getApellidoAcudiente() + " - " + a.getDireccionAcudiente() + " - " + a.getTelefonoAcudiente() + " - " + a.getEmailAcudiente());
                            }
                        }

                        Adaptador = new ArrayAdapter<>(AcudienteActivity.this,
                                android.R.layout.simple_list_item_1, Textos);
                        DatosAcudiente.setAdapter(Adaptador);
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AcudienteActivity.this,
                                "Error: " + error.getMessage(),
                                Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }

    private void Seleccionar(int position){
        Seleccionado = Datos.get(position);

        TxtDocumento.setText(Seleccionado.getDocumentoAcudiente());
        TxtNombre.setText(Seleccionado.getNombreAcudiente());
        TxtApellido.setText(Seleccionado.getApellidoAcudiente());
        TxtDireccion.setText(Seleccionado.getDireccionAcudiente());
        TxtTelefono.setText(Seleccionado.getTelefonoAcudiente());
        TxtEmail.setText(Seleccionado.getEmailAcudiente());

        Toast.makeText(this, "Acudiente seleccionado",
                Toast.LENGTH_SHORT).show();
    }

   private void Actualizar(){
        if (Seleccionado == null){
            Toast.makeText(this, "Seleccione un acudiente",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Validar()) return;

        ClsAcudiente a = new ClsAcudiente(
                Seleccionado.getIdAcudiente(),
                TxtDocumento.getText().toString().trim(),
                TxtNombre.getText().toString().trim(),
                TxtApellido.getText().toString().trim(),
                TxtDireccion.getText().toString().trim(),
                TxtTelefono.getText().toString().trim(),
                TxtEmail.getText().toString().trim()
        );

        RefAcudiente.child(Seleccionado.getIdAcudiente()).setValue(a)
                .addOnSuccessListener(v -> {
                        Toast.makeText(this, "Acudiente actualizado",
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
            Toast.makeText(this, "Seleccione un acudiente",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        RefAcudiente.child(Seleccionado.getIdAcudiente()).removeValue()
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, "Acudiente eliminado",
                            Toast.LENGTH_SHORT).show();
                    Limpiar();
                    Seleccionado = null;
                }).addOnFailureListener(error -> {
                    Toast.makeText(this, "No fue posible eliminar",
                            Toast.LENGTH_SHORT).show();
                });
    }
}