package com.jspm.appinventario3235588;

import android.app.DatePickerDialog;
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
import com.jspm.appinventario3235588.modelo.ClsInstructor;
import com.jspm.appinventario3235588.modelo.ClsNovedades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class NovedadesActivity extends AppCompatActivity {

    // Declaracion de variables
    private EditText TxtFecha;
    private Spinner SpTipo;
    private EditText TxtDescripcion;
    private Spinner SpAmbiente;
    private Spinner SpElemento;
    private Spinner SpInstructor;

    private DatabaseReference RefNovedad;
    private DatabaseReference RefAmbiente;
    private DatabaseReference RefElemento;
    private DatabaseReference RefInstructor;

    // Manejo de listas
    private ListView DatosNovedad;
    private ArrayList<ClsNovedades> Datos;
    private ArrayAdapter<String> Adaptador;
    private ArrayList<String> Textos;
    private ClsNovedades Seleccionado;

    // Listas para los Spinners
    private ArrayList<String> TextosAmbientes;
    private ArrayAdapter<String> AdaptadorAmbiente;

    private ArrayList<String> TextosElementos;
    private ArrayAdapter<String> AdaptadorElemento;

    private ArrayList<String> TextosInstructores;
    private ArrayAdapter<String> AdaptadorInstructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_novedades);

        // Generar menu
        MaterialToolbar Toolbar = findViewById(R.id.ToolbarNovedad);
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
        TxtFecha = findViewById(R.id.TxtFecNov);
        SpTipo = findViewById(R.id.SpTipoNov);
        TxtDescripcion = findViewById(R.id.TxtDesNov);
        SpAmbiente = findViewById(R.id.SpAmbNov);
        SpElemento = findViewById(R.id.SpEleNov);
        SpInstructor = findViewById(R.id.SpInsNov);
        DatosNovedad = findViewById(R.id.DatosNovedad);

        // Firebase
        RefNovedad = FirebaseDatabase.getInstance().getReference("Novedad");
        RefAmbiente = FirebaseDatabase.getInstance().getReference("Ambiente");
        RefElemento = FirebaseDatabase.getInstance().getReference("Elemento");
        RefInstructor = FirebaseDatabase.getInstance().getReference("Instructor");

        Datos = new ArrayList<>();
        Textos = new ArrayList<>();

        TextosAmbientes = new ArrayList<>();
        TextosElementos = new ArrayList<>();
        TextosInstructores = new ArrayList<>();

        CargarSpinnerAmbientes();
        CargarSpinnerElementos();
        CargarSpinnerInstructores();
        Listar();

        DatosNovedad.setOnItemClickListener((parent, view, position, id) -> {
            Seleccionar(position);
        });

        TxtFecha.setFocusable(false);
        TxtFecha.setClickable(true);

        TxtFecha.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int Anio = calendar.get(Calendar.YEAR);
            int Mes = calendar.get(Calendar.MONTH);
            int Dia = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    NovedadesActivity.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        String fechaFormateada = String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, (monthOfYear + 1), year);
                        TxtFecha.setText(fechaFormateada);
                    },
                    Anio, Mes, Dia
            );
            datePickerDialog.show();
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
                    ClsAmbiente a = Item.getValue(ClsAmbiente.class);
                    if (a != null) {
                        TextosAmbientes.add(a.getNumeroIdAmbiente());
                    }
                }

                AdaptadorAmbiente = new ArrayAdapter<>(NovedadesActivity.this,
                        android.R.layout.simple_spinner_item, TextosAmbientes);

                AdaptadorAmbiente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                SpAmbiente.setAdapter(AdaptadorAmbiente);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NovedadesActivity.this, "Error al cargar ambientes",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void CargarSpinnerElementos() {
        RefElemento.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TextosElementos.clear();
                TextosElementos.add("Seleccione...");

                for (DataSnapshot Item : snapshot.getChildren()) {
                    ClsElemento e = Item.getValue(ClsElemento.class);
                    if (e != null) {
                        TextosElementos.add(e.getCodigoElemento());
                    }
                }

                AdaptadorElemento = new ArrayAdapter<>(NovedadesActivity.this,
                        android.R.layout.simple_spinner_item, TextosElementos);

                AdaptadorElemento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                SpElemento.setAdapter(AdaptadorElemento);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NovedadesActivity.this, "Error al cargar elementos",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void CargarSpinnerInstructores() {
        RefInstructor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TextosInstructores.clear();
                TextosInstructores.add("Seleccione...");

                for (DataSnapshot Item : snapshot.getChildren()) {
                    ClsInstructor i = Item.getValue(ClsInstructor.class);
                    if (i != null) {
                        TextosInstructores.add(i.getDocumentoInstructor());
                    }
                }

                AdaptadorInstructor = new ArrayAdapter<>(NovedadesActivity.this,
                        android.R.layout.simple_spinner_item, TextosInstructores);

                AdaptadorInstructor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                SpInstructor.setAdapter(AdaptadorInstructor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NovedadesActivity.this, "Error al cargar instructores",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Metodos
    private boolean Validar() {
        if (TxtFecha.getText().toString().isEmpty()) {
            TxtFecha.setError("Ingrese fecha");
            return false;
        } else if (SpTipo.getSelectedItemPosition() <= 0) {
            Toast.makeText(this, "Seleccione un tipo",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (TxtDescripcion.getText().toString().isEmpty()) {
            TxtDescripcion.setError("Ingrese descripcion");
            return false;
        } else if (SpAmbiente.getSelectedItemPosition() <= 0) {
            Toast.makeText(this, "Seleccione un ambiente",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (SpElemento.getSelectedItemPosition() <= 0) {
            Toast.makeText(this, "Seleccione un elemento",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (SpInstructor.getSelectedItemPosition() <= 0) {
            Toast.makeText(this, "Seleccione un instructor",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void Limpiar() {
        TxtFecha.setText("");
        SpTipo.setSelection(0);
        TxtDescripcion.setText("");
        SpAmbiente.setSelection(0);
        SpElemento.setSelection(0);
        SpInstructor.setSelection(0);
        Seleccionado = null;
    }

    private void Guardar() {
        if (!Validar()) return;

        String Id = RefNovedad.push().getKey();

        ClsNovedades n = new ClsNovedades(
                Id,
                TxtFecha.getText().toString().trim(),
                SpTipo.getSelectedItem().toString(),
                TxtDescripcion.getText().toString().trim(),
                SpAmbiente.getSelectedItem().toString(),
                SpElemento.getSelectedItem().toString(),
                SpInstructor.getSelectedItem().toString()
        );

        RefNovedad.child(Id).setValue(n).addOnSuccessListener(v -> {
            Toast.makeText(this, "Novedad guardada",
                    Toast.LENGTH_SHORT).show();
            Limpiar();
        }).addOnFailureListener(error -> {
            Toast.makeText(this, "Error al guardar",
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void Listar() {
        RefNovedad.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Datos.clear();
                Textos.clear();

                for (DataSnapshot Item : snapshot.getChildren()) {
                    ClsNovedades n = Item.getValue(ClsNovedades.class);

                    if (n != null) {
                        Datos.add(n);
                        Textos.add(n.getFechaNovedad() + " - " + n.getTipoNovedad() + " - " + n.getDescripcionNovedad());
                    }
                }

                Adaptador = new ArrayAdapter<>(NovedadesActivity.this,
                        android.R.layout.simple_list_item_1, Textos);
                DatosNovedad.setAdapter(Adaptador);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NovedadesActivity.this, "Error: " + error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Seleccionar(int position) {
        Seleccionado = Datos.get(position);

        TxtFecha.setText(Seleccionado.getFechaNovedad());
        TxtDescripcion.setText(Seleccionado.getDescripcionNovedad());

        for (int i = 0; i < SpTipo.getCount(); i++) {
            if (SpTipo.getItemAtPosition(i).toString().equals(Seleccionado.getTipoNovedad())) {
                SpTipo.setSelection(i);
                break;
            }
        }

        for (int i = 0; i < SpAmbiente.getCount(); i++) {
            if (SpAmbiente.getItemAtPosition(i).toString().equals(Seleccionado.getAmbienteNovedad())) {
                SpAmbiente.setSelection(i);
                break;
            }
        }

        for (int i = 0; i < SpElemento.getCount(); i++) {
            if (SpElemento.getItemAtPosition(i).toString().equals(Seleccionado.getElementoNovedad())) {
                SpElemento.setSelection(i);
                break;
            }
        }

        for (int i = 0; i < SpInstructor.getCount(); i++) {
            if (SpInstructor.getItemAtPosition(i).toString().equals(Seleccionado.getInstructorNovedad())) {
                SpInstructor.setSelection(i);
                break;
            }
        }

        Toast.makeText(this, "Novedad seleccionada",
                Toast.LENGTH_SHORT).show();
    }

    private void Actualizar() {
        if (Seleccionado == null) {
            Toast.makeText(this, "Seleccione una novedad",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Validar()) return;

        ClsNovedades n = new ClsNovedades(
                Seleccionado.getIdNovedad(),
                TxtFecha.getText().toString().trim(),
                SpTipo.getSelectedItem().toString(),
                TxtDescripcion.getText().toString().trim(),
                SpAmbiente.getSelectedItem().toString(),
                SpElemento.getSelectedItem().toString(),
                SpInstructor.getSelectedItem().toString()
        );

        RefNovedad.child(Seleccionado.getIdNovedad()).setValue(n)
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, "Novedad actualizada",
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
            Toast.makeText(this, "Seleccione una novedad",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        RefNovedad.child(Seleccionado.getIdNovedad()).removeValue()
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, "Novedad eliminada",
                            Toast.LENGTH_SHORT).show();
                    Limpiar();
                    Seleccionado = null;
                }).addOnFailureListener(error -> {
                    Toast.makeText(this, "No fue posible eliminar",
                            Toast.LENGTH_SHORT).show();
                });
    }
}