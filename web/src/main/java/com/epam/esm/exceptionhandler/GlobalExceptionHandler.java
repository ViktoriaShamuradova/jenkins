package com.epam.esm.exceptionhandler;

import com.epam.esm.service.exception.ExceptionCode;
import com.epam.esm.service.exception.NoSuchResourceException;
import com.epam.esm.service.exception.ResourceAlreadyExistsException;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.exception.ValidationException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Data
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource resourceBundle;

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleAuthenticationException(BadCredentialsException e,
                                                                           HttpServletRequest request) {
        return createResponseEntity(e, request, ExceptionCode.WRONG_PASSWORD_USERNAME, HttpStatus.UNAUTHORIZED);
    }

@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
public ResponseEntity<ExceptionResponse> handleAuthenticationException(HttpRequestMethodNotSupportedException e,
                                                                       HttpServletRequest request) {
    return createResponseEntity(e, request, ExceptionCode.NOT_SUPPORTED_OPERATION, HttpStatus.UNAUTHORIZED);
}

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> handleAuthenticationException(AuthenticationException e,
                                                                           HttpServletRequest request) {
        return createResponseEntity(e, request, ExceptionCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(value = ResourceAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleException(ResourceAlreadyExistsException e, HttpServletRequest request) {
        return createResponseEntity(e, request, HttpStatus.CONFLICT);
    }


    @ExceptionHandler({NoSuchResourceException.class})
    public ResponseEntity<ExceptionResponse> handleException(NoSuchResourceException e, HttpServletRequest request) {
        return createResponseEntity(e, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ExceptionResponse> handleException(Exception ex, HttpServletRequest request) {
       return createResponseEntity(ex, request, ExceptionCode.NOT_VALID_ID, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponse> handleException(ValidationException e, HttpServletRequest request) {

        return createResponseEntity(e, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException e,
                                                                         HttpServletRequest request,
                                                                         Authentication authentication) {
        String errorCode = ExceptionCode.ACCESS_IS_DENIED.getErrorCode();
        if (authentication == null) {
            errorCode = ExceptionCode.UNAUTHORIZED.getErrorCode();
        }

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode(errorCode);

        exceptionResponse.setErrorMessage(resourceBundle.getMessage(errorCode, new Object[]{e.getMessage()},
                request.getLocale())
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(BindException exception, HttpServletRequest request) {
        exception.printStackTrace();
        List<ErrorModel> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorModel(err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                .distinct()
                .collect(Collectors.toList());
        return ErrorResponse.builder().errorMessages(errorMessages).build();
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Throwable e) {
        return createResponseEntity(e, null, ExceptionCode.DEFAULT_ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ResponseEntity<ExceptionResponse> createResponseEntity(ServiceException e, HttpServletRequest request, HttpStatus httpStatus) {
        e.printStackTrace();
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode(e.getErrorCode());

        exceptionResponse.setErrorMessage(resourceBundle.getMessage(e.getErrorCode(), new Object[]{e.getMessage()},
                request.getLocale())
        );
        return new ResponseEntity<>(exceptionResponse, httpStatus);
    }

    private ResponseEntity<ExceptionResponse> createResponseEntity(Throwable e,
                                                                   HttpServletRequest request,
                                                                   ExceptionCode exceptionCode,
                                                                   HttpStatus httpStatus) {
        e.printStackTrace();
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode(exceptionCode.getErrorCode());
        exceptionResponse.setErrorMessage(resourceBundle.getMessage(exceptionCode.getErrorCode(), new Object[]{},
                request.getLocale())
        );
        return new ResponseEntity<>(exceptionResponse, httpStatus);
    }
}
