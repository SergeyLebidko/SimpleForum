package simpleforum;

import simpleforum.dao.Account;
import simpleforum.dao.DAOContainer;
import simpleforum.dao.TopicDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class TopicCreator extends HttpServlet {

    private String createTopicPage =
            "<html>" +
                    "<head><title>Simple Forum - Новая тема</title></head>" +
                    "<body>" +
                    "<h3>Новая тема</h3>" +
                    "<form method='post'>" +
                    "<input type='text' name='topic_name' size=50 maxlength=256>" +
                    "<br><br>" +
                    "<input type='submit' name='ok' value='Создать'>" +
                    "</form>" +
                    "</body>" +
                    "</html>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("Windows-1251");
        resp.setContentType("text/html");

        //Сперва проверяем, залогинился ли пользователь. И если нет, то переходим на главную страницу
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("index");
            return;
        }

        //Из сессии получаем пытаемся получить аккаунт пользователя
        Account account = (Account) session.getAttribute("login_user");

        //Выводим страничку создания темы
        PrintWriter out = resp.getWriter();
        out.print(createTopicPage);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("Windows-1251");

        //Из сессии получаем пытаемся получить аккаунт пользователя
        HttpSession session = req.getSession(false);
        Account account = (Account) session.getAttribute("login_user");

        int userId = account.getId();
        LocalDateTime dateAdded = LocalDateTime.now();
        String headerText = req.getParameter("topic_name");

        //Проверяем, заполнено ли поле с названием темы
        if (headerText.equals("")){
            resp.sendRedirect("create_topic");
            return;
        }

        //Записываем данные в базу данных
        TopicDAO topicDAO = DAOContainer.getTopicDAO();
        topicDAO.createTopic(userId, dateAdded, headerText);

        //Переходим на главную страницу
        resp.sendRedirect("index");
    }

}
