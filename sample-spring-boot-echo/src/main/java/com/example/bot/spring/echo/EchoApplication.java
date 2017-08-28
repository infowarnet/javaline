/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.example.bot.spring.echo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@SpringBootApplication
@LineMessageHandler
public class EchoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }
        
    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {
        System.out.println("event: " + event);
        //return new TextMessage("TEST: "+event.getMessage().getText());
try{  
//code that may throw exception  
     URL url = new URL("http://cdn.crunchify.com/wp-content/uploads/code/json.sample.txt");

    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
    String str;
    while ((str = in.readLine()) != null) {
      System.out.println(str); 
    }
    in.close();
}
catch (MalformedURLException e){
        System.err.println("New URL failed");
        System.err.println("exception thrown: " + e.getMessage());
    }

       return new TextMessage("TEST: "+event.getMessage().getText());
 
        //URL website = new URL("http://cdn.crunchify.com/wp-content/uploads/code/json.sample.txt");
        
//        return new TextMessage(response.toString());
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
   
}
