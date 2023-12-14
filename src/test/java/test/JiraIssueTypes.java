package test;
import model.RequestCapability;
import utils.ProjectInfo;


public class JiraIssueTypes implements RequestCapability {
    public static void main(String[] args) {
        String baseUri = "https://danggiang99.atlassian.net";
        String projectKey = "LRA";

        ProjectInfo projectInfo = new ProjectInfo(baseUri, projectKey);
        System.out.println ("Task ID: " + projectInfo.getIssueTypeId("task"));
    }

}
