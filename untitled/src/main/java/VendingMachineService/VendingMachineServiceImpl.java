package VendingMachineService;

import UserInputOutput.VendingMachineView;
import VendingMachineDao.VendingMachineAuditDaoIF;
import VendingMachineDao.VendingMachineDaoIF;
import VendingMachineDao.VendingMachinePersistenceException;
import VendingMachionDto.Change;
import VendingMachionDto.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineServiceImpl implements VendingMachineServiceIF {

    VendingMachineDaoIF dao;
    VendingMachineAuditDaoIF adao;
    VendingMachineView view;

    public VendingMachineServiceImpl(VendingMachineDaoIF dao, VendingMachineAuditDaoIF adao) {
        this.dao = dao;
        this.adao = adao;
    }


    Map<String, Item> itemsInStockToBuy = new HashMap<>();

    @Override
    public Map<String, Item> loadItemsInStock() throws VendingMachinePersistenceException {

        for (Item i : dao.loadItemsFromFile().values()) {
            if (i.getItemsInStock() > 1) {
                adao.writeAuditEntry("Item id: " + i.getItemId() + "quantity in stock is: " + i.getItemsInStock());
            } else {
                itemsInStockToBuy.put(i.getItemId(), i);
            }
        }
        return itemsInStockToBuy;
    }


    @Override
    public void saveItemList() throws VendingMachinePersistenceException {
        dao.writeItemsToFile();
        adao.writeAuditEntry();

    }

    @Override
    public String getChosenItem(String itemId) throws VendingMachineNoItemInventoryException {
        validateItemInStock(String.valueOf(itemId));
        return itemId;
    }

    @Override
    public void checkSufficientMoneyToBuyItem(BigDecimal amount, String itemId) throws VendingMachineInsufficientFundsException {

    }

//    @Override
//    public void checkSufficientMoneyToBuyItem(BigDecimal amount, String itemName) throws VendingMachineInsufficientFundsException {
//        if (amount.compareTo(itemName.getItemPrice()) < 0) {
//            throw new VendingMachineInsufficientFundsException("Not enough money to purchase item " + itemName.getItemName());
//        }
//    }

    @Override
    public void updateItemSale(String itemId) throws VendingMachineNoItemInventoryException, VendingMachinePersistenceException {
        List<String> ids = dao.getAllItemIds();
        Item item = dao.getItem(itemId);
        if (!ids.contains(itemId) || (item.getItemsInStock() < 1)) {
            throw new VendingMachineNoItemInventoryException("The item you selected is not in stock.");
        }
    }


    @Override
    public BigDecimal calculateChange(BigDecimal amount, Item item) {
        BigDecimal change = amount.subtract(item.getItemPrice().multiply(new BigDecimal("100")));
        new Change(change);
        return change;
    }

    private void validateItemInStock(String itemId) throws VendingMachineNoItemInventoryException {
        List<String> ids = dao.getAllItemIds();
        Item item = dao.getItem(itemId);
        if (!ids.contains(itemId) || (item.getItemsInStock() < 1)) {
            throw new VendingMachineNoItemInventoryException("The item you selected is not in stock.");
        }
    }

    @Override
    public List<Item> listAllItemsToBuy() throws VendingMachinePersistenceException{
        return dao.getAllItems();
    }

    @Override
    public BigDecimal getMoneyFromUser(){
        BigDecimal moneyInput = view.promptUserMoneyInput();
        return moneyInput;
    }
}


