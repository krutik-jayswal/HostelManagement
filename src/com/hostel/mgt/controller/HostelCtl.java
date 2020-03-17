/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.HostelBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.exception.DuplicateRecordException;
/*     */ import com.hostel.mgt.model.HostelModel;
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
/*     */ @WebServlet(name = "HostelCtl", urlPatterns = {"/ctl/hostel"})
/*     */ public class HostelCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  31 */   private static Logger log = Logger.getLogger(HostelCtl.class);
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean validate(HttpServletRequest request) {
/*  36 */     log.debug("HostelCtl Method validate Started");
/*     */     
/*  38 */     boolean pass = true;
/*     */ 
/*     */     
/*  41 */     if (DataValidator.isNull(request.getParameter("contact"))) {
/*  42 */       request.setAttribute("contact", PropertyReader.getValue("error.require", "Contact No"));
/*  43 */       pass = false;
/*  44 */     } else if (!DataValidator.isPhoneNo(request.getParameter("contact"))) {
/*  45 */       request.setAttribute("contact", PropertyReader.getValue("error.invalid", "Contact No"));
/*  46 */       pass = false;
/*     */     } 
/*     */     
/*  49 */     if (DataValidator.isNull(request.getParameter("name"))) {
/*  50 */       request.setAttribute("name", 
/*  51 */           PropertyReader.getValue("error.require", " Name"));
/*  52 */       pass = false;
/*     */     } 
/*     */     
/*  55 */     if (DataValidator.isNull(request.getParameter("address"))) {
/*  56 */       request.setAttribute("address", 
/*  57 */           PropertyReader.getValue("error.require", "address"));
/*  58 */       pass = false;
/*     */     } 
/*     */ 
/*     */     
/*  62 */     if (DataValidator.isNull(request.getParameter("description"))) {
/*  63 */       request.setAttribute("description", 
/*  64 */           PropertyReader.getValue("error.require", "Description"));
/*  65 */       pass = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  70 */     if ("-----Select-----".equalsIgnoreCase(request.getParameter("type"))) {
/*  71 */       request.setAttribute("type", 
/*  72 */           PropertyReader.getValue("error.require", "Type"));
/*  73 */       pass = false;
/*     */     } 
/*     */     
/*  76 */     if (DataValidator.isNull(request.getParameter("fee"))) {
/*  77 */       request.setAttribute("fee", 
/*  78 */           PropertyReader.getValue("error.require", "Fee"));
/*  79 */       pass = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  84 */     log.debug("Hostel Method validate Ended");
/*     */     
/*  86 */     return pass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/*  93 */     log.debug("HotelCtl Method populatebean Started");
/*     */     
/*  95 */     HostelBean bean = new HostelBean();
/*     */     
/*  97 */     bean.setId(DataUtility.getLong(request.getParameter("id")));
/*     */     
/*  99 */     bean.setName(DataUtility.getString(request.getParameter("name")));
/*     */     
/* 101 */     bean.setType(DataUtility.getString(request.getParameter("type")));
/*     */     
/* 103 */     bean.setContact(DataUtility.getString(request.getParameter("contact")));
/*     */     
/* 105 */     bean.setAddress(DataUtility.getString(request.getParameter("address")));
/*     */     
/* 107 */     bean.setDescription(DataUtility.getString(request.getParameter("description")));
/*     */     
/* 109 */     bean.setFee(DataUtility.getString(request.getParameter("fee")));
/*     */     
/* 111 */     populateDTO((BaseBean)bean, request);
/*     */     
/* 113 */     log.debug("HostelCtl Method populatebean Ended");
/*     */     
/* 115 */     return (BaseBean)bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 122 */     log.debug("HostelCtl Method doGet Started");
/*     */     
/* 124 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/* 127 */     HostelModel model = new HostelModel();
/*     */     
/* 129 */     long id = DataUtility.getLong(request.getParameter("id"));
/*     */     
/* 131 */     if (id > 0L || op != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 136 */         HostelBean bean = model.findByPK(id);
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
/* 149 */     log.debug("HostelCtl Method doGet Ended");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 156 */     log.debug("UserCtl Method doPost Started");
/* 157 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */     
/* 159 */     HostelModel model = new HostelModel();
/* 160 */     long id = DataUtility.getLong(request.getParameter("id"));
/* 161 */     if ("Save".equalsIgnoreCase(op))
/* 162 */     { HostelBean bean = (HostelBean)populateBean(request);
/*     */       
/*     */       try {
/* 165 */         if (id > 0L) {
/* 166 */           model.update(bean);
/*     */           
/* 168 */           ServletUtility.setSuccessMessage("Data is successfully Updated", request);
/*     */         } else {
/* 170 */           long pk = model.add(bean);
/*     */           
/* 172 */           ServletUtility.setSuccessMessage("Data is successfully saved", request);
/*     */         }
/*     */       
/*     */       }
/* 176 */       catch (ApplicationException e) {
/* 177 */         log.error(e);
/* 178 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         return;
/* 180 */       } catch (DuplicateRecordException e) {
/* 181 */         ServletUtility.setBean((BaseBean)bean, request);
/* 182 */         ServletUtility.setErrorMessage(e.getMessage(), request);
/*     */       } 
/* 184 */       ServletUtility.forward(getView(), request, response); }
/* 185 */     else { if ("Delete".equalsIgnoreCase(op)) {
/*     */         
/* 187 */         HostelBean bean = (HostelBean)populateBean(request);
/*     */         try {
/* 189 */           model.delete(bean);
/* 190 */           ServletUtility.redirect("/Hostel-Management/ctl/hostelList", request, 
/* 191 */               response);
/*     */           return;
/* 193 */         } catch (ApplicationException e) {
/* 194 */           log.error(e);
/* 195 */           ServletUtility.handleException((Exception)e, request, response);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 200 */       if ("Cancel".equalsIgnoreCase(op)) {
/* 201 */         ServletUtility.redirect("/Hostel-Management/ctl/hostelList", request, response);
/*     */       }
/* 203 */       else if ("Reset".equalsIgnoreCase(op)) {
/* 204 */         ServletUtility.redirect("/Hostel-Management/ctl/hostel", request, response);
/*     */         return;
/*     */       }  }
/*     */     
/* 208 */     ServletUtility.forward(getView(), request, response);
/*     */ 
/*     */     
/* 211 */     log.debug("HostelCtl Method doPostEnded");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getView() {
/* 217 */     return "/jsp/HostelView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\HostelCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */