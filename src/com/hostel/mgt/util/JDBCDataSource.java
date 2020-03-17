/*     */ package com.hostel.mgt.util;
/*     */ 
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.mchange.v2.c3p0.ComboPooledDataSource;
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ResourceBundle;
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
/*     */ public class JDBCDataSource
/*     */ {
/*     */   private static JDBCDataSource datasource;
/*  30 */   private ComboPooledDataSource cpds = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JDBCDataSource getInstance() {
/*  39 */     if (datasource == null) {
/*     */       
/*  41 */       ResourceBundle rb = ResourceBundle.getBundle("com.hostel.mgt.bundle.system");
/*     */       
/*  43 */       datasource = new JDBCDataSource();
/*  44 */       datasource.cpds = new ComboPooledDataSource();
/*     */       try {
/*  46 */         datasource.cpds.setDriverClass(rb.getString("driver"));
/*  47 */       } catch (Exception e) {
/*  48 */         e.printStackTrace();
/*     */       } 
/*  50 */       datasource.cpds.setJdbcUrl(rb.getString("url"));
/*  51 */       datasource.cpds.setUser(rb.getString("username"));
/*  52 */       datasource.cpds.setPassword(rb.getString("password"));
/*  53 */       datasource.cpds.setInitialPoolSize((new Integer(rb.getString("initialPoolSize"))).intValue());
/*  54 */       datasource.cpds.setAcquireIncrement((new Integer(rb.getString("acquireIncrement"))).intValue());
/*  55 */       datasource.cpds.setMaxPoolSize((new Integer(rb.getString("maxPoolSize"))).intValue());
/*  56 */       datasource.cpds.setMaxIdleTime(DataUtility.getInt(rb.getString("timeout")));
/*  57 */       datasource.cpds.setMinPoolSize((new Integer(rb.getString("minPoolSize"))).intValue());
/*     */     } 
/*     */     
/*  60 */     return datasource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Connection getConnection() throws Exception {
/*  70 */     return (getInstance()).cpds.getConnection();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void closeConnection(Connection connection) {
/*  81 */     if (connection != null) {
/*     */       try {
/*  83 */         connection.close();
/*  84 */       } catch (Exception exception) {}
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
/*     */   public static void trnRollback(Connection connection) throws ApplicationException {
/*  97 */     if (connection != null)
/*     */       try {
/*  99 */         connection.rollback();
/* 100 */       } catch (SQLException ex) {
/* 101 */         throw new ApplicationException(ex.toString());
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mg\\util\JDBCDataSource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */