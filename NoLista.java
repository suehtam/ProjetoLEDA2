class NoLista<T> {
    T valor;
    NoLista<T> proximo;

    public NoLista(T valor) {
        this.valor = valor;
        this.proximo = null;
    }
}
