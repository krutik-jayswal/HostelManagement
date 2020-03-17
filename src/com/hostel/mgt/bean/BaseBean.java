/*     */ package com.hostel.mgt.bean;
/*     */ 
/*     */ import java.sql.Timestamp;
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
/*     */ public abstract class BaseBean
/*     */   implements DropdownListBean, Comparable<BaseBean>
/*     */ {
/*     */   protected long id;
/*     */   protected String createdBy;
/*     */   protected String modifiedBy;
/*     */   protected Timestamp createdDatetime;
/*     */   protected Timestamp modifiedDatetime;
/*     */   
/*     */   public long getId() {
/*  45 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(long id) {
/*  53 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCreatedBy() {
/*  60 */     return this.createdBy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreatedBy(String createdBy) {
/*  68 */     this.createdBy = createdBy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getModifiedBy() {
/*  75 */     return this.modifiedBy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setModifiedBy(String modifiedBy) {
/*  83 */     this.modifiedBy = modifiedBy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Timestamp getCreatedDatetime() {
/*  91 */     return this.createdDatetime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreatedDatetime(Timestamp createdDatetime) {
/*  99 */     this.createdDatetime = createdDatetime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Timestamp getModifiedDatetime() {
/* 107 */     return this.modifiedDatetime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setModifiedDatetime(Timestamp modifiedDatetime) {
/* 115 */     this.modifiedDatetime = modifiedDatetime;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(BaseBean next) {
/* 120 */     return getValue().compareTo(next.getValue());
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\bean\BaseBean.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */