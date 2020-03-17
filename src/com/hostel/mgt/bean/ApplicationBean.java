/*    */ package com.hostel.mgt.bean;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ApplicationBean
/*    */   extends BaseBean
/*    */ {
/*    */   private long userId;
/*    */   private String name;
/*    */   private long hostelId;
/*    */   private String hostelName;
/*    */   private String qualification;
/*    */   private String address;
/*    */   private String description;
/*    */   
/*    */   public long getUserId() {
/* 17 */     return this.userId;
/*    */   }
/*    */   
/*    */   public void setUserId(long userId) {
/* 21 */     this.userId = userId;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 25 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 29 */     this.name = name;
/*    */   }
/*    */   
/*    */   public long getHostelId() {
/* 33 */     return this.hostelId;
/*    */   }
/*    */   
/*    */   public void setHostelId(long hostelId) {
/* 37 */     this.hostelId = hostelId;
/*    */   }
/*    */   
/*    */   public String getHostelName() {
/* 41 */     return this.hostelName;
/*    */   }
/*    */   
/*    */   public void setHostelName(String hostelName) {
/* 45 */     this.hostelName = hostelName;
/*    */   }
/*    */   
/*    */   public String getQualification() {
/* 49 */     return this.qualification;
/*    */   }
/*    */   
/*    */   public void setQualification(String qualification) {
/* 53 */     this.qualification = qualification;
/*    */   }
/*    */   
/*    */   public String getAddress() {
/* 57 */     return this.address;
/*    */   }
/*    */   
/*    */   public void setAddress(String address) {
/* 61 */     this.address = address;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 65 */     return this.description;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 69 */     this.description = description;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getKey() {
/* 74 */     return (new StringBuilder(String.valueOf(this.id))).toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getValue() {
/* 79 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\bean\ApplicationBean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */