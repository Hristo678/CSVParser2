package com.company;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        //In this Map we save the buyers and their files!
        Map<String, File> buyersAndFilesMap = new LinkedHashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("files/invoices.csv"));
            String line = "";
            String[] firstLineElements =  reader.readLine().split(",");
            FileWriter fileWriter;
            while ((line = reader.readLine()) != null) {
                String[] elements = line.split(",");
                String buyer = elements[0];
                if (!buyersAndFilesMap.containsKey(buyer)) {

                    //If there is new buyer, here we put him in the map and create new file
                    buyersAndFilesMap.put(buyer, new File("files/" + buyer + "File.csv"));
                    fileWriter = new FileWriter(buyersAndFilesMap.get(buyer));

                    writeIntoFile(fileWriter, firstLineElements);

                    writeIntoFile(fileWriter, elements);

                }else {
                    File file = buyersAndFilesMap.get(buyer);
                    fileWriter = new FileWriter(file, true);
                    writeIntoFile(fileWriter, elements);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("No such file!");
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private static void writeIntoFile(FileWriter fileWriter, String[] elements) throws IOException {

        for (String element : elements) {
            fileWriter.write(element + ", ");
        }
        fileWriter.write(System.lineSeparator());
        fileWriter.flush();

    }
}
