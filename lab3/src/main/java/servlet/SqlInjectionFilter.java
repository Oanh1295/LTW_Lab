package servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class SqlInjectionFilter implements Filter {

    private static final String[] DANGEROUS_PATTERNS = {
        "' or '1'='1",
        "\" or \"1\"=\"1",
        "' or 1=1",
        "\" or 1=1",
        " union select ",
        "; drop table ",
        "--",
        "/*",
        "*/"
    };

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        // Bắt buộc đặt UTF-8 trước khi đọc bất kỳ parameter nào
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        if (hasDangerousParameter(httpRequest)) {
            httpResponse.getWriter().println(
                    "<h3>Yêu cầu không hợp lệ.</h3>"
            );
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean hasDangerousParameter(
            HttpServletRequest request
    ) {
        for (String parameterName
                : request.getParameterMap().keySet()) {

            // action có các giá trị hợp lệ: add, update, delete
            if ("action".equalsIgnoreCase(parameterName)) {
                continue;
            }

            String[] values =
                    request.getParameterValues(parameterName);

            if (values == null) {
                continue;
            }

            for (String value : values) {
                if (containsDangerousContent(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean containsDangerousContent(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }

        String normalized =
                " " + value.toLowerCase()
                        .replaceAll("\\s+", " ") + " ";

        for (String pattern : DANGEROUS_PATTERNS) {
            if (normalized.contains(pattern)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void destroy() {
    }
}