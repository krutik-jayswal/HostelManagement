/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.RoleBean;
/*     */ import com.hostel.mgt.bean.UserBean;
/*     */ import com.hostel.mgt.model.RoleModel;
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
/*     */ @WebServlet(name = "LoginCtl", urlPatterns = {"/login"})
/*     */ public class LoginCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String OP_REGISTER = "Register";
/*     */   public static final String OP_SIGN_IN = "SignIn";
/*     */   public static final String OP_SIGN_UP = "SignUp";
/*     */   public static final String OP_LOG_OUT = "logout";
/*  45 */   public static String HIT_URI = null;
/*     */   
/*  47 */   private static Logger log = Logger.getLogger(LoginCtl.class);
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
/*     */   
/*     */   protected boolean validate(HttpServletRequest request) {
/*  67 */     log.debug("LoginCtl Method validate Started");
/*     */     
/*  69 */     boolean pass = true;
/*     */     
/*  71 */     String op = request.getParameter("operation");
/*     */     
/*  73 */     if ("SignUp".equals(op) || "logout".equals(op)) {
/*  74 */       return pass;
/*     */     }
/*     */     
/*  77 */     String login = request.getParameter("login");
/*     */     
/*  79 */     if (DataValidator.isNull(login)) {
/*  80 */       request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
/*  81 */       pass = false;
/*     */     }
/*  83 */     else if (!DataValidator.isEmail(login)) {
/*  84 */       request.setAttribute("login", PropertyReader.getValue("error.email", "Login Id "));
/*  85 */       pass = false;
/*     */     } 
/*  87 */     if (DataValidator.isNull(request.getParameter("password"))) {
/*  88 */       request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
/*  89 */       pass = false;
/*     */     } 
/*  91 */     log.debug("LoginCtl Method validate Ended");
/*  92 */     return pass;
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
/* 104 */     log.debug("LoginCtl Method populateBean Started");
/*     */     
/* 106 */     UserBean bean = new UserBean();
/*     */     
/* 108 */     bean.setId(DataUtility.getLong(request.getParameter("id")));
/*     */     
/* 110 */     bean.setLogin(DataUtility.getString(request.getParameter("login")));
/*     */     
/* 112 */     bean.setPassword(DataUtility.getString(request.getParameter("password")));
/*     */     
/* 114 */     log.debug("LOginCtl Method PopulatedBean End");
/*     */     
/* 116 */     return (BaseBean)bean;
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
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 129 */     log.debug("Method doGet Started");
/*     */     
/* 131 */     HttpSession session = request.getSession(true);
/* 132 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */     
/* 134 */     UserModel model = new UserModel();
/* 135 */     RoleModel role = new RoleModel();
/*     */     
/* 137 */     long id = DataUtility.getLong(request.getParameter("id"));
/*     */     
/* 139 */     if (id > 0L) {
/*     */       
/*     */       try {
/* 142 */         UserBean userBean = model.findByPK(id);
/* 143 */         ServletUtility.setBean((BaseBean)userBean, request);
/*     */       }
/* 145 */       catch (Exception e) {
/* 146 */         log.error(e);
/* 147 */         ServletUtility.handleException(e, request, response);
/*     */         return;
/*     */       } 
/* 150 */     } else if ("logout".equals(op)) {
/* 151 */       session = request.getSession(false);
/* 152 */       session.invalidate();
/* 153 */       ServletUtility.setSuccessMessage("You have been logged out successfully", request);
/*     */       
/* 155 */       ServletUtility.forward("/jsp/LoginView.jsp", request, response);
/*     */       return;
/*     */     } 
/* 158 */     if (session.getAttribute("user") != null) {
/* 159 */       ServletUtility.redirect("/Hostel-Management/welcome", request, response);
/*     */       return;
/*     */     } 
/* 162 */     ServletUtility.forward(getView(), request, response);
/* 163 */     log.debug("Method doGet end");
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
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 176 */     log.debug(" LoginCtl Method doPost Started");
/*     */     
/* 178 */     HttpSession session = request.getSession(true);
/*     */     
/* 180 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */     
/* 182 */     UserModel model = new UserModel();
/* 183 */     RoleModel role = new RoleModel();
/*     */     
/* 185 */     long id = DataUtility.getLong(request.getParameter("id"));
/*     */     
/* 187 */     if ("SignIn".equalsIgnoreCase(op)) {
/* 188 */       UserBean bean = (UserBean)populateBean(request);
/*     */       try {
/* 190 */         bean = model.authenticate(bean.getLogin(), bean.getPassword());
/*     */         
/* 192 */         if (bean != null) {
/* 193 */           session.setAttribute("user", bean);
/* 194 */           session.setMaxInactiveInterval(60000);
/*     */           
/* 196 */           long rollId = bean.getRoleId();
/* 197 */           RoleBean roleBean = role.findByPK(rollId);
/* 198 */           if (roleBean != null) {
/* 199 */             session.setAttribute("role", roleBean.getName());
/*     */           }
/*     */           
/* 202 */           String uri = request.getParameter("uri");
/*     */           
/* 204 */           if (uri == null || "null".equalsIgnoreCase(uri)) {
/* 205 */             ServletUtility.redirect("/Hostel-Management/welcome", request, response);
/*     */             return;
/*     */           } 
/* 208 */           ServletUtility.redirect(uri, request, response);
/*     */           
/*     */           return;
/*     */         } 
/* 212 */         bean = (UserBean)populateBean(request);
/* 213 */         ServletUtility.setBean((BaseBean)bean, request);
/* 214 */         ServletUtility.setErrorMessage("Invalid LoginId And Password", request);
/*     */       
/*     */       }
/* 217 */       catch (Exception exception) {
/* 218 */         log.error(exception);
/* 219 */         System.err.println(exception);
/* 220 */         ServletUtility.handleException(exception, request, response);
/*     */         
/*     */         return;
/*     */       } 
/* 224 */     } else if ("SignUp".equalsIgnoreCase(op)) {
/* 225 */       ServletUtility.redirect("/Hostel-Management/register", request, response);
/*     */       return;
/*     */     } 
/* 228 */     ServletUtility.forward(getView(), request, response);
/* 229 */     log.debug("UserCtl Method doPost Ended");
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
/* 240 */     return "/jsp/LoginView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\LoginCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */