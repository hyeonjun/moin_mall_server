package com.buying.back.infra.advice;

import com.buying.back.application.common.exception.CommonException;
import com.buying.back.application.common.exception.code.InvalidInputValueException.InvalidInputValueExceptionCode;
import com.buying.back.application.common.vo.InvalidInputVO;
import com.buying.back.util.response.CommonResponse;
import com.buying.back.util.response.CommonResponseCode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Pattern.Flag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

  @ResponseStatus(value = HttpStatus.OK)
  @ExceptionHandler(value = CommonException.class)
  public CommonResponse<CommonException> defaultErrorHandler(HttpServletRequest req, CommonException e) {
    log.warn("defaultErrorHandler : " + e.toString());
    return new CommonResponse<>(e);
  }

  @ResponseStatus(value = HttpStatus.OK)
  @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
  public CommonResponse methodArgumentTypeMismatchErrorHandler(HttpServletRequest req,
    MethodArgumentTypeMismatchException e) {
    log.warn("methodArgumentTypeMismatchErrorHandler : " + e.toString());
    List<InvalidInputVO> invalidInputList = new ArrayList<>();
    invalidInputList.add(InvalidInputVO.valueOf(e.getName(), e.getValue(), null));
    return new CommonResponse(invalidInputList,
      InvalidInputValueExceptionCode.METHOD_ARGUMENT_TYPE_MISMATCH);
  }

  @ResponseStatus(value = HttpStatus.OK)
  @ExceptionHandler(value = BindException.class)
  public CommonResponse bindingErrorHandler(HttpServletRequest req, BindException e) {
    log.warn("bindingErrorHandler : " + e.toString());
    return getInvalidInputListError(e.getFieldErrors());
  }

  @ResponseStatus(value = HttpStatus.OK)
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public CommonResponse methodArgumentNotValidErrorHandler(HttpServletRequest req,
    MethodArgumentNotValidException e) {
    log.warn("methodArgumentNotValidErrorHandler : " + e.toString());
    return getInvalidInputListError(e.getBindingResult().getFieldErrors());
  }

  private CommonResponse getInvalidInputListError(List<FieldError> errors) {
    if (!CollectionUtils.isEmpty(errors)) {
      List<InvalidInputVO> invalidInputList = new ArrayList<>();
      for (FieldError error : errors) {
        String field = error.getField();
        Object value = error.getRejectedValue();
        String details = error.getDefaultMessage();
        String code = error.getCode();
        String[] arguments = Arrays.stream(Objects.requireNonNull(error.getArguments()))
          .filter(x -> !(x instanceof Flag[]))
          .skip(1)
          .flatMap(o -> o instanceof Object[] ? Arrays.stream((Object[]) o) : Stream.of(o))
          .map(Object::toString)
          .toArray(String[]::new);
        ArrayUtils.reverse(arguments);
        invalidInputList.add(InvalidInputVO.valueOf(field, value, details, code, arguments));
      }
      return new CommonResponse(invalidInputList,
        InvalidInputValueExceptionCode.INVALID_INPUT_VALUE);
    }
    return new CommonResponse(InvalidInputValueExceptionCode.INVALID_INPUT_VALUE);
  }


  @ResponseStatus(value = HttpStatus.OK)
  @ExceptionHandler(value = MissingServletRequestParameterException.class)
  public CommonResponse missingServletRequestParameterErrorHandler(HttpServletRequest req,
    MissingServletRequestParameterException e) {
    log.warn("missingServletRequestParameterErrorHandler : " + e.toString());
    List<InvalidInputVO> invalidInputList = new ArrayList<>();
    invalidInputList.add(InvalidInputVO.valueOf(e.getParameterName(), null, e.getMessage()));
    return new CommonResponse(invalidInputList, InvalidInputValueExceptionCode.INVALID_INPUT_VALUE);
  }

  @ResponseStatus(value = HttpStatus.OK)
  @ExceptionHandler(value = HttpMessageNotReadableException.class)
  public CommonResponse httpMessageNotReadableErrorHandler(HttpServletRequest req,
    HttpMessageNotReadableException e) {
    log.warn("httpMessageNotReadableErrorHandler : " + e.toString());
    return new CommonResponse(InvalidInputValueExceptionCode.INVALID_INPUT_VALUE);
  }

  @ResponseStatus(value = HttpStatus.OK)
  @ExceptionHandler(value = DataAccessException.class)
  public CommonResponse dataAccessExceptionHandler(HttpServletRequest req,
    DataAccessException e) {
    log.error("dataAccessExceptionHandler : [" + e.getClass().getName() + "]", e);
    return new CommonResponse(InvalidInputValueExceptionCode.INVALID_INPUT_VALUE);
  }

  @ResponseStatus(value = HttpStatus.OK)
  @ExceptionHandler(value = TransactionSystemException.class)
  public CommonResponse transactionSystemExceptionHandler(HttpServletRequest req,
    TransactionSystemException e) {
    log.error("transactionSystemExceptionHandler : [" + e.getClass().getName() + "]", e);
    if (e.contains(ConstraintViolationException.class)) {
      return new CommonResponse(InvalidInputValueExceptionCode.INVALID_INPUT_VALUE);
    }
    return new CommonResponse(CommonResponseCode.FAIL);
  }

  @ResponseStatus(value = HttpStatus.OK)
  @ExceptionHandler(value = JpaSystemException.class)
  public CommonResponse jpaSystemExceptionHandler(HttpServletRequest req,
    JpaSystemException e) {
    log.error("jpaSystemException : [" + e.getClass().getName() + "]", e);
    if (e.contains(ConstraintViolationException.class)) {
      return new CommonResponse(InvalidInputValueExceptionCode.INVALID_INPUT_VALUE);
    }
    return new CommonResponse(CommonResponseCode.FAIL);
  }

  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = Exception.class)
  public CommonResponse errorHandler(HttpServletRequest req, Exception e) {
    log.error("error at : [" + e.getClass().getName() + "]", e);
    return new CommonResponse(CommonResponseCode.FAIL);
  }
}
