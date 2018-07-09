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

public class ExpressionServlet extends HttpServlet {


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String expressionString = req.getParameter("expression");
        Expression expression = null;
        JSONObject json = new JSONObject();

        try {
            expression = ParserExpression.parseInitialExpression(expressionString);
        } catch (NotValidException e){
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("errors.jsp").forward(req, resp);
        }

        json.put("value", expression.calculate());
        json.put("test", null);
        out.println(json.toString());

    }
}
