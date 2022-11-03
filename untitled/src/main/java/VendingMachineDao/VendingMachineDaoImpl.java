package VendingMachineDao;

import UserInputOutput.UserInputOutputIF;
import VendingMachionDto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineDaoImpl implements VendingMachineDaoIF {

    public final String ITEMS_FILE;
    public final String DELIMITER = "::";


    public VendingMachineDaoImpl(UserInputOutputIF myIo) {
        ITEMS_FILE = "items.txt";
    }

    private Map<String, Item> mapOfLoadedItems = new HashMap<>();

    @Override
    public Item addItemAsOwner(String itemId, Item item) {
        Item itemAdded= mapOfLoadedItems.put(itemId, item);
        return itemAdded;
    }

    @Override
    public List<Item> getAllItems() {
       return new ArrayList<Item>(mapOfLoadedItems.values());
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
        Scanner scanner;
        try{
            scanner = new Scanner(
                    new BufferedReader(new FileReader(ITEMS_FILE)));
        } catch (FileNotFoundException e){
            throw new VendingMachinePersistenceException("Could not load items in file", e);
        }
        String currentLine;
        Item currentItem = null;
        while (scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            String[] splitInfo = currentLine.split(DELIMITER);
            String itemName = splitInfo[0];
            BigDecimal itemPrice = new BigDecimal(splitInfo[1]);
            int itemsInStock = Integer.parseInt(splitInfo[2]);
            Item itemToAdd = new Item(itemName, itemPrice, itemsInStock);
            mapOfLoadedItems.put(currentItem.getItemId(), currentItem);
        }
        scanner.close();
        return mapOfLoadedItems;
    }

    @Override//Marshaling - writing data to the file
    public void writeItemsToFile() throws VendingMachinePersistenceException {
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(ITEMS_FILE));
        }catch (IOException e){
            throw new VendingMachinePersistenceException(e.getMessage());
        }
        for(Item currentItem : mapOfLoadedItems.values()) {
            mapOfLoadedItems.get(currentItem);
        }
        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for(Item currentItem : mapOfLoadedItems.values()){

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
