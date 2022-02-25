package cus.study.aop.internalcall;

import cus.study.aop.internalcall.aop.CallLogAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV0Test {

    @Autowired
    CallServiceV0 callServiceV0;

    @Test
    void external() {
        /**
         * external() 메소드 안에서 internal()를 호출하지만 internal 메소드는 aop 의 적용 대상이 아니다.
         * 이유는 external 안에서 자기 자신 내부의 메소드를 직접 호출하기 때문이다.
         * 즉, 프록시 객체를 호출하는게 아닌 실제 대상 객체를 호출 하는 동작 방식이기 때문이다.
         */
        callServiceV0.external();
    }

    @Test
    void internal() {
        callServiceV0.internal();
    }
}