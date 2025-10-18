/*Ejercicio 5. Se desea definir un servidor que provea el siguiente comportamiento: por cada
cliente que se conecta debe generar un n ́umero pseudoaleatorio entero en el rango [0,10]. Los
clientes deben intentar adivinar el n ́umero generado por el servidor. Para ello env ́ıan sucesiva-
mente mensajes conteniendo un n ́umero. Cada mensaje es contestado por el servidor indicando
si el cliente acert ́o o no. Notar que el cliente no puede enviar un nuevo mensaje hasta tanto el
servidor no le conteste el anterior.*/


Channel requestNroGanador = new Channel();

Proccess servidor(){

    int nroGanador;
    Channel[] clientes;
    while true {
        channel servidor_conexion.recieve()
        nroGanador = randomInt(1,10)      
        for c in clientes{
            c.send(nroGanador)
        }
    }

}



Proccess cliente(){
    boolean acerte = false;
    Channel my_channel = new Channel();
    servidor_conexion.send(my_channel);

    while (! acerte){ 
        mi_numero = randomInt(1,10);
        nroGanador = my_channel.recieve();
        acerte = nroGanador == mi_numero;
    }

}