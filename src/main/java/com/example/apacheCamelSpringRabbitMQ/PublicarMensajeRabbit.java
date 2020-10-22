package com.example.apacheCamelSpringRabbitMQ;

import org.apache.camel.builder.RouteBuilder;

public class PublicarMensajeRabbit extends RouteBuilder {

    @Override
    public void configure(){
        from("timer:simple?period=100000")
                .log("Publicando mensaje")
                .setBody(constant("Publicando mensaje desde Camel para rabbit y luego ser leido en camel"))
                .to("rabbitmq:localhost:5672/PlayJavaExchange?username=guest&password=guest&autoDelete=false&routingKey=PlayJavaRoutingKey&queue=tasksQueue").end();


        from("rabbitmq:localhost:5672/PlayJavaExchange?username=guest&password=guest&autoDelete=false&routingKey=PlayJavaRoutingKey")
                .log("aqui llego el mensaje y es : ")
                .process( e -> System.out.println("se supone que aquí imprime el mensaje de la cola "+ e.getMessage().getBody(String.class))).end();

    }


}
