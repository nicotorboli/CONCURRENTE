/*Ejercicio 1. Asumiendo que cada instrucci ́on print es at ́omica, muestre todas las posibles
trazas de ejecuciones del siguiente programa:
*/

thread T1 : {
print ( " Hola " );
print ( " Pepe " );
}

thread T2 : {
print ( " Hola " );
print ( " Jose " );
}

t1 = print ( " Hola " ); print ( " Pepe " ); print ( " Hola " );print ( " Jose " );
t2 = print ( " Hola " ); print ( " Hola " ); print ( " Pepe " );print ( " Jose " );
t3 = print ( " Hola " ); print ( " Hola " ); print ( " Pepe " );print ( " Jose " );
t4 = print ( " Hola " ); print ( " Jose " ); print ( " Hola " );print ( " Pepe " );
t5 = print ( " Hola " ); print ( " Hola " ); print ( " Jose " );print ( " Pepe " );
t6 = print ( " Hola " ); print ( " Hola " ); print ( " Pepe " );print ( " Jose " );


/*Ejercicio 2. ¿Cu ́ales son los valores que puede tomar x al final de la ejecuci ́on de los siguientes
programas?*/
//a) 
global int x = 0;

thread T1 : {
    int local = x ;
    local = local + 1;
    x = local ;
}

thread T2 : {
    int local = x ;
    local = local + 1;
    x = local ;
}


x puede ser x= 1 o 2 

traza x = t2 :

int = 0
(t1)int local x = 0
(t2) int local x = 0 
en ambas x = 1

traza x = 2
(t1)int local x = 0
(t1)local = local + 1;
(t1)x = local ;
(t2)int local = x ; x = 1 por t1 
(t2)local = local + 1;
(t2)x = local ;
 


//b) 
global int x = 0;
thread T1 :
    x = x + 1;

thread T2 :
    x = x + 1;

siempre dara x = 2


/*Ejercicio 3. Dado el siguiente programa:*/
global int x = 0;
global int y = 0;

thread T1 :
y = x + 1;

thread T2 :
x = y + 1;

/*a) Mostrar una traza de ejecuci ́on en el que los valores de las variables globales al final de la
ejecuci ́on del programa son x = 2 e y = 1.*/

(t1) y = x + 1; || x = 0, y = 1
(t2) x = y + 1; || x = 2, y = 1

/*b) Decir si existe una traza de ejecuci ́on tal que al final x = y = 1. Justificar.*/

no se puede ya que las 2 trazas posible son:
 y = x + 1; x = y + 1;  x = 2, y = 1
 o
 x = y + 1; y = x + 1;  y = 2, x = 1


/*Ejercicio 4. Dado el siguiente programa:*/

global int n = 0;
thread T1 : {
    int local ;
    repeat (5) {
    local = n ;
    n = local + 1;
    } 
}

thread T2 : {
    int local ;
    repeat (5) {
    local = n ;
    n = local + 1;
    } 
}

Mostrar una traza de ejecuci ́on del programa en el que el valor final de n sea 5.
(t1) int local ;
(t2) int local ;
(t2) local = n ; local = 0
(t1) local = n ;
     n = local + 1;
     .....  ejecuta todo t1 y n = 5 y local = 4
(t2) n = local + 1; con local = 0 en t2 
     ..... ejecuta todo t2 y n = 5 y local = 4



//¿Es posible que al final de la ejecuci ́on del programa el valor final de n sea menor que 5?
sip 



//¿Cu ́al es el m ́ınimo?Justificar.
el minimo que puede valer n es 2
(t1) int local ;
(t2) int local ;
(t1) local = n ; repeat 1, local = 0, n = 0
(t2) local = n ; repeat 1, local = 0, n = 0
(t1) n = local + 1; repeat 1, local = 0, n = 1
(t1) local = n ; repeat 2, local = 1, n = 1
(t1) n = local + 1; repeat 2, local = 1, n = 2 
(t1) local = n ; repeat 3, local = 2, n = 2
(t1) n = local + 1; repeat 3, local = 2, n = 3 
(t1) local = n ; repeat 4, local = 3, n = 3
(t1) n = local + 1; repeat 4, local = 3, n = 4 
----------------------------
(t2) n = local + 1; repeat 1, local = 0, n = 1
(t1) local = n ; repeat 5, local = 1, n = 1
... aca esta el truco 
-----------------------------
(t2) local = n ; repeat 2, local = 1, n = 1
(t2) n = local + 1; repeat 2, local = 1, n = 2 
(t2) local = n ; repeat 3, local = 2, n = 2
(t2) n = local + 1; repeat 3, local = 2, n = 3 
(t2) local = n ; repeat 4, local = 3, n = 3
(t2) n = local + 1; repeat 4, local = 3, n = 4 
(t2) local = n ; repeat 5, local = 4, n = 4
(t2) n = local + 1; repeat 4, local = 4, n = 5
-----------------------------
(t1) n = local + 1; repeat 5, local = 1, n = 2

