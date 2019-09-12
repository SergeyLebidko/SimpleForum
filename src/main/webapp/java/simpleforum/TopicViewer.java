package simpleforum;

import simpleforum.utilities.SessionUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TopicViewer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Если пользватель не залогинился, то перенаправляем его на главную страницу
        if (SessionUtilities.redirectIfNotLogin(req, resp, "index")){
            return;
        }

        PrintWriter out = resp.getWriter();
        out.print("Просмотр сообщений по теме");
    }

}
