package com.software_packages.domain;

import com.utils.MockReader;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PackageManagerTest {

    private PackageManager man;

    @Before
    public void init() {
        man = new PackageManager(new MockReader());
        man.clearPackages();
        man.getPackages();
    }

    @Test
    public void sortingWorks() {
        List<Package> packs = man.getPackages();
        assertEquals("test-package", packs.get(0).getName());
        assertEquals("found-package", packs.get(1).getName());
        man.sortPackagesByName();
        packs = man.getPackages();
        assertEquals("found-package", packs.get(0).getName());
        assertEquals("test-package", packs.get(1).getName());
    }

    @Test
    public void CorrectNamesWhenCreating() {
        List<Package> packs = man.getPackages();

        assertEquals("test-package", packs.get(0).getName());
        assertEquals("found-package", packs.get(1).getName());
    }

    @Test
    public void CorrectDescriptionWhenCreating() {
        List<Package> packs = man.getPackages();

        List<String> desc = packs.get(0).getDescription();
        assertEquals("Package for tests", desc.get(0));
        assertEquals(" testing displaying of stuff \\ and / and < and > ok?", desc.get(1));
        assertEquals(" one more line", desc.get(2));

        desc = packs.get(1).getDescription();
        assertEquals("Some more tests", desc.get(0));
        assertEquals(" in this package", desc.get(1));
    }

    @Test
    public void CorrectDependenciesWhenCreating() {
        List<Package> packs = man.getPackages();

        List<String> deps = packs.get(0).getDependencies();
        assertEquals("not-found-package", deps.get(0));
        assertEquals("not-found-package2", deps.get(1));
        assertEquals("not-found-package3", deps.get(2));
        assertEquals("found-package", deps.get(3));

        deps = packs.get(1).getDependencies();
        assertEquals("test-package", deps.get(0));
    }

    @Test
    public void CorrectReverseDependenciesWhenCreating() {
        List<Package> packs = man.getPackages();

        List<String> deps = packs.get(0).getReverseDependencies();
        assertEquals("found-package", deps.get(0));
        assertEquals("not-found-package", deps.get(1));

        deps = packs.get(1).getReverseDependencies();
        assertTrue(deps.isEmpty());
    }

    @Test
    public void CorrectIdWhenCreating() {
        List<Package> packs = man.getPackages();
        assertEquals(0, packs.get(0).getId());
        assertEquals(1, packs.get(1).getId());
    }

    @Test
    public void GetPackageWithIdReturnsCorrectPackage() {
        Package pack = man.getPackage(0);
        assertEquals("test-package", pack.getName());
        assertEquals("Package for tests", pack.getDescription().get(0));
        assertEquals(0, pack.getId());
    }
}
