package ru.tecon.causesDetection.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.tecon.causesDetection.ejb.CheckUserSB;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Сервлет обертка для запуска формы Выявление отклонений
 *
 * @author Aleksey Sergeev
 */

@WebServlet("/causesDetection")
public class Servlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Servlet.class.getName());

    @EJB
    private CheckUserSB bean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        if (parameterMap.containsKey("sessionId")) {
            if (bean.checkSession(req.getParameter("sessionId"))) {
        req.getRequestDispatcher("/causesDetection.xhtml").forward(req, resp);
            } else {
                // Авторизуйтесь в системе
                logger.log(Level.WARNING, "authorization error");
                req.getRequestDispatcher("/error.html").forward(req, resp);
            }
        } else {
            // Не хватает параметров
            logger.log(Level.WARNING, "missing parameters");
            req.getRequestDispatcher("/error.html").forward(req, resp);
        }
    }
}

