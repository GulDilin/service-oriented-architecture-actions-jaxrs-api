package guldilin;

import guldilin.controllers.CalculationResource;
import guldilin.filters.CorsFilter;
import guldilin.handler.ArgumentFormatExceptionMapper;
import guldilin.handler.EntryNotFoundMapper;
import guldilin.handler.NullPointerMapper;
import guldilin.handler.StorageServiceRequestErrorMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RestApplication extends Application {
    private final Set<Object> singletons = new HashSet<>();
    private final Set<Class<?>> empty = new HashSet<>();

    public RestApplication() {
        singletons.add(new CalculationResource());
        singletons.add(new NullPointerMapper());
        singletons.add(new StorageServiceRequestErrorMapper());
        singletons.add(new EntryNotFoundMapper());
        singletons.add(new ArgumentFormatExceptionMapper());
        singletons.add(new CorsFilter());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
