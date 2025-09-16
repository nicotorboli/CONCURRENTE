/*Ejercicio 1. Se busca computar la suma de los primeros N n ́umeros impares utilizando dos
threads llamados generador y acumulador, que deben cooperar entre s ́ı para resolver esta tarea.
Contamos con dos variables globales impar, que comienza inicializada en N, y suma, que comienza
inicializada en 0. Buscamos que generador se encargue de que impar contenga el valor i cuando
se deba sumar el i- ́esimo n ́umero impar, y que acumulador compute cu ́al es el i- ́esimo impar y lo
sume a suma. Al finalizar el c ́omputo, el thread generador debe imprimir el valor correcto de la
sumatoria.
*/

//a) Escriba un programa concurrente que funcione seg ́un esta din ́amica.

global int impar = N, suma = 0;

global Semaphore sumar = new Semaphore(n)  


repeat (n)
thread generador {
     if n.c

}


thread acumulador {
    puedeSumar.acquire()
}
//b) ¿Qu ́e modificaci ́on deber ́ıa hacerse si existieran m ́ultiples threads generador? ¿Y si hubieran
//m ́ultiples threads acumulador? ¿Existe posibilidad de starvation?




Ejercicio 6. Se avecina el partido super-mega cl ́asico entre dos equipos que llamaremos BJ y RP.
Para evitar conflictos en la cancha, se dispuso el siguiente mecanismo de control de acceso: No
se permitir ́a que la diferencia entre la gente de la hinchada de BJ y la de gente de la hinchada
RP sea mayor que 1 (toda persona que va a ver el partido es de alguno de los dos equipos).
Cada persona que llegue a la cancha debe asegurarse de esta regla le permite el acceso. Si no,
esperar ́a hasta que pase alguien del equipo opuesto de manera de conseguir acceso. Una vez
que una persona entr ́o a la cancha, se quedar ́a hasta finalizar el partido (que no modelaremos,
estipulando que este sigue indefinidamente).
a) Modele este comportamiento utilizando Sem ́aforos.



global Semaphore capacidad = new Semaphore(N);
global Semaphore puedeEntrar = new Semaphore(1)[2]
//puedeEntrar[0]  // BJ
//puedeEntrar[1]  // RP



thread hincha(int = equipo % 2){
    puedeEntrar[equipo].acquire()
    int rival= (equipo + 1) % 2;
    //entra
    puedeEntrar[rival].release()

}


global Semaphore permisoBJ = new Semaphore(1);
global Semaphore permisoRP = new Semaphore(1);

thread hinchaBoca(){
    //entra
    permisoRP.release()
    permisoBJ.acquire()

}

thread hinchaRiver(){
    //entra
    permisoBJ.release()
    permisoRP.acquire()

}





b) Extienda la soluci ́on anterior contemplando que la cancha tiene una capacidad de N per-
sonas. Cada persona que logra entrar a la cancha ocupa un lugar, y como estos nunca

se liberan, en alg ́un momento la cancha estar ́a llena. En ese momento, toda persona que
llegue deber ́a retirarse sin tener la posibilidad de ingreso. Aseg ́urese, tambi ́en, que ninguna
persona se quede esperando indefinidamente.


global Semaphore puedeEntrar = new Semaphore(1)[2]
//puedeEntrar[0] = // BJ
//puedeEntrar[1] = // RP


int capacidad = N 

thread hincha(int = equipo % 2){
    puedeEntrar[equipo].acquire()
    int rival= (equipo + 1) % 2;
    mutexEntrarCancha.adquire()
    if capacidad > 0 
    then //entra    
        if capacidad-- == 0;
            then puedeEntrar[equipo].release() ;
        puedeEntrar[rival].release()
        
    else  // irse
        puedeEntrar[rival].release() 
        puedeEntrar[equipo].release()
    mutexEntrarCancha.release ()
    
}









c) El partido ya se jug ́o y result ́o uno de los m ́as  ́epicos de la historia. Por ello, se planific ́o pro-
yectarlo en el cine de la ciudad para que la gente pueda verlo en pantalla gigante. La gente

que desea asistir s ́olo debe ir al cine y retirar una entrada, disponible en un mostrador del
lobby (suponemos que las personas las toman de a una, aunque es posible que dos personas

se acerquen a buscar su entrada en momentos muy cercanos. No hay distinci ́on entre per-
sonas de un equipo y otro). La cantidad total de entradas es M, y si alguien llega luego de

que se hayan agotado, se retirar ́a del cine. En un horario determinado, el personal del cine
quita las entradas del mostrador y habilita el acceso a la sala las personas que tengan su
entrada. Luego, se espera a que todas las personas se hayan acomodado en alguna butaca,

el personal cierra las puertas, y comienza la proyecci ́on. Como en el caso anterior, pode-
mos pensar que la gente que entr ́o se queda mirando el partido indefinidamente. Modele

este nuevo escenario utilizando Sem ́aforos. Preste atenci ́on a que el personal del cine no se
quede esperando que se sienten m ́as personas que las que las que retiraron entradas.