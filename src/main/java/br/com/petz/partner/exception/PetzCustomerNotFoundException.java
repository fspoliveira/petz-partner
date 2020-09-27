package br.com.petz.partner.exception;

public class PetzCustomerNotFoundException extends IllegalArgumentException {

    public PetzCustomerNotFoundException() {
        super("Id from Customer not found");
    }
}
