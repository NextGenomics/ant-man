package com.github.antman;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.WaitContainerResultCallback;
import mousio.etcd4j.EtcdClient;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{

    private static DefaultDockerClientConfig config() {
        return config(null);
    }

    protected static DefaultDockerClientConfig config(String password) {
        DefaultDockerClientConfig.Builder builder = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withRegistryUrl("https://index.docker.io/v1/");
        if (password != null) {
            builder = builder.withRegistryPassword(password);
        }

        return builder.build();
    }

    public static void main(String[] args)
    {
//        DockerClient dockerClient;
//        DockerCmdExecFactory dockerCmdExecFactory = DockerClientBuilder.getDefaultDockerCmdExecFactory();
//        dockerClient = DockerClientBuilder.getInstance(config())
//                .withDockerCmdExecFactory(dockerCmdExecFactory)
//                .build();
//
//        String[] commands = new String[] {
//                "true",
//                "echo \"The Young Descendant of Tepes & Septette for the Dead Princess\"",
//                "echo -n 'The Young Descendant of Tepes & Septette for the Dead Princess'",
//                "/bin/sh -c echo Hello World", "/bin/sh -c echo 'Hello World'", "echo 'Night of Nights'",
//                "true && echo 'Night of Nights'"
//        };
//
//        for (String command : commands) {
//            CreateContainerResponse container = dockerClient.createContainerCmd("smalltoolspython2:latest")
//                    .withCmd(command)
//                    .exec();
//            dockerClient.startContainerCmd(container.getId());
//
//            int exitcode = dockerClient.waitContainerCmd(container.getId())
//                    .exec(new WaitContainerResultCallback())
//                    .awaitStatusCode();
//            System.out.println(container.getId());
//            System.out.println(exitcode);
//        }
        EtcdClient etcd = new EtcdClient(URI.create("http://192.168.80.150:2380"));
        // Logs etcd version
        //System.out.println(etcd.put("hello", "world"));
        System.out.println(etcd.get("hello").getRequestParams());

    }
}
