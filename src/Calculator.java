import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Calculator")
public class Calculator extends HttpServlet {
    private static final long serialVersionUID = 1;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    public void doGet(HttpServletRequest request ,HttpServletResponse response) 
            throws IOException, ServletException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String xValue = request.getParameter("xVal");
        String yValue = request.getParameter("yVal");
        String action = request.getParameter("action");

        
        out.println("<!doctype html>");
        out.println("<html>");
        out.println("    <head>");
        out.println("        <title>Calculator</title>");
        out.println("    </head>");
        out.println("    <body>");
        boolean validX = false;
        boolean validY = false;
        double x = 0;
		if (xValue != null) {
			try {
				x = Double.parseDouble(xValue);
				validX = true;
			} catch (NumberFormatException e) {
				out.println("<p><span style=\"color:red\"> x is not a number!</span></p>");
			}
		}
        double y = 0;
        if (yValue != null) {
        	try {
				y = Double.parseDouble(yValue);
				validY = true;
			} catch (NumberFormatException e) {
				out.println("<p><span style=\"color:red\"> y is not a number!</span></p>");
			}
        }
        out.println("  <div>");
        out.println("    <form action=\"Calculator\" method=\"POST\">");
        out.println("       <table class=\"oneTable\">");
        out.println("          <tr>");
		if (action != null && validX && validY) {
			if ("+".equals(action)) {
				double sum = getSum(x, y);
				out.println("            <td colspan=\"2\" style=\"text-align:center;\">" + String.format(" %,.2f", x) + " + " + String.format(" %,.2f", y) + " = " + String.format(" %,.2f", sum) + "</td>");
			} else if ("-".equals(action)) {
				double diff = getDiff(x, y);
				out.println("            <td colspan=\"2\" style=\"text-align:center;\">" + String.format(" %,.2f", x) + " - " + String.format(" %,.2f", y) + " = " + String.format(" %,.2f", diff) + "</td>");
			} else if ("*".equals(action)) {
				double product = getProduct(x, y);
				out.println("            <td colspan=\"2\" style=\"text-align:center;\">" + String.format(" %,.2f", x) + " * " + String.format(" %,.2f", y) + " = " + String.format(" %,.2f", product) + "</td>");
			} else if ("/".equals(action)) {
				if (y == 0) {
					out.println("<p><span style=\"color:red\">y cannot be 0 when dividing</span></p>");
				} else {
					double divide = getDivision(x, y);
					out.println("            <td colspan=\"2\" style=\"text-align:center;\">" + String.format(" %,.2f", x) + " / " + String.format(" %,.2f", y) + " = " + String.format(" %,.2f", divide) + "</td>");
				}
			}
		} else {
//			out.println("            <td colspan=\"2\" style=\"text-align:center;\">" + x + " + " + y + "</td>");
		}
		out.println("          </tr>");
		
		out.println("          <tr>");
        out.println("            <td>X: </td>");
        out.println("            <td>");
        out.println("              <input id=\"Xlabel\" type=\"text\" size=\"30\" name=\"xVal\"/>");
        out.println("            </td>");
        out.println("          </tr>");
        out.println("          <tr>");
        out.println("            <td>Y: </td>");
        out.println("            <td>");
        out.println("              <input id=\"ylabel\" type=\"text\" size=\"30\" name=\"yVal\"/>");
        out.println("            </td>");
        out.println("          </tr>");
        out.println("          <tr>");
        out.println("            <td colspan=\"2\" style=\"text-align:center;\">");

        out.println("               <input id=\"plusButton\" type=\"submit\" name=\"action\" value=\"+\"/>");
        out.println("               <input id=\"minusButton\" type=\"submit\" name=\"action\" value=\"-\"/>");
        out.println("               <input id=\"timesButton\" type=\"submit\" name=\"action\" value=\"*\"/>");
        out.println("               <input id=\"divideButton\" type=\"submit\" name=\"action\" value=\"/\"/>");
        out.println("            </td>");
        out.println("          </tr>");
        out.println("        </table>");
        out.println("      </form>");
        out.println("    </div>");
        out.println("  </body>");
        out.println("</html>");
    }
    private static final String PATTERN = "^<([a-z]+)([^<]+)*(?";
    private double getValue(String textName, PrintWriter out) {
    	double textValue = 0;
    	if (isValidInput(textName, out)) {
    		textValue = Double.parseDouble(textName);
    	} else {
    		if (textName.matches(PATTERN)) {
        		out.println("<p><span style=\"color:green\">&quot;" + textName
                        + "&quot; is a html tag</span></p>");
        	} else {
        		out.println("<p><span style=\"color:blue\">&quot;" + textName
                            + "&quot; is not a number!</span></p>");
        	}
        }
    	return textValue;
    }
    private boolean isValidInput(String textName, PrintWriter out) {
    	if (textName.matches(PATTERN)) {
//    		out.println("<p><span style=\"color:red\">&quot;" + textName
//                    + "&quot; is a html tag</span></p>");
    		return false;
    	}
    	double textValue;
    	if (textName != null) {
        	try {
        		textValue = Double.parseDouble(textName);
        	} catch (NumberFormatException e) {
//        		out.println("<h2><span style=\"color:red\">&quot;" + textName
//                        + "&quot; is not a number!</span></h2>");
        		return false;
        	}
        }
    	return true;
    }
    private double getSum(double x, double y) {
    	return x + y;
    }
    private double getDiff(double x, double y) {
    	return x - y;
    }
    private double getProduct(double x, double y) {
    	return x * y;
    }
    private double getDivision(double x, double y) {
    	return x / y;
    }
}
