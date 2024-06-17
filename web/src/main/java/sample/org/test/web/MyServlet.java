package sample.org.test.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

public class MyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Perform processing or retrieve data
        String message = "Hello from the servlet!";

        // Set the data as an attribute in the request object
        request.setAttribute("message", message);

        // Forward the request and response to the JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("custom_tag_templete.jsp");
        dispatcher.forward(request, response);
    }
}