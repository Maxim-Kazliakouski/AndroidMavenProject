package dto.factories;

import dto.UpdateProject;

public class UpdateProjectFactory {
    public static UpdateProject getUpdateProject(String whatChange, String changeValue){
        switch (whatChange) {
            case "Project Name":
                return UpdateProject.builder()
                        .projectName(changeValue)
                        .build();
            case "Project Code":
                return UpdateProject.builder()
                        .projectCode(changeValue)
                        .build();
            case "Description":
                return UpdateProject.builder()
                        .description(changeValue)
                        .build();
            case "Project access type":
                return UpdateProject.builder()
                        .projectAccessType(changeValue)
                        .build();

            // for making all changes
            default:
                return UpdateProject.builder()
                        .projectName("All changes")
                        .projectCode("AC")
                        .description("Project with changes")
                        .projectAccessType("Public")
                        .build();
        }
    }
}