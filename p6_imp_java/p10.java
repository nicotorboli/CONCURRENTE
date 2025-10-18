package p6_imp_java;
import java.util.ArrayList;
import java.util.List; 
import java.util.Random;

public class p10 {
        static List<Integer> datos = new ArrayList<Integer>(2);
        static AccesoDatos accesoDatos = new AccesoDatos();

        public static void main(String[] args) {

        Thread[] escritores = new Thread[4];
        Thread[] lectores = new Thread[10];
        
        Random random = new Random();


        for (int i = 0; i < 4; i++) {
            int numeroAleatorio = random.nextInt(100); 
            escritores[i] = new Writer(numeroAleatorio);
            escritores[i].start();
        }
        
        // Crear e iniciar 10 lectores
        for (int i = 0; i < 10; i++) {
            lectores[i] = new Reader();
            lectores[i].start();
        }
    }
}



/*Ejercicio 10. Se requiere implementar lo siguiente:
a) Una lista datos de valores enteros y un monitor AccesoDatos con m ́etodos beginRead,
endRead, beginWrite y endWrite, de forma tal que el uso de datos se gestione por medio
de AccesoDatos. De esta manera, si m ́ultiples threads quieren utilizar datos s ́olo para leer su
valor actual, deben poder hacerlo de manera concurrente, pero si hay uno o m ́as threads
que quieran modificar el valor de la lista, deben hacerlo en exclusi ́on mutua entre ellos y
adem ́as no deben haber threads intentando leer el contenido de la lista cuando alguno se
disponga a modificarla.
*/
class AccesoDatos{
    
    private int readers = 0;
    private int writers = 0;
    private int waitingWriters = 0;

    public synchronized void beginRead() {
        while (writers != 0 && waitingWriters != 0) {
            wait();
        }
        readers = readers + 1; 
        //leer()
    }

    public synchronized void endRead(){
        readers = readers - 1; 
        if (readers == 0 ) {
            notifyAll();
        }
        
    }
    public synchronized void beginWrite(){
        waitingWriters = waitingWriters + 1; 
        while (readers != 0 && writers != 0 ){
            wait();
        }    
        waitingWriters = waitingWriters - 1;
        writers = 1;  
    }
    public synchronized void endWrite(){
        writers = 0;
        notifyAll();
        
    }




}
/*b) Una clase Writer que extienda de Thread que agregue alg ́un nuevo valor pasado por
constructor al final de la lista datos.*/

class Writer extends Thread{
    int dato;

    public Writer(int dato){
        this.dato = dato;
    }       
    
    public void escribir(){
        accesoDatos.beginWrite();
        p10.datos.add(this.dato);
        accesoDatos.endWrite();
    }
    
} 
/*c) Una clase Reader que extienda de Thread que lea todos los valores actuales de la lista y
los imprima por pantalla.*/

class Reader extends Thread{
    int dato;

    public Reader(){

    }       
    
    public void leer(){
        accesoDatos.beginRead();
        for (Integer i : p10.datos) {
            System.err.println(i);
        } ;
        accesoDatos.endRead();

    }
    
} 


/*d) Un programa que cree una instancia de AccesoDatos y active concurrentemente cuatro
escritores (instanciados con alg ́un n ́umero generado al azar) y diez lectores, de manera
que por medio de la instancia de AccesoDatos trabajen con la lista datos con la din ́amica
especificada.*/




/*e) Modifique su soluci ́on para que los threads escritores tengan prioridad de acceso a datos.*/