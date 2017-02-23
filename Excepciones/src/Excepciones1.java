//Excepcion.java

import java.util.InputMismatchException;
import java.util.Scanner;
public class Excepciones1 {
    public static int cociente(int a, int b){return a/b;}
    public static void main(String []args){
        int x, y, res; boolean ban = true;
        Scanner in = new Scanner(System.in);
       do{
        try{
        System.out.print("Introducir un entero: ");
        x = in.nextInt();
        System.out.print("Introduce otro entero: ");
        y = in.nextInt();
        res = cociente(x, y);
        System.out.printf("%d / %d = %d\n", x, y, res);
        ban = false;
        }
        catch(InputMismatchException e){
            System.out.print("No es un entero...\n\n");
            in.nextLine();
        }
        catch(ArithmeticException e){
            System.out.print("Division entre cero...\n\n");
        }
       }while(ban);
    }

}
