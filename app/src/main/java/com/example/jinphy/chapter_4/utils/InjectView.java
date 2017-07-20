package com.example.jinphy.chapter_4.utils;

import android.support.annotation.IdRes;
import android.support.annotation.IntRange;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * View对象的自动注入注解，
 * 一个View对象字段加了该注解后，可以实现自动注入，从而避免了
 * 为每个View对象调用findView() 方法的繁琐
 * @see com.example.jinphy.chapter_4.utils.ViewUtils
 * Created by jinphy on 2017/7/20.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectView {
    @IdRes @IntRange(from = 1)
    int id();
}
