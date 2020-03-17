/*     */ package com.hostel.mgt.util;
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
/*     */ public class DataValidator
/*     */ {
/*     */   public static boolean isName(String val) {
/*  24 */     String name = "^[A-Za-z ]*$";
/*  25 */     if (val.matches("^[A-Za-z ]*$")) {
/*  26 */       return true;
/*     */     }
/*  28 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isRollNO(String val) {
/*  34 */     String passregex = "^([0-9]{2}[A-Z]{2}[0-9]{1,})\\S$";
/*     */     
/*  36 */     if (val.matches("^([0-9]{2}[A-Z]{2}[0-9]{1,})\\S$")) {
/*  37 */       return true;
/*     */     }
/*  39 */     return false;
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
/*     */   public static boolean isPassword(String val) {
/*  51 */     String passregex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\S])[A-Za-z0-9\\S]{6,12}$";
/*     */     
/*  53 */     if (val.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\S])[A-Za-z0-9\\S]{6,12}$")) {
/*  54 */       return true;
/*     */     }
/*  56 */     return false;
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
/*     */   public static boolean isPhoneNo(String val) {
/*  68 */     String regex = "^[7-9][0-9]{9}$";
/*  69 */     if (val.matches("^[7-9][0-9]{9}$")) {
/*  70 */       return true;
/*     */     }
/*  72 */     return false;
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
/*     */   public static boolean isNull(String val) {
/*  84 */     if (val == null || val.trim().length() == 0) {
/*  85 */       return true;
/*     */     }
/*  87 */     return false;
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
/*     */   public static boolean isNotNull(String val) {
/*  99 */     return !isNull(val);
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
/*     */   public static boolean isInteger(String val) {
/* 111 */     if (isNotNull(val)) {
/*     */       try {
/* 113 */         int i = Integer.parseInt(val);
/* 114 */         return true;
/* 115 */       } catch (NumberFormatException e) {
/* 116 */         return false;
/*     */       } 
/*     */     }
/*     */     
/* 120 */     return false;
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
/*     */   public static boolean isLong(String val) {
/* 132 */     if (isNotNull(val)) {
/*     */       try {
/* 134 */         long i = Long.parseLong(val);
/* 135 */         return true;
/* 136 */       } catch (NumberFormatException e) {
/* 137 */         return false;
/*     */       } 
/*     */     }
/*     */     
/* 141 */     return false;
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
/*     */   public static boolean isEmail(String val) {
/* 153 */     String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
/* 154 */     if (isNotNull(val)) {
/*     */       try {
/* 156 */         return val.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
/* 157 */       } catch (NumberFormatException e) {
/* 158 */         return false;
/*     */       } 
/*     */     }
/*     */     
/* 162 */     return false;
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
/*     */   public static boolean isDate(String val) {
/* 175 */     Date d = null;
/* 176 */     if (isNotNull(val)) {
/* 177 */       d = DataUtility.getDate(val);
/*     */     }
/* 179 */     return (d != null);
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mg\\util\DataValidator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */