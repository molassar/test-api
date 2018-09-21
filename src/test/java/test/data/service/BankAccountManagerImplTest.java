package test.data.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class BankAccountManagerImplTest {

    private static final int ACC_NUM = 10_000;

    @Autowired
    private BankAccountManager bankAccountManager;

    @Test
    public void create() {
        assertTrue(bankAccountManager.create(ACC_NUM));
    }

    @Test
    public void createDuplicate() {
        assertTrue(bankAccountManager.create(ACC_NUM));
        assertFalse(bankAccountManager.create(ACC_NUM));
    }

    @Test
    public void deposit() {
        bankAccountManager.create(ACC_NUM);
        assertTrue(bankAccountManager.deposit(ACC_NUM, 10));
    }

    @Test
    public void depositIllegal() {
        bankAccountManager.create(ACC_NUM);
        assertFalse(bankAccountManager.deposit(ACC_NUM, -10));
    }

    @Test
    public void withdraw() {
        bankAccountManager.create(ACC_NUM);
        bankAccountManager.deposit(ACC_NUM, 10);
        assertTrue(bankAccountManager.withdraw(ACC_NUM, 10));
    }

    @Test
    public void withdrawIllegal() {
        bankAccountManager.create(ACC_NUM);
        assertFalse(bankAccountManager.withdraw(ACC_NUM, 10));
    }

    @Test
    public void getBalance() {
        bankAccountManager.create(ACC_NUM);
        assertThat(bankAccountManager.getBalance(ACC_NUM), is(0));
        bankAccountManager.deposit(ACC_NUM, 10);
        assertThat(bankAccountManager.getBalance(ACC_NUM), is(10));
        bankAccountManager.withdraw(ACC_NUM, 10);
        assertThat(bankAccountManager.getBalance(ACC_NUM), is(0));
    }
}