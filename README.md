# MicroServices
https://www.youtube.com/watch?v=wUVlvX-UeSQ

1.Eureka
2.Openfeign
3.Spring Cloud LoadBalancer
4.RabbitMq
5.Zipkin
6.RestTemplate
7.h2 Database
8.Spring Cloud api Getway Server
=========================================
rabbitmq(erlang is needed)
rabbitmq if start problem
--------------------
rabbitmqctl shutdown
rabbitmqctl stop
rabbitmq-service.bat remove
rabbitmq-service.bat install
rabbitmq-service.bat start
rabbitmq-plugins enable rabbitmq_management
rabbitmq-plugins enable rabbitmq_shovel
-------------------------------
http://localhost:15672/#/
======================================
zipkin
download jar file
http://localhost:9411/zipkin/
connect zipkin and rabbitmq
set RABBIT_URI=amqp://localhost
java -jar zipkin-server-2.23.19-exec.jar
-----------------------
set rabbit_uri-amqp://localhost java -jar zipkin-server-2.23.19-exec.jar
===========================
