package sample.org.test.authentication;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import sample.org.test.BatchJobEJB;
import sample.org.test.SessionBean;

import java.io.IOException;

@WebServlet("/api-key-auth")
public class APIKeyAuthServlet extends HttpServlet {
    @EJB
    private BatchJobEJB batchJobEJB;

    @EJB
    private SessionBean sessionBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/api_login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String apiKey = request.getParameter("api_key");
        String authorizedKey = "key-b151ba2b1acc"; // input your api key.

        if (username.equals("exampleUser") && apiKey != null && apiKey.equals(authorizedKey)) {
            String message = null;
            try {
                message = batchJobEJB.process(username);
            } catch (Exception e) {
                e.printStackTrace();
            }

            HttpSession session = request.getSession(true);
            sessionBean.startTimer(session, "username", username);
            // Key is valid, forward the request to the secure page
            response.sendRedirect("home");
        } else {
            // Key is not valid, redirect to an error page or handle unauthorized access
            response.setContentType("text/html");
            response.getWriter().println("<h1>Wrong Username or Password!</h1>");
        }
    }
}
