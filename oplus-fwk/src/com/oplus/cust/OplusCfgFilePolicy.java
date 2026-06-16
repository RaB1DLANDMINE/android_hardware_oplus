package com.oplus.cust;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OplusCfgFilePolicy {
    public static final int DEFAULT_SLOT = -2;

    private OplusCfgFilePolicy() {
    }

    public static String getCarrierId(int slot) {
        return "";
    }

    public static List<String> getCfgLevelList(String configName, int slot) {
        if (configName == null || configName.isEmpty()) {
            return Collections.emptyList();
        }

        File file = new File(configName);
        if (file.exists()) {
            return Collections.singletonList(file.getAbsolutePath());
        }

        return Collections.emptyList();
    }

    public static List<String> getCfgFileList(String configName, String dir, int slot) {
        if (dir == null || dir.isEmpty()) {
            return Collections.emptyList();
        }

        File base = new File(dir);
        if (!base.exists()) {
            return Collections.emptyList();
        }

        if (configName != null && !configName.isEmpty()) {
            File file = new File(base, configName);
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

    public static File getCfgTopPriorityFile(String configName, String dir, int slot) {
        List<String> files = getCfgFileList(configName, dir, slot);
        if (files.isEmpty()) {
            return null;
        }
        return new File(files.get(0));
    }
}
