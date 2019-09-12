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

public class TopicViewer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Получаем идентификатор выбранной темы
        int topicId = Integer.parseInt(req.getParameter("topic_id"));

        //Получаем объекты доступа к данным
        RecordDAO recordDAO = DAOContainer.getRecordDAO();
        AccountDAO accountDAO = DAOContainer.getAccountDAO();
        TopicDAO topicDAO = DAOContainer.getTopicDAO();

        //Формируем заголовок
        Topic topic = topicDAO.getTopicById(topicId);
        PrintWriter out = resp.getWriter();
        out.print("<html>");
        out.print("<head><title>Simple Forum - " + topic.getHeaderText() + "</title></head>");
        out.print("<body>");

        //Получаем все сообщения выбранной темы и выводим их
        List<Record> recordList = recordDAO.getRecordsByTopicId(topicId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Account recordAccount;
        for (Record record : recordList) {
            out.print("<p style='margin: 15px'>");
            recordAccount = accountDAO.getAccountById(record.getUserId());
            out.print(recordAccount.getFirstName() + " " + recordAccount.getLastName() + "<br>");
            out.print(formatter.format(record.getDateAdded()) + "<br>");
            out.print(record.getContent());
            out.print("</p>");
        }
        out.print("<br>");

        //Проверяем, залогинился ли пользователь и если да, то получаем его аккаунт
        boolean isLogin = SessionUtilities.isLogin(req);
        if (isLogin){
            out.print("<form name='msg_form' method='post'>");
            out.print("<textarea cols=50 rows=5 name='content' wrap=virtual></textarea><br>");
            out.print("<input type=submit name='ok' value='Отправить'");
            out.print("</form>");
        }

        out.print("</body>");
        out.print("</html>");
    }

}
