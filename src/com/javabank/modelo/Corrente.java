package com.javabank.modelo;

public class Corrente extends Conta {
    private double limite;

    public Corrente(int numero, String titular, double saldo, double limite) {
        super(numero, titular, saldo);
        this.limite = limite;
    }

    public double getLimite() {
        return this.limite;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= this.saldo + limite) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }

}
