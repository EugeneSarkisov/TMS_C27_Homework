package com.task.model.sub_issue;

import org.springframework.beans.factory.annotation.Autowired;

public class SubIssue {
    private String subIssueDescription;

    @Autowired
    public SubIssue() {
    }

    @Autowired
    public SubIssue(String subIssueDescription) {
        this.subIssueDescription = subIssueDescription;
    }

    public String getSubIssueDescription() {
        return subIssueDescription;
    }

    public void setSubIssueDescription(String subIssueDescription) {
        this.subIssueDescription = subIssueDescription;
    }
}
