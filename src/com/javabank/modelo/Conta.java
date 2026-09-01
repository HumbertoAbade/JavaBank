package com.javabank.modelo;

public class Conta {
    private int numero;
    private String titular;
    private double saldo;

    public Conta(int numero, String titular, double saldoInicial) {
        this.numero = numero;
        this.titular = titular;
        if (saldoInicial >= 0) {
            this.saldo = saldoInicial;
        } else {
            this.saldo = 0.0;
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

    public double getSaldo() {
        return this.saldo;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getTitular() {
        return this.titular;
    }

    public boolean setTitular(String titular) {
        if(titular != null && titular.length() > 1){
            this.titular = titular;
            return true;
        }else{
            System.out.println("O nome deve possuir pelo menos 2 caracteres");
            return false;
        }
    }
}
