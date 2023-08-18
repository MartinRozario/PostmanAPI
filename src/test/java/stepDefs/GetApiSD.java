package stepDefs;

import Core.ApiCall;
import Core.HeaderFormatHelper;
import Repository.remoteRepo.responseRepo.UserGetApiResponseModel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.Gson;
import io.restassured.response.Response;

import static Core.CoreContainHelper.base_url;

public class GetApiSD {
    String url;
    private Response responsegetApi;
    private final Gson gson=new Gson();
    @Given("user has the base api")
    public void userHasTheBaseApi() {
        url=base_url+"users";
    }

    @When("user call the {string} and {string}")
    public void userCallTheParameterAndParamsValue(String parameter, String paramValue) {
        url = url+parameter +"=" +paramValue;
        System.out.println(url);
        responsegetApi= ApiCall.getCall(HeaderFormatHelper.commonHeaders(),url);
        System.out.println(responsegetApi.body().asString());
    }

    @Then("it will return valid data")
    public void itWillReturnValidData() {
        System.out.println(url);
        UserGetApiResponseModel getApiResponseModel=gson.fromJson(responsegetApi.getBody().asString(), UserGetApiResponseModel.class);
        System.out.println(getApiResponseModel.getPage());
        System.out.println(getApiResponseModel.getData().get(0).getFirst_name());
    }
}
