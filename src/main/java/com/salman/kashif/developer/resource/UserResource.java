package com.salman.kashif.developer.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salman.kashif.developer.model.User;

@RestController
@RequestMapping("kafka")
public class UserResource {

	// For sending string as message
	// By default Kafka uses string to publish the message
//	@Autowired
//	private KafkaTemplate<String, String> kafkaTemplate;
	
	// For sending json
	// We have to explicitly tell kafka that we are going to publish json message, so some configuration is needed
	// see config package
	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	
	private static final String TOPIC = "warmup_topic";
	
	@GetMapping("/publish/{name}")
	public String post(@PathVariable("name") final String name) {
		
		kafkaTemplate.send(TOPIC, new User(name, "Computer", 100000L));
		
		return "Published Successfully";
	}
	
}
