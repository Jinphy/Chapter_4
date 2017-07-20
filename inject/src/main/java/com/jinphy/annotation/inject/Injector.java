package com.jinphy.annotation.inject;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 注入器
 * 自动注入View和View对象的ViewOnClickListener
 * 所以开发者不用自己手动调用findViewById()和setOnClickListener()
 * @see InjectView
 * @see OnClick
 * Created by jinphy on 2017/7/20.
 */

public class Injector {

    //------------------------------------------------------------------------------------/
    //---------------------- public method -----------------------------------------------/

    /**
     * 在activity中注入View，从而取代所有的findViewById()方法
     * 和为所有的需要设置OnClickListener的View注入该监听器，从而取代
     * setOnClickListener()方法
     * @see Injector#injectViews(Activity)
     * @see Injector#injectListeners(Activity)
     * @param activity 将要进行注入的activity对象
     * */
    public static void inject(Activity activity) {
        // 注入View 来代替在代码中使用findViewById()
        Injector.injectViews(activity);

        // 注入View.OnClickListener 来代替setOnClickListener()
        Injector.injectListeners(activity);
    }

    /**
     * 根据传进来的Activity对象，将该对象的所有使用InjectView注解的字段
     * 都根据注解的id进行注入
     * @param activity 将要使用注解注入相应字段的Activity对象
     * @see Injector#inject(Activity)
     *
     * */
    public static void injectViews(Activity activity){
        // activity的类对象
        Class activityClass = activity.getClass();
        // 获取该累的所有字段
        Field[] fields = activityClass.getDeclaredFields();
        for (Field field : fields) {
            // 获取对应字段的InjectView注解对象
            InjectView annotation = field.getAnnotation(InjectView.class);
            // 筛选所有使用InjectView注解的字段
            if (annotation!=null) {
                // 注入该字段
                injectView(activity,field,annotation);
            }
        }
    }

    /**
     *
     * 根据传进来的activity对象，对该对象上的所有加了OnClick注解的方法
     * 进行注入，在每个这样的方法中，为该注解中所有的id所指向的View对象
     * 都注册该方法对应的监听器
     *
     * @param activity 持有这些注解方法的activity对象
     * @see Injector#inject(Activity)
     * */
    public static void injectListeners(Activity activity) {
        // activity对象对应的类对象
        Class activityClass = activity.getClass();
        // getDeclaredMethods获取的方法是本类声明的方法，不包括基类的方法
        Method[] methods = activityClass.getDeclaredMethods();
        // 遍历每个方法
        for (Method method : methods) {
            // 获取该方法上的OnClick注解对象
            OnClick annotation = method.getAnnotation(OnClick.class);
            if (annotation !=null){
                // 如果该方法加了OnClick注解，则为该方法注解中对应的View注册监听器
                injectListener(activity, method,annotation);
            }
        }
    }



    //----------------------////////////////-----------------------------------------------/
    //---------------------- private method -----------------------------------------------/

    // 注入单个加了InjectView注解的字段
    private static void injectView(Activity activity,Field field, InjectView annotation) {

        // 获取该注解对象对应的id 字段的值
        int id = annotation.id();
        // 私有字段必须设置该方法为true才能进行接下来的访问或赋值
        field.setAccessible(true);
        try {
            // 将findViewById得到的对象赋值给对应的field
            field.set(activity,activity.findViewById(id));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /*
    * 注解单个方法，为该方法中的OnClick注解中的所有id对应的View对象
    * 设置监听器，监听器的处理逻辑就是该方法的逻辑
    * */
    private static void injectListener(Activity activity, Method method,OnClick annotation) {
        // 创建该方法对应的监听器
        View.OnClickListener listener = view -> invoke(method,activity,view);

        // 获取该方法OnClick注解对象中的所有id
        int[] ids = annotation.ids();
        // 遍历每个id，为每个id对应的View对象设置监听器
        for (int id : ids) {
            activity.findViewById(id).setOnClickListener(listener);
        }
    }

    // 调用方法 该函数是为了使用lambda表达式是简洁些写的，利用反射的方式实现方法调用
    private static void invoke(Method method,Object invoker,View view) {

        try {
            method.invoke(invoker,view);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
