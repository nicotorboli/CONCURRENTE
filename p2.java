//Ejercicio 2. Considere la siguiente propuesta para garantizar exclusi ́on Mutua con 2 threads:

global boolean [] flags = {false , false};
thread {
id = 0;
// seccion no critica
otro = ( id + 1) % 2;
while ( flags [ otro ]);
flags [ id ] = true;
// seccion critica
flags [ id ] = false;
// seccion no critica
}

thread {
id = 1;
// seccion no critica
otro = ( id + 1) % 2;
while ( flags [ otro ]);
flags [ id ] = true;
// seccion critica
flags [ id ] = false;
// seccion no critica
}

//a) Explique por qu ́e, en este caso, s ́ı hay Garant ́ıa de Entrada.
hay garantia de entrada porque la condicion de en trada es que el otro 
no este en la seccion critica, entonces si uno falla el otro va a poder entrar 


//b) Muestre que no cumple Mutex.
no cumple mutex ya su si ambos leen intercalados las lineas decodigo, entonces ambos entraran
a la seccion critica



//Ejercicio 4. Considere la siguiente propuesta para resolver el problema de exclusi ́on mutua para
//2 procesos que utiliza las variables compartidas turno y flags:


global int turno = 0;
global boolean [] flags = {false , false};
thread {
id = 0; // o 1 resp .
// seccion no critica
otro = ( id + 1) % 2;
flags [ id ] = true;
while ( flags [ otro ])
if ( turno == otro ) {
flags [ id ] = false;
while ( turno != id );
flags [ id ] = true;
}
// SECCION CRITICA
turno = otro ;
flags [ id ] = false;
// seccion no critica
}

//Indique si esta propuesta resuelve el problema de exclusi ́on mutua, 
// explicitando cu ales con-
//diciones se cumplen y cu ́ales no 
// (justificando o mostrando una traza seg ́un corresponda).

id = 0; // o 1 resp .
// seccion no critica
otro = ( id + 1) % 2;
flags [ id ] = true;

no cumple exclusion mutua porque no cumple garantia de entrada es decir si ambos 
ejecutan esas 3 lineas de codigo, al leer el while cualquiera de los 2, estos se 
quedaran loopeano
  


global boolean[] flags = replicate(n,false);
global int turno = 0;

// seccion no critica
flags[id] = true ;
siguiente = (id + 1) % n;
turno = siguiente;
while (flags[siguiente] && turno == siguiente);
// seccion critica
flags[id] = false;
// seccion no critica


Justificacion

¿Al menos un thread entra? Si. Turno siempre toma algun valor de id valido y las variables siguientes son
todas distintas, luego la condicion del while no puede ser cierta para todos al mismo tiempo.
¿Cada vez que algun thread entra a la seccion critica habilita al menos a un proximo? Si, por hipotesis todo
thread termina su seccion critica y luego setea su flag a false. Esto hace que, aunque el turno no se modifique, si
algun thread estaba esperando en el while por el thread saliente, ya no lo haga mas pues una de las condicciones
del and es falsa. Por otro lado, por la misma razon, cualquier thread que llegue luego y tenga como siguiente
al thread saliente no necesitara esperar.
Obs: En particular, para todo thread Ti existe unico Tj que lo tiene como siguiente (Tj.siguiente == Ti)
Finalmeente, combinando estas dos ideas concluimos que todos los threads eventualmente entran
*/

  /*Ejercicio 6. Dado el algoritmo de Bakery, muestre que la condici ́on j < id en el segundo while
es necesaria. Es decir, muestre que el algoritmo que se obtiene al eliminar esta condici ́on no
resuelve el problema de la exclusi ́on mutua. Mencione al menos una propiedad que esta variante
del algoritmo no cumple y muestre una traza de ejecuci ́on que lo evidencie. Por comodidad,
damos a continuaci ́on el algoritmo modificado (donde se elimin ́o la condici ́on j < id)
*/
global boolean [] entrando = replicate (N ,false);
global int[] numero = replicate (N ,0);
thread {
    // seccion no critica
    entrando [ threadId ] = true;
    numero [ threadId ] = 1 + maximum ( numero );
    entrando [ threadId ] = false;
    for ( j : range (0 ,n -1)) {
        while ( entrando [ j ]);
        while ( numero [ j ] != 0 && ( numero [ j ] < numero [ threadId ] || ( numero [ j ] == numero [ threadId ])));
    }
    // seccion critica
    numero [ threadId ] = 0;
    // seccion no critica
}

