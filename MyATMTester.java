import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Yu Xiu
 * CS151 Sec02
 * Assignment #2 My ATM Network System
 *
 * MyATMTester class contains main class
 * It provides options for users to test features of the system
 * There are two use cases, the first one is to open account, and the second one is to make a withdrawal
 */
public class MyATMTester {

    public static void main (String[] args) {
        Bank bankA = new Bank("A");
        Bank bankB = new Bank("B");
        ATM atmA1 = new ATM("ATM_A1", bankA);
        ATM atmA2 = new ATM("ATM_A2", bankA);
        ATM atmB1 = new ATM("ATM_B1", bankB);
        ATM atmB2 = new ATM("ATM_B2", bankB);
        HashMap<String,CashCard> map = new HashMap<String, CashCard>();
        HashMap<String, ATM> aTMMap = new HashMap<String, ATM>();
        aTMMap.put("ATM_A1", atmA1);
        aTMMap.put("ATM_A2", atmA2);
        aTMMap.put("ATM_B1", atmB1);
        aTMMap.put("ATM_B2", atmB2);

        // Print out the options for users to choose and go into different condition
        while (true) {
            System.out.println("------------------------------------------------------\n" +
                    "Select one of the following options: \n[O]pen account or [W]ithdrawal " +
                    "(Enter O to open, enter W to withdraw): ");
            Scanner inpt = new Scanner(System.in);
            String input = inpt.nextLine();
            if (input.equals("")) {
                continue;
            }
            // User decides to open an account either with bank A or B
            if (input.equals("O")) {
                System.out.println("Select a bank: A or B:");
                Scanner sc1 = new Scanner(System.in);
                String bankid = sc1.nextLine();
                Bank bank = null;
                if (bankid.equals("A")) {
                    bank = bankA;
                } else if (bankid.equals("B")) {
                    bank = bankB;
                } else {
                    // Deal with exceptions
                    System.out.println("No such bank.");
                    continue;
                }
                // User enter password
                System.out.println("Enter your password using integers only (example: 123):");
                Scanner in = new Scanner(System.in);
                String password = in.nextLine();
                // Open account
                CashCard card = bank.openAccount(password);
                // Save the card to bank
                map.put(bank.getBankId() + Integer.toString(card.getAccountNumber()),card);
                System.out.println("\nCongratulations! Here is your account information with Bank " + bankid
                        +": \nCard, expire date, password: "+ card);

            }
            // User decides to make a transaction
            if (input.equals("W")) {
                System.out.println("------------------------------------------------------\n" +
                        "Enter your choice of ATM (example: ATM_A1): ");
                Scanner userInput = new Scanner(System.in);
                String typeOfATM = userInput.nextLine();

                ATM atmT = null;
                // If the correct ATM was found
                if (aTMMap.containsKey(typeOfATM)) {
                    atmT = aTMMap.get(typeOfATM);
                    System.out.println("------------------------------------------------------\n" +
                            "Enter your card (example:A101): ");
                    Scanner sc1 = new Scanner(System.in);
                    String cardInput = sc1.nextLine();
                    // Check validation of card
                    boolean check = atmT.isCardOk(map.get(cardInput));
                    if (!check) {
                        continue;
                    }
                    System.out.println("The card is accepted. Please enter your password: ");
                    Scanner sc3 = new Scanner(System.in);
                    String password = sc3.nextLine();
                    // Check password
                    boolean okPassword = aTMMap.get(typeOfATM).isPasswordCorrect(password,map.get(cardInput));
                    if (!okPassword) {
                        continue;
                    }

                    Scanner input2 = new Scanner(System.in);
                    int inputAmount = input2.nextInt();
                    // Check amounts whether exceeds the limitation
                    boolean okAmount= atmT.checkAmount(inputAmount,map.get(cardInput));
                    boolean isQuit = false;
                    while (!okAmount) {
                        System.out.println("Enter the integer amount again or enter Q for quit");
                        Scanner sc4 = new Scanner(System.in);
                        String quit = sc4.nextLine();
                        // User quits
                        if (quit.equals("Q")) {
                            isQuit = true;
                            break;
                        } else {
                            try {
                                inputAmount = Integer.parseInt(quit);
                                // Check amounts
                                boolean amountOk = aTMMap.get(typeOfATM).checkAmount(inputAmount, map.get(cardInput));
                                if (amountOk) {
                                    //break;
                                }
                            } catch (NumberFormatException e){
                                System.out.println("Error");
                            }
                        }
                    }
                    if (isQuit) {
                        continue;
                    }
                }
                // Deal with invalid ATM
                if (atmT == null) {
                    System.out.println("Error, no such ATM.");
                }
            }

            continue;
        }
    }
}
