package com.software_packages.domain;

import java.util.List;

public interface Package {

    int getId();
    String getName();
    List<String> getDescription();
    List<String> getDependencies();
    List<String> getReverseDependencies();

    void setName(String name);
    void setDescription(List<String> description);
    void setDependencies(List<String> dependencies);
    void addReverseDependency(String dependency);

}
