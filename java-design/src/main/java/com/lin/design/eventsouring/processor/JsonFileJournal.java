package com.lin.design.eventsouring.processor;

import com.lin.design.eventsouring.event.AccountCreateEvent;
import com.lin.design.eventsouring.event.AccountDepositEvent;
import com.lin.design.eventsouring.event.AccountTransferEvent;
import com.lin.design.eventsouring.event.DomainEvent;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/4/25
 *  
 * @name: 
 *
 * @Description: 
 */
public class JsonFileJournal {

    private final File aFile;
    private final List<String> events = new ArrayList<>();
    private int index = 0;

    public static void main(String[] args) {
        File a = new File("resources/");

        System.out.println(a.getAbsolutePath());
        System.out.println(a.exists());

        System.out.println(a.isDirectory());

        System.out.println(a.listFiles());

    }
    public JsonFileJournal() {

        aFile = new File("resources/java-design/account.json");

        System.out.println(aFile.getAbsolutePath());

        if (aFile.exists()) {
            try(BufferedReader input = new BufferedReader(
                    new InputStreamReader(new FileInputStream(aFile),"UTF-8"))
            ){

                String line;
                while ((line = input.readLine()) != null) {
                    events.add(line);
                }
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }

        }
        else{
            reset();
        }
    }

    public void reset() {
        aFile.delete();
    }

    public void write(DomainEvent domainEvent) {
        Gson gson = new Gson();

        try (Writer bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(aFile,true),"UTF-8"))) {
            String eventString = null;
            if (domainEvent instanceof AccountCreateEvent) {
                eventString = gson.toJson(domainEvent, AccountCreateEvent.class);
            } else if (domainEvent instanceof AccountDepositEvent) {
                eventString = gson.toJson(domainEvent, AccountDepositEvent.class);
            } else if (domainEvent instanceof AccountTransferEvent) {
                eventString = gson.toJson(domainEvent, AccountTransferEvent.class);
            }
            bw.write(eventString + "\r\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DomainEvent readNext(){
        if (index >= events.size()) {
            return null;
        }
        String eventString = events.get(index++);

        JsonParser parse = new JsonParser();
        JsonElement element = parse.parse(eventString);
        String className = element.getAsJsonObject().get("eventClassName").getAsString();
        Gson gson = new Gson();
        DomainEvent event = null;
        if (AccountCreateEvent.class.getSimpleName().equals(className)) {
            event = gson.fromJson(eventString, AccountCreateEvent.class);
        } else if (AccountDepositEvent.class.getSimpleName().equals(className)) {
            event = gson.fromJson(eventString,AccountDepositEvent.class);
        } else if (AccountTransferEvent.class.getSimpleName().equals(className)) {
            event = gson.fromJson(eventString,AccountTransferEvent.class);
        }
        event.setRealTime(false);
        return event;
    }
}
