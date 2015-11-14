package org.duckdns.papasv.rabbitmq;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Send {

	private static final String QUEUE_NAME = "hello";

	public static void main(String[] args) throws IOException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("user");
		factory.setPassword("pass");

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		try {
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			
			Item item = new Item();
			item.setLastName("hello");
			item.setGivenNames(Arrays.asList("there1","there2"));
			channel.basicPublish("", QUEUE_NAME, null, dataToJson(item).getBytes());
		} finally {
			channel.close();
			connection.close();
		}
	}
	public static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e){
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }
}
