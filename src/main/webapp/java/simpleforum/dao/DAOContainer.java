package simpleforum.dao;

import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DAOContainer implements ServletContextListener {

    private XmlWebApplicationContext springContext;

    private static AccountDAO accountDAO = null;
    private static TopicDAO topicDAO = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        springContext = new XmlWebApplicationContext();
        springContext.setConfigLocation("/WEB-INF/applicationContext.xml");
        springContext.setServletContext(sce.getServletContext());
        springContext.refresh();

        accountDAO = (AccountDAO) springContext.getBean("account_dao");
        topicDAO = (TopicDAO) springContext.getBean("topic_dao");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        springContext.close();
    }

    public static AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public static TopicDAO getTopicDAO() {
        return topicDAO;
    }

}
