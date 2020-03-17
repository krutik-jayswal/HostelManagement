/*    */ package com.hostel.mgt.bean;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RoleBean
/*    */   extends BaseBean
/*    */ {
/*    */   public static final int ADMIN = 1;
/*    */   public static final int STUDENT = 2;
/*    */   public static final int WARDEN = 3;
/*    */   private String name;
/*    */   private String description;
/*    */   
/*    */   public String getName() {
/* 40 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 48 */     this.name = name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 56 */     return this.description;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDescription(String description) {
/* 64 */     this.description = description;
/*    */   }
/*    */   
/*    */   public String getKey() {
/* 68 */     return (new StringBuilder(String.valueOf(this.id))).toString();
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 72 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\bean\RoleBean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */