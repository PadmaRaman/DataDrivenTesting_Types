package com.ai.testing.yamlIntg;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Map;

public class YamlUtils {
    private String filePath;
    private YamlUtils (String filePath) {
        this.filePath = filePath;
    }

    //Load the YAML file and return the data as a Map
    public Map<String, Object> loadYaml() {
        Yaml yaml = new Yaml();
        try(InputStream inputStream = new FileInputStream(filePath)) {
            return yaml.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Get a value from the YAML data by key
    public Object getValue(String key) {
        Map<String, Object> yamlData = loadYaml();
        if (yamlData != null) {
            return yamlData.get(key);
        }
        return null;
    }

    //Get a list of values from YAML
    public Object getList(String key) {
        Map<String, Object> yamlData = loadYaml();
        if (yamlData != null) {
            return yamlData.get(key);
        }
        return null;
    }

    //Get a nested map from YAML
    public Object getNestedMap(String parentKey, String childKey) {
        Map<String, Object> yamlData = loadYaml();
        if (yamlData != null && yamlData.containsKey(parentKey)) {
            Map<String, Object> nestedMap = (Map<String, Object>) yamlData.get(parentKey);
            if (nestedMap != null) {
                return nestedMap.get(childKey);
            }
        }
        return null;
    }

    //Set a value in the YAML data by key
    public void setValue(String key, Object value) {
        Map<String, Object> yamlData = loadYaml();
        if (yamlData != null) {
            yamlData.put(key, value);
            //Write the updated data back to the YAML file
            Yaml yaml = new Yaml();
            try (FileWriter writer = new FileWriter(filePath)) {
                yaml.dump(yamlData, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Set a value to a nested map in the YAML data
    public void setNestedMapValue(String parentKey, String childKey, Object value) {
        Map<String, Object> yamlData = loadYaml();
        if (yamlData != null && yamlData.containsKey(parentKey)) {
            Map<String, Object> nestedMap = (Map<String, Object>) yamlData.get(parentKey);
            if (nestedMap != null) {
                nestedMap.put(childKey, value);
                //Write the updated data back to the YAML file
                Yaml yaml = new Yaml();
                try (FileWriter writer = new FileWriter(filePath)) {
                    yaml.dump(yamlData, writer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Set a list of values in the YAML data by key
    public void setList(String key, Object value) {
        Map<String, Object> yamlData = loadYaml();
        if (yamlData != null) {
            yamlData.put(key, value);
            //Write the updated data back to the YAML file
            Yaml yaml = new Yaml();
            try (FileWriter writer = new FileWriter(filePath)) {
                yaml.dump(yamlData, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
