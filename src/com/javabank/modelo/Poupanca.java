package com.javabank.modelo;


public class Poupanca extends Conta{
    private double TaxaJuros;

    public Poupanca(int numero, String titular, double saldo, double TaxaJuros) {
        super(numero, titular, saldo);
        this.TaxaJuros = TaxaJuros;
    }

    public double getTaxaJuros() {
        return TaxaJuros;
    }

    public double getCalcularRendimento(){
        return (getSaldo() * TaxaJuros) / 100;
    }

}
