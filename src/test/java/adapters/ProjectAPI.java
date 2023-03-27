package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Project;
import dto.TestCase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import tests.api.Specifications;
import utils.PropertyReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProjectAPI {
    Gson gson;
    public static final String URL_API = PropertyReader.getProperty("qase.url.api");

    public ProjectAPI() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        Specifications.installSpecification(
                Specifications.requestSpec(URL_API),
                Specifications.responseSpecStatusCode(200)
        );
    }

    public Response getAllProjects() {
        return RestAssured
                .given()
                .when()
                .get("/v1/project")
                .then()
                .statusCode(200)
                .extract().response();
    }

    public Response createProject(Project project) {
        String data = gson.toJson(project);
        return
                given()
                        .body(data)
                        .when()
                        .post("/v1/project")
                        .then().log().all()
                        .statusCode(200)
                        .extract().response();
    }

    public ValidatableResponse deleteProjectByCode(String code) {
        return
                given()
                        .when()
                        .delete("/v1/project/" + code)
                        .then().log().all();
    }

    public Response getCertainTestCase(String codeProject, int id) {
        return RestAssured
                .given()
                .when()
                .get("/v1/case/{code}/{id}", codeProject, id)
                .then().log().all()
                .extract().response();
    }

    public ValidatableResponse createNewTestCase(TestCase testCase, String codeProject) {
        String data = gson.toJson(testCase);
        System.out.println(data);
        return
                given()
                        .body(data)
                        .when()
                        .post("/v1/case/" + codeProject)
                        .then().log().all();
    }

    public ValidatableResponse deleteTestCase(String codeProject, int testCaseID) {
        return
                given()
                        .when()
                        .delete("/v1/case/{code}/{id}", codeProject, testCaseID)
                        .then().log().all();
    }

    public Response getAllTestCases(String codeProject) {
        return RestAssured.given()
                .when()
                .get("/v1/case/" + codeProject)
                .then().log().all()
                .extract().response();
    }

    public void setStatus(String status,
                          String codeProject,
                          Integer idTestRun,
                          String idTestCase,
                          Long time) {
        Map<String, Object> data = new HashMap<>();
        data.put("case_id", idTestCase);
        data.put("status", status);
        data.put("time", time);
        given()
                .body(data)
                .when()
                .post("/v1/result/{code}/{id}", codeProject, idTestRun)
                .then();
    }

    public Response createTestRun(String codeProject, String testRunName) {
        Map<String, Object> data = new HashMap<>();
        data.put("title", testRunName);
        data.put("include_all_cases", true);
        return given()
                .body(data)
                .when()
                .post("/v1/run/" + codeProject)
                .then()
                .extract().response();
    }

    public ValidatableResponse deleteTestRun(String codeProject, int idTestRun) {
        return
                given()
                        .when()
                        .delete("/v1/run/{code}/{id}", codeProject, idTestRun)
                        .then();
    }

    public Response getAllRuns(String projectCode) {
        return RestAssured
                .when()
                .get("/v1/run/" + projectCode)
                .then()
                .extract().response();
    }

    public Response createTestPlan(String testPlanName, List<Integer> casesID, String projectCode) {
        Map<String, Object> data = new HashMap<>();
        data.put("title", testPlanName);
        data.put("cases", casesID);
        return RestAssured
                .given()
                .body(data)
                .when()
                .post("/v1/plan/" + projectCode)
                .then().log().all()
                .extract().response();
    }
}