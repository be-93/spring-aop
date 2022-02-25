package cus.study.aop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 내부 메소드를 분리 함으로써 호출 방식 변경
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV3 {
    private final InternalService internalService;

    public void external() {
        log.info("call external");
        internalService.internal();
    }
}
