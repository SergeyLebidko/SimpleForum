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

public class Register extends HttpServlet {

    private String registerPage =
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
                    "</form>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Если пользователь уже зарегистрирован и залогинился, то сразу же переводим его на главную страницу
        if (SessionUtilities.redirectIfLogin(req, resp, "index")){
            return;
        }

        PrintWriter out = resp.getWriter();
        out.print(registerPage);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //Не должно быть не заполненных полей
        if (firstName.equals("") || lastName.equals("") || username.equals("") || password.equals("")) {
            resp.sendRedirect("register");
            return;
        }

        //Если такой пользователь уже существует, то снова выводим страницу регистрации
        AccountDAO accountDAO = DAOContainer.getAccountDAO();
        Account account = accountDAO.getAccountByUsername(username);
        if (account != null) {
            resp.sendRedirect("register");
            return;
        }

        //Если все проверки прошли успешно, то создаем пользователя и переводим его на главную страницу
        accountDAO.createAccount(firstName, lastName, username, password);
        account = accountDAO.getAccountByUsername(username);

        HttpSession session = req.getSession(true);
        session.setAttribute("login_user", account);

        resp.sendRedirect("index");
    }

}
