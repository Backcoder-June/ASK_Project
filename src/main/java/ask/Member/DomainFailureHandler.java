package ask.Member;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class DomainFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 실패로직 핸들링
        Map<String, Object> loginFailMap = writePrintErrorResponse(response, exception);
        request.setAttribute("loginFailMap", loginFailMap);

        String errorMessage = (String)loginFailMap.get("message");

        errorMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8); /* 한글 인코딩 깨진 문제 방지 */
        setDefaultFailureUrl("/login?error=true&exception=" + errorMessage);
        super.onAuthenticationFailure(request, response, exception);
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
        }
        return responseMap;
    }

    private String getExceptionMessage(AuthenticationException exception) {
        if (exception instanceof BadCredentialsException) {
            return "아이디 또는 비밀번호가 일치하지 않습니다.";
        } else if (exception instanceof UsernameNotFoundException) {
            return "계정정보가 없습니다.";
        } else {
            return "잘못된 로그인 시도입니다. 아이디를 확인해 주세요.";
        }
    }

}
