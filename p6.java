//Ejercicio 5. 
/*Se desea implementar el monitor Event con m ́etodos publish y suscribe. Los
threads suscriptores a un evento deben esperar hasta que otro thread publique su ocurrencia,
momento en el cual todos los suscriptores bloqueados contin ́uan su ejecuci ́on. Tenga en cuenta
que un suscriptor siempre debe bloquearse hasta que se ejecute el siguiente publish, es decir,
ignorando las invocaciones a publish previas.
*/

class Event {

    int epoch; 

    public synchronized publish()
        epoch++
        notifyAll()


    public synchronized suscribe() {
        int current = epoch
        while (current == epoch) {
            wait()
        }

    }
}




//Ejercicio 6. 
/*En una red de energ ́ıa inteligente los usuarios conectados pueden comportarse como
consumidores o productores de energ ́ıa (no ambos al mismo tiempo). Para registrar los cambios se
desea utilizar un sistema implementado con un monitor que registre cuando un usuario comienza
a producir, termina de producir, comienza a consumir, y termina de consumir. Por ejemplo, el
siguiente fragmento de c ́odigo muestra un usuario que se comporta como consumidor por una
hora y como productor por 2 horas:
*/
grid.inicioConsumo ();
sleep (1 h );
grid.finConsumo ();
grid.inicioProduccion ();
sleep (2 h );
grid.finProduccion ();



/*Resuelva los siguientes escenarios usando monitores:
a) Los usuarios siempre pueden comportarse como productores, pero s ́olo pueden comportarse
como consumidores si hay una cantidad igual o mayor de productores. Adem ́as se debe
garantizar que un productor no puede dejar de producir si no hay capacidad ociosa en
la red, es decir, no puede dejar de producir si al hacerlo alg ́un consumidor quedar ́ıa sin
suministro.
*/ 

grid = new RedEnergia();
class RedEnergia {
    
    int consumidores = 0
    int productores = 0

    public synchronized inicioConsumo(){
        while consumidores >= productores {
            wait()
        }
        consumidores++
    }

    public synchronized finConsumo(){
        consumidores--
        notifyAll()
    }

    public synchronized inicioProduccion(){
        productores++
        notifyAll()
    }

    public synchronized finProduccion(){
        while (productores == consumidores){
            wait()
        }
        productores--
        
    }


}

class Usuario(Runnable()){

    grid.inicioConsumo();
    sleep (1 h );
    grid.finConsumo();
    grid.inicioProduccion();
    sleep (2 h );
    grid.finProduccion();

}

/*b) Modifique su soluci ́on de forma tal que los usuarios no puedan comportarse como produc-
tores si la red tiene m ́as de N productores ociosos, es decir, que el cambio a productor
genere una espera mientras haya al menos N productores m ́as que consumidores.
*/


grid = new RedEnergia();
class RedEnergia {
    int maxProductoresEnLinea = N
    int consumidores = 0
    int productores = 0

    public synchronized inicioConsumo(){
        while consumidores >= productores {
            wait()
        }
        consumidores++
    }

    public synchronized finConsumo(){
        consumidores--
        notifyAll() // para avisar que un consumidor se bajo entonces chequear la condicion de vuelta
    }

    public synchronized inicioProduccion(){
        while (productores >= maxProductoresEnLinea){
            wait()
        }
        productores++
        notifyAll() // para levantar a los consumidores de que se agrego un productor mas
    }

    public synchronized finProduccion(){
        while (productores == consumidores){
            wait()
        }
        notifyAll() // para habilitar nuevos productores
    }


}

class Usuario(Runnable()){

    grid.inicioConsumo();
    sleep (1 h );
    grid.finConsumo();
    grid.inicioProduccion();
    sleep (2 h );
    grid.finProduccion();

}



/*c) Extienda la soluci ́on anterior de forma tal que se priorice la salida de productores por sobre
la entrada de consumidores nuevos.*/

