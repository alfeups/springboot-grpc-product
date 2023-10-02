package br.com.studies.grpcspringcourse.exception.handler;

import br.com.studies.grpcspringcourse.exception.BaseBusinessException;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ExceptionHandler {

    @GrpcExceptionHandler(BaseBusinessException.class)
    public StatusRuntimeException handleBusinessExpception(BaseBusinessException ex) {
        return ex.getStatusCode()
                .withCause(ex.getCause())
                .withDescription(ex.getErrorMessage())
                .asRuntimeException();
    }
}
