package com.F6.step_definitions;

import com.F6.utils.Driver;
import com.F6.utils.FacsUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class login_step_definitions {


    @When("user enter valid portal, username and password")
    public void user_enter_valid_portal_username_and_password() {
        FacsUtils.login("HDOT","support@facsware.com","gogiants");
    }
    @Then("user should see be directed to main page and see the projects")
    public void user_should_see_be_directed_to_main_page_and_see_the_projects() {
        System.out.println(Driver.getDriver().getCurrentUrl());
    }
}
