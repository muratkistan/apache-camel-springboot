package com.muratkistan.apachecamelspring.components;

import com.muratkistan.apachecamelspring.beans.NameAddress;
import com.muratkistan.apachecamelspring.processor.InboundMessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LegacyFileRoute extends RouteBuilder {

    BeanIODataFormat inboundDataFormat = new BeanIODataFormat("InboundMessageBeanIOMapping.xml","inputMessageStream");


    @Override
    public void configure() throws Exception {

        Logger logger = LoggerFactory.getLogger(getClass());
        from("file:src/data/input?fileName=inputFile.csv")
                .routeId("legacyFileMoveRouteId")
                .split(body().tokenize("\n",1,true))
                .unmarshal(inboundDataFormat)
                    .process(new InboundMessageProcessor())
                .log(LoggingLevel.INFO," Transformed Body : ${body}")
                    .convertBodyTo(String.class)
                    .to("file:src/data/output?fileName=outputFile.csv&fileExist=append&AppendChars=\\n")
                .end();
    }
}
