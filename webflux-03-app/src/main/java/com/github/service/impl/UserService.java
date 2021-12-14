package com.github.service.impl;

import com.github.pojo.User;
import com.github.service.IUserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: Uservice
 * @description: TODO(说明该类的作用, 写完移除TODO关键字)
 * @author: Hanson
 * @version: V1.0
 * @create: 2021/12/15 0:01
 **/
@Service
public class UserService implements IUserService {

    //创建 map 集合存储数据
    private static final Map<Integer, User> users = new HashMap<>();

    static {
        users.put(1, new User("lucy", "nan", 20));
        users.put(2, new User("mary", "nv", 30));
        users.put(3, new User("jack", "nv", 50));
    }


    @Override
    public Mono<User> getUserById(int id) {
        return Mono.justOrEmpty(users.get(id));

    }

    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(users.values());
    }

    @Override
    public Mono<Void> saveUserInfo(Mono<User> user) {
        return user.doOnNext(person -> {
            //向 map 集合里面放值
            int id = users.size() + 1;
            users.put(id, person);
        }).thenEmpty(Mono.empty());

    }
}
