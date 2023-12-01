package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OwnedVouchersFetcher;
import model.Voucher;

/**
 * Servlet implementation class VoucherView
 */
@WebServlet("/VoucherView")
public class VoucherView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoucherView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		ArrayList<Voucher> ownedVouchers = OwnedVouchersFetcher.fetchOwnedVouchers(userId);
		request.setAttribute("userId", userId);
		request.setAttribute("ownedVouchers", ownedVouchers);
		request.getRequestDispatcher("jsp/voucherView.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = (int)request.getAttribute("userId");
		ArrayList<Voucher> ownedVouchers = OwnedVouchersFetcher.fetchOwnedVouchers(userId);
		
		request.setAttribute("userId", userId);
		request.setAttribute("ownedVouchers", ownedVouchers);
		request.getRequestDispatcher("jsp/voucherView.jsp").forward(request,response);
	}

}
