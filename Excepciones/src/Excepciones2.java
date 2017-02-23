//Exepciones2.java

public class Excepciones2 {
    public static void main(String []args){
        try{ 
            System.out.println("Inicio del main. ");
            metodo1(); 
        }
    catch(Exception e){
    System.out.println("Excepcion manejada en el main."); 
    }
    finally{
    System.out.println("Finally del main.");
        }
    System.out.println("Fin del main");
    }
    static void metodo1()throws Exception{
        try{
            System.out.println("Ejecucion  del metodo1.");
            throw new Exception();
        }
        catch(RuntimeException e){
            System.out.println("Excepcion manejada en el metodo1.");
        }
        finally{
            System.out.println("Finally del metodo1.");
        }
        System.out.println("Fin del metodo1.");
    }
}
