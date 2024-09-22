    package nb_wallet_api.utils;


    import com.github.javafaker.Faker;
    import nb_wallet_api.entity.CustomerSignUp;
    import nb_wallet_api.entity.account.AccountCreation;


    public class EntityManager {
        private static final Faker faker = new Faker();

        public static CustomerSignUp generateCustomer() {

            return CustomerSignUp. builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .password(faker.internet().password(10, 15, true, true, true))
                    .email(faker.internet().emailAddress())
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .build();
        }

        public static AccountCreation generateCreationCardForm(){
            return AccountCreation.builder()
                    .accountPlanId(faker.number().numberBetween(1, 4))
                    .currency(faker.number().numberBetween(0,3))
                    .build();
        }
    }