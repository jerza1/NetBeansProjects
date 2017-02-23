// @author jerza
public class matriz {

    public static void main(String[] args){
        int a[][] = new int[10][5];
        int i,j;
        
        for(i = 0; i < 10; i++){
            for(j = 0; j < 5; j++){
                a[i][j] = (int)(Math.random()*20+1); 
                System.out.print(a[i][j]+"\t");
            }System.out.print("\n");
            
        }
    }
}
