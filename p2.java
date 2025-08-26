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
  