//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ze.hk2;

import io.dropwizard.configuration.ConfigurationSourceProvider;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ExtFileConfigurationSourceProvider implements ConfigurationSourceProvider {
    private static final String ENV_CONFIG_PREFIX = "dwApp_";
    private static final String JAR_PREFIX = "jar!";
    public String transformConfigString = null;

    public ExtFileConfigurationSourceProvider() {
    }

    public InputStream open(String path) throws IOException {
        InputStream ymlInputStream = null;
        if (path.indexOf("jar!") > -1) {
            try {
                String[] paths = path.split("jar!");
                JarFile jarFile = new JarFile(paths[0].replace("file:/", "") + "jar");
                JarEntry entry = jarFile.getJarEntry(paths[1].substring(1));
                ymlInputStream = jarFile.getInputStream(entry);
                System.setProperty("confdir", (new File(path)).getParentFile().getPath());
            } catch (Exception var19) {
                throw new FileNotFoundException("File " + path + " not found");
            }
        } else {
            File file = new File(path);
            if (!file.exists()) {
                throw new FileNotFoundException("File " + file + " not found");
            }

            System.setProperty("confdir", (new File(file.getAbsolutePath())).getParentFile().getPath());
            ymlInputStream = new FileInputStream(file);
        }

        Yaml yaml = new Yaml();
        InputStream in = ymlInputStream;
        Throwable var24 = null;

        ByteArrayInputStream var8;
        try {
            Map<String, Object> yamlMap = (Map)yaml.load((InputStream)in);
            this.mergeYamlConfigWithEnvVariables(yamlMap);
            this.transformConfigString = yaml.dumpAsMap(yamlMap);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(this.transformConfigString.getBytes("utf-8"));
            var8 = byteIn;
        } catch (Throwable var18) {
            var24 = var18;
            throw var18;
        } finally {
            if (ymlInputStream != null) {
                if (var24 != null) {
                    try {
                        ((InputStream)in).close();
                    } catch (Throwable var17) {
                        var24.addSuppressed(var17);
                    }
                } else {
                    ((InputStream)ymlInputStream).close();
                }
            }

        }

        return var8;
    }

    private void mergeYamlConfigWithEnvVariables(Map<String, Object> yamlMap) {
        Map<String, String> osEnvVariables = System.getenv();
        Properties processEnvVariables = System.getProperties();
        this.mergeYamlConfigWithMap(yamlMap, osEnvVariables);
        this.mergeYamlConfigWithMap(yamlMap, processEnvVariables);
    }

    private void mergeYamlConfigWithMap(Map<String, Object> yamlRootMap, Map osEnvVariables) {
        osEnvVariables.entrySet();
        osEnvVariables.entrySet().forEach((entry) -> {
            Entry mapEntry = (Entry)entry;
            String key = mapEntry.getKey().toString();
            if (key.startsWith("dwApp_")) {
                key = key.substring("dwApp_".length());
                String[] paths = key.split("_");
                Map nodeMap = this.getNodeMapWithNodePaths(yamlRootMap, paths);
                if (nodeMap != null) {
                    nodeMap.put(paths[paths.length - 1], mapEntry.getValue());
                }
            }

        });
    }

    private Map getNodeMapWithNodePaths(Map<String, Object> yamlRootMap, String[] paths) {
        Map<String, Object> currentMap = yamlRootMap;

        for(int i = 0; i < paths.length - 1; ++i) {
            if (!currentMap.containsKey(paths[i])) {
                return null;
            }

            Object nodeMape = currentMap.get(paths[i]);
            if (!(nodeMape instanceof Map)) {
                return null;
            }

            currentMap = (Map)nodeMape;
        }

        if (currentMap.containsKey(paths[paths.length - 1])) {
            return currentMap;
        } else {
            return currentMap;
        }
    }
}
