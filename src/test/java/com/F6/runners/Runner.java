package com.F6.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Runners class will be used to run the tests by providing tags
 * for the test that need to be executed in the CucumberOptions
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "json:target/cucumber.json",
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber"
        },
        features = "src/test/resources/features",
        glue = "com/F6/step_definitions",
        dryRun = false,
        tags = "@wip"
)

public class Runner {
}
