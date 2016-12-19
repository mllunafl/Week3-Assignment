package com.example;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by LunaFlores on 12/16/16.
 */
public class Processor {

    static ObjectMapper mapper = new ObjectMapper();
    static Map<Status, Set<Message>> todoMap = new HashMap<>();
    static Set<Message> messageSet = new HashSet<>();

    public void doIt() throws InterruptedException {
        while (true) {
            Thread.sleep(900l);
            this.moveIt();
            this.readIt();

        }
    }

    private void moveIt() {
        //todoMap.put(Status.INITIAL, messageSet);
        //messageSet.add(message1.getDescription());
        //Message message = new Message();
        //messageSet.add(message);

        //System.out.println("messageSet" + todoMap);

        for (Map.Entry<Status, Set<Message>> entry : todoMap.entrySet()) {
            System.out.print(entry + ", ");
        }
        for (Map.Entry<Status, Set<Message>> entry : todoMap.entrySet()){
            System.out.println("in iterator");
            if (entry.getKey() == Status.IN_PROGRESS){
                System.out.println("inprogress");
                System.out.println("removed"+ todoMap.remove( entry));

                //todoMap.put(Status.DONE, entry.getValue());
            }
            if (entry.getKey() == Status.ASSIGNED){
                System.out.println("inassigned");
                System.out.println("removed" + todoMap.remove(entry));
                //todoMap.put(Status.IN_PROGRESS, entry.getValue());
            }
            if (entry.getKey() == Status.INITIAL){
                System.out.println("ininitial");

                System.out.println("removed" + todoMap.remove( entry));
                //todoMap.put(Status.IN_PROGRESS, entry.getValue());
                //todoMap.remove(Status.INITIAL, entry.getValue());
               // System.out.println(todoMap.put(Status.ASSIGNED, set));

            }
        }

    }

    private void readIt() {
        File file = new File(".");
        Message message = new Message();
        for (File f : file.listFiles()) {

            if ((f.getName().endsWith(".json"))) {
                try (BufferedReader in = new BufferedReader(new FileReader(f.getName()))) {

                    //read the one and only line of the file
                    String stringMessage = in.readLine();
                    //deseralize json
                    Message message2 = mapper.readValue(stringMessage, Message.class);
                    //System.out.println("after " + message1);
                    if (message2.getPriority() == Priority.NONE) {
                        messageSet.add(message2);
                        todoMap.put(Status.DONE, messageSet);
                    } else {
                        messageSet.add(message2);
                        todoMap.put(Status.INITIAL, messageSet);
                    }


                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();
        processor.doIt();

    }
}



