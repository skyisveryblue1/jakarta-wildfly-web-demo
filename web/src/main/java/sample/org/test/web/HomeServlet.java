package sample.org.test.web;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sample.org.test.SessionBean;

import java.io.IOException;

@WebServlet("/home")

public class HomeServlet extends HttpServlet {
    @EJB
    private SessionBean sessionBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String session_name = (String) session.getAttribute("username");

        if (session_name == null) {
            response.sendRedirect("");
        } else {
            // Perform processing or retrieve data
            String message = "Logged in user! <br> Your name: " + session_name;

            // Set the data as an attribute in the request object
            request.setAttribute("message", message);

            // Forward the request and response to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);
        }
    }
}
