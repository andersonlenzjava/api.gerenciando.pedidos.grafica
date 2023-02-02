package grafica.pedidos.api.infra.exeption;

public class ItemInesistenteException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ItemInesistenteException(String msg) {
		super(msg);
	}
}
