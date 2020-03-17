/*     */ package com.hostel.mgt.util;
/*     */ 
/*     */ import java.sql.Timestamp;
/*     */ import java.text.SimpleDateFormat;
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
/*     */ public class DataUtility
/*     */ {
/*     */   public static final String APP_DATE_FORMAT = "MM/dd/yyyy";
/*  28 */   private static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getString(String val) {
/*  39 */     if (DataValidator.isNotNull(val)) {
/*  40 */       return val.trim();
/*     */     }
/*  42 */     return val;
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
/*     */   public static String getStringData(Object val) {
/*  54 */     if (val != null) {
/*  55 */       return val.toString();
/*     */     }
/*  57 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getInt(String val) {
/*  68 */     if (DataValidator.isInteger(val)) {
/*  69 */       return Integer.parseInt(val);
/*     */     }
/*  71 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getLong(String val) {
/*  82 */     if (DataValidator.isLong(val)) {
/*  83 */       return Long.parseLong(val);
/*     */     }
/*  85 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Date getDate(String val) {
/*  96 */     Date date = null;
/*     */     try {
/*  98 */       date = formatter.parse(val);
/*  99 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 102 */     return date;
/*     */   }
/*     */   
/*     */   public static Date getDate1(String val) {
/* 106 */     Date date = null;
/*     */     
/*     */     try {
/* 109 */       date = formatter.parse(val);
/*     */     }
/* 111 */     catch (Exception exception) {}
/* 112 */     return date;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDateString(Date date) {
/*     */     try {
/* 123 */       if (date != null) {
/* 124 */         return formatter.format(date);
/*     */       }
/*     */       
/* 127 */       return "";
/*     */     }
/* 129 */     catch (Exception e) {
/* 130 */       return "";
/*     */     } 
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
/*     */   public static Date getDate(Date date, int day) {
/* 143 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Timestamp getTimestamp(long l) {
/* 154 */     Timestamp timeStamp = null;
/*     */     try {
/* 156 */       timeStamp = new Timestamp(l);
/* 157 */     } catch (Exception e) {
/* 158 */       return null;
/*     */     } 
/* 160 */     return timeStamp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Timestamp getTimestamp(String cdt) {
/* 171 */     Timestamp timeStamp = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     return timeStamp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getTimestamp(Timestamp tm) {
/*     */     try {
/* 187 */       return tm.getTime();
/* 188 */     } catch (Exception e) {
/* 189 */       return 0L;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Timestamp getCurrentTimestamp() {
/* 200 */     Timestamp timeStamp = null;
/*     */     try {
/* 202 */       timeStamp = new Timestamp((new Date()).getTime());
/* 203 */     } catch (Exception exception) {}
/*     */     
/* 205 */     return timeStamp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 211 */     DataUtility d = new DataUtility();
/*     */     
/* 213 */     Date date = new Date();
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
/* 226 */     System.out.println("formate date :" + getDate("12/09/1991"));
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mg\\util\DataUtility.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */