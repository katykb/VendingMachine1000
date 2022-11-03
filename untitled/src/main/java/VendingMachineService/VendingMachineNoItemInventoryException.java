package VendingMachineService;

public class VendingMachineNoItemInventoryException extends Throwable {

    public VendingMachineNoItemInventoryException(String message){

        super(message);
    }

    public VendingMachineNoItemInventoryException(String message, Throwable cause){

        super(message, cause);
    }
}
