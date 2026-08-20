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


        // For - para fazer uma quantidade de tentativas
        for (int tentativa =1; tentativa <=3; tentativa ++){
            System.out.println("Informe a sua senha: ");
            // entrada de senha pelo usuário - pega como texto e converte para número
            int senha = Integer.parseInt(entrada.nextLine());

            // If + Else para validar o acesso
            if(senha == SENHA_OPERADOR){
                System.out.println("[CONECTADO] Bem-vindo!");
                operadorAutenticado = true;
                // Para o loop quando entra
                break;
            }else{
                System.out.println("[SENHA INCORRETA] Tente novamente.");
            }

        }

        // Mensagem após três tentativas incorretas
        if(operadorAutenticado == false){
            System.out.println("[BLOQUEIO] Limite de tentativas excedidas.");

        // Conta logada
        }else{
            int numeroConta = 0;
            String titular = "";
            double saldo = 0;
            boolean contaAtiva = false;
            int opcao = 0;

            // Menu
            do {
                System.out.println("Escolha uma opção: ");
                System.out.println("1 - Abrir conta");
                System.out.println("2 - Consultar saldo");
                System.out.println("3 - Realizar depósito");
                System.out.println("4 - Realizar saque");
                System.out.println("5 - Sair");
                System.out.println("Selecione a opção");
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao){
                    case 1 -> {
                        System.out.println("Informe o número da conta: ");
                        numeroConta = Integer.parseInt(entrada.nextLine());
                        System.out.println("Informe o titular da conta: ");
                        titular = entrada.nextLine();
                        System.out.println("Informe o saldo inicial: ");
                        saldo = Integer.parseInt(entrada.nextLine());

                        while (saldo < 0){
                            System.out.println("O saldo não deve ser negativo.");
                            System.out.println("Infome o saldo inicial: ");
                            saldo = Integer.parseInt(entrada.nextLine());
                        }

                        contaAtiva = true;
                        System.out.println("Conta criada com sucesso");
                    }
                    case 2 -> {
                        if(contaAtiva == true){
                            //System.out.println("Conta: " + numeroConta +
                            //                    "Titular " + titular +
                            //                    "Saldo atual: R$" + saldo);
                            System.out.printf("Conta: %d / Titular: %s / Saldo atual: R$ %.2f \n",
                                    numeroConta, titular, saldo);

                        }else{
                            System.out.println("[ERRO] Nenhuma conta ativa");
                        }
                    }
                    case 3 -> {}
                    case 4 -> {}
                    case 5 -> {
                        System.out.println("Encerrando o sistema.");
                    }
                    default -> {
                        System.out.println("Opção invalida, escolha novamente.");
                    }
                }

            }while (opcao != 5);

        }

        entrada.close();

    }
}
