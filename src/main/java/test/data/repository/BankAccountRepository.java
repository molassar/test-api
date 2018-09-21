package test.data.repository;

import org.springframework.data.repository.CrudRepository;
import test.data.model.BankAccount;

public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {
}
