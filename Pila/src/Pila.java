//Pila.java
public class Pila <E> {
    private final int size;
    private int tope;
    private E[] elementos;
    public Pila(){ this(10); }
    public Pila(int n){
        size = n > 0 ? n : 10;
        tope = -1;
        elementos = (E[])new Object[size]; 
    }
    public void push(E pushValue){
        if(tope == (size-1))
            throw new PilaLlena();
        else
            elementos[++tope] = pushValue;
    }
    public E pop(){
        if(tope == -1)
            throw new PilaVacia();
        else
            return elementos[tope--];
    }
}
