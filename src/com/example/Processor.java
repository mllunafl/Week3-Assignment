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

    public Processor() {
        Set<Message> messageSet = new HashSet<>();
        todoMap.put(Status.INITIAL, messageSet);
        messageSet = new HashSet<>();
        todoMap.put(Status.ASSIGNED, messageSet);
        messageSet = new HashSet<>();
        todoMap.put(Status.IN_PROGRESS, messageSet);
        messageSet = new HashSet<>();
        todoMap.put(Status.DONE, messageSet);
    }

    public void doIt() throws InterruptedException {
        Scanner s = null;

        while (true) {
            try {
                s = new Scanner(System.in);
                System.out.println("Sleep for ");
                while (s.hasNext()){
                    Thread.sleep(s.nextInt());
                    this.moveIt();
                    this.readIt();
                }
            } finally {
                if (s != null){
                    s.close();
                }
            }
        }
    }

    private void moveIt() {

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
                       //get the set set from the map for done, and add to this set
                        Set<Message> messageSet = todoMap.get(Status.DONE);
                        messageSet.add(message2);
                    } else {
                        Set<Message> messageSet = todoMap.get(Status.INITIAL);
                        messageSet.add(message2);
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



