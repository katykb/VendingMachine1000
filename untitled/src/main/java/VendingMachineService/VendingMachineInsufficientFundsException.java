package VendingMachineService;

public class VendingMachineInsufficientFundsException extends Throwable {

    public VendingMachineInsufficientFundsException(String message){

        super(message);
    }

    public VendingMachineInsufficientFundsException(String message, Throwable cause){

        super(message, cause);
    }
}
