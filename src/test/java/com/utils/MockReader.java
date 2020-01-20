package com.utils;

import java.util.ArrayList;
import java.util.List;

public class MockReader implements Reader {

    private List<String> lines;
    private int index;

    public MockReader() {
        lines = new ArrayList<>();
        index = 0;
    }

    public void readFile() {
        lines.add("Package: test-package");
        lines.add("Status: install ok installed");
        lines.add("Priority: optional");
        lines.add("Section: java");
        lines.add("Installed-Size: 2086");
        lines.add("Maintainer: Ubuntu Developers <ubuntu-devel-discuss@lists.ubuntu.com>");
        lines.add("Architecture: all");
        lines.add("Version: 1.8.2-4build");
        lines.add("Replaces: ant-doc (<= 1.6.5-1), libant1.6-java");
        lines.add("Depends: not-found-package, not-found-package2 | not-found-package3");
        lines.add("Recommends: ant-optional");
        lines.add("Suggests: default-jdk | java-compiler | java-sdk, ant-gcj, ant-doc");
        lines.add("Breaks: ant-doc (<= 1.6.5-1)");
        lines.add("Pre-Depends: does not | depend");
        lines.add("Conflicts: libant1.6-java");
        lines.add("Description: Package for tests");
        lines.add(" testing displaying of stuff \\ and / and < and > ok?");
        lines.add(" one more line");
        lines.add("Original-Maintainer: Debian Java Maintainers <pkg-java-maintainers@lists.alioth.debian.org>");
        lines.add("Homepage: http://ant.apache.org/");
        lines.add("");
        lines.add("Package: found-package");
        lines.add("Status: install ok installed");
        lines.add("Priority: optional");
        lines.add("Section: java");
        lines.add("Installed-Size: 1642");
        lines.add("Maintainer: Ubuntu Developers <ubuntu-devel-discuss@lists.ubuntu.com>");
        lines.add("Architecture: all");
        lines.add("Version: 2.11.0-4");
        lines.add("Depends: test-package (<= 0.33)");
        lines.add("Suggests: libxerces2-java-doc, libxerces2-java-gcj");
        lines.add("Description: Some more tests");
        lines.add(" in this package");
        lines.add("Original-Maintainer: Debian Java Maintainers <pkg-java-maintainers@lists.alioth.debian.org>");
        lines.add("Homepage: http://xerces.apache.org/xerces2-j/");
        lines.add("");
    }

    public String nextLine() {
        if (lines == null || index == lines.size()) {
            return null;
        }
        index++;
        return lines.get(index - 1);
    }

    public String previousLine() {
        if (lines == null) {
            return "";
        }
        index--;
        return lines.get(index);
    }
}

