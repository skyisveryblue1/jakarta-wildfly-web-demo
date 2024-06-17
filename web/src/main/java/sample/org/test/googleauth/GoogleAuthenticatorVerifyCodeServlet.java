package sample.org.test.googleauth;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import java.io.IOException;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.PageContext;
import sample.org.test.BatchJobEJB;
import sample.org.test.SessionBean;

@WebServlet("/google-login-with-authenticator/verify")
public class GoogleAuthenticatorVerifyCodeServlet extends HttpServlet {
    @EJB
    private SessionBean sessionBean;

    @EJB
    private BatchJobEJB batchJobEJB;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the entered verification code from the form
        String enteredCode = request.getParameter("code");

        // Get the previously generated secret key
        String secretKey = request.getParameter("secretKey");

        // Create an instance of GoogleAuthenticator
        GoogleAuthenticator gAuth = new GoogleAuthenticator();

        // Validate the entered code against the secret key
        boolean isValid = gAuth.authorize(secretKey, Integer.parseInt(enteredCode));

        if (isValid) {
            // Code is valid, authentication successful
            // Perform the necessary actions (e.g., grant access, set session attributes)
            HttpSession session = request.getSession();
            try {
                batchJobEJB.process(session.getAttribute("email").toString());
            } catch (Exception e) {
                e.printStackTrace();
            };
            sessionBean.startTimer(session, "username", session.getAttribute("email").toString());
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            // Code is invalid, authentication failed
            // Handle invalid code (e.g., redirect to login page with error message)
            response.getWriter().write("Authentication failed. Retry !");
        }
    }
}