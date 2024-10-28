package com.easy_expense.expense_tracker_api.ContollerAdvice;

import com.easy_expense.expense_tracker_api.CustomException.*;
import com.easy_expense.expense_tracker_api.dto.ErrorMsg;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ErrorMsg> handleExpenseNotFoundException(ExpenseNotFoundException expenseNotFoundException) {
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorMsg.setError(expenseNotFoundException.getMessage());
        errorMsg.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
    }

    /**
     * Category exception handler's
     */

    /**
     * Method for handle category not found exception
     *
     * @param categoryNotFoundException
     * @return ErrorMsg
     */
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorMsg> handleExpenseNotFoundException(CategoryNotFoundException categoryNotFoundException) {
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorMsg.setError(categoryNotFoundException.getMessage());
        errorMsg.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
    }


    /**
     * Method for handle category already exists exception
     *
     * @param categoryAlreadyExistsException
     * @return ErrorMsg
     */
    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorMsg> handleExpenseNotFoundException(CategoryAlreadyExistsException categoryAlreadyExistsException) {
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setStatusCode(HttpStatus.CONFLICT.value());
        errorMsg.setError(categoryAlreadyExistsException.getMessage());
        errorMsg.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMsg> handleGeneralException(Exception exception) {
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorMsg.setError(exception.getMessage());
        errorMsg.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorMsg> handleEmailAlreadyExistsException(EmailAlreadyExistsException emailAlreadyExistsException) {
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setStatusCode(HttpStatus.CONFLICT.value());
        errorMsg.setError(emailAlreadyExistsException.getMessage());
        errorMsg.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorMsg, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorMsg> handleEmailAlreadyExistsException(InvalidCredentialsException invalidCredentialsException) {
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        errorMsg.setError(invalidCredentialsException.getMessage());
        errorMsg.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorMsg, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("timestamp", LocalDateTime.now());
        errorMap.put("statusCode", HttpStatus.BAD_REQUEST.value());
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        errorMap.put("errors", errors);
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
