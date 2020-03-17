/*    */ package com.hostel.mgt.util;
/*    */ 
/*    */ import java.util.ResourceBundle;
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
/*    */ public class PropertyReader
/*    */ {
/* 18 */   private static ResourceBundle rb = ResourceBundle.getBundle("com.hostel.mgt.bundle.system");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getValue(String key) {
/* 29 */     String val = null;
/*    */     
/*    */     try {
/* 32 */       val = rb.getString(key);
/* 33 */     } catch (Exception e) {
/* 34 */       val = key;
/*    */     } 
/*    */     
/* 37 */     return val;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getValue(String key, String param) {
/* 49 */     String msg = getValue(key);
/* 50 */     msg = msg.replace("{0}", param);
/* 51 */     return msg;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getValue(String key, String[] params) {
/* 62 */     String msg = getValue(key);
/* 63 */     for (int i = 0; i < params.length; i++) {
/* 64 */       msg = msg.replace("{" + i + "}", params[i]);
/*    */     }
/* 66 */     return msg;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 76 */     String params = "email";
/* 77 */     System.out.println(getValue("requires", params));
/*    */   }
/*    */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mg\\util\PropertyReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */