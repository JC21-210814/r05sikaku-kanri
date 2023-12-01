package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

/**
 * Servlet implementation class UserVerification
 */
@WebServlet("/UserVerification")
public class UserVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserVerification() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			User user = new User(userName, password);
			
			// ユーザーが認証できない場合は認証ページにリダイレクト
			if (!user.isVerified()) {
				response.sendRedirect("/jsp/index.jsp");
				return;
			}
			
			// ユーザー情報を request に格納
			request.setAttribute("userId", user.getUserId());
			request.getRequestDispatcher("/SikakuView").forward(request,response);
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response.sendRedirect("/jsp/index.jsp");
			return;
		}
	}

}
