package com.atguigu.gmall.item.cache.annotation;


import java.lang.annotation.*;

/**
 * date 2022/9/4 0:43
 * @message 缓存注解
 * @author 孙永刚
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface GmallCache {
    

}