grid = new RedEnergia();
class RedEnergia {
    int maxProductoresEnLinea = N
    int consumidores = 0
    int productores = 0

    public synchronized inicioConsumo(){
        while consumidores >= productores {
            wait()
        }
        consumidores++
    }

    public synchronized finConsumo(){
        consumidores--
        notifyAll() // para avisar que un consumidor se bajo entonces chequear la condicion de vuelta
    }

    public synchronized inicioProduccion(){
        while (productores >= maxProductoresEnLinea){
            wait()
        }
        productores++
        notifyAll() // para levantar a los consumidores de que se agrego un productor mas
    }

    public synchronized finProduccion(){
        while (productores == consumidores){
            wait()
        }
        notifyAll() // para habilitar nuevos productores
    }


}

class Usuario(Runnable()){

    grid.inicioConsumo();
    sleep (1 h );
    grid.finConsumo();
    grid.inicioProduccion();
    sleep (2 h );
    grid.finProduccion();

}





Ejercicio 7. El comit ́e organizador de una conferencia posee una sala para exposici ́on de charlas.
Las personas que desean asistir a una charla deben tener la posibilidad de indicar que entran en
la sala o que salen de ella, mientras que la persona oradora debe tener la posibilidad de indicar el
inicio de la charla y el final de la misma. Por respeto, los asistentes, si una persona asistente desea
retirarse durante la charla, deber ́a espear a que esta termine para poder salir efectivamente. La
sala tiene capacidad para N personas.
De una soluci ́on usando monitores que modele los siguientes escenarios:
a) Se dispone de una sala para dar repetidas veces la misma charla. Si al momento de querer
iniciar la charla el auditorio est ́a vac ́ıo, la persona oradora esperar ́a a que llegue alguien
para escucharla. Asimismo, si una persona llega y quiere entrar cuando la sala est ́a llena,
deber ́a esperar a que se vaya alguien cuando termine la charla. Notar que si hay lugar, s ́ı se
puede entrar en la sala aunque la charla haya comenzado.
b) Al igual que en el punto a) se dispone de una sala, pero ahora se dan tres charlas distintas
que se rotan (charla 0, charla 1, charla 2, charla 0, . . . ). Cada persona que quiere entrar
en la sala y cada persona que va a dar una charla indica con un par ́ametro su n ́umero de
charla; si una persona quiere entrar a la sala cuando va a empezar (o ya se est ́a dando)
una charla que no le corresponde, debe esperar. Asimismo, una persona que quiere dar una
charla tiene que esperar que sea la de su n ́umero correspondiente. Las acciones de terminar
la charla y de retirarse no llevan par ́ametro, se asume que siempre corresponden a la charla
actual (pero en el caso de retirarse sigue existiendo la restricci ́on de no irse mientras se
est ́e dando una charla, independientemente de cual sea).

2

Programacion Concurrente  ́ Pr ́actica 6: Monitores
Ejercicios para implementar en Java
Ejercicio 8. Implementar lo siguiente utilizando monitores:
a) Un buffer (FIFO) de n ́umeros enteros cuya dimensi ́on est ́a prefijada al momento de su
creaci ́on.
b) Una clase productor (que extienda de Thread) que agregue n ́umeros naturales consecutivos
a un buffer dado al momento de creaci ́on.
c) Una clase consumidor (que extienda de Thread) que muestre por pantalla los valores que
toma de un buffer pasado al momento de su creaci ́on.
d) Un programa que cree un buffer de dimensi ́on 2 y active concurrentemente un consumidor
y un productor.

Ejercicio 9. Se desea implementar el monitor Promise, que representa un c ́omputo que con-
cluir ́a en alg ́un momento y devolver ́a un resultado. El monitor posee los siguientes m ́etodos:

await(), que devuelve el resultado del c ́omputo, bloqueando al thread que lo invoca mien-
tras el resultado no est ́a disponible; y

