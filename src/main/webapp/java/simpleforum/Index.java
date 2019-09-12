package simpleforum;

import simpleforum.dao.*;
import simpleforum.utilities.SessionUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Index extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = SessionUtilities.getEnteredUser(req);
        boolean isLogin = SessionUtilities.isLogin(req);

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

        //Выводим список тем форума
        TopicDAO topicDAO = DAOContainer.getTopicDAO();
        AccountDAO accountDAO = DAOContainer.getAccountDAO();

        List<Topic> topicList = topicDAO.getAllTopics();
        Account topicAccount;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        out.print("<br><h3>Темы форума</h3>");
        for (Topic topic : topicList) {
            topicAccount = accountDAO.getAccountById(topic.getUserId());
            out.print("<p style='margin: 15px'>");
            out.print(topicAccount.getFirstName() + " " + topicAccount.getLastName() + "<br>");
            out.print(formatter.format(topic.getDateAdded()) + "<br>");
            out.print("<a href='view_topic?topic_id=" + topic.getId() + "' style='font-weight:bold'>" + topic.getHeaderText() + "</a>");
            out.print("</p>");
        }

        //Если пользователь залогинился, то он может добавлять новые темы
        if (isLogin) {
            out.print("<a href='create_topic'>Добавить тему</a>");
        }

        out.print("</body>");
        out.print("</html>");
    }

}
