/*    */ package com.hostel.mgt.controller;
/*    */ 
/*    */ import com.hostel.mgt.util.ServletUtility;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.annotation.WebServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @WebServlet(name = "WelcomeCtl", urlPatterns = {"/welcome"})
/*    */ public class WelcomeCtl
/*    */   extends BaseCtl
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 52 */     ServletUtility.forward("/jsp/Welcome.jsp", request, response);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getView() {
/* 63 */     return "/jsp/Welcome.jsp";
/*    */   }
/*    */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\WelcomeCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */