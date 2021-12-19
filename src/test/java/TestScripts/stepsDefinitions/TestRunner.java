package TestScripts.stepsDefinitions;

import org.junit.runner.RunWith;

import TestScripts.Resources.BaseTest;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/TestScripts/FeatureFiles",
		glue="TestScripts.stepsDefinitions"
		)
public class TestRunner extends BaseTest{	


}
