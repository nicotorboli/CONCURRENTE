Ejercicio 1. Dados los siguientes threads

global Semaphore puedeF = new Semaphore(0);
global Semaphore puedeC = new Semaphore(0);

thread {
print ( ’A ’ );
puedeF.release();
print ( ’B ’ );
puedeC.adquire();
print ( ’C ’ );
}cq

thread {
print ( ’E ’ );
puedeF.adquire();
print ( ’F ’ );
puedeC.release();
print ( ’G ’ );
}

Utilizar sem ́aforos para garantizar que, simult ́aneamente, ‘A’ se muestra antes que ‘F’, y ‘F’
se muestra antes que ‘C’.

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Ejercicio 2. Dados los siguientes threads:
Semaphore puedeR = new Semaphore(0);
Semaphore puedeC = new Semaphore(0);
Semaphore puedeO = new Semaphore(0);

thread t1 {
   puedeC.acquire();
   print ( " C " );
   puedeR.release();
   print ( " E " );
   puedeO.release();
}

thread t2 {
   print ( ’A ’ );
   puedeC.release();
   puedeR.acquire();
   print ( ’R ’ );
   puedeO.acquire()
   print ( ’O ’ );
}
Utilizar sem ́aforos para garantizar que las  ́unicas salidas posibles sean ACERO y ACREO.

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Ejercicio 3. Considerar los siguientes tres threads:

Semaphore puedeI = new Semaphore(0);
Semaphore puedeO = new Semaphore(0);
Semaphore puedeOK = new Semaphore(0);

thread {
print ( " R " );
puedeI.release();
puedeOK.acquire();
print ( " OK " );
}

thread {
puedeI.acquire();    
print ( " I " );
puedeO.release();
puedeOK.acquire();
print ( " OK " );
}

thread {
puedeO.acquire(); 
print ( " O " );
puedeOK.release();
puedeOK.release();
print ( " OK " );
}

Utilizar sem ́aforos para garantizar que el  ́unico resultado impreso ser ́a R I O OK OK OK
(asumimos que print es at ́omico).

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Ejercicio 4. Dados los siguientes threads:

Semaphore puedeF = new Semaphore(0);
Semaphore puedeO = new Semaphore(0);
Semaphore puedeOK = new Semaphore(0);

thread
while (true) {
print ( ’A ’ );
puedeF.release();
print ( ’B ’ );
puedeC.adquire();
print ( ’C ’ );
print ( ’D ’ );
}

thread
while (true) {
print ( ’E ’ );
puedeH.release();
puedeF.acquire();
print ( ’F ’ );
print ( ’G ’ );
puedeC.release();
}

thread
while (true) {
puedeH.acquire();
print ( ’H ’ );
print ( ’I ’ );
}



Agregar sem ́aforos para garantizar que simult ́aneamente se den las siguientes condiciones:
La cantidad de ‘F’ es menor o igual a la cantidad de ‘A’.
La cantidad de ‘H’ es menor o igual a la cantidad de ‘E’.
La cantidad de ‘C’ es menor o igual a la cantidad de ‘G’.

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Ejercicio 5. Considere un programa compuesto por los siguientes tres threads:


global int x = 0;
Semaphore mutex = new Semaphore(1);

thread T1 : {
    mutex.adquire();
    x = x + 1;
    mutex.release();
}

thread T2 : {
    mutex.adquire();
    x = x + 2;
    mutex.release();
}

thread T3 : {
    mutex.adquire();
    x = x + 3;
    mutex.release();
}

Garantice, por medio del uso de sem ́aforos, que la ejecuci ́on del programa no pierde sumas
(es decir, al finalizar la ejecuci ́on de los tres threads, el valor final de x debe ser 6). Considere la
posibilidad de que alguno de los threads no se ejecute, en ese caso los threads restantes deben
poder finalizar su ejecuci ́on sin quedarse bloqueados (y el valor de x debe ser la suma de sus
incrementos).


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Ejercicio 6. Considere los siguientes threads que comparten dos variables y y z.
global int y = 0 , z = 0;
thread {
int x ;
x = y + z ;
}

thread {
y = 1;
z = 2;
}

a) ¿Cu ́ales son los posibles valores finales de x?
0 1 2

b) Para cada uno de los valores finales de x posibles, modifique el programa usando sem ́aforos
de forma tal que siempre x tenga ese valor al final de la ejecuci ́on (considere que el programa
modificado siempre debe poder terminar).

//para 0
Semaphore siempre0 = new Semaphore(0);

global int y = 0 , z = 0;
thread {
    int x ;
    x = y + z ;
    siempre0.release();
}

thread {
    siempre0.adquire();
    y = 1;
    z = 2;
}

//para 1
Semaphore siempre1 = new Semaphore(0);

global int y = 0 , z = 0;
thread {
    int x ;
    siempre1.adquire();
    x = y + z ;
    
}

thread {
    y = 1;
    siempre1.release();
    z = 2;
}

//para 2
Semaphore siempre2 = new Semaphore(0);

global int y = 0 , z = 0;
thread {
    int x ;
    siempre2.adquire();
    x = y + z ;
    
}

thread {
    y = 1;
    z = 2;
    siempre2.release();
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


Ejercicio 7. Considere los siguientes dos threads:

thread:{
    while (true){
        print ( ’A ’ );
    }
}

thread:{
    while (true){
        print ( ’B ’ );
    }
}


a) Utilice sem ́aforos para garantizar que en todo momento la cantidad de A y B difiera como
m ́aximo en 1.


Semaphore puedeA = new Semaphore(0);
Semaphore puedeB = new Semaphore(0);

thread:{
    while (true){
        print ( ’A ’ );
        puedeB.release();
        puedeA.acquire();
    }
}

thread:{
    while (true){
        print ( ’B ’ );
        puedeB.acquire();
        puedeA.release();
    }
}



b) Modifique la soluci ́on para que la  ́unica salida posible sea ABABABABAB...
Semaphore puedeA = new Semaphore(0);
Semaphore puedeB = new Semaphore(0);

thread:{
    while (true){
        print ( ’A ’ );
        puedeB.release();
        puedeA.acquire();
    }
}

thread:{
    while (true){
        puedeB.acquire();
        print ( ’B ’ );
        puedeA.release();
    }
}



c) Modifique la soluci ́on para que la  ́unica salida posible sea ABBABBABB...


Semaphore puedeA = new Semaphore(0);
Semaphore puedeB = new Semaphore(0);

thread:{
    while (true){
        print ( ’A ’ );
        puedeB.release();
        puedeA.acquire();
    }
}

thread:{
    while (true){
        puedeB.acquire();
        print ( ’B ’ );
        print ( ’B ’ );
        puedeA.release();
    }
}