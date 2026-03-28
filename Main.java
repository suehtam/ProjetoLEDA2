import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        // Inicializa o sistema com capacidades iniciais (Hash e Heap)
        SistemaEstoque sistema = new SistemaEstoque(100, 50);
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        System.out.println("=========================================");
        System.out.println("   SISTEMA HÍBRIDO DE GESTÃO DE ESTOQUE  ");
        System.out.println("=========================================");

        while (opcao != 0) {
            exibirMenu();
            try {
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado

                switch (opcao) {
                    case 1: // Cadastrar Produto
                        System.out.print("ID do Produto: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nome do Produto: ");
                        String nome = scanner.nextLine();
                        System.out.print("Quantidade Inicial: ");
                        int qtd = scanner.nextInt();
                        System.out.print("Preço: ");
                        double preco = scanner.nextDouble();
                        
                        sistema.cadastrarProduto(id, nome, qtd, preco);
                        break;

                    case 2: // Buscar Produto
                        System.out.print("Digite o ID para busca: ");
                        int idBusca = scanner.nextInt();
                        Produto p = sistema.buscarPorId(idBusca);
                        if (p != null) {
                            System.out.println("\n[PRODUTO ENCONTRADO]");
                            System.out.println(p);
                            System.out.println("Preço: R$ " + p.getPreco());
                        } else {
                            System.out.println("ERRO: Produto não encontrado.");
                        }
                        break;

                    case 3: // Registrar Operação (Entrada/Saída)
                        System.out.print("ID do Produto: ");
                        int idOp = scanner.nextInt();
                        System.out.print("Quantidade: ");
                        int qtdOp = scanner.nextInt();
                        System.out.println("Tipo: 1-Entrada | 2-Saída");
                        int tipo = scanner.nextInt();

                        if (tipo == 1) sistema.registrarEntrada(idOp, qtdOp);
                        else if (tipo == 2) sistema.registrarSaida(idOp, qtdOp);
                        else System.out.println("Tipo inválido.");
                        break;

                    case 4: // Processar Próxima Operação (FIFO)
                        System.out.println("\nProcessando fila de operações...");
                        sistema.processarProximaOperacao();
                        break;

                    case 5: // Relatório de Reposição (Min-Heap)
                        System.out.println("\n[RELATÓRIO DE PRIORIDADE]");
                        sistema.mostrarProdutoParaReposicao();
                        break;

                    case 0:
                        System.out.println("Encerrando o sistema... Até logo!");
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("ERRO: Por favor, digite apenas números.");
                scanner.nextLine(); // Limpa o buffer para a próxima tentativa
            }
            System.out.println("-----------------------------------------");
        }
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n[MENU PRINCIPAL]");
        System.out.println("1 - Cadastrar Novo Produto");
        System.out.println("2 - Buscar Produto por ID (Hash)");
        System.out.println("3 - Agendar Operação de Estoque (Fila/Pilha)");
        System.out.println("4 - Processar Próxima Operação Pendente");
        System.out.println("5 - Ver Produto Crítico (Min-Heap)");
        System.out.println("0 - Sair");
    }
}
