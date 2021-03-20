package com.f.app.config;

import com.f.app.d.EventEnum;
import com.f.app.d.StateEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author ins
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<StateEnum, EventEnum> {

    @Override
    public void configure(StateMachineStateConfigurer<StateEnum, EventEnum> states) throws Exception {
        states.withStates().initial(StateEnum.IDLE).states(EnumSet.allOf(StateEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<StateEnum, EventEnum> transitions) throws Exception {
        transitions.withExternal()
                .source(StateEnum.IDLE).target(StateEnum.WORK)
                .event(EventEnum.GO_WORK)
                .and()
                .withExternal()
                .source(StateEnum.IDLE).target(StateEnum.STUDY)
                .event(EventEnum.GO_STUDY)
                .and()
                .withExternal()
                .source(StateEnum.WORK).target(StateEnum.SLEEP)
                .event(EventEnum.GO_STUDY)
                .and()
                .withExternal()
                .source(StateEnum.WORK).target(StateEnum.SLEEP)
                .event(EventEnum.GO_BED);
    }
}
