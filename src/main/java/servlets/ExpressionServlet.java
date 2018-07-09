package servlets;

import expression.Expression;
import parser.NotValidException;
import parser.ParserExpression;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class ExpressionServlet extends HttpServlet {


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String expressionString = req.getParameter("expression");
        Expression expression = null;
        HashMap<String,Double> map = new HashMap<String,Double>();

        try {
            expression = ParserExpression.parseInitialExpression(expressionString);
        } catch (NotValidException e){
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("errors.jsp").forward(req, resp);
        }
        map.put("value", expression.calculate());
        out.println(map);

    }
}
