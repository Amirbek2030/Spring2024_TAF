package nbwallet_test_api;

import nb_wallet_api.application.NBWalletApplication;
import org.testng.annotations.BeforeSuite;
import static nb_wallet_api.enums.NBWalletEndPoints.URL;

public class NBApiTest {
    protected NBWalletApplication application;

    @BeforeSuite
    public void setup() {
        application = new NBWalletApplication(URL);
    }
}