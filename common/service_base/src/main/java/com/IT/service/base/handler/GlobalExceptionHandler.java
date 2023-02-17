package com.IT.service.base.handler;

import com.IT.common.base.result.R;
import com.IT.common.base.result.ResultCodeEnum;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>TODO</p>
 *
 * @author IT小李
 * @version V1.0.0
 * @date 2023/1/23 18:10
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R error(Exception e){
        e.printStackTrace();
        return R.error();
    }

    @ResponseBody
    @ExceptionHandler(BadSqlGrammarException.class)
    public R error(BadSqlGrammarException e) {
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }


    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R error(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }
}
