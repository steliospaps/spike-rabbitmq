package org.duckdns.papasv.rabbitmq;

import java.io.IOException;

import org.duckdns.papasv.rabbitmq.server.DockerServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class ReceiveFromServer {

	  public static void main(String[] argv)
	      throws java.io.IOException,
	             java.lang.InterruptedException {

	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    factory.setPort(5672);
		factory.setUsername("rabbitmq");
		factory.setPassword("rabbitmq");

	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(DockerServer.QUEUE_NAME_OUT, false, false, false, null);
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	    
	    Consumer consumer = new DefaultConsumer(channel) {
	        @Override
	        public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
	            throws IOException {
	          String message = new String(body, "UTF-8");
	          System.out.println(" [x] Received '" + message + "'");

	        }
	      };
	      channel.basicConsume(DockerServer.QUEUE_NAME_OUT, true, consumer);
	      
	  }
}
