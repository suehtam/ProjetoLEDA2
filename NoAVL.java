public class NoAVL<T extends Comparable<T>> {
    T valor;
    NoAVL<T> esquerda;
    NoAVL<T> direita;
    int altura;

    public NoAVL(T valor) {
        this.valor = valor;
        this.altura = 1; 
    }
}
