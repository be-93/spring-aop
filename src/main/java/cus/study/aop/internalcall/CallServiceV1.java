package cus.study.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    /**
     * 생성자 주입은 순환 사이클을 만들기 때문에 실패한다.
     */
    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        /**
         * AOP 적용 대상이 될려면 프록시 객체를 통해 메소드를 호출해야 하기 때문이다.
         * */
        callServiceV1.internal();
    }

    public void internal() {
        log.info("call internal");
    }
}
