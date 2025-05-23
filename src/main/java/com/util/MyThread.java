package com.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class MyThread  {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("test.txt"));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            // Adjust the split regex as per your input file format
            String[] columns = lines.get(i).split("\\s+");
            sb.append(String.join("||", columns));
            if (i < lines.size() - 1) {
                sb.append("//");
            }
        }
        Files.write(Paths.get("output.csv"), sb.toString().getBytes());
        System.out.println("CSV file generated successfully.");
    }
}
