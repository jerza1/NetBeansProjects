//PilaLlena.java

public class PilaLlena extends RuntimeException {
    public PilaLlena(){ this("Pila Llena...");}
    public PilaLlena(String s){ super(s);}   
}
