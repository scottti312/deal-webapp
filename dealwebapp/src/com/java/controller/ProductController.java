package com.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.java.deal.*;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
	   response.setContentType("text/html");
	   PrintWriter out = response.getWriter();
	   String productName = request.getParameter("searchProductName");
       //out.println("name: " + productName);
       
       Application app = new Application();
       String test = app.searchProduct(productName);
       /*LinkedList<Map<String, String>> productList = app.searchProduct(productName);
       if(productList.size() != 0) {
			for(Map<String, String> oneProduct : productList) {
				for(String key : oneProduct.keySet()) {
					System.out.println(key + " : " + oneProduct.get(key));
				}
			}
		}*/
		//request.setAttribute("test", "test");
		request.setAttribute("products", test);
		getServletContext().getRequestDispatcher("/test.jsp").forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
