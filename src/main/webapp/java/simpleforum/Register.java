package simpleforum;

import simpleforum.dao.Account;
import simpleforum.dao.AccountDAO;
import simpleforum.dao.DAOContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Register extends HttpServlet {

    private String registerPage =
            "<html>" +
                    "<head><title>Simple Forum - Регистрация</title></head>" +
                    "<body>" +
                    "<h3>Регистрация</h3>" +
                    "<form method='post'>" +
                    "<table>" +
                    "<tr><td>Имя: </td><td><input type='text' name='first_name' size=20 maxlength=50></td></tr>" +
                    "<tr><td>Фамилия: </td><td><input type='text' name='last_name' size=20 maxlength=50></td></tr>" +
                    "<tr><td>Логин: </td><td><input type='text' name='username' size=20 maxlength=50></td></tr>" +
                    "<tr><td>Пароль: </td><td><input type='password' name='password' size=20 maxlength=50></td></tr>" +
                    "</table>" +
                    "<br>" +
                    "<input type='submit' name='ok' value='Зарегистрироваться'>" +
                    "</form>" +
                    "</body>" +
                    "</html>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("Windows-1251");
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.print(registerPage);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("Windows-1251");
        resp.setCharacterEncoding("Windows-1251");
        resp.setContentType("text/html");

        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (firstName.equals("") || lastName.equals("") || username.equals("") || password.equals("")) {
            resp.sendRedirect("register");
            return;
        }

        AccountDAO accountDAO = DAOContainer.getAccountDAO(req);
        Account account = accountDAO.getAccountByUsername(username);
        if (account != null) {
            resp.sendRedirect("register");
            return;
        }

        accountDAO.createAccount(firstName, lastName, username, password);
        account = accountDAO.getAccountByUsername(username);

        HttpSession session = req.getSession(true);
        session.setAttribute("login_user", account);

        resp.sendRedirect("index");
    }

}
