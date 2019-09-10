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
        if (session != null) {
            account = (Account) session.getAttribute("login_user");
        }

        PrintWriter out = resp.getWriter();

        out.print("<h3>Главная страница</h3>");
        if (account != null) {
            out.print("<br>Вошёл: " + account.getUsername());
        }
    }

}
