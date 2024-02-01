package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time/*")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String reqTimezone = req.getParameter("timezone");
        ZoneId timeZone;
        if (reqTimezone != null) {
            timeZone = ZoneId.of(reqTimezone);
        } else {
            timeZone = ZoneId.of("UTC");
        }

        Instant now = Instant.now();
        ZonedDateTime zdt = ZonedDateTime.ofInstant(now, timeZone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        String formattedTime = zdt.format(formatter);
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write(formattedTime);
    }

}
