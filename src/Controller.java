
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.DBHelper;
import user.User;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/project")
	private DataSource dataSource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String page;
		if (request.getParameterMap().containsKey("page")) {
			page = request.getParameter("page");
		} else {
			page = "index";
		}

		switch (page) {
		case "list_users":
				listUsers(request, response);
			break;
		case "add_user":
			addUser(request,response);
			break;
		case "index":
			homepage(request,response);
			break;
		case "submitUser":
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			User user = new User(name,email);
		    submitUser(user,request,response);
		default:
			error(request,response);
			break;
		}	
	}

	private void submitUser(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
		new DBHelper().addUser(user, dataSource);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		request.getRequestDispatcher("submittedUser.jsp").forward(request, response);
       
	}

	private void error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("error.jsp").forward(request, response);
		
	}

	private void homepage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("add_users.jsp").forward(request, response);
		
	}
	

	private void listUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("is it coming in list user at start");
		List<User> usersList = null;
		try {
			usersList = new DBHelper().getUsers(dataSource);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("users", usersList);
		System.out.println("is it coming in list user at before forward request");
		request.getRequestDispatcher("list_users.jsp").forward(request, response);

	}

}
