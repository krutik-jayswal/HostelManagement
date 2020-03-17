/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.RoomBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.exception.DuplicateRecordException;
/*     */ import com.hostel.mgt.model.HostelModel;
/*     */ import com.hostel.mgt.model.RoomModel;
/*     */ import com.hostel.mgt.util.DataUtility;
/*     */ import com.hostel.mgt.util.DataValidator;
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
/*     */ @WebServlet(name = "RoomCtl", urlPatterns = {"/ctl/room"})
/*     */ public class RoomCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  34 */   private static Logger log = Logger.getLogger(RoomCtl.class);
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preload(HttpServletRequest request) {
/*  39 */     log.debug("RoomCtl preload method start");
/*  40 */     HostelModel model = new HostelModel();
/*     */     try {
/*  42 */       List l = model.list();
/*  43 */       request.setAttribute("hostelList", l);
/*  44 */     } catch (ApplicationException e) {
/*  45 */       log.error(e);
/*     */     } 
/*  47 */     log.debug("RoomCtl preload method end");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean validate(HttpServletRequest request) {
/*  54 */     log.debug("RoomCtl Method validate Started");
/*     */     
/*  56 */     boolean pass = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  61 */     if (DataValidator.isNull(request.getParameter("room"))) {
/*  62 */       request.setAttribute("room", 
/*  63 */           PropertyReader.getValue("error.require", " RoomNo"));
/*  64 */       pass = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     if (DataValidator.isNull(request.getParameter("description"))) {
/*  71 */       request.setAttribute("description", 
/*  72 */           PropertyReader.getValue("error.require", "Description"));
/*  73 */       pass = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  78 */     if ("-----Select-----".equalsIgnoreCase(request.getParameter("hostelId"))) {
/*  79 */       request.setAttribute("hostelId", 
/*  80 */           PropertyReader.getValue("error.require", "Hostel Name"));
/*  81 */       pass = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  86 */     log.debug("RoomCtl Method validate Ended");
/*     */     
/*  88 */     return pass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/*  95 */     log.debug("RoomCtl Method populatebean Started");
/*     */     
/*  97 */     RoomBean bean = new RoomBean();
/*     */     
/*  99 */     bean.setId(DataUtility.getLong(request.getParameter("id")));
/*     */     
/* 101 */     bean.setRoomNo(DataUtility.getString(request.getParameter("room")));
/*     */     
/* 103 */     bean.setHostelId(DataUtility.getLong(request.getParameter("hostelId")));
/*     */ 
/*     */     
/* 106 */     bean.setDescription(DataUtility.getString(request.getParameter("description")));
/*     */     
/* 108 */     populateDTO((BaseBean)bean, request);
/*     */     
/* 110 */     log.debug("RoomCtl Method populatebean Ended");
/*     */     
/* 112 */     return (BaseBean)bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 119 */     log.debug("RoomCtl Method doGet Started");
/*     */     
/* 121 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/* 124 */     RoomModel model = new RoomModel();
/*     */     
/* 126 */     long id = DataUtility.getLong(request.getParameter("id"));
/*     */     
/* 128 */     if (id > 0L || op != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 133 */         RoomBean bean = model.findByPK(id);
/*     */         
/* 135 */         ServletUtility.setBean((BaseBean)bean, request);
/*     */       }
/* 137 */       catch (ApplicationException e) {
/* 138 */         log.error(e);
/*     */         
/* 140 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         
/*     */         return;
/*     */       } 
/*     */     }
/* 145 */     ServletUtility.forward(getView(), request, response);
/* 146 */     log.debug("RoomCtl Method doGet Ended");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 153 */     log.debug("RoomCtl Method doPost Started");
/* 154 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */     
/* 156 */     RoomModel model = new RoomModel();
/*     */     
/* 158 */     long id = DataUtility.getLong(request.getParameter("id"));
/*     */     
/* 160 */     if ("Save".equalsIgnoreCase(op))
/* 161 */     { RoomBean bean = (RoomBean)populateBean(request);
/*     */       
/*     */       try {
/* 164 */         if (id > 0L) {
/* 165 */           model.update(bean);
/*     */           
/* 167 */           ServletUtility.setSuccessMessage("Data is successfully Updated", request);
/*     */         } else {
/* 169 */           long pk = model.add(bean);
/*     */           
/* 171 */           ServletUtility.setSuccessMessage("Data is successfully saved", request);
/*     */         }
/*     */       
/*     */       }
/* 175 */       catch (ApplicationException e) {
/* 176 */         log.error(e);
/* 177 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         return;
/* 179 */       } catch (DuplicateRecordException e) {
/* 180 */         ServletUtility.setBean((BaseBean)bean, request);
/* 181 */         ServletUtility.setErrorMessage(e.getMessage(), request);
/*     */       } 
/* 183 */       ServletUtility.forward(getView(), request, response); }
/* 184 */     else { if ("Delete".equalsIgnoreCase(op)) {
/*     */         
/* 186 */         RoomBean bean = (RoomBean)populateBean(request);
/*     */         try {
/* 188 */           model.delete(bean);
/* 189 */           ServletUtility.redirect("/Hostel-Management/ctl/roomList", request, 
/* 190 */               response);
/*     */           return;
/* 192 */         } catch (ApplicationException e) {
/* 193 */           log.error(e);
/* 194 */           ServletUtility.handleException((Exception)e, request, response);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 199 */       if ("Cancel".equalsIgnoreCase(op)) {
/* 200 */         ServletUtility.redirect("/Hostel-Management/ctl/roomList", request, response);
/*     */       }
/* 202 */       else if ("Reset".equalsIgnoreCase(op)) {
/* 203 */         ServletUtility.redirect("/Hostel-Management/ctl/room", request, response);
/*     */         return;
/*     */       }  }
/*     */     
/* 207 */     ServletUtility.forward(getView(), request, response);
/*     */ 
/*     */     
/* 210 */     log.debug("RoomCtl Method doPostEnded");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getView() {
/* 216 */     return "/jsp/RoomView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\RoomCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */