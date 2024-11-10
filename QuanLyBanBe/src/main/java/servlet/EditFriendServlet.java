package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.FriendDAO;
import model.Friend;

import java.io.IOException;

@WebServlet("/edit")
public class EditFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        Friend friend = new Friend();
        friend.setId(id);
        friend.setName(name);
        friend.setPhone(phone);
        friend.setEmail(email);
        friend.setAddress(address);

        FriendDAO friendDAO = new FriendDAO();
        friendDAO.updateFriend(friend);
        response.sendRedirect("list");
    }
}

