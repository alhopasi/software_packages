package com.software_packages.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SystemPackageTest {

    private Package sysPackage;

    @Before
    public void init() {
        sysPackage = new SoftwarePackage(0);
    }

    @Test
    public void testSetters() {
        List<String> description = new ArrayList() {{add("Description of"); add("awesome strings");}};
        List<String> deps = new ArrayList<>() {{add("lib_1"); add("lib_2"); }};
        List<String> reverseDeps = new ArrayList<>() {{add("lib_3"); add("lib_4"); }};
        sysPackage.setName("pack");
        sysPackage.setDescription(description);
        sysPackage.setDependencies(deps);
        sysPackage.setReverseDependencies(reverseDeps);
        assertEquals("pack", sysPackage.getName());
        assertTrue(sysPackage.getDescription() == description);
        assertTrue(sysPackage.getDependencies() == deps);
        assertTrue(sysPackage.getReverseDependencies() == reverseDeps);
    }


}