set(object), que asigna el resultado, desbloqueando a cualquier thread esperando en el
monitor.
Si va a implementar la clase en Java, una buena idea es que Promise implemente la interfaz
Future, que s ́olo declara el m ́etodo await. De esta manera una funci ́on que desea devolver su
resultado de manera asincr ́onica, puede crear un Promise e iniciar un thread para que sete el
valor una vez haya concluido el computo necesario. La funci ́on puede devolver el Promise como
un Future, evitando as ́ı que alguien m ́as pueda setear el valor.
Ejemplo:
Future async ( Object x ) {
promise = new Promise ();
thread {
// computo costo en funcion de x que genera un resultado
promise . set ( resultado );
}
return promise ;
}
¿Qu ́e sucede en su implementaci ́on de Promise si la funci ́on ejecutada de manera asincr ́onica
arroja una excepci ́on? ¿C ́omo puede garantizar que el thread haciendo await pueda manejar la
excepci ́on?
Ejercicio 10. Se requiere implementar lo siguiente:
a) Una lista datos de valores enteros y un monitor AccesoDatos con m ́etodos beginRead,
endRead, beginWrite y endWrite, de forma tal que el uso de datos se gestione por medio
de AccesoDatos. De esta manera, si m ́ultiples threads quieren utilizar datos s ́olo para leer su
valor actual, deben poder hacerlo de manera concurrente, pero si hay uno o m ́as threads
que quieran modificar el valor de la lista, deben hacerlo en exclusi ́on mutua entre ellos y

3

Programacion Concurrente  ́ Pr ́actica 6: Monitores

adem ́as no deben haber threads intentando leer el contenido de la lista cuando alguno se
disponga a modificarla.
b) Una clase Writer que extienda de Thread que agregue alg ́un nuevo valor pasado por
constructor al final de la lista datos.
c) Una clase Reader que extienda de Thread que lea todos los valores actuales de la lista y
los imprima por pantalla.
d) Un programa que cree una instancia de AccesoDatos y active concurrentemente cuatro
escritores (instanciados con alg ́un n ́umero generado al azar) y diez lectores, de manera
que por medio de la instancia de AccesoDatos trabajen con la lista datos con la din ́amica
especificada.
e) Modifique su soluci ́on para que los threads escritores tengan prioridad de acceso a datos.
Ejercicio 11. Dise ̃ne un monitor ThreadPool encargado de administrar la asignaci ́on de tareas
entre m ́ultiples threads Worker, implementando los siguientes puntos:
a) Un monitor ThreadPool, encargado de crear un Buffer de una capacidad total dada (como
el del ejercicio 3), e iniciar una cantidad dada de threads Worker asign ́andole a cada el
mismo Buffer de tareas. El monitor debe implementar un m ́etodo launch que env ́ıa una
tarea a un Worker ocioso encol ́andola en el buffer.
b) Un thread Worker que por siempre resuelve tareas obtenidas de un Buffer dado en su
construcci ́on (asuma que las tareas son un objeto Runnable, es decir, implementa el m ́etodo
run).
c) Una tarea DummyTask (i.e., implementa Runnable) cuyo m ́etodo run simplemente imprime
un string por consola.
d) Una tarea PoisonPill (i.e., implementa Runnable) cuyo m ́etodo run arroja una excepci ́on
de tipo PoisonException cuyo objetivo es terminar la ejecuci ́on de un Worker. Extienda,
adem ́as, la interfaz del ThreadPool con un m ́etodo stop que, luego de agotadas las tareas
encoladas, termina la ejecuci ́on de todos los workers mediante el uso de PosionPills.
e) Un programa que instancia un ThreadPool para administrar 8 threads Worker y lance 100
tareas de tipo DummyTask. Al terminar la ejecuci ́on de las 100 tareas el programa debe
garantizar que todos los threads creados terminan su ejecuci ́on exitosamente.