/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.RoomBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.model.RoomModel;
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
/*     */ @WebServlet(name = "RoomListCtl", urlPatterns = {"/ctl/roomList"})
/*     */ public class RoomListCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  31 */   private static Logger log = Logger.getLogger(RoomListCtl.class);
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/*  35 */     log.debug("RoomListCtl populateBean method start");
/*  36 */     RoomBean bean = new RoomBean();
/*     */     
/*  38 */     bean.setRoomNo(DataUtility.getString(request.getParameter("room")));
/*     */     
/*  40 */     log.debug("RoomListCtl populateBean method end");
/*  41 */     return (BaseBean)bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  48 */     log.debug("RoomListCtl doGet Start");
/*  49 */     List list = null;
/*     */     
/*  51 */     int pageNo = 1;
/*     */     
/*  53 */     int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
/*     */     
/*  55 */     RoomBean bean = (RoomBean)populateBean(request);
/*     */     
/*  57 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/*  60 */     String[] ids = request.getParameterValues("ids");
/*     */     
/*  62 */     RoomModel model = new RoomModel();
/*     */     
/*     */     try {
/*  65 */       list = model.search(bean, pageNo, pageSize);
/*     */       
/*  67 */       if (list == null || list.size() == 0) {
/*  68 */         ServletUtility.setErrorMessage("No record found ", request);
/*     */       }
/*     */       
/*  71 */       ServletUtility.setList(list, request);
/*  72 */       ServletUtility.setPageNo(pageNo, request);
/*  73 */       ServletUtility.setPageSize(pageSize, request);
/*  74 */       ServletUtility.forward(getView(), request, response);
/*  75 */     } catch (ApplicationException e) {
/*  76 */       log.error(e);
/*  77 */       ServletUtility.handleException((Exception)e, request, response);
/*     */       return;
/*     */     } 
/*  80 */     log.debug("RoomListCtl doPOst End");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  87 */     log.debug("RoomListCtl doPost Start");
/*     */     
/*  89 */     List list = null;
/*  90 */     int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
/*  91 */     int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
/*     */     
/*  93 */     pageNo = (pageNo == 0) ? 1 : pageNo;
/*  94 */     pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
/*     */     
/*  96 */     RoomBean bean = (RoomBean)populateBean(request);
/*     */     
/*  98 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/* 101 */     String[] ids = request.getParameterValues("ids");
/*     */     
/* 103 */     RoomModel model = new RoomModel();
/*     */     
/*     */     try {
/* 106 */       if ("Search".equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {
/*     */         
/* 108 */         if ("Search".equalsIgnoreCase(op)) {
/* 109 */           pageNo = 1;
/* 110 */         } else if ("Next".equalsIgnoreCase(op)) {
/* 111 */           pageNo++;
/* 112 */         } else if ("Previous".equalsIgnoreCase(op) && pageNo > 1) {
/* 113 */           pageNo--;
/*     */         } 
/*     */       } else {
/* 116 */         if ("New".equalsIgnoreCase(op)) {
/* 117 */           ServletUtility.redirect("/Hostel-Management/ctl/room", request, response); return;
/*     */         } 
/* 119 */         if ("Delete".equalsIgnoreCase(op)) {
/* 120 */           pageNo = 1;
/* 121 */           if (ids != null && ids.length > 0) {
/* 122 */             RoomBean deletebean = new RoomBean(); byte b; int i; String[] arrayOfString;
/* 123 */             for (i = (arrayOfString = ids).length, b = 0; b < i; ) { String id = arrayOfString[b];
/* 124 */               deletebean.setId(DataUtility.getInt(id));
/* 125 */               model.delete(deletebean); b++; }
/*     */             
/* 127 */             ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
/*     */           } else {
/* 129 */             ServletUtility.setErrorMessage("Select at least one record", request);
/*     */           } 
/* 131 */         } else if ("Reset".equalsIgnoreCase(op)) {
/* 132 */           ServletUtility.redirect("/Hostel-Management/ctl/roomList", request, response);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 137 */       list = model.search(bean, pageNo, pageSize);
/*     */       
/* 139 */       if (list == null || list.size() == 0) {
/* 140 */         ServletUtility.setErrorMessage("No record found ", request);
/*     */       }
/* 142 */       ServletUtility.setList(list, request);
/* 143 */       ServletUtility.setPageNo(pageNo, request);
/* 144 */       ServletUtility.setPageSize(pageSize, request);
/* 145 */       ServletUtility.forward(getView(), request, response);
/*     */     }
/* 147 */     catch (ApplicationException e) {
/* 148 */       log.error(e);
/* 149 */       ServletUtility.handleException((Exception)e, request, response);
/*     */       return;
/*     */     } 
/* 152 */     log.debug("RoomListCtl doGet End");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getView() {
/* 158 */     return "/jsp/RoomListView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\RoomListCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */