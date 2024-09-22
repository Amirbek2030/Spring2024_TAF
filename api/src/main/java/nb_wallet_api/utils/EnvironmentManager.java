package nb_wallet_api.utils;

import static nb_wallet_api.enums.NBWalletEndPoints.*;

public class EnvironmentManager {

    public static int choosePort(String endpoint) {

        if (endpoint.contains(AUTHENTICATION)) {
            return Integer.parseInt(ApiConfigReader.getValue("identity.port"));
        } else if (endpoint.contains(ACCOUNT) || endpoint.contains("transactions") || endpoint.contains("customers")) {
            return Integer.parseInt(ApiConfigReader.getValue("customer.port"));
        } else if (endpoint.contains(MANAGER_API)) {
            return Integer.parseInt(ApiConfigReader.getValue("manager.port"));
        } throw new IllegalArgumentException("This is incorrect endpoint " + endpoint);
    }
}