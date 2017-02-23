//PilaVacia.java

public class PilaVacia extends RuntimeException {
    public PilaVacia(){ this("Pila Vacia...");}
    public PilaVacia(String s){ super(s);}   
}
