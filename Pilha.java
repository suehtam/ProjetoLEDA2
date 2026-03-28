public class Pilha<T> {
    private ListaEncadeada<T> lista;

    public Pilha() {
        this.lista = new ListaEncadeada<>();
    }

    public void push(T valor) {
        lista.addFirst(valor);
    }

    public T pop() {
        return lista.removeFirst();
    }

    public T peek() {
        return lista.getFirst();
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }
}
