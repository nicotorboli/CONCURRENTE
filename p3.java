//Ejercicio 1. Considere el siguiente fragmento de codigo (donde la variable x comienza iniciali-
//zada en cero):

thread T1 : {
while( entrar ());
x = x + 1;
salir ();
}

thread T2 : {
while( entrar ());
x = x + 2;
salir ();
}

thread T3 : {
while( entrar ());
x = x + 3;
salir ();
}

//a) Dadas las siguientes implementaciones triviales de las funciones entrar y salir, determi-
//nar todos los valores posibles de la variable x al final de la ejecuci ́on de los threads.

entrar () {
return false;
}

salir () { }

valores posibles de x = 6 
valores posibles de x = 1 
valores posibles de x = 2
valores posibles de x = 3
valores posibles de x = 4 
valores posibles de x = 5 


//b) Considere ahora las siguientes implementaciones de las funciones entrar y salir que

//utilizan la variable booleana global ocupado (inicializada en false) e indique si al usar-
//las se delimita correctamente una secci ́on cr ́ıtica (en caso contrario indicar al menos una

//propiedad que no se cumpla y mostrar una traza que ejemplifique el problema).
entrar () {
if ( ocupado )
    return true;
    ocupado = true;
    return false;
}

salir () {
ocupado = false;
}

(t1)if ( ocupado )
(t1)ocupado = true;
(t2)// andaria todo bien 


(t1)if ( ocupado )    // ocupado = false 
(t2)if ( ocupado )    // ocupado = false 
(t1)ocupado = true;
(t1)return false;
(t2)ocupado = true;
(t2)return false;


no se cumple mutex porque ambas trazas entrarian a la seccion critica 

//c) Analice el problema considerando que las funciones entrar y salir son at ́omicas ¿Se
//resuelve en este caso el problema de la exclusi ́on mutua? Justifique su respuesta.

si, se cumple mutex ya que para cualquier thread que entre, ya que al ser la funcion entrar atomica ningun otro thread va a poder entrar hasta que este termine


/*Ejercicio 2. Considere la siguiente propuesta para resolver el problema de exclusi ́on mutua para
3 threads que utiliza las operaciones at ́omicas PedirTurno y LiberarTurno:
*/

global int actual = 0 , turnos = 0;
PedirTurno () {
int turno = turnos ;
turnos = turnos + 1;
return turno ;
}

LiberarTurno () {
turnos = turnos - 1;
}

//Considere, tambi ́en, que cada thread ejecuta el siguiente protocolo:


// seccion no critica
miTurno = PedirTurno ();
while ( actual != miTurno );
// seccion critica
LiberarTurno ();
// seccion no critica
/*Muestre que esta propuesta no resuelve el problema de la exclusi ́on mutua. Explique cu ́ales
condiciones se cumplen y cu ́ales no, justificando o mostrando una traza seg ́un corresponda.
*/

(t1) = PedirTurno ()        // turnos = 1  turno = 0
(t2) = PedirTurno ()        // turnos = 2  turno = 1
(t1) = while ( actual != miTurno ); // entra y sale de SC
(t1) = LiberarTurno ();     // turnos = 1
(t3) = PedirTurno ()        // turnos = 2  turno = 1

no se cumple la exclusion mutua ya que no se cumple la garantia de entrada ya que en la traza anterior 
actual que es igual a 0 siempre va a ser diferente a 1 que son los turno de los otros 2 threads que no terminaron 



/*Ejercicio 3. Considere la siguiente operaci ́on:*/
tomarFlag ( mia , otro ) {
flags [ mia ] = ! flags [ otro ];
}
Se propone el siguiente algoritmo para resolver el problema de la exclusi ́on mutua entre dos
procesos que utilizan el array compartido:
global boolean [] flags = {false , false};
thread { // threadId =0
while (! flags [0])
tomarFlag (0 ,1);
// seccion critica
flags [0] = false;
}

thread { // threadId =1
while (! flags [1])
tomarFlag (1 ,0);
// seccion critica
flag [1] = false;
}


/*
Responda si la propuesta anterior resuelve el problema de la exclusi ́on mutua en los siguientes
casos:
a) La operaci ́on tomarFlag no es atomica.
b) La operaci ́on tomarFlag s ́ı es at ́omica. 
*/
si no es atomica se puede romper en la lectura y escritura de variables
(t1)while (! flags [0])         // flags[0] = false, !false 
(t2)while (! flags [1])         // flags[0] = false, !false
(t1)tomarFlag (0 ,1);           // flags [ 0] = ! flags [ 1 ]; local: true 
(t2)tomarFlag (1 ,0);           // flags [ 1] = ! flags [ 0 ]; local: true
(t2)tomarFlag (0 ,1);           // flags [ 1] = ! flags [ 0]; local: true ; flags [0] = local 
(t1)tomarFlag (1 ,0);           // flags [ 0] = ! flags [ 1 ]; local: true ; flags [1] = local 


