package com.f.app.b;

import com.f.app.d.StateEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @author ins
 */
@WithStateMachine
@Data
@Slf4j
public class BizBean {

    private StateEnum status = StateEnum.IDLE;

    @OnTransition(target = "STUDY")
    public void online() {
        log.warn("去学习. target status:{}", StateEnum.STUDY.name());
        setStatus(StateEnum.STUDY);
    }

    @OnTransition(target = "WORK")
    public void work() {
        log.info("去工作. target status:{}", StateEnum.WORK.name());
        setStatus(StateEnum.WORK);
    }

//    @OnTransition(target = "Alive")
//    public void sleep() {
//        log.info("操作回滚,回到草稿状态. target status:{}", StateEnum.Alive.name());
//        setStatus(StateEnum.Alive.name());
//    }

}
