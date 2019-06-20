import java.time.LocalDate;
import java.util.ArrayList;

/**
 * ATM class defines the max amount of cash the user could withdraw per transaction
 * It reads cash card number, checks validation (passwords, expire dates)
 * and interact with Bank, Card and users
 *
 */
public class ATM {
    private int maxAmount = 50; // max amount per transaction of withdrawal is 50
    private String ATMType;
    private LocalDate currentDate = LocalDate.now();
    private Bank bank;
    ArrayList<CashCard> cardList;

    /**
     * Constructor
     * @param ATMType
     */
    public ATM (String ATMType, Bank bank) {
        this.ATMType = ATMType;
        this.bank = bank;
        cardList = new ArrayList<CashCard>();
    }

    /**
     * Print out the max amount of per transaction
     */
    public void printMaxAmount() {
        System.out.println("The maximum amount of a cash card can withdraw per transaction: $" + maxAmount);
    }

    /**
     * Check whether the card is supported by the ATM and expire date
     * @param card CashCard type
     * @return boolean type
     */
    public boolean isCardOk(CashCard card) {
        if (!bank.list.contains(card)) {
            System.out.println("This card is not supported by this ATM.");
            return false;
        }
        CashCard tempCard = null;
        for (int i = 0; i <= bank.list.size(); i++) {
            CashCard cardT = bank.list.get(i);
            int accountNumber1 = card.getAccountNumber();
            int accountNumber2 = cardT.getAccountNumber();
            if(accountNumber1 == accountNumber2) {
                tempCard = cardT;
                break;
            }
        }
        if(tempCard == null) {
            System.out.println("This ATM.");
            return false;
        }
        LocalDate checkDate = card.getExpireDate();
        boolean isExpire = checkExpireAndPrint(checkDate);
        if (isExpire){
            return false;
        }
        return true;
    }

    /**
     * Check transaction authorization and withdraw
     * If the input transaction is less than the max amount of the ATM day transaction, the transaction is authorized
     * If the input transaction is more than the max amount, the error message would be displayed
     * @param amount integer amount to withdraw
     * @param card CashCard
     * @return
     */
    public boolean checkAmount(int amount, CashCard card) {
        if (amount <= maxAmount) {
            if (amount <= card.getAmount()) {
                bank.withdrawal(amount,card);
               // System.out.println("@@ ");
                return true;
            }

        } else {
            System.out.println("This amount exceeds the maximum amount you can withdraw per transaction.");
            return false;
        }
        return true;
    }


    /**
     * check validation of expire date and print string and return card to user
     * @param expireDate LocalDate
     * @return
     */
    public boolean checkExpireAndPrint(LocalDate expireDate) {
        //System.out.println(currentDate + ", " + expireDate);
        if (expireDate.compareTo(currentDate) > 0) {
            return false;
        } else {
            System.out.println("This card is expired and returned to you.");

        }
        return true;
    }

    /**
     * Check password
     * @param password String, which associated with card
     * @param card CashCard
     * @return
     */
    public boolean isPasswordCorrect(String password, CashCard card) {
        if (card.isPasswordCorrect(password)) {
            System.out.println("Authorization is accepted. Start your transaction by entering the amount (integers) " +
                    "to withdraw (example: 20).");
            return true;
        } else {
            System.out.println("This is a wrong password. Please reselect your option.");
            return false;
        }

    }

    /**
     * Convert variables to string
     * @return
     */
    public String toString () {
        return ATMType;
    }
}
