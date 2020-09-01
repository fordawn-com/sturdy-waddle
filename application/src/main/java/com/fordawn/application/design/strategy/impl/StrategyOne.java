package com.fordawn.application.design.strategy.impl;

import com.fordawn.application.design.strategy.Strategy;
import org.springframework.stereotype.Component;

@Component
public class StrategyOne implements Strategy {
    @Override
    public String handle() {
        return "strategy one";
    }
}
