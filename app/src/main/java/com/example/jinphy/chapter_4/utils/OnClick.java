package com.example.jinphy.chapter_4.utils;

import android.support.annotation.IdRes;
import android.support.annotation.IntRange;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * View对象的监听器注入注解，
 * 用来对android中的View对象进行自动注入View.OnClickListener，
 * 从而避免的手动调用每个View对象的setOnClickListener()方法的繁琐操作
 * @see com.example.jinphy.chapter_4.utils.ViewUtils
 *
 * Created by jinphy on 2017/7/20.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OnClick {

    @IntRange(from = 1) @IdRes
    int[] ids();
}
