package test.data.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.data.service.BankAccountManager;

import static java.util.Collections.singletonMap;

@RestController
@RequestMapping("bankaccount")
public class BankAccountController {

    private BankAccountManager bankAccountManager;

    @ApiOperation(value = "Зарегистрировать счет")
    @PostMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity createAccount(@PathVariable int id) {
        if (bankAccountManager.create(id)) {
            return ResponseEntity.ok(singletonMap("result", "OK"));
        } else {
            return ResponseEntity.badRequest().body(singletonMap("error", "Account registration failed"));
        }
    }

    @ApiOperation(value = "Зачислить средства на счет")
    @PutMapping(value = "/{id}/deposit/{amount}", produces = "application/json")
    public ResponseEntity deposit(@PathVariable int id, @PathVariable int amount) {
        if (bankAccountManager.deposit(id, amount)) {
            return ResponseEntity.ok(singletonMap("result", "OK"));
        } else {
            return ResponseEntity.badRequest().body(singletonMap("error", "Deposit failed"));
        }
    }

    @ApiOperation(value = "Снять средства со счета")
    @PutMapping(value = "/{id}/withdraw/{amount}", produces = "application/json")
    public ResponseEntity withdraw(@PathVariable int id, @PathVariable int amount) {
        if (bankAccountManager.withdraw(id, amount)) {
            return ResponseEntity.ok(singletonMap("result", "OK"));
        } else {
            return ResponseEntity.badRequest().body(singletonMap("error", "Withdrawal failed"));
        }
    }

    @ApiOperation(value = "Получить баланс счета")
    @GetMapping(value = "/{id}/balance", produces = "application/json")
    public ResponseEntity getBalance(@PathVariable int id) {
        int result = bankAccountManager.getBalance(id);
        if (result < 0) {
            return ResponseEntity.badRequest().body(singletonMap("error", "Unknown account number"));
        } else {
            return ResponseEntity.ok(singletonMap("result", result));
        }
    }


    @Autowired
    public void setBankAccountManager(BankAccountManager bankAccountManager) {
        this.bankAccountManager = bankAccountManager;
    }
}
