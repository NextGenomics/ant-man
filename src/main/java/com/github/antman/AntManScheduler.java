package com.github.antman;

import com.github.antman.Utils.AntManUtils;
import com.github.antman.model.BatchJob;
import com.github.antman.model.Node;
import com.github.antman.model.VolumnBinding;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.netty.NettyDockerCmdExecFactory;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;


/**
 * @author zhenfeiWang
 * @create 2016-11-17 上午9:25
 **/
public class AntManScheduler {

    public class ContainerClient{

        private String jobId;

        private String workerHost;

        private String containerId;

        /**
         *
         * @param jobId
         */
        public ContainerClient(String jobId){
            this.jobId = jobId;
            String[] jobIdInfo = jobId.split("@");
            this.containerId = jobIdInfo[0];
            this.workerHost = jobIdInfo[1];
        }

        /**
         *
         * @return
         */
        public final DockerClient getDockerClient(){
            return DockerClientBuilder.getInstance(this.workerHost)
                    .withDockerCmdExecFactory(DockerClientBuilder
                            .getDefaultDockerCmdExecFactory()).build();
        }

        /**
         *
         * @return
         */
        public final String getContainerId(){
            return this.containerId;
        }
    }

    /**
     * 根据资源需求等约束条件筛选最合适的节点
     * @return
     */
    private Node getBestNode(){
        Node node = new Node();
        node.setIpAddr("192.168.80.150");
        node.setPort("2375");
        return node;
    }

    /**
     *
     * @param batchJob
     * @return
     */
    public String runJob(BatchJob batchJob){
        Node workerNode = this.getBestNode();
        String dockerHost = AntManUtils.getDockerHost(workerNode.getIpAddr(), workerNode.getPort());
        DockerCmdExecFactory dockerCmdExecFactory = new NettyDockerCmdExecFactory();
        DockerClient dockerClient = DockerClientBuilder.getInstance(dockerHost).withDockerCmdExecFactory(dockerCmdExecFactory).build();

        CreateContainerResponse container = dockerClient.createContainerCmd(batchJob.getImageName())
                    .withName(batchJob.getJobName())
                    .withRestartPolicy(batchJob.getRestartPolicy().policy)
                    .withMemory(batchJob.getMemoryBytes())
                    .withCpusetCpus(AntManUtils.constructCpuBindings(workerNode.getAllocateCpus()))
                    .withEnv(AntManUtils.constructEnvVars(batchJob.getEnvVars()))
                    .withBinds(batchJob.getVolumnBinding().constructDockerBindings())
                    .withCmd("sh", "-c", "".join(" ", batchJob.getCmdline()))
                    .exec();

        try {
            dockerClient.startContainerCmd(container.getId()).exec();
        } catch (Exception e){
            e.printStackTrace();
        }

        //close the client
        try{
            dockerClient.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return container.getId() + "@" + dockerHost;
    }

    /**
     * 获取作业的状态，基于container的状态为判断依据，
     * 1. 如果container的status==exited && exitCode == 0则运行成功，返回0
     * 2. 如果container的status==exited && exitCode != 0则运行失败，返回exitCode
     * 3. 如果container的status==exited 则返回 -1
     * @param jobId
     * @return
     */
    public int getJobStatus(String jobId) {

        ContainerClient containerClient = new ContainerClient(jobId);
        DockerClient dockerClient = containerClient.getDockerClient();
        String containerId = containerClient.getContainerId();

        InspectContainerResponse inspectContainerResponse = dockerClient.inspectContainerCmd(containerId).exec();

        int exitCode;

        if (inspectContainerResponse.getState().getStatus().equals("exited")){
            exitCode = inspectContainerResponse.getState().getExitCode();
        } else {
            exitCode = -1;
        }


        //close the client
        try{
            dockerClient.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return exitCode;
    }

    /**
     *
     * @param jobId
     * @param timeout
     *
     */
    public void stopJob(String jobId, int timeout){
        ContainerClient containerClient = new ContainerClient(jobId);
        DockerClient dockerClient = containerClient.getDockerClient();
        String containerId = containerClient.getContainerId();

        dockerClient.stopContainerCmd(containerId).withTimeout(timeout).exec();

        //close the client
        try{
            dockerClient.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(Instant.now().getEpochSecond());
        AntManScheduler antManScheduler = new AntManScheduler();

        BatchJob batchJob = new BatchJob("smalltoolspython2:latest", "echo", "${TEST}");

        batchJob.setVolumnBinding(new VolumnBinding().add("/root/", "/opt/test/"));

        HashMap<String, String> envVars = new HashMap<>();
        envVars.put("TEST", "hello");
        batchJob.setEnvVars(envVars);
        String jobId = antManScheduler.runJob(batchJob);
        //antManScheduler.stopJob(jobId, 1);
        System.out.println(antManScheduler.getJobStatus(jobId));
        System.out.println(Instant.now().getEpochSecond());
    }
}
