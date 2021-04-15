package src.p03.c01;
/*
 * Autor: Jonas Martinez 
 * 		  David Perez
 * 
 * Interfaz IParque donde instanciamos los metodos de entrada y salida del parque.
 * 
 * Version 1.3
 * 
 */
public interface IParque {
	
	// Metodo abstacto de entrada utilizado en la clase Parque
	public abstract void entrarAlParque(String puerta);
	
	// Metodo abstacto de salida utilizado en la clase Parque
	public abstract void salirDelParque(String puerta);
	
}
