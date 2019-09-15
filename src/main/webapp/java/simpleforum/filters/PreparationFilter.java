package simpleforum.filters;

import javax.servlet.*;
import java.io.IOException;

public class PreparationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.getWriter().print("<html>");
        filterChain.doFilter(servletRequest, servletResponse);
        servletResponse.getWriter().print("</body></html>");
    }

}
