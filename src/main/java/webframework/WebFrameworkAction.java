package webframework;

import webframework.impls.WebMethodFramework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by shyslav on 9/10/16.
 */
public class WebFrameworkAction {
    public static void anotate(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class cl = Class.forName("com.shyslav.controller.HomeController");
        if (!cl.isAnnotationPresent(WebMethodFramework.class)) {
            System.err.println("no annotation");
        } else {
            System.out.println("class annotated ; name  -  " + cl.getAnnotation(WebMethodFramework.class));
        }
        Method[] method = cl.getMethods();
        for (Method md : method) {
            if (md.isAnnotationPresent(WebMethodFramework.class)) {
                WebMethodFramework ta = md.getAnnotation(WebMethodFramework.class);
                System.out.println(ta.role());
                md.invoke(cl.newInstance(), req, resp);
            }
        }
    }
}
