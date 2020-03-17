/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.HostelBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.model.HostelModel;
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
/*     */ @WebServlet(name = "HostelListCtl", urlPatterns = {"/ctl/hostelList"})
/*     */ public class HostelListCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  31 */   private static Logger log = Logger.getLogger(HostelListCtl.class);
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/*  35 */     log.debug("HostelListCtl populateBean method start");
/*  36 */     HostelBean bean = new HostelBean();
/*     */     
/*  38 */     bean.setName(DataUtility.getString(request.getParameter("name")));
/*     */     
/*  40 */     log.debug("HostelListCtl populateBean method end");
/*  41 */     return (BaseBean)bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  50 */     log.debug("HostelListCtl doGet Start");
/*  51 */     List list = null;
/*     */     
/*  53 */     int pageNo = 1;
/*     */     
/*  55 */     int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
/*     */     
/*  57 */     HostelBean bean = (HostelBean)populateBean(request);
/*     */     
/*  59 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/*  62 */     String[] ids = request.getParameterValues("ids");
/*     */     
/*  64 */     HostelModel model = new HostelModel();
/*     */     
/*     */     try {
/*  67 */       list = model.search(bean, pageNo, pageSize);
/*     */       
/*  69 */       if (list == null || list.size() == 0) {
/*  70 */         ServletUtility.setErrorMessage("No record found ", request);
/*     */       }
/*     */       
/*  73 */       ServletUtility.setList(list, request);
/*  74 */       ServletUtility.setPageNo(pageNo, request);
/*  75 */       ServletUtility.setPageSize(pageSize, request);
/*  76 */       ServletUtility.forward(getView(), request, response);
/*  77 */     } catch (ApplicationException e) {
/*  78 */       log.error(e);
/*  79 */       ServletUtility.handleException((Exception)e, request, response);
/*     */       return;
/*     */     } 
/*  82 */     log.debug("HostelListCtl doPOst End");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  91 */     log.debug("HostelListCtl doPost Start");
/*     */     
/*  93 */     List list = null;
/*  94 */     int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
/*  95 */     int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
/*     */     
/*  97 */     pageNo = (pageNo == 0) ? 1 : pageNo;
/*  98 */     pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
/*     */     
/* 100 */     HostelBean bean = (HostelBean)populateBean(request);
/*     */     
/* 102 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/* 105 */     String[] ids = request.getParameterValues("ids");
/*     */     
/* 107 */     HostelModel model = new HostelModel();
/*     */     
/*     */     try {
/* 110 */       if ("Search".equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {
/*     */         
/* 112 */         if ("Search".equalsIgnoreCase(op)) {
/* 113 */           pageNo = 1;
/* 114 */         } else if ("Next".equalsIgnoreCase(op)) {
/* 115 */           pageNo++;
/* 116 */         } else if ("Previous".equalsIgnoreCase(op) && pageNo > 1) {
/* 117 */           pageNo--;
/*     */         } 
/*     */       } else {
/* 120 */         if ("New".equalsIgnoreCase(op)) {
/* 121 */           ServletUtility.redirect("/Hostel-Management/ctl/hostel", request, response); return;
/*     */         } 
/* 123 */         if ("Delete".equalsIgnoreCase(op)) {
/* 124 */           pageNo = 1;
/* 125 */           if (ids != null && ids.length > 0) {
/* 126 */             HostelBean deletebean = new HostelBean(); byte b; int i; String[] arrayOfString;
/* 127 */             for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String id = arrayOfString[b];
/* 128 */               deletebean.setId(DataUtility.getInt(id));
/* 129 */               model.delete(deletebean); b++; }
/*     */             
/* 131 */             ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
/*     */           } else {
/* 133 */             ServletUtility.setErrorMessage("Select at least one record", request);
/*     */           } 
/* 135 */         } else if ("Reset".equalsIgnoreCase(op)) {
/* 136 */           ServletUtility.redirect("/Hostel-Management/ctl/hostelList", request, response);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 141 */       list = model.search(bean, pageNo, pageSize);
/*     */       
/* 143 */       if (list == null || list.size() == 0) {
/* 144 */         ServletUtility.setErrorMessage("No record found ", request);
/*     */       }
/* 146 */       ServletUtility.setList(list, request);
/* 147 */       ServletUtility.setPageNo(pageNo, request);
/* 148 */       ServletUtility.setPageSize(pageSize, request);
/* 149 */       ServletUtility.forward(getView(), request, response);
/*     */     }
/* 151 */     catch (ApplicationException e) {
/* 152 */       log.error(e);
/* 153 */       ServletUtility.handleException((Exception)e, request, response);
/*     */       return;
/*     */     } 
/* 156 */     log.debug("HostelListCtl doGet End");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getView() {
/* 162 */     return "/jsp/HostelListView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\HostelListCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */