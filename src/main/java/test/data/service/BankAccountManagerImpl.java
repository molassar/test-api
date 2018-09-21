package test.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.data.model.BankAccount;
import test.data.repository.BankAccountRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class BankAccountManagerImpl implements BankAccountManager {

    private static final int START_BALANCE = 0;
    private static final int UNKNOWN_ACCOUNT_BALANCE = -1;

    private final BankAccountRepository repository;

    @Autowired
    public BankAccountManagerImpl(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean create(int number) {
        if (repository.existsById(number)) {
            return false;
        } else {
            BankAccount bankAccount = new BankAccount(number, START_BALANCE);
            repository.save(bankAccount);
            return true;
        }
    }

    @Override
    public boolean deposit(int number, int amount) {
        if (!validateAmount(amount) || !repository.existsById(number)) {
            return false;
        } else {
            Optional<BankAccount> bankAccountO = repository.findById(number);
            if (bankAccountO.isPresent()) {
                BankAccount bankAccount = bankAccountO.get();
                bankAccount.setBalance(bankAccount.getBalance() + amount);
                repository.save(bankAccount);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean withdraw(int number, int amount) {
        if (!validateAmount(amount) || !repository.existsById(number)) {
            return false;
        } else {
            Optional<BankAccount> bankAccountO = repository.findById(number);
            if (bankAccountO.isPresent() && bankAccountO.get().getBalance() >= amount) {
                BankAccount bankAccount = bankAccountO.get();
                bankAccount.setBalance(bankAccount.getBalance() - amount);
                repository.save(bankAccount);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public int getBalance(int number) {
        return repository.findById(number).map(BankAccount::getBalance).orElse(UNKNOWN_ACCOUNT_BALANCE);
    }

    private boolean validateAmount(int amount) {
        return amount >= 0;
    }
}
