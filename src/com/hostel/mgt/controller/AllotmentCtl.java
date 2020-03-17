/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.AllotmentBean;
/*     */ import com.hostel.mgt.bean.ApplicationBean;
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.RoomBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.exception.DuplicateRecordException;
/*     */ import com.hostel.mgt.model.AllotmentModel;
/*     */ import com.hostel.mgt.model.ApplicationModel;
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
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @WebServlet(name = "AllotmentCtl", urlPatterns = {"/ctl/allotment"})
/*     */ public class AllotmentCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  35 */   private static Logger log = Logger.getLogger(AllotmentCtl.class);
/*     */ 
/*     */   
/*     */   protected void preload(HttpServletRequest request) {
/*  39 */     log.debug("AllotmentCtl preload method start");
/*  40 */     RoomModel model = new RoomModel();
/*  41 */     ApplicationModel aModel = new ApplicationModel();
/*  42 */     ApplicationBean aBean = null;
/*  43 */     long userId = DataUtility.getLong(request.getParameter("uId"));
/*  44 */     System.out.println("application Id  " + userId);
/*     */     
/*     */     try {
/*  47 */       if (userId > 0L) {
/*  48 */         aBean = aModel.findByPK(userId);
/*     */       }
/*  50 */       RoomBean rBean = new RoomBean();
/*     */       
/*  52 */       rBean.setHostelId(aBean.getHostelId());
/*     */       
/*  54 */       List l = model.search(rBean);
/*  55 */       request.setAttribute("roomList", l);
/*  56 */       HttpSession session = request.getSession();
/*  57 */       session.setAttribute("allotmentId", Long.valueOf(userId));
/*  58 */     } catch (ApplicationException e) {
/*  59 */       log.error(e);
/*     */     } 
/*  61 */     log.debug("AllotmentCtl preload method end");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean validate(HttpServletRequest request) {
/*  68 */     log.debug("AllotmentCtl Method validate Started");
/*     */     
/*  70 */     boolean pass = true;
/*     */     
/*  72 */     if ("-----Select-----".equalsIgnoreCase(request.getParameter("roomId"))) {
/*  73 */       request.setAttribute("roomId", 
/*  74 */           PropertyReader.getValue("error.require", "Room No"));
/*  75 */       pass = false;
/*     */     } 
/*     */     
/*  78 */     log.debug("AllotmentCtl Method validate Ended");
/*     */     
/*  80 */     return pass;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/*  86 */     log.debug("AllotmentCtl Method populatebean Started");
/*     */     
/*  88 */     AllotmentBean bean = new AllotmentBean();
/*     */     
/*  90 */     bean.setId(DataUtility.getLong(request.getParameter("id")));
/*     */     
/*  92 */     bean.setRoomId(DataUtility.getLong(request.getParameter("roomId")));
/*     */     
/*  94 */     populateDTO((BaseBean)bean, request);
/*     */     
/*  96 */     log.debug("AllotmentCtl Method populatebean Ended");
/*     */     
/*  98 */     return (BaseBean)bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 105 */     log.debug("AllotmentCtl Method doGet Started");
/*     */     
/* 107 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/* 110 */     AllotmentModel model = new AllotmentModel();
/*     */     
/* 112 */     long id = DataUtility.getLong(request.getParameter("id"));
/*     */     
/* 114 */     if (id > 0L || op != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 119 */         AllotmentBean bean = model.findByPk(id);
/*     */         
/* 121 */         ServletUtility.setBean((BaseBean)bean, request);
/*     */       }
/* 123 */       catch (ApplicationException e) {
/* 124 */         log.error(e);
/*     */         
/* 126 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         
/*     */         return;
/*     */       } 
/*     */     }
/* 131 */     ServletUtility.forward(getView(), request, response);
/* 132 */     log.debug("AllotmentCtl Method doGet Ended");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 139 */     log.debug("AllotmentCtl Method doPost Started");
/* 140 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */     
/* 142 */     AllotmentModel model = new AllotmentModel();
/*     */     
/* 144 */     long id = DataUtility.getLong(request.getParameter("id"));
/*     */     
/* 146 */     if ("Save".equalsIgnoreCase(op))
/* 147 */     { AllotmentBean bean = (AllotmentBean)populateBean(request);
/*     */       
/* 149 */       HttpSession session = request.getSession();
/* 150 */       long aId = ((Long)session.getAttribute("allotmentId")).longValue();
/* 151 */       ApplicationModel aModel = new ApplicationModel();
/* 152 */       ApplicationBean aBean = null;
/*     */ 
/*     */       
/*     */       try {
/* 156 */         if (aId > 0L) {
/* 157 */           aBean = aModel.findByPK(aId);
/*     */         }
/*     */         
/* 160 */         bean.setUserId(aBean.getUserId());
/* 161 */         bean.setHostelId(aBean.getHostelId());
/*     */         
/* 163 */         if (id > 0L) {
/* 164 */           model.update(bean);
/* 165 */           ServletUtility.setSuccessMessage("Data is successfully Updated", request);
/*     */         } else {
/* 167 */           long pk = model.add(bean);
/* 168 */           ServletUtility.setSuccessMessage("Data is successfully saved", request);
/*     */         }
/*     */       
/*     */       }
/* 172 */       catch (ApplicationException e) {
/* 173 */         log.error(e);
/* 174 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         return;
/* 176 */       } catch (DuplicateRecordException e) {
/* 177 */         ServletUtility.setBean((BaseBean)bean, request);
/* 178 */         ServletUtility.setErrorMessage(e.getMessage(), request);
/*     */       } 
/* 180 */       ServletUtility.forward(getView(), request, response); }
/* 181 */     else { if ("Delete".equalsIgnoreCase(op)) {
/*     */         
/* 183 */         AllotmentBean bean = (AllotmentBean)populateBean(request);
/*     */         try {
/* 185 */           model.delete(bean);
/* 186 */           ServletUtility.redirect("/Hostel-Management/ctl/allotmentList", request, 
/* 187 */               response);
/*     */           return;
/* 189 */         } catch (ApplicationException e) {
/* 190 */           log.error(e);
/* 191 */           ServletUtility.handleException((Exception)e, request, response);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 196 */       if ("Cancel".equalsIgnoreCase(op)) {
/* 197 */         ServletUtility.redirect("/Hostel-Management/ctl/allotmentList", request, response);
/*     */       }
/* 199 */       else if ("Reset".equalsIgnoreCase(op)) {
/* 200 */         ServletUtility.redirect("/Hostel-Management/ctl/allotment", request, response);
/*     */         return;
/*     */       }  }
/*     */     
/* 204 */     ServletUtility.forward(getView(), request, response);
/*     */ 
/*     */     
/* 207 */     log.debug("AllotmentCtl Method doPostEnded");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getView() {
/* 213 */     return "/jsp/AllotmentView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\AllotmentCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */