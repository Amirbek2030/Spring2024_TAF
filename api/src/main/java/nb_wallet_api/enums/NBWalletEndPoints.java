package nb_wallet_api.enums;

import nb_wallet_api.utils.ApiConfigReader;

public class NBWalletEndPoints {
    public static final String URL = ApiConfigReader.getValue("url");
    public static final String API = "/api";
    public static final String V1 = "v1";
    public static final String AUTHENTICATION = "authentication";
    public static final String SIGNUP = "signup";
    public static final String LOGIN = "login";
    public static final String ACCOUNT = "account";
    public static final String TRANSACTIONS = "transactions";
    public static final String ADD_FUNDS = "add-funds";
    public static final String MANAGER_API = "manager-api";

}
