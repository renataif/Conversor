package com.example.atividade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etVariacao, etValorInicial, etValorFinal;
    private Spinner cbTipoMedida;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etValorInicial = findViewById(R.id.etValorInicial);
        etValorFinal = findViewById(R.id.etValorFinal);
        etVariacao = findViewById(R.id.etVariacao);
        cbTipoMedida = findViewById(R.id.cbTipoMedida);
        tvResultado = findViewById(R.id.tvResultado);
        tvResultado.setText("");

        ArrayList lista = new ArrayList();
        lista.add("Centímetros");
        lista.add("Metros");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lista);
        cbTipoMedida.setAdapter(adapter);

    }

    public void OnClick(View v){
        if(etValorInicial.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Informe um valor inicial.", Toast.LENGTH_SHORT).show();
            etValorInicial.requestFocus();
        }
        else if(etValorFinal.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Informe o valor final.", Toast.LENGTH_SHORT).show();
            etValorFinal.requestFocus();
        }
        else if(etVariacao.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Informe o variação.", Toast.LENGTH_SHORT).show();
            etVariacao.requestFocus();
        }
        else if (Double.parseDouble(etValorInicial.getText().toString())>Double.parseDouble(etValorFinal.getText().toString())){
            Toast.makeText(getApplicationContext(), "Informe um Valor Inicial menor que o final.", Toast.LENGTH_SHORT).show();
            etValorInicial.setText("");
            etValorInicial.requestFocus();
        }
        else{
            ArrayList<String> listafinal = new ArrayList<String>();
            String res;
            double pe = 0, p = 0;
            double vi = Double.parseDouble(etValorInicial.getText().toString()), vf = Double.parseDouble(etValorFinal.getText().toString());
            double vr = Double.parseDouble(etVariacao.getText().toString());
            if(cbTipoMedida.getSelectedItemPosition() == 0){
                while(vi<=vf){
                    pe = vi/30.48;
                    p = vi/2.54;
                    res = String.valueOf(vi) + "cm " + String.format("%.3f", pe) + "ft " + String.format("%.3f", p) + "in ";
                    listafinal.add(res);
                    vi = vi + vr;
                    res = "";
                }
            }
            else{
                while(vi<=vf){
                    pe = vi/ 0.3048;
                    p = vi/0.0254;
                    res = String.valueOf(vi) + "m " + String.format("%.3f", pe) + "ft " + String.format("%.3f", p) + "in ";
                    listafinal.add(res);
                    vi = vi + vr;
                    res = "";
                }
            }

            tvResultado.setMovementMethod(new ScrollingMovementMethod());
            StringBuilder interests = new StringBuilder();
            for(String str: listafinal){
                interests.append("\n").append(str);
            }
            tvResultado.setText(interests);
        }
    }
}