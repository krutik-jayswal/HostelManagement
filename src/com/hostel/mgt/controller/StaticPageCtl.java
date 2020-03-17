/*    */ package com.hostel.mgt.controller;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.annotation.WebServlet;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @WebServlet(name = "StaticPageCtl", urlPatterns = {"/static"})
/*    */ public class StaticPageCtl
/*    */   extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 25 */   private static Logger log = Logger.getLogger(StaticPageCtl.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 34 */     log.debug("Static controller get method - Called");
/*    */     
/* 36 */     log.debug(request.getParameter("action"));
/*    */     
/* 38 */     if (request.getParameter("action").equalsIgnoreCase("c")) {
/*    */       
/* 40 */       request.getRequestDispatcher("/jsp/ContactUs.jsp").forward((ServletRequest)request, (ServletResponse)response);
/* 41 */     } else if (request.getParameter("action").equalsIgnoreCase("a")) {
/* 42 */       request.getRequestDispatcher("/jsp/AboutUs.jsp").forward((ServletRequest)request, (ServletResponse)response);
/*    */     } else {
/* 44 */       request.getRequestDispatcher("/jsp/Reports.jsp").forward((ServletRequest)request, (ServletResponse)response);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\StaticPageCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */