package p6_imp_java;
import java.util.ArrayList;
import java.util.List; 

public class p11 {
    
}


/*Ejercicio 11. Dise ̃ne un monitor ThreadPool encargado de administrar la asignaci ́on de tareas
entre m ́ultiples threads Worker, implementando los siguientes puntos:*/
/*a) Un monitor ThreadPool, encargado de crear un Buffer de una capacidad total dada (como
el del ejercicio 3), e iniciar una cantidad dada de threads Worker asign ́andole a cada el
mismo Buffer de tareas. El monitor debe implementar un m ́etodo launch que env ́ıa una
tarea a un Worker ocioso encol ́andola en el buffer.*/


class ThreadPool{
    private Buffer buffer;
    private Thread[] workers;

    public ThreadPool(int capacidad_buffer, int cantThreads){
            this.buffer = new Buffer(capacidad_buffer);
            this.iniciarWorkers(cantThreads);
    }
    public synchronized void launch(Task tarea){
            if hay_worker_oscioso{
                worker_oscioso.setTarea(tarea);
                buffer.add(worker_oscioso);
            }
            else { 
                wait();            
            }
    }

    private void iniciarWorkers(int cantThreads){
        workers = new Thread[cantThreads];
        for (int i = 0; i < cantThreads; i++) {
            workers[i] = new Worker(this.buffer);
            workers[i].start();
        }
    }
}

class Worker extends Thread{
    private Buffer buffer;

    public Worker(Buffer buffer){
        this.buffer = buffer;
    }

    public setTarea

}

/*b) Un thread Worker que por siempre resuelve tareas obtenidas de un Buffer dado en su
construcc/i ́on (asuma que las tareas son un objeto Runnable, es decir, implementa el m ́etodo
run).*/


/*c) Una tarea DummyTask (i.e., implementa Runnable) cuyo m ́etodo run simplemente imprime
un string por consola.*/


/*d) Una tarea PoisonPill (i.e., implementa Runnable) cuyo m ́etodo run arroja una excepci ́on
de tipo PoisonException cuyo objetivo es terminar la ejecuci ́on de un Worker. Extienda,
adem ́as, la interfaz del ThreadPool con un m ́etodo stop que, luego de agotadas las tareas
encoladas, termina la ejecuci ́on de todos los workers mediante el uso de PosionPills.*/


/*e) Un programa que instancia un ThreadPool para administrar 8 threads Worker y lance 100
tareas de tipo DummyTask. Al terminar la ejecuci ́on de las 100 tareas el programa debe
garantizar que todos los threads creados terminan su ejecuci ́on exitosamente.*/