Ejercicio 4. Considere la siguiente operaci ́on at ́omica que implementa un swap entre dos refe-
rencias booleanas:

void Exchange ( Ref sref , Ref lref ) {
temp = sref . value ;
sref . value = lref . value ;
lref . value = temp ;
}
Y la siguiente propuesta para resolver el problema de la exclusi ́on mutua:
global Ref shared = new Ref (false);
thread {
Ref local = new Ref (true);
// seccion no critica
do { Exchange ( shared , local ); } while ( local . value );
// seccion critica
shared . value = false;
// seccion no critica
}

2

Programacion Concurrente  ́ Pr ́actica 3: Atomicidad

Indique si la propuesta resuelve el problema de la exclusi ́on mutua para una cantidad no
acotada de threads. Justifique (en caso negativo indicar que condici ́on no se cumple y mostrar
una traza). Recuerde analizar si la soluci ́on es libre de starvation.
Nota: Tenga en cuenta que la estructura de control do-while ejecuta el cuerpo del ciclo al
menos una vez, y luego verifica la condici ́on antes de repetir la ejecuci ́on.
Ejercicio 5. Considere el siguiente fragmento de c ́odigo:
global int ticket = 0;
global int turn = 0;
thread T1 : {
// non - critical section
int myTurn = getTurn ();
while ( myTurn != turn );
// critical section
releaseTurn ();
// non - critical section
}

thread T2 : {
// non - critical section
int myTurn = getTurn ();
while ( myTurn != turn );
// critical section
releaseTurn ();
// non - critical section
}

Dadas las siguientes implementaciones de las funciones getTurn y releaseTurn.
getTurn () {
return ticket ++;
}

releaseTurn () {
turn ++;
}

a) Determine si al utilizar las funciones getTurn y releaseTurn (no at ́omicas) se delimita
correctamente una secci ́on cr ́ıtica (en caso contrario indicar al menos una propiedad que
no se cumpla y mostrar una traza que ejemplifique el problema).
b) Ahora considere como at ́omicas las funciones getTurn y releaseTurn e indique si en este
caso se resuelve el problema de la exclusi ́on mutua.
c) Consiedere si la respuesta se mantiene en caso de que se tenga una cantidad no acotada
de threads.
d) Si esta soluci ́on se ejecuta en un entorno donde los enteros son representados con un byte
(8 bits), es decir, el n ́umero sin signo m ́as significativo representable es 255, ¿Afecta esto
su respuesta anterior?
Ejercicio 6. Considere la operaciones at ́omicas push, peek y pop que implementan una cola
FIFO sobre un array de n posiciones. Es decir, push agrega un elemento al final de la cola, pop
remueve el elemento al principio de la cola, y peek retorna el elemento al principio de la cola
sin removerlo. M ́as el siguiente algoritmo para resolver el problema de la exclusi ́on mutua para
n threads, que utiliza la variable compartida queue.
global int[] queue = new int[ n ];
thread {
id = 1; // ids de 1 a n
// seccion no critica

3

Programacion Concurrente  ́ Pr ́actica 3: Atomicidad

push ( queue , id );
while ( id != peek ( queue ));
// SECCION CRITICA
pop ( queue );
// seccion no critica
}
Indique si el algoritmo resuelve el problema de la exclusi ́on mutua para n threads. Justifique
(en caso negativo indicar que condici ́on no se cumple y mostrar una traza).
Ejercicio 7. Considere la siguiente propuesta para resolver el problema de exclusi ́on mutua para
N threads:


global LLSC lock = new LLSC (false , -1);

thread {
    // Seccion No Critica
    LLSC local ;
    do {
        do { local = lock . loadLink (); }
        while ( local . flag );
    } while (! lock . storeConditional ( local , true ));
    // Seccion Critica
    lock . reset ();
    // Seccion No Critica
}


Dada la siguiente implementacion de la clase LLSC.
class LLSC {
    private boolean flag ;
    private long timestamp ;
    public LLSC (boolean flag , long timestamp ) {
        this. flag = flag ;
        this. timestamp = timestamp ;
    }

    public LLSC loadLink () {
        this. timestamp = System . currentTimeMillis ();
    return new LLSC (this. flag , this. timestamp );
    }
    
    public boolean storeConditional ( LLSC other , boolean value ) {
        if (this. timestamp != other . timestamp )
        return false;
        this. flag = value ;
    return true;
    }
    
    public void reset () {
        this. flag = false;
    }
}

Asumiendo que System.currentTimeMillis() nunca retorna dos veces el mismo valor, res-
ponda:

a) Muestre que la propuesta no cumple la propiedad de Mutex.
b) Muestre que la propuesta no cumple la propiedad de Garantia de Entrada.

c) ¿Cambian en algo sus respuestas si loadLink y storeConditional son at ́omicas? Justifi-
que apropiadamente.