package nb_wallet_api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import nb_wallet_api.entity.CustomerLogin;
import nb_wallet_api.entity.CustomerSignUp;

import nb_wallet_api.entity.account.AccountAddFunds;
import nb_wallet_api.entity.account.AccountAuthLogin;
import nb_wallet_api.entity.account.AccountResponse;
import nb_wallet_api.utils.ApiRequest;
import java.util.HashMap;
import java.util.Map;

import static nb_wallet_api.enums.NBWalletEndPoints.*;
import static nb_wallet_api.enums.NBWalletEndPoints.API;
import static nb_wallet_api.enums.NBWalletEndPoints.V1;

@Slf4j
public class CustomerController extends ApiRequest {
    private String customerToken;

    public CustomerController(String url) {
        super(url);
    }

    public void signUpCustomer(CustomerSignUp customer) {
        HashMap<String, String> params = new HashMap<>();
        {
            {
                params.put("FirstName", customer.getFirstName());
                params.put("LastName", customer.getLastName());
                params.put("password", customer.getPassword());
                params.put("Email", customer.getEmail());
                params.put("PhoneNumber", customer.getPhoneNumber());
            }
        }
        ;

        super.postWithParams(getEndpoint(API, V1, AUTHENTICATION, SIGNUP), params);
    }

    public void customerLogin(CustomerLogin customer) {
        super.post(getEndpoint(API, V1, AUTHENTICATION, LOGIN), customer.toJson());
    }

    public void enterCustomer(AccountAuthLogin accountAuthLogin) throws JsonProcessingException {
        Map<String, String> params = new HashMap<>() {{
            put("userName", accountAuthLogin.getUserName());
            put("password", accountAuthLogin.getPassword());
        }};

        // Преобразуем Map в JSON строку
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(params);

        // Теперь передаем JSON строку
        super.post(getEndpoint(API, V1, AUTHENTICATION, LOGIN), jsonBody);
    }

    public void addFunds(String accountNumber, String amount) throws JsonProcessingException {
        AccountAddFunds accountAddFunds = AccountAddFunds.builder()
                .accountNumber(accountNumber)
                .amount(amount)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(accountAddFunds);
        super.post(getEndpoint(API, V1, TRANSACTIONS, ADD_FUNDS), jsonBody);
    }
}

