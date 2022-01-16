package guldilin.handler;

import guldilin.dto.ErrorDTO;
import lombok.SneakyThrows;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.rmi.RemoteException;

public class RemoteExceptionMapper implements ExceptionMapper<RemoteException> {
    @Override
    @SneakyThrows
    public Response toResponse(RemoteException e) {
//        if (e.getCause() != null) throw e.getCause();
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(ErrorDTO
                        .builder()
                        .error(ErrorCode.INTERNAL_SERVER_ERROR.name())
                        .message(e.getCause().getMessage())
                        .build())
                .build();
    }
}
