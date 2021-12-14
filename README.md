# spring-webflux-parent
## 1.1、Spring Webflux 介绍
1. 是Spring5 添加新的模块，用于web开发，功能和SpringMVC类似，WebFlux本身追随当下最火的Reactive Programming而诞生的框架。

2. 使用传统的web框架，比如SpringMVC,Struts等这些机遇Servlet容器，Webflux是一种异步非阻塞的框架，异步非阻塞的框架在Servlet3.1以后才支持，核心是基于Reactor的相关API实现的。

3. 什么是异步非阻塞？

    1. 同步和异步
        1. 同步和异步针对的是调用者，调用者发送请求，如果等着对方回应之后才去做其他事情就是同步，如果发送请求之后不等着对方回应就去做其他事情就是异步
    2. 阻塞和非阻塞
        1. 被调用者收到请求之后，昨晚请求任务之后才给出反馈就是阻塞，收到请求之后立马给出反馈然后再去做事情就是非阻塞

4. Webflux特点

    1. 非阻塞式：在有限资源下，提高系统吞吐量和伸缩性，以Reactor未基础实现响应式编程
    2. 函数式编程：Spring5框架基于Java8，Webflux使用Java8函数式编程方式现实路由请求

5. 与比较SpringMVC

    ![SpringMVC vs Spring Webflux](https://docs.spring.io/spring-framework/docs/current/reference/html/images/spring-mvc-and-webflux-venn.png)

    它们都可以用注解式编程模型，都可以运行在tomcat，jetty，undertow等servlet容器当中。但是SpringMVC采用命令式编程方式，代码一句一句的执行，这样更有利于理解与调试，而WebFlux则是基于异步响应式编程，对于初次接触的码农们来说会不习惯。对于这两种框架官方给出的建议是：

    1. 如果原先使用用SpringMVC好好的话，则没必要迁移。因为命令式编程是编写、理解和调试代码的最简单方法。因为老项目的类库与代码都是基于阻塞式的。
    2. 如果你的团队打算使用非阻塞式web框架，WebFlux确实是一个可考虑的技术路线，而且它支持类似于SpringMvc的Annotation的方式实现编程模式，也可以在微服务架构中让WebMvc与WebFlux共用Controller，切换使用的成本相当小
    3. 在SpringMVC项目里如果需要调用远程服务的话，你不妨考虑一下使用WebClient，而且方法的返回值可以考虑使用Reactive Type类型的，当每个调用的延迟时间越长，或者调用之间的相互依赖程度越高，其好处就越大

## 1.2、响应式编程（Java实现）

### 1.2.1、什么是响应式编程

是一种基于**数据流**（data stream）和**变化传递**（propagation of change）的**声明式**（declarative）的编程范式。



### 1.2.2、Java8 及其之前的版本

```java
import java.util.Observable;

public class ObserverDemo extends Observable {
    public static void main(String[] args) {
        ObserverDemo observer = new ObserverDemo();
        //添加观察者
        observer.addObserver((o,arg)->{
            System.out.println("发生变化");
        });
        observer.addObserver((o,arg)->{
            System.out.println("手动被观察者通知，准备改变");
        });
        observer.setChanged(); //数据变化
        observer.notifyObservers(); //通知
    }
}
```
## 1.3、响应式编程（Reactor实现）
- 1)、响应式编程操作中，Reactor是满足Reactive规范框架
- 2)、Reactor有连个核心类，Mono和Flux，这两个类实现接口Publisher，提供丰富操作符。Flux对象实现发布者，返回N个元素；
Mono实现发布者，返回0或者1个元素
- 3)、Flux和Mono都是数据流发布者，使用Flux和Mono都可以发出三种数据信号：元素值，错误信号，完成信号；
错误信号和完成信号都代表终止信号，终止信号用于告诉订阅者数据流结束了，错误信号终止数据流同时把错误信息传递给订阅者。
- 4)、三种信号特点
      * 错误信号和完成信号都是终止信号，不能共存的
      * 如果没有发送任何元素值，而是直接发送错误或者完成信号，表示是空数据流
      * 如果没有错误信号，没有完成信号，表示是无限数据流
- 5)、调用 just 或者其他方法只是声明数据流，数据流并没有发出，只有进行订阅之后才会触发数据流，不订阅什么都不会发生的
    ```java
     Flux.fromArray(integers).subscribe(System.out::println);
     Mono.just(3).subscribe(System.out::println);
    ```
- 6)、操作符
    - 对数据流进行一道道操作，称为操作符，比如工厂流水线
        - 01)、map操作符 将元素映射为新元素
        - 02)、flatMap操作符 将元素映射为流，把每个元素转换流，把转换之后多个流合并大的流
