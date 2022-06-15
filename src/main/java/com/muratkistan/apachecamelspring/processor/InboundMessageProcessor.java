package com.muratkistan.apachecamelspring.processor;

import com.muratkistan.apachecamelspring.beans.NameAddress;
import com.muratkistan.apachecamelspring.beans.OutboundNameAddress;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@Slf4j
public class InboundMessageProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

            NameAddress nameAddress = exchange.getIn().getBody(NameAddress.class);
            exchange.getIn().setBody(new OutboundNameAddress(nameAddress.getName(),returnOutboundAddress(nameAddress)));
    }

    private String returnOutboundAddress(NameAddress nameAddress){
        StringBuilder concatenatedAddress = new StringBuilder(200);
        concatenatedAddress.append(nameAddress.getHouseNumber() + ",");
        concatenatedAddress.append(" " + nameAddress.getCity() + ",");
        concatenatedAddress.append(" " + nameAddress.getProvince() + ",");
        concatenatedAddress.append(" " + nameAddress.getPostalCode());

        return concatenatedAddress.toString();
    }
}
