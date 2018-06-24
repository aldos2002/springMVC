import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"steps"},
        features = "./src/test/java",
        format = {"json:target/Destination/cucumber.json"}
)

public class TestRunnerTest {

}