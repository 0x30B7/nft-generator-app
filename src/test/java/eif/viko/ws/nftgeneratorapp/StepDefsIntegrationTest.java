package eif.viko.ws.nftgeneratorapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class StepDefsIntegrationTest extends SpringIntegrationTest {

    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectReader objectReader = objectMapper.reader();

    @When("^the client calls /api/v1/colors$")
    public void the_client_issues_GET_colors() throws Throwable {
        execute("http://localhost:8080/api/v1/colors", HttpMethod.GET);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }

    @And("^the response has (\\d+) colors$")
    public void the_response_has_n_colors(int colors) throws Throwable {
        List<Object> elements = objectReader.readValue(latestResponse.getBody(), List.class);

        assertThat(elements.size(), is(colors));
    }

}