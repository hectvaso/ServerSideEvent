package com.server.side.sevent.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class NewsController {
	
	private List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
	// method for client supscription
	
	@CrossOrigin
	@RequestMapping(value ="/suscribe", consumes = MediaType.ALL_VALUE)
	public SseEmitter suscribe() {
		
		SseEmitter sseEmitter = new SseEmitter();
		System.out.print("entro por aqui");
		try {
			sseEmitter.send(SseEmitter.event().name("INIT"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return sseEmitter;
	}
	
	//method receive
	
	
	
	

}