/*Ejercicio 5. Asumir que la funci ́on f tiene una ra ́ız entera, es decir, f(x) = 0 para alg ́un valor x
entero. A continuaci ́on proponemos distintos programas para encontrar tal ra ́ız. Consideraremos
que un programa es correcto si ambos threads terminan cuando uno de ellos ha encontrado la
ra ́ız. Para cada programa, decir si es correcto o no, justificando la respuesta.*/

//a) 
global boolean found = false;

thread T1 : {
    int i = 0;
    found = false;
    while (! found ) {
    i = i + 1;
    found = ( f ( i ) == 0);
    } 
}

thread T2 : {
    int j = 1;
    found = false;
    while (! found ) {
    j = j - 1;
    found = ( f ( j ) == 0);
    } 
}
aca inevitablemente t1 lo va a contrar pero si el programa ejecuta j = j - 1; found = ( f ( j ) == 0); 
y found va a volver a dar false



//b) 
global boolean found = false;
thread T1 : {
    int i = 0;
    while (! found ) {
    i = i + 1;
    found = ( f ( i ) == 0);
    } 
}

thread T2 : {
    int j = 1;
    while (! found ) {
    j = j - 1;
    found = ( f ( j ) == 0);
    } 
}
 existe la chance en el que t1 haga f(1) = 0 y t2 haga f(0)= 0 y ahi ambos terminarian o 
 el caso en el que t1 llegase a encontrar y y t2 revise la condicion y corte

//c) 
global boolean found = false;
thread T1 : {
    int i = 0;
    while (! found ) {
    i = i + 1;
    if ( f ( i ) == 0)
    found = true;
    } 
}

thread T2 : {
    int j = 1;
    while (! found ) {
    j = j - 1;
    if ( f ( j ) == 0)
    found = true;
    } 
}

en el a y b te puede pasar que se le asigne false a found en el c no


//Ejercicio 6. Considerar el siguiente programa:

global int n = 0;

thread T1 : {
    while ( n < 2)
    print ( n );
}

thread T2 : {
    n = n + 1;
    n = n + 1;
}

// a) ¿Cual es la longitud de la secuencia m ́as corta que puede ser mostrada?
4
// b) ¿Cu ́antas veces puede aparecer el caracter ‘2’ en la salida?
1
// c) ¿Cu ́antas veces puede aparecer el caracter ‘1’ en la salida?
infinitamente hasta que t2 haga     
n = n + 1; por 2da vez





//Ejercicio 7. Considerar el siguiente programa:

global int n = 0;
thread T1 :
while ( n < 1)
n = n + 1;

thread T2 :
while ( n >= 0)
n = n - 1;

//a) Describir una traza en la que el programa termina.
(t1)while ( n < 1)  n = 0
(t1) n = n + 1;     n = 1
(t2)while ( n >= 0) n = 1
(t1)while ( n < 1)  n = 1 , t1 termina
(t2)n = n - 1;      n = 0
(t2)while ( n >= 0) n = 0
(t2)n = n - 1;      n = -1
(t2)while ( n >= 0) n = -1, t2 termina


//b) Describir una traza en la que el programa no termina.

(t1)while ( n < 1)  n = 0 ---1
(t1) n = n + 1;     n = 1
(t2)while ( n >= 0) n = 1 ---2
(t2) n = n - 1;     n = 0
(t1)while ( n < 1)  n = 0 ---1
(t1) n = n + 1;     n = 1
....
//Ejercicio 8. Considerar el siguiente programa:

global int n = 0;
global boolean flag = false;
thread T1 :
while (! flag )
n = 1 - n ;

thread T2 :
while (! flag )
if ( n == 0)
flag = true;

a) Describir una traza en la que el programa termina.

(t2)while (! flag )   flag = false 
(t2)if ( n == 0)      n = 0
(t2)flag = true;      flag = true 
(t1)while (! flag )   flag = true 

b) Describir una traza en la que el programa no termina.

(t2)while (! flag )   flag = false, n = 0 
(t1)while (! flag )   flag = false, n = 0 
(t1)n = 1 - n         flag = false, n = 1
(t2)if ( n == 0)      flag = false, n = 0
(t1)while (! flag )   flag = false, n = 0 
(t1)n = 1 - n         flag = false, n = 0


