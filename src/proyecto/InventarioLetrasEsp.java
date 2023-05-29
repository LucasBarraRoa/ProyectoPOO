package proyecto;

public class InventarioLetrasEsp extends InventarioLetras {
	// inicialización similar a la de la superclase, pero con diferentes valores ya que el alfabeto en español es distintos.
	public InventarioLetrasEsp() {
		alfabeto = "abcdefghijklmnñopqrstuvwxyz";
		largoInv = 27;
		inicializar();
	}
	public InventarioLetrasEsp(String data) {
		// inicializando igual que la superclase, pero reemplazando cada tilde por letras sin tilde,
		// igualmente, cambio las variables con cosas especiales en español (ñ y 27 letras totales),
		// decidimos expresar cada replace, en vez de hacerlo con un for, ya que es lineal y hace las mismas comparaciones.
		this.data = data.toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u").replace(" ","");
		this.alfabeto = "abcdefghijklmnñopqrstuvwxyz";
		this.largoInv = 27;
		// inicializa las variables que necesitan las anteriores 2 para existir.
		inicializar();
		// hace el conteo de letras gracias a surtir() heredado de la superclase.
		surtir();
	}
	
	/*
	 Nota: todos los métodos (excepto los que vienen) deben funcionar y no necesitan ser cambiados ya que las diferencias de los idiomas,
	 funcionan gracias a las variables que son diferentes, que son alfabeto, largoInv y separado.
	*/
	
	
	// métodos que ejecutan lo mismo que sus contrapartes en inglés, pero cambiado los tipos de InventarioLetras a InventarioLetrasEsp.
	public InventarioLetrasEsp add(InventarioLetrasEsp otro) {
		return new InventarioLetrasEsp(this.data + otro.getData());
	}
	public InventarioLetrasEsp amplifies(int n) {
		return new InventarioLetrasEsp(amp(n));
	}
	public InventarioLetrasEsp subtract(InventarioLetrasEsp otro) {
		if (sub(otro) == null)
			return null;
		return new InventarioLetrasEsp(sub(otro));
	}
}


