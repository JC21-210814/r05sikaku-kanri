package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CertifiedInfo;
import model.CertifiedInfomationsFetcher;

/**
 * Servlet implementation class SikakuView
 */
@WebServlet("/SikakuView")
public class SikakuView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SikakuView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		ArrayList<CertifiedInfo> certifiedInfomations = CertifiedInfomationsFetcher.fetchCertifiedInfo(userId);
		request.setAttribute("userId", userId);
		request.setAttribute("certifiedInfomations", certifiedInfomations);
		request.getRequestDispatcher("/jsp/sikakuView.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = (int)request.getAttribute("userId");
		ArrayList<CertifiedInfo> certifiedInfomations = CertifiedInfomationsFetcher.fetchCertifiedInfo(userId);
		
		request.setAttribute("userId", userId);
		request.setAttribute("certifiedInfomations", certifiedInfomations);
		request.getRequestDispatcher("/jsp/sikakuView.jsp").forward(request,response);
	}

}
