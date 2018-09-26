package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import windows.Calculator;

import static windows.Calculator.EQUALS;
import static windows.Calculator.MULTIPLY;

public class Multiplication {
    private Calculator calculator = new Calculator();

    @Given("^I want to test something$")
    public void iWantToTestSomething() {
    }

    @When("^I click (\\d+) and x and (\\d+)$")
    public void iClickAndXAnd(String value1, String value2) {
        calculator.clickOn(value1);
        calculator.clickOn(MULTIPLY);
        calculator.clickOn(value2);
        calculator.clickOn(EQUALS);
    }

    @Then("^The result is (\\d+)$")
    public void theResultIs(int result) {
        String actualResult = calculator.getResult().trim();
        String expectedResult = String.valueOf(result).trim();
        if (!expectedResult.equals(actualResult))
            throw new RuntimeException("actual result is " + actualResult + "," +
                    " but should be " + expectedResult);
    }
}
