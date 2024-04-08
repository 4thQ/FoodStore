package Controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DBConnection;


import java.util.Map;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");

    try {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Check if the provided username and password match the predefined values
        if (username.equals("admin") && password.equals("123")) {
            // If the credentials match, create a dummy user object
            User user = new User();
            user.setUsername(username);

            // Set the user object in the session
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            // Redirect to the desired page
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("ShowAllFoodProduct.jsp");
            requestDispatcher.forward(req, res);
            return;
        }

        // If the provided credentials don't match, proceed with database authentication
        UserDAO userDAO = new UserDAO(DBConnection.getConnection());
        User user = userDAO.getUserByUsernameAndPassword(username, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("ShowAllFoodProduct.jsp");
            requestDispatcher.forward(req, res);
            return;
        }

        req.setAttribute("message", "Incorrect username or password!");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("LoginPage.jsp");
        requestDispatcher.forward(req, res);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
}
