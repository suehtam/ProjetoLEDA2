public class Queue<T> {
    private Pilha<T> pilhaEntrada;
    private Pilha<T> pilhaSaida;

    public Queue() {
        this.pilhaEntrada = new Pilha<>();
        this.pilhaSaida = new Pilha<>();
    }

    public void enqueue(T value) {
        pilhaEntrada.push(value);
    }

    public T dequeue() {
        // SÓ transferimos se a saída estiver vazia para preservar a ordem FIFO
        if (pilhaSaida.isEmpty()) {
            if (pilhaEntrada.isEmpty()) {
                throw new RuntimeException("Fila vazia!");
            }
            
            // Move tudo da entrada para a saída invertendo a ordem
            while (!pilhaEntrada.isEmpty()) {
                pilhaSaida.push(pilhaEntrada.pop());
            }
        }
        
        return pilhaSaida.pop();
    }

    public boolean isEmpty() {
        return pilhaEntrada.isEmpty() && pilhaSaida.isEmpty();
    }
}
