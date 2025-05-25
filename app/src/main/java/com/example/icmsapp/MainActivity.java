package com.example.icmsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextEstado;
    private EditText mEditTextValor;
    private TextView mTextViewPorcentagem;
    private TextView mTextViewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mEditTextEstado = findViewById(R.id.editTextEstado);
        mEditTextValor = findViewById(R.id.editTextValor);
        mTextViewPorcentagem = findViewById(R.id.textViewPorcentagem);
        mTextViewTotal = findViewById(R.id.textViewTotal);
    }

    public void calcular(View view) {
        String estado = mEditTextEstado.getText().toString().toUpperCase(); // Convert to uppercase for case-insensitive comparison

        // Add input validation
        if (estado.isEmpty() || mEditTextValor.getText().toString().isEmpty()) {
            mTextViewPorcentagem.setText("Preencha todos os campos");
            mTextViewTotal.setText("");
            return;
        }

        try {
            float valor = Float.parseFloat(mEditTextValor.getText().toString());
            float porcentagem = 0;

            switch(estado) {
                case "RO":
                    porcentagem = 17.5f;
                    break;
                case "SP":
                    porcentagem = 18f;
                    break;
                default:
                    mTextViewPorcentagem.setText("Estado não encontrado");
                    mTextViewTotal.setText("");
                    return;
            }

            float total = valor + (valor * porcentagem/100);
            mTextViewPorcentagem.setText(String.valueOf(porcentagem) + "%");
            mTextViewTotal.setText(String.format("R$ %.2f", total));

        } catch (NumberFormatException e) {
            mTextViewPorcentagem.setText("Valor inválido");
            mTextViewTotal.setText("");
        }
    }
}