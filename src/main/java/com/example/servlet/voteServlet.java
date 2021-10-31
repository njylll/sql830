package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet(urlPatterns = "/vote")
public class voteServlet extends HttpServlet {
    CopyOnWriteArrayList<String> ipList = new CopyOnWriteArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        resp.sendRedirect("/vote.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String ip = req.getRemoteAddr();
        if(ipList.contains(ip))
        {
            ServletOutputStream output = resp.getOutputStream();
            output.write("您已经投过票了".getBytes(StandardCharsets.UTF_8));
        }
        else {
            PrintWriter writer = resp.getWriter();
            writer.print("投票成功");
            ipList.add(ip);
        }
    }

}
