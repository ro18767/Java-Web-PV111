package step.learning.filters;

import com.google.inject.Singleton;
import javax.servlet.*;
import java.io.IOException;
@Singleton
public class EncodingFilter implements Filter {
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
