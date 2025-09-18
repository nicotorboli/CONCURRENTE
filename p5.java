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

2

global Semaphore permisoAbordar = new Semaphore(0,True);
global Semaphore asuntoOcupado  = new Semaphore(0);
global Semaphore permisoBajar   = new Semaphore(0);
global Semaphore permisoVolver  = new Semaphore(0);


thread bote {
    costa = 0
    permisoAbordar.release()
    asuntoOcupado.adquire()
    permisoBajar.release()
    permisoVolver.adquire()
}


thread Persona {

    permisoAbordar.adquire()
    asuntoOcupado.release()
    permisoBajar.adquire()
    permisoVolver.release()

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Ejercicio 3. En un gimnasio hay cuatro aparatos, cada uno para trabajar un grupo muscular
distinto. Los aparatos son cargados con discos (el gimnasio cuenta con 20 discos, todos del mismo
peso). Cada cliente del gimnasio posee una rutina que le indica qu ́e aparatos usar, en qu ́e orden
y cuanto peso utilizar en cada caso (asuma que la rutina es una lista de tuplas con el n ́umero de
aparato a usar y la cantidad de discos cargar, la rutina podr ́ıa incluir repeticiones de un mismo
aparato). Como norma el gimnasio exige que cada vez que un cliente termina de utilizar un
aparato descargue todos los discos y los coloque en el lugar destinado a su almacenamiento (lo
que incluye usos consecutivos del mismo aparato).
a) Indique cuales son los recursos compartidos y roles activos.
/*
b) Escriba un c ́odigo que simule el funcionamiento del gimnasio, garantizando exclusi ́on mutua
en el acceso a los recursos compartidos y que est ́e libre de deadlock y livelock.
Ayuda: Considere modelar a los clientes como threads (cada uno con su propia rutina) y a
los aparatos como un arreglo de sem ́aforos.

*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Semaphore maquina[] = new Semaphore[4] {1,1,1,1}; // fuerte
Semaphore discos = new Semaphore(20);
Semaphore mutexDiscos = new Semaphore(1)


thread Persona {
    for ((nromaq,cantDiscos) in rutina){
        maquina[nromaq].adquire();
        mutexDiscos.adquire();
        repeat(cantDiscos){
            discos.adquire();
        }
        mutexDiscos.release();

        //ejercita
        repeat(cantDiscos){
            discos.release();
        }
    maquina(nromaq).release();
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



/*
Ejercicio 4. En un famoso shopping de la ciudad se agreg ́o un puesto de tintorer ́ıa autom ́atica
para que la gente pueda dejar su ropa mientras hace sus compras. Novedosamente, estas m ́aquinas
env ́ıan un mensaje de texto a las personas una vez que sus prendas est ́an listas para ser retiradas.
Hay K m ́aquinas de lavado listas para funcionar. Cada persona que quiere utilizar el servicio
(en este ejemplo no modelaremos a quieres no lo requieran) espera que haya alguna m ́aquina
disponible, carga su ropa y se va a comprar. Terminadas las compras, la persona se fija en su
tel ́efono si ya lleg ́o el mensaje de la m ́aquina tintorera; de no ser as ́ı, se lo queda esperando hasta
que aparezca. Una vez que el mensaje es recibido, la persona se dirige al puesto de tintorer ́ıa a
hacer el retiro. Modele este escenario por medio de sem ́aforos. Procure que no suceda que una
persona vaya a retirar su ropa cuando la que se termin ́o de procesar es la de alguien distinto
(Nota: Por simplicidad, la persona chequear ́a si est ́a el mensaje reci ́en una vez terminadas sus
compras).*/
Semaphore puedeAnunciarse() = new Semaphore(1);
Semaphore hayMaquinaDisponible() = new Semaphore(0);
Semaphore mensajeEnviado[] = new Semaphore[k] {0...0};
Semaphore ropaCargada[] = new Semaphore[k] {0...0};
Semaphore ropaRetirada[] = new Semaphore[k] {0...0};

global int maquinaDisponible;

thread maquina(id){
    while (True){
        puedeAnunciarse.adquire();
        maquinaDisponible = id;
        hayMaquinaDisponible.release();
        ropaCargada(id).adquire();
        //lavar
        //enviar mensaje
        mensajeEnviado[id].release();
        ropaRetirada[id].adquire();
    }
}


