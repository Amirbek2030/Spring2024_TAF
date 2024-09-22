package nb_wallet_api.entity.account;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import nb_wallet_api.entity.BaseEntity;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class AccountResponse extends BaseEntity {

    List<Account> items;
    int pageNumber;
    int totalPages;
    int totalCount;
    boolean hasPreviousPage;
    boolean hasNextPage;

}
