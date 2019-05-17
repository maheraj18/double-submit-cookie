
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class home
 */
@WebServlet("/home")
public class home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public home() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String token = request.getParameter("tokenval");

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("doubleSubCookie")) {

					String doubleSubmitCookie = cookie.getValue();
					System.out.println("doubleSubmitCookie val"
							+ doubleSubmitCookie);

					if (token.equals(doubleSubmitCookie)) {

						System.out.println("ok: " + token);
						PrintWriter out = response.getWriter();

						out.print("<script language='JavaScript'>alert('Success');</script>");

					} else {
						System.out.println("no token: " + token);
						PrintWriter out = response.getWriter();
						out.print("<script language='JavaScript'>alert('Error');</script>");
					}

				}
			}
		}

	}

}
