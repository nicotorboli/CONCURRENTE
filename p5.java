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


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



Ejercicio 5. Considere el ejercicio 2 y piense este escenario adicional:
a) Modifique la soluci ́on de la variante (i) considerando que ahora que hay K botes distintos
llevando gente de manera concurrente (donde cada uno viaja a su tiempo). Todos los barcos
comienzan en la costa 0. El thread transbordador recibe como par ́ametro un id de barco
 ́unico. Modele el problema prestando atenci ́on a que el comportamiento sea consistente (ie.
que no pase que una persona se suba a un bote y se baje de otro).


global Semaphore mutexBarcosEnCosta = new Semaphore[2]{1,1}
global Semaphore mutexBarcosEnCosta = new Semaphore[2]{1,1}
global Semaphore permisoAbordar = new Semaphore(0,True);
global Semaphore asientoOcupado  = new Semaphore(0);
global Semaphore permisoBajar   = new Semaphore[K]{0...0};
global Semaphore permisoVolver  = new Semaphore[K]{0...0};


global Array barcoIdDisponibleEnCosta = new Array[2];

thread bote(int id){
    costa = 0
    while true 
    costa = costa mod 2  
    mutexBarcosEnCosta[costa].adquire(); 
    
    barcoIdDisponibleEnCosta[costa] = id; 
    
    permisoAbordar[costa].release(N);
    
    ficharPasajerosEnbarco[id].adquire(n);
    
    mutexBarcosEnCosta[costa].release(); 
    
    asientoOcupado.adquire(N);
    
    permisoBajar[id].release(N);
    
    costa++

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
global Semaphore ficharPasajerosEnbarco = new Semaphore[K]{0...0};
global Semaphore permisoAbordar = new Semaphore(0,True);
global Semaphore asientoOcupado  = new Semaphore[K]{0...0};
global Semaphore permisoBajar   = new Semaphore[K]{0...0};
global Semaphore permisoVolver  = new Semaphore[K]{0...0};


global Array barcoIdDisponibleEnCosta = new Array[2];

thread bote(int id){
    costa = 0
    while true{ 
        mutexBarcosEnCosta[costa].adquire(); 

        barcoIdDisponibleEnCosta[costa] = id; 

        permisoAbordar[costa].release(N);

        ficharPasajerosEnbarco[id].adquire(N);

        mutexBarcosEnCosta[costa].release(); 

        asientoOcupado[id].adquire(N);

        permisoBajar[id].release(N);

        permisoVolver[id].adquire(N);
    }
}


thread Persona(int costa) {

    permisoAbordar[costa].adquire();

    barcoIdEnElQueEstoy = barcoIdDisponibleEnCosta;

    ficharPasajerosEnbarco[barcoIdEnElQueEstoy].release(n)
    
    asientoOcupadO[barcoIdEnElQueEstoy].release();

    permisoBajar[barcoIdEnElQueEstoy].adquire(); 
    
    permisoVolver[barcoIdEnElQueEstoy].release();
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



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
global int capacidad = M ;

thread hincha(){
        mutexEntrarCine.adquire()
        if cineAbierto && capacidad > 0 {
            //entra  
            capacidad --  
            mutexEntrarCine.release()
            permisoParaEntrar.adquire()
            permisoParaEmpezarLaFuncion.release()

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
    repeat(m - capacidad ){ 
        permisoParaEntrar.release()
    }
    permisoParaEmpezarLaFuncion.acquire(m - capacidad)

}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/*
Ejercicio 7. Dada la soluci ́on al problema de Lectores-Escritores con prioridad para escritores,
considere las siguientes modificaciones al thread Lector:
*/
/*
a) ¿Qu ́e efecto producir ́ıa si se usara un permisoL para todo el cuerpo del Lector? Es decir,
con el siguiente c ́odigo para los lectores:*/

thread Lector () {
    mutexP . acquire ();
    permisoL . acquire ();
    lectores ++;
    if ( lectores == 1)
        permisoE . acquire ();
    leer ();
    lectores - -;
    if ( lectores == 0)
        permisoE . release ();
    permisoL . release ();
    mutexP . release ();
}


b) ¿Funcionar ́ıa la soluci ́on si se quitara el sem ́aforo mutexL delegando la exclusi ́on mutua de
los lectores a permisoL? Es decir, con el siguiente c ́odigo para los lectores:
thread Lector : {

mutexP . acquire ();
permisoL . acquire ();
lectores ++;
if ( lectores == 1)
permisoE . acquire ();
permisoL . release ();
mutexP . release ();
leer ();
permisoL . acquire ();
lectores - -;
if ( lectores == 0)
permisoE . release ();
permisoL . release ();
}



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



Ejercicio 8. En una oficina hay un ba ̃no unisex con 8 toiletes. A lo largo del d ́ıa, distintas
personas entran a utilizarlo. Si sucede que en ese momento todos los toiletes est ́an ocupados,
las personas esperan hasta que alguno se libere. Por otra parte, peri ́odicamente el personal de
limpieza debe pasar a mantener las instalaciones en condiciones. La limpieza del ba ̃no no se
puede hacer mientras haya gente dentro del mismo, por lo que si en ese momento hay personas
utilizando alg ́un toilete o esperando que se libere alguno, el personal de limpieza debe esperar a
que el ba ̃no se vac ́ıe completamente. En contraparte, si hay un empleado de limpieza trabajando
en el ba ̃no, las personas que quieran utilizarlo deber ́an esperar a que termine.

a) Modele esta situaci ́on utilizando sem ́aforos como mecanismo de sincronizaci ́on (puede mo-
delar al personal de limpieza como un  ́unico thread).





thread persona {
    mutexP.acquire ();
    permisoBanio.acquire ();
    mutexC.acquire ();
    clientes ++;
    if ( clientes == 1)
        permisoTrabajar. acquire ();
    mutexC . release ();
    permisoBanio . release ();
    mutexP . release ();
    
    permisoIrAlBaño.acquire()
    irAlBaño ();
    permisoIrAlBaño.release()

    mutexC . acquire ();
    clientes - -;
    if ( clientes == 0)
        permisoTrabajar . release ();
    mutexC . release ();
}


thread personal {
    
    mutexT . acquire ();
    limpiadores ++;
    if ( limpiadores == 1)
        permisoBanio . acquire ();
    mutexT . release ();

    permisoTrabajar . acquire ();
    trabajar ();
    permisoTrabajar . release ();


    mutexT . acquire ();
    limpiadores --;
    if ( limpiadores == 0)
        permisoBanio . release ();
    mutexT . release ();

}



b) Modifique la soluci ́on anterior para contemplar el caso donde el personal de limpieza tiene
prioridad. Es decir, si hay un empleado de limpieza esperando para hacer el mantenimiento,
las siguientes personas que lleguen deben esperar a que logre terminar la limpieza.


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

