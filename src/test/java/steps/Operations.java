package steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import windows.Calculator;

import java.net.MalformedURLException;

import static windows.Calculator.EQUALS;
import static windows.Calculator.MULTIPLY;

public class Operations{
    private Calculator calculator = new Calculator();

    @Given("variables 5 and 3")
    public void variables5And3() {
    }

    @When("I multiply $value1 by $value2")
    public void IMultiply5By3(@Named("value1") String value1, @Named("value2") String value2) {
        calculator.clickOn(value1);
        calculator.clickOn(MULTIPLY);
        calculator.clickOn(value2);
        calculator.clickOn(EQUALS);
    }

    @Then("result should equal $value1 multiply $value2")
    public void resultShouldEqual15(@Named("value1") int value1, @Named("value2") int value2) {
        String actualResult = calculator.getResult().trim();
        String expectedResult = String.valueOf(value1*value2).trim();
        if (!expectedResult.equals(actualResult))
            throw new RuntimeException("actual result is " + actualResult + "," +
                    " but should be " + expectedResult);
    }
}
