package sample.org.test.authentication;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import sample.org.test.BatchJobEJB;
import sample.org.test.SessionBean;

@WebServlet("/google-login")
public class GoogleLoginServlet extends HttpServlet {
    @EJB
    private BatchJobEJB batchJobEJB;

    @EJB
    private SessionBean sessionBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("google_login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String username = req.getParameter("name");
            HttpSession session = req.getSession(true);
            sessionBean.startTimer(session, "username", username);

            String message = null;
            try {
                message = batchJobEJB.process(username);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
