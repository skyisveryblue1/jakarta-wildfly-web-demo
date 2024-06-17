package sample.org.test.authentication;

import jakarta.annotation.Resource;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import sample.org.test.BatchJobEJB;
import sample.org.test.SessionBean;

import java.io.IOException;

@WebServlet("/plain-user-pass-auth")
@RolesAllowed("user")
public class PlainUserPassAuthServlet extends HttpServlet {
    @EJB
    private BatchJobEJB batchJobEJB;

    @EJB
    private SessionBean sessionBean;

    @Inject
    private SecurityContext securityContext;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Perform processing or retrieve data
        String message = "Hello from the servlet!";

        // Set the data as an attribute in the request object
        request.setAttribute("message", message);

        // Forward the request and response to the JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String message = null;

        /*AuthenticationStatus status = securityContext.authenticate(
                request,
                response,
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(username, password)));
        System.out.println(securityContext.isCallerInRole("user"));
        if (status.equals(AuthenticationStatus.SUCCESS)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("home.jsp");
        } else {
            // Authentication failed
            response.sendRedirect("login.jsp");
        }*/

        // Perform authentication
        securityContext.authenticate(
                request,
                response,
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(username, password))
        );

        System.out.println("Authentication: " + securityContext.getCallerPrincipal().getName());

        // Check if authentication succeeded
        if (securityContext.getCallerPrincipal() != null) {
            HttpSession session = request.getSession(true);
            sessionBean.startTimer(session, "username", username);
            try {
                message = batchJobEJB.process(username);
            } catch (Exception e) {
                e.printStackTrace();
            }

            response.sendRedirect("home");
        } else {
            response.setContentType("text/html");
            response.getWriter().println("<h1>Wrong Username or Password!</h1>");
        }

        // Perform any necessary processing with the form data
        /*if (username.equals("exampleUser") && password.equals("examplePassword")) {

        } else {
            response.setContentType("text/html");
            response.getWriter().println("<h1>Wrong Username or Password!</h1>");
        }*/

    }
}
