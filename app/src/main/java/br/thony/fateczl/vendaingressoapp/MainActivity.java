package br.thony.fateczl.vendaingressoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etValorIngresso, etCodigoIngresso, etFuncao, etTaxaConv;
    private Button btnComprar;
    private CheckBox cbVip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        etValorIngresso = findViewById(R.id.etValorIngresso);
        etCodigoIngresso = findViewById(R.id.etCodigoIngresso);
        etTaxaConv = findViewById(R.id.etTaxaConv);
        etFuncao = findViewById(R.id.etFuncao);
        btnComprar = findViewById(R.id.btnComprar);
        cbVip = findViewById(R.id.cbVip);
        
        btnComprar.setOnClickListener(op -> calcularValor());
    }

    public void calcularValor() {
        float valorIngresso = Float.parseFloat(etValorIngresso.getText().toString());
        float taxaConv = Float.parseFloat(etTaxaConv.getText().toString());
        String codigoIngresso = etCodigoIngresso.getText().toString();
        String funcao = etFuncao.getText().toString();
        boolean vip = cbVip.isChecked();

        float valorFinal;

        if (vip && !funcao.isEmpty()) {
            IngressoVip ingressoVip = new IngressoVip(codigoIngresso, valorIngresso);
            valorFinal = ingressoVip.valorFinal(taxaConv);
        } else if (vip) {
            Toast.makeText(this, "Informe a função para o ingresso VIP", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Ingresso ingresso = new Ingresso(codigoIngresso, valorIngresso);
            valorFinal = ingresso.valorFinal(taxaConv);
        }

        Intent intent = new Intent(MainActivity.this, DadosCompra.class);
        intent.putExtra("valorFinal", valorFinal);
        intent.putExtra("codigoIngresso", codigoIngresso);
        intent.putExtra("funcao", funcao);
        intent.putExtra("vip", vip);

        startActivity(intent);
    }
}