package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBean;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//Till denna kod kommer du n�r du �r i en session (undantag finns). Om du uppdatrerar snacks, �ndrar url manuellt etc.
		
		// Kollar om en session �r ig�ng
		if (request.getSession().getAttribute("user") != null) {
			// Hittar session
			UserBean bean = (UserBean) request.getSession().getAttribute("user");

			// Kollar om anv�ndaren �r validerad. kollar s�ledes inte l�senord igen utan om l�senordet har blivit validerat tidigare. 
			if (bean.validate()) {
				
				// Om anv�ndaren har skrivit in en snack s� kommer den med igen h�r
				if (request.getParameter("snack") != null && request.getParameter("snack") != "") {
					
					bean.setFavoriteSnack(request.getParameter("snack"));
				}
				// Om anv�ndaren har skrivit in en m�nad s� kommer den med igen h�r
				if (request.getParameter("month") != null && request.getParameter("month") != "") {
					
					bean.setFavoriteMonth(request.getParameter("month"));
				}
		
				
				// Om session vara validerad. Laddar in session och skickar till inloggad.jsp
				HttpSession session = request.getSession();
				session.setAttribute("user", bean);
				request.setAttribute("user", bean);

				RequestDispatcher rd = request.getRequestDispatcher("inloggad.jsp");
				rd.forward(request, response);
			} else {
				
				// Om du inte har en seesionid, du inte �r validerad. Du skickas till Logout servlet
			
				RequestDispatcher rd = request.getRequestDispatcher("Logout");
				rd.forward(request, response);
				
			}
		} else {
			// Finns det ingen session d� skickas du till index/inloggning
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Till denna kod kommer du fr�n inloggningen p� index.
		
		
		// get the login data
		String pass = request.getParameter("pass");
		String uname = request.getParameter("uname");

		// Set values of the user bean
		UserBean bean = new UserBean();
		bean.setName(uname);
		bean.setPassword(pass);

		// Kollar om anv�ndarnamn och l�senord �r korrekt
		if (bean.validate()) {

			// S� h�r kallar vi in session
			HttpSession session = request.getSession();

			// Nu spara vi data som ett atribut i session
			session.setAttribute("user", bean);

			request.setAttribute("user", bean);

			// Vi skickar information vidare till inloggad.jpg. RequestDispatcher forward g�r att den skickar vidare informationen
			RequestDispatcher rd = request.getRequestDispatcher("inloggad.jsp");
			rd.forward(request, response);

		

		} else {
			// g�r till Errorsidan som inkluderar indexsidan
			// Kunde skicka direkt till index ocks�... men d� hade vi inte f�tt den fina informationen att Anv�ndarnamn eller l�senord var fel. 
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
	}

}