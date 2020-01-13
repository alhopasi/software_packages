package com.software_packages.domain;

import com.utils.Reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageManager {

    private Reader reader;
    private List<Package> packages;
    private Map<String, Package> packagesNameMap;
    private Map<Integer, Package> packagesIdMap;

    public PackageManager(Reader reader) {
        this.reader = reader;
        packages = new ArrayList<>();
        packagesNameMap = new HashMap<>();
        packagesIdMap = new HashMap<>();
    }

    public void clearPackages() {
        packages = new ArrayList<>();
        packagesNameMap = new HashMap<>();
        packagesIdMap = new HashMap<>();
    }

    public List<Package> getPackages() {
        if (packages != null && !packages.isEmpty()) {
            return packages;
        }
        reader.readFile();
        createNewPackages();
        return packages;
    }

    private void createNewPackages() {
        int id = 1;
        Package sysPackage = new SoftwarePackage(0);
        String line = reader.nextLine();

        while (line != null) {
            addContents(sysPackage, line);
            addPackage(sysPackage);
            sysPackage = new SoftwarePackage(id);
            id++;
            line = reader.nextLine();
        }
    }

    private void addContents(Package sysPackage, String line) {
        while (true) {
            String[] bits = line.split(":");

            if (bits[0].equals("Package")) {
                sysPackage.setName(bits[1].trim());
            }
            if (bits[0].equals("Description")) {
                setDescription(sysPackage, line);
            }
            if (bits[0].equals("Depends")) {
                setDependencies(sysPackage, bits[1].split(",|\\|"));
            }
            if (bits[0].equals("Pre-Depends")) {
                setReverseDependencies(sysPackage, bits[1].split(",|\\|"));
            }
            line = reader.nextLine();
            if (line == null || line.isEmpty()) {
                return;
            }
        }
    }

    private void setReverseDependencies(Package sysPackage, String[] dependencies) {
        List<String> dependenciesToSet = SplitAndFormatDependencies(dependencies);
        sysPackage.setReverseDependencies(dependenciesToSet);
    }

    private void setDependencies(Package sysPackage, String[] dependencies) {
        List<String> dependenciesToSet = SplitAndFormatDependencies(dependencies);
        sysPackage.setDependencies(dependenciesToSet);
    }

    private List<String> SplitAndFormatDependencies(String[] dependencies) {
        List<String> dependenciesToReturn = new ArrayList<>();
        for (String dependency : dependencies) {
            dependency = dependency.trim().split(" ")[0];
            dependenciesToReturn.add(dependency);
        }
        return dependenciesToReturn;
    }

    private void addPackage(Package sysPackage) {
        packages.add(sysPackage);
        packagesNameMap.put(sysPackage.getName(), sysPackage);
        packagesIdMap.put(sysPackage.getId(), sysPackage);
    }

    private void setDescription(Package sysPackage, String firstLine) {
        List<String> description = new ArrayList<>();
        String[] bits = firstLine.split("Description: ");
        description.add(bits[1]);
        String s = reader.nextLine();
        while (s.startsWith(" ")) {
            description.add(s);
            s = reader.nextLine();
        }
        reader.previousLine();
        sysPackage.setDescription(description);
    }

    public boolean hasPackage(String sysPackage) {
        return packagesNameMap.containsKey(sysPackage);
    }

    public Package getPackage(int id) {
        return packagesIdMap.get(id);
    }

    private Package getPackage(String packageName) {
        return packagesNameMap.getOrDefault(packageName, null);
    }

    public int getId(String sysPackage) {
        Package pack = packagesNameMap.getOrDefault(sysPackage, null);
        if (pack == null) {
            return -1;
        } else {
            return pack.getId();
        }
    }

    public void sortPackagesByName() {
        packages.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
    }
}
