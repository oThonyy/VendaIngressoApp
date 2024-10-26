package br.thony.fateczl.vendaingressoapp;

public class IngressoVip extends Ingresso {

    private String funcao;

    public IngressoVip(String codigo, float valor) {
        super(codigo, valor);
        this.funcao = funcao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public float valorFinal(float taxaConv) {
        float valorTaxaVip = getValor() * 1.18f;
        return valorTaxaVip + taxaConv;
    }
}
