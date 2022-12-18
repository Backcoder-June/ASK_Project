package ask.Member;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class DomainFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 실패로직 핸들링

        exception.printStackTrace();

        Map<String, Object> loginFailMap = writePrintErrorResponse(response, exception);
        request.setAttribute("loginFailMap", loginFailMap);
    }



    private Map<String, Object> writePrintErrorResponse(HttpServletResponse response, AuthenticationException exception) {
        Map<String, Object> responseMap = new HashMap<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String message = getExceptionMessage(exception);

            responseMap.put("status", 401);
            responseMap.put("message", message);

            response.getOutputStream().println(objectMapper.writeValueAsString(responseMap));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseMap;
    }

    private String getExceptionMessage(AuthenticationException exception) {
        if (exception instanceof BadCredentialsException) {
            return "비밀번호불일치";
        } else if (exception instanceof UsernameNotFoundException) {
            return "계정없음";
        } else {
            return "잘못된 로그인 시도";
        }
    }

}
