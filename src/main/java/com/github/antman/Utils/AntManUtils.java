package com.github.antman.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhenfeiWang
 * @create 2016-11-17 上午10:15
 **/
public class AntManUtils {

    /**
     *
     * @param ipAddr
     * @param port
     * @return
     */
    public static String getDockerHost(String ipAddr, String port){
        return "tcp://" + ipAddr + ":" + port;
    }

    /**
     *
     * @param envVars
     * @return
     */
    public static List<String> constructEnvVars(Map<String, String> envVars){
        List<String> envVarList = new ArrayList<>();
        //可以考虑Java8的Stream
        if (envVars != null && !envVars.isEmpty()) {
            envVars.entrySet().stream().forEach(envVar -> envVarList.add(envVar.getKey() + "=" + envVar.getValue()));
        }
        return envVarList;
    }

    /**
     *
     * @param cpus
     * @return
     */
    public static String constructCpuBindings(String[] cpus){
        if (cpus == null || cpus.length == 0) {
            return "";
        } else {
            return "".join(",", cpus);
        }
    }
}
