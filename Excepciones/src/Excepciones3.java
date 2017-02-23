//Excepxiones3.java
public class Excepciones3 {
    public static void main(String []args){
        try{
            System.out.println("Inicia la ejecucion del main. ");
            metodo1();
        }
        catch(Exception e){
            System.out.println("Manejo de excepciones en el main. ");
        }
        finally{
            System.out.println("Finally del main. ");
        }
        System.out.println("Fin del main");
    }
    public static void metodo1()throws Exception{
        try{
            System.out.println("Inicio del metodo1. ");
            metodo2();
        }
        catch(RuntimeException e){
            System.out.println("Excepcion manejada en el metodo1. ");
            throw new Exception("Excepcion 1. ");
        }
        finally{
            System.out.println("Finally del metodo1");
        }
        System.out.println("Fin del metodo1. ");
    }
    public static void metodo2()throws Exception{
        try{
            System.out.println("Inicio del metodo2. ");
            metodo3();
        }
        catch(Exception e){
            System.out.println("Excepcion manejada en el metodo2. ");
            throw new RuntimeException();
        }
        finally{
            System.out.println("Finally del metodo2");
        }
        System.out.println("Fin del metodo2. ");
    }
    public static void metodo3()throws Exception{
        System.out.println("Metodo3. ");
        throw new Exception("Excepcion 3");
    }
    
}
