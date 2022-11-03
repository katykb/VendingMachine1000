package VendingMachionDto;

import java.math.BigDecimal;

public class Change {

    private static int quarters;
    private static int dimes;
    private static int nickles;
    private static int pennies;

    //constructor to initialize the private change variables
    public Change(BigDecimal amount){
        this.quarters = amount.divide(new BigDecimal("25")).intValue();
        amount = amount.remainder(new BigDecimal("25"));
        this.dimes = amount.divide(new BigDecimal("10")).intValue();
        amount = amount.remainder(new BigDecimal("10"));
        this.nickles = amount.divide((new BigDecimal("5"))).intValue();
        amount = amount.divide(new BigDecimal("5"));
        this.pennies = amount.remainder(new BigDecimal("1")).intValue();

    }

    public static int getQuarters() {
        return quarters;
    }

    public static int getDimes() {
        return dimes;
    }

    public static int getNickles() {
        return nickles;
    }

    public static int getPennies() {
        return pennies;
    }
}