thread Persona {
    int miMaquina;
    hayMaquinaDisponible.adquire();
    miMaquina = maquinaDisponible;
    puedeAnunciarse.release();
    // cargar ropa 
    ropaCargada[miMaquina].adquire();
    // se va 
    mensajeEnviado[miMaquina].adquire();
    // retira la ropa

    ropaRetirada[miMaquina].release();

}

////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
Ejercicio 5. Considere el ejercicio 2 y piense este escenario adicional:
a) Modifique la soluci ́on de la variante (i) considerando que ahora que hay K botes distintos
llevando gente de manera concurrente (donde cada uno viaja a su tiempo). Todos los barcos
comienzan en la costa 0. El thread transbordador recibe como par ́ametro un id de barco
 ́unico. Modele el problema prestando atenci ́on a que el comportamiento sea consistente (ie.
que no pase que una persona se suba a un bote y se baje de otro).


global Semaphore mutexBarcosEnCosta = new Semaphore[2]{1,1}
global Semaphore mutexBarcosEnCosta = new Semaphore[2]{1,1}
global Semaphore permisoAbordar = new Semaphore(0,True);
global Semaphore asuntoOcupado  = new Semaphore(0);
global Semaphore permisoBajar   = new Semaphore[K]{0...0};
global Semaphore permisoVolver  = new Semaphore(0);


global Array barcoIdDisponibleEnCosta = new Array[2];

thread bote(int id){
    costa = 0
    mutexBarcosEnCosta[costa].adquire(); 
    
    barcoIdDisponibleEnCosta[costa] = id; 
    
    permisoAbordar[costa].release(N);
    
    ficharPasajerosEnbarco[id].adquire(n);
    
    mutexBarcosEnCosta[costa].release(); 
    
    asientoOcupado.adquire(N);
    
    permisoBajar[id].release(N);
    
    permisoVolver[id].adquire(N);
}


thread Persona(int costa) {

    permisoAbordar[costa].adquire();

    barcoIdEnElQueEstoy = barcoIdDisponibleEnCosta;
    ficharPasajerosEnbarco[barcoIdEnElQueEstoy].release(n)
    asientoOcupado.release();

    permisoBajar[barcoIdEnElQueEstoy].adquire(); 
    
    permisoVolver[barcoIdEnElQueEstoy].release();
}





global Semaphore mutexBarcosEnCosta = new Semaphore[2]{1,1}
global Semaphore mutexBarcosEnCosta = new Semaphore[2]{1,1}
global Semaphore permisoAbordar = new Semaphore(0,True);
global Semaphore asuntoOcupado  = new Semaphore(0);
global Semaphore permisoBajar   = new Semaphore[K]{0...0};
global Semaphore permisoVolver  = new Semaphore(0);


global Array barcoIdDisponibleEnCosta = new Array[2];

thread bote(int id){
    costa = 0
    mutexBarcosEnCosta[costa].adquire(); 
    
    barcoIdDisponibleEnCosta[costa] = id; 
    
    permisoAbordar[costa].release(N);
    
    ficharPasajerosEnbarco[id].adquire(n);
    
    mutexBarcosEnCosta[costa].release(); 
    
    asientoOcupado.adquire(N);
    
    permisoBajar[id].release(N);
    
    permisoVolver[id].adquire(N);
}


thread Persona(int costa) {

    permisoAbordar[costa].adquire();

    barcoIdEnElQueEstoy = barcoIdDisponibleEnCosta;
    ficharPasajerosEnbarco[barcoIdEnElQueEstoy].release(n)
    asientoOcupado.release();

    permisoBajar[barcoIdEnElQueEstoy].adquire(); 
    
    permisoVolver[barcoIdEnElQueEstoy].release();
}
////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
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


global Semaphore mutexEntrarCine = new Semaphore(1);
global Semaphore permisoSentar = new Semaphore(0); 

global int cineAbieto = true
global int entradasTomadas = 0;
global int capacidad = M ;

thread hincha(){
        mutexEntrarCine.adquire()
        if cineAbierto && capacidad > 0 {
            //entra  
            capacidad -- 
            entradasTomadas ++  
            mutexEntrarCine.release()
            //////////

            //////////
            permisoSentar.release()
        }
        else{  // irse
            mutexEntrarCine.release()
        }
}

thread personal(){
    mutexEntrarCine.adquire()
    cineAbieto = false
    mutexEntrarCine.release()
    /////////

    ////////////
    repeat(entradas){ 
        permisoSentar.adquire()
    }
}

 