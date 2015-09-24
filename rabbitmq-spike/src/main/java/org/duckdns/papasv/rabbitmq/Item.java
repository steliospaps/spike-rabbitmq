package org.duckdns.papasv.rabbitmq;

import java.util.List;

import lombok.Data;

@Data
public class Item {
	private String lastName;
	private List<String> givenNames;
}
