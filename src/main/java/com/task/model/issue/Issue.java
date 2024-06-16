package com.task.model.issue;


import com.task.model.sub_issue.SubIssue;
import org.springframework.beans.factory.annotation.Autowired;

public class Issue {
    private String issueDescription;
    private SubIssue subIssue;

    @Autowired
    public Issue() {
    }

    @Autowired
    public Issue(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    @Autowired
    public Issue(String issueDescription, SubIssue subIssue) {
        this.issueDescription = issueDescription;
        this.subIssue = subIssue;
    }

    public void solveIssue(){
        taskSolution();
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public SubIssue getSubIssue() {
        return subIssue;
    }

    public void setSubIssue(SubIssue subIssue) {
        this.subIssue = subIssue;
    }
    private void taskSolution(){
        System.out.println(getIssueDescription() + " - solved!");
        if(subIssue != null) {
            System.out.println(getSubIssue().getSubIssueDescription() + " - solved!");
        }
    }
}
