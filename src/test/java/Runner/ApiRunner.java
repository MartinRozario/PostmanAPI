package Runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(features = {"src/test/resources/Feature/getAPI.feature","src/test/resources/Feature/postAPI.feature"},
        glue = {"stepDefs"},   monochrome = true,
        dryRun = false,
      //  tags = "@getApi", "@postApi",
        plugin = {"pretty","html:build/reports/api.html"})
@Test
class ApiRunner extends AbstractTestNGCucumberTests{
}