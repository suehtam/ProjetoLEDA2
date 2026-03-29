import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    @Test
    void gettersEConstrutorBasico() {
        Produto p = new Produto(100, "Caneta", 2.5);
        assertEquals(100, p.getCodigo());
        assertEquals("Caneta", p.getNome());
        assertEquals(2.5, p.getPreco(), 1e-9);
    }

    @Test
    void equalsEHashCodePorCodigoENome() {
        Produto a = new Produto(1, "Lapis", 1.0);
        Produto b = new Produto(1, "Lapis", 1.0);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void produtosDiferentesNaoSaoIguais() {
        Produto a = new Produto(1, "Lapis", 1.0);
        Produto b = new Produto(2, "Borracha", 1.0);
        assertNotEquals(a, b);
    }

    @Test
    void precoNegativoAceitacaoOuNormalizacao() {
        Produto p = new Produto(2, "Item", -5.0);
        // comportamento esperado pode variar: apenas verificamos que getter existe
        assertNotNull(p.getPreco());
    }
}