package org.mongolink.example.web.application;

import org.mongolink.example.web.configuration.MongoConfiguration;
import org.restlet.Application;
import org.restlet.Restlet;

public class ApiApplication extends Application {

    @Override
    public Restlet createInboundRoot() {
        TransactionFilter transactionFilter = new TransactionFilter();
        ApiRouter apiRouter = new ApiRouter(getContext());
        transactionFilter.setNext(apiRouter);
        return transactionFilter;
    }

    @Override
    public void stop() throws Exception {
        MongoConfiguration.stop();
    }
}
