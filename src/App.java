import java.util.Scanner;
import com.javabank.modelo.Conta;
import com.javabank.modelo.Corrente;
import com.javabank.modelo.Poupanca;

public class App {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        final int PIN_OPERADOR = 1234;
        int tentativa = 1;
        boolean operadorAutenticado = false;

        System.out.println("=== JAVABANK 2026.2 - TERMINAL DO CAIXA ===");

        // Autenticação do Operador
        while (tentativa <= 3) {
            System.out.print("Informe o PIN do Operador (Tentativa " + tentativa + " de 3): ");
            int pinDigitado = Integer.parseInt(teclado.nextLine());
            if (pinDigitado == PIN_OPERADOR) {
                operadorAutenticado = true;
                break;
            } else {
                System.out.println("[ALERTA] PIN incorreto!");
                tentativa++;
            }
        }

        if (!operadorAutenticado) {
            System.out.println("\n[BLOQUEIO] Limite de tentativas do PIN excedido. Caixa bloqueado!");
            teclado.close();
            return;
        }

        System.out.println("\n[SESSÃO INICIADA] Bem-vindo, Operador!");

        Conta conta1 = null;
        Conta conta2 = null;
        int opcao = 0;

        while (opcao != 7) { // Encerramento na opção 7
            System.out.println("\n--- OPERAÇÕES DO TERMINAL ---");
            System.out.println("1 - Abrir Conta de Cliente");
            System.out.println("2 - Consultar Saldo");
            System.out.println("3 - Realizar Depósito");
            System.out.println("4 - Realizar Saque");
            System.out.println("5 - Realizar Transferência");
            System.out.println("6 - Aplicar Rendimento (Poupança)");
            System.out.println("7 - Encerrar Caixa");
            System.out.print("Selecione a operação: ");

            try {
                opcao = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Entrada inválida. Por favor, digite um número inteiro válido.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n--- ABERTURA DE CONTA ---");
                    if (conta1 != null && conta2 != null) {
                        System.out.println("\n[ALERTA] O limite de contas no terminal (2) foi atingido.");
                        break;
                    }

                    // ETAPA 1: Seleção de Modalidade no Cadastro
                    System.out.println("Selecione o tipo de conta:");
                    System.out.println("1 - Conta Corrente");
                    System.out.println("2 - Conta Poupança");
                    System.out.print("Opção: ");
                    int tipoConta = 0;
                    try {
                        tipoConta = Integer.parseInt(teclado.nextLine());
                        if (tipoConta != 1 && tipoConta != 2) {
                            System.out.println("[ERRO] Tipo de conta inválido!");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("[ERRO] Entrada inválida.");
                        break;
                    }

                    System.out.print("Informe o número da nova conta: ");
                    int numero = 0;
                    try {
                        numero = Integer.parseInt(teclado.nextLine());
                        if (numero <= 0) {
                            System.out.println("[ERRO] O número da conta deve ser maior que zero.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("[ERRO] Entrada inválida. Por favor, digite um número inteiro válido.");
                        break;
                    }

                    if ((conta1 != null && conta1.getNumero() == numero) ||
                            (conta2 != null && conta2.getNumero() == numero)) {
                        System.out.println("[ERRO] Já existe uma conta cadastrada com esse número. Tente outro.");
                        break;
                    }

                    System.out.print("Informe o nome do titular (mínimo 5 caracteres): ");
                    String titular = teclado.nextLine().trim();
                    if (titular.length() < 5) {
                        System.out.println("[ERRO] O nome do titular deve ter pelo menos 5 caracteres.");
                        break;
                    }

                    System.out.print("Informe o depósito inicial: R$ ");
                    double depositoInicial = 0;
                    try {
                        depositoInicial = Double.parseDouble(teclado.nextLine());
                        if (depositoInicial < 0) {
                            System.out.println("[ERRO] O valor do depósito não pode ser negativo.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("[ERRO] Entrada inválida. Por favor, digite um valor numérico válido (ex: 150.00).");
                        break;
                    }

                    Conta novaConta = null;

                    if (tipoConta == 1) {
                        System.out.print("Informe o limite do Cheque Especial: R$ ");
                        double limiteEspecial = 0;
                        try {
                            limiteEspecial = Double.parseDouble(teclado.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("[ERRO] Entrada inválida para o limite.");
                            break;
                        }
                        novaConta = new Corrente(numero, titular, depositoInicial, limiteEspecial);
                    } else if (tipoConta == 2) {
                        System.out.print("Informe a taxa de rendimento (ex: 0.1 para 10%): ");
                        double taxaRendimento = 0;
                        try {
                            taxaRendimento = Double.parseDouble(teclado.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("[ERRO] Entrada inválida para a taxa.");
                            break;
                        }
                        novaConta = new Poupanca(numero, titular, depositoInicial, taxaRendimento);
                    }

                    if (conta1 == null) {
                        conta1 = novaConta;
                        System.out.println("\n-> Conta 1 criada com sucesso!");
                    } else {
                        conta2 = novaConta;
                        System.out.println("\n-> Conta 2 criada com sucesso!");
                    }
                    break;

                case 2:
                    // ETAPA 2: Exibição Detalhada na Consulta (usando instanceof diretamente no main)
                    System.out.println("\n--- CONSULTA DE SALDO ---");
                    if (conta1 == null && conta2 == null) {
                        System.out.println("[ERRO] Nenhuma conta ativa no momento.");
                    } else {
                        if (conta1 != null) {
                            if (conta1 instanceof Corrente cc) {
                                System.out.printf("Conta: %d [CORRENTE] | Titular: %s | Saldo: R$ %.2f | Limite Especial: R$ %.2f%n",
                                        cc.getNumero(), cc.getTitular(), cc.getSaldo(), cc.getLimiteEspecial());
                            } else if (conta1 instanceof Poupanca cp) {
                                System.out.printf("Conta: %d [POUPANÇA] | Titular: %s | Saldo: R$ %.2f | Taxa Rendimento: %.2f%%%n",
                                        cp.getNumero(), cp.getTitular(), cp.getSaldo(), cp.getTaxaRendimento() * 100);
                            }
                        }

                        if (conta2 != null) {
                            if (conta2 instanceof Corrente cc) {
                                System.out.printf("Conta: %d [CORRENTE] | Titular: %s | Saldo: R$ %.2f | Limite Especial: R$ %.2f%n",
                                        cc.getNumero(), cc.getTitular(), cc.getSaldo(), cc.getLimiteEspecial());
                            } else if (conta2 instanceof Poupanca cp) {
                                System.out.printf("Conta: %d [POUPANÇA] | Titular: %s | Saldo: R$ %.2f | Taxa Rendimento: %.2f%%%n",
                                        cp.getNumero(), cp.getTitular(), cp.getSaldo(), cp.getTaxaRendimento() * 100);
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- REALIZAR DEPÓSITO ---");
                    System.out.print("Informe o número da conta destino: ");
                    int numDep = Integer.parseInt(teclado.nextLine());
                    Conta alvoDep = null;

                    if (conta1 != null && conta1.getNumero() == numDep) alvoDep = conta1;
                    else if (conta2 != null && conta2.getNumero() == numDep) alvoDep = conta2;

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
                    Conta alvoSaq = null;

                    if (conta1 != null && conta1.getNumero() == numSaq) alvoSaq = conta1;
                    else if (conta2 != null && conta2.getNumero() == numSaq) alvoSaq = conta2;

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
                        break;
                    }
                    System.out.print("Informe o número da conta de ORIGEM: ");
                    int numOrigem = Integer.parseInt(teclado.nextLine());
                    Conta origem = null, destino = null;

                    if (conta1.getNumero() == numOrigem) {
                        origem = conta1;
                        destino = conta2;
                    } else if (conta2.getNumero() == numOrigem) {
                        origem = conta2;
                        destino = conta1;
                    }

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
                    break;

                case 6:
                    // ETAPA 3: Aplicar Rendimento (Poupança)
                    System.out.println("\n--- APLICAR RENDIMENTO (POUPANÇA) ---");
                    System.out.print("Informe o número da conta poupança: ");
                    int numPoup = Integer.parseInt(teclado.nextLine());
                    Conta alvoPoup = null;

                    if (conta1 != null && conta1.getNumero() == numPoup) alvoPoup = conta1;
                    else if (conta2 != null && conta2.getNumero() == numPoup) alvoPoup = conta2;

                    if (alvoPoup != null) {
                        if (alvoPoup instanceof Poupanca cp) {
                            double rendimento = cp.renderJuros();
                            System.out.printf("-> Rendimento creditado com sucesso: R$ %.2f%n", rendimento);
                            System.out.printf("-> Novo saldo: R$ %.2f%n", cp.getSaldo());
                        } else {
                            System.out.println("[ERRO] A conta informada não é uma Conta Poupança!");
                        }
                    } else {
                        System.out.println("[ERRO] Conta não encontrada.");
                    }
                    break;

                case 7:
                    System.out.println("\n[FECHAMENTO] Encerrando expediente do caixa...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }

        teclado.close();
    }
}