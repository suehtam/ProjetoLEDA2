import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListaEncadeadaTest {

    @Test
    void addFirstEremoveFirstOrdemEsperada() {
        ListaEncadeada<String> lista = new ListaEncadeada<>();
        lista.addFirst("A");
        lista.addFirst("B");
        lista.addFirst("C");
        // LIFO para addFirst/removeFirst: C, B, A
        assertEquals("C", lista.removeFirst());
        assertEquals("B", lista.removeFirst());
        assertEquals("A", lista.removeFirst());
        assertTrue(lista.isEmpty());
    }

    @Test
    void getFirstNaoRemoveElemento() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();
        lista.addFirst(10);
        lista.addFirst(20);
        // getFirst deve retornar o primeiro sem remover
        assertEquals(20, lista.getFirst());
        assertFalse(lista.isEmpty());
        // remover confirma que o elemento ainda está lá
        assertEquals(20, lista.removeFirst());
        assertEquals(10, lista.removeFirst());
    }

    @Test
    void isEmptyTransicoes() {
        ListaEncadeada<String> lista = new ListaEncadeada<>();
        assertTrue(lista.isEmpty());
        lista.addFirst("x");
        assertFalse(lista.isEmpty());
        lista.removeFirst();
        assertTrue(lista.isEmpty());
    }

    @Test
    void removeFirstEmListaVaziaLancaRuntimeException() {
        ListaEncadeada<Double> lista = new ListaEncadeada<>();
        assertThrows(RuntimeException.class, lista::removeFirst);
    }

    @Test
    void getFirstEmListaVaziaLancaRuntimeException() {
        ListaEncadeada<Object> lista = new ListaEncadeada<>();
        assertThrows(RuntimeException.class, lista::getFirst);
    }

    @Test
    void aceitaValorNuloEtratamento() {
        ListaEncadeada<String> lista = new ListaEncadeada<>();
        lista.addFirst(null);
        // getFirst deve retornar null sem lançar
        assertNull(lista.getFirst());
        // removeFirst deve retornar null e deixar lista vazia
        assertNull(lista.removeFirst());
        assertTrue(lista.isEmpty());
    }

    @Test
    void operacoesEmSequenciaMaiorVolume() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();
        final int N = 1000;
        for (int i = 0; i < N; i++) {
            lista.addFirst(i);
        }
        // remover todos e verificar ordem LIFO
        for (int i = N - 1; i >= 0; i--) {
            assertEquals(i, lista.removeFirst());
        }
        assertTrue(lista.isEmpty());
    }

    @Test
    void genericsComTiposDiferentes() {
        ListaEncadeada<Integer> intList = new ListaEncadeada<>();
        intList.addFirst(1);
        assertEquals(1, intList.getFirst());

        ListaEncadeada<String> strList = new ListaEncadeada<>();
        strList.addFirst("ok");
        assertEquals("ok", strList.getFirst());
    }
}
