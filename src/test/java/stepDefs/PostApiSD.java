package stepDefs;

import Core.ApiCall;
import Core.FileHandleHelper;
import Core.HeaderFormatHelper;
import Repository.remoteRepo.requestRepo.UserPostRequestModel;
import Repository.remoteRepo.responseRepo.UserPostApiResponseModel;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.Gson;
import io.cucumber.messages.internal.com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static Core.CoreContainHelper.base_url;
import static Core.FilePathHelper.postApiPath;

public class PostApiSD {
    private String requestModel;
    String url;
    UserPostRequestModel userPostRequestModel;
    Response postApiResponse;
    Gson gson=new Gson(); //imported cucumber one
    @Given("user has API {string}")
    public void userHasAPIPath(String path) {
        url=base_url+path;
    }

    @When("user hit {string} and {string}")
    public void userHitNameAndJob(String name, String job) {
        JSONObject requestBody=new FileHandleHelper().readJsonFile(postApiPath);   //imported cucumber one
        userPostRequestModel=new Gson().fromJson(requestBody.toJSONString(), UserPostRequestModel.class);
        userPostRequestModel.setName(name);
        userPostRequestModel.setJob(job);
        requestModel=gson.toJson(userPostRequestModel);
    }

    @And("call the API with body")
    public void callTheAPIWithBody() {
        postApiResponse= ApiCall.postCall(HeaderFormatHelper.commonHeaders(), requestModel,url);
        System.out.println(postApiResponse.body().asString());
    }

    @Then("it will return created data")
    public void itWillReturnCreatedData() {
    UserPostApiResponseModel postApiResponseModel=gson.fromJson(postApiResponse.getBody().asString(), UserPostApiResponseModel.class);
        System.out.println(postApiResponseModel.getCreatedAt());
        postApiResponseModel.getName();
    }
}
