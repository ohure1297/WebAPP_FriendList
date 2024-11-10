package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Friend;

import java.io.IOException;
import java.util.List;

import controller.FriendDAO;

@WebServlet("/list")
public class ListFriendServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FriendDAO friendDAO = new FriendDAO();
        List<Friend> friends = friendDAO.getAllFriends();
        request.setAttribute("friends", friends);
        request.getRequestDispatcher("jsp/friendList.jsp").forward(request, response);
    }
}
