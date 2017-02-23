//ColaVacia.java
public class ColaVacia extends RuntimeException {
    public ColaVacia(){ this("Cola Vacia...");}
    public ColaVacia(String s){ super(s);}   
}
