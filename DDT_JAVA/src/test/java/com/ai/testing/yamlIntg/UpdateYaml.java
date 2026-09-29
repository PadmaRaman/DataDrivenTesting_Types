package com.ai.testing.yamlIntg;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateYaml {
    public static void main(String[] args) {
        String filePath = "TestData.yaml";
        Map<String, Object> yamlData = new HashMap<>();

        try (InputStream inputStream = new FileInputStream(filePath)) {
            Yaml yaml = new Yaml();
            // Load the existing YAML data
            yamlData = yaml.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        yamlData.put("Name", "Jane Doe");

        // Update the list
        @SuppressWarnings("unchecked")
        List<String> hobbies = (List<String>) yamlData.get("hobbies");
        if (hobbies != null) {
            hobbies.add("Cooking");
        } else {
            hobbies = new ArrayList<>();
            hobbies.add("Cooking");
            yamlData.put("hobbies", hobbies);
        }

        //Update teh Map
        @SuppressWarnings("unchecked")
        Map<String, Object> address = (Map<String, Object>) yamlData.get("address");
        if (address != null) {
            address.put("street", "456 Elm St");
            address.put("Door No", "Apt 2B");
        } else {
            address = new HashMap<>();
            address.put("street", "456 Elm St");
            yamlData.put("address", address);
        }

        //Configure DumperOptions for pretty printing
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);

        Yaml yaml = new Yaml(options);
        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(yamlData, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
