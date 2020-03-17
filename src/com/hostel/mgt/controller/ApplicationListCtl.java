/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.ApplicationBean;
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.UserBean;
/*     */ import com.hostel.mgt.bean.WardenBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.model.ApplicationModel;
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
/*     */ import javax.servlet.http.HttpSession;
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
/*     */ @WebServlet(name = "ApplicationListCtl", urlPatterns = {"/ctl/applicationList"})
/*     */ public class ApplicationListCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  35 */   private static Logger log = Logger.getLogger(HostelListCtl.class);
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/*  39 */     log.debug("ApplicationListCtl populateBean method start");
/*  40 */     ApplicationBean bean = new ApplicationBean();
/*     */     
/*  42 */     bean.setName(DataUtility.getString(request.getParameter("name")));
/*     */     
/*  44 */     log.debug("ApplicationListCtl populateBean method end");
/*  45 */     return (BaseBean)bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  52 */     log.debug("ApplicationListCtl doGet Start");
/*  53 */     List list = null;
/*     */     
/*  55 */     int pageNo = 1;
/*     */     
/*  57 */     int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
/*     */     
/*  59 */     ApplicationBean bean = (ApplicationBean)populateBean(request);
/*     */     
/*  61 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/*  64 */     String[] ids = request.getParameterValues("ids");
/*     */     
/*  66 */     ApplicationModel model = new ApplicationModel();
/*     */     
/*     */     try {
/*  69 */       HttpSession session = request.getSession();
/*  70 */       UserBean uBean = (UserBean)session.getAttribute("user");
/*     */       
/*  72 */       WardenModel wModel = new WardenModel();
/*  73 */       WardenBean wBean = wModel.findByUserId(uBean.getId());
/*  74 */       bean.setHostelId(wBean.getHostelId());
/*     */       
/*  76 */       list = model.search(bean, pageNo, pageSize);
/*     */       
/*  78 */       if (list == null || list.size() == 0) {
/*  79 */         ServletUtility.setErrorMessage("No record found ", request);
/*     */       }
/*     */       
/*  82 */       ServletUtility.setList(list, request);
/*  83 */       ServletUtility.setPageNo(pageNo, request);
/*  84 */       ServletUtility.setPageSize(pageSize, request);
/*  85 */       ServletUtility.forward(getView(), request, response);
/*  86 */     } catch (ApplicationException e) {
/*  87 */       log.error(e);
/*  88 */       ServletUtility.handleException((Exception)e, request, response);
/*     */       return;
/*     */     } 
/*  91 */     log.debug("ApplicationListCtl doPOst End");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  98 */     log.debug("ApplicationListCtl doPost Start");
/*     */     
/* 100 */     List list = null;
/* 101 */     int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
/* 102 */     int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
/*     */     
/* 104 */     pageNo = (pageNo == 0) ? 1 : pageNo;
/* 105 */     pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
/*     */     
/* 107 */     ApplicationBean bean = (ApplicationBean)populateBean(request);
/*     */     
/* 109 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/* 112 */     String[] ids = request.getParameterValues("ids");
/*     */     
/* 114 */     ApplicationModel model = new ApplicationModel();
/*     */     
/*     */     try {
/* 117 */       if ("Search".equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {
/*     */         
/* 119 */         if ("Search".equalsIgnoreCase(op)) {
/* 120 */           pageNo = 1;
/* 121 */         } else if ("Next".equalsIgnoreCase(op)) {
/* 122 */           pageNo++;
/* 123 */         } else if ("Previous".equalsIgnoreCase(op) && pageNo > 1) {
/* 124 */           pageNo--;
/*     */         } 
/*     */       } else {
/* 127 */         if ("New".equalsIgnoreCase(op)) {
/* 128 */           ServletUtility.redirect("/Hostel-Management/ctl/hostel", request, response); return;
/*     */         } 
/* 130 */         if ("Delete".equalsIgnoreCase(op)) {
/* 131 */           pageNo = 1;
/* 132 */           if (ids != null && ids.length > 0) {
/* 133 */             ApplicationBean deletebean = new ApplicationBean(); byte b; int i; String[] arrayOfString;
/* 134 */             for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String id = arrayOfString[b];
/* 135 */               deletebean.setId(DataUtility.getInt(id));
/* 136 */               model.delete(deletebean); b++; }
/*     */             
/* 138 */             ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
/*     */           } else {
/* 140 */             ServletUtility.setErrorMessage("Select at least one record", request);
/*     */           } 
/* 142 */         } else if ("Reset".equalsIgnoreCase(op)) {
/* 143 */           ServletUtility.redirect("/Hostel-Management/ctl/applicationList", request, response);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 148 */       HttpSession session = request.getSession();
/* 149 */       UserBean uBean = (UserBean)session.getAttribute("user");
/*     */       
/* 151 */       WardenModel wModel = new WardenModel();
/* 152 */       WardenBean wBean = wModel.findByUserId(uBean.getId());
/* 153 */       bean.setHostelId(wBean.getHostelId());
/*     */       
/* 155 */       list = model.search(bean, pageNo, pageSize);
/*     */       
/* 157 */       if (list == null || list.size() == 0) {
/* 158 */         ServletUtility.setErrorMessage("No record found ", request);
/*     */       }
/* 160 */       ServletUtility.setList(list, request);
/* 161 */       ServletUtility.setPageNo(pageNo, request);
/* 162 */       ServletUtility.setPageSize(pageSize, request);
/* 163 */       ServletUtility.forward(getView(), request, response);
/*     */     }
/* 165 */     catch (ApplicationException e) {
/* 166 */       log.error(e);
/* 167 */       ServletUtility.handleException((Exception)e, request, response);
/*     */       return;
/*     */     } 
/* 170 */     log.debug("ApplicationListCtl doGet End");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getView() {
/* 176 */     return "/jsp/ApplicationListView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\ApplicationListCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */