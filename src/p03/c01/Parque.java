package src.p03.c01;
/*
 * Autor: Jonas Martinez 
 * 		  David Perez
 * 
 * Esta es la clase principal de parque donde indicamos cuando entran y salen y por que puerta.
 * 
 * Version 1.2
 * 
 */
import java.util.Enumeration;
import java.util.Hashtable;

public class Parque implements IParque{

	// TODO
	private int contadorPersonasTotales;
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	public static final int totalPersonasPuertas = 50;
	
	public Parque() {	// TODO
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
		// TODO
	}


	@Override
	public synchronized void entrarAlParque(String puerta){		// TODO
		
		// Si no hay entradas por esa puerta, inicializamos
		
		
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
		// TODO, no tenemos seguro la comprobacion aqui de la entrada.
		comprobarAntesDeEntrar();
		
		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)+1);
		
		 
		
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");
		//TODO
		checkInvariante();
		
		//TODO
		notifyAll();
	}
	
	// 
	// TODO Método salirDelParque
	//
	@Override
	public synchronized void salirDelParque(String puerta){		// TODO
		//TODO
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
				
		// TODO 
		
		comprobarAntesDeSalir(puerta);
		
		// Disminuir el contador total y el individual
		contadorPersonasTotales--;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)-1);
		
		
				
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Salida");	
		// TODO
		checkInvariante();	
				
		// TODO
		notifyAll();
	}
	
	private void imprimirInfo (String puerta, String movimiento){
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); //+ " tiempo medio de estancia: "  + tmedio);
		
		// Iteramos por todas las puertas e imprimimos sus entradas
		for(String p: contadoresPersonasPuerta.keySet()){
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}
	
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
			Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
			while (iterPuertas.hasMoreElements()) {
				sumaContadoresPuerta += iterPuertas.nextElement();
			}
		return sumaContadoresPuerta;
	}
	
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales : "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		// TODO 
		assert contadorPersonasTotales <= totalPersonasPuertas: "Invariante incumplido";
		// TODO
		assert contadorPersonasTotales >= 0: "No puede haber una cantidad negativa de personas";
	}

	protected void comprobarAntesDeEntrar(){	// TODO
		//
		// TODO
		//

		while(contadorPersonasTotales >= totalPersonasPuertas) {
			try {
				wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
	}

	protected void comprobarAntesDeSalir(String puerta){		// TODO
		//
		// TODO
		//

		while(contadorPersonasTotales <=0) {
			try {
				wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}


}
