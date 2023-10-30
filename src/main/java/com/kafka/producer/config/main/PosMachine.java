package com.kafka.producer.config.main;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import com.kafka.producer.config.AppConfig;

public class PosMachine {
	public static void main(String[] args) {
		System.out.println("creating kafka producer configurations");
		System.out.println("================");
		
		//1.properties object is create
		Properties prop = new Properties();
		
		//add all required producer configuration to the properties object
		prop.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfig.APPLICATION_ID);
		prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOTSTRAP_SERVERS);
		prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
		prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		//2.create kafka producer
		KafkaProducer<Integer, String> posMachine = new KafkaProducer<Integer, String>(prop);
		System.out.println("kafka producer created");
		
		System.out.println("sending the 1 million records");
		//3.create producer record and send to the kafka brokers
		long startTime = System.currentTimeMillis();
		for(int i=0; i<AppConfig.NUM_EVENTS; i++) {
			String message = "invoice copy-"+i;
			ProducerRecord<Integer, String> invoice = new ProducerRecord<Integer, String>(AppConfig.TOPIC_NAME, i, message);
			posMachine.send(invoice);
		}
		System.out.println("records sent");
		long endTime = System.currentTimeMillis();
		System.out.println("total time taken to send "+AppConfig.NUM_EVENTS+ "are :\t"+(endTime - startTime)+"in millis");
		//4.close the producer session
		posMachine.close();
		System.out.println("producer is closed");
	}
}
