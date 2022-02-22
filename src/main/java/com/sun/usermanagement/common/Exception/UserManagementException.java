package com.sun.usermanagement.common.Exception;

import lombok.Data;

@Data
public class UserManagementException extends RuntimeException {

    private ErrorObject errorObject;

    public UserManagementException(ErrorObject response) {
        this.errorObject = response;
    }

    public static Builder badRequest() {
        return new Builder(BadRequestException.class);
    }

    public static Builder dataNotFound() {
        return new Builder(DataNotFoundException.class);
    }

    public static Builder dataConflict() {
        return new Builder(DataConflictException.class);
    }

    public static Builder unauthorized() {
        return new Builder(UnauthorizedException.class);
    }

    public static Builder serviceError() {
        return new Builder(UserManagementException.class);
    }

    /// Builder class
    public static class Builder {

        public final Class<? extends UserManagementException> exceptionClass;

        public Builder(Class<? extends UserManagementException> exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public UserManagementException build(String errorMessage) {
            ErrorObject errorObject = new ErrorObject();
            errorObject.setMessage(errorMessage);
            return createCorresponseException(errorObject);
        }

        private UserManagementException createCorresponseException(ErrorObject errorObject) {
            if (this.exceptionClass == BadRequestException.class) {
                return new BadRequestException(errorObject);
            }

            if (this.exceptionClass == DataNotFoundException.class) {
                return new DataNotFoundException(errorObject);
            }

            if (this.exceptionClass == UnauthorizedException.class) {
                return new UnauthorizedException(errorObject);
            }

            if (this.exceptionClass == InternerServerErrorException.class) {
                return new InternerServerErrorException(errorObject);
            }

            if (this.exceptionClass == ForbiddenException.class) {
                return new ForbiddenException(errorObject);
            }

            if (this.exceptionClass == DataConflictException.class) {
                return new DataConflictException(errorObject);
            }

            return new UserManagementException(errorObject);
        }

    }

}
