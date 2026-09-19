package com.javabank.modelo;

public class Poupanca extends Conta {
    private double taxaRendimento;

    public Poupanca(int numero, String titular, double saldoInicial, double taxaRendimento) {
        super(numero, titular, saldoInicial);
        if (taxaRendimento >= 0) {
            this.taxaRendimento = taxaRendimento;
        } else {
            this.taxaRendimento = 0.005; // 0.5% como padrão
        }
    }

    public double renderJuros() {
        double rendimento = this.saldo * this.taxaRendimento;
        this.saldo += rendimento;
        return rendimento;
    }

    public double getTaxaRendimento() {
        return this.taxaRendimento;
    }
}