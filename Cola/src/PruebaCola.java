//PruebaCola.java
public class PruebaCola {
    public static void main(String []args){
        Cola<Integer> colaInt;
        Cola<Double> colaDoubles;
        double[] elementos = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9, 1.2};
        
        colaInt = new Cola<Integer>(5);
        colaDoubles = new Cola<Double>(10);

        for(int i = 0; i < 10; i++){
            System.out.print(elementos[i] + "  ");
            colaDoubles.push(elementos[i]);
        }
        System.out.println();
        for(double i = 0; i < 10; i++){
            System.out.println(
            colaDoubles.pop());
        }
        System.out.println();
        for(int i = 1; i <= 5; i++){
            System.out.print(i + " ");
            colaInt.push(i);
        }
        System.out.println();
        for(int i = 1; i <= 5; i++){
            System.out.println(
            colaInt.pop());
        }
        System.out.println();
        
    }
}
