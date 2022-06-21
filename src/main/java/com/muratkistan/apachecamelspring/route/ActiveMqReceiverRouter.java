package com.muratkistan.apachecamelspring.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqReceiverRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:my-activemq-queue2")
                .routeId("Activemq-receiver-route")
                .to("log:received-message-from-activemq");
    }
}
