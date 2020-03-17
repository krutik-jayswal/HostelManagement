/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.UserBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.exception.DuplicateRecordException;
/*     */ import com.hostel.mgt.model.UserModel;
/*     */ import com.hostel.mgt.util.DataUtility;
/*     */ import com.hostel.mgt.util.DataValidator;
/*     */ import com.hostel.mgt.util.PropertyReader;
/*     */ import com.hostel.mgt.util.ServletUtility;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Paths;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.annotation.MultipartConfig;
/*     */ import javax.servlet.annotation.WebServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.Part;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @WebServlet(name = "UserRegistrationCtl", urlPatterns = {"/register"})
/*     */ @MultipartConfig(maxFileSize = 16177215L)
/*     */ public class UserRegistrationCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   public static final String OP_SIGN_UP = "SignUp";
/*  49 */   private static Logger log = Logger.getLogger(UserRegistrationCtl.class);
/*     */ 
/*     */ 
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean validate(HttpServletRequest request) {
/*  58 */     log.debug("UserRegistrationCtl Method validate Started");
/*     */     
/*  60 */     boolean pass = true;
/*     */     
/*  62 */     String login = request.getParameter("login");
/*  63 */     String dob = request.getParameter("dob");
/*  64 */     String gender = request.getParameter("gender");
/*     */     
/*  66 */     if (DataValidator.isNull(request.getParameter("firstName"))) {
/*  67 */       request.setAttribute("firstName", 
/*  68 */           PropertyReader.getValue("error.require", "First Name"));
/*  69 */       pass = false;
/*  70 */     } else if (!DataValidator.isName(request.getParameter("firstName"))) {
/*  71 */       request.setAttribute("firstName", 
/*  72 */           PropertyReader.getValue("error.name", "First Name"));
/*  73 */       pass = false;
/*     */     } 
/*  75 */     if (DataValidator.isNull(request.getParameter("lastName"))) {
/*  76 */       request.setAttribute("lastName", 
/*  77 */           PropertyReader.getValue("error.require", "Last Name"));
/*  78 */       pass = false;
/*  79 */     } else if (!DataValidator.isName(request.getParameter("lastName"))) {
/*  80 */       request.setAttribute("lastName", 
/*  81 */           PropertyReader.getValue("error.name", "Last Name"));
/*  82 */       pass = false;
/*     */     } 
/*     */     
/*  85 */     if (DataValidator.isNull(login)) {
/*  86 */       request.setAttribute("login", 
/*  87 */           PropertyReader.getValue("error.require", "Login Id"));
/*  88 */       pass = false;
/*  89 */     } else if (!DataValidator.isEmail(request.getParameter("login"))) {
/*  90 */       request.setAttribute("login", 
/*  91 */           PropertyReader.getValue("error.email", "Login"));
/*  92 */       pass = false;
/*     */     } 
/*  94 */     if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
/*  95 */       request.setAttribute("confirmPassword", PropertyReader.getValue(
/*  96 */             "error.require", "Confirm Password"));
/*  97 */       pass = false;
/*     */     } 
/*  99 */     if ("-----Select-----".equalsIgnoreCase(request.getParameter("gender"))) {
/* 100 */       request.setAttribute("gender", 
/* 101 */           PropertyReader.getValue("error.require", "Gender"));
/* 102 */       pass = false;
/*     */     } 
/*     */     
/* 105 */     if (DataValidator.isNull(dob)) {
/* 106 */       request.setAttribute("dob", 
/* 107 */           PropertyReader.getValue("error.require", "Date of Birth"));
/* 108 */       pass = false;
/* 109 */     } else if (!DataValidator.isDate(dob)) {
/* 110 */       request.setAttribute("dob", "Min Age Must be 17 years");
/* 111 */       pass = false;
/*     */     } 
/*     */     
/* 114 */     if (DataValidator.isNull(request.getParameter("password"))) {
/* 115 */       request.setAttribute("password", 
/* 116 */           PropertyReader.getValue("error.require", "Password"));
/* 117 */       pass = false;
/*     */     } else {
/* 119 */       if (!DataValidator.isPassword(request.getParameter("password"))) {
/* 120 */         request.setAttribute("password", 
/* 121 */             PropertyReader.getValue("error.password", "Password"));
/* 122 */         return false;
/* 123 */       }  if (!DataValidator.isPassword(request.getParameter("password"))) {
/* 124 */         request.setAttribute("password", 
/* 125 */             PropertyReader.getValue("error.password", "Password"));
/* 126 */         return false;
/*     */       } 
/*     */     } 
/* 129 */     if (!request.getParameter("password").equals(
/* 130 */         request.getParameter("confirmPassword")) && 
/* 131 */       !"".equals(request.getParameter("confirmPassword"))) {
/*     */ 
/*     */       
/* 134 */       request.setAttribute("confirmPassword", PropertyReader.getValue("error.confirmPassword", "Confirm Password"));
/* 135 */       pass = false;
/*     */     } 
/*     */     
/* 138 */     if (DataValidator.isNull(request.getParameter("mobile"))) {
/* 139 */       request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile No"));
/* 140 */       pass = false;
/* 141 */     } else if (!DataValidator.isPhoneNo(request.getParameter("mobile"))) {
/* 142 */       request.setAttribute("mobile", PropertyReader.getValue("error.invalid", "Mobile No"));
/* 143 */       pass = false;
/*     */     } 
/*     */     
/* 146 */     Part part = null;
/*     */     try {
/* 148 */       part = request.getPart("photo");
/* 149 */     } catch (IOException e) {
/*     */       
/* 151 */       e.printStackTrace();
/* 152 */       request.setAttribute("photo", PropertyReader.getValue("error.require", "Issue with uploading profile picture"));
/* 153 */     } catch (ServletException e) {
/*     */       
/* 155 */       e.printStackTrace();
/*     */     } 
/* 157 */     String imgName = Paths.get(part.getSubmittedFileName(), new String[0]).getFileName().toString();
/*     */     
/* 159 */     if (DataValidator.isNull(imgName)) {
/* 160 */       request.setAttribute("photo", PropertyReader.getValue("error.require", "Profile Picture"));
/* 161 */       pass = false;
/*     */     } 
/*     */     
/* 164 */     log.debug("UserRegistrationCtl Method validate Ended");
/* 165 */     return pass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BaseBean populateBean(HttpServletRequest request) {
/* 176 */     log.debug("UserRegistrationCtl Method populatebean Started");
/*     */     
/* 178 */     UserBean bean = new UserBean();
/*     */     
/* 180 */     bean.setId(DataUtility.getLong(request.getParameter("id")));
/*     */     
/* 182 */     bean.setRoleId(2L);
/*     */     
/* 184 */     bean.setFirstName(DataUtility.getString(request
/* 185 */           .getParameter("firstName")));
/*     */     
/* 187 */     bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
/*     */     
/* 189 */     bean.setLogin(DataUtility.getString(request.getParameter("login")));
/*     */     
/* 191 */     bean.setPassword(DataUtility.getString(request.getParameter("password")));
/*     */     
/* 193 */     bean.setConfirmPassword(DataUtility.getString(request
/* 194 */           .getParameter("confirmPassword")));
/*     */     
/* 196 */     bean.setGender(DataUtility.getString(request.getParameter("gender")));
/*     */     
/* 198 */     bean.setDob(DataUtility.getDate(request.getParameter("dob")));
/*     */     
/* 200 */     bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
/*     */     
/* 202 */     populateDTO((BaseBean)bean, request);
/*     */     
/* 204 */     log.debug("UserRegistrationCtl Method populatebean Ended");
/*     */     
/* 206 */     return (BaseBean)bean;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 225 */     log.debug("UserRegistrationCtl Method doGet Started");
/* 226 */     ServletUtility.forward(getView(), request, response);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 236 */     System.out.println("in post method");
/* 237 */     log.debug("UserRegistrationCtl Method doPost Started");
/*     */     
/* 239 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */     
/* 241 */     UserModel model = new UserModel();
/*     */     
/* 243 */     long id = DataUtility.getLong(request.getParameter("id"));
/*     */ 
/*     */     
/* 246 */     if ("SignUp".equalsIgnoreCase(op)) {
/*     */       
/* 248 */       UserBean bean = (UserBean)populateBean(request);
/* 249 */       bean.setImage(SubImage(request, response));
/*     */       
/*     */       try {
/* 252 */         long pk = model.registerUser(bean);
/*     */         
/* 254 */         bean.setId(pk);
/*     */         
/* 256 */         request.getSession().setAttribute("UserBean", bean);
/* 257 */         ServletUtility.setSuccessMessage("User Successfully Registered", request);
/* 258 */         ServletUtility.forward("/jsp/UserRegistrationView.jsp", request, response);
/*     */         return;
/* 260 */       } catch (DuplicateRecordException e) {
/* 261 */         log.error(e);
/* 262 */         ServletUtility.setBean((BaseBean)bean, request);
/* 263 */         ServletUtility.setErrorMessage("Login id already exists", 
/* 264 */             request);
/* 265 */         ServletUtility.forward(getView(), request, response);
/* 266 */       } catch (ApplicationException e) {
/* 267 */         ServletUtility.handleException((Exception)e, request, response);
/* 268 */         e.printStackTrace();
/*     */         return;
/*     */       } 
/* 271 */     } else if ("Reset".equalsIgnoreCase(op)) {
/* 272 */       ServletUtility.redirect("/Hostel-Management/register", request, response);
/*     */       return;
/*     */     } 
/* 275 */     log.debug("UserRegistrationCtl Method doPost Ended");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String SubImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 281 */     response.setContentType("");
/* 282 */     String savePath = DataUtility.getString(PropertyReader.getValue("imagePath"));
/*     */     
/* 284 */     File fileSaveDir = new File(savePath);
/* 285 */     if (!fileSaveDir.exists()) {
/* 286 */       fileSaveDir.mkdir();
/*     */     }
/*     */     
/* 289 */     Part part = request.getPart("photo");
/* 290 */     String fileName = extractFileName(part);
/* 291 */     part.write(String.valueOf(savePath) + File.separator + fileName);
/* 292 */     String filePath = fileName;
/* 293 */     System.out.println("Path----" + savePath + File.separator + fileName);
/*     */     
/* 295 */     return fileName;
/*     */   }
/*     */ 
/*     */   
/*     */   private String extractFileName(Part part) {
/* 300 */     String contentDisp = part.getHeader("content-disposition");
/* 301 */     String[] items = contentDisp.split(";"); byte b; int i; String[] arrayOfString1;
/* 302 */     for (i = (arrayOfString1 = items).length, b = 0; b < i; ) { String s = arrayOfString1[b];
/* 303 */       if (s.trim().startsWith("filename"))
/* 304 */         return s.substring(s.indexOf("=") + 2, s.length() - 1); 
/*     */       b++; }
/*     */     
/* 307 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getView() {
/* 316 */     return "/jsp/UserRegistrationView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\UserRegistrationCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */