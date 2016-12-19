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
    public void doIt() throws IOException {
        Scanner s = null;
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
                    if (s.next() == "NONE") {
                        message.setPriority(Priority.NONE);
                    }
                    if (s.next() == "LOW") {
                        message.setPriority(Priority.LOW);
                    }
                    if (s.next() == "STANDARD") {
                        message.setPriority(Priority.STANDARD);
                    }
                    if (s.next() == "HIGH") {
                        message.setPriority(Priority.HIGH);
                    }
                    if (s.next() == "CRITICAL") {
                        message.setPriority(Priority.CRITICAL);
                    } else {
                        System.out.println("TRY AGAIN");
                    }
                    if (message.getPriority()!= null){
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
            if (s != null){
                s.close();
            }
        }


    }

    public static void main(String[] args) throws IOException {
        Creator creator = new Creator();
        creator.doIt();
//        message.setId(3);
//        message.setPriority(Priority.HIGH);
//        message.setDescription("test-description3");
//        message.setSenderName("text-name3");


    }
}