global Semaphore permisoCargar              = new Semaphore(0,true)
global Semaphore mutexPuesto                = new Semaphore(0)
global Semaphore[] permisoDeIrse            = new Semaphore(6) {0,...,0}
global Semaphore permisoSolicitarCarga      = new Semaphore(1)
global Semaphore permisoSolicitarDescarga   = new Semaphore(1)
global Semaphore mutexP                     = new Semaphore(1)
global Semaphore mutexv                     = new Semaphore(1)
global Semaphore mutexC                     = new Semaphore(1)


global int puestoDeCargaDisponible;
global int vehiculos; 
global int camiones; 

thread vehiculos:{
    mutexP.adquire() 
    
    permisoSolicitarCarga.adquire()
    
    permisoCargar.adquire()
    
    miPuesto = puestoDeCargaDisponible
    puestoAsignado[miPuesto].release()
    
    mutexV.adquire()
    
    vehiculos ++
    if vehiculos == 1
        permisoSolicitarDescargar.adquire()
    
    mutexV.release()
    
    permisoSolicitarCarga.release()
    
    mutexP.release()


    // ir al puesto
    cargar() 


    mutexV.adquire()
    vehiculo--
    permisoDeIrse[miPuesto].release()
    mutexV.release()
    
}


thread camiones:{
    
    mutexC.acquire ();
    camiones  ++;
    if ( camiones == 1)
        permisoSolicitarCarga.acquire();
    mutexC . release ();

    permisoSolicitarDescargar.acquire ();
    descargar ();
    permisoSolicitarDescargar.release ();
    
    mutexC.acquire ();
    camiones --;
    if ( camiones == 0)
        permisoSolicitarCarga.release ();
    mutexC.release ();

}

thread puestoDeCarga(int id):{
    while true 
        mutexPuesto.adquire()
        permisoCargar.release()
        puestoDeCargaDisponible = id 
        puestoAsignado[id].adquire()
        mutexPuesto.release()
        permisoDeIrse[id].adquire()

}



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
Ejercicio 10. Se desea modelar el control de tr ́ansito de un puente que conecta dos ciudades.


Dado que el puente es muy estrecho se debe evitar que dos autos circulen al mismo tiempo en
direcci ́on opuesta, dado que quedar ́ıan atascados.
Resuelva los siguientes problemas usando sem ́aforos, modelando cada coche como un thread
independiente que desea atravesar el puente en alguna de las dos direcciones posibles. Tenga
en cuenta que atravesar el puente no es una acci ́on at ́omica, y por lo tanto, requiere de cierto
tiempo.
a) De una soluci ́on que permita que varios coches que se desplazan en la misma direcci ́on
puedan circular simult ́aneamente.
b) Modifique la soluci ́on anterior para que como m ́aximo 3 coches puedan circular por el
puente al mismo tiempo.


global Semaphore[] mutexDir             = new Semaphore[1]{1,1}

global array autos ;


thread vehiculo (int dir){
    mutexDir[dir].adquire()
    autos[dir] ++
    if autos[dir] == 1
        permisoPuente.adquire()
    mutexDir[dir].release()

    puedeTransitar.adquire()
    //viajar
    puedeTransitar.release()

    mutexDir[dir].adquire()
    autos[dir] --
    if autos[dir] == 0
        permisoPuente.release()
    mutexDir[dir].release()


}