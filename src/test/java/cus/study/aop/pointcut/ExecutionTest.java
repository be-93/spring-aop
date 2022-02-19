package cus.study.aop.pointcut;

import cus.study.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ExecutionTest {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {
        //public java.lang.String cus.study.aop.member.MemberServiceImpl.hello(java.lang.String)
        log.info("helloMethod={}", helloMethod);
    }

    @Test
    public void exactMatch(){
        // public java.lang.String cus.study.aop.member.MemberServiceImpl.hello(java.lang.String)
        pointcut.setExpression("execution(public java.lang.String cus.study.aop.member.MemberServiceImpl.hello(java.lang.String))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void allMatch(){
        pointcut.setExpression("execution(* *(..))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void nameMatch(){
        pointcut.setExpression("execution(* hello(..))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void patternMatch(){
        pointcut.setExpression("execution(* hel*(..))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void patternMatch2(){
        pointcut.setExpression("execution(* *el*(..))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void packageExactMatch(){
        pointcut.setExpression("execution(* cus.study.aop.member.MemberServiceImpl.hello(..))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void packageExactMatch2(){
        pointcut.setExpression("execution(* cus.study.aop.member.*.*(..))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void packageExactMatch3(){
        pointcut.setExpression("execution(* cus.study.aop.member..*.*(..))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void typeExactMatch(){
        pointcut.setExpression("execution(* cus.study.aop.member.MemberServiceImpl.*(..))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void superTypeMatch(){
        pointcut.setExpression("execution(* cus.study.aop.member.MemberService.*(..))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void typeMatchInternal() throws NoSuchMethodException {
        pointcut.setExpression("execution(* cus.study.aop.member.MemberServiceImpl.*(..))");

        Method internal = MemberServiceImpl.class.getMethod("internal", String.class);

        assertThat(pointcut.matches(internal, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void typeMatchNoSuperTypeMethodFalse() throws NoSuchMethodException {
        pointcut.setExpression("execution(* cus.study.aop.member.MemberService.*(..))");

        Method internal = MemberServiceImpl.class.getMethod("internal", String.class);

        assertThat(pointcut.matches(internal, MemberServiceImpl.class)).isFalse();
    }

    @Test
    public void argsMatch(){
        pointcut.setExpression("execution(* *(String))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void argsMatchNoArgs(){
        pointcut.setExpression("execution(* *())");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    public void argsMatchOnlyOneArgs(){
        pointcut.setExpression("execution(* *(*))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void argsMatchAllArgs(){
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    public void argsMatchComplexArgs(){
        pointcut.setExpression("execution(* *(String, ..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
}
