package src.p03.c01;

/*
 * Autor: Jonas Martinez 
 * 		  David Perez
 * 
 * Clase ActividadSalidaPuerta donde indicamos cuando se sale del parque y por que puerta.
 * 
 * Version 1.3
 * 
 */


import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActividadSalidaPuerta implements Runnable{
	
	// Declaramos la variable contador NUMSALIDAS
	private static final int NUMSALIDAS = 20;
	private String puerta;
	private IParque parque;
	
	public ActividadSalidaPuerta(String puerta, IParque parque) {
		this.puerta = puerta;
		this.parque = parque;
	}

	@Override
	public void run() {
		for (int i = 0; i < NUMSALIDAS; i ++) {
			try {
				//Llamamos a la funcion salirDelParque() en dicha puerta
 				parque.salirDelParque(puerta);
 				//El programa espera mediante un sleep un tiempo random de milisegundos
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5)*1000);
			} catch (InterruptedException e) {
				Logger.getGlobal().log(Level.INFO, "Salida interrumpida");
				Logger.getGlobal().log(Level.INFO, e.toString());
				return;
			}
		}
	}
}
