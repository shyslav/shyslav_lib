package webframework.impls;


import com.sun.istack.internal.NotNull;
import siteentity.entity.RoleType;

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
    @NotNull
    String url() default "";
    @NotNull
    RoleType [] role();
    @NotNull
    String jspPath();
}
