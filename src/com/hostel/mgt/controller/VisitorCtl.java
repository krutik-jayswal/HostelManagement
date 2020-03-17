/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.VisitorBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.exception.DuplicateRecordException;
/*     */ import com.hostel.mgt.model.VisitorModel;
/*     */ import com.hostel.mgt.util.DataUtility;
/*     */ import com.hostel.mgt.util.DataValidator;
/*     */ import com.hostel.mgt.util.PropertyReader;
/*     */ import com.hostel.mgt.util.ServletUtility;
/*     */ import java.io.IOException;
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
/*     */ @WebServlet(name = "VisitorCtl", urlPatterns = {"/ctl/visitor"})
/*     */ public class VisitorCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  32 */   private static Logger log = Logger.getLogger(VisitorCtl.class);
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean validate(HttpServletRequest request) {
/*  37 */     log.debug("VisitorCtl Method validate Started");
/*     */     
/*  39 */     boolean pass = true;
/*     */ 
/*     */     
/*  42 */     if (DataValidator.isNull(request.getParameter("contact"))) {
/*  43 */       request.setAttribute("contact", PropertyReader.getValue("error.require", "Contact No"));
/*  44 */       pass = false;
/*  45 */     } else if (!DataValidator.isPhoneNo(request.getParameter("contact"))) {
/*  46 */       request.setAttribute("contact", PropertyReader.getValue("error.invalid", "Contact No"));
/*  47 */       pass = false;
/*     */     } 
/*     */     
/*  50 */     if (DataValidator.isNull(request.getParameter("name"))) {
/*  51 */       request.setAttribute("name", 
/*  52 */           PropertyReader.getValue("error.require", " Name"));
/*  53 */       pass = false;
/*     */     } 
/*     */     
/*  56 */     if (DataValidator.isNull(request.getParameter("studentName"))) {
/*  57 */       request.setAttribute("studentName", 
/*  58 */           PropertyReader.getValue("error.require", " Student Name"));
/*  59 */       pass = false;
/*     */     } 
/*     */     
/*  62 */     if (DataValidator.isNull(request.getParameter("address"))) {
/*  63 */       request.setAttribute("address", 
/*  64 */           PropertyReader.getValue("error.require", "address"));
/*  65 */       pass = false;
/*     */     } 
/*     */ 
/*     */     
/*  69 */     if (DataValidator.isNull(request.getParameter("purpose"))) {
/*  70 */       request.setAttribute("purpose", 
/*  71 */           PropertyReader.getValue("error.require", "Purpose"));
/*  72 */       pass = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  77 */     if (DataValidator.isNull(request.getParameter("relation"))) {
/*  78 */       request.setAttribute("relation", 
/*  79 */           PropertyReader.getValue("error.require", "Relation"));
/*  80 */       pass = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  85 */     log.debug("VisitorCtl Method validate Ended");
/*     */     
/*  87 */     return pass;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/*  93 */     log.debug("VisitorCtl Method populatebean Started");
/*     */     
/*  95 */     VisitorBean bean = new VisitorBean();
/*     */     
/*  97 */     bean.setId(DataUtility.getLong(request.getParameter("id")));
/*     */     
/*  99 */     bean.setName(DataUtility.getString(request.getParameter("name")));
/*     */     
/* 101 */     bean.setStudentName(DataUtility.getString(request.getParameter("studentName")));
/*     */     
/* 103 */     bean.setContactNo(DataUtility.getString(request.getParameter("contact")));
/*     */     
/* 105 */     bean.setAddress(DataUtility.getString(request.getParameter("address")));
/*     */     
/* 107 */     bean.setRelation(DataUtility.getString(request.getParameter("relation")));
/*     */     
/* 109 */     bean.setPurpose(DataUtility.getString(request.getParameter("purpose")));
/*     */     
/* 111 */     populateDTO((BaseBean)bean, request);
/*     */     
/* 113 */     log.debug("VisitorCtl Method populatebean Ended");
/*     */     
/* 115 */     return (BaseBean)bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 122 */     log.debug("VisitorCtl Method doGet Started");
/*     */     
/* 124 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/* 127 */     VisitorModel model = new VisitorModel();
/*     */     
/* 129 */     long id = DataUtility.getLong(request.getParameter("id"));
/*     */     
/* 131 */     if (id > 0L || op != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 136 */         VisitorBean bean = model.findByPK(id);
/*     */         
/* 138 */         ServletUtility.setBean((BaseBean)bean, request);
/*     */       }
/* 140 */       catch (ApplicationException e) {
/* 141 */         log.error(e);
/*     */         
/* 143 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         
/*     */         return;
/*     */       } 
/*     */     }
/* 148 */     ServletUtility.forward(getView(), request, response);
/* 149 */     log.debug("VisitorCtl Method doGet Ended");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 156 */     log.debug("VisitorCtl Method doPost Started");
/* 157 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */     
/* 159 */     VisitorModel model = new VisitorModel();
/* 160 */     long id = DataUtility.getLong(request.getParameter("id"));
/* 161 */     if ("Save".equalsIgnoreCase(op))
/* 162 */     { VisitorBean bean = (VisitorBean)populateBean(request);
/*     */       
/*     */       try {
/* 165 */         if (id > 0L) {
/* 166 */           model.update(bean);
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
/* 186 */         VisitorBean bean = (VisitorBean)populateBean(request);
/*     */         try {
/* 188 */           model.delete(bean);
/* 189 */           ServletUtility.redirect("/Hostel-Management/ctl/visitorList", request, 
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
/* 200 */         ServletUtility.redirect("/Hostel-Management/ctl/visitorList", request, response);
/*     */       }
/* 202 */       else if ("Reset".equalsIgnoreCase(op)) {
/* 203 */         ServletUtility.redirect("/Hostel-Management/ctl/visitor", request, response);
/*     */         return;
/*     */       }  }
/*     */     
/* 207 */     ServletUtility.forward(getView(), request, response);
/*     */ 
/*     */     
/* 210 */     log.debug("VisitorCtl Method doPostEnded");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getView() {
/* 216 */     return "/jsp/VisitorView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\VisitorCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */