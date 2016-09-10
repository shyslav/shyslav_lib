package webframework;

import siteentity.storage.UserStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by shyslav on 9/10/16.
 */
public class WebFrameworkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setNewUser(req,resp);
        try {
            WebFrameworkAction.findController(req, resp);
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setNewUser(req,resp);
        try {
            WebFrameworkAction.findController(req, resp);
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
    protected void setNewUser(HttpServletRequest req, HttpServletResponse resp){
        if(req.getSession().getAttribute("userstorage")==null){
            String ipAddress = req.getRemoteAddr();
            UserStorage userStorage = new UserStorage(ipAddress,0);
            req.getSession().setAttribute("userstorage",userStorage);
        }
    }
}
