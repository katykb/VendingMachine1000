package VendingMachineDao;

import VendingMachionDto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineDaoImpl implements VendingMachineDaoIF {

    public static final String ITEMS_FILE = "myitems.txt";
    public static final String DELIMITER = "::";
    private Map<String, Item> mapOfLoadedItems = new HashMap<>();




//    @Override
//    public Item addItemAsOwner(String itemId, Item item) {
//        Item itemAdded = mapOfLoadedItems.put(itemId, item);
//        return itemAdded;
//    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadItemsFromFile();
        return new ArrayList<>(mapOfLoadedItems.values());
    }

    @Override
    public List<String> getAllItemIds() {
        return new ArrayList<>(mapOfLoadedItems.keySet());
    }

    @Override
    public Item getItem(String itemId) {
        return mapOfLoadedItems.get(itemId);
    }

    @Override
    public Item updateItem(String itemId, Item item) {
        return mapOfLoadedItems.replace(itemId, item);
    }

    @Override
    public Item removeItem(String itemId) {
        Item removedItem = mapOfLoadedItems.remove(itemId);
        return removedItem;
    }

    @Override
    public Map<String, Item> loadItemsFromFile() throws VendingMachinePersistenceException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(ITEMS_FILE));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
//
        String currentLine;
        Item currentItem;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            String[] splitInfo = currentLine.split(DELIMITER);
            String itemId = splitInfo[0] + DELIMITER;
            String itemName = splitInfo[1] + DELIMITER;
            BigDecimal itemPrice = new BigDecimal (splitInfo[2] );
            int itemsInStock = Integer.parseInt(splitInfo[3]);
            Item itemToAdd = new Item(itemId, itemName, itemPrice, itemsInStock);
            mapOfLoadedItems.put(itemToAdd.getItemId(), itemToAdd);

        }

        scanner.close();
        return mapOfLoadedItems;
    }

    @Override//Marshaling - writing data to the file
    public void writeItemsToFile() throws VendingMachinePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ITEMS_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(e.getMessage());
        }
        for (Item currentItem : mapOfLoadedItems.values()) {
            mapOfLoadedItems.get(currentItem);
        }
        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : mapOfLoadedItems.values()) {

            out.println(currentItem);
        }
        //private String marshalItemsAsText(Item aItem){
        //get past try cant, outputFileWriting2 is our actual file
        //Write to the file
        //Done writing to my output file, flush and close it out
        //Need to write from our dao(loadItemsFromFile) to our
        out.close();
    }
}
