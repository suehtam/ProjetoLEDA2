public class NoAVL<T extends Comparable<T>> {
    T valor;
    NoAVL<T> esquerda, direita;
    int altura;

    public NoAVL(T valor) {
        this.valor = valor;
        this.altura = 1;
    }
}

public class AVLTree<T extends Comparable<T>> {
    private NoAVL<T> raiz;

    private int altura(NoAVL<T> no) {
        return (no == null) ? 0 : no.altura;
    }

    private int getFatorBalanceamento(NoAVL<T> no) {
        return (no == null) ? 0 : altura(no.esquerda) - altura(no.direita);
    }

    private NoAVL<T> rotacaoDireita(NoAVL<T> y) {
        NoAVL<T> x = y.esquerda;
        NoAVL<T> T2 = x.direita;
        x.direita = y;
        y.esquerda = T2;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        return x;
    }

    private NoAVL<T> rotacaoEsquerda(NoAVL<T> x) {
        NoAVL<T> y = x.direita;
        NoAVL<T> T2 = y.esquerda;
        y.esquerda = x;
        x.direita = T2;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        return y;
    }

    public void insert(T valor) {
        raiz = insertRec(raiz, valor);
    }

    private NoAVL<T> insertRec(NoAVL<T> no, T valor) {
        if (no == null) return new NoAVL<>(valor);

        int comp = valor.compareTo(no.valor);
        if (comp < 0) no.esquerda = insertRec(no.esquerda, valor);
        else if (comp > 0) no.direita = insertRec(no.direita, valor);
        else return no;

        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));
        int balance = getFatorBalanceamento(no);
        if (balance > 1 && valor.compareTo(no.esquerda.valor) < 0) return rotacaoDireita(no);
        if (balance < -1 && valor.compareTo(no.direita.valor) > 0) return rotacaoEsquerda(no);
        if (balance > 1 && valor.compareTo(no.esquerda.valor) > 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (balance < -1 && valor.compareTo(no.direita.valor) < 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }
        return no;
    }

    public T search(T valor) {
        NoAVL<T> res = searchRec(raiz, valor);
        return (res != null) ? res.valor : null;
    }

    private NoAVL<T> searchRec(NoAVL<T> no, T valor) {
        if (no == null || no.valor.compareTo(valor) == 0) return no;
        if (no.valor.compareTo(valor) > 0) return searchRec(no.esquerda, valor);
        return searchRec(no.direita, valor);
    }

    public void remove(T valor) {
        raiz = removeRec(raiz, valor);
    }

    private NoAVL<T> removeRec(NoAVL<T> no, T valor) {
        if (no == null) return no;

        int comp = valor.compareTo(no.valor);
        if (comp < 0) no.esquerda = removeRec(no.esquerda, valor);
        else if (comp > 0) no.direita = removeRec(no.direita, valor);
        else {
            if ((no.esquerda == null) || (no.direita == null)) {
                NoAVL<T> temp = (no.esquerda != null) ? no.esquerda : no.direita;
                if (temp == null) no = null;
                else no = temp;
            } else {
                NoAVL<T> temp = minNode(no.direita);
                no.valor = temp.valor;
                no.direita = removeRec(no.direita, temp.valor);
            }
        }
        if (no == null) return no;

        no.altura = Math.max(altura(no.esquerda), altura(no.direita)) + 1;
        int balance = getFatorBalanceamento(no);

        if (balance > 1 && getFatorBalanceamento(no.esquerda) >= 0) return rotacaoDireita(no);
        if (balance > 1 && getFatorBalanceamento(no.esquerda) < 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (balance < -1 && getFatorBalanceamento(no.direita) <= 0) return rotacaoEsquerda(no);
        if (balance < -1 && getFatorBalanceamento(no.direita) > 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }
        return no;
    }

    private NoAVL<T> minNode(NoAVL<T> no) {
        NoAVL<T> curr = no;
        while (curr.esquerda != null) curr = curr.esquerda;
        return curr;
    }
}
