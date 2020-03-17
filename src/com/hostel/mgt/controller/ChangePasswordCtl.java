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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @WebServlet(name = "ChangePasswordCtl", urlPatterns = {"/ctl/changePassword"})
/*     */ public class ChangePasswordCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  41 */   private static final Logger log = Logger.getLogger(ChangePasswordCtl.class);
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean validate(HttpServletRequest request) {
/*  55 */     log.debug("ChangePasswordCtl  validate method start");
/*     */     
/*  57 */     boolean pass = true;
/*     */     
/*  59 */     String op = request.getParameter("operation");
/*     */     
/*  61 */     if ("Change My Profile".equalsIgnoreCase(op))
/*     */     {
/*  63 */       return pass;
/*     */     }
/*     */     
/*  66 */     if (DataValidator.isNull(request.getParameter("oldPassword"))) {
/*  67 */       request.setAttribute("oldPassword", PropertyReader.getValue("error.require", "Old Password"));
/*  68 */       pass = false;
/*  69 */     } else if (!DataValidator.isPassword(request.getParameter("oldPassword"))) {
/*  70 */       request.setAttribute("oldPassword", PropertyReader.getValue("error.password", "Old Password"));
/*  71 */       return false;
/*     */     } 
/*  73 */     if (DataValidator.isNull(request.getParameter("newPassword"))) {
/*  74 */       request.setAttribute("newPassword", PropertyReader.getValue("error.require", "New Password"));
/*  75 */       pass = false;
/*  76 */     } else if (!DataValidator.isPassword(request.getParameter("newPassword"))) {
/*  77 */       request.setAttribute("newPassword", PropertyReader.getValue("error.password", "New Password"));
/*  78 */       return false;
/*     */     } 
/*  80 */     if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
/*  81 */       request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "Confirm Password"));
/*  82 */       pass = false;
/*     */     } 
/*  84 */     if (!request.getParameter("newPassword").equals(request.getParameter("confirmPassword")) && 
/*  85 */       !"".equals(request.getParameter("confirmPassword"))) {
/*  86 */       ServletUtility.setErrorMessage("New and confirm passwords not matched", request);
/*     */       
/*  88 */       pass = false;
/*     */     } 
/*     */     
/*  91 */     log.debug("ChangePasswordCtl  validate method end");
/*  92 */     return pass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/* 102 */     log.debug("ChangePasswordCtl  populateBean method start");
/*     */     
/* 104 */     UserBean bean = new UserBean();
/* 105 */     bean.setPassword(DataUtility.getString(request.getParameter("oldPassword")));
/*     */     
/* 107 */     bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
/* 108 */     populateDTO((BaseBean)bean, request);
/* 109 */     log.debug("ChangePasswordCtl  populateBean method end");
/* 110 */     return (BaseBean)bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 118 */     log.debug("ChangePasswordCtl  doGet method start");
/* 119 */     ServletUtility.forward(getView(), request, response);
/* 120 */     log.debug("ChangePasswordCtl  doGet method end");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 128 */     log.debug("ChangePasswordCtl  doPost method start");
/*     */     
/* 130 */     HttpSession session = request.getSession(true);
/*     */     
/* 132 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */     
/* 134 */     UserModel model = new UserModel();
/* 135 */     UserBean bean = (UserBean)populateBean(request);
/*     */     
/* 137 */     UserBean UserBean = (UserBean)session.getAttribute("user");
/*     */     
/* 139 */     String newPassword = request.getParameter("newPassword");
/*     */     
/* 141 */     long id = UserBean.getId();
/*     */     
/* 143 */     if ("Save".equalsIgnoreCase(op)) {
/*     */       try {
/* 145 */         boolean flag = model.changePassword(Long.valueOf(id), bean.getPassword(), newPassword);
/*     */         
/* 147 */         if (flag) {
/*     */           
/* 149 */           bean = model.findByLogin(UserBean.getLogin());
/*     */           
/* 151 */           session.setAttribute("user", bean);
/*     */           
/* 153 */           ServletUtility.setBean((BaseBean)bean, request);
/* 154 */           ServletUtility.setSuccessMessage("Password has been changed Successfully", request);
/*     */         } 
/* 156 */       } catch (ApplicationException e) {
/*     */         
/* 158 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         
/*     */         return;
/* 161 */       } catch (RecordNotFoundException e) {
/* 162 */         ServletUtility.setErrorMessage("Old Password is Invalid", request);
/*     */       } 
/* 164 */     } else if ("Change My Profile".equalsIgnoreCase(op)) {
/* 165 */       ServletUtility.redirect("/Hostel-Management/ctl/myProfile", request, response);
/*     */       
/*     */       return;
/*     */     } 
/* 169 */     ServletUtility.forward("/jsp/ChangePasswordView.jsp", request, response);
/* 170 */     log.debug("ChangePasswordCtl  doPost method end");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getView() {
/* 181 */     return "/jsp/ChangePasswordView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\ChangePasswordCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */