package com.IT.service.base.exception;

import com.IT.common.base.result.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 自定义异常
 * </p>
 * / @EqualsAndHashCode(callSuper = true)
 * 该注解在实现 ToString EqualsAndHashCode方法时,不会考虑父类的属性
 * @author Kim
 * @version 1.0
 * @since 2022/11/28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ITException extends RuntimeException{

    private Integer code;

    public ITException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    /**
     * 打印完整信息
     */
    @Override
    public String toString() {
        return "ITException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}