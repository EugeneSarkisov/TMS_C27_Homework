package com.task.config;


import com.task.model.issue.Issue;
import com.task.model.sub_issue.SubIssue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class TaskAppConfig {

    @Bean("task_list")
    public List<Issue> getIssueList(){
        List<Issue> issueList = new ArrayList<>();
        issueList.add(new Issue("1. Test Task"));
        issueList.add(new Issue("2. Test task", new SubIssue("2.1 Test Sub Issue")));
        issueList.add(new Issue("3. Test Task"));
        issueList.add(new Issue("4. Test task", new SubIssue("4.1 Test Sub Issue")));
        return issueList;
    }
}
