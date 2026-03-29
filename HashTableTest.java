import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {

    @Test
    void putGetRemoveBasico() {
        HashTable<String, Integer> ht = new HashTable<>();
        ht.put("a", 1);
        assertEquals(1, ht.get("a"));
        ht.put("a", 2);
        assertEquals(2, ht.get("a"));
        ht.remove("a");
        assertNull(ht.get("a"));
    }

    @Test
    void containsKeyESize() {
        HashTable<String, String> ht = new HashTable<>();
        assertEquals(0, ht.size());
        ht.put("k1", "v1");
        ht.put("k2", "v2");
        assertTrue(ht.containsKey("k1"));
        assertTrue(ht.containsKey("k2"));
        assertEquals(2, ht.size());
        ht.remove("k1");
        assertFalse(ht.containsKey("k1"));
    }

    @Test
    void colisoesBasicas() {
        HashTable<Integer, String> ht = new HashTable<>(3); // se existir construtor com capacidade
        ht.put(1, "a");
        ht.put(4, "b"); // 1 e 4 podem colidir em tabelas pequenas
        assertEquals("a", ht.get(1));
        assertEquals("b", ht.get(4));
    }
}