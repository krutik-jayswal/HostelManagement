/*     */ package com.hostel.mgt.model;
/*     */ 
/*     */ import com.hostel.mgt.bean.UserBean;
/*     */ import com.hostel.mgt.exception.ApplicationException;
/*     */ import com.hostel.mgt.exception.DatabaseException;
/*     */ import com.hostel.mgt.exception.DuplicateRecordException;
/*     */ import com.hostel.mgt.exception.RecordNotFoundException;
/*     */ import com.hostel.mgt.util.JDBCDataSource;
/*     */ import java.sql.Connection;
/*     */ import java.sql.Date;
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
/*     */ public class UserModel
/*     */ {
/*  28 */   private static Logger log = Logger.getLogger(UserModel.class);
/*     */ 
/*     */   
/*     */   public Integer nextPK() throws DatabaseException {
/*  32 */     log.debug("Model nextPK Started");
/*  33 */     Connection conn = null;
/*  34 */     int pk = 0;
/*     */     
/*     */     try {
/*  37 */       conn = JDBCDataSource.getConnection();
/*  38 */       PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM H_USER");
/*  39 */       ResultSet rs = pstmt.executeQuery();
/*  40 */       while (rs.next()) {
/*  41 */         pk = rs.getInt(1);
/*     */       }
/*  43 */       rs.close();
/*     */     }
/*  45 */     catch (Exception e) {
/*  46 */       log.error("Database Exception..", e);
/*  47 */       throw new DatabaseException("Exception : Exception in getting PK");
/*     */     } finally {
/*  49 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*  51 */     log.debug("Model nextPK End");
/*  52 */     return Integer.valueOf(pk + 1);
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
/*     */   public long add(UserBean bean) throws ApplicationException, DuplicateRecordException {
/*  64 */     Connection conn = null;
/*  65 */     int pk = 0;
/*     */     
/*  67 */     UserBean existbean = findByLogin(bean.getLogin());
/*     */     
/*  69 */     if (existbean != null) {
/*  70 */       throw new DuplicateRecordException("Login Id already exists");
/*     */     }
/*     */     
/*     */     try {
/*  74 */       conn = JDBCDataSource.getConnection();
/*  75 */       pk = nextPK().intValue();
/*     */       
/*  77 */       System.out.println(String.valueOf(pk) + " in ModelJDBC");
/*  78 */       conn.setAutoCommit(false);
/*  79 */       PreparedStatement pstmt = conn.prepareStatement("INSERT INTO H_USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
/*  80 */       pstmt.setInt(1, pk);
/*  81 */       pstmt.setString(2, bean.getFirstName());
/*  82 */       pstmt.setString(3, bean.getLastName());
/*  83 */       pstmt.setString(4, bean.getLogin());
/*  84 */       pstmt.setString(5, bean.getPassword());
/*  85 */       pstmt.setDate(6, new Date(bean.getDob().getTime()));
/*  86 */       pstmt.setString(7, bean.getMobileNo());
/*  87 */       pstmt.setLong(8, bean.getRoleId());
/*  88 */       pstmt.setString(9, bean.getGender());
/*  89 */       pstmt.setString(10, bean.getCreatedBy());
/*  90 */       pstmt.setString(11, bean.getModifiedBy());
/*  91 */       pstmt.setTimestamp(12, bean.getCreatedDatetime());
/*  92 */       pstmt.setTimestamp(13, bean.getModifiedDatetime());
/*  93 */       pstmt.setString(14, bean.getImage());
/*  94 */       pstmt.executeUpdate();
/*  95 */       conn.commit();
/*  96 */       pstmt.close();
/*  97 */     } catch (Exception e) {
/*     */       
/*     */       try {
/* 100 */         conn.rollback();
/* 101 */       } catch (Exception ex) {
/* 102 */         ex.printStackTrace();
/* 103 */         throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
/*     */       } 
/* 105 */       throw new ApplicationException("Exception : Exception in add User");
/*     */     } finally {
/* 107 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*     */     
/* 110 */     return pk;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete(UserBean bean) throws ApplicationException {
/* 121 */     Connection conn = null;
/*     */     try {
/* 123 */       conn = JDBCDataSource.getConnection();
/* 124 */       conn.setAutoCommit(false);
/* 125 */       PreparedStatement pstmt = conn.prepareStatement("DELETE FROM H_USER WHERE ID=?");
/* 126 */       pstmt.setLong(1, bean.getId());
/* 127 */       pstmt.executeUpdate();
/* 128 */       conn.commit();
/* 129 */       pstmt.close();
/*     */     }
/* 131 */     catch (Exception e) {
/*     */       
/*     */       try {
/* 134 */         conn.rollback();
/* 135 */       } catch (Exception ex) {
/* 136 */         throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
/*     */       } 
/* 138 */       throw new ApplicationException("Exception : Exception in delete User");
/*     */     } finally {
/* 140 */       JDBCDataSource.closeConnection(conn);
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
/*     */ 
/*     */   
/*     */   public UserBean findByLogin(String login) throws ApplicationException {
/* 155 */     log.debug("Model findByLogin Started");
/* 156 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_USER WHERE LOGIN=?");
/* 157 */     UserBean bean = null;
/* 158 */     Connection conn = null;
/* 159 */     System.out.println("sql" + sql);
/*     */     
/*     */     try {
/* 162 */       conn = JDBCDataSource.getConnection();
/* 163 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 164 */       pstmt.setString(1, login);
/* 165 */       ResultSet rs = pstmt.executeQuery();
/* 166 */       while (rs.next()) {
/* 167 */         bean = new UserBean();
/* 168 */         bean.setId(rs.getLong(1));
/* 169 */         bean.setFirstName(rs.getString(2));
/* 170 */         bean.setLastName(rs.getString(3));
/* 171 */         bean.setLogin(rs.getString(4));
/* 172 */         bean.setPassword(rs.getString(5));
/* 173 */         bean.setDob(rs.getDate(6));
/* 174 */         bean.setMobileNo(rs.getString(7));
/* 175 */         bean.setRoleId(rs.getLong(8));
/* 176 */         bean.setGender(rs.getString(9));
/* 177 */         bean.setCreatedBy(rs.getString(10));
/* 178 */         bean.setModifiedBy(rs.getString(11));
/* 179 */         bean.setCreatedDatetime(rs.getTimestamp(12));
/* 180 */         bean.setModifiedDatetime(rs.getTimestamp(13));
/* 181 */         bean.setImage(rs.getString(14));
/*     */       } 
/*     */       
/* 184 */       rs.close();
/* 185 */     } catch (Exception e) {
/* 186 */       e.printStackTrace();
/* 187 */       log.error("Database Exception..", e);
/* 188 */       throw new ApplicationException("Exception : Exception in getting User by login");
/*     */     } finally {
/* 190 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 192 */     log.debug("Model findByLogin End");
/* 193 */     return bean;
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
/*     */   public UserBean findByPK(long pk) throws ApplicationException {
/* 206 */     log.debug("Model findByPK Started");
/* 207 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_USER WHERE ID=?");
/* 208 */     UserBean bean = null;
/* 209 */     Connection conn = null;
/*     */     
/*     */     try {
/* 212 */       conn = JDBCDataSource.getConnection();
/* 213 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 214 */       pstmt.setLong(1, pk);
/* 215 */       ResultSet rs = pstmt.executeQuery();
/* 216 */       while (rs.next()) {
/* 217 */         bean = new UserBean();
/* 218 */         bean.setId(rs.getLong(1));
/* 219 */         bean.setFirstName(rs.getString(2));
/* 220 */         bean.setLastName(rs.getString(3));
/* 221 */         bean.setLogin(rs.getString(4));
/* 222 */         bean.setPassword(rs.getString(5));
/* 223 */         bean.setDob(rs.getDate(6));
/* 224 */         bean.setMobileNo(rs.getString(7));
/* 225 */         bean.setRoleId(rs.getLong(8));
/* 226 */         bean.setGender(rs.getString(9));
/* 227 */         bean.setCreatedBy(rs.getString(10));
/* 228 */         bean.setModifiedBy(rs.getString(11));
/* 229 */         bean.setCreatedDatetime(rs.getTimestamp(12));
/* 230 */         bean.setModifiedDatetime(rs.getTimestamp(13));
/* 231 */         bean.setImage(rs.getString(14));
/*     */       } 
/*     */       
/* 234 */       rs.close();
/* 235 */     } catch (Exception e) {
/* 236 */       e.printStackTrace();
/* 237 */       log.error("Database Exception..", e);
/* 238 */       throw new ApplicationException("Exception : Exception in getting User by pk");
/*     */     } finally {
/* 240 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 242 */     log.debug("Model findByPK End");
/* 243 */     return bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(UserBean bean) throws ApplicationException, DuplicateRecordException {
/* 254 */     log.debug("Model update Started");
/* 255 */     Connection conn = null;
/*     */     
/* 257 */     UserBean beanExist = findByLogin(bean.getLogin());
/*     */     
/* 259 */     if (beanExist != null && beanExist.getId() != bean.getId()) {
/* 260 */       throw new DuplicateRecordException("LoginId is already exist");
/*     */     }
/*     */     
/*     */     try {
/* 264 */       conn = JDBCDataSource.getConnection();
/* 265 */       conn.setAutoCommit(false);
/* 266 */       PreparedStatement pstmt = conn.prepareStatement(
/* 267 */           "UPDATE H_USER SET FIRSTNAME=?,LASTNAME=?,LOGIN=?,PASSWORD=?,DOB=?,MOBILENO=?,ROLEID=?,GENDER=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=?,image=? WHERE ID=?");
/*     */ 
/*     */       
/* 270 */       pstmt.setString(1, bean.getFirstName());
/* 271 */       pstmt.setString(2, bean.getLastName());
/* 272 */       pstmt.setString(3, bean.getLogin());
/* 273 */       pstmt.setString(4, bean.getPassword());
/* 274 */       pstmt.setDate(5, new Date(bean.getDob().getTime()));
/* 275 */       pstmt.setString(6, bean.getMobileNo());
/* 276 */       pstmt.setLong(7, bean.getRoleId());
/* 277 */       pstmt.setString(8, bean.getGender());
/* 278 */       pstmt.setString(9, bean.getCreatedBy());
/* 279 */       pstmt.setString(10, bean.getModifiedBy());
/* 280 */       pstmt.setTimestamp(11, bean.getCreatedDatetime());
/* 281 */       pstmt.setTimestamp(12, bean.getModifiedDatetime());
/* 282 */       pstmt.setString(13, bean.getImage());
/* 283 */       pstmt.setLong(14, bean.getId());
/* 284 */       pstmt.executeUpdate();
/* 285 */       conn.commit();
/* 286 */       pstmt.close();
/* 287 */     } catch (Exception e) {
/* 288 */       e.printStackTrace();
/* 289 */       log.error("Database Exception..", e);
/*     */       try {
/* 291 */         conn.rollback();
/* 292 */       } catch (Exception ex) {
/* 293 */         throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
/*     */       } 
/* 295 */       throw new ApplicationException("Exception in updating User ");
/*     */     } finally {
/* 297 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 299 */     log.debug("Model update End");
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
/*     */   public List search(UserBean bean) throws ApplicationException {
/* 311 */     return search(bean, 0, 0);
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
/*     */   public List search(UserBean bean, int pageNo, int pageSize) throws ApplicationException {
/* 329 */     log.debug("Model search Started");
/* 330 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_USER WHERE 1=1");
/*     */     
/* 332 */     if (bean != null) {
/* 333 */       if (bean.getId() > 0L) {
/* 334 */         sql.append(" AND id = " + bean.getId());
/*     */       }
/* 336 */       if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
/* 337 */         sql.append(" AND FIRSTNAME like '" + bean.getFirstName() + "%'");
/*     */       }
/* 339 */       if (bean.getLastName() != null && bean.getLastName().length() > 0) {
/* 340 */         sql.append(" AND LASTNAME like '" + bean.getLastName() + "%'");
/*     */       }
/* 342 */       if (bean.getLogin() != null && bean.getLogin().length() > 0) {
/* 343 */         sql.append(" AND LOGIN like '" + bean.getLogin() + "%'");
/*     */       }
/* 345 */       if (bean.getPassword() != null && bean.getPassword().length() > 0) {
/* 346 */         sql.append(" AND PASSWORD like '" + bean.getPassword() + "%'");
/*     */       }
/* 348 */       if (bean.getDob() != null && bean.getDob().getDate() > 0) {
/* 349 */         sql.append(" AND DOB = " + bean.getGender());
/*     */       }
/* 351 */       if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
/* 352 */         sql.append(" AND MOBILENO = " + bean.getMobileNo());
/*     */       }
/* 354 */       if (bean.getRoleId() > 0L) {
/* 355 */         sql.append(" AND ROLEID = " + bean.getRoleId());
/*     */       }
/*     */       
/* 358 */       if (bean.getGender() != null && bean.getGender().length() > 0) {
/* 359 */         sql.append(" AND GENDER like '" + bean.getGender() + "%'");
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 366 */     if (pageSize > 0) {
/*     */       
/* 368 */       pageNo = (pageNo - 1) * pageSize;
/*     */       
/* 370 */       sql.append(" Limit " + pageNo + ", " + pageSize);
/*     */     } 
/*     */ 
/*     */     
/* 374 */     System.out.println("user model search  :" + sql);
/* 375 */     ArrayList<UserBean> list = new ArrayList();
/* 376 */     Connection conn = null;
/*     */     try {
/* 378 */       conn = JDBCDataSource.getConnection();
/* 379 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 380 */       ResultSet rs = pstmt.executeQuery();
/* 381 */       while (rs.next()) {
/* 382 */         bean = new UserBean();
/* 383 */         bean.setId(rs.getLong(1));
/* 384 */         bean.setFirstName(rs.getString(2));
/* 385 */         bean.setLastName(rs.getString(3));
/* 386 */         bean.setLogin(rs.getString(4));
/* 387 */         bean.setPassword(rs.getString(5));
/* 388 */         bean.setDob(rs.getDate(6));
/* 389 */         bean.setMobileNo(rs.getString(7));
/* 390 */         bean.setRoleId(rs.getLong(8));
/* 391 */         bean.setGender(rs.getString(9));
/* 392 */         bean.setCreatedBy(rs.getString(10));
/* 393 */         bean.setModifiedBy(rs.getString(11));
/* 394 */         bean.setCreatedDatetime(rs.getTimestamp(12));
/* 395 */         bean.setModifiedDatetime(rs.getTimestamp(13));
/* 396 */         bean.setImage(rs.getString(14));
/* 397 */         list.add(bean);
/*     */       } 
/* 399 */       rs.close();
/* 400 */     } catch (Exception e) {
/* 401 */       log.error("Database Exception..", e);
/* 402 */       throw new ApplicationException("Exception : Exception in search user");
/*     */     } finally {
/* 404 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*     */     
/* 407 */     log.debug("Model search End");
/* 408 */     return list;
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
/* 419 */     return list(0, 0);
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
/* 434 */     log.debug("Model list Started");
/* 435 */     ArrayList<UserBean> list = new ArrayList();
/* 436 */     StringBuffer sql = new StringBuffer("select * from H_USER");
/*     */     
/* 438 */     if (pageSize > 0) {
/*     */       
/* 440 */       pageNo = (pageNo - 1) * pageSize;
/* 441 */       sql.append(" limit " + pageNo + "," + pageSize);
/*     */     } 
/*     */ 
/*     */     
/* 445 */     System.out.println("sql in list user :" + sql);
/* 446 */     Connection conn = null;
/*     */     
/*     */     try {
/* 449 */       conn = JDBCDataSource.getConnection();
/* 450 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 451 */       ResultSet rs = pstmt.executeQuery();
/* 452 */       while (rs.next()) {
/* 453 */         UserBean bean = new UserBean();
/* 454 */         bean.setId(rs.getLong(1));
/* 455 */         bean.setFirstName(rs.getString(2));
/* 456 */         bean.setLastName(rs.getString(3));
/* 457 */         bean.setLogin(rs.getString(4));
/* 458 */         bean.setPassword(rs.getString(5));
/* 459 */         bean.setDob(rs.getDate(6));
/* 460 */         bean.setMobileNo(rs.getString(7));
/* 461 */         bean.setRoleId(rs.getLong(8));
/* 462 */         bean.setGender(rs.getString(9));
/* 463 */         bean.setCreatedBy(rs.getString(10));
/* 464 */         bean.setModifiedBy(rs.getString(11));
/* 465 */         bean.setCreatedDatetime(rs.getTimestamp(12));
/* 466 */         bean.setModifiedDatetime(rs.getTimestamp(13));
/* 467 */         bean.setImage(rs.getString(14));
/* 468 */         list.add(bean);
/*     */       } 
/* 470 */       rs.close();
/* 471 */     } catch (Exception e) {
/* 472 */       log.error("Database Exception..", e);
/* 473 */       throw new ApplicationException("Exception : Exception in getting list of users");
/*     */     } finally {
/* 475 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*     */     
/* 478 */     log.debug("Model list End");
/* 479 */     return list;
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
/*     */   public UserBean authenticate(String login, String password) throws ApplicationException {
/* 494 */     log.debug("Model authenticate Started");
/* 495 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_USER WHERE LOGIN = ? AND PASSWORD = ?");
/* 496 */     UserBean bean = null;
/* 497 */     Connection conn = null;
/*     */     
/*     */     try {
/* 500 */       conn = JDBCDataSource.getConnection();
/* 501 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 502 */       pstmt.setString(1, login);
/* 503 */       pstmt.setString(2, password);
/* 504 */       ResultSet rs = pstmt.executeQuery();
/* 505 */       while (rs.next()) {
/* 506 */         bean = new UserBean();
/* 507 */         bean.setId(rs.getLong(1));
/* 508 */         bean.setFirstName(rs.getString(2));
/* 509 */         bean.setLastName(rs.getString(3));
/* 510 */         bean.setLogin(rs.getString(4));
/* 511 */         bean.setPassword(rs.getString(5));
/* 512 */         bean.setDob(rs.getDate(6));
/* 513 */         bean.setMobileNo(rs.getString(7));
/* 514 */         bean.setRoleId(rs.getLong(8));
/* 515 */         bean.setGender(rs.getString(9));
/* 516 */         bean.setCreatedBy(rs.getString(10));
/* 517 */         bean.setModifiedBy(rs.getString(11));
/* 518 */         bean.setCreatedDatetime(rs.getTimestamp(12));
/* 519 */         bean.setModifiedDatetime(rs.getTimestamp(13));
/* 520 */         bean.setImage(rs.getString(14));
/* 521 */         System.out.println("Usermodel here");
/*     */       } 
/* 523 */     } catch (Exception e) {
/* 524 */       log.error("Database Exception..", e);
/* 525 */       throw new ApplicationException("Exception : Exception in get roles");
/*     */     } finally {
/*     */       
/* 528 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*     */     
/* 531 */     log.debug("Model authenticate End");
/* 532 */     return bean;
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
/*     */   public List getRoles(UserBean bean) throws ApplicationException {
/* 546 */     log.debug("Model get roles Started");
/* 547 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_USER WHERE role_Id=?");
/* 548 */     Connection conn = null;
/* 549 */     List<UserBean> list = new ArrayList();
/*     */     
/*     */     try {
/* 552 */       conn = JDBCDataSource.getConnection();
/* 553 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 554 */       pstmt.setLong(1, bean.getRoleId());
/* 555 */       ResultSet rs = pstmt.executeQuery();
/* 556 */       while (rs.next()) {
/* 557 */         bean = new UserBean();
/* 558 */         bean.setId(rs.getLong(1));
/* 559 */         bean.setFirstName(rs.getString(2));
/* 560 */         bean.setLastName(rs.getString(3));
/* 561 */         bean.setLogin(rs.getString(4));
/* 562 */         bean.setPassword(rs.getString(5));
/* 563 */         bean.setDob(rs.getDate(6));
/* 564 */         bean.setMobileNo(rs.getString(7));
/* 565 */         bean.setRoleId(rs.getLong(8));
/* 566 */         bean.setGender(rs.getString(9));
/* 567 */         bean.setCreatedBy(rs.getString(10));
/* 568 */         bean.setModifiedBy(rs.getString(11));
/* 569 */         bean.setCreatedDatetime(rs.getTimestamp(12));
/* 570 */         bean.setModifiedDatetime(rs.getTimestamp(13));
/* 571 */         bean.setImage(rs.getString(14));
/* 572 */         list.add(bean);
/*     */       } 
/* 574 */       rs.close();
/* 575 */     } catch (Exception e) {
/* 576 */       log.error("Database Exception..", e);
/* 577 */       throw new ApplicationException("Exception : Exception in get roles");
/*     */     } finally {
/*     */       
/* 580 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 582 */     log.debug("Model get roles End");
/* 583 */     return list;
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
/*     */   public boolean changePassword(Long id, String oldPassword, String newPassword) throws RecordNotFoundException, ApplicationException {
/* 600 */     log.debug("model changePassword Started");
/*     */     
/* 602 */     boolean flag = false;
/*     */     
/* 604 */     UserBean beanExist = null;
/*     */     
/* 606 */     beanExist = findByPK(id.longValue());
/*     */     
/* 608 */     if (beanExist != null && beanExist.getPassword().equals(oldPassword)) {
/* 609 */       beanExist.setPassword(newPassword);
/*     */       try {
/* 611 */         update(beanExist);
/* 612 */       } catch (DuplicateRecordException e) {
/* 613 */         log.error(e);
/* 614 */         throw new ApplicationException("LoginId is already exist");
/*     */       } 
/* 616 */       flag = true;
/*     */     } else {
/* 618 */       throw new RecordNotFoundException("Old password is Invalid");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 625 */     log.debug("Model changePassword End");
/* 626 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public UserBean updateAccess(UserBean bean) throws ApplicationException {
/* 631 */     return null;
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
/*     */   public long registerUser(UserBean bean) throws ApplicationException, DuplicateRecordException {
/* 647 */     log.debug("Model add Started");
/*     */     
/* 649 */     long pk = add(bean);
/*     */ 
/*     */     
/* 652 */     return pk;
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
/*     */   public boolean forgetPassword(String login) throws ApplicationException, RecordNotFoundException, ApplicationException {
/* 714 */     UserBean userData = findByLogin(login);
/*     */     
/* 716 */     boolean flag = false;
/*     */     
/* 718 */     if (userData == null) {
/* 719 */       throw new RecordNotFoundException("Email ID does not exists !");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 724 */     flag = true;
/*     */     
/* 726 */     return flag;
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\model\UserModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */