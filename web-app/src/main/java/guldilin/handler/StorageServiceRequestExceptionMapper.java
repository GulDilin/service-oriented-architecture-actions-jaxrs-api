package guldilin.handler;

import guldilin.dto.ErrorDTO;
import guldilin.exceptions.ErrorMessage;
import guldilin.exceptions.StorageServiceRequestException;
import lombok.SneakyThrows;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class StorageServiceRequestExceptionMapper implements ExceptionMapper<StorageServiceRequestException> {
    @Override
    @SneakyThrows
    public Response toResponse(StorageServiceRequestException e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(ErrorDTO
                        .builder()
                        .error(ErrorCode.STORAGE_SERVICE_REQUEST_FAILED.name())
                        .message(ErrorMessage.STORAGE_SERVICE_REQUEST_FAILED)
                        .build())
                .build();
    }
}