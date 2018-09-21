package test.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import test.data.repository.BankAccountRepository;
import test.data.service.BankAccountManager;
import test.data.service.BankAccountManagerImpl;
import test.data.swagger.config.SwaggerConfig;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Autowired
    public BankAccountManager bankAccountManager(BankAccountRepository repository) {
        return new BankAccountManagerImpl(repository);
    }

}
