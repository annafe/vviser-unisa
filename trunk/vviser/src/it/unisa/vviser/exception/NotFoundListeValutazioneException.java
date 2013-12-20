package it.unisa.vviser.exception;

/**
 * 
 * @author Giuseppe Sabato
 *
 */
public class NotFoundListeValutazioneException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public NotFoundListeValutazioneException(String err)
	{
		super(err);
	}

}
