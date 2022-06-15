package com.muratkistan.apachecamelspring.route;

import com.muratkistan.apachecamelspring.beans.NameAddress;
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
                .log(LoggingLevel.INFO,"Fetched Rows : ${body}");
    }
}
