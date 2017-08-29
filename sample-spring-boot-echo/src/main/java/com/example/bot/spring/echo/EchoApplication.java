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


import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;


@SpringBootApplication
@LineMessageHandler
public class EchoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }
        
    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event)  {
        System.out.println("event: " + event);
        //return new TextMessage("TEST: "+event.getMessage().getText());
String result = getUrlAsString("https://www.google.com");
System.out.println(result);
        
       return new TextMessage("TEST: "+event.getMessage().getText());
 
        //URL website = new URL("http://cdn.crunchify.com/wp-content/uploads/code/json.sample.txt");
        
//        return new TextMessage(response.toString());
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
   
public static String getUrlAsString(String url)
{
    try
    {
        URL urlObj = new URL(url);
        URLConnection con = urlObj.openConnection();

        con.setDoOutput(true); // we want the response 
        con.setRequestProperty("Cookie", "myCookie=test123");
        con.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        String newLine = System.getProperty("line.separator");
        while ((inputLine = in.readLine()) != null)
        {
            response.append(inputLine + newLine);
        }

        in.close();

        return response.toString();
    }
    catch (Exception e)
    {
        throw new RuntimeException(e);
    }
}
}
