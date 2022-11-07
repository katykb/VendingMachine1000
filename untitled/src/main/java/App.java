import UserInputOutput.UserInputOutputIF;
import UserInputOutput.UserInputOutputImpl;
import UserInputOutput.VendingMachineView;
import VendingMachineController.VendingMachineController;
import VendingMachineDao.*;
import VendingMachineService.VendingMachineServiceIF;
import VendingMachineService.VendingMachineServiceImpl;


public class App {
    public static void main(String[] args) throws VendingMachinePersistenceException {
        UserInputOutputIF myIo = new UserInputOutputImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDaoIF myDao = new VendingMachineDaoImpl();
        VendingMachineAuditDaoIF myAuditDao = new VendingMachineAuditDaoImpl();
        VendingMachineServiceIF myService = new VendingMachineServiceImpl(myDao, myAuditDao);
        VendingMachineController myController = new VendingMachineController(myView, myService, myAuditDao);
        myController.run();
    }
}