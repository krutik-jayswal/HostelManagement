/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.UserBean;
/*     */ import com.hostel.mgt.bean.WardenBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.exception.DuplicateRecordException;
/*     */ import com.hostel.mgt.model.HostelModel;
/*     */ import com.hostel.mgt.model.UserModel;
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
/*     */ 
/*     */ @WebServlet(name = "WardenCtl", urlPatterns = {"/ctl/warden"})
/*     */ public class WardenCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  36 */   private static Logger log = Logger.getLogger(RoomCtl.class);
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preload(HttpServletRequest request) {
/*  41 */     log.debug("WardenCtl preload method start");
/*  42 */     HostelModel model = new HostelModel();
/*  43 */     UserModel uModel = new UserModel();
/*  44 */     UserBean bean = new UserBean();
/*  45 */     bean.setRoleId(3L);
/*     */     try {
/*  47 */       List l = model.list();
/*  48 */       List l2 = uModel.search(bean);
/*  49 */       request.setAttribute("hostelList", l);
/*  50 */       request.setAttribute("userList", l2);
/*  51 */     } catch (ApplicationException e) {
/*  52 */       log.error(e);
/*     */     } 
/*  54 */     log.debug("WardenCtl preload method end");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean validate(HttpServletRequest request) {
/*  61 */     log.debug("WardenCtl Method validate Started");
/*     */     
/*  63 */     boolean pass = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     if ("-----Select-----".equalsIgnoreCase(request.getParameter("hostelId"))) {
/*  71 */       request.setAttribute("hostelId", 
/*  72 */           PropertyReader.getValue("error.require", "Hostel Name"));
/*  73 */       pass = false;
/*     */     } 
/*     */     
/*  76 */     if ("-----Select-----".equalsIgnoreCase(request.getParameter("userId"))) {
/*  77 */       request.setAttribute("userId", 
/*  78 */           PropertyReader.getValue("error.require", "Warden Name"));
/*  79 */       pass = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  84 */     log.debug("WardenCtl Method validate Ended");
/*     */     
/*  86 */     return pass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/*  93 */     log.debug("WardenCtl Method populatebean Started");
/*     */     
/*  95 */     WardenBean bean = new WardenBean();
/*     */     
/*  97 */     bean.setId(DataUtility.getLong(request.getParameter("id")));
/*     */     
/*  99 */     bean.setUserId(DataUtility.getLong(request.getParameter("userId")));
/*     */     
/* 101 */     bean.setHostelId(DataUtility.getLong(request.getParameter("hostelId")));
/*     */     
/* 103 */     populateDTO((BaseBean)bean, request);
/*     */     
/* 105 */     log.debug("WardenCtl Method populatebean Ended");
/*     */     
/* 107 */     return (BaseBean)bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 114 */     log.debug("WardenCtl Method doGet Started");
/*     */     
/* 116 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/* 119 */     WardenModel model = new WardenModel();
/*     */     
/* 121 */     long id = DataUtility.getLong(request.getParameter("id"));
/*     */     
/* 123 */     if (id > 0L || op != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 128 */         WardenBean bean = model.findByPK(id);
/*     */         
/* 130 */         ServletUtility.setBean((BaseBean)bean, request);
/*     */       }
/* 132 */       catch (ApplicationException e) {
/* 133 */         log.error(e);
/*     */         
/* 135 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         
/*     */         return;
/*     */       } 
/*     */     }
/* 140 */     ServletUtility.forward(getView(), request, response);
/* 141 */     log.debug("WardenCtl Method doGet Ended");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 148 */     log.debug("WardenCtl Method doPost Started");
/* 149 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */     
/* 151 */     WardenModel model = new WardenModel();
/*     */     
/* 153 */     long id = DataUtility.getLong(request.getParameter("id"));
/*     */     
/* 155 */     if ("Save".equalsIgnoreCase(op))
/* 156 */     { WardenBean bean = (WardenBean)populateBean(request);
/*     */       
/*     */       try {
/* 159 */         if (id > 0L) {
/* 160 */           model.update(bean);
/* 161 */           ServletUtility.setSuccessMessage("Data is successfully Updated", request);
/*     */         } else {
/* 163 */           long pk = model.add(bean);
/* 164 */           ServletUtility.setSuccessMessage("Data is successfully saved", request);
/*     */         }
/*     */       
/*     */       }
/* 168 */       catch (ApplicationException e) {
/* 169 */         log.error(e);
/* 170 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         return;
/* 172 */       } catch (DuplicateRecordException e) {
/* 173 */         ServletUtility.setBean((BaseBean)bean, request);
/* 174 */         ServletUtility.setErrorMessage(e.getMessage(), request);
/*     */       } 
/* 176 */       ServletUtility.forward(getView(), request, response); }
/* 177 */     else { if ("Delete".equalsIgnoreCase(op)) {
/*     */         
/* 179 */         WardenBean bean = (WardenBean)populateBean(request);
/*     */         try {
/* 181 */           model.delete(bean);
/* 182 */           ServletUtility.redirect("/Hostel-Management/ctl/wardenList", request, 
/* 183 */               response);
/*     */           return;
/* 185 */         } catch (ApplicationException e) {
/* 186 */           log.error(e);
/* 187 */           ServletUtility.handleException((Exception)e, request, response);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 192 */       if ("Cancel".equalsIgnoreCase(op)) {
/* 193 */         ServletUtility.redirect("/Hostel-Management/ctl/wardenList", request, response);
/*     */       }
/* 195 */       else if ("Reset".equalsIgnoreCase(op)) {
/* 196 */         ServletUtility.redirect("/Hostel-Management/ctl/warden", request, response);
/*     */         return;
/*     */       }  }
/*     */     
/* 200 */     ServletUtility.forward(getView(), request, response);
/*     */ 
/*     */     
/* 203 */     log.debug("WardenCtl Method doPostEnded");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getView() {
/* 209 */     return "/jsp/WardenView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\WardenCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */