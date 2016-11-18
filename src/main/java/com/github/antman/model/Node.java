package com.github.antman.model;

import java.util.Map;

/**
 * @author zhenfeiWang
 * @create 2016-11-17 上午9:27
 **/
public class Node {

    private String hostname;

    private String ipAddr;

    private String port = "2375";

    private Map<String, Object> tags;

    private long totalMemory;

    private int totalCpuNum;

    private long allocateMemory;

    private String[] allocateCpus;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public int getTotalCpuNum() {
        return totalCpuNum;
    }

    public void setTotalCpuNum(int totalCpuNum) {
        this.totalCpuNum = totalCpuNum;
    }

    public long getAllocateMemory() {
        return allocateMemory;
    }

    public void setAllocateMemory(long allocateMemory) {
        this.allocateMemory = allocateMemory;
    }

    public String[] getAllocateCpus() {
        return allocateCpus;
    }

    public void setAllocateCpus(String[] allocateCpus) {
        this.allocateCpus = allocateCpus;
    }
}
