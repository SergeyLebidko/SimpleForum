package simpleforum.utilities;

import simpleforum.dao.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionUtilities {

    //Метод возвращает аккаунт залогинившегося пользователя. Если такового нет, то возвращает null
    public static Account getEnteredUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) return null;
        return (Account) session.getAttribute("login_user");
    }

    //Метод возвращает true, если есть залогинившийся пользователь
    public static boolean isLogin(HttpServletRequest req) {
        if (getEnteredUser(req) != null) return true;
        return false;
    }

    //Метод делает редирект на указанный адрес, если есть залогинившийся пользователь
    public static boolean redirectIfLogin(HttpServletRequest req, HttpServletResponse resp, String redirectAddr) throws IOException {
        if (isLogin(req)){
            resp.sendRedirect(redirectAddr);
            return true;
        }
        return false;
    }

    //Метод делает редирект на указанный адрес, если нет залогинившегося пользователя
    public static boolean redirectIfNotLogin(HttpServletRequest req, HttpServletResponse resp, String redirectAddr) throws IOException {
        if (!isLogin(req)){
            resp.sendRedirect(redirectAddr);
            return true;
        }
        return false;
    }

}
