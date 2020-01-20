package com.software_packages.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SoftwarePackageTest {

    private Package pack;

    @Before
    public void init() {
        pack = new SoftwarePackage(0);
    }

    @Test
    public void testSettersAndGetters() {
        List<String> description = new ArrayList() {{add("Description of"); add("awesome strings");}};
        List<String> deps = new ArrayList<>() {{add("lib_1"); add("lib_2"); }};
        List<String> reverseDeps = new ArrayList<>() {{add("lib_3"); add("lib_4"); }};
        pack.setName("pack");
        pack.setDescription(description);
        pack.setDependencies(deps);
        for (String dep : reverseDeps) {
            pack.addReverseDependency(dep);
        }
        assertEquals("pack", pack.getName());
        assertTrue(pack.getDescription() == description);
        assertTrue(pack.getDependencies() == deps);
        assertTrue(pack.getReverseDependencies().equals(reverseDeps));
    }


}
