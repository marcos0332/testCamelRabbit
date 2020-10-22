package com.example.apacheCamelSpringRabbitMQ;

import org.apache.camel.main.Main;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;

@SpringBootApplication
public class ApacheCamelSpringRabbitMqApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApacheCamelSpringRabbitMqApplication.class, args);
        Main main= new Main();
        main.configure().addRoutesBuilder(new PublicarMensajeRabbit());
        main.run(args);
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }

/*

    public static final String QUEUE ="PlayJavaQueue";
    public static final String EXCHANGE ="PlayJavaExchange";
    public static final String ROUTING_KEY ="PlayJavaRoutingKey";

    @Bean
    Queue queue() {
        return new Queue(QUEUE, false);
    }

    @Bean
    DirectExchange exchange(){
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
*/




}
