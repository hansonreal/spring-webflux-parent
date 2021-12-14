package com.github.service;

import com.github.pojo.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @className: IUserService
 * @description: TODO(说明该类的作用, 写完移除TODO关键字)
 * @author: Hanson
 * @version: V1.0
 * @create: 2021/12/15 0:00
 **/
public interface IUserService {
    //根据 id 查询用户
    Mono<User> getUserById(int id);

    //查询所有用户
    Flux<User> getAllUser();

    //添加用户
    Mono<Void> saveUserInfo(Mono<User> user);

}
