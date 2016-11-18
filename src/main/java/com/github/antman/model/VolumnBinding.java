package com.github.antman.model;

import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.Volume;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenfeiWang
 * @create 2016-11-17 上午9:33
 **/
public class VolumnBinding {

    public class Binding {

        private String sourcePath;

        private String targetPath;

        public Binding(String sourcePath, String targetPath){
            this.sourcePath = sourcePath;
            this.targetPath = targetPath;
        }

        public String getSourcePath() {
            return sourcePath;
        }

        public void setSourcePath(String sourcePath) {
            this.sourcePath = sourcePath;
        }

        public String getTargetPath() {
            return targetPath;
        }

        public void setTargetPath(String targetPath) {
            this.targetPath = targetPath;
        }
    }

    private List<Binding> bindings;

    public VolumnBinding(){
        this.bindings = new ArrayList<>();
    }

    /**
     *
     * @param binding
     * @return
     */
    public VolumnBinding add(Binding binding){
        this.bindings.add(binding);
        return this;
    }

    /**
     *
     * @param sourcePath
     * @param targetPath
     * @return
     */
    public VolumnBinding add(String sourcePath, String targetPath){
        this.bindings.add(new Binding(sourcePath, targetPath));
        return this;
    }

    /**
     *
     * @return
     */
    public List<Bind> constructDockerBindings(){
        List<Bind> dockerBinds = new ArrayList<>();
        bindings.forEach(binding -> dockerBinds.add(new Bind(binding.getSourcePath(),
                new Volume(binding.getTargetPath()))));
        return dockerBinds;
    }


}
