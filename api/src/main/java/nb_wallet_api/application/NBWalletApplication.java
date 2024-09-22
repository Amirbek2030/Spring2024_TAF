package nb_wallet_api.application;

import lombok.Data;
import nb_wallet_api.controllers.CustomerController;

@Data

public class NBWalletApplication {
        private CustomerController customerController;

        public NBWalletApplication(String url) {
                this.customerController = new CustomerController(url);
        }
}
