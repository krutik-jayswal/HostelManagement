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
/*     */ 
/*     */ 
/*     */ 
/*     */ @WebServlet(name = "MyProfileCtl", urlPatterns = {"/ctl/myProfile"})
/*     */ public class MyProfileCtl
/*     */   extends BaseCtl
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";
/*     */   public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword";
/*  46 */   private static Logger log = Logger.getLogger(MyProfileCtl.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean validate(HttpServletRequest request) {
/*  56 */     log.debug("MyProfileCtl Method validate Started");
/*     */     
/*  58 */     boolean pass = true;
/*     */     
/*  60 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */     
/*  62 */     if ("ChangePassword".equalsIgnoreCase(op) || op == null) {
/*  63 */       return pass;
/*     */     }
/*     */     
/*  66 */     if ("-----Select-----".equalsIgnoreCase(request.getParameter("gender"))) {
/*  67 */       request.setAttribute("gender", 
/*  68 */           PropertyReader.getValue("error.require", "Gender"));
/*  69 */       pass = false;
/*     */     } 
/*  71 */     if (DataValidator.isNull(request.getParameter("dob"))) {
/*  72 */       request.setAttribute("dob", 
/*  73 */           PropertyReader.getValue("error.require", "Date"));
/*  74 */       pass = false;
/*     */     } 
/*  76 */     if (DataValidator.isNull(request.getParameter("login"))) {
/*  77 */       request.setAttribute("login", PropertyReader.getValue("error.require", "Login ID"));
/*  78 */       pass = false;
/*     */     } 
/*  80 */     if (DataValidator.isNull(request.getParameter("firstName"))) {
/*  81 */       System.out.println("firstName" + request.getParameter("firstName"));
/*  82 */       request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
/*  83 */       pass = false;
/*  84 */     } else if (!DataValidator.isName(request.getParameter("firstName"))) {
/*  85 */       request.setAttribute("firstName", PropertyReader.getValue("error.name", "First Name"));
/*  86 */       pass = false;
/*     */     } 
/*     */     
/*  89 */     if (DataValidator.isNull(request.getParameter("lastName"))) {
/*  90 */       request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
/*  91 */       pass = false;
/*  92 */     } else if (!DataValidator.isName(request.getParameter("lastName"))) {
/*  93 */       request.setAttribute("lastName", PropertyReader.getValue("error.name", "Last Name"));
/*  94 */       pass = false;
/*     */     } 
/*     */     
/*  97 */     if (DataValidator.isNull(request.getParameter("gender"))) {
/*  98 */       System.out.println("gender" + request.getParameter("gender"));
/*  99 */       request.setAttribute("error.require", PropertyReader.getValue("Gender"));
/* 100 */       pass = false;
/* 101 */     } else if (DataValidator.isNotNull(request.getParameter("gender")) && 
/* 102 */       "Select".equals(request.getParameter("gender"))) {
/* 103 */       request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
/* 104 */       pass = false;
/*     */     } 
/*     */     
/* 107 */     if (DataValidator.isNull(request.getParameter("mobileNo"))) {
/* 108 */       request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile No"));
/* 109 */       pass = false;
/* 110 */     } else if (!DataValidator.isPhoneNo(request.getParameter("mobileNo"))) {
/* 111 */       request.setAttribute("mobileNo", PropertyReader.getValue("error.invalid", "Mobile No"));
/* 112 */       pass = false;
/*     */     } 
/* 114 */     if (DataValidator.isNull(request.getParameter("dob"))) {
/* 115 */       request.setAttribute("error.require", PropertyReader.getValue("Date Of Birth"));
/* 116 */       pass = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     log.debug("MyProfileCtl Method validate Ended");
/* 123 */     return pass;
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
/* 135 */     log.debug("MyProfileCtl Method PopulateBean Started ");
/* 136 */     UserBean bean = new UserBean();
/*     */     
/* 138 */     bean.setId(DataUtility.getLong(request.getParameter("id")));
/* 139 */     bean.setLogin(DataUtility.getString(request.getParameter("login")));
/* 140 */     bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
/* 141 */     bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
/* 142 */     bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
/* 143 */     bean.setGender(DataUtility.getString(request.getParameter("gender")));
/* 144 */     bean.setDob(DataUtility.getDate(request.getParameter("dob")));
/*     */     
/* 146 */     populateDTO((BaseBean)bean, request);
/*     */     
/* 148 */     log.debug("MyProfileCtl Method PopulateBean End ");
/* 149 */     return (BaseBean)bean;
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
/* 162 */     log.debug("MyProfileCTl Method doGet Started");
/*     */     
/* 164 */     HttpSession session = request.getSession(true);
/*     */     
/* 166 */     UserBean userBean = (UserBean)session.getAttribute("user");
/*     */     
/* 168 */     long id = userBean.getId();
/*     */     
/* 170 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */ 
/*     */     
/* 173 */     UserModel model = new UserModel();
/*     */ 
/*     */     
/* 176 */     if (id > 0L || op != null) {
/* 177 */       System.out.println("in id>0 condition");
/*     */       
/*     */       try {
/* 180 */         UserBean bean = model.findByPK(id);
/* 181 */         ServletUtility.setBean((BaseBean)bean, request);
/*     */       }
/* 183 */       catch (ApplicationException e) {
/* 184 */         log.error(e);
/* 185 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         return;
/*     */       } 
/*     */     } 
/* 189 */     ServletUtility.forward(getView(), request, response);
/* 190 */     log.debug("MyProfileCtl Method doGet Ended");
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
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 202 */     log.debug("MyprofileCtl Method doPost Started");
/*     */     
/* 204 */     HttpSession session = request.getSession(true);
/*     */     
/* 206 */     UserBean userBean = (UserBean)session.getAttribute("user");
/*     */     
/* 208 */     long id = userBean.getId();
/*     */     
/* 210 */     String op = DataUtility.getString(request.getParameter("operation"));
/*     */     
/* 212 */     UserModel model = new UserModel();
/*     */     
/* 214 */     if ("Save".equalsIgnoreCase(op)) {
/* 215 */       UserBean bean = (UserBean)populateBean(request);
/*     */       try {
/* 217 */         if (id > 0L) {
/* 218 */           userBean.setFirstName(bean.getFirstName());
/* 219 */           userBean.setLastName(bean.getLastName());
/* 220 */           userBean.setGender(bean.getGender());
/* 221 */           userBean.setMobileNo(bean.getMobileNo());
/* 222 */           userBean.setDob(bean.getDob());
/*     */           
/* 224 */           model.update(userBean);
/*     */           
/* 226 */           ServletUtility.setBean((BaseBean)bean, request);
/* 227 */           ServletUtility.setSuccessMessage("Profile has been updated Successfully. ", request);
/*     */         }
/*     */       
/* 230 */       } catch (ApplicationException e) {
/* 231 */         log.error(e);
/* 232 */         ServletUtility.handleException((Exception)e, request, response);
/*     */         return;
/* 234 */       } catch (DuplicateRecordException e) {
/* 235 */         ServletUtility.setBean((BaseBean)bean, request);
/* 236 */         ServletUtility.setErrorMessage("Login id already exists", request);
/*     */       }
/*     */     
/* 239 */     } else if ("ChangePassword".equalsIgnoreCase(op)) {
/* 240 */       ServletUtility.redirect("/Hostel-Management/ctl/changePassword", request, response);
/*     */       return;
/*     */     } 
/* 243 */     ServletUtility.forward(getView(), request, response);
/* 244 */     log.debug("MyProfileCtl doPost method end");
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
/* 255 */     return "/jsp/MyProfileView.jsp";
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\controller\MyProfileCtl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */