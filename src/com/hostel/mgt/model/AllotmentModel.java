/*     */ package com.hostel.mgt.model;
/*     */ 
/*     */ import com.hostel.mgt.bean.AllotmentBean;
/*     */ import com.hostel.mgt.bean.HostelBean;
/*     */ import com.hostel.mgt.bean.RoomBean;
/*     */ import com.hostel.mgt.bean.UserBean;
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
/*     */ public class AllotmentModel
/*     */ {
/*  22 */   private static Logger log = Logger.getLogger(AllotmentModel.class);
/*     */   
/*     */   public Integer nextPK() throws DatabaseException {
/*  25 */     log.debug("Model nextPK Started");
/*  26 */     Connection conn = null;
/*  27 */     int pk = 0;
/*     */     try {
/*  29 */       conn = JDBCDataSource.getConnection();
/*  30 */       PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM H_Allotment");
/*  31 */       ResultSet rs = pstmt.executeQuery();
/*  32 */       while (rs.next()) {
/*  33 */         pk = rs.getInt(1);
/*     */       }
/*  35 */       rs.close();
/*  36 */     } catch (Exception e) {
/*  37 */       log.error("Database Exception..", e);
/*  38 */       throw new DatabaseException("Exception : Exception in getting PK");
/*     */     } finally {
/*  40 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*  42 */     log.debug("Model nextPK End");
/*  43 */     return Integer.valueOf(pk + 1);
/*     */   }
/*     */   
/*     */   public AllotmentBean findByUserIdAndHostelId(long userid, long hostelId) throws ApplicationException {
/*  47 */     log.debug("Model findBy EmailId Started");
/*  48 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Allotment  where userId=? and hostelId=?");
/*  49 */     AllotmentBean bean = null;
/*  50 */     Connection conn = null;
/*     */     try {
/*  52 */       conn = JDBCDataSource.getConnection();
/*  53 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/*  54 */       pstmt.setLong(1, userid);
/*  55 */       pstmt.setLong(2, hostelId);
/*  56 */       ResultSet rs = pstmt.executeQuery();
/*  57 */       while (rs.next()) {
/*  58 */         bean = new AllotmentBean();
/*  59 */         bean.setId(rs.getLong(1));
/*  60 */         bean.setUserId(rs.getLong(2));
/*  61 */         bean.setName(rs.getString(3));
/*  62 */         bean.setHostelId(rs.getLong(4));
/*  63 */         bean.setHostelName(rs.getString(5));
/*  64 */         bean.setRoomId(rs.getLong(6));
/*  65 */         bean.setRoomNo(rs.getString(7));
/*  66 */         bean.setCreatedBy(rs.getString(8));
/*  67 */         bean.setModifiedBy(rs.getString(9));
/*  68 */         bean.setCreatedDatetime(rs.getTimestamp(10));
/*  69 */         bean.setModifiedDatetime(rs.getTimestamp(11));
/*     */       } 
/*  71 */       rs.close();
/*  72 */     } catch (Exception e) {
/*  73 */       log.error("Database Exception..", e);
/*  74 */       throw new ApplicationException("Exception : Exception in getting User by Room No");
/*     */     } finally {
/*  76 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*  78 */     log.debug("Model findBy EmailId End");
/*  79 */     return bean;
/*     */   }
/*     */   
/*     */   public AllotmentBean findByUserIdAndHostelIdAndRoomId(long userid, long hostelId, long roomId) throws ApplicationException {
/*  83 */     log.debug("Model findBy EmailId Started");
/*  84 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Allotment  where userId=? and hostelId=? and roomId=?");
/*  85 */     AllotmentBean bean = null;
/*  86 */     Connection conn = null;
/*     */     try {
/*  88 */       conn = JDBCDataSource.getConnection();
/*  89 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/*  90 */       pstmt.setLong(1, userid);
/*  91 */       pstmt.setLong(2, hostelId);
/*  92 */       pstmt.setLong(3, roomId);
/*  93 */       ResultSet rs = pstmt.executeQuery();
/*  94 */       while (rs.next()) {
/*  95 */         bean = new AllotmentBean();
/*  96 */         bean.setId(rs.getLong(1));
/*  97 */         bean.setUserId(rs.getLong(2));
/*  98 */         bean.setName(rs.getString(3));
/*  99 */         bean.setHostelId(rs.getLong(4));
/* 100 */         bean.setHostelName(rs.getString(5));
/* 101 */         bean.setRoomId(rs.getLong(6));
/* 102 */         bean.setRoomNo(rs.getString(7));
/* 103 */         bean.setCreatedBy(rs.getString(8));
/* 104 */         bean.setModifiedBy(rs.getString(9));
/* 105 */         bean.setCreatedDatetime(rs.getTimestamp(10));
/* 106 */         bean.setModifiedDatetime(rs.getTimestamp(11));
/*     */       } 
/* 108 */       rs.close();
/* 109 */     } catch (Exception e) {
/* 110 */       log.error("Database Exception..", e);
/* 111 */       throw new ApplicationException("Exception : Exception in getting User by Room No");
/*     */     } finally {
/* 113 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 115 */     log.debug("Model findBy EmailId End");
/* 116 */     return bean;
/*     */   }
/*     */   
/*     */   public AllotmentBean findByPk(long id) throws ApplicationException {
/* 120 */     log.debug("Model findBy EmailId Started");
/* 121 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Allotment  where Id=? ");
/* 122 */     AllotmentBean bean = null;
/* 123 */     Connection conn = null;
/*     */     try {
/* 125 */       conn = JDBCDataSource.getConnection();
/* 126 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 127 */       pstmt.setLong(1, id);
/* 128 */       ResultSet rs = pstmt.executeQuery();
/* 129 */       while (rs.next()) {
/* 130 */         bean = new AllotmentBean();
/* 131 */         bean.setId(rs.getLong(1));
/* 132 */         bean.setUserId(rs.getLong(2));
/* 133 */         bean.setName(rs.getString(3));
/* 134 */         bean.setHostelId(rs.getLong(4));
/* 135 */         bean.setHostelName(rs.getString(5));
/* 136 */         bean.setRoomId(rs.getLong(6));
/* 137 */         bean.setRoomNo(rs.getString(7));
/* 138 */         bean.setCreatedBy(rs.getString(8));
/* 139 */         bean.setModifiedBy(rs.getString(9));
/* 140 */         bean.setCreatedDatetime(rs.getTimestamp(10));
/* 141 */         bean.setModifiedDatetime(rs.getTimestamp(11));
/*     */       } 
/* 143 */       rs.close();
/* 144 */     } catch (Exception e) {
/* 145 */       log.error("Database Exception..", e);
/* 146 */       throw new ApplicationException("Exception : Exception in getting User by Room No");
/*     */     } finally {
/* 148 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 150 */     log.debug("Model findBy EmailId End");
/* 151 */     return bean;
/*     */   }
/*     */   
/*     */   public long add(AllotmentBean bean) throws ApplicationException, DuplicateRecordException {
/* 155 */     log.debug("Model add Started");
/* 156 */     Connection conn = null;
/* 157 */     int pk = 0;
/* 158 */     AllotmentBean duplicataRole = findByUserIdAndHostelIdAndRoomId(bean.getUserId(), bean.getHostelId(), bean.getRoomId());
/*     */     
/* 160 */     if (duplicataRole != null) {
/* 161 */       throw new DuplicateRecordException("Student are  already exists in this Room");
/*     */     }
/*     */     
/* 164 */     AllotmentBean duplicataRole1 = findByUserIdAndHostelId(bean.getUserId(), bean.getHostelId());
/*     */     
/* 166 */     if (duplicataRole1 != null) {
/* 167 */       throw new DuplicateRecordException("Student are  already exists in this Hostel");
/*     */     }
/*     */     
/* 170 */     HostelModel hModel = new HostelModel();
/* 171 */     HostelBean hBean = hModel.findByPK(bean.getHostelId());
/*     */     
/* 173 */     UserModel uModel = new UserModel();
/* 174 */     UserBean uBean = uModel.findByPK(bean.getUserId());
/*     */     
/* 176 */     RoomModel rModel = new RoomModel();
/* 177 */     RoomBean rBean = rModel.findByPK(bean.getRoomId());
/*     */ 
/*     */     
/*     */     try {
/* 181 */       conn = JDBCDataSource.getConnection();
/* 182 */       pk = nextPK().intValue();
/*     */ 
/*     */       
/* 185 */       System.out.println(String.valueOf(pk) + " in ModelJDBC");
/* 186 */       conn.setAutoCommit(false);
/* 187 */       PreparedStatement pstmt = conn.prepareStatement("INSERT INTO H_Allotment VALUES(?,?,?,?,?,?,?,?,?,?,?)");
/* 188 */       pstmt.setInt(1, pk);
/* 189 */       pstmt.setLong(2, bean.getUserId());
/* 190 */       pstmt.setString(3, String.valueOf(uBean.getFirstName()) + " " + uBean.getLastName());
/* 191 */       pstmt.setLong(4, bean.getHostelId());
/* 192 */       pstmt.setString(5, hBean.getName());
/* 193 */       pstmt.setLong(6, bean.getRoomId());
/* 194 */       pstmt.setString(7, rBean.getRoomNo());
/* 195 */       pstmt.setString(8, bean.getCreatedBy());
/* 196 */       pstmt.setString(9, bean.getModifiedBy());
/* 197 */       pstmt.setTimestamp(10, bean.getCreatedDatetime());
/* 198 */       pstmt.setTimestamp(11, bean.getModifiedDatetime());
/* 199 */       pstmt.executeUpdate();
/* 200 */       conn.commit();
/* 201 */       pstmt.close();
/* 202 */     } catch (Exception e) {
/* 203 */       e.printStackTrace();
/* 204 */       log.error("Database Exception..", e);
/*     */       try {
/* 206 */         conn.rollback();
/* 207 */       } catch (Exception ex) {
/* 208 */         throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
/*     */       } 
/* 210 */       throw new ApplicationException("Exception : Exception in add Room");
/*     */     } finally {
/* 212 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 214 */     log.debug("Model add End");
/* 215 */     return pk;
/*     */   }
/*     */ 
/*     */   
/*     */   public void delete(AllotmentBean bean) throws ApplicationException {
/* 220 */     log.debug("Model delete Started");
/* 221 */     Connection conn = null;
/*     */     try {
/* 223 */       conn = JDBCDataSource.getConnection();
/* 224 */       conn.setAutoCommit(false);
/* 225 */       PreparedStatement pstmt = conn.prepareStatement("DELETE FROM H_Allotment WHERE ID=?");
/* 226 */       pstmt.setLong(1, bean.getId());
/* 227 */       pstmt.executeUpdate();
/* 228 */       conn.commit();
/* 229 */       pstmt.close();
/* 230 */     } catch (Exception e) {
/*     */       
/*     */       try {
/* 233 */         conn.rollback();
/* 234 */       } catch (Exception ex) {
/* 235 */         throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
/*     */       } 
/* 237 */       throw new ApplicationException("Exception : Exception in delete Role");
/*     */     } finally {
/* 239 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 241 */     log.debug("Model delete Started");
/*     */   }
/*     */   
/*     */   public void update(AllotmentBean bean) throws ApplicationException, DuplicateRecordException {
/* 245 */     log.debug("Model update Started");
/* 246 */     Connection conn = null;
/* 247 */     AllotmentBean duplicataRole = findByUserIdAndHostelIdAndRoomId(bean.getUserId(), bean.getHostelId(), bean.getRoomId());
/*     */     
/* 249 */     if (duplicataRole != null && duplicataRole.getId() != bean.getId()) {
/* 250 */       throw new DuplicateRecordException("Student is already exists this Room");
/*     */     }
/*     */     
/* 253 */     AllotmentBean duplicataRole1 = findByUserIdAndHostelId(bean.getUserId(), bean.getHostelId());
/*     */     
/* 255 */     if (duplicataRole1 != null && duplicataRole1.getId() != bean.getId()) {
/* 256 */       throw new DuplicateRecordException("Student is already exists this Hostel");
/*     */     }
/*     */ 
/*     */     
/* 260 */     HostelModel hModel = new HostelModel();
/* 261 */     HostelBean hBean = hModel.findByPK(bean.getHostelId());
/*     */     
/* 263 */     UserModel uModel = new UserModel();
/* 264 */     UserBean uBean = uModel.findByPK(bean.getUserId());
/*     */     
/* 266 */     RoomModel rModel = new RoomModel();
/* 267 */     RoomBean rBean = rModel.findByPK(bean.getRoomId());
/*     */     
/*     */     try {
/* 270 */       conn = JDBCDataSource.getConnection();
/* 271 */       conn.setAutoCommit(false);
/* 272 */       PreparedStatement pstmt = conn.prepareStatement(
/* 273 */           "UPDATE H_Allotment SET UserId=?,name=?,hostelId=?,hostelName=?,roomId=?,roomNo=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
/* 274 */       pstmt.setLong(1, bean.getUserId());
/* 275 */       pstmt.setString(2, String.valueOf(uBean.getFirstName()) + " " + uBean.getLastName());
/* 276 */       pstmt.setLong(3, bean.getHostelId());
/* 277 */       pstmt.setString(4, hBean.getName());
/* 278 */       pstmt.setLong(5, bean.getRoomId());
/* 279 */       pstmt.setString(6, rBean.getRoomNo());
/* 280 */       pstmt.setString(7, bean.getCreatedBy());
/* 281 */       pstmt.setString(8, bean.getModifiedBy());
/* 282 */       pstmt.setTimestamp(9, bean.getCreatedDatetime());
/* 283 */       pstmt.setTimestamp(10, bean.getModifiedDatetime());
/* 284 */       pstmt.setLong(11, bean.getId());
/* 285 */       pstmt.executeUpdate();
/* 286 */       conn.commit();
/* 287 */       pstmt.close();
/* 288 */     } catch (Exception e) {
/* 289 */       log.error("Database Exception..", e);
/*     */       try {
/* 291 */         conn.rollback();
/* 292 */       } catch (Exception ex) {
/* 293 */         throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
/*     */       } 
/* 295 */       throw new ApplicationException("Exception in updating Room ");
/*     */     } finally {
/* 297 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 299 */     log.debug("Model update End");
/*     */   }
/*     */ 
/*     */   
/*     */   public List search(AllotmentBean bean) throws ApplicationException {
/* 304 */     return search(bean, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List search(AllotmentBean bean, int pageNo, int pageSize) throws ApplicationException {
/* 310 */     log.debug("Model search Started");
/* 311 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Allotment WHERE 1=1");
/* 312 */     if (bean != null) {
/* 313 */       if (bean.getId() > 0L) {
/* 314 */         sql.append(" AND id = " + bean.getId());
/*     */       }
/* 316 */       if (bean.getHostelId() > 0L) {
/* 317 */         sql.append(" AND HostelId = " + bean.getHostelId());
/*     */       }
/* 319 */       if (bean.getUserId() > 0L) {
/* 320 */         sql.append(" AND UserId = " + bean.getUserId());
/*     */       }
/* 322 */       if (bean.getRoomId() > 0L) {
/* 323 */         sql.append(" AND RoomId = " + bean.getRoomId());
/*     */       }
/* 325 */       if (bean.getRoomNo() != null && bean.getRoomNo().length() > 0) {
/* 326 */         sql.append(" AND RoomNo LIKE '" + bean.getRoomNo() + "%'");
/*     */       }
/* 328 */       if (bean.getName() != null && bean.getName().length() > 0) {
/* 329 */         sql.append(" AND Name LIKE '" + bean.getName() + "%'");
/*     */       }
/* 331 */       if (bean.getHostelName() != null && bean.getHostelName().length() > 0) {
/* 332 */         sql.append(" AND RoomNo LIKE '" + bean.getHostelName() + "%'");
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 338 */     if (pageSize > 0) {
/*     */       
/* 340 */       pageNo = (pageNo - 1) * pageSize;
/* 341 */       sql.append(" Limit " + pageNo + ", " + pageSize);
/*     */     } 
/*     */     
/* 344 */     ArrayList<AllotmentBean> list = new ArrayList();
/* 345 */     Connection conn = null;
/*     */     try {
/* 347 */       conn = JDBCDataSource.getConnection();
/* 348 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 349 */       ResultSet rs = pstmt.executeQuery();
/* 350 */       while (rs.next()) {
/* 351 */         bean = new AllotmentBean();
/* 352 */         bean.setId(rs.getLong(1));
/* 353 */         bean.setUserId(rs.getLong(2));
/* 354 */         bean.setName(rs.getString(3));
/* 355 */         bean.setHostelId(rs.getLong(4));
/* 356 */         bean.setHostelName(rs.getString(5));
/* 357 */         bean.setRoomId(rs.getLong(6));
/* 358 */         bean.setRoomNo(rs.getString(7));
/* 359 */         bean.setCreatedBy(rs.getString(8));
/* 360 */         bean.setModifiedBy(rs.getString(9));
/* 361 */         bean.setCreatedDatetime(rs.getTimestamp(10));
/* 362 */         bean.setModifiedDatetime(rs.getTimestamp(11));
/* 363 */         list.add(bean);
/*     */       } 
/* 365 */       rs.close();
/* 366 */     } catch (Exception e) {
/* 367 */       log.error("Database Exception..", e);
/* 368 */       throw new ApplicationException(
/* 369 */           "Exception : Exception in search Role");
/*     */     } finally {
/* 371 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 373 */     log.debug("Model search End");
/* 374 */     return list;
/*     */   }
/*     */   
/*     */   public List list() throws ApplicationException {
/* 378 */     return list(0, 0);
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
/* 393 */     log.debug("Model list Started");
/* 394 */     ArrayList<AllotmentBean> list = new ArrayList();
/* 395 */     StringBuffer sql = new StringBuffer("select * from H_Allotment");
/*     */     
/* 397 */     if (pageSize > 0) {
/*     */       
/* 399 */       pageNo = (pageNo - 1) * pageSize;
/* 400 */       sql.append(" limit " + pageNo + "," + pageSize);
/*     */     } 
/* 402 */     Connection conn = null;
/*     */     try {
/* 404 */       conn = JDBCDataSource.getConnection();
/* 405 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 406 */       ResultSet rs = pstmt.executeQuery();
/* 407 */       while (rs.next()) {
/* 408 */         AllotmentBean bean = new AllotmentBean();
/* 409 */         bean.setId(rs.getLong(1));
/* 410 */         bean.setUserId(rs.getLong(2));
/* 411 */         bean.setName(rs.getString(3));
/* 412 */         bean.setHostelId(rs.getLong(4));
/* 413 */         bean.setHostelName(rs.getString(5));
/* 414 */         bean.setRoomId(rs.getLong(6));
/* 415 */         bean.setRoomNo(rs.getString(7));
/* 416 */         bean.setCreatedBy(rs.getString(8));
/* 417 */         bean.setModifiedBy(rs.getString(9));
/* 418 */         bean.setCreatedDatetime(rs.getTimestamp(10));
/* 419 */         bean.setModifiedDatetime(rs.getTimestamp(11));
/* 420 */         list.add(bean);
/*     */       } 
/* 422 */       rs.close();
/* 423 */     } catch (Exception e) {
/*     */       
/* 425 */       throw new ApplicationException(
/* 426 */           "Exception : Exception in getting list of Role");
/*     */     } finally {
/* 428 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 430 */     log.debug("Model list End");
/* 431 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\model\AllotmentModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */