package src.p03.c01;
/*
 * Autor: Jonas Martinez 
 * 		  David Perez
 * 
 * Esta es la clase principal de parque donde indicamos cuando entran y salen y por que puerta.
 * 
 * Version 1.3
 * 
 */
import java.util.Enumeration;
import java.util.Hashtable;

public class Parque implements IParque{

	private int contadorPersonasTotales;
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	// definimos la variable parqueLleno y la asignamos el valor de 50
	public static final int parqueLleno = 50;
	// definimos la variable parqueVacio y la asignamos el valor de 0
	public static final int parqueVacio = 0;
	
	public Parque() {
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
	}


	@Override
	public synchronized void entrarAlParque(String puerta){
		
		// Si no hay entradas por esa puerta, inicializamos		
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
		// Comprueba si el parque está lleno
		comprobarAntesDeEntrar();
		
		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)+1);
		
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");
		
		// Realizamos las comprobaciones necesarias par ver si nos pasamos de los salimos de los rangos establecidos
		checkInvariante();
		
		//
		notifyAll();
	}
	
	
	@Override
	public synchronized void salirDelParque(String puerta){		// TODO

		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
				
		// Comprueba si el parque está vacío		
		comprobarAntesDeSalir(puerta);
		
		// Disminuir el contador total y el individual
		contadorPersonasTotales--;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)-1);
			
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Salida");	
		
		// Realizamos las comprobaciones necesarias par ver si nos pasamos de los salimos de los rangos establecidos
		checkInvariante();	
				
		// 
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
		// Comprobamos que el nuemro de personas dentro del parque es menor o igual al máximo de personas que pueden estar dentro (50) 
		assert contadorPersonasTotales <= parqueLleno: "Invariante incumplido";
		// Comprobamos que la cantidad de personas es un numeropositivo ya que no se puede dar una cantidad de personas negativa
		assert contadorPersonasTotales >= 0: "No puede haber una cantidad negativa de personas";
	}

	protected void comprobarAntesDeEntrar(){
		// bucle que lanza un wait() si el parque está lleno, de esta forma no puede entrar ninguna otra persona hasta que no salga almenos una del parque
		while(contadorPersonasTotales >= parqueLleno) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void comprobarAntesDeSalir(String puerta){
		// bucle que lanza un wait() si el parque está vacio, de esta forma nunca tendremos una cantidad de personas negativa en el parque
		while(contadorPersonasTotales <= parqueVacio) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
