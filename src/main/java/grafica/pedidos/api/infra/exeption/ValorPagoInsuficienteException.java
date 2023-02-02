package grafica.pedidos.api.infra.exeption;

public class ValorPagoInsuficienteException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ValorPagoInsuficienteException(String msg) {
		super(msg);
	}
}
