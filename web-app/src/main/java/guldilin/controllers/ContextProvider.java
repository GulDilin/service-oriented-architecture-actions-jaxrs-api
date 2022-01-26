package guldilin.controllers;

import guldilin.util.Utils;
import lombok.SneakyThrows;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class ContextProvider {
    @SneakyThrows
    public Context getContext() {
        Hashtable<String, String> environment = new Hashtable<>();

        environment.put(Context.INITIAL_CONTEXT_FACTORY,
                Utils.getEnvOrPropElseDefault("CONTEXT_PROVIDER_FACTORY_CLASS",
                        "fish.payara.ejb.rest.client.RemoteEJBContextFactory"));
        environment.put(Context.PROVIDER_URL, Utils.getEnvOrPropElseDefault("CONTEXT_PROVIDER_URL",
                "http://localhost:8080/ejb-invoker/"));
        return new InitialContext(environment);
    }
}
