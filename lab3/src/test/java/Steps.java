import compiler.Compiler;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.assertEquals;

public class Steps {

    private Compiler compiler;
    private Object result;
    private Exception exception;

    @Given("a new compiler")
    public void newCompiler() {
        compiler = new Compiler();
    }

    @Given("an compiler with a created variable $varname with value $value")
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
        try{
            result = compiler.compile(string);
        } catch (Exception e){
            exception = e;
        }
    }

    @Then("I should get result $value")
    public void iShouldGetResult(String value) {
        assertEquals(value, result.toString());
    }

    @Then("I should get an error message $message")
    public void iShouldGetErrorMessage(String message) {
        assertEquals(message, result.toString());
    }

    @Then("should be created var $varname with value $value")
    public void varCreated(String varname, String value) {
        assertEquals(value, compiler.compile(varname).toString());
    }
}