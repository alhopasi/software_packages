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
        Package pack = new SoftwarePackage(0);
        String line = reader.nextLine();

        while (line != null) {
            addContents(pack, line);
            addPackage(pack);
            pack = new SoftwarePackage(id);
            id++;
            line = reader.nextLine();
        }

        setReverseDependencies();
    }

    private void addContents(Package pack, String line) {
        while (true) {
            String[] bits = line.split(":");

            if (bits[0].equals("Package")) {
                pack.setName(bits[1].trim());
            }
            if (bits[0].equals("Description")) {
                setDescription(pack, line);
            }
            if (bits[0].equals("Depends")) {
                setDependencies(pack, bits[1].split(",|\\|"));
            }
            line = reader.nextLine();
            if (line == null || line.isEmpty()) {
                return;
            }
        }
    }

    private void setReverseDependencies() {
        for (Package pack : packages) {
            List<String> dependencies = pack.getDependencies();
            for (String dependency : dependencies) {
                Package depPack = packagesNameMap.get(dependency);
                if (depPack == null) {
                    continue;
                }
                depPack.addReverseDependency(pack.getName());
            }
        }
        sortReversePackagesByName();
    }

    private void setDependencies(Package pack, String[] dependencies) {
        List<String> dependenciesToSet = SplitAndFormatDependencies(dependencies);
        dependenciesToSet.sort((d1,d2) -> d1.compareTo(d2));
        pack.setDependencies(dependenciesToSet);
    }

    private List<String> SplitAndFormatDependencies(String[] dependencies) {
        List<String> dependenciesToReturn = new ArrayList<>();
        for (String dependency : dependencies) {
            dependency = dependency.trim().split(" ")[0];
            dependenciesToReturn.add(dependency);
        }
        return dependenciesToReturn;
    }

    private void addPackage(Package pack) {
        packages.add(pack);
        packagesNameMap.put(pack.getName(), pack);
        packagesIdMap.put(pack.getId(), pack);
    }

    private void setDescription(Package pack, String firstLine) {
        List<String> description = new ArrayList<>();
        String[] bits = firstLine.split("Description: ");
        description.add(bits[1]);
        String s = reader.nextLine();
        while (s.startsWith(" ")) {
            description.add(s);
            s = reader.nextLine();
        }
        reader.previousLine();
        pack.setDescription(description);
    }

    public boolean hasPackage(String pack) {
        return packagesNameMap.containsKey(pack);
    }

    public Package getPackage(int id) {
        return packagesIdMap.get(id);
    }

    private Package getPackage(String packageName) {
        return packagesNameMap.getOrDefault(packageName, null);
    }

    public int getId(String packageName) {
        Package pack = packagesNameMap.getOrDefault(packageName, null);
        if (pack == null) {
            return -1;
        } else {
            return pack.getId();
        }
    }

    public void sortPackagesByName() {
        packages.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
    }

    public void sortReversePackagesByName() {
        for (Package pack : packages) {
            pack.getReverseDependencies().sort((d1,d2) -> d1.compareTo(d2));
        }
    }
}
