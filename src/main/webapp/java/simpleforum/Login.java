package simpleforum;

import simpleforum.dao.Account;
import simpleforum.dao.AccountDAO;
import simpleforum.dao.DAOContainer;
import simpleforum.utilities.SessionUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends HttpServlet {

    private String loginPage =
            "<head><title>Simple Forum - Вход</title></head>" +
                    "<body>" +
                    "<h3>Вход</h3>" +
                    "<form method='post'>" +
                    "<table>" +
                    "<tr><td>Логин: </td><td><input type='text' name='username' size=20 maxlength=50></td></tr>" +
                    "<tr><td>Пароль: </td><td><input type='password' name='password' size=20 maxlength=50></td></tr>" +
                    "</table>" +
                    "<br>" +
                    "<input type='submit' name='ok' value='Войти'> или <a href='register'>зарегистрироваться</a>" +
                    "</form>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Если пользователь уже залогинился, то сразу же переводим его на главную страницу
        if (SessionUtilities.redirectIfLogin(req, resp, "index")) {
            return;
        }

        //Выводим страничку входа
        PrintWriter out = resp.getWriter();
        out.print(loginPage);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //Ищем пользователя с введенным именем
        AccountDAO accountDAO = DAOContainer.getAccountDAO();
        Account account = accountDAO.getAccountByUsername(username);

        //Если такой пользователь не найден - снова выводим страничку ввода учетных данных
        if (account == null || !password.equals(account.getPassword())) {
            resp.sendRedirect("login");
            return;
        }

        //Если пользователь найден - переходим на главную страницу
        HttpSession session = req.getSession(true);
        session.setAttribute("login_user", account);
        resp.sendRedirect("index");
    }

}
