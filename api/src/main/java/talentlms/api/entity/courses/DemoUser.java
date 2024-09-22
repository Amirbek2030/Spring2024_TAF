package talentlms.api.entity.courses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import talentlms.api.entity.BaseEntity;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)


public class DemoUser extends BaseEntity {

    public String id;
    public String name;
    public String role;
    @JsonProperty(value = "enrolled_on")
    public String enrolledOn;
    @JsonProperty(value = "enrolled_on_timestamp")
    public String enrolledOnTimestamp;
    @JsonProperty(value = "completed_on")
    public String completedOn;
    @JsonProperty(value = "completed_on_timestamp")
    public String completedOnTimestamp;
    @JsonProperty(value = "completion_percentage")
    public String completionPercentage;
    @JsonProperty(value = "expired_on")
    public String expiredOn;
    @JsonProperty(value = "xpired_on_timestam")
    public String expiredOnTimestamp;
    @JsonProperty(value = "total_time")
    public String totalTime;
    @JsonProperty(value = "total_time_seconds")
    public int totalTimeSeconds;

}
