package com.ai.testing.yamlIntg;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteYaml {
    public static void main(String[] args) {
        String filePath = "TestData.yaml";
        Map<String, Object> dataYaml = new HashMap<>();

        try (InputStream inputStream = new FileInputStream(filePath)) {
            Yaml yaml = new Yaml();
            dataYaml = yaml.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Delete a key-value pair from the map
        dataYaml.remove("age");

        // Delete an item from a list
        @SuppressWarnings("unchecked")
        String hobbyToRemove = "Traveling";
        List<String> hobbies = (List<String>) dataYaml.get("hobbies");
        if (hobbies != null) {
            hobbies.remove(hobbyToRemove);
        }

        //Delete entire list
        dataYaml.remove("hobbies");

        //Delete a key-value pair from nested map
        String keyToRemove = "zip";
        @SuppressWarnings("unchecked")
        Map<String, Object> address = (Map<String, Object>) dataYaml.get("address");
        if (address != null) {
            address.remove(keyToRemove);
        }

        //Delete entire nested map
        dataYaml.remove("address");

        //DumperOptions for pretty printing
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);

        //Load the options into the Yaml instance
        Yaml yaml = new Yaml(options);

        //Write the updated data back to the YAML file
        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(dataYaml, writer);
            System.out.println("Yaml file updated successfully at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

