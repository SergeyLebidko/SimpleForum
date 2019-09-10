package simpleforum.dao;

import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.http.HttpServletRequest;

public class DAOContainer {

    private static AccountDAO accountDAO = null;

    private static void load(HttpServletRequest req) {
        XmlWebApplicationContext springContext = new XmlWebApplicationContext();
        springContext.setConfigLocation("/WEB-INF/applicationContext.xml");
        springContext.setServletContext(req.getServletContext());
        springContext.refresh();

        accountDAO = (AccountDAO) springContext.getBean("account_dao");
    }

    public static AccountDAO getAccountDAO(HttpServletRequest req) {
        if (accountDAO == null) {
            load(req);
        }
        return accountDAO;
    }

}
