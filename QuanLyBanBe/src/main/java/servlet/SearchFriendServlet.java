package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.FriendDAO;
import model.Friend;

import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        FriendDAO friendDAO = new FriendDAO();
        List<Friend> friends = friendDAO.searchFriends(keyword);
        request.setAttribute("friends", friends);
        request.getRequestDispatcher("jsp/friendList.jsp").forward(request, response);
    }
}

