package com.fancky.multipledatasource.model.viewmodel;

import com.fancky.multipledatasource.model.entity.demo.User;
import com.fancky.multipledatasource.model.entity.test.Job;

public class UserJobVM {
    private User user;
    private Job job;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
