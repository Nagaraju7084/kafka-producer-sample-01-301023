package com.kafka.producer.config;

public class AppConfig {
	public final static String APPLICATION_ID = "POS_MACHINE-01";
	public final static String BOOTSTRAP_SERVERS = "localhost:9092, localhost:9093,localhost:9094";
	public final static String TOPIC_NAME = "stock-ticks";
	public final static int NUM_EVENTS = 1000000;
}
