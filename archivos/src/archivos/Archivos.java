package archivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author jerza
 */
public class Archivos {
     static int inf = Integer.MAX_VALUE;
     static int max = 1000;
     static int[] distancia = new int[max];
     static boolean[] visitado = new boolean[max];
     static int[] padre = new int[max];
     static Cola prio = new Cola();
     static int matriz[][] = null;
     static int ady[][] = null;
     static int V[] = null;
    public static void main(String[] args) {
        FileReader arch = null;
        try{
            arch = new FileReader("archivo1.txt");
            BufferedReader entrada = new BufferedReader(arch);
            String carac;
            int nodo =  0,aris = 0 ,origen , dest;
            int i = 0, fila = 0, col = 0;
            
            while((carac = entrada.readLine()) != null){
                String []a = carac.split(" ");
                if(i == 0){
                    nodo = Integer.parseInt(a[0]);
                    aris = Integer.parseInt(a[1]);
                    matriz = new int[aris][3];
                    i++;  
                }else{
                    matriz[i-1][0] = Integer.parseInt(a[0]);
                    matriz[i-1][1] = Integer.parseInt(a[1]);
                    matriz[i-1][2] = Integer.parseInt(a[2]);
                    i++;
                } 
            } V = new int[nodo];
            for(fila = 0; fila < nodo; fila++){
                    distancia[fila] = inf;
                    visitado[fila] = false;
                    padre[fila] = -1;
                    V[fila] = fila+1;
            }
            prio.insertar(V[0],0,distancia[0],0);
            distancia[V[0]] = 0;
            System.out.println(V[0]+","+distancia[V[0]]);
            /*int actual,adyacente,peso,numAdy = 0,arista,aux;
            while(!prio.vacia()){
                actual = prio.extraer();
                aux = actual-1;
                if(visitado[actual-1]){ continue;}
                visitado[actual-1] = true;
                arista = matriz[actual-1][0];
                while(matriz[aux++][0] == arista){numAdy += 1;}
                for(i = 0; i < numAdy; i++){
                    adyacente = matriz[actual-1][1];
                    peso = matriz[actual-1][2];
                    if(!visitado[adyacente]){
                        relajacion(actual-1,adyacente,peso);
                    }
                  }
                }
            for(fila = 0; fila < nodo; fila++){
                    System.out.println(distancia[fila]);
                    System.out.println(visitado[fila]);
                    System.out.println(padre[i]);
            }*/
            entrada.close();
	}catch(FileNotFoundException e){
            System.out.println(e.getMessage());
	}catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static void relajacion(int actual, int adyacente, int peso) {
        if(distancia[actual] + peso < distancia[adyacente]){
            distancia[adyacente] = distancia[actual] + peso;
            padre[adyacente] = actual;
            prio.insertar(matriz[actual][2], distancia[adyacente],0,0);
        }
    }
public static class Cola {
    
    class Nodo {
        int tope;
        Nodo sig;
    }
    
    private Nodo padre,hijo;
    
    Cola() {
        padre=null;
        hijo=null;
    }
    
    boolean vacia (){
        if (padre == null)
            return true;
        else
            return false;
    }

    void insertar (int nodo1, int nodo2,int dist1, int dist2)
    {
        Nodo primero, segundo;
        primero = new Nodo();
        segundo = new Nodo();
        primero.tope = nodo1;
        primero.sig = null;
        segundo.tope = nodo2;
        segundo.sig = null;
        if (vacia ()) {
            if(dist2 < dist1){
            padre = primero;
            hijo = primero;
            }else{ 
                padre = segundo;
                hijo = segundo;
            }
        } else {
            if(primero.tope < segundo.tope){
            hijo.sig = primero;
            hijo = primero;
            }else{
            hijo.sig = segundo;
            hijo = segundo;
            }
        }
    }

    int extraer ()
    {
        if (!vacia ())
        {
            int informacion = padre.tope;
            if (padre == hijo){
                padre = null;
                hijo = null;
            } else {
                padre = padre.sig;
            }
            return informacion;
        } else
            return max;
    }
}
}