package test.data.service;

public interface BankAccountManager {

    /**
     * Create new bank account.
     *
     * @param number - the number of a new bank account
     * @return true if success
     */
    boolean create(int number);

    /**
     * Deposit.
     *
     * @param number - the number of the bank account
     * @param amount - the amount to deposit
     * @return true if success
     */
    boolean deposit(int number, int amount);

    /**
     * Withdraw.
     *
     * @param number - the number of the bank account
     * @param amount - the amount to withdraw
     * @return true if success
     */
    boolean withdraw(int number, int amount);

    /**
     * Get balance.
     *
     * @param number - the number of the bank account
     * @return negative integer if the bank account with the number does not exist
     */
    int getBalance(int number);
}
