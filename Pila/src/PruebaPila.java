//PruebaPila.java
public class PruebaPila {
    public static void main(String []args){
        Pila<Integer> pilaInt;
        Pila<Double> pilaDoubles;
        double[] elementos = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9, 1.2};
        
        pilaInt = new Pila<Integer>(5);
        pilaDoubles = new Pila<Double>(10);

        for(int i = 0; i < 10; i++){
            System.out.print(elementos[i] + "  ");
            pilaDoubles.push(elementos[i]);
        }
        System.out.println();
        for(double i = 0; i < 10; i++){
            System.out.println(
            pilaDoubles.pop());
        }
        System.out.println();
        
        for(int i = 1; i < 5; i++){
            System.out.print(i + " ");
            pilaInt.push(i);
        }
        System.out.println();
        for(int i = 1; i < 5; i++){
            System.out.println(
            pilaInt.pop());
        }
        System.out.println();
        
    }
}
