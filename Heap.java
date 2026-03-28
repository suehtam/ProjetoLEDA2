import java.util.Comparator;

public class Heap<T> { 
    private T[] array;
    private int tamanho;
    private Comparator<T> comparador;

    @SuppressWarnings("unchecked") 
    public Heap(int capacidadeMax, Comparator<T> comparador) {
        this.array = (T[]) new Object[capacidadeMax]; 
        this.tamanho = 0;
        this.comparador = comparador; // Guarda a regra de comparação
    }

    public void insert(T valor) {
        if (tamanho == array.length) {
            throw new RuntimeException("Heap cheia!");
        }
        array[tamanho] = valor;
        heapifyUp(tamanho);
        tamanho++;
    }

    public T remove() {
        if (tamanho == 0) {
            throw new RuntimeException("Heap vazia!");
        }
        T raiz = array[0];
        array[0] = array[tamanho - 1];
        tamanho--;
        heapify(0);

        return raiz;
    }

    private void heapify(int index) {
        int menor = index;
        int esquerdo = 2 * index + 1;
        int direito = 2 * index + 2;
        if (esquerdo < tamanho && comparador.compare(array[esquerdo], array[menor]) < 0) {
            menor = esquerdo;
        }
        if (direito < tamanho && comparador.compare(array[direito], array[menor]) < 0) {
            menor = direito;
        }
        if (menor != index) {
            swap(index, menor);
            heapify(menor);
        }
    }

    private void heapifyUp(int index) {
        int pai = (index - 1) / 2;
        if (index > 0 && comparador.compare(array[index], array[pai]) < 0) {
            swap(index, pai);
            heapifyUp(pai);
        }
    }

    private void swap(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
