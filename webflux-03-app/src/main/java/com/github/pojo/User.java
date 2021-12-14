package com.github.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: User
 * @description: TODO(说明该类的作用, 写完移除TODO关键字)
 * @author: Hanson
 * @version: V1.0
 * @create: 2021/12/14 23:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;

    private String gender;

    private Integer age;
}
