# spike-rabbitmq
a rabbit mq spike that I plant to eventually use as a prototype. 
usage:
  > 
  (cd rabbitmq-spike; mvn package)
  vagrant up

  mvn exec:java -Dexec.mainClass=org.duckdns.papasv.rabbitmq.ReceiveFromServer
  mvn exec:java -Dexec.mainClass=org.duckdns.papasv.rabbitmq.SendToServer -Dexec.args="24345"

Then you can access the rabbitmq management console on http://localhost:15672 (user/pass)

see also
  >  
  mvn exec:java -Dexec.mainClass=org.duckdns.papasv.rabbitmq.Send 
  mvn exec:java -Dexec.mainClass=org.duckdns.papasv.rabbitmq.Receive 



