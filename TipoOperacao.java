public enum TipoOperacao {
    ENTRADA,
    SAIDA
}

public class OperacaoEstoque {
    private int produtoId;
    private int quantidade;
    private TipoOperacao tipoOperacao;

    public OperacaoEstoque(int produtoId, int quantidade, TipoOperacao tipoOperacao) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.tipoOperacao = tipoOperacao;
    }

    public int getProdutoId() { return produtoId; }
    public int getQuantidade() { return quantidade; }
    public TipoOperacao getTipoOperacao() { return tipoOperacao; }
}
