package servlets;


import expression.Expression;
import parser.ParserExpression;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExpressionServlet extends HttpServlet {


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String expressionString = req.getParameter("expression");
        Expression expression = ParserExpression.parseInitialExpression(expressionString);

        double result = expression.calculate();

        req.setAttribute("expression",expressionString);
        req.setAttribute("result", result);
        req.getRequestDispatcher("solution.jsp").forward(req, resp);
    }
}
