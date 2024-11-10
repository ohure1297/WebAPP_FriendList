package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.FriendDAO;

import java.io.IOException;

@WebServlet("/delete")
public class DeleteFriendServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        FriendDAO friendDAO = new FriendDAO();
        friendDAO.deleteFriend(id);
        response.sendRedirect("list");
    }
}

