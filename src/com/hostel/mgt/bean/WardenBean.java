/*    */ package com.hostel.mgt.bean;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WardenBean
/*    */   extends BaseBean
/*    */ {
/*    */   private long userId;
/*    */   private String name;
/*    */   private String login;
/*    */   private long hostelId;
/*    */   
/*    */   public long getUserId() {
/* 15 */     return this.userId;
/*    */   }
/*    */   
/*    */   public void setUserId(long userId) {
/* 19 */     this.userId = userId;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 23 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 27 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getLogin() {
/* 31 */     return this.login;
/*    */   }
/*    */   
/*    */   public void setLogin(String login) {
/* 35 */     this.login = login;
/*    */   }
/*    */   
/*    */   public long getHostelId() {
/* 39 */     return this.hostelId;
/*    */   }
/*    */   
/*    */   public void setHostelId(long hostelId) {
/* 43 */     this.hostelId = hostelId;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getKey() {
/* 48 */     return (new StringBuilder(String.valueOf(this.id))).toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getValue() {
/* 53 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\bean\WardenBean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */