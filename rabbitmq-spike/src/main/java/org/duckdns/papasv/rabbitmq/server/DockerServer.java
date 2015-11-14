package org.duckdns.papasv.rabbitmq.server;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.duckdns.papasv.rabbitmq.Item;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class DockerServer {
	public final static String QUEUE_NAME_IN = "server.in";
	public final static String QUEUE_NAME_OUT = "server.out";

	public static void main(String[] argv) throws java.io.IOException,
			java.lang.InterruptedException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("rabbit");
		factory.setUsername("user");
		factory.setPassword("pass");

		Connection connection = factory.newConnection();
		final Channel channelIn = connection.createChannel();

		channelIn.queueDeclare(QUEUE_NAME_IN, false, false, false, null);
		channelIn.queueDeclare(QUEUE_NAME_OUT, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channelIn) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				int in = Integer.parseInt(message);
				System.out
						.println(" [*] got "+in);
				channelIn.basicPublish("", QUEUE_NAME_OUT, null, String.valueOf(in+1).getBytes()); 	
			}
		};
		channelIn.basicConsume(QUEUE_NAME_IN, true, consumer);

	}
}
