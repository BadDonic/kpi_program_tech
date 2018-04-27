import compiler.Compiler;
import matrix.Matrix;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.assertEquals;

public class Steps {

    private Compiler compiler;
    private Object result;

    @Given("Compiler")
    public void Compiler() {
        compiler = new Compiler();
    }

    @Given("Compiler with a created variable $varname with value $value")
    public void givenCompilerWithCreatedVariableWithValue(String varname, String value) {
        compiler = new Compiler();
        compiler.compile(varname + " = " + value);
    }

    @Given("a created variable $varname with value $value")
    public void givenCreatedVariableWithValue(String varname, String value) {
        compiler.compile(varname + " = " + value);
    }

    @When("I compile string $string")
    public void iCompileString(String string) {
        result = compiler.compile(string);
    }

    @Then("I should get result $value")
    public void iShouldGetResult(String value) {
        if (result instanceof Matrix)
            assertEquals(Matrix.convert(value), result);
        else if (result instanceof Double)
            assertEquals(Double.parseDouble(value), result);
        else
            assertEquals(value, result);
    }

    @Then("should be created var $varname with value $value")
    public void varCreated(String varname, String value) {
        if (result instanceof Matrix)
            assertEquals(Matrix.convert(value), compiler.compile(varname));
        else if (result instanceof Double)
            assertEquals(Double.parseDouble(value), compiler.compile(varname));
        else
            assertEquals(value, result);
    }
}