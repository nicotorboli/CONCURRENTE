package p6_imp_java;

public class p8 {
    public static void main(String[] args){
        Buffer buffer = new Buffer(2);
        Productor p = new Productor(buffer, 10);
        Consumidor c = new Consumidor(buffer, 10);
        p.start();
        c.start();
    }

}
/*Ejercicio 8. Implementar lo siguiente utilizando monitores:*/

/*a) Un buffer (FIFO) de n ́umeros enteros cuya dimensi ́on est ́a prefijada al momento de su
creaci ́on.*/

class Buffer{
    int[] buffer;
    int capacidad;
    int contador  = 0;
    int in = 0;
    int out = 0;

    public Buffer(int size){
        this.capacidad = size;
        this.buffer = new int[size];
    }

    public synchronized void agregar(int value){
        while (contador == capacidad){
            try {
                wait();
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        };
        buffer[in] = value;
        in = (in + 1) % capacidad;
        contador++;
        notifyAll();
    }

    public synchronized int remover(){
        while (contador == 0){
            try {
                wait();
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        };
        int value = buffer[out];
        out = (out + 1) % capacidad;
        contador--;
        notifyAll();
        return value;
    }


}



//b) Una clase productor (que extienda de Thread) que agregue n ́umeros naturales consecutivos
//a un buffer dado al momento de creaci ́on.

class Productor extends Thread{
    Buffer buffer;
    int n;

    public Productor(Buffer buffer, int n){
        this.buffer = buffer;
        this.n = n;
    }

    public void run(){
        for (int i = 0; i < n; i++){
            buffer.agregar(i);
        }
    }
}

//c) Una clase consumidor (que extienda de Thread) que muestre por pantalla los valores que
//toma de un buffer pasado al momento de su creaci ́on.

class Consumidor extends Thread{
    Buffer buffer ;
    int n ;

    public Consumidor(Buffer buffer, int n){
        this.buffer = buffer ;
        this.n = n;
    }

    public void run(){
        for (int i = 0; i < n; i++){
            int value = buffer.remover();
            System.out.println(value);
        }
    }
}

//d) Un programa que cree un buffer de dimensi ́on 2 y active concurrentemente un consumidor
//y un productor.


