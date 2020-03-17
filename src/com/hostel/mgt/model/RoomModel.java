/*     */ package com.hostel.mgt.model;
/*     */ 
/*     */ import com.hostel.mgt.bean.RoomBean;
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
/*     */ public class RoomModel
/*     */ {
/*  19 */   private static Logger log = Logger.getLogger(RoomModel.class);
/*     */ 
/*     */   
/*     */   public Integer nextPK() throws DatabaseException {
/*  23 */     log.debug("Model nextPK Started");
/*  24 */     Connection conn = null;
/*  25 */     int pk = 0;
/*     */     try {
/*  27 */       conn = JDBCDataSource.getConnection();
/*  28 */       PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM H_Room");
/*  29 */       ResultSet rs = pstmt.executeQuery();
/*  30 */       while (rs.next()) {
/*  31 */         pk = rs.getInt(1);
/*     */       }
/*  33 */       rs.close();
/*  34 */     } catch (Exception e) {
/*  35 */       log.error("Database Exception..", e);
/*  36 */       throw new DatabaseException("Exception : Exception in getting PK");
/*     */     } finally {
/*  38 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*  40 */     log.debug("Model nextPK End");
/*  41 */     return Integer.valueOf(pk + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public RoomBean findByRoomNo(String roomNo) throws ApplicationException {
/*  46 */     log.debug("Model findBy EmailId Started");
/*  47 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Room  where roomNo=?");
/*  48 */     RoomBean bean = null;
/*  49 */     Connection conn = null;
/*     */     try {
/*  51 */       conn = JDBCDataSource.getConnection();
/*  52 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/*  53 */       pstmt.setString(1, roomNo);
/*  54 */       ResultSet rs = pstmt.executeQuery();
/*  55 */       while (rs.next()) {
/*  56 */         bean = new RoomBean();
/*  57 */         bean.setId(rs.getLong(1));
/*  58 */         bean.setRoomNo(rs.getString(2));
/*  59 */         bean.setHostelId(rs.getLong(3));
/*  60 */         bean.setDescription(rs.getString(4));
/*  61 */         bean.setCreatedBy(rs.getString(5));
/*  62 */         bean.setModifiedBy(rs.getString(6));
/*  63 */         bean.setCreatedDatetime(rs.getTimestamp(7));
/*  64 */         bean.setModifiedDatetime(rs.getTimestamp(8));
/*     */       } 
/*  66 */       rs.close();
/*  67 */     } catch (Exception e) {
/*  68 */       log.error("Database Exception..", e);
/*  69 */       throw new ApplicationException("Exception : Exception in getting User by Room No");
/*     */     } finally {
/*  71 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*  73 */     log.debug("Model findBy EmailId End");
/*  74 */     return bean;
/*     */   }
/*     */ 
/*     */   
/*     */   public RoomBean findByRoomNoAndHostelId(String roomNo, long hostelId) throws ApplicationException {
/*  79 */     log.debug("Model findBy EmailId Started");
/*  80 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Room where roomNo=? and hostelId=?");
/*  81 */     RoomBean bean = null;
/*  82 */     Connection conn = null;
/*     */     try {
/*  84 */       conn = JDBCDataSource.getConnection();
/*  85 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/*  86 */       pstmt.setString(1, roomNo);
/*  87 */       pstmt.setLong(2, hostelId);
/*  88 */       ResultSet rs = pstmt.executeQuery();
/*  89 */       while (rs.next()) {
/*  90 */         bean = new RoomBean();
/*  91 */         bean.setId(rs.getLong(1));
/*  92 */         bean.setRoomNo(rs.getString(2));
/*  93 */         bean.setHostelId(rs.getLong(3));
/*  94 */         bean.setDescription(rs.getString(4));
/*  95 */         bean.setCreatedBy(rs.getString(5));
/*  96 */         bean.setModifiedBy(rs.getString(6));
/*  97 */         bean.setCreatedDatetime(rs.getTimestamp(7));
/*  98 */         bean.setModifiedDatetime(rs.getTimestamp(8));
/*     */       } 
/* 100 */       rs.close();
/* 101 */     } catch (Exception e) {
/* 102 */       log.error("Database Exception..", e);
/* 103 */       throw new ApplicationException("Exception : Exception in getting User by Room No");
/*     */     } finally {
/* 105 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 107 */     log.debug("Model findBy EmailId End");
/* 108 */     return bean;
/*     */   }
/*     */ 
/*     */   
/*     */   public RoomBean findByPK(long pk) throws ApplicationException {
/* 113 */     log.debug("Model findByPK Started");
/* 114 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Room WHERE ID=?");
/* 115 */     RoomBean bean = null;
/* 116 */     Connection conn = null;
/*     */     try {
/* 118 */       conn = JDBCDataSource.getConnection();
/* 119 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 120 */       pstmt.setLong(1, pk);
/* 121 */       ResultSet rs = pstmt.executeQuery();
/* 122 */       while (rs.next()) {
/* 123 */         bean = new RoomBean();
/* 124 */         bean.setId(rs.getLong(1));
/* 125 */         bean.setRoomNo(rs.getString(2));
/* 126 */         bean.setHostelId(rs.getLong(3));
/* 127 */         bean.setDescription(rs.getString(4));
/* 128 */         bean.setCreatedBy(rs.getString(5));
/* 129 */         bean.setModifiedBy(rs.getString(6));
/* 130 */         bean.setCreatedDatetime(rs.getTimestamp(7));
/* 131 */         bean.setModifiedDatetime(rs.getTimestamp(8));
/*     */       } 
/* 133 */       rs.close();
/* 134 */     } catch (Exception e) {
/* 135 */       log.error("Database Exception..", e);
/* 136 */       throw new ApplicationException("Exception : Exception in getting User by pk");
/*     */     } finally {
/* 138 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 140 */     log.debug("Model findByPK End");
/* 141 */     return bean;
/*     */   }
/*     */ 
/*     */   
/*     */   public long add(RoomBean bean) throws ApplicationException, DuplicateRecordException {
/* 146 */     log.debug("Model add Started");
/* 147 */     Connection conn = null;
/* 148 */     int pk = 0;
/* 149 */     RoomBean duplicataRole = findByRoomNoAndHostelId(bean.getRoomNo(), bean.getHostelId());
/*     */ 
/*     */     
/* 152 */     if (duplicataRole != null) {
/* 153 */       throw new DuplicateRecordException("Room  already exists in this Hostel");
/*     */     }
/*     */     try {
/* 156 */       conn = JDBCDataSource.getConnection();
/* 157 */       pk = nextPK().intValue();
/*     */ 
/*     */       
/* 160 */       System.out.println(String.valueOf(pk) + " in ModelJDBC");
/* 161 */       conn.setAutoCommit(false);
/* 162 */       PreparedStatement pstmt = conn.prepareStatement("INSERT INTO H_Room VALUES(?,?,?,?,?,?,?,?)");
/* 163 */       pstmt.setInt(1, pk);
/* 164 */       pstmt.setString(2, bean.getRoomNo());
/* 165 */       pstmt.setLong(3, bean.getHostelId());
/* 166 */       pstmt.setString(4, bean.getDescription());
/* 167 */       pstmt.setString(5, bean.getCreatedBy());
/* 168 */       pstmt.setString(6, bean.getModifiedBy());
/* 169 */       pstmt.setTimestamp(7, bean.getCreatedDatetime());
/* 170 */       pstmt.setTimestamp(8, bean.getModifiedDatetime());
/* 171 */       pstmt.executeUpdate();
/* 172 */       conn.commit();
/* 173 */       pstmt.close();
/* 174 */     } catch (Exception e) {
/* 175 */       e.printStackTrace();
/* 176 */       log.error("Database Exception..", e);
/*     */       try {
/* 178 */         conn.rollback();
/* 179 */       } catch (Exception ex) {
/* 180 */         throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
/*     */       } 
/* 182 */       throw new ApplicationException("Exception : Exception in add Room");
/*     */     } finally {
/* 184 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 186 */     log.debug("Model add End");
/* 187 */     return pk;
/*     */   }
/*     */ 
/*     */   
/*     */   public void delete(RoomBean bean) throws ApplicationException {
/* 192 */     log.debug("Model delete Started");
/* 193 */     Connection conn = null;
/*     */     try {
/* 195 */       conn = JDBCDataSource.getConnection();
/* 196 */       conn.setAutoCommit(false);
/* 197 */       PreparedStatement pstmt = conn.prepareStatement("DELETE FROM H_ROOM WHERE ID=?");
/* 198 */       pstmt.setLong(1, bean.getId());
/* 199 */       pstmt.executeUpdate();
/* 200 */       conn.commit();
/* 201 */       pstmt.close();
/* 202 */     } catch (Exception e) {
/*     */       
/*     */       try {
/* 205 */         conn.rollback();
/* 206 */       } catch (Exception ex) {
/* 207 */         throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
/*     */       } 
/* 209 */       throw new ApplicationException("Exception : Exception in delete Role");
/*     */     } finally {
/* 211 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 213 */     log.debug("Model delete Started");
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(RoomBean bean) throws ApplicationException, DuplicateRecordException {
/* 218 */     log.debug("Model update Started");
/* 219 */     Connection conn = null;
/* 220 */     RoomBean duplicataRole = findByRoomNoAndHostelId(bean.getRoomNo(), bean.getHostelId());
/*     */     
/* 222 */     if (duplicataRole != null && duplicataRole.getId() != bean.getId()) {
/* 223 */       throw new DuplicateRecordException("Room is already exists this hostel");
/*     */     }
/*     */     try {
/* 226 */       conn = JDBCDataSource.getConnection();
/* 227 */       conn.setAutoCommit(false);
/* 228 */       PreparedStatement pstmt = conn.prepareStatement(
/* 229 */           "UPDATE H_Room SET RoomNo=?,hostelId=?,DESCRIPTION=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
/* 230 */       pstmt.setString(1, bean.getRoomNo());
/* 231 */       pstmt.setLong(2, bean.getHostelId());
/* 232 */       pstmt.setString(3, bean.getDescription());
/* 233 */       pstmt.setString(4, bean.getCreatedBy());
/* 234 */       pstmt.setString(5, bean.getModifiedBy());
/* 235 */       pstmt.setTimestamp(6, bean.getCreatedDatetime());
/* 236 */       pstmt.setTimestamp(7, bean.getModifiedDatetime());
/* 237 */       pstmt.setLong(8, bean.getId());
/* 238 */       pstmt.executeUpdate();
/* 239 */       conn.commit();
/* 240 */       pstmt.close();
/* 241 */     } catch (Exception e) {
/* 242 */       log.error("Database Exception..", e);
/*     */       try {
/* 244 */         conn.rollback();
/* 245 */       } catch (Exception ex) {
/* 246 */         throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
/*     */       } 
/* 248 */       throw new ApplicationException("Exception in updating Room ");
/*     */     } finally {
/* 250 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 252 */     log.debug("Model update End");
/*     */   }
/*     */ 
/*     */   
/*     */   public List search(RoomBean bean) throws ApplicationException {
/* 257 */     return search(bean, 0, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public List search(RoomBean bean, int pageNo, int pageSize) throws ApplicationException {
/* 262 */     log.debug("Model search Started");
/* 263 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_ROOM WHERE 1=1");
/* 264 */     if (bean != null) {
/* 265 */       if (bean.getId() > 0L) {
/* 266 */         sql.append(" AND id = " + bean.getId());
/*     */       }
/* 268 */       if (bean.getHostelId() > 0L) {
/* 269 */         sql.append(" AND HostelId = " + bean.getHostelId());
/*     */       }
/* 271 */       if (bean.getRoomNo() != null && bean.getRoomNo().length() > 0) {
/* 272 */         sql.append(" AND RoomNo LIKE '" + bean.getRoomNo() + "%'");
/*     */       }
/* 274 */       if (bean.getDescription() != null && bean.getDescription().length() > 0) {
/* 275 */         sql.append(" AND DESCRIPTION LIKE '" + bean.getDescription() + "%'");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 280 */     if (pageSize > 0) {
/*     */       
/* 282 */       pageNo = (pageNo - 1) * pageSize;
/* 283 */       sql.append(" Limit " + pageNo + ", " + pageSize);
/*     */     } 
/*     */     
/* 286 */     ArrayList<RoomBean> list = new ArrayList();
/* 287 */     Connection conn = null;
/*     */     try {
/* 289 */       conn = JDBCDataSource.getConnection();
/* 290 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 291 */       ResultSet rs = pstmt.executeQuery();
/* 292 */       while (rs.next()) {
/* 293 */         bean = new RoomBean();
/* 294 */         bean.setId(rs.getLong(1));
/* 295 */         bean.setRoomNo(rs.getString(2));
/* 296 */         bean.setHostelId(rs.getLong(3));
/* 297 */         bean.setDescription(rs.getString(4));
/* 298 */         bean.setCreatedBy(rs.getString(5));
/* 299 */         bean.setModifiedBy(rs.getString(6));
/* 300 */         bean.setCreatedDatetime(rs.getTimestamp(7));
/* 301 */         bean.setModifiedDatetime(rs.getTimestamp(8));
/* 302 */         list.add(bean);
/*     */       } 
/* 304 */       rs.close();
/* 305 */     } catch (Exception e) {
/* 306 */       log.error("Database Exception..", e);
/* 307 */       throw new ApplicationException("Exception : Exception in search Role");
/*     */     } finally {
/* 309 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 311 */     log.debug("Model search End");
/* 312 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public List list() throws ApplicationException {
/* 317 */     return list(0, 0);
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
/*     */   public List list(int pageNo, int pageSize) throws ApplicationException {
/* 333 */     log.debug("Model list Started");
/* 334 */     ArrayList<RoomBean> list = new ArrayList();
/* 335 */     StringBuffer sql = new StringBuffer("select * from H_ROOM");
/*     */     
/* 337 */     if (pageSize > 0) {
/*     */       
/* 339 */       pageNo = (pageNo - 1) * pageSize;
/* 340 */       sql.append(" limit " + pageNo + "," + pageSize);
/*     */     } 
/* 342 */     Connection conn = null;
/*     */     try {
/* 344 */       conn = JDBCDataSource.getConnection();
/* 345 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 346 */       ResultSet rs = pstmt.executeQuery();
/* 347 */       while (rs.next()) {
/* 348 */         RoomBean bean = new RoomBean();
/* 349 */         bean.setId(rs.getLong(1));
/* 350 */         bean.setRoomNo(rs.getString(2));
/* 351 */         bean.setHostelId(rs.getLong(3));
/* 352 */         bean.setDescription(rs.getString(4));
/* 353 */         bean.setCreatedBy(rs.getString(5));
/* 354 */         bean.setModifiedBy(rs.getString(6));
/* 355 */         bean.setCreatedDatetime(rs.getTimestamp(7));
/* 356 */         bean.setModifiedDatetime(rs.getTimestamp(8));
/* 357 */         list.add(bean);
/*     */       } 
/* 359 */       rs.close();
/* 360 */     } catch (Exception e) {
/*     */       
/* 362 */       throw new ApplicationException("Exception : Exception in getting list of Role");
/*     */     } finally {
/* 364 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 366 */     log.debug("Model list End");
/* 367 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\model\RoomModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */