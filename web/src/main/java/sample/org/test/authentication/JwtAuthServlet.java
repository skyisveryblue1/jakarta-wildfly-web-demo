package sample.org.test.authentication;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import sample.org.test.BatchJobEJB;
import sample.org.test.SessionBean;
import sample.org.test.utils.JWTUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


@WebServlet("/jwt-auth")
public class JwtAuthServlet extends HttpServlet {

    @EJB
    private BatchJobEJB batchJobEJB;

    @EJB
    private SessionBean sessionBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jwt_login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username"); // Replace with the authenticated username
        String password = request.getParameter("password");

        if (username.equals("exampleUser") && password.equals("examplePassword")) {
            String message = null;
            try {
                message = batchJobEJB.process(username);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String jwtToken = null;
            try {
                jwtToken = JWTUtil.generateToken(username);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            // Include the JWT token in the session
            HttpSession session = request.getSession(true);
            session.setAttribute("jwtToken", jwtToken);
            sessionBean.startTimer(session, "username", username);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("jwt_result.jsp");
        dispatcher.forward(request, response);
    }
}
