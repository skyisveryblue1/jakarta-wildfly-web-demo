package sample.org.test.web;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import sample.org.test.SessionBean;
import sample.org.test.utils.JWTUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/jwt-result")
public class JwtResultServlet extends HttpServlet {
    @EJB
    private SessionBean sessionBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = "exampleUser"; // Replace with the authenticated username
        HttpSession session = request.getSession(true);
        String jwtToken = null;
        try {
            jwtToken = JWTUtil.generateToken(username);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // Include the JWT token in the session
        sessionBean.startTimer(session, "jwtToken", jwtToken);

        RequestDispatcher dispatcher = request.getRequestDispatcher("jwt_result.jsp");
        dispatcher.forward(request, response);
    }

}