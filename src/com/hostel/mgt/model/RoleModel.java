/*     */ package com.hostel.mgt.model;
/*     */ 
/*     */ import com.hostel.mgt.bean.RoleBean;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RoleModel
/*     */ {
/*  28 */   private static Logger log = Logger.getLogger(RoleModel.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer nextPK() throws DatabaseException {
/*  36 */     log.debug("Model nextPK Started");
/*  37 */     Connection conn = null;
/*  38 */     int pk = 0;
/*     */     try {
/*  40 */       conn = JDBCDataSource.getConnection();
/*  41 */       PreparedStatement pstmt = conn
/*  42 */         .prepareStatement("SELECT MAX(ID) FROM H_Role");
/*  43 */       ResultSet rs = pstmt.executeQuery();
/*  44 */       while (rs.next()) {
/*  45 */         pk = rs.getInt(1);
/*     */       }
/*  47 */       rs.close();
/*  48 */     } catch (Exception e) {
/*  49 */       log.error("Database Exception..", e);
/*  50 */       throw new DatabaseException("Exception : Exception in getting PK");
/*     */     } finally {
/*  52 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*  54 */     log.debug("Model nextPK End");
/*  55 */     return Integer.valueOf(pk + 1);
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
/*     */   public long add(RoleBean bean) throws ApplicationException, DuplicateRecordException {
/*  69 */     log.debug("Model add Started");
/*  70 */     Connection conn = null;
/*  71 */     int pk = 0;
/*  72 */     RoleBean duplicataRole = findByName(bean.getName());
/*     */ 
/*     */     
/*  75 */     if (duplicataRole != null) {
/*  76 */       throw new DuplicateRecordException("Role already exists");
/*     */     }
/*     */     try {
/*  79 */       conn = JDBCDataSource.getConnection();
/*  80 */       pk = nextPK().intValue();
/*     */ 
/*     */       
/*  83 */       System.out.println(String.valueOf(pk) + " in ModelJDBC");
/*  84 */       conn.setAutoCommit(false);
/*  85 */       PreparedStatement pstmt = conn
/*  86 */         .prepareStatement("INSERT INTO H_ROLE VALUES(?,?,?,?,?,?,?)");
/*  87 */       pstmt.setInt(1, pk);
/*  88 */       pstmt.setString(2, bean.getName());
/*  89 */       pstmt.setString(3, bean.getDescription());
/*  90 */       pstmt.setString(4, bean.getCreatedBy());
/*  91 */       pstmt.setString(5, bean.getModifiedBy());
/*  92 */       pstmt.setTimestamp(6, bean.getCreatedDatetime());
/*  93 */       pstmt.setTimestamp(7, bean.getModifiedDatetime());
/*  94 */       pstmt.executeUpdate();
/*  95 */       conn.commit();
/*  96 */       pstmt.close();
/*  97 */     } catch (Exception e) {
/*  98 */       e.printStackTrace();
/*  99 */       log.error("Database Exception..", e);
/*     */       try {
/* 101 */         conn.rollback();
/* 102 */       } catch (Exception ex) {
/* 103 */         throw new ApplicationException(
/* 104 */             "Exception : add rollback exception " + ex.getMessage());
/*     */       } 
/* 106 */       throw new ApplicationException("Exception : Exception in add Role");
/*     */     } finally {
/* 108 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 110 */     log.debug("Model add End");
/* 111 */     return pk;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete(RoleBean bean) throws ApplicationException {
/* 122 */     log.debug("Model delete Started");
/* 123 */     Connection conn = null;
/*     */     try {
/* 125 */       conn = JDBCDataSource.getConnection();
/* 126 */       conn.setAutoCommit(false);
/* 127 */       PreparedStatement pstmt = conn
/* 128 */         .prepareStatement("DELETE FROM H_ROLE WHERE ID=?");
/* 129 */       pstmt.setLong(1, bean.getId());
/* 130 */       pstmt.executeUpdate();
/* 131 */       conn.commit();
/* 132 */       pstmt.close();
/* 133 */     } catch (Exception e) {
/*     */       
/*     */       try {
/* 136 */         conn.rollback();
/* 137 */       } catch (Exception ex) {
/* 138 */         throw new ApplicationException(
/* 139 */             "Exception : Delete rollback exception " + 
/* 140 */             ex.getMessage());
/*     */       } 
/* 142 */       throw new ApplicationException(
/* 143 */           "Exception : Exception in delete Role");
/*     */     } finally {
/* 145 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 147 */     log.debug("Model delete Started");
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
/*     */   public RoleBean findByName(String name) throws ApplicationException {
/* 160 */     log.debug("Model findBy EmailId Started");
/* 161 */     StringBuffer sql = new StringBuffer(
/* 162 */         "SELECT * FROM H_ROLE WHERE NAME=?");
/* 163 */     RoleBean bean = null;
/* 164 */     Connection conn = null;
/*     */     try {
/* 166 */       conn = JDBCDataSource.getConnection();
/* 167 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 168 */       pstmt.setString(1, name);
/* 169 */       ResultSet rs = pstmt.executeQuery();
/* 170 */       while (rs.next()) {
/* 171 */         bean = new RoleBean();
/* 172 */         bean.setId(rs.getLong(1));
/* 173 */         bean.setName(rs.getString(2));
/* 174 */         bean.setDescription(rs.getString(3));
/* 175 */         bean.setCreatedBy(rs.getString(4));
/* 176 */         bean.setModifiedBy(rs.getString(5));
/* 177 */         bean.setCreatedDatetime(rs.getTimestamp(6));
/* 178 */         bean.setModifiedDatetime(rs.getTimestamp(7));
/*     */       } 
/* 180 */       rs.close();
/* 181 */     } catch (Exception e) {
/* 182 */       log.error("Database Exception..", e);
/* 183 */       throw new ApplicationException(
/* 184 */           "Exception : Exception in getting User by emailId");
/*     */     } finally {
/* 186 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 188 */     log.debug("Model findBy EmailId End");
/* 189 */     return bean;
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
/*     */   public RoleBean findByPK(long pk) throws ApplicationException {
/* 202 */     log.debug("Model findByPK Started");
/* 203 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_ROLE WHERE ID=?");
/* 204 */     RoleBean bean = null;
/* 205 */     Connection conn = null;
/*     */     try {
/* 207 */       conn = JDBCDataSource.getConnection();
/* 208 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 209 */       pstmt.setLong(1, pk);
/* 210 */       ResultSet rs = pstmt.executeQuery();
/* 211 */       while (rs.next()) {
/* 212 */         bean = new RoleBean();
/* 213 */         bean.setId(rs.getLong(1));
/* 214 */         bean.setName(rs.getString(2));
/* 215 */         bean.setDescription(rs.getString(3));
/* 216 */         bean.setCreatedBy(rs.getString(4));
/* 217 */         bean.setModifiedBy(rs.getString(5));
/* 218 */         bean.setCreatedDatetime(rs.getTimestamp(6));
/* 219 */         bean.setModifiedDatetime(rs.getTimestamp(7));
/*     */       } 
/* 221 */       rs.close();
/* 222 */     } catch (Exception e) {
/* 223 */       log.error("Database Exception..", e);
/* 224 */       throw new ApplicationException(
/* 225 */           "Exception : Exception in getting User by pk");
/*     */     } finally {
/* 227 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 229 */     log.debug("Model findByPK End");
/* 230 */     return bean;
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
/*     */   public void update(RoleBean bean) throws ApplicationException, DuplicateRecordException {
/* 242 */     log.debug("Model update Started");
/* 243 */     Connection conn = null;
/* 244 */     RoleBean duplicataRole = findByName(bean.getName());
/*     */ 
/*     */     
/* 247 */     if (duplicataRole != null && duplicataRole.getId() != bean.getId()) {
/* 248 */       throw new DuplicateRecordException("Role already exists");
/*     */     }
/*     */     try {
/* 251 */       conn = JDBCDataSource.getConnection();
/* 252 */       conn.setAutoCommit(false);
/* 253 */       PreparedStatement pstmt = conn
/* 254 */         .prepareStatement("UPDATE H_ROLE SET NAME=?,DESCRIPTION=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
/* 255 */       pstmt.setString(1, bean.getName());
/* 256 */       pstmt.setString(2, bean.getDescription());
/* 257 */       pstmt.setString(3, bean.getCreatedBy());
/* 258 */       pstmt.setString(4, bean.getModifiedBy());
/* 259 */       pstmt.setTimestamp(5, bean.getCreatedDatetime());
/* 260 */       pstmt.setTimestamp(6, bean.getModifiedDatetime());
/* 261 */       pstmt.setLong(7, bean.getId());
/* 262 */       pstmt.executeUpdate();
/* 263 */       conn.commit();
/* 264 */       pstmt.close();
/* 265 */     } catch (Exception e) {
/* 266 */       log.error("Database Exception..", e);
/*     */       try {
/* 268 */         conn.rollback();
/* 269 */       } catch (Exception ex) {
/* 270 */         throw new ApplicationException(
/* 271 */             "Exception : Delete rollback exception " + 
/* 272 */             ex.getMessage());
/*     */       } 
/* 274 */       throw new ApplicationException("Exception in updating Role ");
/*     */     } finally {
/* 276 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 278 */     log.debug("Model update End");
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
/*     */   public List search(RoleBean bean) throws ApplicationException {
/* 290 */     return search(bean, 0, 0);
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
/*     */   
/*     */   public List search(RoleBean bean, int pageNo, int pageSize) throws ApplicationException {
/* 309 */     log.debug("Model search Started");
/* 310 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_ROLE WHERE 1=1");
/* 311 */     if (bean != null) {
/* 312 */       if (bean.getId() > 0L) {
/* 313 */         sql.append(" AND id = " + bean.getId());
/*     */       }
/* 315 */       if (bean.getName() != null && bean.getName().length() > 0) {
/* 316 */         sql.append(" AND NAME LIKE '" + bean.getName() + "%'");
/*     */       }
/* 318 */       if (bean.getDescription() != null && 
/* 319 */         bean.getDescription().length() > 0) {
/* 320 */         sql.append(" AND DESCRIPTION LIKE '" + bean.getDescription() + 
/* 321 */             "%'");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 326 */     if (pageSize > 0) {
/*     */       
/* 328 */       pageNo = (pageNo - 1) * pageSize;
/* 329 */       sql.append(" Limit " + pageNo + ", " + pageSize);
/*     */     } 
/*     */     
/* 332 */     ArrayList<RoleBean> list = new ArrayList();
/* 333 */     Connection conn = null;
/*     */     try {
/* 335 */       conn = JDBCDataSource.getConnection();
/* 336 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 337 */       ResultSet rs = pstmt.executeQuery();
/* 338 */       while (rs.next()) {
/* 339 */         bean = new RoleBean();
/* 340 */         bean.setId(rs.getLong(1));
/* 341 */         bean.setName(rs.getString(2));
/* 342 */         bean.setDescription(rs.getString(3));
/* 343 */         bean.setCreatedBy(rs.getString(4));
/* 344 */         bean.setModifiedBy(rs.getString(5));
/* 345 */         bean.setCreatedDatetime(rs.getTimestamp(6));
/* 346 */         bean.setModifiedDatetime(rs.getTimestamp(7));
/* 347 */         list.add(bean);
/*     */       } 
/* 349 */       rs.close();
/* 350 */     } catch (Exception e) {
/* 351 */       log.error("Database Exception..", e);
/* 352 */       throw new ApplicationException(
/* 353 */           "Exception : Exception in search Role");
/*     */     } finally {
/* 355 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 357 */     log.debug("Model search End");
/* 358 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List list() throws ApplicationException {
/* 369 */     return list(0, 0);
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
/* 384 */     log.debug("Model list Started");
/* 385 */     ArrayList<RoleBean> list = new ArrayList();
/* 386 */     StringBuffer sql = new StringBuffer("select * from H_ROLE");
/*     */     
/* 388 */     if (pageSize > 0) {
/*     */       
/* 390 */       pageNo = (pageNo - 1) * pageSize;
/* 391 */       sql.append(" limit " + pageNo + "," + pageSize);
/*     */     } 
/* 393 */     Connection conn = null;
/*     */     try {
/* 395 */       conn = JDBCDataSource.getConnection();
/* 396 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 397 */       ResultSet rs = pstmt.executeQuery();
/* 398 */       while (rs.next()) {
/* 399 */         RoleBean bean = new RoleBean();
/* 400 */         bean.setId(rs.getLong(1));
/* 401 */         bean.setName(rs.getString(2));
/* 402 */         bean.setDescription(rs.getString(3));
/* 403 */         bean.setCreatedBy(rs.getString(4));
/* 404 */         bean.setModifiedBy(rs.getString(5));
/* 405 */         bean.setCreatedDatetime(rs.getTimestamp(6));
/* 406 */         bean.setModifiedDatetime(rs.getTimestamp(7));
/* 407 */         list.add(bean);
/*     */       } 
/* 409 */       rs.close();
/* 410 */     } catch (Exception e) {
/*     */       
/* 412 */       throw new ApplicationException(
/* 413 */           "Exception : Exception in getting list of Role");
/*     */     } finally {
/* 415 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 417 */     log.debug("Model list End");
/* 418 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\model\RoleModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */