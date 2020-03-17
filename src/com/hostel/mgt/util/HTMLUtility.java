/*    */ package com.hostel.mgt.util;
/*    */ 
/*    */ import com.hostel.mgt.bean.DropdownListBean;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Set;
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
/*    */ public class HTMLUtility
/*    */ {
/*    */   public static String getList(String name, String selectedVal, HashMap<String, String> map) {
/* 32 */     StringBuffer sb = new StringBuffer("<select class='form-control' name='" + name + "'>");
/*    */     
/* 34 */     Set<String> keys = map.keySet();
/* 35 */     String val = null;
/* 36 */     String select = "-----Select-----";
/* 37 */     sb.append("<option selected value='-----Select-----'>-----Select-----</option>");
/* 38 */     for (String key : keys) {
/* 39 */       val = map.get(key);
/* 40 */       if (key.trim().equals(selectedVal)) {
/*    */         
/* 42 */         sb.append("<option selected value='" + key + "'>" + val + "</option>"); continue;
/*    */       } 
/* 44 */       sb.append("<option value='" + key + "'>" + val + "</option>");
/*    */     } 
/*    */     
/* 47 */     sb.append("</select>");
/* 48 */     return sb.toString();
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
/*    */ 
/*    */   
/*    */   public static String getList(String name, String selectedVal, List<DropdownListBean> list) {
/* 62 */     List<DropdownListBean> dd = list;
/*    */     
/* 64 */     StringBuffer sb = new StringBuffer("<select class='form-control' name='" + name + "' id='" + name + "'>");
/*    */     
/* 66 */     String key = null;
/*    */     
/* 68 */     String val = null;
/*    */     
/* 70 */     String select = "-----Select-----";
/*    */     
/* 72 */     sb.append("<option selected value='-----Select-----'>-----Select-----</option>");
/*    */     
/* 74 */     for (DropdownListBean obj : dd) {
/* 75 */       key = obj.getKey();
/* 76 */       val = obj.getValue();
/*    */       
/* 78 */       if (key.trim().equals(selectedVal)) {
/* 79 */         sb.append("<option selected value='" + key + "'>" + val + "</option>"); continue;
/*    */       } 
/* 81 */       sb.append("<option value='" + key + "'>" + val + "</option>");
/*    */     } 
/*    */ 
/*    */     
/* 85 */     sb.append("</select>");
/* 86 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mg\\util\HTMLUtility.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */