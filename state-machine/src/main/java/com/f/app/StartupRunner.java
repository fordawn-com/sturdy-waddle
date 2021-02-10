package com.f.app;

import com.f.app.d.EventEnum;
import com.f.app.d.StateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class StartupRunner implements CommandLineRunner {

    @Resource
    StateMachine<StateEnum, EventEnum> stateMachine;

    @Override
    public void run(String... args) throws Exception {
        stateMachine.start();
        log.error("{}", stateMachine.getState());
        stateMachine.sendEvent(EventEnum.GO_STUDY);
        stateMachine.sendEvent(EventEnum.GO_BED);
        stateMachine.sendEvent(EventEnum.GO_STUDY);
//        stateMachine.sendEvent(EventEnum.Sleep);
    }
}