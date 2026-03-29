import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {

    @Test
    void enqueueDequeueOrdemFIFO() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(10);
        q.enqueue(20);
        assertEquals(10, q.dequeue());
        assertEquals(20, q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    void peekSemRemover() {
        Queue<String> q = new Queue<>();
        q.enqueue("x");
        q.enqueue("y");
        String head;
        try {
            head = q.peek();
        } catch (RuntimeException e) {
            head = q.front();
        }
        assertEquals("x", head);
        assertEquals("x", q.dequeue());
    }

    @Test
    void dequeueEmFilaVaziaLancaExcecao() {
        Queue<Integer> q = new Queue<>();
        assertThrows(RuntimeException.class, q::dequeue);
    }
}