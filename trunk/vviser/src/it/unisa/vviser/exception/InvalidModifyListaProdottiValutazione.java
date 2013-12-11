package it.unisa.vviser.exception;

/**
 * 
 * @author Giuseppe Sabato
 *
 */
public class InvalidModifyListaProdottiValutazione extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidModifyListaProdottiValutazione(String err)
	{
		super(err);
	}

}
