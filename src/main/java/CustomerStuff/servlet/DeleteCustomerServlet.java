package CustomerStuff.servlet;

import CustomerStuff.CustomerDAO;
import Database.DBConnection;
import Controllers.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/delete-customer")
public class DeleteCustomerServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");

    try{
      HttpSession session = req.getSession();
      User user = (User) session.getAttribute("user");
      if (user == null){
        res.sendRedirect("LoginPage.jsp");
        return;
      }

      Map<String, String[]> parameters = req.getParameterMap();
      System.out.println("============");
      parameters.forEach((key, value) -> System.out.println(key + " : " + value[0]));

      int id = Integer.parseInt(req.getParameter("id"));
      CustomerDAO customerDAO = new CustomerDAO(DBConnection.getConnection());
      customerDAO.deleteCustomerById(id);
      RequestDispatcher requestDispatcher = req.getRequestDispatcher("ShowAllCustomer.jsp");
      requestDispatcher.forward(req, res);
    }catch (Exception ex){

    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

  }
}
