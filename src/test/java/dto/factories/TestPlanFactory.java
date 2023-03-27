package dto.factories;

import dto.TestPlan;

public class TestPlanFactory {
    public static TestPlan getTestPlan(String title, String description) {
        return TestPlan.builder()
                .title(title)
                .description(description)
                .build();
    }
}