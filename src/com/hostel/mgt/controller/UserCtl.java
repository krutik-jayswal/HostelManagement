/*     */ package com.hostel.mgt.controller;
/*     */ 
/*     */ import com.hostel.mgt.bean.BaseBean;
/*     */ import com.hostel.mgt.bean.UserBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.exception.DuplicateRecordException;
/*     */ import com.hostel.mgt.model.RoleModel;
/*     */ import com.hostel.mgt.model.UserModel;
/*     */ import com.hostel.mgt.util.DataUtility;
/*     */ import com.hostel.mgt.util.DataValidator;
/*     */ import com.hostel.mgt.util.PropertyReader;
/*     */ import com.hostel.mgt.util.ServletUtility;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.List;
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
/*     */ @WebServlet(name = "UserCtl", urlPatterns = {"/ctl/user"})
/*     */ @MultipartConfig(maxFileSize = 16177215L)
/*     */ public class UserCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  51 */   private static Logger log = Logger.getLogger(UserCtl.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preload(HttpServletRequest request) {
/*  59 */     log.debug("UserCtl preload method start");
/*  60 */     RoleModel model = new RoleModel();
/*     */     try {
/*  62 */       List l = model.list();
/*  63 */       request.setAttribute("roleList", l);
/*  64 */     } catch (ApplicationException e) {
/*  65 */       log.error(e);
/*     */     } 
/*  67 */     log.debug("UserCtl preload method end");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean validate(HttpServletRequest request) {
/*  78 */     log.debug("UserCtl Method validate Started");
/*     */     
/*  80 */     boolean pass = true;
/*     */     
/*  82 */     String login = request.getParameter("login");
/*  83 */     String dob = request.getParameter("dob");
/*  84 */     if (DataValidator.isNull(request.getParameter("mobile"))) {
/*  85 */       request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile No"));
/*  86 */       pass = false;
/*  87 */     } else if (!DataValidator.isPhoneNo(request.getParameter("mobile"))) {
/*  88 */       request.setAttribute("mobile", PropertyReader.getValue("error.invalid", "Mobile No"));
/*  89 */       pass = false;
/*     */     } 
/*     */     
/*  92 */     if (DataValidator.isNull(request.getParameter("firstName"))) {
/*  93 */       request.setAttribute("firstName", 
/*  94 */           PropertyReader.getValue("error.require", "First Name"));
/*  95 */       pass = false;
/*  96 */     } else if (!DataValidator.isName(request.getParameter("firstName"))) {
/*  97 */       request.setAttribute("firstName", 
/*  98 */           PropertyReader.getValue("error.name", "First Name"));
/*  99 */       pass = false;
/*     */     } 
/*     */     
/* 102 */     if (DataValidator.isNull(request.getParameter("lastName"))) {
/* 103 */       request.setAttribute("lastName", 
/* 104 */           PropertyReader.getValue("error.require", "Last Name"));
/* 105 */       pass = false;
/* 106 */     } else if (!DataValidator.isName(request.getParameter("lastName"))) {
/* 107 */       request.setAttribute("lastName", 
/* 108 */           PropertyReader.getValue("error.name", "LastName"));
/* 109 */       pass = false;
/*     */     } 
/*     */     
/* 112 */     if (DataValidator.isNull(login)) {
/* 113 */       request.setAttribute("login", 
/* 114 */           PropertyReader.getValue("error.require", "Login Id"));
/* 115 */       pass = false;
/* 116 */     } else if (!DataValidator.isEmail(login)) {
/* 117 */       request.setAttribute("login", 
/* 118 */           PropertyReader.getValue("error.email", "Login "));
/* 119 */       pass = false;
/*     */     } 
/*     */     
/* 122 */     if (DataValidator.isNull(request.getParameter("password"))) {
/* 123 */       request.setAttribute("password", 
/* 124 */           PropertyReader.getValue("error.require", "Password"));
/* 125 */       pass = false;
/* 126 */     } else if (!DataValidator.isPassword(request.getParameter("password"))) {
/* 127 */       request.setAttribute("password", 
/* 128 */           PropertyReader.getValue("error.password", "Password"));
/* 129 */       return false;
/*     */     } 
/*     */     
/* 132 */     if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
/* 133 */       request.setAttribute("confirmPassword", PropertyReader.getValue(
/* 134 */             "error.require", "Confirm Password"));
/* 135 */       pass = false;
/*     */     } 
/*     */     
/* 138 */     if ("-----Select-----".equalsIgnoreCase(request.getParameter("gender"))) {
/* 139 */       request.setAttribute("gender", 
/* 140 */           PropertyReader.getValue("error.require", "Gender"));
/* 141 */       pass = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 149 */     if ("-----Select-----".equalsIgnoreCase(request
/* 150 */         .getParameter("roleId"))) {
/* 151 */       request.setAttribute("roleId", 
/* 152 */           PropertyReader.getValue("error.require", "Role Name"));
/* 153 */       pass = false;
/*     */     } 
/* 155 */     if (DataValidator.isNull(dob)) {
/* 156 */       request.setAttribute("dob", 
/* 157 */           PropertyReader.getValue("error.require", "Date Of Birth"));
/* 158 */       pass = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     if (DataValidator.isNull(request.getParameter("gender"))) {
/* 165 */       System.out.println("gender" + request.getParameter("gender"));
/* 166 */       request.setAttribute("error.require", PropertyReader.getValue("Gender"));
/* 167 */       pass = false;
/* 168 */     } else if (DataValidator.isNotNull(request.getParameter("gender")) && 
/* 169 */       "Select".equals(request.getParameter("gender"))) {
/* 170 */       request.setAttribute("gender", 
/* 171 */           PropertyReader.getValue("error.require", "Gender"));
/* 172 */       pass = false;
/*     */     } 
/*     */ 
/*     */     
/* 176 */     if (DataValidator.isNull(request.getParameter("roleId"))) {
/* 177 */       request.setAttribute("roleId", 
/* 178 */           PropertyReader.getValue("error.require", "Role"));
/* 179 */       pass = false;
/*     */     }
/* 181 */     else if (DataValidator.isNotNull(request.getParameter("roleId")) && 
/* 182 */       "Select".equals(request.getParameter("roleId"))) {
/* 183 */       request.setAttribute("roleId", 
/* 184 */           PropertyReader.getValue("error.require", "Role"));
/* 185 */       pass = false;
/*     */     } 
/*     */     
/* 188 */     if (!request.getParameter("password").equals(
/* 189 */         request.getParameter("confirmPassword")) && 
/* 190 */       !"".equals(request.getParameter("confirmPassword"))) {
/*     */       
/* 192 */       request.setAttribute("confirmPassword", PropertyReader.getValue("error.confirmPassword", "Confirm Password"));
/* 193 */       pass = false;
/*     */     } 
/*     */ 
/*     */     
/* 197 */     Part part = null;
/*     */     try {
/* 199 */       part = request.getPart("photo");
/* 200 */     } catch (IOException e) {
/*     */       
/* 202 */       e.printStackTrace();
/* 203 */     } catch (ServletException e) {
/*     */       
/* 205 */       e.printStackTrace();
/*     */     } 
/* 207 */     String imgName = Paths.get(part.getSubmittedFileName(), new String[0]).getFileName().toString();
/*     */     
/* 209 */     if (DataValidator.isNull(imgName)) {
/* 210 */       request.setAttribute("photo", PropertyReader.getValue("error.require", "Profile Picture"));
/* 211 */       pass = false;
/*     */     } 
/*     */     
/* 214 */     log.debug("UserCtl Method validate Ended");
/*     */     
/* 216 */     return pass;
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
/* 227 */     log.debug("UserCtl Method populatebean Started");
/*     */     
/* 229 */     UserBean bean = new UserBean();
/*     */     
/* 231 */     bean.setId(DataUtility.getLong(request.getParameter("id")));
/*     */     
/* 233 */     bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));
/*     */     
/* 235 */     bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
/*     */     
/* 237 */     bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
/*     */     
/* 239 */     bean.setLogin(DataUtility.getString(request.getParameter("login")));
/*     */     
/* 241 */     bean.setPassword(DataUtility.getString(request.getParameter("password")));
/*     */     
/* 243 */     bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
/* 244 */     bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
/*     */     
/* 246 */     bean.setGender(DataUtility.getString(request.getParameter("gender")));
/*     */     
/* 248 */     bean.setDob(DataUtility.getDate(request.getParameter("dob")));
/*     */     
/* 250 */     populateDTO((BaseBean)bean, request);
/*     */     
/* 252 */     log.debug("UserCtl Method populatebean Ended");
/*     */     
/* 254 */     return (BaseBean)bean;
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
/* 265 */     log.debug("UserCtl Method doGet Started");
/*     */     
/* 267 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/* 270 */     UserModel model = new UserModel();
/*     */     
/* 272 */     long id = DataUtility.getLong(request.getParameter("id"));
/*     */     
/* 274 */     if (id > 0L || op != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 279 */         UserBean bean = model.findByPK(id);
/*     */         
/* 281 */         ServletUtility.setBean((BaseBean)bean, request);
/*     */       }
/* 283 */       catch (ApplicationException e) {
/* 284 */         log.error(e);
/*     */         
/* 286 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         
/*     */         return;
/*     */       } 
/*     */     }
/* 291 */     ServletUtility.forward(getView(), request, response);
/* 292 */     log.debug("UserCtl Method doGet Ended");
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
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 306 */     log.debug("UserCtl Method doPost Started");
/* 307 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */     
/* 309 */     UserModel model = new UserModel();
/* 310 */     long id = DataUtility.getLong(request.getParameter("id"));
/* 311 */     if ("Save".equalsIgnoreCase(op))
/* 312 */     { UserBean bean = (UserBean)populateBean(request);
/* 313 */       bean.setImage(SubImage(request, response));
/*     */       
/*     */       try {
/* 316 */         if (id > 0L) {
/* 317 */           model.update(bean);
/*     */           
/* 319 */           ServletUtility.setSuccessMessage("Data is successfully Updated", request);
/*     */         } else {
/* 321 */           long pk = model.add(bean);
/*     */           
/* 323 */           ServletUtility.setSuccessMessage("Data is successfully saved", request);
/*     */         }
/*     */       
/*     */       }
/* 327 */       catch (ApplicationException e) {
/* 328 */         log.error(e);
/* 329 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         return;
/* 331 */       } catch (DuplicateRecordException e) {
/* 332 */         ServletUtility.setBean((BaseBean)bean, request);
/* 333 */         ServletUtility.setErrorMessage("Login id already exists", request);
/*     */       } 
/* 335 */       ServletUtility.forward(getView(), request, response); }
/* 336 */     else { if ("Delete".equalsIgnoreCase(op)) {
/*     */         
/* 338 */         UserBean bean = (UserBean)populateBean(request);
/*     */         try {
/* 340 */           model.delete(bean);
/* 341 */           ServletUtility.redirect("/Hostel-Management/ctl/userList", request, 
/* 342 */               response);
/*     */           return;
/* 344 */         } catch (ApplicationException e) {
/* 345 */           log.error(e);
/* 346 */           ServletUtility.handleException((Exception)e, request, response);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 351 */       if ("Cancel".equalsIgnoreCase(op)) {
/* 352 */         ServletUtility.redirect("/Hostel-Management/ctl/userList", request, response);
/*     */       }
/* 354 */       else if ("Reset".equalsIgnoreCase(op)) {
/* 355 */         ServletUtility.redirect("/Hostel-Management/ctl/user", request, response);
/*     */         
/*     */         return;
/*     */       }  }
/*     */     
/* 360 */     ServletUtility.forward(getView(), request, response);
/*     */ 
/*     */     
/* 363 */     log.debug("UserCtl Method doPostEnded");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String SubImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 370 */     response.setContentType("");
/* 371 */     String savePath = DataUtility.getString(PropertyReader.getValue("imagePath"));
/*     */     
/* 373 */     File fileSaveDir = new File(savePath);
/* 374 */     if (!fileSaveDir.exists()) {
/* 375 */       fileSaveDir.mkdir();
/*     */     }
/*     */     
/* 378 */     Part part = request.getPart("photo");
/* 379 */     String fileName = extractFileName(part);
/* 380 */     part.write(String.valueOf(savePath) + File.separator + fileName);
/* 381 */     String filePath = fileName;
/* 382 */     System.out.println("Path----" + savePath + File.separator + fileName);
/*     */     
/* 384 */     return fileName;
/*     */   }
/*     */ 
/*     */   
/*     */   private String extractFileName(Part part) {
/* 389 */     String contentDisp = part.getHeader("content-disposition");
/* 390 */     String[] items = contentDisp.split(";"); byte b; int i; String[] arrayOfString1;
/* 391 */     for (i = (arrayOfString1 = items).length, b = 0; b < i; ) { String s = arrayOfString1[b];
/* 392 */       if (s.trim().startsWith("filename"))
/* 393 */         return s.substring(s.indexOf("=") + 2, s.length() - 1); 
/*     */       b++; }
/*     */     
/* 396 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getView() {
/* 406 */     return "/jsp/UserView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\UserCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */