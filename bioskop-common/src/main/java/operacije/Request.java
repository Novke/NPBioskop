package operacije;

import java.io.Serializable;

/**
 * Klasa koja predstavlja zahtev za izvršavanje određene operacije.
 * Implementira interfejs Serializable kako bi objekti ove klase mogli biti preneti preko mreže.
 */
public class Request implements Serializable {
    private Operation operation;
    private Object argument;

    /**
     * Podrazumevani konstruktor.
     */
    public Request() {
    }

    /**
     * Konstruktor koji inicijalizuje Request objekat sa zadatom operacijom i argumentom.
     *
     * @param operation operacija koja se zahteva
     * @param argument  argument koji se prosleđuje uz operaciju
     */
    public Request(Operation operation, Object argument) {
        this.operation = operation;
        this.argument = argument;
    }

    /**
     * Getter metoda za operaciju.
     *
     * @return operacija koja se zahteva
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Setter metoda za operaciju.
     *
     * @param operation operacija koja se postavlja
     */
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    /**
     * Getter metoda za argument.
     *
     * @return argument koji se prosleđuje uz operaciju
     */
    public Object getArgument() {
        return argument;
    }

    /**
     * Setter metoda za argument.
     *
     * @param argument argument koji se postavlja
     */
    public void setArgument(Object argument) {
        this.argument = argument;
    }
}