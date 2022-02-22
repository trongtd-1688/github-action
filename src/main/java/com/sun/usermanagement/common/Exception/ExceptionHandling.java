package com.sun.usermanagement.common.Exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleObjectStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(UserManagementException.class)
    public ResponseEntity handlerUserNameNotFound(UserManagementException ex) {
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        if (ex instanceof ForbiddenException) {
            status = HttpStatus.FORBIDDEN.value();
        }

        if (ex instanceof UnauthorizedException) {
            status = HttpStatus.UNAUTHORIZED.value();
        }

        if (ex instanceof DataConflictException) {
            status = HttpStatus.CONFLICT.value();
        }

        if (ex instanceof BadRequestException) {
            status = HttpStatus.BAD_REQUEST.value();
        }

        if (ex instanceof DataNotFoundException) {
            status = HttpStatus.NOT_FOUND.value();
        }

        ErrorObject errorObject = ex.getErrorObject();
        errorObject.setStatus(status);

        return new ResponseEntity(errorObject, HttpStatus.OK);
    }

    @ExceptionHandler(value = {ObjectOptimisticLockingFailureException.class, StaleObjectStateException.class})
    public final ResponseEntity<?> handleObjectOptimisticLockingFailureException(Exception ex) {
        log.error(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.CONFLICT;

        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatus(status.value());
        errorObject.setMessage("");
        return new ResponseEntity<>(errorObject, status);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleACLMethodException(AccessDeniedException ex, Model model) {
        log.error(ex.getMessage(), ex);
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());
        errorObject.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        model.addAttribute(errorObject);

        return getErrorPath();
    }

    private String getErrorPath() {
        return "error";
    }

    private ErrorObject setLogDebug(ErrorObject errorObject, Exception exception) {
        if (!(exception instanceof UserManagementException)) {
            log.error(exception.getMessage(), exception);
        }
        // only development env
        if (log.isDebugEnabled()) {
            errorObject.setDebug(exception.getStackTrace());
        }

        return errorObject;
    }
}
