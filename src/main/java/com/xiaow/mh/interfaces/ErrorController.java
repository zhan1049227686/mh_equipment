package com.xiaow.mh.interfaces;


import com.xiaow.mh.framework.vc.AppErrorController;
import com.xiaow.mh.framework.vc.ErrorBrief;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * 错误页
 * Created by POJO on 6/16/16.
 */
@Controller
@ControllerAdvice
@PropertySource("classpath:AppResources.properties")
public class ErrorController extends AppErrorController {

    @Value("${app.domain}")
    public String domain;

    @Override
    public String handle403(ErrorBrief eb) {
        return "redirect:http://" + domain;
    }

    @Override
    public String handle502(ErrorBrief eb) {
        return handle403(eb);
    }

    /**
     * 错误处理入口
     *
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(value = {Throwable.class})
    public ModelAndView errorMapping(HttpServletRequest request, HttpServletResponse response) {
        ErrorBrief eb = new ErrorBrief(response.getStatus(), getErrorAttributes(request, true));
        String header = request.getHeader("X-Requested-With");

        if (StringUtils.isNotBlank(header) && (header.equals("X-Requested-With") || header.equals("XMLHttpRequest")) || request.getRequestURI().toLowerCase().contains("js"))
            return assertResponse(eb);

        return error(eb, request, response);
    }

    /**
     * 尽量用assertion来判断数据合法 这样可以拿到很详尽的Error
     *
     * @param eb
     * @return
     */
    @ResponseBody
    public ModelAndView assertResponse(ErrorBrief eb) {
        Map<String, Object> model = new HashMap<>();
        model.put("error", eb.message.replaceAll("\n", " "));
        MappingJackson2JsonView v = new MappingJackson2JsonView();
        v.setPrettyPrint(true);
        return new ModelAndView(v, model);
    }
}