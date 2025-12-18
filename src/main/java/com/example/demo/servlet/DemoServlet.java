package com.example.demo.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import java.util.logging.Level;

@WebServlet(urlPatterns = "/demo")
public class DemoServlet extends HttpServlet {

    private static final Logger LOGGER =
            Logger.getLogger(DemoServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try {
            resp.setContentType("text/plain");
            PrintWriter writer = resp.getWriter();
            writer.write("OK");
            writer.flush();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error in /demo servlet", e);
            try {
                resp.reset();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("Internal Server Error");
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Failed to write error response", ex);
            }
        }
    }
}
