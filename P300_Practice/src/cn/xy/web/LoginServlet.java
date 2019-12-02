package cn.xy.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xy.dao.UserDao;
import cn.xy.domain.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User loginUser = new User();
		loginUser.setUsername(username);
		loginUser.setPassword(password);

		UserDao userDao = new UserDao();
		User user = userDao.login(loginUser);
		System.out.println(user);
		if (user != null) {
			request.getRequestDispatcher("/FailServlet").forward(request, response);

		} else {
			request.setAttribute("user", user);
			request.getRequestDispatcher("/SuccessServlet").forward(request, response);
		}

	}
}