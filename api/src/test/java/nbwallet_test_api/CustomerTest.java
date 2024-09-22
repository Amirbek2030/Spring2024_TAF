package nbwallet_test_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import nb_wallet_api.asserts.ApiAssert;
import nb_wallet_api.controllers.CustomerController;
import nb_wallet_api.entity.CustomerLogin;
import nb_wallet_api.entity.CustomerSignUp;
import nb_wallet_api.entity.account.AccountAuthLogin;
import nb_wallet_api.entity.account.AccountResponse;
import nb_wallet_api.utils.EntityManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class CustomerTest extends NBApiTest {
    CustomerController customerController;
    CustomerSignUp generatedCustomer;
    AccountResponse accounts;

    @BeforeTest(alwaysRun = true)
    public void beforeClass() {
        customerController = application.getCustomerController();
    }

    @Test
        public void customerSignUp() {
        generatedCustomer = EntityManager.generateCustomer();
        customerController.signUpCustomer(generatedCustomer);
    }

    @Test(description = "Customer login", dependsOnMethods = {"customerSignUp"})
    public void customerLoginTest() {
        customerController.customerLogin(CustomerLogin.builder()
                .userName(generatedCustomer.getEmail())
                .password(generatedCustomer.getPassword())
                .build());
    }

    @Test(dependsOnMethods = {"customerLoginTest"})
    public void testEnterCustomer() throws JsonProcessingException {
        AccountAuthLogin accountAuthLogin = new AccountAuthLogin();
        accountAuthLogin.setUserName("amirbek@mail.ru");
        accountAuthLogin.setPassword("Admin12345");

        customerController.enterCustomer(accountAuthLogin);

        ApiAssert.assertThat(customerController.getResponse())
                .isCorrectStatusCode(200);
    }

    @Test(dependsOnMethods = {"customerLoginTest"})
    public void testAddFunds() throws JsonProcessingException {
        String accountNumber = "00000000114";
        String amount = "1000"; // Предполагаем, что сумма – это строка

        customerController.addFunds(accountNumber, amount);

        ApiAssert.assertThat(customerController.getResponse())
                .isCorrectStatusCode(200);
    }

}

