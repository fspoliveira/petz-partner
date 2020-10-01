package br.com.petz.partner.exception;

public class PetzPetNotFoundException extends IllegalArgumentException {
	
	public PetzPetNotFoundException() {
        super("Id from Pet not found");
    }
}
