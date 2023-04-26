package com.shinD.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("*.com")
public class LoginCheckFilter extends HttpFilter implements Filter {

	public LoginCheckFilter() {
		super();
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		
		 if(req.getServletPath().equals( "/view/memberView/signup.com") ||
			 req.getServletPath().equals("/view/memberView/idDupCheck.com") ||
			 req.getServletPath().equals("/view/memberView/classCheck.com") ||
			 req.getServletPath().equals("/view/memberView/MemberSignUp.com")||
			 req.getServletPath().equals("/view/memberView/MemberLogin.com")||
			 req.getServletPath().equals("/view/memberView/login.com")) {
		 
		 }else { HttpSession session = req.getSession();
		 
			 if(session.getAttribute("loginUser") == null) {
				 res.sendRedirect(req.getContextPath()+"/index.jsp");
				 return;
			}
		 }
		 

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
