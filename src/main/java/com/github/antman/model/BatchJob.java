package com.github.antman.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhenfeiWang
 * @create 2016-11-17 上午9:21
 **/
public class BatchJob {

    /**
     * generate uuid as the jobName by default
     */
    private String jobName = UUID.randomUUID().toString();

    private List<String> cmdline;

    private String imageId;

    private String imageName;

    private Integer cpuNum;

    private Long memoryBytes = 128 * 1024 * 1024L;

    private Integer disk;

    private String network;

    private VolumnBinding volumnBinding;

    private Map<String, String> envVars;

    private AntManRestartPolicy restartPolicy = AntManRestartPolicy.noRestart;

    /**
     * construct a batch job with imageName and commandline directly
     * @param imageName
     * @param cmdline
     */
    public BatchJob(String imageName, List<String> cmdline) {
        this.imageName = imageName;
        this.cmdline = cmdline;
    }

    /**
     * construct a batch job with imageName and commandline directly
     * @param imageName
     * @param cmdline
     */
    public BatchJob(String imageName, String ... cmdline) {
        this.imageName = imageName;
        this.cmdline = Arrays.asList(cmdline);
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public List<String> getCmdline() {
        return cmdline;
    }

    public void setCmdline(List<String> cmdline) {
        this.cmdline = cmdline;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(Integer cpuNum) {
        this.cpuNum = cpuNum;
    }

    public Long getMemoryBytes() {
        return memoryBytes;
    }

    public void setMemoryBytes(Long memoryBytes) {
        this.memoryBytes = memoryBytes;
    }

    public Integer getDisk() {
        return disk;
    }

    public void setDisk(Integer disk) {
        this.disk = disk;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public VolumnBinding getVolumnBinding() {
        return volumnBinding;
    }

    public void setVolumnBinding(VolumnBinding volumnBinding) {
        this.volumnBinding = volumnBinding;
    }

    public Map<String, String> getEnvVars() {
        return envVars;
    }

    public void setEnvVars(Map<String, String> envVars) {
        this.envVars = envVars;
    }

    public AntManRestartPolicy getRestartPolicy() {
        return restartPolicy;
    }

    public void setRestartPolicy(AntManRestartPolicy restartPolicy) {
        this.restartPolicy = restartPolicy;
    }
}
