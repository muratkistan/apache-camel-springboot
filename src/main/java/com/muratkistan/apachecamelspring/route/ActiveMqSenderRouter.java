package com.muratkistan.apachecamelspring.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqSenderRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("timer:active-mq-timer?period=10000")
                .routeId("Activemq-sender-route")
                .transform().constant("This is a message for ActiveMQ")
                .log("${body}")
                .to("activemq:my-activemq-queue2");

    }
}
