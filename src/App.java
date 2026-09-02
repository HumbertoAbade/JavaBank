import com.javabank.modelo.Conta; // Importa a classe Conta
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        final int PIN_OPERADOR = 8888;
        boolean operadorAutenticado = false;

        System.out.println("=== JAVABANK 2026.2 - TERMINAL DO CAIXA ===");

        // Autenticação por PIN
        for (int tentativa = 1; tentativa <= 3; tentativa++) {
            System.out.print("Informe o PIN do Operador (Tentativa " + tentativa + " de 3): ");
            int pinDigitado = Integer.parseInt(teclado.nextLine());

            if (pinDigitado == PIN_OPERADOR) {
                operadorAutenticado = true;
                break;
            } else {
                System.out.println("[ALERTA] PIN incorreto!");
            }
        }

        // Execução do Terminal com Orientação a Objetos
        if (operadorAutenticado) {
            System.out.println("\n[SESSÃO INICIADA] Bem-vindo, Operador!");

            // Variáveis de referência para guardar os Objetos de Conta
            Conta conta1 = null;
            Conta conta2 = null;

            int opcao;
            do {
                System.out.println("\n--- OPERAÇÕES DO TERMINAL ---");
                System.out.println("1 - Abrir Conta de Cliente");
                System.out.println("2 - Consultar Saldo");
                System.out.println("3 - Realizar Depósito");
                System.out.println("4 - Realizar Saque");
                System.out.println("5 - Realizar Transferência");
                System.out.println("6 - Encerrar Caixa");
                System.out.print("Selecione a operação: ");
                opcao = Integer.parseInt(teclado.nextLine());

                // Estrutura do switch tradicional
                switch (opcao) {
                    case 1:
                        System.out.println("\n--- ABERTURA DE CONTA ---");

                        // 1. Validação do Número da Conta
                        int numero = 0;
                        boolean numeroValido = false;

                        while (!numeroValido) {
                            System.out.print("Informe o número da nova conta: ");
                            String entrada = teclado.nextLine().trim();

                            try {
                                int numeroInformado = Integer.parseInt(entrada);

                                // Verifica se o número informado já pertence à conta1 ou conta2
                                boolean jaExisteConta1 = (conta1 != null && conta1.getNumero() == numeroInformado);
                                boolean jaExisteConta2 = (conta2 != null && conta2.getNumero() == numeroInformado);

                                if (jaExisteConta1 || jaExisteConta2) {
                                    System.out.println("[ERRO] Já existe uma conta cadastrada com esse número. Tente outro.\n");
                                } else if (numeroInformado <= 0) {
                                    System.out.println("[ERRO] O número da conta deve ser maior que zero.\n");
                                } else {
                                    numero = numeroInformado;
                                    numeroValido = true; // Número é válido e único
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("[ERRO] Entrada inválida. Por favor, digite um número inteiro válido.\n");
                            }
                        }

                        // 2. Validação do Nome do Titular
                        String titular = "";
                        while (titular.trim().length() < 5) {
                            System.out.print("Informe o nome do titular (mínimo 5 caracteres): ");
                            titular = teclado.nextLine().trim();

                            if (titular.trim().length() < 5) {
                                System.out.println("[ERRO] O nome do titular deve ter pelo menos 5 caracteres.\n");
                            }
                        }

                        // 3. Validação do Depósito Inicial
                        double depositoInicial = 0.0;
                        boolean depositoValido = false;

                        while (!depositoValido) {
                            System.out.print("Informe o depósito inicial: R$ ");
                            String entrada = teclado.nextLine().trim();

                            try {
                                depositoInicial = Double.parseDouble(entrada);

                                if (depositoInicial < 0) {
                                    System.out.println("[ERRO] O valor do depósito não pode ser negativo.\n");
                                } else {
                                    depositoValido = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("[ERRO] Entrada inválida. Por favor, digite um valor numérico válido (ex: 150.00).\n");
                            }
                        }

                        // Instanciação e alocação da nova Conta nos espaços disponíveis
                        if (conta1 == null) {
                            conta1 = new Conta(numero, titular, depositoInicial);
                            System.out.println("\n-> Conta 1 criada com sucesso!");
                        } else if (conta2 == null) {
                            conta2 = new Conta(numero, titular, depositoInicial);
                            System.out.println("\n-> Conta 2 criada com sucesso!");
                        } else {
                            System.out.println("\n[ALERTA] O limite de contas no terminal (2) foi atingido.");
                        }
                        break;

                    case 2:
                        System.out.println("\n--- CONSULTA DE SALDO ---");
                        if (conta1 == null && conta2 == null) {
                            System.out.println("[ERRO] Nenhuma conta ativa no momento.");
                        } else {
                            if (conta1 != null) {
                                System.out.printf("Conta: %d | Titular: %s | Saldo: R$ %.2f%n",
                                        conta1.getNumero(), conta1.getTitular(), conta1.getSaldo());
                            }
                            if (conta2 != null) {
                                System.out.printf("Conta: %d | Titular: %s | Saldo: R$ %.2f%n",
                                        conta2.getNumero(), conta2.getTitular(), conta2.getSaldo());
                            }
                        }
                        break;

                    case 3:
                        System.out.println("\n--- REALIZAR DEPÓSITO ---");
                        System.out.print("Informe o número da conta destino: ");
                        int numDep = Integer.parseInt(teclado.nextLine());

                        // Identifica qual conta deve receber o depósito
                        Conta alvoDep = (conta1 != null && conta1.getNumero() == numDep) ? conta1 :
                                (conta2 != null && conta2.getNumero() == numDep) ? conta2 : null;

                        if (alvoDep != null) {
                            System.out.print("Valor do depósito: R$ ");
                            double valor = Double.parseDouble(teclado.nextLine());

                            if (alvoDep.depositar(valor)) {
                                System.out.printf("-> Depósito efetuado com sucesso. Novo saldo: R$ %.2f%n", alvoDep.getSaldo());
                            } else {
                                System.out.println("[ERRO] Valor inválido! O valor deve ser maior que zero.");
                            }
                        } else {
                            System.out.println("[ERRO] Conta não encontrada.");
                        }
                        break;

                    case 4:
                        System.out.println("\n--- REALIZAR SAQUE ---");
                        System.out.print("Informe o número da conta: ");
                        int numSaq = Integer.parseInt(teclado.nextLine());

                        Conta alvoSaq = (conta1 != null && conta1.getNumero() == numSaq) ? conta1 :
                                (conta2 != null && conta2.getNumero() == numSaq) ? conta2 : null;

                        if (alvoSaq != null) {
                            System.out.print("Valor do saque: R$ ");
                            double valor = Double.parseDouble(teclado.nextLine());

                            if (alvoSaq.sacar(valor)) {
                                System.out.printf("-> Saque efetuado com sucesso. Novo saldo: R$ %.2f%n", alvoSaq.getSaldo());
                            } else {
                                System.out.println("[RECUSADO] Saldo insuficiente ou valor inválido.");
                            }
                        } else {
                            System.out.println("[ERRO] Conta não encontrada.");
                        }
                        break;

                    case 5:
                        System.out.println("\n--- REALIZAR TRANSFERÊNCIA ---");
                        if (conta1 == null || conta2 == null) {
                            System.out.println("[ERRO] É necessário cadastrar pelo menos 2 contas para realizar transferências.");
                        } else {
                            System.out.print("Informe o número da conta de ORIGEM: ");
                            int numOrigem = Integer.parseInt(teclado.nextLine());

                            Conta origem = (conta1.getNumero() == numOrigem) ? conta1 :
                                    (conta2.getNumero() == numOrigem) ? conta2 : null;

                            // A conta destino será automaticamente a outra conta cadastrada
                            Conta destino = (origem == conta1) ? conta2 : conta1;

                            if (origem != null) {
                                System.out.print("Valor da transferência: R$ ");
                                double valor = Double.parseDouble(teclado.nextLine());

                                if (origem.transferir(valor, destino)) {
                                    System.out.println("-> Transferência efetuada com sucesso!");
                                } else {
                                    System.out.println("[RECUSADO] Saldo insuficiente ou valor inválido.");
                                }
                            } else {
                                System.out.println("[ERRO] Conta de origem não encontrada.");
                            }
                        }
                        break;

                    case 6:
                        System.out.println("\n[FECHAMENTO] Encerrando expediente do caixa...");
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                        break;
                }
            } while (opcao != 6);

        } else {
            System.out.println("\n[BLOQUEIO] Limite de tentativas do PIN excedido. Caixa bloqueado!");
        }

        teclado.close();
    }
}