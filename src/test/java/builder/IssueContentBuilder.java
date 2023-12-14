package builder;

import model.IssueFields;

public class IssueContentBuilder {
    public static String build(String projectKey, String taskTypeld, String summary) {

        IssueFields.IssueType issueType   = new IssueFields.IssueType(taskTypeld);
        IssueFields.Project project = new IssueFields.Project(projectKey);
        IssueFields.Fields fields  = new IssueFields.Fields(project, issueType, summary) ;
        IssueFields issueFields = new IssueFields(fields);

        return BodyJSonBuilder.getJSONContent(issueFields);
    }
}
