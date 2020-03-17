/*     */ package com.hostel.mgt.bean;
/*     */ 
/*     */ import java.util.Date;
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
/*     */ public class UserBean
/*     */   extends BaseBean
/*     */ {
/*     */   private String firstName;
/*     */   private String lastName;
/*     */   private String login;
/*     */   private String password;
/*     */   private String confirmPassword;
/*     */   private Date dob;
/*     */   private String mobileNo;
/*     */   private long roleId;
/*     */   private String gender;
/*     */   private String image;
/*     */   
/*     */   public String getImage() {
/*  57 */     return this.image;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImage(String image) {
/*  62 */     this.image = image;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFirstName() {
/*  71 */     return this.firstName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFirstName(String firstName) {
/*  80 */     this.firstName = firstName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLastName() {
/*  88 */     return this.lastName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLastName(String lastName) {
/*  97 */     this.lastName = lastName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLogin() {
/* 105 */     return this.login;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLogin(String login) {
/* 114 */     this.login = login;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPassword() {
/* 122 */     return this.password;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPassword(String password) {
/* 131 */     this.password = password;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getConfirmPassword() {
/* 139 */     return this.confirmPassword;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConfirmPassword(String confirmPassword) {
/* 148 */     this.confirmPassword = confirmPassword;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getDob() {
/* 156 */     return this.dob;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDob(Date dob) {
/* 166 */     this.dob = dob;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMobileNo() {
/* 174 */     return this.mobileNo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMobileNo(String mobileNo) {
/* 183 */     this.mobileNo = mobileNo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getRoleId() {
/* 191 */     return this.roleId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRoleId(long roleId) {
/* 200 */     this.roleId = roleId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getGender() {
/* 208 */     return this.gender;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGender(String gender) {
/* 217 */     this.gender = gender;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getKey() {
/* 222 */     return (new StringBuilder(String.valueOf(this.id))).toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValue() {
/* 227 */     return String.valueOf(this.firstName) + " " + this.lastName;
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\bean\UserBean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */