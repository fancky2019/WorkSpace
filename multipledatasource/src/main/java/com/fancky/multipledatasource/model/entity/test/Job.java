package com.fancky.multipledatasource.model.entity.test;

public class Job {
    private Integer id;

    private String jobname;

    public Job(Integer id, String jobname) {
        this.id = id;
        this.jobname = jobname;
    }

    public Job() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname == null ? null : jobname.trim();
    }
}