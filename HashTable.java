public class HashTable<K, V> {
    private EntradaHash<K, V>[] tabela;
    private int capacidade;

    @SuppressWarnings("unchecked")
    public HashTable(int capacidade) {
        this.capacidade = capacidade;
        this.tabela = new EntradaHash[capacidade];
    }
    private int calcularHash(K chave) {
        return (chave.hashCode() & 0x7fffffff) % capacidade;
    }
    public void put(K chave, V valor) {
        if (chave == null) {
            throw new IllegalArgumentException("Chave não pode ser nula");
        }
        int indice = calcularHash(chave);
        EntradaHash<K, V> atual = tabela[indice];

        while (atual != null) {
            if (atual.getChave().equals(chave)) {
                atual.setValor(valor);
                return;
            }
            atual = atual.getProximo();
        }
        EntradaHash<K, V> novaEntrada = new EntradaHash<>(chave, valor);
        novaEntrada.setProximo(tabela[indice]);
        tabela[indice] = novaEntrada;
    }
    public V get(K chave) {
        if (chave == null) {
            throw new IllegalArgumentException("Chave não pode ser nula");
        }
        int indice = calcularHash(chave);
        EntradaHash<K, V> atual = tabela[indice];
        while (atual != null) {
            if (atual.getChave().equals(chave)) {
                return atual.getValor();
            }
            atual = atual.getProximo();
        }
        return null;
    }
    public void remove(K chave) {
            if (chave == null) {
                throw new IllegalArgumentException("Chave não pode ser nula");
            }
            int indice = calcularHash(chave);
            EntradaHash<K, V> atual = tabela[indice];
            EntradaHash<K, V> anterior = null;
    
            while (atual != null) {
                if (atual.getChave().equals(chave)) {
                    if (anterior == null) {
                        tabela[indice] = atual.getProximo();
                    } else {
                        anterior.setProximo(atual.getProximo());
                    }
                    return;
                }
                anterior = atual;
                atual = atual.getProximo();
            }
        }
    }
