package talentlms.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum ResponseValidationMessage {
    SUCCESS_OPERATION_USER("Operation completed successfully"),
    SUCCESS_OPERATION_COURSE("Operation completed successfully ");

    private String message;
}
