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

/* 
Ejercicio 7. 
El comit ́e organizador de una conferencia posee una sala para exposici ́on de charlas.
Las personas que desean asistir a una charla deben tener la posibilidad de indicar que entran en
la sala o que salen de ella, mientras que la persona oradora debe tener la posibilidad de indicar el
inicio de la charla y el final de la misma. Por respeto, los asistentes, si una persona asistente desea
retirarse durante la charla, deber ́a espear a que esta termine para poder salir efectivamente. La
sala tiene capacidad para N personas.
De una soluci ́on usando monitores que modele los siguientes escenarios:*/

/*a) Se dispone de una sala para dar repetidas veces la misma charla. Si al momento de querer
iniciar la charla el auditorio est ́a vac ́ıo, la persona oradora esperar ́a a que llegue alguien
para escucharla. Asimismo, si una persona llega y quiere entrar cuando la sala est ́a llena,
deber ́a esperar a que se vaya alguien cuando termine la charla. Notar que si hay lugar, s ́ı se
puede entrar en la sala aunque la charla haya comenzado.*/


class Sala {
    int capacidad = N
    int asistentes = 0
    boolean charlaEnCurso = false

    public synchronized iniciar_charla(){
        while (charlaEnCurso == true && asistentes == 0 ){
            wait();
        } 
        charlaEnCurso = true 
    } 

    public synchronized ingresar_a_sala(){
        while (asistentes == capacidad) {
            wait();
        }
        asistentes++
    }
    
    public synchronized dejar_sala(){
        while (charlaEnCurso){
            wait();
        }
        asistentes--
        notifyAll();
    }

    public synchronized finalizar_charla(){ 
        charlaEnCurso = false 
        notifyAll();
    } 

/*    
b) Al igual que en el punto a) se dispone de una sala, pero ahora se dan tres charlas distintas
que se rotan (charla 0, charla 1, charla 2, charla 0, . . . ). Cada persona que quiere entrar
en la sala y cada persona que va a dar una charla indica con un par ́ametro su n ́umero de
charla; si una persona quiere entrar a la sala cuando va a empezar (o ya se est ́a dando)
una charla que no le corresponde, debe esperar. Asimismo, una persona que quiere dar una
charla tiene que esperar que sea la de su n ́umero correspondiente. Las acciones de terminar
la charla y de retirarse no llevan par ́ametro, se asume que siempre corresponden a la charla
actual (pero en el caso de retirarse sigue existiendo la restricci ́on de no irse mientras se
est ́e dando una charla, independientemente de cual sea).
*/

class Sala {
    int capacidad = N
    int asistentes = 0
    boolean charlaEnCurso = false
    int charlaActual = 0

    public synchronized iniciar_charla(int charla){
        while (charlaEnCurso == true && asistentes == 0 && charlaActual /=  charla){
            wait();
        } 
        charlaEnCurso = true 
    } 

    public synchronized ingresar_a_sala(int charla ){
        while (charlaActual /= charla && asistentes == capacidad) {
            wait();
        }
        asistentes++
    }
    
    public synchronized dejar_sala(){
        while (charlaEnCurso){
            wait();
        }
        asistentes--
        notifyAll();
    }

    public synchronized finalizar_charla(){ 
        charlaEnCurso = false 
        charlaActual = (charlaActual + 1) mod 3   
        notifyAll();
    } 
