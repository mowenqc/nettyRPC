package com.mowen.common.annatation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * desc  : com.mowen.common.annatation
 * author: mowen
 * create_time: 2019/6/11 14:00
 * project_name : nettyRPC_parent
 */
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

    String value = "";
}
