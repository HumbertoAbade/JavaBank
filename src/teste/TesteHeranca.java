package teste;

import com.javabank.modelo.Corrente;
import com.javabank.modelo.Poupanca;

import javax.annotation.processing.SupportedSourceVersion;

public class TesteHeranca {
    public static void main(String[] args){
        Corrente cc1 = new Corrente(2001,"Qwe", 500, 1000);
        Poupanca cp1 = new Poupanca(2002,"Asd", 1000, 1);

        System.out.println("Titular: " + cc1.getTitular());
        System.out.println("Saldo: " + cc1.getSaldo());
        System.out.println("Limite: " + cc1.getLimite());
        System.out.println();
        System.out.println("Titular: " + cp1.getTitular());
        System.out.println("Saldo: " + cp1.getSaldo());
        System.out.println("Limite: " + cp1.getTaxaJuros() + "%");
        System.out.println("Redimento " + cp1.getCalcularRendimento());
    }

}
