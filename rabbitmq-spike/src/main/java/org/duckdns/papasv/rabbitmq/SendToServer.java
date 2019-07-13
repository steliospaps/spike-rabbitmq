package org.duckdns.papasv.rabbitmq;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

import org.duckdns.papasv.rabbitmq.server.DockerServer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class SendToServer {

	public static void main(String[] args) throws IOException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		factory.setUsername("rabbitmq");
		factory.setPassword("rabbitmq");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		try {
			channel.queueDeclare(DockerServer.QUEUE_NAME_IN, false, false, false, null);
			
			channel.basicPublish("", DockerServer.QUEUE_NAME_IN, null, args[0].getBytes());
		} finally {
			channel.close();
			connection.close();
		}
	}
}
