package test.aspect;

import org.aspectj.lang.annotation.*;

/**
 * 切面类
 *
 * @author chenhonghao
 * @date 2020-03-20 15:52
 */
@Aspect
public class TestAspect {

    @Pointcut("execution(* test.dto.Fox.*(..))")
    public void point(){

    }
    @Before("point()")
    public void before() {
        System.out.println("this is from HelloAspect#before...");
    }

    @After("point()")
    public void after() {
        System.out.println("this is from HelloAspect#after...");
    }

    @AfterReturning("point()")
    public void afterReturning() {
        System.out.println("this is from HelloAspect#afterReturning...");
    }

    @AfterThrowing("point()")
    public void afterThrowing() {
        System.out.println("this is from HelloAspect#afterThrowing...");
    }
}
