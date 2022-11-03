package VendingMachineController;

import UserInputOutput.VendingMachineView;
import VendingMachineDao.VendingMachinePersistenceException;
import VendingMachineService.VendingMachineInsufficientFundsException;
import VendingMachineService.VendingMachineNoItemInventoryException;
import VendingMachineService.VendingMachineServiceIF;
import VendingMachionDto.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineController {
    private VendingMachineView view;
    private VendingMachineServiceIF service;
    private String item;

    Map<String, Item> showListOfItemsToBuy = new HashMap<>();

    public VendingMachineController(VendingMachineView view, VendingMachineServiceIF service) {
        this.view = view;
        this.service = service;

        try {
            service.loadItemsInStock();
        } catch (VendingMachinePersistenceException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws VendingMachinePersistenceException {
        BigDecimal moneyDeposited = new BigDecimal("0");
        //String chosenItem = null;
        boolean keepGoing = true;
        int menuSelection = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            while (keepGoing) {

                view.displayVendingMachineWelcome();
                menuSelection = view.displayMainMenuAndGetSelection();

                switch (menuSelection) {
                    case 1:
                        service.listAllItemsToBuy();
                        break;

                    case 2:
                        BigDecimal userMoneyInput;
                        userMoneyInput = service.getMoneyFromUser();
                        break;

                    case 3:
                        keepGoing = false;
                    default:
                        unknownCommand();
                }
            }
            view.displayFinalMessage();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }


    String getChosenItem(String itemId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            view.promptUserItemChoice();
            scanner.nextLine();
            try {
                String myItem = service.getChosenItem(itemId);
                view.displayUserChoiceOfItem(item);

            } catch (VendingMachineNoItemInventoryException exception) {
            }
        }
    }

    boolean didUserPutSufficientFundsIn(BigDecimal userAmount, Item item) {
        try {
            service.checkSufficientMoneyToBuyItem(userAmount, String.valueOf(item));
            return true;
        } catch (VendingMachineInsufficientFundsException ex) {
            view.displayErrorMessage("Not enough money to buy!");
            view.displayUserMoneyInput(userAmount);
            return false;
        }
    }

    void displayChangeReturnedToUser(BigDecimal amount, Item item) {
        BigDecimal change = service.calculateChange(amount, item);
        view.displayChangeReturnedToCustomer(change, String.valueOf(item));
    }

//        public boolean toExitVendingMachine ( boolean isEnoughMoney){
//            if (isEnoughMoney) {
//                return false;
//            } else {
//                return view.toExit(true);
//            }
//        }

//        void displayErrorMessage (String message){
//            view.displayErrorMessage(message);
//        }

    void updateSoldItem(Item item) throws VendingMachinePersistenceException {
        try {
            service.updateItemSale(String.valueOf(item));
        } catch (VendingMachineNoItemInventoryException ex) {
            throw new VendingMachinePersistenceException(ex.getMessage());
        }
    }
//        void saveItemList () throws VendingMachinePersistenceException {
//            try {
//                service.saveItemList();
//            } catch (VendingMachinePersistenceException e) {
//                throw new RuntimeException(e);
//            }
//        }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}



