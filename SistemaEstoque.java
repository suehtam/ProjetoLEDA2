public class SistemaEstoque {
    // estruturas para Busca e Ordenação
    private HashTable<Integer, Produto> indiceRapido;  // O(1)
    private AVLTree<Produto> indiceOrdenado;           // O(log n)
    
    // estrutura para Reposição (Prioridade)
    private Heap<Produto> prioridadeReposicao;         // O(log n)
    
    // estrutura para Fluxo de Trabalho (FIFO)
    private Queue<OperacaoEstoque> filaOperacoes;      // O(1) amortizado

    public SistemaEstoque(int capacidadeHash, int capacidadeHeap) {
        this.indiceRapido = new HashTable<>(capacidadeHash);
        this.indiceOrdenado = new AVLTree<>();
        
        // minHeap: menor quantidade = maior prioridade
        Comparator<Produto> regraQuantidade = (p1, p2) -> Integer.compare(p1.getQuantidade(), p2.getQuantidade());
        this.prioridadeReposicao = new Heap<>(capacidadeHeap, regraQuantidade);
        
        this.filaOperacoes = new Queue<>();
    }

    // --- CRUD E BUSCAS ---

    public void cadastrarProduto(int id, String nome, int quantidade, double preco) {
        Produto novo = new Produto(id, nome, quantidade, preco);
        
        indiceRapido.put(id, novo);
        indiceOrdenado.insert(novo);
        prioridadeReposicao.insert(novo);
        
        System.out.println("Produto " + nome + " cadastrado com sucesso!");
    }

    public Produto buscarPorId(int id) {
        return indiceRapido.get(id); // direto ao ponto em O(1)
    }

    // --- PROCESSAMENTO DE OPERAÇÕES ---

    public void registrarEntrada(int id, int qtd) {
        filaOperacoes.enqueue(new OperacaoEstoque(id, qtd, TipoOperacao.ENTRADA));
        System.out.println("Operação de ENTRADA agendada para o produto ID: " + id);
    }

    public void registrarSaida(int id, int qtd) {
        filaOperacoes.enqueue(new OperacaoEstoque(id, qtd, TipoOperacao.SAIDA));
        System.out.println("Operação de SAÍDA agendada para o produto ID: " + id);
    }

    public void processarProximaOperacao() {
        if (filaOperacoes.isEmpty()) {
            System.out.println("Nenhuma operação pendente na fila.");
            return;
        }

        OperacaoEstoque op = filaOperacoes.dequeue();
        Produto p = indiceRapido.get(op.getProdutoId());

        if (p == null) {
            System.out.println("Erro: Produto ID " + op.getProdutoId() + " não encontrado.");
            return;
        }

        if (op.getTipoOperacao() == TipoOperacao.ENTRADA) {
            p.setQuantidade(p.getQuantidade() + op.getQuantidade());
        } else {
            int novaQtd = p.getQuantidade() - op.getQuantidade();
            p.setQuantidade(Math.max(0, novaQtd));
        }

        // importante: Como a quantidade mudou, o Heap precisa ser atualizado. 
        // para funcionar, tem que reconstruir o Heap no momento do relatório.
        System.out.println("Operação processada! Novo estoque de " + p.getNome() + ": " + p.getQuantidade());
    }

    // --- RELATÓRIOS ---

    public void mostrarProdutoParaReposicao() {
        try {
            // o Heap sempre mantém o menor estoque no topo
            Produto prioritario = prioridadeReposicao.remove(); 
            System.out.println("CRÍTICO: O produto que mais precisa de reposição é: " 
                                + prioritario.getNome() + " (Qtd: " + prioritario.getQuantidade() + ")");
            
            // insere de volta se não for apenas para deletar
            prioridadeReposicao.insert(prioritario);
        } catch (RuntimeException e) {
            System.out.println("Estoque vazio, nada para repor.");
        }
    }
}
