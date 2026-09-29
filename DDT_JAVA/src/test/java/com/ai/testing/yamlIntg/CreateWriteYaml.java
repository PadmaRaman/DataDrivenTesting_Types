package com.ai.testing.yamlIntg;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateWriteYaml {
    public static void main(String[] args) {
        String filePath = "TestData.yaml";

        //1/\. Create Map to hold the YAML structure
        Map<String, Object> yamlData = new HashMap<>();

        //2. Add simple key-value pairs to the map
        yamlData.put("name", "John Doe");
        yamlData.put("age", 30);
        yamlData.put("city", "New York");

        //3. Add a nested map to represent a complex structure
        Map<String, Object> address = new HashMap<>();
        address.put("street", "123 Main St");
        address.put("zip", "10001");
        yamlData.put("address", address);

        //4. Add a list to the map
        List<String> hobbies = new ArrayList<>();
        hobbies.add("Reading");
        hobbies.add("Traveling");
        hobbies.add("Swimming");
        yamlData.put("hobbies", hobbies);

        //5. Add a list of maps to represent a more complex structure
        List<Map<String, Object>> contacts = new ArrayList<>();
        Map<String, Object> contact1 = new HashMap<>();
        contact1.put("type", "email");
        contact1.put("value", "123@gmail.com");
        contacts.add(contact1);

        Map<String, Object> contact2 = new HashMap<>();
        contact1.put("type", "email");
        contact1.put("value", "456@gmail.com");
        contacts.add(contact2);

        yamlData.put("contacts", contacts);

        //6. Configure Formatting Options (Optional)
        // You can configure formatting options for the YAML output if needed
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true); // Enable pretty printing ie., clean indentation and line breaks for better readability

        //7. Instantiate SnakeYaml parser/serializer with the configured options
        Yaml yaml = new Yaml(options);

        //8. Write the YAML data to a file
        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(yamlData, writer); //Serializes map to file in YAML format
            System.out.println("YAML file created successfully at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
