package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by LunaFlores on 12/16/16.
 */
public class Creator {
//    Priority priority;
//
//    public Creator(Priority priority) {
//        this.priority = priority;
//    }

    public void doIt() throws IOException {
        Scanner s = null;

        while (true){
            try {
                s = new Scanner(System.in);
                Message message = new Message();
                System.out.println("Input Description");
                int count = 1;
                while (s.hasNext()) {
                    if (message.getId() == 0) {
                        message.setId(++count);
                        message.setDescription(s.nextLine());
                        System.out.println(message.getDescription());
                        System.out.println("Input Name");
                    }
                    if (message.getDescription() != null) {
                        message.setSenderName(s.nextLine());
                        System.out.println(message.getSenderName());
                    }
                    if (message.getSenderName() != null) {
                        System.out.println("Input either (NONE, LOW, STANDARD, HIGH, CRITICAL) priority");

                        switch (s.nextLine()) {
                            case "NONE": message.setPriority(Priority.NONE);
                            case "LOW": message.setPriority(Priority.LOW);
                            case "STANDARD": message.setPriority(Priority.STANDARD);
                            case "HIGH": message.setPriority(Priority.HIGH);
                            case "CRITICAL": message.setPriority(Priority.CRITICAL);
                        }
                        if (message.getPriority() != null) {
                            ObjectMapper mapper = new ObjectMapper();
                            String json = mapper.writeValueAsString(message);
                            System.out.println(json);

                            try (PrintWriter out = new PrintWriter(new FileWriter("" + message.getId() + ".json"))) {

                                //write json to file.. only writing one line to file
                                out.println(json);
                                //flush it so its done writing
                                out.flush();
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }

                        }

                    }
                }
            } finally {
                if (s != null) {
                    s.close();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Creator creator = new Creator();
        creator.doIt();

    }
}
