package com.ai.testing.yamlIntg;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class LoadReadYaml {
    public static void main(String[] args) {
        String filePath = "TestData.yaml";

        //1. Load the YAML file using SnakeYAML
        Yaml yaml = new Yaml();
        try (InputStream inputStream = new FileInputStream(filePath)) {
            //2. Parse the YAML content into a Map
            Map<String, Object> yamlData = yaml.load(inputStream);

            //3. Access and print the loaded data
            System.out.println("Loaded YAML Data:");
            System.out.println(yamlData);

            System.out.println("\nAccessing individual elements:");
            System.out.println("Name: " + yamlData.get("name"));
            System.out.println("Age: " + yamlData.get("age"));
            System.out.println("City: " + yamlData.get("city"));

            //Read the list
            System.out.println("Hobbies: " + yamlData.get("hobbies"));
            List<String> hobbies = (List<String>) yamlData.get("hobbies");
            System.out.println("My hobbies are:"+ hobbies);

            //Read the nested map
            Map<String, Object> address = (Map<String, Object>) yamlData.get("address");
            System.out.println("Street: " + address.get("street"));
            System.out.println("Zip: " + address.get("zip"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
