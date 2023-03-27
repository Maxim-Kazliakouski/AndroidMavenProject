package dto.factories;

import dto.TestCase;

public class TestCaseFactory {
    public static TestCase getTestCase(String APIorUI, String title) {
        if (APIorUI.equals("UI")) {
            return TestCase.builder()
                    .title(title)
                    .status("Draft")
                    .description("new test case description")
                    .suite("Test cases without suite")
                    .severity("Critical")
                    .priority("High")
                    .type("Functional")
                    .layer("API")
                    .isFlaky("No")
                    .behaviour("Positive")
                    .automationStatus("Automated")
                    .preConditions("start browser")
                    .postConditions("close browser")
                    .build();
        } else if (APIorUI.equals("API")) {
            return TestCase.builder()
                    .title(title)
                    .status(1)
                    .description("new test case description")
                    .suite("Test cases without suite")
                    .severity(1)
                    .priority(1)
                    .type(1)
                    .layer(1)
                    .isFlaky(1)
                    .behaviour("Positive")
                    .automationStatus("Automated")
                    .preConditions("start browser")
                    .postConditions("close browser")
                    .build();
        } else throw new Error("The Test type has been chosen incorrectly");
    }
}