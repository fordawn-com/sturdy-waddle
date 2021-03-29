package com.fordawn.application.test.s;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MainTest {

    public static void main(String[] args) {

        List<A> source = new ArrayList<>();

        A a = new A();
        a.setA("1");
        a.setB("2");

        source.add(a);

        A a2 = new A();
        a2.setA("3");
        a2.setB("4");

        source.add(a2);

        List<B> convert = Convert.convert(source);
        log.info("{}", convert);

        List<A> source2 = new ArrayList<>();
        A a3 = new A();
        a3.setA("5");
        a3.setB("6");

        source2.add(a3);

        List<B> convert2 = Convert.convert(source2);
        log.info("{} {}", convert, convert2);
    }
}
