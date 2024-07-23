package com.example.bank.filter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private static final long MAX_REQUESTS_PER_MINUTE = 5;
    private final Map<String, UserRequestInfo> requestCounts = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        String clientIp = request.getRemoteAddr();
        long currentTime = System.currentTimeMillis();

        UserRequestInfo userRequestInfo = requestCounts.getOrDefault(clientIp, new UserRequestInfo(0, currentTime));

        if (currentTime - userRequestInfo.timestamp > TimeUnit.MINUTES.toMillis(1)) {
            userRequestInfo = new UserRequestInfo(0, currentTime);
        }

        if (userRequestInfo.requestCount >= MAX_REQUESTS_PER_MINUTE) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too many requests - try again later");
            return;
        }

        userRequestInfo.requestCount++;
        requestCounts.put(clientIp, userRequestInfo);

        filterChain.doFilter(request, response);
    }

    private static class UserRequestInfo {
        long requestCount;
        long timestamp;

        UserRequestInfo(long requestCount, long timestamp) {
            this.requestCount = requestCount;
            this.timestamp = timestamp;
        }
    }
}
