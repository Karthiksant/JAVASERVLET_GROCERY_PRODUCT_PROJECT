package controller;

import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import dto.ProductDto;
import jakarta.servlet.ServletContext;

public class ProductSave extends HttpServlet 
{
	protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws IOException,ServletException
	{
		String Pname=req.getParameter("productName");
		String Pstate=req.getParameter("state");
		double Pprice=Double.parseDouble(req.getParameter("productPrice"));
		
		ProductDto productDto=new ProductDto();
		productDto.setProductName(Pname);
		productDto.setProductPrice(Pprice);
		
		javax.servlet.ServletContext servletContext=getServletContext();
		double CGST=Double.parseDouble(servletContext.getInitParameter("CGST"));
		
		ServletConfig servletConfig=getServletConfig();
		
		if(Pstate.equals("TS"))
		{
			double SGST=Double.parseDouble(servletConfig.getInitParameter("TS"));
			double totalprice=Pprice+Pprice*(SGST+CGST);
			productDto.setProductTotalPrice(totalprice);
		}
		else
		{
			double SGST=Double.parseDouble(servletConfig.getInitParameter("AP"));
			double totalprice=Pprice+Pprice*(SGST+CGST);
			productDto.setProductTotalPrice(totalprice);
		}		
		
		ProductDao productDao=new ProductDao();
		productDao.save(productDto);
		
		PrintWriter out=resp.getWriter();
		out.print("data saved");
	}

}
