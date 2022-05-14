package team.wuse.koob.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import team.wuse.koob.result.Result;
import team.wuse.koob.result.ResultFactory;

@ControllerAdvice
@ResponseBody
public class DefaultExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e) {
        String message = null;
        System.out.println("Except: ");
        e.printStackTrace();

        if (e instanceof IllegalArgumentException) {
            message = "传入了错误的参数";
        }

        if (e instanceof MethodArgumentNotValidException) {
            message = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
        }

        if (e instanceof UnauthorizedException) {
            message = "权限认证失败";
        }

        return ResultFactory.buildFailResult(message);
    }
}
