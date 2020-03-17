/*     */ package com.hostel.mgt.model;
/*     */ 
/*     */ import com.hostel.mgt.bean.FeeBean;
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
/*     */ 
/*     */ public class FeeModel
/*     */ {
/*  23 */   private static Logger log = Logger.getLogger(FeeModel.class);
/*     */   
/*     */   public Integer nextPK() throws DatabaseException {
/*  26 */     log.debug("Model nextPK Started");
/*  27 */     Connection conn = null;
/*  28 */     int pk = 0;
/*     */     try {
/*  30 */       conn = JDBCDataSource.getConnection();
/*  31 */       PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM H_fee");
/*  32 */       ResultSet rs = pstmt.executeQuery();
/*  33 */       while (rs.next()) {
/*  34 */         pk = rs.getInt(1);
/*     */       }
/*  36 */       rs.close();
/*  37 */     } catch (Exception e) {
/*  38 */       log.error("Database Exception..", e);
/*  39 */       throw new DatabaseException("Exception : Exception in getting PK");
/*     */     } finally {
/*  41 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*  43 */     log.debug("Model nextPK End");
/*  44 */     return Integer.valueOf(pk + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public FeeBean findByPk(long id) throws ApplicationException {
/*  49 */     log.debug("Model findBy EmailId Started");
/*  50 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Fee  where Id=? ");
/*  51 */     FeeBean bean = null;
/*  52 */     Connection conn = null;
/*     */     try {
/*  54 */       conn = JDBCDataSource.getConnection();
/*  55 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/*  56 */       pstmt.setLong(1, id);
/*  57 */       ResultSet rs = pstmt.executeQuery();
/*  58 */       while (rs.next()) {
/*  59 */         bean = new FeeBean();
/*  60 */         bean.setId(rs.getLong(1));
/*  61 */         bean.setUserId(rs.getLong(2));
/*  62 */         bean.setName(rs.getString(3));
/*  63 */         bean.setHostelId(rs.getLong(4));
/*  64 */         bean.setHostelName(rs.getString(5));
/*  65 */         bean.setRoomId(rs.getLong(6));
/*  66 */         bean.setRoomName(rs.getString(7));
/*  67 */         bean.setTotalfee(rs.getString(8));
/*  68 */         bean.setPay(rs.getString(9));
/*  69 */         bean.setPaidfee(rs.getString(10));
/*  70 */         bean.setRemainingfee(rs.getString(11));
/*  71 */         bean.setCreatedBy(rs.getString(12));
/*  72 */         bean.setModifiedBy(rs.getString(13));
/*  73 */         bean.setCreatedDatetime(rs.getTimestamp(14));
/*  74 */         bean.setModifiedDatetime(rs.getTimestamp(15));
/*     */       } 
/*  76 */       rs.close();
/*  77 */     } catch (Exception e) {
/*  78 */       log.error("Database Exception..", e);
/*  79 */       throw new ApplicationException("Exception : Exception in getting User by Room No");
/*     */     } finally {
/*  81 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*  83 */     log.debug("Model findBy EmailId End");
/*  84 */     return bean;
/*     */   }
/*     */ 
/*     */   
/*     */   public FeeBean findByPkUserId(long id) throws ApplicationException {
/*  89 */     log.debug("Model findBy EmailId Started");
/*  90 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Fee  where UserId=? ");
/*  91 */     FeeBean bean = null;
/*  92 */     Connection conn = null;
/*     */     try {
/*  94 */       conn = JDBCDataSource.getConnection();
/*  95 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/*  96 */       pstmt.setLong(1, id);
/*  97 */       ResultSet rs = pstmt.executeQuery();
/*  98 */       while (rs.next()) {
/*  99 */         bean = new FeeBean();
/* 100 */         bean.setId(rs.getLong(1));
/* 101 */         bean.setUserId(rs.getLong(2));
/* 102 */         bean.setName(rs.getString(3));
/* 103 */         bean.setHostelId(rs.getLong(4));
/* 104 */         bean.setHostelName(rs.getString(5));
/* 105 */         bean.setRoomId(rs.getLong(6));
/* 106 */         bean.setRoomName(rs.getString(7));
/* 107 */         bean.setTotalfee(rs.getString(8));
/* 108 */         bean.setPay(rs.getString(9));
/* 109 */         bean.setPaidfee(rs.getString(10));
/* 110 */         bean.setRemainingfee(rs.getString(11));
/* 111 */         bean.setCreatedBy(rs.getString(12));
/* 112 */         bean.setModifiedBy(rs.getString(13));
/* 113 */         bean.setCreatedDatetime(rs.getTimestamp(14));
/* 114 */         bean.setModifiedDatetime(rs.getTimestamp(15));
/*     */       } 
/* 116 */       rs.close();
/* 117 */     } catch (Exception e) {
/* 118 */       log.error("Database Exception..", e);
/* 119 */       throw new ApplicationException("Exception : Exception in getting User by Room No");
/*     */     } finally {
/* 121 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 123 */     log.debug("Model findBy EmailId End");
/* 124 */     return bean;
/*     */   }
/*     */   
/*     */   public FeeBean findByUserIdAndHostelIdAndRoomId(long userId, long hostelId, long roomId) throws ApplicationException {
/* 128 */     log.debug("Model findBy EmailId Started");
/* 129 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Fee  where userId=? and hostelId=? and roomId=?");
/* 130 */     FeeBean bean = null;
/* 131 */     Connection conn = null;
/*     */     try {
/* 133 */       conn = JDBCDataSource.getConnection();
/* 134 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 135 */       pstmt.setLong(1, userId);
/* 136 */       pstmt.setLong(2, hostelId);
/* 137 */       pstmt.setLong(3, roomId);
/* 138 */       ResultSet rs = pstmt.executeQuery();
/* 139 */       while (rs.next()) {
/* 140 */         bean = new FeeBean();
/* 141 */         bean.setId(rs.getLong(1));
/* 142 */         bean.setUserId(rs.getLong(2));
/* 143 */         bean.setName(rs.getString(3));
/* 144 */         bean.setHostelId(rs.getLong(4));
/* 145 */         bean.setHostelName(rs.getString(5));
/* 146 */         bean.setRoomId(rs.getLong(6));
/* 147 */         bean.setRoomName(rs.getString(7));
/* 148 */         bean.setTotalfee(rs.getString(8));
/* 149 */         bean.setPay(rs.getString(9));
/* 150 */         bean.setPaidfee(rs.getString(10));
/* 151 */         bean.setRemainingfee(rs.getString(11));
/* 152 */         bean.setCreatedBy(rs.getString(12));
/* 153 */         bean.setModifiedBy(rs.getString(13));
/* 154 */         bean.setCreatedDatetime(rs.getTimestamp(14));
/* 155 */         bean.setModifiedDatetime(rs.getTimestamp(15));
/*     */       } 
/* 157 */       rs.close();
/* 158 */     } catch (Exception e) {
/* 159 */       log.error("Database Exception..", e);
/* 160 */       throw new ApplicationException("Exception : Exception in getting User by Room No");
/*     */     } finally {
/* 162 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 164 */     log.debug("Model findBy EmailId End");
/* 165 */     return bean;
/*     */   }
/*     */ 
/*     */   
/*     */   public long add(FeeBean bean) throws ApplicationException, DuplicateRecordException {
/* 170 */     log.debug("Model add Started");
/* 171 */     Connection conn = null;
/* 172 */     int pk = 0;
/*     */ 
/*     */     
/* 175 */     HostelModel hModel = new HostelModel();
/* 176 */     HostelBean hBean = hModel.findByPK(bean.getHostelId());
/*     */     
/* 178 */     UserModel uModel = new UserModel();
/* 179 */     UserBean uBean = uModel.findByPK(bean.getUserId());
/*     */     
/* 181 */     RoomModel rModel = new RoomModel();
/* 182 */     RoomBean rBean = rModel.findByPK(bean.getRoomId());
/*     */ 
/*     */     
/*     */     try {
/* 186 */       conn = JDBCDataSource.getConnection();
/* 187 */       pk = nextPK().intValue();
/*     */ 
/*     */       
/* 190 */       System.out.println(String.valueOf(pk) + " in ModelJDBC");
/* 191 */       conn.setAutoCommit(false);
/* 192 */       PreparedStatement pstmt = conn.prepareStatement("INSERT INTO H_fee VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
/* 193 */       pstmt.setInt(1, pk);
/* 194 */       pstmt.setLong(2, bean.getUserId());
/* 195 */       pstmt.setString(3, String.valueOf(uBean.getFirstName()) + " " + uBean.getLastName());
/* 196 */       pstmt.setLong(4, bean.getHostelId());
/* 197 */       pstmt.setString(5, hBean.getName());
/* 198 */       pstmt.setLong(6, bean.getRoomId());
/* 199 */       pstmt.setString(7, rBean.getRoomNo());
/* 200 */       pstmt.setString(8, bean.getTotalfee());
/* 201 */       pstmt.setString(9, bean.getPay());
/* 202 */       pstmt.setString(10, bean.getPaidfee());
/* 203 */       pstmt.setString(11, bean.getRemainingfee());
/* 204 */       pstmt.setString(12, bean.getCreatedBy());
/* 205 */       pstmt.setString(13, bean.getModifiedBy());
/* 206 */       pstmt.setTimestamp(14, bean.getCreatedDatetime());
/* 207 */       pstmt.setTimestamp(15, bean.getModifiedDatetime());
/* 208 */       pstmt.executeUpdate();
/* 209 */       conn.commit();
/* 210 */       pstmt.close();
/* 211 */     } catch (Exception e) {
/* 212 */       e.printStackTrace();
/* 213 */       log.error("Database Exception..", e);
/*     */       try {
/* 215 */         conn.rollback();
/* 216 */       } catch (Exception ex) {
/* 217 */         throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
/*     */       } 
/* 219 */       throw new ApplicationException("Exception : Exception in add Room");
/*     */     } finally {
/* 221 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 223 */     log.debug("Model add End");
/* 224 */     return pk;
/*     */   }
/*     */   
/*     */   public void delete(FeeBean bean) throws ApplicationException {
/* 228 */     log.debug("Model delete Started");
/* 229 */     Connection conn = null;
/*     */     try {
/* 231 */       conn = JDBCDataSource.getConnection();
/* 232 */       conn.setAutoCommit(false);
/* 233 */       PreparedStatement pstmt = conn.prepareStatement("DELETE FROM H_fee WHERE ID=?");
/* 234 */       pstmt.setLong(1, bean.getId());
/* 235 */       pstmt.executeUpdate();
/* 236 */       conn.commit();
/* 237 */       pstmt.close();
/* 238 */     } catch (Exception e) {
/*     */       
/*     */       try {
/* 241 */         conn.rollback();
/* 242 */       } catch (Exception ex) {
/* 243 */         throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
/*     */       } 
/* 245 */       throw new ApplicationException("Exception : Exception in delete Role");
/*     */     } finally {
/* 247 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 249 */     log.debug("Model delete Started");
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(FeeBean bean) throws ApplicationException, DuplicateRecordException {
/* 254 */     log.debug("Model update Started");
/* 255 */     Connection conn = null;
/*     */ 
/*     */ 
/*     */     
/* 259 */     HostelModel hModel = new HostelModel();
/* 260 */     HostelBean hBean = hModel.findByPK(bean.getHostelId());
/*     */     
/* 262 */     UserModel uModel = new UserModel();
/* 263 */     UserBean uBean = uModel.findByPK(bean.getUserId());
/*     */     
/* 265 */     RoomModel rModel = new RoomModel();
/* 266 */     RoomBean rBean = rModel.findByPK(bean.getRoomId());
/*     */     
/*     */     try {
/* 269 */       conn = JDBCDataSource.getConnection();
/* 270 */       conn.setAutoCommit(false);
/* 271 */       PreparedStatement pstmt = conn.prepareStatement(
/* 272 */           "UPDATE H_fee SET UserId=?,name=?,hostelId=?,hostelName=?,roomId=?,roomName=?,totalfee=?,pay=?,paidfee=?,Remainingfee=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
/* 273 */       pstmt.setLong(1, bean.getUserId());
/* 274 */       pstmt.setString(2, String.valueOf(uBean.getFirstName()) + " " + uBean.getLastName());
/* 275 */       pstmt.setLong(3, bean.getHostelId());
/* 276 */       pstmt.setString(4, hBean.getName());
/* 277 */       pstmt.setLong(5, bean.getRoomId());
/* 278 */       pstmt.setString(6, rBean.getRoomNo());
/* 279 */       pstmt.setString(7, bean.getTotalfee());
/* 280 */       pstmt.setString(8, bean.getPay());
/* 281 */       pstmt.setString(9, bean.getPaidfee());
/* 282 */       pstmt.setString(10, bean.getRemainingfee());
/* 283 */       pstmt.setString(11, bean.getCreatedBy());
/* 284 */       pstmt.setString(12, bean.getModifiedBy());
/* 285 */       pstmt.setTimestamp(13, bean.getCreatedDatetime());
/* 286 */       pstmt.setTimestamp(14, bean.getModifiedDatetime());
/* 287 */       pstmt.setLong(15, bean.getId());
/* 288 */       pstmt.executeUpdate();
/* 289 */       conn.commit();
/* 290 */       pstmt.close();
/* 291 */     } catch (Exception e) {
/* 292 */       log.error("Database Exception..", e);
/*     */       try {
/* 294 */         conn.rollback();
/* 295 */       } catch (Exception ex) {
/* 296 */         throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
/*     */       } 
/* 298 */       throw new ApplicationException("Exception in updating Room ");
/*     */     } finally {
/* 300 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 302 */     log.debug("Model update End");
/*     */   }
/*     */ 
/*     */   
/*     */   public List search(FeeBean bean) throws ApplicationException {
/* 307 */     return search(bean, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List search(FeeBean bean, int pageNo, int pageSize) throws ApplicationException {
/* 313 */     log.debug("Model search Started");
/* 314 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_fee WHERE 1=1");
/* 315 */     if (bean != null) {
/* 316 */       if (bean.getId() > 0L) {
/* 317 */         sql.append(" AND id = " + bean.getId());
/*     */       }
/* 319 */       if (bean.getHostelId() > 0L) {
/* 320 */         sql.append(" AND HostelId = " + bean.getHostelId());
/*     */       }
/* 322 */       if (bean.getUserId() > 0L) {
/* 323 */         sql.append(" AND UserId = " + bean.getUserId());
/*     */       }
/* 325 */       if (bean.getRoomId() > 0L) {
/* 326 */         sql.append(" AND RoomId = " + bean.getRoomId());
/*     */       }
/* 328 */       if (bean.getRoomName() != null && bean.getRoomName().length() > 0) {
/* 329 */         sql.append(" AND RoomName LIKE '" + bean.getName() + "%'");
/*     */       }
/* 331 */       if (bean.getName() != null && bean.getName().length() > 0) {
/* 332 */         sql.append(" AND Name LIKE '" + bean.getName() + "%'");
/*     */       }
/* 334 */       if (bean.getHostelName() != null && bean.getHostelName().length() > 0) {
/* 335 */         sql.append(" AND RoomNo LIKE '" + bean.getHostelName() + "%'");
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 341 */     if (pageSize > 0) {
/*     */       
/* 343 */       pageNo = (pageNo - 1) * pageSize;
/* 344 */       sql.append(" Limit " + pageNo + ", " + pageSize);
/*     */     } 
/*     */     
/* 347 */     ArrayList<FeeBean> list = new ArrayList();
/* 348 */     Connection conn = null;
/*     */     try {
/* 350 */       conn = JDBCDataSource.getConnection();
/* 351 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 352 */       ResultSet rs = pstmt.executeQuery();
/* 353 */       while (rs.next()) {
/* 354 */         bean = new FeeBean();
/* 355 */         bean.setId(rs.getLong(1));
/* 356 */         bean.setUserId(rs.getLong(2));
/* 357 */         bean.setName(rs.getString(3));
/* 358 */         bean.setHostelId(rs.getLong(4));
/* 359 */         bean.setHostelName(rs.getString(5));
/* 360 */         bean.setRoomId(rs.getLong(6));
/* 361 */         bean.setRoomName(rs.getString(7));
/* 362 */         bean.setTotalfee(rs.getString(8));
/* 363 */         bean.setPay(rs.getString(9));
/* 364 */         bean.setPaidfee(rs.getString(10));
/* 365 */         bean.setRemainingfee(rs.getString(11));
/* 366 */         bean.setCreatedBy(rs.getString(12));
/* 367 */         bean.setModifiedBy(rs.getString(13));
/* 368 */         bean.setCreatedDatetime(rs.getTimestamp(14));
/* 369 */         bean.setModifiedDatetime(rs.getTimestamp(15));
/* 370 */         list.add(bean);
/*     */       } 
/* 372 */       rs.close();
/* 373 */     } catch (Exception e) {
/* 374 */       log.error("Database Exception..", e);
/* 375 */       throw new ApplicationException(
/* 376 */           "Exception : Exception in search Role");
/*     */     } finally {
/* 378 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 380 */     log.debug("Model search End");
/* 381 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public List list() throws ApplicationException {
/* 386 */     return list(0, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public List list(int pageNo, int pageSize) throws ApplicationException {
/* 391 */     log.debug("Model list Started");
/* 392 */     ArrayList<FeeBean> list = new ArrayList();
/* 393 */     StringBuffer sql = new StringBuffer("select * from H_Fee");
/*     */     
/* 395 */     if (pageSize > 0) {
/*     */       
/* 397 */       pageNo = (pageNo - 1) * pageSize;
/* 398 */       sql.append(" limit " + pageNo + "," + pageSize);
/*     */     } 
/* 400 */     Connection conn = null;
/*     */     try {
/* 402 */       conn = JDBCDataSource.getConnection();
/* 403 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 404 */       ResultSet rs = pstmt.executeQuery();
/* 405 */       while (rs.next()) {
/* 406 */         FeeBean bean = new FeeBean();
/* 407 */         bean.setId(rs.getLong(1));
/* 408 */         bean.setUserId(rs.getLong(2));
/* 409 */         bean.setName(rs.getString(3));
/* 410 */         bean.setHostelId(rs.getLong(4));
/* 411 */         bean.setHostelName(rs.getString(5));
/* 412 */         bean.setRoomId(rs.getLong(6));
/* 413 */         bean.setRoomName(rs.getString(7));
/* 414 */         bean.setTotalfee(rs.getString(8));
/* 415 */         bean.setPay(rs.getString(9));
/* 416 */         bean.setPaidfee(rs.getString(10));
/* 417 */         bean.setRemainingfee(rs.getString(11));
/* 418 */         bean.setCreatedBy(rs.getString(12));
/* 419 */         bean.setModifiedBy(rs.getString(13));
/* 420 */         bean.setCreatedDatetime(rs.getTimestamp(14));
/* 421 */         bean.setModifiedDatetime(rs.getTimestamp(15));
/* 422 */         list.add(bean);
/*     */       } 
/* 424 */       rs.close();
/* 425 */     } catch (Exception e) {
/*     */       
/* 427 */       throw new ApplicationException(
/* 428 */           "Exception : Exception in getting list of Role");
/*     */     } finally {
/* 430 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 432 */     log.debug("Model list End");
/* 433 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\model\FeeModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */