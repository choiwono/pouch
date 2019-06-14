package my.examples.pouch.aop;

import com.google.common.base.Joiner;
import lombok.RequiredArgsConstructor;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final Logger log = LoggerFactory.getLogger(LoggingAspect.class);
    private final SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
    private final SlackMessage sm = new SlackMessage();
    private final Environment environment;

    private String paramMapToString(Map<String, String[]> paramMap) {
        return paramMap.entrySet().stream()
                .map(entry -> String.format("%s -> (%s)",
                        entry.getKey(), Joiner.on(",").join(entry.getValue())))
                .collect(Collectors.joining(", "));
    }

    @Pointcut("within(my.examples.pouch.controller.api..*)") // 3
    public void onRequest() {}

    //api메서드들이 실행이 되고 나서 무슨 요청이 들어왔는지 log를 보여준다.
    @After("execution(* my.examples.pouch.controller.api.*.*(..))") // 4
    public Object doLogging(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = // 5
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        //파라미터로 받은 값들을 map에 저장
        Map<String, String[]> paramMap = request.getParameterMap();
        String params = "";
        //파라미터가 비어있지 않다면
        if (paramMap.isEmpty() == false) {
            params = " [" + paramMapToString(paramMap) + "]";
        }

        long start = System.currentTimeMillis();
        try {
            return joinPoint.getArgs();
        } finally {
            long end = System.currentTimeMillis();
            log.info("Request: {} {}{} < {} ({}ms)", request.getMethod(), request.getRequestURI(),
                    params, request.getRemoteHost(), end - start);
        }
    }

    @AfterThrowing (pointcut = "execution(* my.examples.pouch.controller.api.*.*(..))", throwing = "ex")
    public void logAfterThrowingAllMethods(Exception ex) throws Throwable
    {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.error("Exception Occured at {} with {}", request.getRequestURI(), ex);

        sm.setUsername(environment.getProperty("slackbot.userName"));
        sm.setChannel(environment.getProperty("slackbot.channel"));
        sm.setText("Exception 발생, url={"+request.getRequestURL()+"}, errorMessage={"+ex+"}");
        api.call(sm);
    }
}
