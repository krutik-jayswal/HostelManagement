/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.UserBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.exception.RecordNotFoundException;
/*     */ import com.hostel.mgt.model.UserModel;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @WebServlet(name = "ForgetPasswordCtl", urlPatterns = {"/forgetPassword"})
/*     */ public class ForgetPasswordCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  40 */   private static Logger log = Logger.getLogger(ForgetPasswordCtl.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean validate(HttpServletRequest request) {
/*  51 */     log.debug("ForgetPasswordCtl validate  Method Started");
/*     */     
/*  53 */     boolean pass = true;
/*     */     
/*  55 */     String login = request.getParameter("login");
/*     */     
/*  57 */     if (DataValidator.isNull(login)) {
/*  58 */       request.setAttribute("login", 
/*  59 */           PropertyReader.getValue("error.require", "Email Id"));
/*  60 */       pass = false;
/*  61 */     } else if (!DataValidator.isEmail(login)) {
/*  62 */       request.setAttribute("login", 
/*  63 */           PropertyReader.getValue("error.email", "Login "));
/*  64 */       pass = false;
/*     */     } 
/*  66 */     log.debug("ForgetPasswordCtl  validate Method Ended");
/*     */     
/*  68 */     return pass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/*  80 */     log.debug("ForgetPasswordCtl Method populatebean Started");
/*     */     
/*  82 */     UserBean bean = new UserBean();
/*     */     
/*  84 */     bean.setLogin(DataUtility.getString(request.getParameter("login")));
/*     */     
/*  86 */     log.debug("ForgetPasswordCtl Method populatebean Ended");
/*     */     
/*  88 */     return (BaseBean)bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  99 */     log.debug("ForgetPasswordCtl Method doGet Started");
/*     */     
/* 101 */     ServletUtility.forward(getView(), request, response);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 110 */     log.debug("ForgetPasswordCtl Method doPost Started");
/*     */     
/* 112 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */     
/* 114 */     UserBean bean = (UserBean)populateBean(request);
/*     */ 
/*     */     
/* 117 */     UserModel model = new UserModel();
/*     */     
/* 119 */     if ("Go".equalsIgnoreCase(op)) {
/*     */       
/*     */       try {
/* 122 */         model.forgetPassword(bean.getLogin());
/*     */         
/* 124 */         ServletUtility.setSuccessMessage(
/* 125 */             "Password has been sent to your email id.", request);
/* 126 */       } catch (RecordNotFoundException e) {
/* 127 */         ServletUtility.setErrorMessage(e.getMessage(), request);
/* 128 */         log.error(e);
/* 129 */       } catch (ApplicationException e) {
/* 130 */         log.error(e);
/* 131 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         
/*     */         return;
/*     */       } 
/* 135 */       ServletUtility.forward(getView(), request, response);
/*     */     } 
/*     */     
/* 138 */     log.debug("ForgetPasswordCtl Method doPost Ended");
/*     */   }
/*     */   
/*     */   protected String getView() {
/* 142 */     return "/jsp/ForgetPasswordView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\ForgetPasswordCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */