/*    */ package com.hostel.mgt.controller;
/*    */ 
/*    */ import com.hostel.mgt.util.ServletUtility;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.annotation.WebFilter;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @WebFilter(filterName = "FrontCtl", urlPatterns = {"/ctl/*", "/doc/*"})
/*    */ public class FrontController
/*    */   implements Filter
/*    */ {
/* 28 */   private static Logger log = Logger.getLogger(FrontController.class);
/*    */ 
/*    */   
/*    */   public void destroy() {}
/*    */ 
/*    */   
/*    */   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
/* 35 */     log.debug("FrontController doFilter method start");
/*    */     
/* 37 */     HttpServletRequest request = (HttpServletRequest)req;
/* 38 */     HttpServletResponse response = (HttpServletResponse)resp;
/* 39 */     HttpSession session = request.getSession(true);
/*    */     
/* 41 */     if (session.getAttribute("user") == null) {
/*    */       
/* 43 */       ServletUtility.setErrorMessage("Your session has been expired. Please re-Login.", request);
/*    */       
/* 45 */       String hitUri = request.getRequestURI();
/*    */       
/* 47 */       req.setAttribute("uri", hitUri);
/*    */       
/* 49 */       log.debug("req : " + request.getParameter("target"));
/*    */       
/* 51 */       ServletUtility.forward("/LoginCtl", request, response);
/*    */     } else {
/*    */       
/* 54 */       chain.doFilter(req, resp);
/* 55 */       System.out.println("respronse front coltoller");
/*    */     } 
/* 57 */     log.debug("FrontController doFilter method end");
/*    */   }
/*    */   
/*    */   public void init(FilterConfig conf) throws ServletException {}
/*    */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\FrontController.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */