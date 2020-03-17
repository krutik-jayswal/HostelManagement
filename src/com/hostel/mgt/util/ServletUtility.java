/*     */ package com.hostel.mgt.util;
/*     */ 
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import javax.servlet.RequestDispatcher;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.ServletRequest;
/*     */ import javax.servlet.ServletResponse;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServletUtility
/*     */ {
/*     */   public static void forward(String page, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
/*  31 */     RequestDispatcher rd = request.getRequestDispatcher(page);
/*  32 */     System.out.println(page);
/*  33 */     rd.forward((ServletRequest)request, (ServletResponse)response);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void redirect(String page, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
/*  38 */     response.sendRedirect(page);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
/*  43 */     request.setAttribute("exception", e);
/*  44 */     forward("/ctl/ErrorCtl", request, response);
/*  45 */     e.printStackTrace();
/*     */   }
/*     */   
/*     */   public static String getErrorMessage(String property, HttpServletRequest request) {
/*  49 */     String val = (String)request.getAttribute(property);
/*  50 */     if (val == null) {
/*  51 */       return "";
/*     */     }
/*  53 */     return val;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getMessage(String property, HttpServletRequest request) {
/*  58 */     String val = (String)request.getAttribute(property);
/*  59 */     if (val == null) {
/*  60 */       return "";
/*     */     }
/*  62 */     return val;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setErrorMessage(String msg, HttpServletRequest request) {
/*  67 */     request.setAttribute("error", msg);
/*     */   }
/*     */   
/*     */   public static String getErrorMessage(HttpServletRequest request) {
/*  71 */     String val = (String)request.getAttribute("error");
/*  72 */     if (val == null) {
/*  73 */       return "";
/*     */     }
/*  75 */     return val;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setSuccessMessage(String msg, HttpServletRequest request) {
/*  80 */     request.setAttribute("success", msg);
/*     */   }
/*     */   
/*     */   public static String getSuccessMessage(HttpServletRequest request) {
/*  84 */     String val = (String)request.getAttribute("success");
/*  85 */     if (val == null) {
/*  86 */       return "";
/*     */     }
/*  88 */     return val;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setBean(BaseBean bean, HttpServletRequest request) {
/*  93 */     request.setAttribute("bean", bean);
/*     */   }
/*     */   
/*     */   public static BaseBean getBean(HttpServletRequest request) {
/*  97 */     return (BaseBean)request.getAttribute("bean");
/*     */   }
/*     */   
/*     */   public static String getParameter(String property, HttpServletRequest request) {
/* 101 */     String val = request.getParameter(property);
/* 102 */     if (val == null) {
/* 103 */       return "";
/*     */     }
/* 105 */     return val;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setList(List list, HttpServletRequest request) {
/* 110 */     request.setAttribute("list", list);
/*     */   }
/*     */   
/*     */   public static List getList(HttpServletRequest request) {
/* 114 */     return (List)request.getAttribute("list");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setPageNo(int pageNo, HttpServletRequest request) {
/* 124 */     request.setAttribute("pageNo", Integer.valueOf(pageNo));
/*     */   }
/*     */   
/*     */   public static int getPageNo(HttpServletRequest request) {
/* 128 */     return ((Integer)request.getAttribute("pageNo")).intValue();
/*     */   }
/*     */   
/*     */   public static void setPageSize(int pageSize, HttpServletRequest request) {
/* 132 */     request.setAttribute("pageSize", Integer.valueOf(pageSize));
/*     */   }
/*     */   
/*     */   public static int getPageSize(HttpServletRequest request) {
/* 136 */     return ((Integer)request.getAttribute("pageSize")).intValue();
/*     */   }
/*     */   
/*     */   public static void setOpration(String msg, HttpServletRequest request) {
/* 140 */     request.setAttribute("Opration", msg);
/*     */   }
/*     */   
/*     */   public static String getOpration(HttpServletRequest request) {
/* 144 */     String val = (String)request.getAttribute("Opration");
/* 145 */     if (val == null) {
/* 146 */       return "";
/*     */     }
/* 148 */     return val;
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mg\\util\ServletUtility.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */