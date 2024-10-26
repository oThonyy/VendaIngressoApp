package br.thony.fateczl.vendaingressoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DadosCompra extends AppCompatActivity       {

    private TextView lblDadosCompra;
    private Button btnRetornar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dados_compra);

        lblDadosCompra = findViewById(R.id.lblDadosCompra);
        btnRetornar = findViewById(R.id.btnRetornar);

        Intent intent = getIntent();
        float valorFinal = intent.getFloatExtra("valorFinal", 0);
        String codigoIngresso = intent.getStringExtra("codigoIngresso");
        String funcao = intent.getStringExtra("funcao");
        boolean vip = intent.getBooleanExtra("vip", false);

        String dados;
        if(vip) {
            dados = "INGRESSO VIP\nValor Final: " + valorFinal + "\nCódigo Ingresso: " + codigoIngresso +
                    "\nFunção: "  + funcao;
        } else {
            dados = "INGRESSO COMUM\nValor Final " + valorFinal + "\nCódigo Ingresso: " + codigoIngresso;
        }

        lblDadosCompra.setText(dados);

        btnRetornar.setOnClickListener(op -> retornar());
    }

    private void retornar() {
        finish();
    }
}
