package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/Feature/Thinkific.feature"},
        plugin = {"pretty","json:target/cucumber.json","html:target/cucumber-html-report"},
        tags = "@Smoke",
        glue = {"stepDefs"}
)
public class TestRunner {
}
