/*     */ package com.hostel.mgt.model;
/*     */ 
/*     */ import com.hostel.mgt.bean.UserBean;
/*     */ import com.hostel.mgt.bean.WardenBean;
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
/*     */ public class WardenModel
/*     */ {
/*  21 */   private static Logger log = Logger.getLogger(WardenModel.class);
/*     */   
/*     */   public Integer nextPK() throws DatabaseException {
/*  24 */     log.debug("Model nextPK Started");
/*  25 */     Connection conn = null;
/*  26 */     int pk = 0;
/*     */     try {
/*  28 */       conn = JDBCDataSource.getConnection();
/*  29 */       PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM H_warden");
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
/*     */   public WardenBean findByUserIdAndHostelId(long userId, long hostelId) throws ApplicationException {
/*  46 */     log.debug("Model findBy EmailId Started");
/*  47 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_warden  where userId=? and hostelId=?");
/*  48 */     WardenBean bean = null;
/*  49 */     Connection conn = null;
/*     */     try {
/*  51 */       conn = JDBCDataSource.getConnection();
/*  52 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/*  53 */       pstmt.setLong(1, userId);
/*  54 */       pstmt.setLong(2, hostelId);
/*  55 */       ResultSet rs = pstmt.executeQuery();
/*  56 */       while (rs.next()) {
/*  57 */         bean = new WardenBean();
/*  58 */         bean.setId(rs.getLong(1));
/*  59 */         bean.setUserId(rs.getLong(2));
/*  60 */         bean.setName(rs.getString(3));
/*  61 */         bean.setLogin(rs.getString(4));
/*  62 */         bean.setHostelId(rs.getLong(5));
/*  63 */         bean.setCreatedBy(rs.getString(6));
/*  64 */         bean.setModifiedBy(rs.getString(7));
/*  65 */         bean.setCreatedDatetime(rs.getTimestamp(8));
/*  66 */         bean.setModifiedDatetime(rs.getTimestamp(9));
/*     */       } 
/*  68 */       rs.close();
/*  69 */     } catch (Exception e) {
/*  70 */       log.error("Database Exception..", e);
/*  71 */       throw new ApplicationException("Exception : Exception in getting warden by ");
/*     */     } finally {
/*  73 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*  75 */     log.debug("Model findBy EmailId End");
/*  76 */     return bean;
/*     */   }
/*     */   
/*     */   public WardenBean findByName(String name) throws ApplicationException {
/*  80 */     log.debug("Model findBy EmailId Started");
/*  81 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_warden  where name=?");
/*  82 */     WardenBean bean = null;
/*  83 */     Connection conn = null;
/*     */     try {
/*  85 */       conn = JDBCDataSource.getConnection();
/*  86 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/*  87 */       pstmt.setString(1, name);
/*  88 */       ResultSet rs = pstmt.executeQuery();
/*  89 */       while (rs.next()) {
/*  90 */         bean = new WardenBean();
/*  91 */         bean.setId(rs.getLong(1));
/*  92 */         bean.setUserId(rs.getLong(2));
/*  93 */         bean.setName(rs.getString(3));
/*  94 */         bean.setLogin(rs.getString(4));
/*  95 */         bean.setHostelId(rs.getLong(5));
/*  96 */         bean.setCreatedBy(rs.getString(6));
/*  97 */         bean.setModifiedBy(rs.getString(7));
/*  98 */         bean.setCreatedDatetime(rs.getTimestamp(8));
/*  99 */         bean.setModifiedDatetime(rs.getTimestamp(9));
/*     */       } 
/* 101 */       rs.close();
/* 102 */     } catch (Exception e) {
/* 103 */       log.error("Database Exception..", e);
/* 104 */       throw new ApplicationException("Exception : Exception in getting User by Room No");
/*     */     } finally {
/* 106 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 108 */     log.debug("Model findBy EmailId End");
/* 109 */     return bean;
/*     */   }
/*     */   
/*     */   public WardenBean findByLogin(String name) throws ApplicationException {
/* 113 */     log.debug("Model findBy EmailId Started");
/* 114 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_warden  where login=?");
/* 115 */     WardenBean bean = null;
/* 116 */     Connection conn = null;
/*     */     try {
/* 118 */       conn = JDBCDataSource.getConnection();
/* 119 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 120 */       pstmt.setString(1, name);
/* 121 */       ResultSet rs = pstmt.executeQuery();
/* 122 */       while (rs.next()) {
/* 123 */         bean = new WardenBean();
/* 124 */         bean.setId(rs.getLong(1));
/* 125 */         bean.setUserId(rs.getLong(2));
/* 126 */         bean.setName(rs.getString(3));
/* 127 */         bean.setLogin(rs.getString(4));
/* 128 */         bean.setHostelId(rs.getLong(5));
/* 129 */         bean.setCreatedBy(rs.getString(6));
/* 130 */         bean.setModifiedBy(rs.getString(7));
/* 131 */         bean.setCreatedDatetime(rs.getTimestamp(8));
/* 132 */         bean.setModifiedDatetime(rs.getTimestamp(9));
/*     */       } 
/* 134 */       rs.close();
/* 135 */     } catch (Exception e) {
/* 136 */       log.error("Database Exception..", e);
/* 137 */       throw new ApplicationException("Exception : Exception in getting User by Room No");
/*     */     } finally {
/* 139 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 141 */     log.debug("Model findBy EmailId End");
/* 142 */     return bean;
/*     */   }
/*     */ 
/*     */   
/*     */   public WardenBean findByUserId(long pk) throws ApplicationException {
/* 147 */     log.debug("Model findByPK Started");
/* 148 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_warden WHERE userId=?");
/* 149 */     WardenBean bean = null;
/* 150 */     Connection conn = null;
/*     */     try {
/* 152 */       conn = JDBCDataSource.getConnection();
/* 153 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 154 */       pstmt.setLong(1, pk);
/* 155 */       ResultSet rs = pstmt.executeQuery();
/* 156 */       while (rs.next()) {
/* 157 */         bean = new WardenBean();
/* 158 */         bean.setId(rs.getLong(1));
/* 159 */         bean.setUserId(rs.getLong(2));
/* 160 */         bean.setName(rs.getString(3));
/* 161 */         bean.setLogin(rs.getString(4));
/* 162 */         bean.setHostelId(rs.getLong(5));
/* 163 */         bean.setCreatedBy(rs.getString(6));
/* 164 */         bean.setModifiedBy(rs.getString(7));
/* 165 */         bean.setCreatedDatetime(rs.getTimestamp(8));
/* 166 */         bean.setModifiedDatetime(rs.getTimestamp(9));
/*     */       } 
/* 168 */       rs.close();
/* 169 */     } catch (Exception e) {
/* 170 */       log.error("Database Exception..", e);
/* 171 */       throw new ApplicationException("Exception : Exception in getting User by pk");
/*     */     } finally {
/* 173 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 175 */     log.debug("Model findByPK End");
/* 176 */     return bean;
/*     */   }
/*     */ 
/*     */   
/*     */   public WardenBean findByPK(long pk) throws ApplicationException {
/* 181 */     log.debug("Model findByPK Started");
/* 182 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_warden WHERE ID=?");
/* 183 */     WardenBean bean = null;
/* 184 */     Connection conn = null;
/*     */     try {
/* 186 */       conn = JDBCDataSource.getConnection();
/* 187 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 188 */       pstmt.setLong(1, pk);
/* 189 */       ResultSet rs = pstmt.executeQuery();
/* 190 */       while (rs.next()) {
/* 191 */         bean = new WardenBean();
/* 192 */         bean.setId(rs.getLong(1));
/* 193 */         bean.setUserId(rs.getLong(2));
/* 194 */         bean.setName(rs.getString(3));
/* 195 */         bean.setLogin(rs.getString(4));
/* 196 */         bean.setHostelId(rs.getLong(5));
/* 197 */         bean.setCreatedBy(rs.getString(6));
/* 198 */         bean.setModifiedBy(rs.getString(7));
/* 199 */         bean.setCreatedDatetime(rs.getTimestamp(8));
/* 200 */         bean.setModifiedDatetime(rs.getTimestamp(9));
/*     */       } 
/* 202 */       rs.close();
/* 203 */     } catch (Exception e) {
/* 204 */       log.error("Database Exception..", e);
/* 205 */       throw new ApplicationException("Exception : Exception in getting User by pk");
/*     */     } finally {
/* 207 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 209 */     log.debug("Model findByPK End");
/* 210 */     return bean;
/*     */   }
/*     */ 
/*     */   
/*     */   public long add(WardenBean bean) throws ApplicationException, DuplicateRecordException {
/* 215 */     log.debug("Model add Started");
/* 216 */     Connection conn = null;
/* 217 */     int pk = 0;
/* 218 */     WardenBean duplicataRole = findByUserIdAndHostelId(bean.getUserId(), bean.getHostelId());
/*     */ 
/*     */     
/* 221 */     if (duplicataRole != null) {
/* 222 */       throw new DuplicateRecordException("Warden is already exists in this Hostel");
/*     */     }
/*     */     
/* 225 */     UserModel uModel = new UserModel();
/* 226 */     UserBean ubean = uModel.findByPK(bean.getUserId());
/*     */     
/*     */     try {
/* 229 */       conn = JDBCDataSource.getConnection();
/* 230 */       pk = nextPK().intValue();
/*     */ 
/*     */       
/* 233 */       System.out.println(String.valueOf(pk) + " in ModelJDBC");
/* 234 */       conn.setAutoCommit(false);
/* 235 */       PreparedStatement pstmt = conn.prepareStatement("INSERT INTO H_warden VALUES(?,?,?,?,?,?,?,?,?)");
/* 236 */       pstmt.setInt(1, pk);
/* 237 */       pstmt.setLong(2, bean.getUserId());
/* 238 */       pstmt.setString(3, String.valueOf(ubean.getFirstName()) + " " + ubean.getLastName());
/* 239 */       pstmt.setString(4, ubean.getLogin());
/* 240 */       pstmt.setLong(5, bean.getHostelId());
/* 241 */       pstmt.setString(6, bean.getCreatedBy());
/* 242 */       pstmt.setString(7, bean.getModifiedBy());
/* 243 */       pstmt.setTimestamp(8, bean.getCreatedDatetime());
/* 244 */       pstmt.setTimestamp(9, bean.getModifiedDatetime());
/* 245 */       pstmt.executeUpdate();
/* 246 */       conn.commit();
/* 247 */       pstmt.close();
/* 248 */     } catch (Exception e) {
/* 249 */       e.printStackTrace();
/* 250 */       log.error("Database Exception..", e);
/*     */       try {
/* 252 */         conn.rollback();
/* 253 */       } catch (Exception ex) {
/* 254 */         throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
/*     */       } 
/* 256 */       throw new ApplicationException("Exception : Exception in add Warden");
/*     */     } finally {
/* 258 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 260 */     log.debug("Model add End");
/* 261 */     return pk;
/*     */   }
/*     */   
/*     */   public void delete(WardenBean bean) throws ApplicationException {
/* 265 */     log.debug("Model delete Started");
/* 266 */     Connection conn = null;
/*     */     try {
/* 268 */       conn = JDBCDataSource.getConnection();
/* 269 */       conn.setAutoCommit(false);
/* 270 */       PreparedStatement pstmt = conn.prepareStatement("DELETE FROM H_Warden WHERE ID=?");
/* 271 */       pstmt.setLong(1, bean.getId());
/* 272 */       pstmt.executeUpdate();
/* 273 */       conn.commit();
/* 274 */       pstmt.close();
/* 275 */     } catch (Exception e) {
/*     */       
/*     */       try {
/* 278 */         conn.rollback();
/* 279 */       } catch (Exception ex) {
/* 280 */         throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
/*     */       } 
/* 282 */       throw new ApplicationException("Exception : Exception in delete Warden");
/*     */     } finally {
/* 284 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 286 */     log.debug("Model delete Started");
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(WardenBean bean) throws ApplicationException, DuplicateRecordException {
/* 291 */     log.debug("Model update Started");
/* 292 */     Connection conn = null;
/* 293 */     WardenBean duplicataRole = findByUserIdAndHostelId(bean.getUserId(), bean.getHostelId());
/*     */     
/* 295 */     if (duplicataRole != null && duplicataRole.getId() != bean.getId()) {
/* 296 */       throw new DuplicateRecordException("Warden is already exists this hostel");
/*     */     }
/*     */     
/* 299 */     UserModel uModel = new UserModel();
/* 300 */     UserBean ubean = uModel.findByPK(bean.getUserId());
/*     */     try {
/* 302 */       conn = JDBCDataSource.getConnection();
/* 303 */       conn.setAutoCommit(false);
/* 304 */       PreparedStatement pstmt = conn.prepareStatement(
/* 305 */           "UPDATE H_warden SET userId=?,name=?,login=?,hostelId=?,DESCRIPTION=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
/* 306 */       pstmt.setLong(1, bean.getUserId());
/* 307 */       pstmt.setString(2, String.valueOf(ubean.getFirstName()) + " " + ubean.getLastName());
/* 308 */       pstmt.setString(3, ubean.getLogin());
/* 309 */       pstmt.setLong(4, bean.getHostelId());
/* 310 */       pstmt.setString(5, bean.getCreatedBy());
/* 311 */       pstmt.setString(6, bean.getModifiedBy());
/* 312 */       pstmt.setTimestamp(7, bean.getCreatedDatetime());
/* 313 */       pstmt.setTimestamp(8, bean.getModifiedDatetime());
/* 314 */       pstmt.setLong(9, bean.getId());
/* 315 */       pstmt.executeUpdate();
/* 316 */       conn.commit();
/* 317 */       pstmt.close();
/* 318 */     } catch (Exception e) {
/* 319 */       log.error("Database Exception..", e);
/*     */       try {
/* 321 */         conn.rollback();
/* 322 */       } catch (Exception ex) {
/* 323 */         throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
/*     */       } 
/* 325 */       throw new ApplicationException("Exception in updating warden ");
/*     */     } finally {
/* 327 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 329 */     log.debug("Model update End");
/*     */   }
/*     */ 
/*     */   
/*     */   public List search(WardenBean bean) throws ApplicationException {
/* 334 */     return search(bean, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List search(WardenBean bean, int pageNo, int pageSize) throws ApplicationException {
/* 340 */     log.debug("Model search Started");
/* 341 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Warden WHERE 1=1");
/* 342 */     if (bean != null) {
/* 343 */       if (bean.getId() > 0L) {
/* 344 */         sql.append(" AND id = " + bean.getId());
/*     */       }
/* 346 */       if (bean.getHostelId() > 0L) {
/* 347 */         sql.append(" AND HostelId = " + bean.getHostelId());
/*     */       }
/* 349 */       if (bean.getUserId() > 0L) {
/* 350 */         sql.append(" AND UserId = " + bean.getUserId());
/*     */       }
/* 352 */       if (bean.getName() != null && bean.getName().length() > 0) {
/* 353 */         sql.append(" AND Name LIKE '" + bean.getName() + "%'");
/*     */       }
/* 355 */       if (bean.getLogin() != null && 
/* 356 */         bean.getLogin().length() > 0) {
/* 357 */         sql.append(" AND Login LIKE '" + bean.getLogin() + 
/* 358 */             "%'");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 363 */     if (pageSize > 0) {
/*     */       
/* 365 */       pageNo = (pageNo - 1) * pageSize;
/* 366 */       sql.append(" Limit " + pageNo + ", " + pageSize);
/*     */     } 
/*     */     
/* 369 */     ArrayList<WardenBean> list = new ArrayList();
/* 370 */     Connection conn = null;
/*     */     try {
/* 372 */       conn = JDBCDataSource.getConnection();
/* 373 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 374 */       ResultSet rs = pstmt.executeQuery();
/* 375 */       while (rs.next()) {
/* 376 */         bean = new WardenBean();
/* 377 */         bean.setId(rs.getLong(1));
/* 378 */         bean.setUserId(rs.getLong(2));
/* 379 */         bean.setName(rs.getString(3));
/* 380 */         bean.setLogin(rs.getString(4));
/* 381 */         bean.setHostelId(rs.getLong(5));
/* 382 */         bean.setCreatedBy(rs.getString(6));
/* 383 */         bean.setModifiedBy(rs.getString(7));
/* 384 */         bean.setCreatedDatetime(rs.getTimestamp(8));
/* 385 */         bean.setModifiedDatetime(rs.getTimestamp(9));
/* 386 */         list.add(bean);
/*     */       } 
/* 388 */       rs.close();
/* 389 */     } catch (Exception e) {
/* 390 */       log.error("Database Exception..", e);
/* 391 */       throw new ApplicationException(
/* 392 */           "Exception : Exception in search Role");
/*     */     } finally {
/* 394 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 396 */     log.debug("Model search End");
/* 397 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public List list() throws ApplicationException {
/* 402 */     return list(0, 0);
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
/* 417 */     log.debug("Model list Started");
/* 418 */     ArrayList<WardenBean> list = new ArrayList();
/* 419 */     StringBuffer sql = new StringBuffer("select * from H_Warden");
/*     */     
/* 421 */     if (pageSize > 0) {
/*     */       
/* 423 */       pageNo = (pageNo - 1) * pageSize;
/* 424 */       sql.append(" limit " + pageNo + "," + pageSize);
/*     */     } 
/* 426 */     Connection conn = null;
/*     */     try {
/* 428 */       conn = JDBCDataSource.getConnection();
/* 429 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 430 */       ResultSet rs = pstmt.executeQuery();
/* 431 */       while (rs.next()) {
/* 432 */         WardenBean bean = new WardenBean();
/* 433 */         bean.setId(rs.getLong(1));
/* 434 */         bean.setUserId(rs.getLong(2));
/* 435 */         bean.setName(rs.getString(3));
/* 436 */         bean.setLogin(rs.getString(4));
/* 437 */         bean.setHostelId(rs.getLong(5));
/* 438 */         bean.setCreatedBy(rs.getString(6));
/* 439 */         bean.setModifiedBy(rs.getString(7));
/* 440 */         bean.setCreatedDatetime(rs.getTimestamp(8));
/* 441 */         bean.setModifiedDatetime(rs.getTimestamp(9));
/* 442 */         list.add(bean);
/*     */       } 
/* 444 */       rs.close();
/* 445 */     } catch (Exception e) {
/*     */       
/* 447 */       throw new ApplicationException(
/* 448 */           "Exception : Exception in getting list of Role");
/*     */     } finally {
/* 450 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 452 */     log.debug("Model list End");
/* 453 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\model\WardenModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */