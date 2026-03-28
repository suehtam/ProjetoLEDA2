public class EntradaHash<K, V> {
    private K chave;
    private V valor;
    private EntradaHash<K, V> proximo;
    public EntradaHash(K chave, V valor) {
        this.chave = chave;
        this.valor = valor;
        this.proximo = null;
    }

    public K getChave() { return chave; }
    public V getValor() { return valor; }
    public void setValor(V valor) { this.valor = valor; }
    public EntradaHash<K, V> getProximo() { return proximo; }
    public void setProximo(EntradaHash<K, V> proximo) { this.proximo = proximo; }
}
