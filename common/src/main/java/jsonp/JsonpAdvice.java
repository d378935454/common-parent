package jsonp;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * Created by bean on 2016/5/19.
 */
@ControllerAdvice(basePackages = "com.bean")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice{
    public JsonpAdvice(){
        super("callback","jsonp");
    }
}
