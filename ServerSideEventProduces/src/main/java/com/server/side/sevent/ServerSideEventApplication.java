package com.server.side.sevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.server.side.sevent.controller.SimpleEventHandler;

import java.net.URI;
import java.util.concurrent.TimeUnit;
@SpringBootApplication
public class ServerSideEventApplication {

	/*public static void main(String[] args) {
		SpringApplication.run(ServerSideEventApplication.class, args);
	}*/
	 public static void main(String[] args) throws InterruptedException {
	        EventHandler eventHandler = new SimpleEventHandler();
	        String url = "http://localhost:8084/sse/mvc/words";
	        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));

	        try (EventSource eventSource = builder.build()) {
	        	 System.out.print("entro al consumidor dos");
	            eventSource.start();
	            System.out.print("entro al consumidor");

	            TimeUnit.MINUTES.sleep(10);
	        }
	    }
}
