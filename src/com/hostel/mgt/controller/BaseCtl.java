/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.UserBean;
/*     */ import com.hostel.mgt.util.DataUtility;
/*     */ import com.hostel.mgt.util.DataValidator;
/*     */ import com.hostel.mgt.util.ServletUtility;
/*     */ import java.io.IOException;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.annotation.WebServlet;
/*     */ import javax.servlet.http.HttpServlet;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @WebServlet({"/BaseCtl"})
/*     */ public abstract class BaseCtl
/*     */   extends HttpServlet
/*     */ {
/*  34 */   private static final Logger log = Logger.getLogger(BaseCtl.class);
/*     */ 
/*     */   
/*     */   public static final String OP_SAVE = "Save";
/*     */ 
/*     */   
/*     */   public static final String OP_CANCEL = "Cancel";
/*     */ 
/*     */   
/*     */   public static final String OP_DELETE = "Delete";
/*     */ 
/*     */   
/*     */   public static final String OP_LIST = "List";
/*     */ 
/*     */   
/*     */   public static final String OP_SEARCH = "Search";
/*     */ 
/*     */   
/*     */   public static final String OP_VIEW = "View";
/*     */ 
/*     */   
/*     */   public static final String OP_NEXT = "Next";
/*     */ 
/*     */   
/*     */   public static final String OP_PREVIOUS = "Previous";
/*     */   
/*     */   public static final String OP_NEW = "New";
/*     */   
/*     */   public static final String OP_GO = "Go";
/*     */   
/*     */   public static final String OP_BACK = "Back";
/*     */   
/*     */   public static final String OP_LOG_OUT = "Logout";
/*     */   
/*     */   public static final String OP_RESET = "Reset";
/*     */   
/*     */   public static final String MSG_SUCCESS = "success";
/*     */   
/*     */   public static final String MSG_ERROR = "error";
/*     */ 
/*     */   
/*     */   protected boolean validate(HttpServletRequest request) {
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preload(HttpServletRequest request) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/*  96 */     return null;
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
/*     */   protected BaseBean populateDTO(BaseBean dto, HttpServletRequest request) {
/* 109 */     log.debug("BaseCtl populate DTO method start");
/*     */     
/* 111 */     String createdBy = request.getParameter("createdBy");
/* 112 */     String modifiedBy = null;
/*     */     
/* 114 */     UserBean userbean = (UserBean)request.getSession().getAttribute("user");
/*     */     
/* 116 */     if (userbean == null) {
/*     */       
/* 118 */       createdBy = "root";
/* 119 */       modifiedBy = "root";
/*     */     } else {
/*     */       
/* 122 */       modifiedBy = userbean.getLogin();
/*     */ 
/*     */       
/* 125 */       if ("null".equalsIgnoreCase(createdBy) || DataValidator.isNull(createdBy)) {
/* 126 */         createdBy = modifiedBy;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 131 */     dto.setCreatedBy(createdBy);
/* 132 */     dto.setModifiedBy(modifiedBy);
/*     */     
/* 134 */     long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));
/*     */     
/* 136 */     if (cdt > 0L) {
/* 137 */       dto.setCreatedDatetime(DataUtility.getTimestamp(cdt));
/*     */     } else {
/* 139 */       dto.setCreatedDatetime(DataUtility.getCurrentTimestamp());
/*     */     } 
/*     */     
/* 142 */     dto.setModifiedDatetime(DataUtility.getCurrentTimestamp());
/*     */     
/* 144 */     log.debug("BaseCtl populate DTO method end");
/* 145 */     return dto;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 150 */     log.debug("BaseCtl service method start");
/*     */ 
/*     */     
/* 153 */     preload(request);
/*     */ 
/*     */     
/* 156 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     System.out.println("operation =" + op);
/*     */     
/* 163 */     if (DataValidator.isNotNull(op) && !"Cancel".equalsIgnoreCase(op) && !"View".equalsIgnoreCase(op) && !"Delete".equalsIgnoreCase(op) && !"Reset".equalsIgnoreCase(op))
/*     */     {
/*     */       
/* 166 */       if (!validate(request)) {
/* 167 */         BaseBean bean = populateBean(request);
/* 168 */         ServletUtility.setBean(bean, request);
/* 169 */         ServletUtility.forward(getView(), request, response);
/*     */         
/*     */         return;
/*     */       } 
/*     */     }
/* 174 */     log.debug("BaseCtl service method end");
/* 175 */     super.service(request, response);
/*     */   }
/*     */   
/*     */   protected abstract String getView();
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\BaseCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */