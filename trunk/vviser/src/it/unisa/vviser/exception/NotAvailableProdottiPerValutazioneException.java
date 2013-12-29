package it.unisa.vviser.exception;

/**
 * 
 * @author Giuseppe Sabato
 *
 */
public class NotAvailableProdottiPerValutazioneException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NotAvailableProdottiPerValutazioneException(String err)
	{
		super(err);
	}

}
