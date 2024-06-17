package sample.org.test.authentication;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import sample.org.test.BatchJobEJB;
import sample.org.test.SessionBean;

import java.io.IOException;

@WebServlet("/two-step-auth-verify")
public class TwoStepAuthVerifyServlet extends HttpServlet {
    @EJB
    private BatchJobEJB batchJobEJB;

    @EJB
    private SessionBean sessionBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implementation for two-step authentication logic
        // response.getWriter().println("Two-step authentication logic");
        String username = request.getParameter("username");

        String enteredCode = request.getParameter("verificationCode");
        HttpSession session = request.getSession();

        // Retrieve the generated verification code from session or database for the user
        String savedCode = (String) session.getAttribute("qrcode");
        if (enteredCode.equals(savedCode)) {
            String message = null;
            try {
                message = batchJobEJB.process(username);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sessionBean.startTimer(session, "username", username);
            // Successful verification
            response.sendRedirect("home");
        } else {
            // Invalid code
            response.getWriter().println("Invalid verification code! Access denied.");
        }
    }

}
