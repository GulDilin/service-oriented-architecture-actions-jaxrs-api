package guldilin.handler;

import guldilin.dto.ErrorDTO;
import guldilin.exceptions.ErrorMessage;
import lombok.SneakyThrows;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.rmi.RemoteException;

public class RemoteExceptionMapper implements ExceptionMapper<RemoteException> {
    @Override
    @SneakyThrows
    public Response toResponse(RemoteException e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(ErrorDTO
                        .builder()
                        .error(ErrorCode.REMOTE_SERVICE_UNAVAILABLE.name())
                        .message(ErrorMessage.REMOTE_SERVICE_UNAVAILABLE)
                        .build())
                .build();
    }
}
