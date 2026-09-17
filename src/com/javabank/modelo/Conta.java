package com.javabank.modelo;

public class Conta {

    protected int numero;
    protected String titular;
    protected double saldo;

    public String getTitular() {
        return this.titular;
    }

    public int getNumero() {
        return this.numero;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public Conta(int numero, String titular, double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
        if (saldo >= 0) {
            this.saldo = saldo;
        } else {
            this.saldo = 0.0;
        }
    }

    public boolean setTitular(String titular) {
        if (titular != null && titular.trim().length() >= 5) {
            this.titular = titular;
            return true;
        } else {
            System.out.println("O nome deve possuir pelo menos 5 caracteres");
            return false;
        }
    }

    public boolean depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            return true;
        }
        return false;
    }

    public boolean sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    public boolean transferir(double valor, Conta destino) {
        if (destino != null && this.sacar(valor)) {
            destino.depositar(valor);
            return true;
        }
        return false;
    }

}