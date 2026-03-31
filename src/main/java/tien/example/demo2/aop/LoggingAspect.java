package tien.example.demo2.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut("execution(* tien.example.demo2.service.*.save(..))")
    public void saveItemPointCut() {}

    @Pointcut("execution(* tien.example.demo2.service.*.testTransactionCase_1(..))")
    public void testTransactionCase_1() {}

    @Pointcut("execution(* tien.example.demo2.service.*.testTransactionCase_2(..))")
    public void testTransactionCase_2() {}

    @Before("saveItemPointCut()")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        try {
            String params = objectMapper.writeValueAsString(args);
            log.info("[Before] Method: {} — Params: {}",
                    joinPoint.getSignature().toShortString(),
                    params);
        } catch (Exception e) {
            log.warn("Không convert được args!");
        }
    }

    @Before("testTransactionCase_1()")
    public void logBefore1(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        try {
            String params = objectMapper.writeValueAsString(args);
            log.info("[Before] Method: {} — Params: {}",
                    joinPoint.getSignature().toShortString(),
                    params);
        } catch (Exception e) {
            log.warn("Không convert được args!");
        }
    }

    @Before("testTransactionCase_2()")
    public void logBefore2(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        try {
            String params = objectMapper.writeValueAsString(args);
            log.info("[Before] Method: {} — Params: {}",
                    joinPoint.getSignature().toShortString(),
                    params);
        } catch (Exception e) {
            log.warn("Không convert được args!");
        }
    }

    @AfterReturning(value = "saveItemPointCut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        try {
            // giả sử result là User
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            if (result != null) {
                String json = objectMapper.writeValueAsString(result);
                log.info("[AfterReturning] Method: {} — Result: {}",
                        methodSignature.toShortString(),
                        json);
            }
        } catch (Exception e) {
            log.warn("Không log được object result!");
        }
    }

    @AfterReturning(value = "testTransactionCase_1()", returning = "result")
    public void logAfterReturning1(JoinPoint joinPoint, Object result) {
        try {
            // giả sử result là User
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            if (result != null) {
                String json = objectMapper.writeValueAsString(result);
                log.info("[AfterReturning] Method: {} — Result: {}",
                        methodSignature.toShortString(),
                        json);
            }
        } catch (Exception e) {
            log.warn("Không log được object result!");
        }
    }

    @AfterReturning(value = "testTransactionCase_2()", returning = "result")
    public void logAfterReturning2(JoinPoint joinPoint, Object result) {
        try {
            // giả sử result là User
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            if (result != null) {
                String json = objectMapper.writeValueAsString(result);
                log.info("[AfterReturning] Method: {} — Result: {}",
                        methodSignature.toShortString(),
                        json);
            }
        } catch (Exception e) {
            log.warn("Không log được object result!");
        }
    }

    @AfterThrowing(value = "saveItemPointCut()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("[Exception] Method: {} — Lỗi: {}",
                joinPoint.getSignature().toShortString(),
                ex.getMessage(), ex);
    }

    @AfterThrowing(value = "testTransactionCase_1()", throwing = "ex")
    public void logAfterThrowing1(JoinPoint joinPoint, Throwable ex) {
        log.error("[Exception] Method: {} — Lỗi: {}",
                joinPoint.getSignature().toShortString(),
                ex.getMessage(), ex);
    }

    @AfterThrowing(value = "testTransactionCase_2()", throwing = "ex")
    public void logAfterThrowing2(JoinPoint joinPoint, Throwable ex) {
        log.error("[Exception] Method: {} — Lỗi: {}",
                joinPoint.getSignature().toShortString(),
                ex.getMessage(), ex);
    }
}
