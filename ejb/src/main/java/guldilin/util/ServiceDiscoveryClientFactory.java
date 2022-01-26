package guldilin.util;

import lombok.SneakyThrows;

import java.io.Serializable;
import java.net.URL;
import java.util.Optional;

public class ServiceDiscoveryClientFactory implements Serializable {

    @SneakyThrows
    public static String getServiceApiUrl(String serviceName) {
        String baseUrl = Optional.ofNullable(System.getenv("ZUUL_URI"))
                .orElse(Optional.ofNullable(System.getProperty("ZUUL_URI"))
                        .orElse("http://localhost:8762"));
        return new URL(new URL(baseUrl), serviceName).toString();
    }

    public static String getStorageApiUrl() {
        return getServiceApiUrl("storage-api");
    }
}
