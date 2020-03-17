/*     */ package com.hostel.mgt.model;
/*     */ 
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
/*     */ public class HostelModel
/*     */ {
/*  20 */   private static Logger log = Logger.getLogger(HostelModel.class);
/*     */   
/*     */   public Integer nextPK() throws DatabaseException {
/*  23 */     log.debug("Model nextPK Started");
/*  24 */     Connection conn = null;
/*  25 */     int pk = 0;
/*     */     try {
/*  27 */       conn = JDBCDataSource.getConnection();
/*  28 */       PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM H_Hostel");
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
/*     */   public long add(HostelBean bean) throws ApplicationException, DuplicateRecordException {
/*  45 */     log.debug("Model add Started");
/*  46 */     Connection conn = null;
/*  47 */     int pk = 0;
/*  48 */     HostelBean duplicataRole = findByName(bean.getName());
/*     */ 
/*     */     
/*  51 */     if (duplicataRole != null) {
/*  52 */       throw new DuplicateRecordException("Hostel already exists");
/*     */     }
/*     */     try {
/*  55 */       conn = JDBCDataSource.getConnection();
/*  56 */       pk = nextPK().intValue();
/*     */ 
/*     */       
/*  59 */       System.out.println(String.valueOf(pk) + " in ModelJDBC");
/*  60 */       conn.setAutoCommit(false);
/*  61 */       PreparedStatement pstmt = conn.prepareStatement("INSERT INTO H_Hostel VALUES(?,?,?,?,?,?,?,?,?,?,?)");
/*  62 */       pstmt.setInt(1, pk);
/*  63 */       pstmt.setString(2, bean.getName());
/*  64 */       pstmt.setString(3, bean.getType());
/*  65 */       pstmt.setString(4, bean.getContact());
/*  66 */       pstmt.setString(5, bean.getAddress());
/*  67 */       pstmt.setString(6, bean.getDescription());
/*  68 */       pstmt.setString(7, bean.getCreatedBy());
/*  69 */       pstmt.setString(8, bean.getModifiedBy());
/*  70 */       pstmt.setTimestamp(9, bean.getCreatedDatetime());
/*  71 */       pstmt.setTimestamp(10, bean.getModifiedDatetime());
/*  72 */       pstmt.setString(11, bean.getFee());
/*  73 */       pstmt.executeUpdate();
/*  74 */       conn.commit();
/*  75 */       pstmt.close();
/*  76 */     } catch (Exception e) {
/*  77 */       e.printStackTrace();
/*  78 */       log.error("Database Exception..", e);
/*     */       try {
/*  80 */         conn.rollback();
/*  81 */       } catch (Exception ex) {
/*  82 */         throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
/*     */       } 
/*  84 */       throw new ApplicationException("Exception : Exception in add Hostel");
/*     */     } finally {
/*  86 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*  88 */     log.debug("Model add End");
/*  89 */     return pk;
/*     */   }
/*     */   
/*     */   public HostelBean findByName(String name) throws ApplicationException {
/*  93 */     log.debug("Model findBy Name Started");
/*  94 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Hostel WHERE NAME=?");
/*  95 */     HostelBean bean = null;
/*  96 */     Connection conn = null;
/*     */     try {
/*  98 */       conn = JDBCDataSource.getConnection();
/*  99 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 100 */       pstmt.setString(1, name);
/* 101 */       ResultSet rs = pstmt.executeQuery();
/* 102 */       while (rs.next()) {
/* 103 */         bean = new HostelBean();
/* 104 */         bean.setId(rs.getLong(1));
/* 105 */         bean.setName(rs.getString(2));
/* 106 */         bean.setType(rs.getString(3));
/* 107 */         bean.setContact(rs.getString(4));
/* 108 */         bean.setAddress(rs.getString(5));
/* 109 */         bean.setDescription(rs.getString(6));
/* 110 */         bean.setCreatedBy(rs.getString(7));
/* 111 */         bean.setModifiedBy(rs.getString(8));
/* 112 */         bean.setCreatedDatetime(rs.getTimestamp(9));
/* 113 */         bean.setModifiedDatetime(rs.getTimestamp(10));
/* 114 */         bean.setFee(rs.getString(11));
/*     */       } 
/* 116 */       rs.close();
/* 117 */     } catch (Exception e) {
/* 118 */       log.error("Database Exception..", e);
/* 119 */       throw new ApplicationException("Exception : Exception in getting Hostel in Name");
/*     */     } finally {
/* 121 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 123 */     log.debug("Model findBy Name End");
/* 124 */     return bean;
/*     */   }
/*     */   
/*     */   public HostelBean findByPK(long pk) throws ApplicationException {
/* 128 */     log.debug("Model findByPK Started");
/* 129 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_HOSTEL WHERE ID=?");
/* 130 */     HostelBean bean = null;
/* 131 */     Connection conn = null;
/*     */     try {
/* 133 */       conn = JDBCDataSource.getConnection();
/* 134 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 135 */       pstmt.setLong(1, pk);
/* 136 */       ResultSet rs = pstmt.executeQuery();
/* 137 */       while (rs.next()) {
/* 138 */         bean = new HostelBean();
/* 139 */         bean.setId(rs.getLong(1));
/* 140 */         bean.setName(rs.getString(2));
/* 141 */         bean.setType(rs.getString(3));
/* 142 */         bean.setContact(rs.getString(4));
/* 143 */         bean.setAddress(rs.getString(5));
/* 144 */         bean.setDescription(rs.getString(6));
/* 145 */         bean.setCreatedBy(rs.getString(7));
/* 146 */         bean.setModifiedBy(rs.getString(8));
/* 147 */         bean.setCreatedDatetime(rs.getTimestamp(9));
/* 148 */         bean.setModifiedDatetime(rs.getTimestamp(10));
/* 149 */         bean.setFee(rs.getString(11));
/*     */       } 
/* 151 */       rs.close();
/* 152 */     } catch (Exception e) {
/* 153 */       log.error("Database Exception..", e);
/* 154 */       throw new ApplicationException("Exception : Exception in getting Hostel by pk");
/*     */     } finally {
/* 156 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 158 */     log.debug("Model findByPK End");
/* 159 */     return bean;
/*     */   }
/*     */   
/*     */   public void delete(HostelBean bean) throws ApplicationException {
/* 163 */     log.debug("Model delete Started");
/* 164 */     Connection conn = null;
/*     */     try {
/* 166 */       conn = JDBCDataSource.getConnection();
/* 167 */       conn.setAutoCommit(false);
/* 168 */       PreparedStatement pstmt = conn.prepareStatement("DELETE FROM H_Hostel WHERE ID=?");
/* 169 */       pstmt.setLong(1, bean.getId());
/* 170 */       pstmt.executeUpdate();
/* 171 */       conn.commit();
/* 172 */       pstmt.close();
/* 173 */     } catch (Exception e) {
/*     */       
/*     */       try {
/* 176 */         conn.rollback();
/* 177 */       } catch (Exception ex) {
/* 178 */         throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
/*     */       } 
/* 180 */       throw new ApplicationException("Exception : Exception in delete Role");
/*     */     } finally {
/* 182 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 184 */     log.debug("Model delete Started");
/*     */   }
/*     */   
/*     */   public void update(HostelBean bean) throws ApplicationException, DuplicateRecordException {
/* 188 */     log.debug("Model update Started");
/* 189 */     Connection conn = null;
/* 190 */     HostelBean duplicataRole = findByName(bean.getName());
/*     */ 
/*     */     
/* 193 */     if (duplicataRole != null && duplicataRole.getId() != bean.getId()) {
/* 194 */       throw new DuplicateRecordException("Hostel already exists");
/*     */     }
/*     */     try {
/* 197 */       conn = JDBCDataSource.getConnection();
/* 198 */       conn.setAutoCommit(false);
/* 199 */       PreparedStatement pstmt = conn.prepareStatement(
/* 200 */           "UPDATE H_Hostel SET NAME=?,type=?,Contact=?,Address=?,DESCRIPTION=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=?,fee=? WHERE ID=?");
/* 201 */       pstmt.setString(1, bean.getName());
/* 202 */       pstmt.setString(2, bean.getType());
/* 203 */       pstmt.setString(3, bean.getContact());
/* 204 */       pstmt.setString(4, bean.getAddress());
/* 205 */       pstmt.setString(5, bean.getDescription());
/* 206 */       pstmt.setString(6, bean.getCreatedBy());
/* 207 */       pstmt.setString(7, bean.getModifiedBy());
/* 208 */       pstmt.setTimestamp(8, bean.getCreatedDatetime());
/* 209 */       pstmt.setTimestamp(9, bean.getModifiedDatetime());
/* 210 */       pstmt.setString(10, bean.getFee());
/* 211 */       pstmt.setLong(11, bean.getId());
/* 212 */       pstmt.executeUpdate();
/* 213 */       conn.commit();
/* 214 */       pstmt.close();
/* 215 */     } catch (Exception e) {
/* 216 */       log.error("Database Exception..", e);
/*     */       try {
/* 218 */         conn.rollback();
/* 219 */       } catch (Exception ex) {
/* 220 */         throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
/*     */       } 
/* 222 */       throw new ApplicationException("Exception in updating Hostel ");
/*     */     } finally {
/* 224 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 226 */     log.debug("Model update End");
/*     */   }
/*     */   
/*     */   public List search(HostelBean bean) throws ApplicationException {
/* 230 */     return search(bean, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List search(HostelBean bean, int pageNo, int pageSize) throws ApplicationException {
/* 236 */     log.debug("Model search Started");
/* 237 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Hostel WHERE 1=1");
/* 238 */     if (bean != null) {
/* 239 */       if (bean.getId() > 0L) {
/* 240 */         sql.append(" AND id = " + bean.getId());
/*     */       }
/* 242 */       if (bean.getName() != null && bean.getName().length() > 0) {
/* 243 */         sql.append(" AND NAME LIKE '" + bean.getName() + "%'");
/*     */       }
/* 245 */       if (bean.getDescription() != null && 
/* 246 */         bean.getDescription().length() > 0) {
/* 247 */         sql.append(" AND DESCRIPTION LIKE '" + bean.getDescription() + 
/* 248 */             "%'");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 253 */     if (pageSize > 0) {
/*     */       
/* 255 */       pageNo = (pageNo - 1) * pageSize;
/* 256 */       sql.append(" Limit " + pageNo + ", " + pageSize);
/*     */     } 
/*     */     
/* 259 */     ArrayList<HostelBean> list = new ArrayList();
/* 260 */     Connection conn = null;
/*     */     try {
/* 262 */       conn = JDBCDataSource.getConnection();
/* 263 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 264 */       ResultSet rs = pstmt.executeQuery();
/* 265 */       while (rs.next()) {
/* 266 */         bean = new HostelBean();
/* 267 */         bean.setId(rs.getLong(1));
/* 268 */         bean.setName(rs.getString(2));
/* 269 */         bean.setType(rs.getString(3));
/* 270 */         bean.setContact(rs.getString(4));
/* 271 */         bean.setAddress(rs.getString(5));
/* 272 */         bean.setDescription(rs.getString(6));
/* 273 */         bean.setCreatedBy(rs.getString(7));
/* 274 */         bean.setModifiedBy(rs.getString(8));
/* 275 */         bean.setCreatedDatetime(rs.getTimestamp(9));
/* 276 */         bean.setModifiedDatetime(rs.getTimestamp(10));
/* 277 */         bean.setFee(rs.getString(11));
/* 278 */         list.add(bean);
/*     */       } 
/* 280 */       rs.close();
/* 281 */     } catch (Exception e) {
/* 282 */       log.error("Database Exception..", e);
/* 283 */       throw new ApplicationException(
/* 284 */           "Exception : Exception in search Hostel");
/*     */     } finally {
/* 286 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 288 */     log.debug("Model search End");
/* 289 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public List list() throws ApplicationException {
/* 294 */     return list(0, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public List list(int pageNo, int pageSize) throws ApplicationException {
/* 299 */     log.debug("Model list Started");
/* 300 */     ArrayList<HostelBean> list = new ArrayList();
/* 301 */     StringBuffer sql = new StringBuffer("select * from H_HOSTEL");
/*     */     
/* 303 */     if (pageSize > 0) {
/*     */       
/* 305 */       pageNo = (pageNo - 1) * pageSize;
/* 306 */       sql.append(" limit " + pageNo + "," + pageSize);
/*     */     } 
/* 308 */     Connection conn = null;
/*     */     try {
/* 310 */       conn = JDBCDataSource.getConnection();
/* 311 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 312 */       ResultSet rs = pstmt.executeQuery();
/* 313 */       while (rs.next()) {
/* 314 */         HostelBean bean = new HostelBean();
/* 315 */         bean.setId(rs.getLong(1));
/* 316 */         bean.setName(rs.getString(2));
/* 317 */         bean.setType(rs.getString(3));
/* 318 */         bean.setContact(rs.getString(4));
/* 319 */         bean.setAddress(rs.getString(5));
/* 320 */         bean.setDescription(rs.getString(6));
/* 321 */         bean.setCreatedBy(rs.getString(7));
/* 322 */         bean.setModifiedBy(rs.getString(8));
/* 323 */         bean.setCreatedDatetime(rs.getTimestamp(9));
/* 324 */         bean.setModifiedDatetime(rs.getTimestamp(10));
/* 325 */         bean.setFee(rs.getString(11));
/* 326 */         list.add(bean);
/*     */       } 
/* 328 */       rs.close();
/* 329 */     } catch (Exception e) {
/*     */       
/* 331 */       throw new ApplicationException(
/* 332 */           "Exception : Exception in getting list of Hostel");
/*     */     } finally {
/* 334 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 336 */     log.debug("Model list End");
/* 337 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\model\HostelModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */