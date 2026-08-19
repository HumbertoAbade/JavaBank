import java.util.Scanner;

public class App {
    // Atalho > PSVM + TAB > main()
    public static void main(String[] args) {

        // Atalho > SOUT + TAB > println
        System.out.println("JAVABANK - TERMINAL DO CAIXA");

        // Variáveis
        Scanner entrada = new Scanner (System.in);
        // boolean para validar o acesso do usuário (dois estados logado / deslogado)
        boolean operadorAutenticado = false;

        // final = constante, não pode ser alterado (boa prática tudo em caps lock)
        final int SENHA_OPERADOR = 8888;
        System.out.println("Informe a sua senha: ");
        // entrada de senha pelo usuário - pega como texto e converte para número
        int senha = Integer.parseInt(entrada.nextLine());

        // If + Else para validar o acesso
        if(senha == SENHA_OPERADOR){
            System.out.println("[CONECTADO] Bem-vindo!");
            operadorAutenticado = true;
        }else{
            System.out.println("[SENHA INCORRETA] Tente novamente");
        }

        entrada.close();

    }
}
