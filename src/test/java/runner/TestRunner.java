package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {"utility","stepDefinition"},
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
//        monochrome = true
)
public class TestRunner {


}


