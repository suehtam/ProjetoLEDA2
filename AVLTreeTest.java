import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class AVLTreeTest {

    @Test
    void inserirEConter() {
        AVLTree<Integer> avl = new AVLTree<>();
        avl.insert(10);
        avl.insert(20);
        avl.insert(5);
        assertTrue(avl.contains(10));
        assertTrue(avl.contains(20));
        assertTrue(avl.contains(5));
        assertFalse(avl.contains(999));
    }

    @Test
    void rotacaoDireitaEsquerda() {
        AVLTree<Integer> avl = new AVLTree<>();
        // inserir em ordem que cause rotações: 10, 20, 30 (rotacao esquerda)
        avl.insert(10);
        avl.insert(20);
        avl.insert(30);
        int h = avl.height();
        assertTrue(h <= 2); // altura balanceada deve ser pequena
    }

    @Test
    void ordemEmOrdemRetornaOrdenado() {
        AVLTree<Integer> avl = new AVLTree<>();
        avl.insert(3);
        avl.insert(1);
        avl.insert(2);
        List<Integer> in = avl.inOrder(); // método comum
        assertEquals(List.of(1,2,3), in);
    }

    @Test
    void removerElemento() {
        AVLTree<Integer> avl = new AVLTree<>();
        avl.insert(2);
        avl.insert(1);
        avl.insert(3);
        avl.remove(2);
        assertFalse(avl.contains(2));
        List<Integer> in = avl.inOrder();
        assertEquals(List.of(1,3), in);
    }
}