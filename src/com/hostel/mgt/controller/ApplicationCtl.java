/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.ApplicationBean;
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.UserBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.exception.DuplicateRecordException;
/*     */ import com.hostel.mgt.model.ApplicationModel;
/*     */ import com.hostel.mgt.model.HostelModel;
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
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @WebServlet(name = "ApplicationCtl", urlPatterns = {"/ctl/application"})
/*     */ public class ApplicationCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  34 */   private static Logger log = Logger.getLogger(ApplicationCtl.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preload(HttpServletRequest request) {
/*  42 */     log.debug("ApplicationCtl preload method start");
/*  43 */     HostelModel model = new HostelModel();
/*     */     try {
/*  45 */       List l = model.list();
/*  46 */       request.setAttribute("hostelList", l);
/*  47 */     } catch (ApplicationException e) {
/*  48 */       log.error(e);
/*     */     } 
/*  50 */     log.debug("ApplicationCtl preload method end");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean validate(HttpServletRequest request) {
/*  57 */     log.debug("ApplicationCtl Method validate Started");
/*     */     
/*  59 */     boolean pass = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     if (DataValidator.isNull(request.getParameter("qual"))) {
/*  65 */       request.setAttribute("qual", 
/*  66 */           PropertyReader.getValue("error.require", " Qualification"));
/*  67 */       pass = false;
/*     */     } 
/*     */     
/*  70 */     if (DataValidator.isNull(request.getParameter("address"))) {
/*  71 */       request.setAttribute("address", 
/*  72 */           PropertyReader.getValue("error.require", "address"));
/*  73 */       pass = false;
/*     */     } 
/*     */ 
/*     */     
/*  77 */     if (DataValidator.isNull(request.getParameter("description"))) {
/*  78 */       request.setAttribute("description", 
/*  79 */           PropertyReader.getValue("error.require", "Description"));
/*  80 */       pass = false;
/*     */     } 
/*     */ 
/*     */     
/*  84 */     if ("-----Select-----".equalsIgnoreCase(request.getParameter("hostel"))) {
/*  85 */       request.setAttribute("hostel", 
/*  86 */           PropertyReader.getValue("error.require", "Hostel Name"));
/*  87 */       pass = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  92 */     log.debug("ApplicationCtl Method validate Ended");
/*     */     
/*  94 */     return pass;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/* 100 */     log.debug("ApplicationCtl Method populatebean Started");
/*     */     
/* 102 */     ApplicationBean bean = new ApplicationBean();
/*     */     
/* 104 */     bean.setId(DataUtility.getLong(request.getParameter("id")));
/*     */     
/* 106 */     bean.setHostelId(DataUtility.getLong(request.getParameter("hostel")));
/*     */     
/* 108 */     bean.setQualification(DataUtility.getString(request.getParameter("qual")));
/*     */     
/* 110 */     bean.setAddress(DataUtility.getString(request.getParameter("address")));
/*     */     
/* 112 */     bean.setDescription(DataUtility.getString(request.getParameter("description")));
/*     */     
/* 114 */     populateDTO((BaseBean)bean, request);
/*     */     
/* 116 */     log.debug("ApplicationCtl Method populatebean Ended");
/*     */     
/* 118 */     return (BaseBean)bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 125 */     log.debug("ApplicationCtl Method doGet Started");
/*     */     
/* 127 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/* 130 */     ApplicationModel model = new ApplicationModel();
/*     */     
/* 132 */     long id = DataUtility.getLong(request.getParameter("id"));
/*     */     
/* 134 */     if (id > 0L || op != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 139 */         ApplicationBean bean = model.findByPK(id);
/*     */         
/* 141 */         ServletUtility.setBean((BaseBean)bean, request);
/*     */       }
/* 143 */       catch (ApplicationException e) {
/* 144 */         log.error(e);
/*     */         
/* 146 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         
/*     */         return;
/*     */       } 
/*     */     }
/* 151 */     ServletUtility.forward(getView(), request, response);
/* 152 */     log.debug("ApplicationCtl Method doGet Ended");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 159 */     log.debug("ApplicationCtl Method doPost Started");
/* 160 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */     
/* 162 */     ApplicationModel model = new ApplicationModel();
/* 163 */     long id = DataUtility.getLong(request.getParameter("id"));
/* 164 */     if ("Save".equalsIgnoreCase(op))
/* 165 */     { ApplicationBean bean = (ApplicationBean)populateBean(request);
/* 166 */       HttpSession session = request.getSession();
/* 167 */       UserBean uBean = (UserBean)session.getAttribute("user");
/* 168 */       bean.setUserId(uBean.getId());
/* 169 */       bean.setName(String.valueOf(uBean.getFirstName()) + " " + uBean.getLastName());
/*     */       try {
/* 171 */         if (id > 0L) {
/*     */           
/* 173 */           ServletUtility.setSuccessMessage("Data is successfully Updated", request);
/*     */         } else {
/* 175 */           long pk = model.add(bean);
/*     */           
/* 177 */           ServletUtility.setSuccessMessage("Data is successfully saved", request);
/*     */         }
/*     */       
/*     */       }
/* 181 */       catch (ApplicationException e) {
/* 182 */         log.error(e);
/* 183 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         return;
/* 185 */       } catch (DuplicateRecordException e) {
/* 186 */         ServletUtility.setBean((BaseBean)bean, request);
/* 187 */         ServletUtility.setErrorMessage(e.getMessage(), request);
/*     */       } 
/* 189 */       ServletUtility.forward(getView(), request, response); }
/* 190 */     else { if ("Delete".equalsIgnoreCase(op)) {
/*     */         
/* 192 */         ApplicationBean bean = (ApplicationBean)populateBean(request);
/*     */         try {
/* 194 */           model.delete(bean);
/* 195 */           ServletUtility.redirect("/Hostel-Management/ctl/applicationList", request, 
/* 196 */               response);
/*     */           return;
/* 198 */         } catch (ApplicationException e) {
/* 199 */           log.error(e);
/* 200 */           ServletUtility.handleException((Exception)e, request, response);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 205 */       if ("Cancel".equalsIgnoreCase(op)) {
/* 206 */         ServletUtility.redirect("/Hostel-Management/ctl/applicationList", request, response);
/*     */       }
/* 208 */       else if ("Reset".equalsIgnoreCase(op)) {
/* 209 */         ServletUtility.redirect("/Hostel-Management/ctl/application", request, response);
/*     */         return;
/*     */       }  }
/*     */     
/* 213 */     ServletUtility.forward(getView(), request, response);
/*     */ 
/*     */     
/* 216 */     log.debug("ApplicationCtl Method doPostEnded");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getView() {
/* 222 */     return "/jsp/ApplicationView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\ApplicationCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */