package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ExamPlan;
import model.ExamPlansFetcher;

/**
 * Servlet implementation class ScheduleView
 */
@WebServlet("/ScheduleView")
public class ScheduleView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		ArrayList<ExamPlan> examPlans = ExamPlansFetcher.fetchExamPlans(userId);
		request.setAttribute("userId", userId);
		request.setAttribute("examPlans", examPlans);
		request.getRequestDispatcher("jsp/scheduleView.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int user = (int)request.getAttribute("userId");
		ArrayList<ExamPlan> examPlans = ExamPlansFetcher.fetchExamPlans(user);
		
		request.setAttribute("examPlans", examPlans);
		request.getRequestDispatcher("jsp/scheduleView.jsp").forward(request,response);
	}

}
