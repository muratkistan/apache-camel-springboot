package com.muratkistan.apachecamelspring.route;

import com.muratkistan.apachecamelspring.beans.NameAddress;
import com.muratkistan.apachecamelspring.processor.InboundMessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class BatchJPAProcessingRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("timer:readDB?period=10000")
                .routeId("readDBId")
                .to("jpa:"+ NameAddress.class.getName()+"?namedQuery=fetchAllRows")
                .split(body())
                    .process(new InboundMessageProcessor())
                    .log(LoggingLevel.INFO," Transformed Body : ${body}")
                    .convertBodyTo(String.class)
                    .to("file:src/data/output?fileName=outputFile.csv&fileExist=append&AppendChars=\\n")
                    .toD("jpa:"+NameAddress.class.getName()+"?nativeQuery=DELETE FROM NAME_ADDRESS WHERE id=${header.consumedId}&useExecuteUpdate=true")
                .end();
    }
}
