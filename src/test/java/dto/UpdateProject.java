package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class UpdateProject {
    String projectName;
    String projectCode;
    String description;
    String projectAccessType;
}