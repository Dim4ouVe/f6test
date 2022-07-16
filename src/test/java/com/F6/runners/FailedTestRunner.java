package com.F6.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * FailedTestRunner class will be used to execute only the tests that failed in the previous run.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com/F6/step_definitions",
        features = "@target/rerun.txt"
)

public class FailedTestRunner {
}
