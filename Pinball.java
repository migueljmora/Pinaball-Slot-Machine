import java.util.Random;
import java.util.Scanner;

public class PinballSlotMachine {
    private static final String[] SYMBOLS = {"CHERRY", "BAR", "BARBAR", "BARBARBAR", "7", "DOUBLE DIAMOND"};
    private static final int[] PAYOUTS = {2, 3, 4, 5, 10, 50}; // Payouts for each symbol
    
    private static final int INITIAL_BALANCE = 100;
    private static final int MIN_BET = 1;
    private static final int MAX_BET = 10;
    
    private int balance;
    private Random random;

    public PinballSlotMachine() {
        this.balance = INITIAL_BALANCE;
        this.random = new Random();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Pinball Slot Machine!");
        
        while (balance > 0) {
            System.out.println("Balance: $" + balance);
            System.out.println("Place your bet (Minimum bet: $" + MIN_BET + ", Maximum bet: $" + MAX_BET + "):");
            int bet = scanner.nextInt();
            
            if (bet < MIN_BET || bet > MAX_BET) {
                System.out.println("Invalid bet amount.");
                continue;
            }
            
            if (bet > balance) {
                System.out.println("Insufficient balance.");
                continue;
            }
            
            balance -= bet;
            spin();
            System.out.println("Spin result: ");
            displaySymbols();
            int winnings = calculateWinnings();
            
            if (winnings > 0) {
                balance += bet * winnings;
                System.out.println("Congratulations! You won $" + bet * winnings);
            } else {
                System.out.println("Better luck next time!");
            }
        }
        
        System.out.println("Game over. Your balance is $0.");
        scanner.close();
    }

    private void spin() {
        System.out.println("Spinning the reels...");
    }

    private void displaySymbols() {
        for (int i = 0; i < 3; i++) {
            System.out.print(SYMBOLS[random.nextInt(SYMBOLS.length)] + " ");
        }
        System.out.println();
    }

    private int calculateWinnings() {
        String[] symbols = new String[3];
        for (int i = 0; i < symbols.length; i++) {
            symbols[i] = SYMBOLS[random.nextInt(SYMBOLS.length)];
        }
        
        // Check for winning combinations
        if (symbols[0].equals(symbols[1]) && symbols[1].equals(symbols[2])) {
            for (int i = 0; i < SYMBOLS.length; i++) {
                if (symbols[0].equals(SYMBOLS[i])) {
                    return PAYOUTS[i];
                }
            }
        }
        
        return 0;
    }

    public static void main(String[] args) {
        PinballSlotMachine game = new PinballSlotMachine();
        game.play();
    }
}

