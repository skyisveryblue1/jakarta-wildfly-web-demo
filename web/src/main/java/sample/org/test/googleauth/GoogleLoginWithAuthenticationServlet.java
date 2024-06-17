package sample.org.test.googleauth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;


@WebServlet("/google-login-with-authenticator")
public class GoogleLoginWithAuthenticationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/google_authenticator_login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("email");
        request.setAttribute("username", username);
        HttpSession session = request.getSession();
        session.setAttribute("email", username);
        request.getRequestDispatcher("/google_authenticator_qrcode.jsp").forward(request, response);
    }
}
