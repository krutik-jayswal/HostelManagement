/*    */ package com.hostel.mgt.bean;
/*    */ 
/*    */ public class HostelBean
/*    */   extends BaseBean
/*    */ {
/*    */   private String name;
/*    */   private String type;
/*    */   private String contact;
/*    */   private String address;
/*    */   private String description;
/*    */   private String fee;
/*    */   
/*    */   public String getFee() {
/* 14 */     return this.fee;
/*    */   }
/*    */   
/*    */   public void setFee(String fee) {
/* 18 */     this.fee = fee;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 22 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 26 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getType() {
/* 30 */     return this.type;
/*    */   }
/*    */   
/*    */   public void setType(String type) {
/* 34 */     this.type = type;
/*    */   }
/*    */   
/*    */   public String getContact() {
/* 38 */     return this.contact;
/*    */   }
/*    */   
/*    */   public void setContact(String contact) {
/* 42 */     this.contact = contact;
/*    */   }
/*    */   
/*    */   public String getAddress() {
/* 46 */     return this.address;
/*    */   }
/*    */   
/*    */   public void setAddress(String address) {
/* 50 */     this.address = address;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 54 */     return this.description;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 58 */     this.description = description;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getKey() {
/* 63 */     return (new StringBuilder(String.valueOf(this.id))).toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getValue() {
/* 68 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\bean\HostelBean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */