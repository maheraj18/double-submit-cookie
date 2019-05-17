
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username.equals("mahe") && password.equals("chillax")) {
			
			UUID idOne = UUID.randomUUID();
			String id = idOne.toString();
			Cookie loginCookie = new Cookie("user", id);
			loginCookie.setMaxAge(30*60);
			response.addCookie(loginCookie);

			/*Generate the CSRF token for the session and set a cookie in the browser*/
			HttpSession session = request.getSession();

			String storedToken = (String) session.getAttribute("doubleSubCookie");
			System.out.println("storedToken: " + storedToken);
			if (storedToken == null) {
				
				String doubleCsrfToken = generateCSRFToken();
				Cookie doubleSubCookie = new Cookie("doubleSubCookie", doubleCsrfToken);
				doubleSubCookie.setMaxAge(30*60);
				response.addCookie(doubleSubCookie);
				
				
			}
			
			response.sendRedirect("form.jsp");

		} else {
			response.sendRedirect("error.jsp");
		}

	}

	public static String generateCSRFToken() {
		SecureRandom random = new SecureRandom();

		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		Encoder encoder = Base64.getUrlEncoder().withoutPadding();
		String token = encoder.encodeToString(bytes);
		return token;

	}

}
