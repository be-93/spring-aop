package cus.study.aop.internalcall;

import cus.study.aop.internalcall.aop.CallLogAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV2Test {

    @Autowired
    CallServiceV2 callServiceV2;

    @Test
    void externalApplicationContextTest() {
        callServiceV2.externalApplicationContext();
    }

    @Test
    void externalObjectProviderTest() {
        callServiceV2.externalObjectProvider();
    }
}