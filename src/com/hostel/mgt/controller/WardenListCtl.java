/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.WardenBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.model.WardenModel;
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
/*     */ 
/*     */ 
/*     */ @WebServlet(name = "WardenListCtl", urlPatterns = {"/ctl/wardenList"})
/*     */ public class WardenListCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  31 */   private static Logger log = Logger.getLogger(HostelListCtl.class);
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/*  35 */     log.debug("WardenListCtl populateBean method start");
/*  36 */     WardenBean bean = new WardenBean();
/*     */     
/*  38 */     bean.setName(DataUtility.getString(request.getParameter("name")));
/*  39 */     bean.setLogin(DataUtility.getString(request.getParameter("login")));
/*     */     
/*  41 */     log.debug("WardenListCtl populateBean method end");
/*  42 */     return (BaseBean)bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  49 */     log.debug("WardenListCtl doGet Start");
/*  50 */     List list = null;
/*     */     
/*  52 */     int pageNo = 1;
/*     */     
/*  54 */     int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
/*     */     
/*  56 */     WardenBean bean = (WardenBean)populateBean(request);
/*     */     
/*  58 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/*  61 */     String[] ids = request.getParameterValues("ids");
/*     */     
/*  63 */     WardenModel model = new WardenModel();
/*     */     
/*     */     try {
/*  66 */       list = model.search(bean, pageNo, pageSize);
/*     */       
/*  68 */       if (list == null || list.size() == 0) {
/*  69 */         ServletUtility.setErrorMessage("No record found ", request);
/*     */       }
/*     */       
/*  72 */       ServletUtility.setList(list, request);
/*  73 */       ServletUtility.setPageNo(pageNo, request);
/*  74 */       ServletUtility.setPageSize(pageSize, request);
/*  75 */       ServletUtility.forward(getView(), request, response);
/*  76 */     } catch (ApplicationException e) {
/*  77 */       log.error(e);
/*  78 */       ServletUtility.handleException((Exception)e, request, response);
/*     */       return;
/*     */     } 
/*  81 */     log.debug("WardenListCtl doPOst End");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  88 */     log.debug("WardenListCtl doPost Start");
/*     */     
/*  90 */     List list = null;
/*  91 */     int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
/*  92 */     int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
/*     */     
/*  94 */     pageNo = (pageNo == 0) ? 1 : pageNo;
/*  95 */     pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
/*     */     
/*  97 */     WardenBean bean = (WardenBean)populateBean(request);
/*     */     
/*  99 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/* 102 */     String[] ids = request.getParameterValues("ids");
/*     */     
/* 104 */     WardenModel model = new WardenModel();
/*     */     
/*     */     try {
/* 107 */       if ("Search".equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {
/*     */         
/* 109 */         if ("Search".equalsIgnoreCase(op)) {
/* 110 */           pageNo = 1;
/* 111 */         } else if ("Next".equalsIgnoreCase(op)) {
/* 112 */           pageNo++;
/* 113 */         } else if ("Previous".equalsIgnoreCase(op) && pageNo > 1) {
/* 114 */           pageNo--;
/*     */         } 
/*     */       } else {
/* 117 */         if ("New".equalsIgnoreCase(op)) {
/* 118 */           ServletUtility.redirect("/Hostel-Management/ctl/warden", request, response); return;
/*     */         } 
/* 120 */         if ("Delete".equalsIgnoreCase(op)) {
/* 121 */           pageNo = 1;
/* 122 */           if (ids != null && ids.length > 0) {
/* 123 */             WardenBean deletebean = new WardenBean(); byte b; int i; String[] arrayOfString;
/* 124 */             for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String id = arrayOfString[b];
/* 125 */               deletebean.setId(DataUtility.getInt(id));
/* 126 */               model.delete(deletebean); b++; }
/*     */             
/* 128 */             ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
/*     */           } else {
/* 130 */             ServletUtility.setErrorMessage("Select at least one record", request);
/*     */           } 
/* 132 */         } else if ("Reset".equalsIgnoreCase(op)) {
/* 133 */           ServletUtility.redirect("/Hostel-Management/ctl/wardenList", request, response);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 138 */       list = model.search(bean, pageNo, pageSize);
/*     */       
/* 140 */       if (list == null || list.size() == 0) {
/* 141 */         ServletUtility.setErrorMessage("No record found ", request);
/*     */       }
/* 143 */       ServletUtility.setList(list, request);
/* 144 */       ServletUtility.setPageNo(pageNo, request);
/* 145 */       ServletUtility.setPageSize(pageSize, request);
/* 146 */       ServletUtility.forward(getView(), request, response);
/*     */     }
/* 148 */     catch (ApplicationException e) {
/* 149 */       log.error(e);
/* 150 */       ServletUtility.handleException((Exception)e, request, response);
/*     */       return;
/*     */     } 
/* 153 */     log.debug("WardenListCtl doGet End");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getView() {
/* 159 */     return "/jsp/WardenListView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\WardenListCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */