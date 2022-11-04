package UserInputOutput;

import VendingMachineService.VendingMachineServiceIF;
import VendingMachionDto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView {

    private UserInputOutputIF io;
    private VendingMachineServiceIF service;

    public VendingMachineView(UserInputOutputIF io) {
        this.io = io;
    }

    public void displayVendingMachineWelcome() {
        io.print("Hello and Welcome to Vending Machine1000!");
    }

    public int displayMainMenuAndGetSelection() {
        io.print("Vending Machine1000 Main Menu");
        io.print("=================================");
        io.print("1. View and Buy an Item");
        io.print("2. Add Money");
        io.print("3. Exit Machine");

        return io.readInt("Please select from the menu", 1, 3);
    }

    public void displayItemList(List<Item> itemList) {
        for (Item currentItem : itemList) {
            String itemInfo = String.format("#%s : %s %s",
                    currentItem.getItemId(),
                    currentItem.getItemName(),
                    currentItem.getItemPrice());
            io.print(itemInfo);
        }
        io.readString("Please hit enter to continue");

    }


    public BigDecimal promptUserMoneyInput() {
        String moneyDeposited = io.readString("How much money do you want to deposit?");
        BigDecimal userMoney;
        userMoney = new BigDecimal(moneyDeposited);
        return userMoney;
    }

    public void displayUserMoneyInput(BigDecimal moneyDeposited) {
        io.print("You have deposited: " + moneyDeposited);
    }

    public void promptUserItemChoice() {

        io.print("Please enter a selection from the item menu. Please enter a number.");
    }

    public void displayUserChoiceOfItem(String item) {
        io.print("You have selected: " + item);
    }
    public void displayChangeReturnedToCustomer(BigDecimal moneyDeposited, String chosenItem) {
    }

    public void displayFinalMessage() {
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
