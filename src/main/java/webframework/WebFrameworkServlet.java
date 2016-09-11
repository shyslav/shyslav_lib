package webframework;

import siteentity.storage.UserStorage;
import webframework.impls.UserVariables;

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
        setNewUser(req, resp);
        if (!bannedUserCheck(req, resp)) {
            try {
                WebFrameworkAction.findController(req, resp);
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setNewUser(req, resp);
        if (!bannedUserCheck(req, resp)) {
            try {
                WebFrameworkAction.findController(req, resp);
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();

            }
        }
    }

    /**
     * Check if user banned
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private boolean bannedUserCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserStorage storage = (UserStorage) request.getSession().getAttribute("userstorage");
        if (storage.getAmountLogin() >= UserVariables.AMOUN_WRONK_PASSWORD_ATTEMPTS) {
            response.sendError(302);
            return true;
        }
        return false;
    }

    /**
     * Set new user to session
     * @param req - servlet request
     * @param resp - servlet response
     */
    private void setNewUser(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getSession().getAttribute("userstorage") == null) {
            String ipAddress = req.getRemoteAddr();
            UserStorage userStorage = new UserStorage(ipAddress);
            req.getSession().setAttribute("userstorage", userStorage);
        }
    }
}
