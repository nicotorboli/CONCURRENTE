Ejercicio 1. [Exclusi ́on mutua] Considere la siguiente propuesta para extender el algoritmo
de Peterson a 3 threads:
global flags = { False , False , False };
global turno = 0;
thread ( id ): {
izq = id -1 %3;
der = id +1 %3;
// SNC
flags [ id ]=true;
turno = der ;
while(( flags [ izq ] && turno == izq ) || ( flags [ der ] && turno == der );
// SC
flags [ id ] = false;
// SNC

}
(c) →

a) Muestre que esta propuesta no cumple la condici ́on de Mutex.

b) Muestre que esta propuesta s ́ı tiene Garant ́ıa de Entrada (Idea: Considere el caso de un
deadlock entre los 3 threads y el caso con s ́olo 2)

c) ¿Se cumple la condici ́on de Mutex si se cambia la l ́ınea turno=der de su posici ́on actual a
inmediatamente despu ́es de la Secci ́on Cr ́ıtica?






//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////



Ejercicio 2. [Sem ́aforos] Se desea dise ̃nar un sistema de control para un local de lavado de
autos automatizado. El proceso de lavado se lleva a cabo por maquinaria especializada localizada
en las siguientes 5 estaciones: remojado, enjabonado, enjuague, secado, encerado.
Un veh ́ıculo avanza a trav ́es de las estaciones en secuencia. En cada estaci ́on s ́olo puede haber
un veh ́ıculo en un momento dado, por lo que el sistema debe evitar que un veh ́ıculo avance hasta
que la estaci ́on siguiente est ́e libre. No obstante, el sistema debe permitir el lavado simult ́aneo
de m ́ultiples veh ́ıculos siempre y cuando no haya riesgo de colisiones.

Resuelva los siguientes puntos teniendo en cuenta que (i) el proceso realizado por cada m ́aqui-
na no es una acci ́on at ́omica, y por lo tanto, requiere de cierto tiempo durante el cu ́al el veh ́ıculo
debe permanecer en la estaci ́on correspondiente; y (ii) el desplazarse de una estaci ́on a la si-
guiente tambi ́en requiere de cierto tiempo durante el cu ́al se considera que el veh ́ıculo ocupa las
dos estaciones.


a) De un programa utilizando sem ́aforos que modele el escenario descrito. Utilice threads para
modelar las m ́aquinas lavadoras y los veh ́ıculos.

global Semaphore[] estaciones           = new Semaphore [5] {0,...,0}
global Semaphore[] procesos             = new Semaphore [5] {0,...,0}
global Semaphore[] permisoHabiliar      = new Semaphore [5] {0,...,0}


thread vehiculo(){
    for e in range(0,4){
        estaciones[e].adquire()
        procesos[e].adquire()
        estaciones[e++].adquire()
        permisoHabiliar[e-1].release()
    }
    estaciones[e].adquire()
    procesos[e].adquire()
    permisoHabiliar[e-1].release()
}


thread maquina(int id){
    while (true)   
        estaciones[id].release()
        procesos[id].release()
        permisoHabiliar[id].adquire()
}






global Semaphore[] permisoEntrarEstacion    = new Semaphore [5] {1,...,1}
global Semaphore[] permisoLavar             = new Semaphore [5] {0,...,0}
global Semaphore[] lavadoTerminado          = new Semaphore [5] {0,...,0}




thread vehiculo(){
    for e in range(0,4){
        permisoEntrarEstacion[e].adquire()
        if e>0
            permisoParaEntrarMaquina[e].release()
        permisoLavar[e].release()
        lavadoTerminado[e].adquire()
    }

thread maquina(){
    while true 
        permisoLavar[e].adquire()
        lavadoTerminado[e].release()

}


}

global Semaphore[] estaciones               = new Semaphore [7] {1,0,...,0}
global Semaphore[] procesos                 = new Semaphore [7] {0,...,0}
global Semaphore[] permisoHabiliar          = new Semaphore [7] {0,...,0}
global Semaphore[] permisoDeEmpezar         = new Semaphore [6] {0,...,0}
global Semaphore[] permisoDeBajar           = new Semaphore [6] {0,...,0}
global Semaphore[] lavadoTerminadoDelRobot  = new Semaphore [6] {0,...,0}

global int idRobotDisponible,


thread vehiculo(){
    estaciones[0].adquire();
    idMiRobot = idRobotDisponible;
    permisoDeEmpezar[idMiRobot].release();

    for e in range(1,6){
        estaciones[e].adquire()
        if e == 1
            then estaciones[0].release() 
        procesos[e].adquire()
        estaciones[e++].adquire()
        permisoHabiliar[e-1].release()
    }

    estaciones[e].adquire()
    procesos[e].adquire()
    permisoHabiliar[e-1].release()

    lavadoTerminadoDelRobot[idMiRobot].adquire()
    permisoParaBajar[idMiRobot].release()
}


thread maquina(int id){
    id++
    while (true)   
        estaciones[id].release()
        procesos[id].release()
        permisoHabiliar[id].adquire()
}


thread robot(int id ){
    mutexR.adquire()
    idRobotDisponible = id 
    permisoDeEmpezar[id].adquire()
    mutexR.release()

    // lavar
    lavadoTerminadoDelRobot[id].release()
    permisoParaBajar[id].adquire()
} 







b) Extienda la soluci ́on anterior contemplando adicionalmente una nueva estaci ́on inicial y
final. En la nueva estaci ́on inicial una aspiradora rob ́otica (modelada con un thread aparte)
asciende al veh ́ıculo e inicia un proceso de limpieza interno. La limpieza toma alg ́un tiempo,
pero no previene que el auto avance por las estaciones. El robot s ́olo puede descender del
veh ́ıculo en la  ́ultima estaci ́on. Tenga en cuenta que el ascenso y descenso del robot toma
alg ́un tiempo y que el veh ́ıculo no debe desplazarse hasta que esos movimientos terminen.
Asuma que hay 6 robots en funcionamiento.
c) Distinga los threads que puedan sufrir inanici ́on en su soluci ́on al asumir que todos los
sem ́aforos utilizados son d ́ebiles (o unfair ).






