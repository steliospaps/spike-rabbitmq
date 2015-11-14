# spike-rabbitmq
a rabbit mq spike that I plant to eventually use as a prototype. 
usage:
  > vagrant up
  cd rabbitmq-spike
  mvn exec:java -Dexec.mainClass=org.duckdns.papasv.rabbitmq.Send 
  mvn exec:java -Dexec.mainClass=org.duckdns.papasv.rabbitmq.Receive 

Then you can access the rabbitmq management console on http://localhost:15672 (user/pass)
