import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Bank class defines max amount of the Bank and check whether user's transaction amount exceed the amount
 * Opens account and issue cards, then save the card in the list
 * Provides APIs
 *
 */
public class Bank {
    private String bankId;
    ArrayList<CashCard> list;

    /**
     * Constructor
     * @param bankId String bank id
     */
    public Bank (String bankId) {
        this.bankId = bankId;
        list = new ArrayList<CashCard>();
    }

    /**
     * Get bank id
     * @return String of bank id
     */
    public String getBankId() {
        return bankId;
    }

    /**
     * Open account and issue a cash card, then add to the card list of the bank
     * Initialize card number as 100, and find the largest card number in the card list
     * then a new card's number would be the max card number + 1. Example: card number 101, 102
     * @param password String of password
     * @return
     */
    public CashCard openAccount(String password) {
        // Initialize the card number
        int maxCardNumber = 100;
        for (int i = 0; i < list.size(); i++) {
            CashCard card = list.get(i);
            if (maxCardNumber < card.getAccountNumber()) {
                maxCardNumber = card.getAccountNumber();
            }
        }
        // The new account number creates by adding 1 to the the max account number
        maxCardNumber++;
        LocalDate date =  LocalDate.now();
        LocalDate expireDate = date.plusDays(90);
        CashCard issueCard = new CashCard(bankId,maxCardNumber,password,expireDate);
        // Save card to list
        list.add(issueCard);
        return issueCard;
    }

    /**
     * Check the validation of a withdrawal
     * @param wa integer amount of withdrawal
     * @param card CashCard
     */
    public void withdrawal(int wa, CashCard card) {
        // If withdrawal less than the amount balance, issue the transaction
        if (wa <= card.getAmount()) {
            card.setAmount(card.getAmount() - wa);
            System.out.println("$" + wa + " is withdrawn from your account. " +
                    "The remaining balance of this account is $" + card.getAmount() + " ." +
                    "\nIf you have more transactions, enter the amount or quit");
        }
        // Exceeds the balance
        else {
            if (wa > card.getAmount()) {
                System.out.println("The amount exceeds the current balance.");
            }
        }
    }

    /**
     * Convert to String
     * @return String
     */
    public String toString() {
        return bankId;
    }
}
