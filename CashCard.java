import java.time.LocalDate;

/**
 * CashCard class stores cards' information
 * Defines password and expire date of a card and checks if it is a valid card
 * Provides API for ATM and Bank classes
 *
 */
public class CashCard {
    private int amount = 40;
    private int accountNumber;
    private String bankId;
    private String password;
    private LocalDate expireDate;
    private boolean isValid;

    /**
     * Constructor of the class
     * @param bankId could be A or B
     * @param accountNumber integers
     * @param password integers
     * @param expireDate a date
     */
    public CashCard(String bankId, int accountNumber, String password, LocalDate expireDate) {
        this.bankId = bankId;
        this.accountNumber = accountNumber;
        this.password = password;
        this.expireDate = expireDate;
    }

    /**
     * Set account number
     * @param accountNumber integers
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Set Bank id
     * @param bankId string
     */
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    /**
     * Set expire date for a card or account
     * @param expireDate LocalDate
     */
    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Set password for a card
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set amount for a card
     * @param amount integer amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Get amount number of card
     * @return account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Get expire date of card
     * @return expire date
     */
    public LocalDate getExpireDate() {
        return expireDate;
    }

    /**
     * Get balance amount of card
     * @return balance amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Check correction of password
     * @param password String
     * @return true
     */
    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }

    /**
     * Get bank id
     * @return bank id
     */
    public String getBankId() {
        return bankId;
    }

    /**
     * check valid
     * @return
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * Convert variables to string
     * @return
     */
    public String toString () {
        return bankId + accountNumber+ ", " + expireDate.toString() + ", " + password;
    }

}

