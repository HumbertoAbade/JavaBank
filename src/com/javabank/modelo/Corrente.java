package com.javabank.modelo;

public class Corrente extends Conta {
    private double limiteEspecial;

    public Corrente(int numero, String titular, double saldoInicial, double limiteEspecial) {
        super(numero, titular, saldoInicial);
        if (limiteEspecial >= 0) {
            this.limiteEspecial = limiteEspecial;
        } else {
            this.limiteEspecial = 0.0;
        }
    }

    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= (this.saldo + this.limiteEspecial)) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    public double getLimiteEspecial() {
        return this.limiteEspecial;
    }
}