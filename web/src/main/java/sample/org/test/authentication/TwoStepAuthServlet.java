package sample.org.test.authentication;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/two-step-auth")
public class TwoStepAuthServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("two_step_login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.equals("exampleUser") && password.equals("examplePassword")) {
            request.setAttribute("username", username);

            RequestDispatcher dispatcher = request.getRequestDispatcher("two_step_verify.jsp");
            dispatcher.forward(request, response);
        } else {
            response.setContentType("text/html");
            response.getWriter().println("<h1>Wrong Username or Password!</h1>");
        }

    }

}
