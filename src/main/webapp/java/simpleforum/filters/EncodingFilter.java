package simpleforum.filters;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("Windows-1251");
        servletResponse.setCharacterEncoding("Windows-1251");
        servletResponse.setContentType("text/html");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
