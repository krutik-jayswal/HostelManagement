/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.FeeBean;
/*     */ import com.hostel.mgt.bean.UserBean;
/*     */ import com.hostel.mgt.bean.WardenBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.model.FeeModel;
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
/*     */ @WebServlet(name = "FeeListCtl", urlPatterns = {"/ctl/feeList"})
/*     */ public class FeeListCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  35 */   private static Logger log = Logger.getLogger(HostelListCtl.class);
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/*  39 */     log.debug("FeeListCtl populateBean method start");
/*  40 */     FeeBean bean = new FeeBean();
/*     */     
/*  42 */     bean.setName(DataUtility.getString(request.getParameter("name")));
/*     */     
/*  44 */     log.debug("FeeListCtl populateBean method end");
/*  45 */     return (BaseBean)bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  52 */     log.debug("FeeListCtl doGet Start");
/*  53 */     List list = null;
/*     */     
/*  55 */     int pageNo = 1;
/*     */     
/*  57 */     int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
/*     */     
/*  59 */     FeeBean bean = (FeeBean)populateBean(request);
/*     */     
/*  61 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/*  64 */     String[] ids = request.getParameterValues("ids");
/*     */     
/*  66 */     FeeModel model = new FeeModel();
/*     */     
/*     */     try {
/*  69 */       HttpSession session = request.getSession();
/*  70 */       UserBean uBean = (UserBean)session.getAttribute("user");
/*  71 */       if (uBean.getRoleId() == 3L) {
/*  72 */         WardenModel wModel = new WardenModel();
/*  73 */         WardenBean wBean = wModel.findByUserId(uBean.getId());
/*  74 */         bean.setHostelId(wBean.getHostelId());
/*  75 */       } else if (uBean.getRoleId() == 2L) {
/*  76 */         bean.setUserId(uBean.getId());
/*     */       } 
/*     */       
/*  79 */       list = model.search(bean, pageNo, pageSize);
/*     */       
/*  81 */       if (list == null || list.size() == 0) {
/*  82 */         ServletUtility.setErrorMessage("No record found ", request);
/*     */       }
/*     */       
/*  85 */       ServletUtility.setList(list, request);
/*  86 */       ServletUtility.setPageNo(pageNo, request);
/*  87 */       ServletUtility.setPageSize(pageSize, request);
/*  88 */       ServletUtility.forward(getView(), request, response);
/*  89 */     } catch (ApplicationException e) {
/*  90 */       log.error(e);
/*  91 */       ServletUtility.handleException((Exception)e, request, response);
/*     */       return;
/*     */     } 
/*  94 */     log.debug("FeeListCtl doPOst End");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 101 */     log.debug("FeeListCtl doPost Start");
/*     */     
/* 103 */     List list = null;
/* 104 */     int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
/* 105 */     int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
/*     */     
/* 107 */     pageNo = (pageNo == 0) ? 1 : pageNo;
/* 108 */     pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
/*     */     
/* 110 */     FeeBean bean = (FeeBean)populateBean(request);
/*     */     
/* 112 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/* 115 */     String[] ids = request.getParameterValues("ids");
/*     */     
/* 117 */     FeeModel model = new FeeModel();
/*     */     
/*     */     try {
/* 120 */       if ("Search".equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {
/*     */         
/* 122 */         if ("Search".equalsIgnoreCase(op)) {
/* 123 */           pageNo = 1;
/* 124 */         } else if ("Next".equalsIgnoreCase(op)) {
/* 125 */           pageNo++;
/* 126 */         } else if ("Previous".equalsIgnoreCase(op) && pageNo > 1) {
/* 127 */           pageNo--;
/*     */         } 
/*     */       } else {
/* 130 */         if ("New".equalsIgnoreCase(op)) {
/* 131 */           ServletUtility.redirect("/Hostel-Management/ctl/applicationList", request, response); return;
/*     */         } 
/* 133 */         if ("Delete".equalsIgnoreCase(op)) {
/* 134 */           pageNo = 1;
/* 135 */           if (ids != null && ids.length > 0) {
/* 136 */             FeeBean deletebean = new FeeBean(); byte b; int i; String[] arrayOfString;
/* 137 */             for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String id = arrayOfString[b];
/* 138 */               deletebean.setId(DataUtility.getInt(id));
/* 139 */               model.delete(deletebean); b++; }
/*     */             
/* 141 */             ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
/*     */           } else {
/* 143 */             ServletUtility.setErrorMessage("Select at least one record", request);
/*     */           } 
/* 145 */         } else if ("Reset".equalsIgnoreCase(op)) {
/* 146 */           ServletUtility.redirect("/Hostel-Management/ctl/feeList", request, response);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 151 */       HttpSession session = request.getSession();
/* 152 */       UserBean uBean = (UserBean)session.getAttribute("user");
/* 153 */       if (uBean.getRoleId() == 3L) {
/* 154 */         WardenModel wModel = new WardenModel();
/* 155 */         WardenBean wBean = wModel.findByUserId(uBean.getId());
/* 156 */         bean.setHostelId(wBean.getHostelId());
/* 157 */       } else if (uBean.getRoleId() == 2L) {
/* 158 */         bean.setUserId(uBean.getId());
/*     */       } 
/*     */       
/* 161 */       list = model.search(bean, pageNo, pageSize);
/*     */       
/* 163 */       if (list == null || list.size() == 0) {
/* 164 */         ServletUtility.setErrorMessage("No record found ", request);
/*     */       }
/* 166 */       ServletUtility.setList(list, request);
/* 167 */       ServletUtility.setPageNo(pageNo, request);
/* 168 */       ServletUtility.setPageSize(pageSize, request);
/* 169 */       ServletUtility.forward(getView(), request, response);
/*     */     }
/* 171 */     catch (ApplicationException e) {
/* 172 */       log.error(e);
/* 173 */       ServletUtility.handleException((Exception)e, request, response);
/*     */       return;
/*     */     } 
/* 176 */     log.debug("FeeListCtl doGet End");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getView() {
/* 182 */     return "/jsp/FeeListView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\FeeListCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */