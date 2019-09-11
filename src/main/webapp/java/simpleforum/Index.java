package simpleforum;

import simpleforum.dao.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Index extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("Windows-1251");
        resp.setContentType("text/html");

        HttpSession session = req.getSession(false);
        Account account = null;
        boolean isLogin = false;
        if (session != null) {
            account = (Account) session.getAttribute("login_user");
            isLogin = true;
        }

        PrintWriter out = resp.getWriter();
        out.print("<html>");
        out.print("<head><title>Simple Forum - Главная</title></head>");
        out.print("<body>");

        //Формируем заголовок страницы в зависимости от того, залогинился пользователь или нет
        if (isLogin) {
            String fullName = account.getFirstName() + " " + account.getLastName();
            out.print("Simple Forum - Добро пожаловать, " + fullName + " - <a href='logout'>Выйти</a>");
        } else {
            out.print("Simple Forum - <a href='login'>Войти</a> - <a href='register'>Зарегистрироваться</a>");
        }

        out.print("</body>");
        out.print("</html>");
    }

}
