package VendingMachineService;

import VendingMachineDao.VendingMachinePersistenceException;
import VendingMachionDto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface VendingMachineServiceIF {

    public Map<String, Item> loadItemsInStock() throws VendingMachinePersistenceException;

    public List<Item> listAllItemsToBuy() throws VendingMachinePersistenceException;

    public void saveItemList() throws VendingMachinePersistenceException;

    public String getChosenItem(String itemId) throws VendingMachineNoItemInventoryException;

    public void checkSufficientMoneyToBuyItem(BigDecimal amount, String itemId) throws VendingMachineInsufficientFundsException;

    public void updateItemSale(String item) throws VendingMachineNoItemInventoryException, VendingMachinePersistenceException;

    public void updateSoldItem(Item item) throws VendingMachinePersistenceException;

    public BigDecimal calculateChange(BigDecimal amount, Item item);

    public BigDecimal getMoneyFromUser();

}
