package nb_wallet_api.entity.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import nb_wallet_api.entity.BaseEntity;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Account extends BaseEntity {

    int id;
    String number;
    int balance;
    int currency;
    int status;
    AccountPlan accountPlan;
    AccountTransactionLimit accountTransactionLimit;
    Date createDate;

}
