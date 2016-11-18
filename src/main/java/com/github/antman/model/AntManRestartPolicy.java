package com.github.antman.model;

import com.github.dockerjava.api.model.RestartPolicy;

/**
 * @author zhenfeiWang
 * @create 2016-11-18 上午9:53
 **/

public enum  AntManRestartPolicy {

    /**
     * 默认重启三次
     */
    onFailure(3),

    /**
     *
     */
    noRestart(RestartPolicy.noRestart()),

    /**
     *
     */
    alwaysRestart(RestartPolicy.alwaysRestart());

    /**
     *
     */
    public RestartPolicy policy;

    /**
     *
     */
    private int maxTimeLimit;

    /**
     *
     * @param policy
     * @param maxTimeLimit
     */
    AntManRestartPolicy(int maxTimeLimit){
        this.policy = RestartPolicy.onFailureRestart(maxTimeLimit);
        this.maxTimeLimit = maxTimeLimit;
    }

    /**
     *
     * @param policy
     */
    AntManRestartPolicy(RestartPolicy policy){
        this.policy = policy;
    }


}
