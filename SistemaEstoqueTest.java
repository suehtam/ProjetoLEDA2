import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SistemaEstoqueTest {

    @Test
    void adicionarProdutoEConsultar() {
        SistemaEstoque s = new SistemaEstoque();
        Produto p = new Produto(1000, "Fita", 3.0);
        s.addProduto(p);
        Produto obtido = s.getProduto(1000);
        assertNotNull(obtido);
        assertEquals("Fita", obtido.getNome());
    }

    @Test
    void entradaESaidaBasica() {
        SistemaEstoque s = new SistemaEstoque();
        Produto p = new Produto(2000, "Parafuso", 0.5);
        s.addProduto(p);
        s.entrada(2000, 50);
        assertEquals(50, s.getQuantidade(2000));
        s.saida(2000, 20);
        assertEquals(30, s.getQuantidade(2000));
    }

    @Test
    void saidaMaiorQueEstoqueLancaExcecao() {
        SistemaEstoque s = new SistemaEstoque();
        Produto p = new Produto(3000, "Porca", 0.2);
        s.addProduto(p);
        s.entrada(3000, 5);
        assertThrows(RuntimeException.class, () -> s.saida(3000, 10));
    }
}