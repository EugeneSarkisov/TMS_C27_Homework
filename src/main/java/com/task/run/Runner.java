package com.task.run;


import com.task.config.TaskAppConfig;
import com.task.model.issue.Issue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TaskAppConfig.class);
        List<Issue> issueList = (List<Issue>) context.getBean("task_list");
        for (Issue issue : issueList){
            issue.solveIssue();
        }
    }
}
