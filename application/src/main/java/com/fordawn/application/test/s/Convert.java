package com.fordawn.application.test.s;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Convert {
    public static List<B> convert(List<A> input) {
        return input.stream()
                .filter(Objects::nonNull)
                .map(a -> {
                    B b = new B();
                    BeanUtils.copyProperties(a, b);
                    return b;
                })
                .collect(Collectors.toList());
    }
}
