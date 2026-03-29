import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeapTest {

    @Test
    void inserirERemoverMaxHeap() {
        Heap<Integer> heap = new Heap<>(); // assume max-heap padrão
        heap.insert(5);
        heap.insert(10);
        heap.insert(3);
        assertEquals(10, heap.remove());
        assertEquals(5, heap.remove());
        assertEquals(3, heap.remove());
        assertTrue(heap.isEmpty());
    }

    @Test
    void peekSemRemover() {
        Heap<Integer> heap = new Heap<>();
        heap.insert(7);
        heap.insert(2);
        assertEquals(7, heap.peek());
        assertEquals(7, heap.remove());
    }

    @Test
    void tamanhoEEstado() {
        Heap<Integer> heap = new Heap<>();
        assertTrue(heap.isEmpty());
        heap.insert(1);
        assertFalse(heap.isEmpty());
        assertEquals(1, heap.size());
    }
}