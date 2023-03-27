package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestCase<T> {
    //Basic
    String title;
    Integer id;
    T status;
    String description;
    String suite;
    T severity;
    T priority;
    T type;
    T layer;
    @SerializedName("is_flaky")
    T isFlaky;
    String milestone;
    String behaviour;
    String automationStatus;
    //Conditions
    @SerializedName("preconditions")
    String preConditions;
    @SerializedName("postconditions")
    String postConditions;
    //Tags
    String tag;
    //Attachments
    String attachment;
    String addParam;
    String testCaseSteps;
    String addStep;
}