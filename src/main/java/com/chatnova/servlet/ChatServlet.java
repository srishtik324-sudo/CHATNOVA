
package com.chatnova.servlet;

import com.chatnova.service.ChatService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

@WebServlet(name = "chatServlet", urlPatterns = "/servlet/chat")
public class ChatServlet extends HttpServlet {

    private ChatService chatService;

    @Override
    public void init() {
        WebApplicationContext context =
                WebApplicationContextUtils.getRequiredWebApplicationContext(
                        getServletContext());

        this.chatService = context.getBean(ChatService.class);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        response.setContentType("text/plain;charset=UTF-8");

        String message = request.getParameter("message");

        if (message == null || message.isBlank()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Please enter a message.");
            return;
        }

        try {
            response.getWriter().write(chatService.getResponse(message));
        } catch (Exception e) {
            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(
                    "Unable to process your message.");
        }
    }
}