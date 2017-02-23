//Cola.java
public class Cola <E> {
    private final int size;
    private int tope;
    private int aux;
    private E[] elementos;
    public Cola(){ this(10); }
    public Cola(int n){
        size = n > 0 ? n : 10;
        tope = -1;
        aux = 0;
        elementos = (E[])new Object[size]; 
    }
    public void push(E pushValue){
        if(tope == (size-1))
            throw new ColaLlena();
        else
            elementos[++tope] = pushValue;
    }
    public E pop(){
        if(aux > tope)
            throw new ColaVacia();
        else
            return elementos[aux++];
    }
}