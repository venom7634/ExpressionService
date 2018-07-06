package servlets;


import expression.Expression;
import org.json.JSONObject;
import parser.NotValidException;
import parser.ParserExpression;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExpressionServlet extends HttpServlet {


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String expressionString = req.getParameter("expression");
        JSONObject result = new JSONObject();
        Expression expression = null;

        try {
            expression = ParserExpression.parseInitialExpression(expressionString);
        } catch (NotValidException e){
            req.setAttribute("error",e.getMessage());
            req.getRequestDispatcher("solution.jsp").forward(req, resp);
        }

        result.put("value",expression.calculate());
        out.println(result);
    }
}
