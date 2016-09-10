package webframework.impls;


import webframework.entity.RoleType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by shyslav on 9/10/16.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebMethodFramework {
    String url() default "";
    RoleType role() default RoleType.USER;
}
