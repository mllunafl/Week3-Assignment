package com.example;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by LunaFlores on 12/16/16.
 */
public class Message {
    private int id;
    private String description;
    private String senderName;
    private Priority priority;

    public Message() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Enum getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return id == message.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", priority=" + priority +
                ", description='" + description + '\'' +
                ", senderName='" + senderName + '\'' +
                '}';
    }
}
