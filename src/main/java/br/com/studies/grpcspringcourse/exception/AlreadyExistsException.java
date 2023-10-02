package br.com.studies.grpcspringcourse.exception;

import io.grpc.Status;

public class AlreadyExistsException extends BaseBusinessException {

    private static final String ERROR_MESSAGE = "Product is already registered in the system.";
    private final String name;

    public AlreadyExistsException(String name) {
        super(String.format(ERROR_MESSAGE, name));
        this.name = name;
    }

    @Override
    public Status getStatusCode() {
        return Status.ALREADY_EXISTS;
    }

    @Override
    public String getErrorMessage() {
        return String.format(ERROR_MESSAGE, this.name);
    }
}
