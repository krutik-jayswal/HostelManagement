/*    */ package com.hostel.mgt.bean;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RoomBean
/*    */   extends BaseBean
/*    */ {
/*    */   private String roomNo;
/*    */   private long hostelId;
/*    */   private String description;
/*    */   
/*    */   public long getHostelId() {
/* 13 */     return this.hostelId;
/*    */   }
/*    */   
/*    */   public void setHostelId(long hostelId) {
/* 17 */     this.hostelId = hostelId;
/*    */   }
/*    */   
/*    */   public String getRoomNo() {
/* 21 */     return this.roomNo;
/*    */   }
/*    */   
/*    */   public void setRoomNo(String roomNo) {
/* 25 */     this.roomNo = roomNo;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 29 */     return this.description;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 33 */     this.description = description;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getKey() {
/* 38 */     return (new StringBuilder(String.valueOf(this.id))).toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getValue() {
/* 43 */     return this.roomNo;
/*    */   }
/*    */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\bean\RoomBean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */