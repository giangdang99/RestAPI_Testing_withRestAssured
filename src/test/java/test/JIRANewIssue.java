package test;

import builder.BodyJSonBuilder;
import builder.IssueContentBuilder;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.IssueFields;
import model.RequestCapability;
import utils.AuthenticationHandler;
import utils.ProjectInfo;

import static io.restassured.RestAssured.given;

public class JIRANewIssue implements RequestCapability {
    public static void main(String[] args) {

        // API Info
        String baseUri = "https://danggiang99.atlassian.net";
        String path= "/rest/api/3/issue";
        String projectKey = "LRA";

        String email = "dangthigiang99@gmail.com";
        String apiToken = "ATATT3xFfGF0huPPsJGZSNCVW9ckLUv0ByutxhNlafHMwVOvYgO-wSB_4UhG9vGdb-FobDqBrBXWQbs4cSzwY3CpSh7SopHlUGEsnBBdOFaU_maBYBpBFREUw7HAJm41W-2dBJc0ofvyTP3G_eSF8hrlV2V2s_aVHl_2_v29xoSSQ6siLO_b5JA=FD0B0CCB";
        String encodedCredStr = AuthenticationHandler.encodedCredStr(email,apiToken);

        // Request object
        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(defaultHeader);
        request.header(acceptJSONHeader);
        request. header(getAuthenticatedHeader. apply(encodedCredStr)) ;

        // Define body data
        ProjectInfo projectInfo = new ProjectInfo (baseUri, projectKey);
        String taskTypeId = projectInfo.getIssueTypeId("task");
        String summary = "Summary | IssueContentBuilder";
        String issueFieldsContent = IssueContentBuilder.build(projectKey,taskTypeId,summary);

        // Send request
        Response response = request.body(issueFieldsContent).post(path);
        response.prettyPrint();
    }
}
