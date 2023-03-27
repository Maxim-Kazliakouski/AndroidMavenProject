package dto.factories;

import dto.Project;

public class ProjectFactory {
    public static Project getByAccessType(String projectName, String code, String desc, String accessType) {
        if (accessType.equals("private")) {
            return Project.builder()
                    .projectName(projectName)
                    .projectCode(code)
                    .description(desc)
                    .projectAccessType("Private")
                    .build();
        } else if (accessType.equals("public")) {
            return Project.builder()
                    .projectName(projectName)
                    .projectCode("")
                    .description(desc)
                    .projectAccessType("Public")
                    .build();
        } else throw new Error("There is no suitable project type");
    }
}