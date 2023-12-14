package utils;

import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.RequestCapability;
import org.apache.commons.codec.binary.Base64;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProjectInfo implements RequestCapability {
    private String baseUri;
    private String projectKey;
    private List<Map<String, String>> issueTypes;
    private Map<String, List<Map<String, String>>> projectInfo;

    public ProjectInfo(String baseUri, String projectKey){
        this.baseUri = baseUri;
        this.projectKey = projectKey;
        getProjectInfo();
    }

    public String getIssueTypeId(String issueTypeStr){
        getIssueTypes();

        String issueTypeId = null;

        for(Map<String, String> issueType:issueTypes){
            if(issueType.get("name").equalsIgnoreCase(issueTypeStr)){
                issueTypeId = issueType.get("id");
                break;
            }
        }

        if(issueTypeId == null){
            throw new IllegalArgumentException("[ERR] encodedCredStr can't be null!" + issueTypeStr);
        }
        return issueTypeId;
    }

    private void getIssueTypes(){
        issueTypes =  projectInfo.get("issueTypes");
    }

    private void getProjectInfo(){

        String path = "/rest/api/3/project/".concat(projectKey);

        String email = "dangthigiang99@gmail.com";
        String apiToken = "ATATT3xFfGF0huPPsJGZSNCVW9ckLUv0ByutxhNlafHMwVOvYgO-wSB_4UhG9vGdb-FobDqBrBXWQbs4cSzwY3CpSh7SopHlUGEsnBBdOFaU_maBYBpBFREUw7HAJm41W-2dBJc0ofvyTP3G_eSF8hrlV2V2s_aVHl_2_v29xoSSQ6siLO_b5JA=FD0B0CCB";
        String encodedCredStr = AuthenticationHandler.encodedCredStr(email,apiToken);

        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(defaultHeader);
        request.header(getAuthenticatedHeader.apply(encodedCredStr));

        Response response  = request.get(path);
        projectInfo = JsonPath.from(response.asString()).get();
//        Map<String, List<Map<String, String>>> projectInfo = JsonPath.from(response.asString()).get();
    }
}
