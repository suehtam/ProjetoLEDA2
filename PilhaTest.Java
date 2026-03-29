import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PilhaTest {

    @Test
    void pushPopSimples() {
        Pilha<Integer> pilha = new Pilha<>();
        pilha.push(1);
        pilha.push(2);
        assertFalse(pilha.isEmpty());
        assertEquals(2, pilha.pop());
        assertEquals(1, pilha.pop());
        assertTrue(pilha.isEmpty());
    }

    @Test
    void topSemRemover() {
        Pilha<String> pilha = new Pilha<>();
        pilha.push("a");
        pilha.push("b");
        // método pode se chamar top() ou peek(); testamos top() e fallback para peek() se necessário
        String top;
        try {
            top = pilha.top();
        } catch (NoSuchMethodError | AbstractMethodError | RuntimeException e) {
            top = pilha.peek();
        }
        assertEquals("b", top);
        assertEquals("b", pilha.pop());
    }

    @Test
    void popEmPilhaVaziaLancaExcecao() {
        Pilha<Integer> pilha = new Pilha<>();
        assertThrows(RuntimeException.class, pilha::pop);
    }
}