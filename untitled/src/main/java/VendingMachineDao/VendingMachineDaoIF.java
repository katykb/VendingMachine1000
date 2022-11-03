package VendingMachineDao;

import VendingMachionDto.Item;

import java.util.List;
import java.util.Map;

public interface VendingMachineDaoIF {

    Item addItemAsOwner(String itemId, Item item);

    List<Item> getAllItems();

    List<String > getAllItemIds();

    Item getItem(String itemId);

    Item updateItem(String itemId, Item item);

    Item removeItem(String itemId);

    Map<String, Item> loadItemsFromFile() throws VendingMachinePersistenceException;

    void writeItemsToFile() throws VendingMachinePersistenceException;
}
