package guldilin.util;

import java.util.Optional;

public class Utils {
    public static final String ACTIONS_API_BASE = getEnvOrProp("ACTIONS_API_BASE");
    public static final String ACTIONS_API_CALCULATION_RESOURCE = "http://localhost:8080/service-oriented-architecture-actions-jaxrs-api/CalculationResource";

    public static String getEnvOrPropElseDefault(String prop, String defaultValue) {
        return Optional.ofNullable(System.getenv(prop))
                .orElse(Optional.ofNullable(System.getProperty(prop))
                        .orElse(defaultValue));
    }

    public static String getEnvOrProp(String prop) {
        return Optional.ofNullable(System.getenv(prop)).orElse(System.getProperty(prop));
    }
}
