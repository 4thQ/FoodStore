package FoodProductStuff.servlet;

import Database.DBConnection;
import FoodProductStuff.FoodProduct;
import FoodProductStuff.FoodProductDAO;
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

@WebServlet("/go-to-update-foodproduct")
public class GoToUpdateFoodProductServlet extends HttpServlet {
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

      FoodProductDAO foodProductDAO = new FoodProductDAO(DBConnection.getConnection());
      int id = Integer.parseInt(req.getParameter("id"));
      FoodProduct foodProduct = foodProductDAO.findProduct(id);

      req.setAttribute("FoodProduct", foodProduct);
      RequestDispatcher requestDispatcher = req.getRequestDispatcher("UpdateFoodProduct.jsp");
      requestDispatcher.forward(req, res);
    }catch (Exception ex){
      ex.printStackTrace();
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

  }
}
