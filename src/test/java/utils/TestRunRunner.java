package utils;

import adapters.ProjectAPI;
import io.restassured.response.Response;

public class TestRunRunner {
    ProjectAPI projectAPI;
    public Integer launchTestRun(String testRunName) {
        String startTestRun = PropertyReader.getProperty("testRun");
        String codeProject = PropertyReader.getProperty("codeProject");
        if (Boolean.parseBoolean(startTestRun)) {
            System.out.printf("======================================== Creating test run for project --> %s ========================================%n", PropertyReader.getProperty("codeProject"));
            projectAPI = new ProjectAPI();
            Response response = projectAPI.createTestRun(codeProject, testRunName);
            return response.path("result.id");
        }
        else return null;
    }
}