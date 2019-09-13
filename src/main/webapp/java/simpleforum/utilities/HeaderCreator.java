package simpleforum.utilities;

import simpleforum.dao.Account;

import javax.servlet.http.HttpServletRequest;

public class HeaderCreator {

    public static String createHeader(HttpServletRequest req) {
        String header;
        if (SessionUtilities.isLogin(req)) {
            Account account = SessionUtilities.getEnteredUser(req);
            String fullName = account.getFirstName() + " " + account.getLastName();
            header = "<a href='index'>Simple Forum</a> - Добро пожаловать, " + fullName + " - <a href='logout'>Выйти</a>";
        } else {
            header = "<a href='index'>Simple Forum</a> - <a href='login'>Войти</a> - <a href='register'>Зарегистрироваться</a>";
        }
        return header;
    }

}
