package guldilin.exceptions;

import java.io.Serializable;

public class StorageServiceRequestException extends Throwable {
    public StorageServiceRequestException(String message) {
        super(message);
    }

    public StorageServiceRequestException() {
        super(ErrorMessage.STORAGE_SERVICE_REQUEST_FAILED);
    }
}
