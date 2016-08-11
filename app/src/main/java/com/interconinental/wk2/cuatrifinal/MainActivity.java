package com.interconinental.wk2.cuatrifinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btnAgregar)
    Button btnAgregar;
    @Bind(R.id.etCalificaciones)
    EditText etCalificaciones;
    @Bind(R.id.tvCalif)
    TextView tvCalif;
    @Bind(R.id.etTotalAlumnos) EditText etTotalAlumnos;
    @Bind(R.id.tvLista) TextView tvLista;
    @Bind(R.id.tvPromedio) TextView tvPromedio;
    @Bind(R.id.tvAprobados) TextView tvAprobados;
    @Bind(R.id.tvReprobados) TextView tvReprobados;
    @Bind(R.id.tvIAprobacion) TextView tvIAprobacion;
    @Bind(R.id.tvIReprobacion) TextView tvIReprobacion;
    @Bind(R.id.tvIDesercion) TextView tvIDesercion;
    @Bind(R.id.etTotalAlumnosDes) EditText etTotalAlumnosDes;
    @Bind(R.id.btnBorrar) Button btnBorrar;

    int numTotAlum;
    String lista = "|";
   ArrayList<Double> calif;
    double promedio = 0;
    double suma = 0;
    int aprobados = 0;
    int reprobados = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        NumAlumnos();
        calif = new ArrayList<Double>();
    }

    public void NumAlumnos(){

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                etTotalAlumnos.getText().clear();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                numTotAlum = Integer.parseInt(etTotalAlumnos.getText().toString());
                tvCalif.setText(etTotalAlumnos.getText().toString());
            }
        };
        etTotalAlumnos.addTextChangedListener(textWatcher);

    }

    @OnClick(R.id.btnAgregar)
    public void Agregar(){

        if(numTotAlum > 0) {


            calif.add(Double.parseDouble(etCalificaciones.getText().toString()));

            if(numTotAlum == 1){
                lista += etCalificaciones.getText().toString() + "|";
                for (Double c:calif) {
                    suma += c;
                    if(c < 7.0){
                        reprobados++;
                    }

                }
                promedio = suma/calif.size();
                aprobados = calif.size() - reprobados;
                tvPromedio.setText("Promedio: " + String.valueOf(promedio));
                tvAprobados.setText("# Aprobados: " + String.valueOf(aprobados));
                tvReprobados.setText("# Reprobados: " + String.valueOf(reprobados));
                tvIAprobacion.setText("I. de Aprobación: " + String.valueOf(Porciento(calif.size(),aprobados)) + "%");
                tvIReprobacion.setText("I. de Reprobación: " + String.valueOf(Porciento(calif.size(),reprobados)) + "%");
                tvIDesercion.setText("I. de Deserción: " + String.valueOf(Porciento(calif.size(),Integer.parseInt(etTotalAlumnosDes.getText().toString()))) + "%");
            }else {
                lista += etCalificaciones.getText().toString() + "-";
            }


            numTotAlum--;
            tvCalif.setText((String.valueOf(numTotAlum)));
            tvLista.setText(lista);
            etCalificaciones.getText().clear();

        }else {
            Toast.makeText(this,"Lista completa!",Toast.LENGTH_LONG).show();
        }
    }

    protected double Porciento(int total, int c){
        double res;
            res = c*100/total;
        return res;
    }

    @OnClick(R.id.btnBorrar)
    public void Borrar(){

        reiniciar();
    }

    private void reiniciar() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}