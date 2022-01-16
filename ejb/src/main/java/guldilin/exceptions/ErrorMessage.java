package guldilin.exceptions;

import java.io.Serializable;

public class ErrorMessage implements Serializable {
    public static final String IS_INTEGER = "Need to be integer";
    public static final String NOT_FOUND = "Entry not found";
    public static final String CITY_NOT_FOUND = "City not found";
    public static final String COORDINATES_NOT_FOUND = "Coordinates not found";
    public static final String STORAGE_SERVICE_REQUEST_FAILED = "Storage service request failed";
}
