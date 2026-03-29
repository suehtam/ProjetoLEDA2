class ListaEncadeada<T> {
    private NoLista<T> head;
    
    public ListaEncadeada() {
        this.head = null; 
    }

    public void addFirst(T valor) {
        NoLista<T> novoNo = new NoLista<>(valor);
        novoNo.proximo = head;
        head = novoNo;
    }

    public T removeFirst(){
        if(isEmpty()){
            throw new RuntimeException("Lista vazia!");
        }
        T valor = head.valor;
        head = head.proximo;
        return valor;
    }

    public T getFirst(){
        if(isEmpty()){
            throw new RuntimeException("Lista vazia!");
        }
        return head.valor;
    }
    public boolean isEmpty(){
        return head == null;
    }

}
