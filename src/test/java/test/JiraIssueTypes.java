package test;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.RequestCapability;
import org.apache.commons.codec.binary.Base64;

import static io.restassured.RestAssured.given;
public class JiraIssueTypes implements RequestCapability {
    public static void main(String[] args) {
        String baseUri = "https://danggiang99.atlassian.net";
        String path = "/rest/api/3/project/LRA";

        String email = System.getenv("dangthigiang99@gmail.com");
        String apiToken = System.getenv("ATATT3xFfGF05WZYXt1G4E7ZfYyCxAhNRY13qMf4P3frGhqoWwGQCXQCro5kc3TGXXrxqUyU0rzyjLSM77qxrBW-zHlYxadMFw1dOQwO25jqWI2zsbkYGD6sN5YjbBVp-4tViYFtpxUWN9vHkDl5eEoLzzVcnEx4ytsTbLytQnNeVV3WM4xbZ5Y=AA2773FC");
        String cred = email.concat(":").concat(apiToken);
        byte[] encodedCred = Base64.encodeBase64(cred.getBytes());
        String encodedCredStr = new String(encodedCred);

        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(defaultHeader);
        request.header("Authorization", "Basic " + encodedCredStr);

        Response response  = request.get(path);
        response.prettyPrint();

    }
}
