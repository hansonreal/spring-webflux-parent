package com.github;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @className: ReactorExample
 * @description: Reactor 演示Mono和Flux
 * @author: Hanson
 * @version: V1.0
 * @create: 2021/12/7 23:26
 **/
public class ReactorExample {

    public static void main(String[] args) {
        // 直接声明
        Flux.just(1, 2, 4, 5);// flux可以一次性处理N个
        Mono.just(3);//Mono一次只可以至多只可以处理1个

        // 其他API
        Integer[] integers = new Integer[3];
        integers[0] = 1;
        integers[1] = 2;
        integers[2] = 3;
        Flux.fromArray(integers);//API-1

        List<Integer> integerList = Arrays.asList(integers);
        Flux.fromIterable(integerList);//API-2

        Stream<Integer> integerStream = integerList.stream();
        Flux.fromStream(integerStream);//API-3



        Flux.fromArray(integers).subscribe(System.out::println);

        Mono.just(3).subscribe(System.out::println);
    }

}
