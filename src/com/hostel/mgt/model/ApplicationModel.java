/*     */ package com.hostel.mgt.model;
/*     */ 
/*     */ import com.hostel.mgt.bean.ApplicationBean;
/*     */ import com.hostel.mgt.bean.HostelBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.exception.DatabaseException;
/*     */ import com.hostel.mgt.exception.DuplicateRecordException;
/*     */ import com.hostel.mgt.util.JDBCDataSource;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ApplicationModel
/*     */ {
/*  21 */   private static Logger log = Logger.getLogger(ApplicationModel.class);
/*     */   
/*     */   public Integer nextPK() throws DatabaseException {
/*  24 */     log.debug("Model nextPK Started");
/*  25 */     Connection conn = null;
/*  26 */     int pk = 0;
/*     */     try {
/*  28 */       conn = JDBCDataSource.getConnection();
/*  29 */       PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM H_Application");
/*  30 */       ResultSet rs = pstmt.executeQuery();
/*  31 */       while (rs.next()) {
/*  32 */         pk = rs.getInt(1);
/*     */       }
/*  34 */       rs.close();
/*  35 */     } catch (Exception e) {
/*  36 */       log.error("Database Exception..", e);
/*  37 */       throw new DatabaseException("Exception : Exception in getting PK");
/*     */     } finally {
/*  39 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*  41 */     log.debug("Model nextPK End");
/*  42 */     return Integer.valueOf(pk + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public long add(ApplicationBean bean) throws ApplicationException, DuplicateRecordException {
/*  47 */     Connection conn = null;
/*  48 */     int pk = 0;
/*     */     
/*  50 */     ApplicationBean existbean = findByUserIdandHostelId(bean.getUserId(), bean.getHostelId());
/*     */     
/*  52 */     if (existbean != null) {
/*  53 */       throw new DuplicateRecordException("You have already Applied in This Hostel");
/*     */     }
/*     */     
/*  56 */     HostelModel hModel = new HostelModel();
/*  57 */     HostelBean hBean = hModel.findByPK(bean.getHostelId());
/*     */     
/*     */     try {
/*  60 */       conn = JDBCDataSource.getConnection();
/*  61 */       pk = nextPK().intValue();
/*     */       
/*  63 */       System.out.println(String.valueOf(pk) + " in ModelJDBC");
/*  64 */       conn.setAutoCommit(false);
/*  65 */       PreparedStatement pstmt = conn
/*  66 */         .prepareStatement("INSERT INTO H_APPLICATION VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
/*  67 */       pstmt.setInt(1, pk);
/*  68 */       pstmt.setLong(2, bean.getUserId());
/*  69 */       pstmt.setString(3, bean.getName());
/*  70 */       pstmt.setLong(4, bean.getHostelId());
/*  71 */       pstmt.setString(5, hBean.getName());
/*  72 */       pstmt.setString(6, bean.getQualification());
/*  73 */       pstmt.setString(7, bean.getAddress());
/*  74 */       pstmt.setString(8, bean.getDescription());
/*  75 */       pstmt.setString(9, bean.getCreatedBy());
/*  76 */       pstmt.setString(10, bean.getModifiedBy());
/*  77 */       pstmt.setTimestamp(11, bean.getCreatedDatetime());
/*  78 */       pstmt.setTimestamp(12, bean.getModifiedDatetime());
/*  79 */       pstmt.executeUpdate();
/*  80 */       conn.commit();
/*  81 */       pstmt.close();
/*  82 */     } catch (Exception e) {
/*     */       
/*     */       try {
/*  85 */         conn.rollback();
/*  86 */       } catch (Exception ex) {
/*  87 */         ex.printStackTrace();
/*  88 */         throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
/*     */       } 
/*  90 */       throw new ApplicationException("Exception : Exception in add User");
/*     */     } finally {
/*  92 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*     */     
/*  95 */     return pk;
/*     */   }
/*     */   
/*     */   public ApplicationBean findByLogin(String name) throws ApplicationException {
/*  99 */     log.debug("Model findByLogin Started");
/* 100 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Application WHERE Name=?");
/* 101 */     ApplicationBean bean = null;
/* 102 */     Connection conn = null;
/* 103 */     System.out.println("sql" + sql);
/*     */     
/*     */     try {
/* 106 */       conn = JDBCDataSource.getConnection();
/* 107 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 108 */       pstmt.setString(1, name);
/* 109 */       ResultSet rs = pstmt.executeQuery();
/* 110 */       while (rs.next()) {
/* 111 */         bean = new ApplicationBean();
/* 112 */         bean.setId(rs.getLong(1));
/* 113 */         bean.setUserId(rs.getLong(2));
/* 114 */         bean.setName(rs.getString(3));
/* 115 */         bean.setHostelId(rs.getLong(4));
/* 116 */         bean.setHostelName(rs.getString(5));
/* 117 */         bean.setQualification(rs.getString(6));
/* 118 */         bean.setAddress(rs.getString(7));
/* 119 */         bean.setDescription(rs.getString(8));
/* 120 */         bean.setCreatedBy(rs.getString(9));
/* 121 */         bean.setModifiedBy(rs.getString(10));
/* 122 */         bean.setCreatedDatetime(rs.getTimestamp(11));
/* 123 */         bean.setModifiedDatetime(rs.getTimestamp(12));
/*     */       } 
/*     */       
/* 126 */       rs.close();
/* 127 */     } catch (Exception e) {
/* 128 */       e.printStackTrace();
/* 129 */       log.error("Database Exception..", e);
/* 130 */       throw new ApplicationException("Exception : Exception in getting User by login");
/*     */     } finally {
/* 132 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 134 */     log.debug("Model findByLogin End");
/* 135 */     return bean;
/*     */   }
/*     */   
/*     */   public ApplicationBean findByPK(long id) throws ApplicationException {
/* 139 */     log.debug("Model findByLogin Started");
/* 140 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Application WHERE ID=?");
/* 141 */     ApplicationBean bean = null;
/* 142 */     Connection conn = null;
/* 143 */     System.out.println("sql" + sql);
/*     */     
/*     */     try {
/* 146 */       conn = JDBCDataSource.getConnection();
/* 147 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 148 */       pstmt.setLong(1, id);
/* 149 */       ResultSet rs = pstmt.executeQuery();
/* 150 */       while (rs.next()) {
/* 151 */         bean = new ApplicationBean();
/* 152 */         bean.setId(rs.getLong(1));
/* 153 */         bean.setUserId(rs.getLong(2));
/* 154 */         bean.setName(rs.getString(3));
/* 155 */         bean.setHostelId(rs.getLong(4));
/* 156 */         bean.setHostelName(rs.getString(5));
/* 157 */         bean.setQualification(rs.getString(6));
/* 158 */         bean.setAddress(rs.getString(7));
/* 159 */         bean.setDescription(rs.getString(8));
/* 160 */         bean.setCreatedBy(rs.getString(9));
/* 161 */         bean.setModifiedBy(rs.getString(10));
/* 162 */         bean.setCreatedDatetime(rs.getTimestamp(11));
/* 163 */         bean.setModifiedDatetime(rs.getTimestamp(12));
/*     */       } 
/*     */       
/* 166 */       rs.close();
/* 167 */     } catch (Exception e) {
/* 168 */       e.printStackTrace();
/* 169 */       log.error("Database Exception..", e);
/* 170 */       throw new ApplicationException("Exception : Exception in getting User by login");
/*     */     } finally {
/* 172 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 174 */     log.debug("Model findByLogin End");
/* 175 */     return bean;
/*     */   }
/*     */   
/*     */   public ApplicationBean findByUserIdandHostelId(long userId, long hostelId) throws ApplicationException {
/* 179 */     log.debug("Model findByLogin Started");
/* 180 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Application WHERE UserId=? and hostelId=?");
/* 181 */     ApplicationBean bean = null;
/* 182 */     Connection conn = null;
/* 183 */     System.out.println("sql" + sql);
/*     */     
/*     */     try {
/* 186 */       conn = JDBCDataSource.getConnection();
/* 187 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 188 */       pstmt.setLong(1, userId);
/* 189 */       pstmt.setLong(2, hostelId);
/* 190 */       ResultSet rs = pstmt.executeQuery();
/* 191 */       while (rs.next()) {
/* 192 */         bean = new ApplicationBean();
/* 193 */         bean.setId(rs.getLong(1));
/* 194 */         bean.setUserId(rs.getLong(2));
/* 195 */         bean.setName(rs.getString(3));
/* 196 */         bean.setHostelId(rs.getLong(4));
/* 197 */         bean.setHostelName(rs.getString(5));
/* 198 */         bean.setQualification(rs.getString(6));
/* 199 */         bean.setAddress(rs.getString(7));
/* 200 */         bean.setDescription(rs.getString(8));
/* 201 */         bean.setCreatedBy(rs.getString(9));
/* 202 */         bean.setModifiedBy(rs.getString(10));
/* 203 */         bean.setCreatedDatetime(rs.getTimestamp(11));
/* 204 */         bean.setModifiedDatetime(rs.getTimestamp(12));
/*     */       } 
/*     */       
/* 207 */       rs.close();
/* 208 */     } catch (Exception e) {
/* 209 */       e.printStackTrace();
/* 210 */       log.error("Database Exception..", e);
/* 211 */       throw new ApplicationException("Exception : Exception in getting User by login");
/*     */     } finally {
/* 213 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 215 */     log.debug("Model findByLogin End");
/* 216 */     return bean;
/*     */   }
/*     */ 
/*     */   
/*     */   public void delete(ApplicationBean bean) throws ApplicationException {
/* 221 */     Connection conn = null;
/*     */     try {
/* 223 */       conn = JDBCDataSource.getConnection();
/* 224 */       conn.setAutoCommit(false);
/* 225 */       PreparedStatement pstmt = conn.prepareStatement("DELETE FROM H_USER WHERE ID=?");
/* 226 */       pstmt.setLong(1, bean.getId());
/* 227 */       pstmt.executeUpdate();
/* 228 */       conn.commit();
/* 229 */       pstmt.close();
/*     */     }
/* 231 */     catch (Exception e) {
/*     */       
/*     */       try {
/* 234 */         conn.rollback();
/* 235 */       } catch (Exception ex) {
/* 236 */         throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
/*     */       } 
/* 238 */       throw new ApplicationException("Exception : Exception in delete User");
/*     */     } finally {
/* 240 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List search(ApplicationBean bean) throws ApplicationException {
/* 247 */     return search(bean, 0, 0);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List search(ApplicationBean bean, int pageNo, int pageSize) throws ApplicationException {
/* 265 */     log.debug("Model search Started");
/* 266 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Application WHERE 1=1");
/*     */     
/* 268 */     if (bean != null) {
/* 269 */       if (bean.getId() > 0L) {
/* 270 */         sql.append(" AND id = " + bean.getId());
/*     */       }
/* 272 */       if (bean.getHostelId() > 0L) {
/* 273 */         sql.append(" AND HostelId = " + bean.getHostelId());
/*     */       }
/* 275 */       if (bean.getName() != null && bean.getName().length() > 0) {
/* 276 */         sql.append(" AND Name like '" + bean.getName() + "%'");
/*     */       }
/* 278 */       if (bean.getHostelName() != null && bean.getHostelName().length() > 0) {
/* 279 */         sql.append(" AND HostelName like '" + bean.getHostelName() + "%'");
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 287 */     if (pageSize > 0) {
/*     */       
/* 289 */       pageNo = (pageNo - 1) * pageSize;
/*     */       
/* 291 */       sql.append(" Limit " + pageNo + ", " + pageSize);
/*     */     } 
/*     */ 
/*     */     
/* 295 */     System.out.println("user model search  :" + sql);
/* 296 */     ArrayList<ApplicationBean> list = new ArrayList();
/* 297 */     Connection conn = null;
/*     */     try {
/* 299 */       conn = JDBCDataSource.getConnection();
/* 300 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 301 */       ResultSet rs = pstmt.executeQuery();
/* 302 */       while (rs.next()) {
/* 303 */         bean = new ApplicationBean();
/* 304 */         bean.setId(rs.getLong(1));
/* 305 */         bean.setUserId(rs.getLong(2));
/* 306 */         bean.setName(rs.getString(3));
/* 307 */         bean.setHostelId(rs.getLong(4));
/* 308 */         bean.setHostelName(rs.getString(5));
/* 309 */         bean.setQualification(rs.getString(6));
/* 310 */         bean.setAddress(rs.getString(7));
/* 311 */         bean.setDescription(rs.getString(8));
/* 312 */         bean.setCreatedBy(rs.getString(9));
/* 313 */         bean.setModifiedBy(rs.getString(10));
/* 314 */         bean.setCreatedDatetime(rs.getTimestamp(11));
/* 315 */         bean.setModifiedDatetime(rs.getTimestamp(12));
/* 316 */         list.add(bean);
/*     */       } 
/* 318 */       rs.close();
/* 319 */     } catch (Exception e) {
/* 320 */       log.error("Database Exception..", e);
/* 321 */       throw new ApplicationException("Exception : Exception in search user");
/*     */     } finally {
/* 323 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*     */     
/* 326 */     log.debug("Model search End");
/* 327 */     return list;
/*     */   }
/*     */   
/*     */   public List list() throws ApplicationException {
/* 331 */     return list(0, 0);
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
/*     */ 
/*     */   
/*     */   public List list(int pageNo, int pageSize) throws ApplicationException {
/* 346 */     log.debug("Model list Started");
/* 347 */     ArrayList<ApplicationBean> list = new ArrayList();
/* 348 */     StringBuffer sql = new StringBuffer("select * from H_APPLICATION");
/*     */     
/* 350 */     if (pageSize > 0) {
/*     */       
/* 352 */       pageNo = (pageNo - 1) * pageSize;
/* 353 */       sql.append(" limit " + pageNo + "," + pageSize);
/*     */     } 
/*     */ 
/*     */     
/* 357 */     System.out.println("sql in list user :" + sql);
/* 358 */     Connection conn = null;
/*     */     
/*     */     try {
/* 361 */       conn = JDBCDataSource.getConnection();
/* 362 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 363 */       ResultSet rs = pstmt.executeQuery();
/* 364 */       while (rs.next()) {
/* 365 */         ApplicationBean bean = new ApplicationBean();
/* 366 */         bean.setId(rs.getLong(1));
/* 367 */         bean.setUserId(rs.getLong(2));
/* 368 */         bean.setName(rs.getString(3));
/* 369 */         bean.setHostelId(rs.getLong(4));
/* 370 */         bean.setHostelName(rs.getString(5));
/* 371 */         bean.setQualification(rs.getString(6));
/* 372 */         bean.setAddress(rs.getString(7));
/* 373 */         bean.setDescription(rs.getString(8));
/* 374 */         bean.setCreatedBy(rs.getString(9));
/* 375 */         bean.setModifiedBy(rs.getString(10));
/* 376 */         bean.setCreatedDatetime(rs.getTimestamp(11));
/* 377 */         bean.setModifiedDatetime(rs.getTimestamp(12));
/* 378 */         list.add(bean);
/*     */       } 
/* 380 */       rs.close();
/* 381 */     } catch (Exception e) {
/* 382 */       log.error("Database Exception..", e);
/* 383 */       throw new ApplicationException("Exception : Exception in getting list of users");
/*     */     } finally {
/* 385 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*     */     
/* 388 */     log.debug("Model list End");
/* 389 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\model\ApplicationModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */