package com.oplus.cust;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class OplusCfgFilePolicy {
    private OplusCfgFilePolicy() {
    }

    public static List<String> getCfgLevelList(String path, int type) {
        if (path == null || path.isEmpty()) {
            return Collections.emptyList();
        }

        File file = new File(path);
        if (file.exists()) {
            return Collections.singletonList(file.getAbsolutePath());
        }

        return Collections.emptyList();
    }

    public static List<String> getCfgFileList(String dir, String name, int type) {
        if (dir == null || dir.isEmpty()) {
            return Collections.emptyList();
        }

        File base = new File(dir);
        if (!base.exists()) {
            return Collections.emptyList();
        }

        if (name != null && !name.isEmpty()) {
            File file = new File(base, name);
            if (file.exists()) {
                return Collections.singletonList(file.getAbsolutePath());
            }
            return Collections.emptyList();
        }

        File[] children = base.listFiles();
        if (children == null || children.length == 0) {
            return Collections.emptyList();
        }

        ArrayList<String> result = new ArrayList<>(children.length);
        for (File child : children) {
            result.add(child.getAbsolutePath());
        }
        return result;
    }
}
