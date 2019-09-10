package simpleforum;

import simpleforum.dao.Account;
import simpleforum.dao.AccountDAO;
import simpleforum.dao.DAOContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends HttpServlet {

    private String loginPage =
            "<html>" +
                    "<head><title>Simple Forum - Вход</title></head>" +
                    "<body>" +
                    "<h3>Вход</h3>" +
                    "<form method='post'>" +
                    "<table>" +
                    "<tr><td>Имя: </td><td><input type='text' name='username' size=20 maxlength=50></td></tr>" +
                    "<tr><td>Пароль: </td><td><input type='password' name='password' size=20 maxlength=50></td></tr>" +
                    "</table>" +
                    "<br>" +
                    "<input type='submit' name='ok' value='Войти'> или <a href='register'>зарегистрироваться</a>" +
                    "</form>" +
                    "</body>" +
                    "</html>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("Windows-1251");
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.print(loginPage);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("Windows-1251");
        resp.setCharacterEncoding("Windows-1251");
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        AccountDAO accountDAO = DAOContainer.getAccountDAO(req);
        Account account = accountDAO.getAccountByUsername(username);

        if (account == null || !password.equals(account.getPassword())) {
            resp.sendRedirect("login");
            return;
        }
        resp.sendRedirect("index");
    }

}