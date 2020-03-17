/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.AllotmentBean;
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.FeeBean;
/*     */ import com.hostel.mgt.bean.HostelBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.exception.DuplicateRecordException;
/*     */ import com.hostel.mgt.model.AllotmentModel;
/*     */ import com.hostel.mgt.model.FeeModel;
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
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @WebServlet(name = "FeeCtl", urlPatterns = {"/ctl/fee"})
/*     */ public class FeeCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  34 */   private static Logger log = Logger.getLogger(FeeCtl.class);
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean validate(HttpServletRequest request) {
/*  39 */     log.debug("FeeCtl Method validate Started");
/*     */     
/*  41 */     boolean pass = true;
/*     */ 
/*     */ 
/*     */     
/*  45 */     if (DataValidator.isNull(request.getParameter("pay"))) {
/*  46 */       request.setAttribute("pay", 
/*  47 */           PropertyReader.getValue("error.require", " Pay Amount"));
/*  48 */       pass = false;
/*     */     } 
/*     */ 
/*     */     
/*  52 */     log.debug("FeeCtl Method validate Ended");
/*     */     
/*  54 */     return pass;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/*  60 */     log.debug("FeeCtl Method populatebean Started");
/*     */     
/*  62 */     FeeBean bean = new FeeBean();
/*     */     
/*  64 */     bean.setId(DataUtility.getLong(request.getParameter("id")));
/*     */     
/*  66 */     bean.setAllotmentId(DataUtility.getLong(request.getParameter("aId")));
/*     */     
/*  68 */     System.out.println("Application Id in Poulate Bean" + bean.getAllotmentId());
/*     */     
/*  70 */     HttpSession session = request.getSession();
/*  71 */     session.setAttribute("aId", Long.valueOf(bean.getAllotmentId()));
/*     */ 
/*     */     
/*  74 */     bean.setPay(DataUtility.getString(request.getParameter("pay")));
/*     */     
/*  76 */     populateDTO((BaseBean)bean, request);
/*     */     
/*  78 */     log.debug("FeeCtl Method populatebean Ended");
/*     */     
/*  80 */     return (BaseBean)bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  87 */     log.debug("FeeCtl Method doGet Started");
/*     */     
/*  89 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/*  92 */     Long aId = Long.valueOf(DataUtility.getLong(request.getParameter("aId")));
/*     */     
/*  94 */     HttpSession session = request.getSession();
/*  95 */     session.setAttribute("aId", aId);
/*     */     
/*  97 */     FeeModel model = new FeeModel();
/*     */     
/*  99 */     long id = DataUtility.getLong(request.getParameter("id"));
/*     */     
/* 101 */     if (id > 0L || op != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 106 */         FeeBean bean = model.findByPk(id);
/*     */         
/* 108 */         ServletUtility.setBean((BaseBean)bean, request);
/*     */       }
/* 110 */       catch (ApplicationException e) {
/* 111 */         log.error(e);
/*     */         
/* 113 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         return;
/*     */       } 
/*     */     }
/* 117 */     ServletUtility.forward(getView(), request, response);
/* 118 */     log.debug("FeeCtl Method doGet Ended");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 125 */     log.debug("FeeCtl Method doPost Started");
/* 126 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */     
/* 128 */     FeeModel model = new FeeModel();
/* 129 */     long id = DataUtility.getLong(request.getParameter("id"));
/* 130 */     if ("Save".equalsIgnoreCase(op))
/* 131 */     { FeeBean bean = (FeeBean)populateBean(request);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 139 */         System.out.println("Allotead id in  fee Pstt" + bean.getAllotmentId());
/* 140 */         AllotmentModel aModel = new AllotmentModel();
/* 141 */         AllotmentBean aBean = aModel.findByPk(bean.getAllotmentId());
/*     */         
/* 143 */         System.out.println("Hostel Id in fee post" + aBean.getHostelId());
/*     */         
/* 145 */         HostelModel hModel = new HostelModel();
/* 146 */         HostelBean hBean = hModel.findByPK(aBean.getHostelId());
/*     */         
/* 148 */         FeeBean fBean = model.findByUserIdAndHostelIdAndRoomId(aBean.getUserId(), aBean.getHostelId(), aBean.getRoomId());
/* 149 */         if (fBean != null) {
/*     */           
/* 151 */           fBean.setPay(bean.getPay());
/*     */           
/* 153 */           long totalfee = DataUtility.getLong(fBean.getTotalfee());
/* 154 */           long pay = DataUtility.getLong(fBean.getPay());
/* 155 */           long paid = DataUtility.getLong(fBean.getPaidfee());
/* 156 */           long rem = totalfee - paid + pay;
/*     */           
/* 158 */           paid += pay;
/* 159 */           fBean.setPaidfee(String.valueOf(paid));
/*     */           
/* 161 */           fBean.setRemainingfee(String.valueOf(rem));
/* 162 */           model.update(fBean);
/*     */         } else {
/*     */           
/* 165 */           bean.setUserId(aBean.getUserId());
/* 166 */           bean.setHostelId(aBean.getHostelId());
/* 167 */           bean.setRoomId(aBean.getRoomId());
/*     */           
/* 169 */           long totalfee = DataUtility.getLong(hBean.getFee());
/* 170 */           long pay = DataUtility.getLong(bean.getPay());
/* 171 */           long rem = totalfee - pay;
/*     */           
/* 173 */           bean.setTotalfee(hBean.getFee());
/* 174 */           bean.setPay(bean.getPay());
/* 175 */           bean.setPaidfee(bean.getPay());
/* 176 */           bean.setRemainingfee(String.valueOf(rem));
/*     */           
/* 178 */           long l1 = model.add(bean);
/*     */         } 
/* 180 */         ServletUtility.setSuccessMessage("Data is successfully saved", request);
/*     */ 
/*     */       
/*     */       }
/* 184 */       catch (ApplicationException e) {
/* 185 */         log.error(e);
/* 186 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         return;
/* 188 */       } catch (DuplicateRecordException e) {
/* 189 */         ServletUtility.setBean((BaseBean)bean, request);
/* 190 */         ServletUtility.setErrorMessage(e.getMessage(), request);
/*     */       } 
/* 192 */       ServletUtility.forward(getView(), request, response); }
/* 193 */     else { if ("Delete".equalsIgnoreCase(op)) {
/*     */         
/* 195 */         FeeBean bean = (FeeBean)populateBean(request);
/*     */         try {
/* 197 */           model.delete(bean);
/* 198 */           ServletUtility.redirect("/Hostel-Management/ctl/feeList", request, 
/* 199 */               response);
/*     */           return;
/* 201 */         } catch (ApplicationException e) {
/* 202 */           log.error(e);
/* 203 */           ServletUtility.handleException((Exception)e, request, response);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 208 */       if ("Cancel".equalsIgnoreCase(op)) {
/* 209 */         ServletUtility.redirect("/Hostel-Management/ctl/feeList", request, response);
/*     */       }
/* 211 */       else if ("Reset".equalsIgnoreCase(op)) {
/* 212 */         ServletUtility.redirect("/Hostel-Management/ctl/allotmentList", request, response);
/*     */         return;
/*     */       }  }
/*     */     
/* 216 */     ServletUtility.forward(getView(), request, response);
/*     */ 
/*     */     
/* 219 */     log.debug("FeeCtl Method doPostEnded");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getView() {
/* 225 */     return "/jsp/FeeView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\FeeCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */