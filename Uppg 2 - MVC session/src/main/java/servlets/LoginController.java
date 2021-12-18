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
		
		
		//Till denna kod kommer du när du är i en session (undantag finns). Om du uppdatrerar snacks, ändrar url manuellt etc.
		
		// Kollar om en session är igång
		if (request.getSession().getAttribute("user") != null) {
			// Hittar session
			UserBean bean = (UserBean) request.getSession().getAttribute("user");

			// Kollar om användaren är validerad. kollar således inte lösenord igen utan om lösenordet har blivit validerat tidigare. 
			if (bean.validate()) {
				
				// Om användaren har skrivit in en snack så kommer den med igen här
				if (request.getParameter("snack") != null && request.getParameter("snack") != "") {
					
					bean.setFavoriteSnack(request.getParameter("snack"));
				}
				// Om användaren har skrivit in en månad så kommer den med igen här
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
				
				// Om du inte har en seesionid, du inte är validerad. Du skickas till Logout servlet
			
				RequestDispatcher rd = request.getRequestDispatcher("Logout");
				rd.forward(request, response);
				
			}
		} else {
			// Finns det ingen session då skickas du till index/inloggning
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Till denna kod kommer du från inloggningen på index.
		
		
		// get the login data
		String pass = request.getParameter("pass");
		String uname = request.getParameter("uname");

		// Set values of the user bean
		UserBean bean = new UserBean();
		bean.setName(uname);
		bean.setPassword(pass);

		// Kollar om användarnamn och lösenord är korrekt
		if (bean.validate()) {

			// Så här kallar vi in session
			HttpSession session = request.getSession();

			// Nu spara vi data som ett atribut i session
			session.setAttribute("user", bean);

			request.setAttribute("user", bean);

			// Vi skickar information vidare till inloggad.jpg. RequestDispatcher forward gör att den skickar vidare informationen
			RequestDispatcher rd = request.getRequestDispatcher("inloggad.jsp");
			rd.forward(request, response);

		

		} else {
			// går till Errorsidan som inkluderar indexsidan
			// Kunde skicka direkt till index också... men då hade vi inte fått den fina informationen att Användarnamn eller lösenord var fel. 
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
	}

}