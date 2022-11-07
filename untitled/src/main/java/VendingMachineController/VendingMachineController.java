package VendingMachineController;

import UserInputOutput.VendingMachineView;
import VendingMachineDao.VendingMachineAuditDaoIF;
import VendingMachineDao.VendingMachinePersistenceException;
import VendingMachineService.VendingMachineInsufficientFundsException;
import VendingMachineService.VendingMachineNoItemInventoryException;
import VendingMachineService.VendingMachineServiceIF;
import VendingMachionDto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineController {
    private VendingMachineView view;
    public VendingMachineServiceIF service;
    public VendingMachineAuditDaoIF adao;
    String input;

    //Map<String, Item> showMapOfItemsToBuy = new HashMap<>();

    public VendingMachineController(VendingMachineView view, VendingMachineServiceIF service, VendingMachineAuditDaoIF adao) {

        this.view = view;
        this.service = service;
        this.adao = adao;
    }

    public void run() {

        BigDecimal moneyDeposited = new BigDecimal("0");
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing) {

                view.displayVendingMachineWelcome();
                menuSelection = view.displayMainMenuAndGetSelection();

                switch (menuSelection) {
                    case 1:
                        view.promptUserMoneyInput();
                        moneyDeposited = view.promptUserMoneyInput();
                        break;

                    case 2:
                        viewAndBuyItems();
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
        } catch (VendingMachineNoItemInventoryException e) {
            throw new RuntimeException(e);
        }
    }

//    public BigDecimal addFunds(BigDecimal balance) {
//        view.promptUserMoneyInput();
//        service.getMoneyFromUser();
//        Scanner scanner = new Scanner(System.in);
//        BigDecimal moneyAdded;
//        moneyAdded = BigDecimal.valueOf(scanner.nextFloat());
//        return moneyAdded;
//    }

    private BigDecimal viewAndBuyItems() throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
        BigDecimal userFunds = view.promptUserMoneyInput();
        view.displayUserMoneyInput(userFunds);

        List<Item> showListOfItemsToBuy = service.listAllItemsToBuy();
        view.displayItemList(showListOfItemsToBuy);
        int getChosenItem = view.promptUserItemChoice();

        // String getChosenItem =view.displayUserChoiceOfItem(String.valueOf(showListOfItemsToBuy));
        Item chosenItem = service.listAllItemsToBuy().get(getChosenItem);
//        service.updateItemSale("" + getChosenItem);
//        service.calculateChange(userFunds, chosenItem);
        adao.writeAuditEntry("purchased item " + chosenItem.getItemName() + " for $" + chosenItem.getItemPrice() + " user funds = " + userFunds);

        return userFunds;
//        String userItemSelection = view.displayUserChoiceOfItem(input);
//        Item itemUserSelected = showListOfItemsToBuy.get(userItemSelection);


    }

//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            view.promptUserItemChoice();
//            scanner.nextLine();
//            try {
//                String myItem = service.getChosenItem(itemId);
//                view.displayUserChoiceOfItem(item);
//
//            } catch (VendingMachineNoItemInventoryException exception) {
//            }
//        }
//    }

    public boolean didUserPutSufficientFundsIn(BigDecimal userAmount, Item item) {
        try {
            service.checkSufficientMoneyToBuyItem(userAmount, String.valueOf(item));
            return true;
        } catch (VendingMachineInsufficientFundsException ex) {
            view.displayErrorMessage("Not enough money to buy!");
            view.displayUserMoneyInput(userAmount);
            return false;
        }
    }

    public void displayChangeReturnedToUser(BigDecimal amount, Item item) {
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



