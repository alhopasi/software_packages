package com.software_packages.domain;

import java.util.ArrayList;
import java.util.List;

public class SoftwarePackage implements Package {

    private int id;
    private String name;
    private List<String> description;
    private List<String> dependencies;
    private List<String> reverseDependencies;

    public SoftwarePackage(int id) {
        name = "unnamed";
        description = new ArrayList<>();
        dependencies = new ArrayList<>();
        reverseDependencies = new ArrayList<>();
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    public List<String> getReverseDependencies() {
        return reverseDependencies;
    }

    public void addReverseDependency(String reverseDependency) {
        this.reverseDependencies.add(reverseDependency);
    }

}
