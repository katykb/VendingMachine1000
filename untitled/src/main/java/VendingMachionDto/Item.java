package VendingMachionDto;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    private String itemId;
    private String itemName;
    private BigDecimal itemPrice;
    private int itemsInStock;

    public Item(String itemId, String itemName, BigDecimal itemPrice, int itemsInStock){
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice =itemPrice;
        this.itemsInStock = itemsInStock;
    }

    public Item(String itemName, BigDecimal itemPrice, int itemsInStock) {
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemsInStock() {
        return itemsInStock;
    }

    public void setItemsInStock(int itemsInStock) {
        this.itemsInStock = itemsInStock;
    }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.itemId);
        hash = 89 * hash + Objects.hashCode(this.itemName);
        hash = 89 * hash + Objects.hashCode(this.itemPrice);
        hash = 89 * hash + this.itemsInStock;
        return hash;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        final Item other = (Item) obj;
        if(this.itemsInStock != other.itemsInStock){
            return false;
        }
        if (!Objects.equals(this.itemId, other.itemId)){
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)){
            return false;
        }
        if(!Objects.equals(this.itemPrice, other.itemPrice)){
            return false;
        }
        return true;
    }



}

   // private final String DELIMITER = "::";
//
//    public String marhalItemsAsText(String itemAsText) throws VendingMachinePersistenceException {
//        try{
//            String[] fields = itemAsText.split(DELIMITER);
//            this.itemId = fields[0];
//            this.itemName = fields[1];
//            this.price = new BigDecimal(fields[2]);
//            this.itemsInStock = Integer.parseInt(fields[3]);
//        }catch (PatternSyntaxException ex) {
//            throw new VendingMachinePersistenceException(ex.getMessage());
//        }catch (NullPointerException | NumberFormatException exception){
//            throw new VendingMachinePersistenceException(exception.getMessage());
//        }
//
//        return unmarshallItem(itemAsText);
//    }
//
//    private String unmarshallItem(String itemAsText) {
//        String[] itemTokens = itemAsText.split(DELIMITER);
//        String itemId = itemTokens[0];
//        Item itemFromFile = new Item(itemId);
//        itemFromFile.setItemName(itemTokens[1]);
//        itemFromFile.setPrice(new BigDecimal(itemTokens[2]));
//        itemFromFile.
//
//    }
