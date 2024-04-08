package CustomerStuff.servlet;

import CustomerStuff.Address;
import CustomerStuff.Customer;
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

@WebServlet("/add-customer")
public class AddCustomerServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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

      String BusinessName = req.getParameter("BusinessName");
      String AddressLine1 = req.getParameter("AddressLine1");
      String AddressLine2 = req.getParameter("AddressLine2");
      String AddressLine3 = req.getParameter("AddressLine3");
      String Country = req.getParameter("Country");
      String PostCode = req.getParameter("PostCode");
      String TelephoneNumber = req.getParameter("TelephoneNumber");

      Address address = new Address();
      address.setAddressLine1(AddressLine1);
      address.setAddressLine2(AddressLine2);
      address.setAddressLine3(AddressLine3);
      address.setCountry(Country);
      address.setPostCode(PostCode);

      Customer customer = new Customer();
      customer.setBusinessName(BusinessName);
      customer.setAddress(address);
      customer.setTelephoneNumber(TelephoneNumber);

      CustomerDAO customerDAO = new CustomerDAO(DBConnection.getConnection());
      customerDAO.addCustomer(customer);

      RequestDispatcher requestDispatcher = req.getRequestDispatcher("ShowAllCustomer.jsp");
      requestDispatcher.forward(req, res);
    }catch (Exception ex){
      ex.printStackTrace();
    }
  }
}
