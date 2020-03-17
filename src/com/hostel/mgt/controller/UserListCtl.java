/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.UserBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.model.UserModel;
/*     */ import com.hostel.mgt.util.DataUtility;
/*     */ import com.hostel.mgt.util.PropertyReader;
/*     */ import com.hostel.mgt.util.ServletUtility;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.annotation.WebServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @WebServlet(name = "UserListCtl", urlPatterns = {"/ctl/userList"})
/*     */ public class UserListCtl
/*     */   extends BaseCtl
/*     */ {
/*  28 */   private static Logger log = Logger.getLogger(UserListCtl.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/*  38 */     log.debug("UserListCtl populateBean method start");
/*  39 */     UserBean bean = new UserBean();
/*     */     
/*  41 */     bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
/*     */     
/*  43 */     bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
/*     */     
/*  45 */     bean.setLogin(DataUtility.getString(request.getParameter("login")));
/*  46 */     log.debug("UserListCtl populateBean method end");
/*  47 */     return (BaseBean)bean;
/*     */   }
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
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  60 */     log.debug("UserListCtl doGet Start");
/*  61 */     List list = null;
/*     */     
/*  63 */     int pageNo = 1;
/*     */     
/*  65 */     int pageSize = DataUtility.getInt(
/*  66 */         PropertyReader.getValue("page.size"));
/*     */     
/*  68 */     UserBean bean = (UserBean)populateBean(request);
/*     */     
/*  70 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/*  73 */     String[] ids = request.getParameterValues("ids");
/*     */     
/*  75 */     UserModel model = new UserModel();
/*     */     
/*     */     try {
/*  78 */       list = model.search(bean, pageNo, pageSize);
/*     */ 
/*     */ 
/*     */       
/*  82 */       if (list == null || list.size() == 0) {
/*  83 */         ServletUtility.setErrorMessage("No record found ", request);
/*     */       }
/*     */       
/*  86 */       ServletUtility.setList(list, request);
/*  87 */       ServletUtility.setPageNo(pageNo, request);
/*  88 */       ServletUtility.setPageSize(pageSize, request);
/*  89 */       ServletUtility.forward(getView(), request, response);
/*  90 */     } catch (ApplicationException e) {
/*  91 */       log.error(e);
/*  92 */       ServletUtility.handleException((Exception)e, request, response);
/*     */       return;
/*     */     } 
/*  95 */     log.debug("UserListCtl doPOst End");
/*     */   }
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
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 110 */     log.debug("UserListCtl doPost Start");
/*     */ 
/*     */     
/* 113 */     List list = null;
/* 114 */     int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
/* 115 */     int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
/*     */     
/* 117 */     pageNo = (pageNo == 0) ? 1 : pageNo;
/* 118 */     pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
/*     */     
/* 120 */     UserBean bean = (UserBean)populateBean(request);
/*     */     
/* 122 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/* 125 */     String[] ids = request.getParameterValues("ids");
/*     */     
/* 127 */     UserModel model = new UserModel();
/*     */     
/*     */     try {
/* 130 */       if ("Search".equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {
/*     */         
/* 132 */         if ("Search".equalsIgnoreCase(op)) {
/* 133 */           pageNo = 1;
/* 134 */         } else if ("Next".equalsIgnoreCase(op)) {
/* 135 */           pageNo++;
/* 136 */         } else if ("Previous".equalsIgnoreCase(op) && pageNo > 1) {
/* 137 */           pageNo--;
/*     */         } 
/*     */       } else {
/* 140 */         if ("New".equalsIgnoreCase(op)) {
/* 141 */           ServletUtility.redirect("/Hostel-Management/ctl/user", request, response); return;
/*     */         } 
/* 143 */         if ("Delete".equalsIgnoreCase(op)) {
/* 144 */           pageNo = 1;
/* 145 */           if (ids != null && ids.length > 0) {
/* 146 */             UserBean deletebean = new UserBean(); byte b; int i; String[] arrayOfString;
/* 147 */             for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String id = arrayOfString[b];
/* 148 */               deletebean.setId(DataUtility.getInt(id));
/* 149 */               model.delete(deletebean); b++; }
/*     */             
/* 151 */             ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
/*     */           } else {
/* 153 */             ServletUtility.setErrorMessage("Select at least one record", request);
/*     */           } 
/* 155 */         } else if ("Reset".equalsIgnoreCase(op)) {
/* 156 */           ServletUtility.redirect("/Hostel-Management/ctl/userList", request, response);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 161 */       list = model.search(bean, pageNo, pageSize);
/*     */ 
/*     */       
/* 164 */       if (list == null || list.size() == 0) {
/* 165 */         ServletUtility.setErrorMessage("No record found ", request);
/*     */       }
/* 167 */       ServletUtility.setList(list, request);
/* 168 */       ServletUtility.setPageNo(pageNo, request);
/* 169 */       ServletUtility.setPageSize(pageSize, request);
/* 170 */       ServletUtility.forward(getView(), request, response);
/*     */     }
/* 172 */     catch (ApplicationException e) {
/* 173 */       log.error(e);
/* 174 */       ServletUtility.handleException((Exception)e, request, response);
/*     */       return;
/*     */     } 
/* 177 */     log.debug("UserListCtl doGet End");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getView() {
/* 188 */     return "/jsp/UserListView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\UserListCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */