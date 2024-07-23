import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;

@Component
public class DataMaskingInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        Object responseBody = response.getOutputStream();

        if (Objects.nonNull(responseBody)) {
            maskSensitiveData(responseBody);
        }
    }

    private void maskSensitiveData(Object responseBody) throws IllegalAccessException, IOException {
        for (Field field : responseBody.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Mask.class)) {
                field.setAccessible(true);
                field.set(responseBody, maskValue(field.get(responseBody)));
            }
        }
        String maskedResponse = objectMapper.writeValueAsString(responseBody);
        // Write the masked response back to the output stream
        response.getOutputStream().write(maskedResponse.getBytes());
    }

    private String maskValue(Object value) {
        if (value instanceof String) {
            String str = (String) value;
            int length = str.length();
            if (length <= 4) {
                return "****";
            }
            return str.substring(0, 2) + "****" + str.substring(length - 2);
        }
        return "****";
    }
}
