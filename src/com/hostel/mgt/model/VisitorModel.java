/*     */ package com.hostel.mgt.model;
/*     */ 
/*     */ import com.hostel.mgt.bean.VisitorBean;
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
/*     */ public class VisitorModel
/*     */ {
/*  20 */   private static Logger log = Logger.getLogger(VisitorModel.class);
/*     */   
/*     */   public Integer nextPK() throws DatabaseException {
/*  23 */     log.debug("Model nextPK Started");
/*  24 */     Connection conn = null;
/*  25 */     int pk = 0;
/*     */     try {
/*  27 */       conn = JDBCDataSource.getConnection();
/*  28 */       PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM H_Visitor");
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
/*     */   public VisitorBean findByName(String name) throws ApplicationException {
/*  45 */     log.debug("Model findBy Name Started");
/*  46 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Visitor WHERE NAME=?");
/*  47 */     VisitorBean bean = null;
/*  48 */     Connection conn = null;
/*     */     try {
/*  50 */       conn = JDBCDataSource.getConnection();
/*  51 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/*  52 */       pstmt.setString(1, name);
/*  53 */       ResultSet rs = pstmt.executeQuery();
/*  54 */       while (rs.next()) {
/*  55 */         bean = new VisitorBean();
/*  56 */         bean.setId(rs.getLong(1));
/*  57 */         bean.setName(rs.getString(2));
/*  58 */         bean.setContactNo(rs.getString(3));
/*  59 */         bean.setStudentName(rs.getString(4));
/*  60 */         bean.setAddress(rs.getString(5));
/*  61 */         bean.setRelation(rs.getString(6));
/*  62 */         bean.setPurpose(rs.getString(7));
/*  63 */         bean.setCreatedBy(rs.getString(8));
/*  64 */         bean.setModifiedBy(rs.getString(9));
/*  65 */         bean.setCreatedDatetime(rs.getTimestamp(10));
/*  66 */         bean.setModifiedDatetime(rs.getTimestamp(11));
/*     */       } 
/*  68 */       rs.close();
/*  69 */     } catch (Exception e) {
/*  70 */       log.error("Database Exception..", e);
/*  71 */       throw new ApplicationException("Exception : Exception in getting Hostel in Name");
/*     */     } finally {
/*  73 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/*  75 */     log.debug("Model findBy Name End");
/*  76 */     return bean;
/*     */   }
/*     */ 
/*     */   
/*     */   public VisitorBean findByPK(long Id) throws ApplicationException {
/*  81 */     log.debug("Model findBy Name Started");
/*  82 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_Visitor WHERE Id=?");
/*  83 */     VisitorBean bean = null;
/*  84 */     Connection conn = null;
/*     */     try {
/*  86 */       conn = JDBCDataSource.getConnection();
/*  87 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/*  88 */       pstmt.setLong(1, Id);
/*  89 */       ResultSet rs = pstmt.executeQuery();
/*  90 */       while (rs.next()) {
/*  91 */         bean = new VisitorBean();
/*  92 */         bean.setId(rs.getLong(1));
/*  93 */         bean.setName(rs.getString(2));
/*  94 */         bean.setContactNo(rs.getString(3));
/*  95 */         bean.setStudentName(rs.getString(4));
/*  96 */         bean.setAddress(rs.getString(5));
/*  97 */         bean.setRelation(rs.getString(6));
/*  98 */         bean.setPurpose(rs.getString(7));
/*  99 */         bean.setCreatedBy(rs.getString(8));
/* 100 */         bean.setModifiedBy(rs.getString(9));
/* 101 */         bean.setCreatedDatetime(rs.getTimestamp(10));
/* 102 */         bean.setModifiedDatetime(rs.getTimestamp(11));
/*     */       } 
/* 104 */       rs.close();
/* 105 */     } catch (Exception e) {
/* 106 */       log.error("Database Exception..", e);
/* 107 */       throw new ApplicationException("Exception : Exception in getting Hostel in Name");
/*     */     } finally {
/* 109 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 111 */     log.debug("Model findBy Name End");
/* 112 */     return bean;
/*     */   }
/*     */   
/*     */   public long add(VisitorBean bean) throws ApplicationException, DuplicateRecordException {
/* 116 */     log.debug("Model add Started");
/* 117 */     Connection conn = null;
/* 118 */     int pk = 0;
/*     */     
/*     */     try {
/* 121 */       conn = JDBCDataSource.getConnection();
/* 122 */       pk = nextPK().intValue();
/*     */ 
/*     */       
/* 125 */       System.out.println(String.valueOf(pk) + " in ModelJDBC");
/* 126 */       conn.setAutoCommit(false);
/* 127 */       PreparedStatement pstmt = conn.prepareStatement("INSERT INTO H_visitor VALUES(?,?,?,?,?,?,?,?,?,?,?)");
/* 128 */       pstmt.setInt(1, pk);
/* 129 */       pstmt.setString(2, bean.getName());
/* 130 */       pstmt.setString(3, bean.getContactNo());
/* 131 */       pstmt.setString(4, bean.getStudentName());
/* 132 */       pstmt.setString(5, bean.getAddress());
/* 133 */       pstmt.setString(6, bean.getRelation());
/* 134 */       pstmt.setString(7, bean.getPurpose());
/* 135 */       pstmt.setString(8, bean.getCreatedBy());
/* 136 */       pstmt.setString(9, bean.getModifiedBy());
/* 137 */       pstmt.setTimestamp(10, bean.getCreatedDatetime());
/* 138 */       pstmt.setTimestamp(11, bean.getModifiedDatetime());
/* 139 */       pstmt.executeUpdate();
/* 140 */       conn.commit();
/* 141 */       pstmt.close();
/* 142 */     } catch (Exception e) {
/* 143 */       e.printStackTrace();
/* 144 */       log.error("Database Exception..", e);
/*     */       try {
/* 146 */         conn.rollback();
/* 147 */       } catch (Exception ex) {
/* 148 */         throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
/*     */       } 
/* 150 */       throw new ApplicationException("Exception : Exception in add Hostel");
/*     */     } finally {
/* 152 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 154 */     log.debug("Model add End");
/* 155 */     return pk;
/*     */   }
/*     */   
/*     */   public void delete(VisitorBean bean) throws ApplicationException {
/* 159 */     log.debug("Model delete Started");
/* 160 */     Connection conn = null;
/*     */     try {
/* 162 */       conn = JDBCDataSource.getConnection();
/* 163 */       conn.setAutoCommit(false);
/* 164 */       PreparedStatement pstmt = conn.prepareStatement("DELETE FROM H_Visitor WHERE ID=?");
/* 165 */       pstmt.setLong(1, bean.getId());
/* 166 */       pstmt.executeUpdate();
/* 167 */       conn.commit();
/* 168 */       pstmt.close();
/* 169 */     } catch (Exception e) {
/*     */       
/*     */       try {
/* 172 */         conn.rollback();
/* 173 */       } catch (Exception ex) {
/* 174 */         throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
/*     */       } 
/* 176 */       throw new ApplicationException("Exception : Exception in delete Role");
/*     */     } finally {
/* 178 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 180 */     log.debug("Model delete Started");
/*     */   }
/*     */   
/*     */   public void update(VisitorBean bean) throws ApplicationException, DuplicateRecordException {
/* 184 */     log.debug("Model update Started");
/* 185 */     Connection conn = null;
/*     */ 
/*     */     
/*     */     try {
/* 189 */       conn = JDBCDataSource.getConnection();
/* 190 */       conn.setAutoCommit(false);
/* 191 */       PreparedStatement pstmt = conn.prepareStatement(
/* 192 */           "UPDATE H_Visitor SET NAME=?,ContactNo=?,StudentName=?,Address=?,relation=?,purpose=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
/* 193 */       pstmt.setString(1, bean.getName());
/* 194 */       pstmt.setString(2, bean.getContactNo());
/* 195 */       pstmt.setString(3, bean.getStudentName());
/* 196 */       pstmt.setString(4, bean.getAddress());
/* 197 */       pstmt.setString(5, bean.getRelation());
/* 198 */       pstmt.setString(6, bean.getPurpose());
/* 199 */       pstmt.setString(7, bean.getCreatedBy());
/* 200 */       pstmt.setString(8, bean.getModifiedBy());
/* 201 */       pstmt.setTimestamp(9, bean.getCreatedDatetime());
/* 202 */       pstmt.setTimestamp(10, bean.getModifiedDatetime());
/* 203 */       pstmt.setLong(11, bean.getId());
/* 204 */       pstmt.executeUpdate();
/* 205 */       conn.commit();
/* 206 */       pstmt.close();
/* 207 */     } catch (Exception e) {
/* 208 */       log.error("Database Exception..", e);
/* 209 */       System.err.println("ex " + e);
/*     */       
/*     */       try {
/* 212 */         conn.rollback();
/* 213 */       } catch (Exception ex) {
/* 214 */         throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
/*     */       } 
/* 216 */       throw new ApplicationException("Exception in updating Hostel ");
/*     */     } finally {
/* 218 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 220 */     log.debug("Model update End");
/*     */   }
/*     */   
/*     */   public List search(VisitorBean bean) throws ApplicationException {
/* 224 */     return search(bean, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List search(VisitorBean bean, int pageNo, int pageSize) throws ApplicationException {
/* 230 */     log.debug("Model search Started");
/* 231 */     StringBuffer sql = new StringBuffer("SELECT * FROM H_visitor WHERE 1=1");
/* 232 */     if (bean != null) {
/* 233 */       if (bean.getId() > 0L) {
/* 234 */         sql.append(" AND id = " + bean.getId());
/*     */       }
/* 236 */       if (bean.getName() != null && bean.getName().length() > 0) {
/* 237 */         sql.append(" AND NAME LIKE '" + bean.getName() + "%'");
/*     */       }
/* 239 */       if (bean.getStudentName() != null && 
/* 240 */         bean.getStudentName().length() > 0) {
/* 241 */         sql.append(" AND DESCRIPTION LIKE '" + bean.getStudentName() + 
/* 242 */             "%'");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 247 */     if (pageSize > 0) {
/*     */       
/* 249 */       pageNo = (pageNo - 1) * pageSize;
/* 250 */       sql.append(" Limit " + pageNo + ", " + pageSize);
/*     */     } 
/*     */     
/* 253 */     ArrayList<VisitorBean> list = new ArrayList();
/* 254 */     Connection conn = null;
/*     */     try {
/* 256 */       conn = JDBCDataSource.getConnection();
/* 257 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 258 */       ResultSet rs = pstmt.executeQuery();
/* 259 */       while (rs.next()) {
/* 260 */         bean = new VisitorBean();
/* 261 */         bean.setId(rs.getLong(1));
/* 262 */         bean.setName(rs.getString(2));
/* 263 */         bean.setContactNo(rs.getString(3));
/* 264 */         bean.setStudentName(rs.getString(4));
/* 265 */         bean.setAddress(rs.getString(5));
/* 266 */         bean.setRelation(rs.getString(6));
/* 267 */         bean.setPurpose(rs.getString(7));
/* 268 */         bean.setCreatedBy(rs.getString(8));
/* 269 */         bean.setModifiedBy(rs.getString(9));
/* 270 */         bean.setCreatedDatetime(rs.getTimestamp(10));
/* 271 */         bean.setModifiedDatetime(rs.getTimestamp(11));
/* 272 */         list.add(bean);
/*     */       } 
/* 274 */       rs.close();
/* 275 */     } catch (Exception e) {
/* 276 */       log.error("Database Exception..", e);
/* 277 */       throw new ApplicationException(
/* 278 */           "Exception : Exception in search Hostel");
/*     */     } finally {
/* 280 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 282 */     log.debug("Model search End");
/* 283 */     return list;
/*     */   }
/*     */   
/*     */   public List list() throws ApplicationException {
/* 287 */     return list(0, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public List list(int pageNo, int pageSize) throws ApplicationException {
/* 292 */     log.debug("Model list Started");
/* 293 */     ArrayList<VisitorBean> list = new ArrayList();
/* 294 */     StringBuffer sql = new StringBuffer("select * from H_VISITOR");
/*     */     
/* 296 */     if (pageSize > 0) {
/*     */       
/* 298 */       pageNo = (pageNo - 1) * pageSize;
/* 299 */       sql.append(" limit " + pageNo + "," + pageSize);
/*     */     } 
/* 301 */     Connection conn = null;
/*     */     try {
/* 303 */       conn = JDBCDataSource.getConnection();
/* 304 */       PreparedStatement pstmt = conn.prepareStatement(sql.toString());
/* 305 */       ResultSet rs = pstmt.executeQuery();
/* 306 */       while (rs.next()) {
/* 307 */         VisitorBean bean = new VisitorBean();
/* 308 */         bean.setId(rs.getLong(1));
/* 309 */         bean.setName(rs.getString(2));
/* 310 */         bean.setContactNo(rs.getString(3));
/* 311 */         bean.setStudentName(rs.getString(4));
/* 312 */         bean.setAddress(rs.getString(5));
/* 313 */         bean.setRelation(rs.getString(6));
/* 314 */         bean.setPurpose(rs.getString(7));
/* 315 */         bean.setCreatedBy(rs.getString(8));
/* 316 */         bean.setModifiedBy(rs.getString(9));
/* 317 */         bean.setCreatedDatetime(rs.getTimestamp(10));
/* 318 */         bean.setModifiedDatetime(rs.getTimestamp(11));
/* 319 */         list.add(bean);
/*     */       } 
/* 321 */       rs.close();
/* 322 */     } catch (Exception e) {
/*     */       
/* 324 */       throw new ApplicationException(
/* 325 */           "Exception : Exception in getting list of Hostel");
/*     */     } finally {
/* 327 */       JDBCDataSource.closeConnection(conn);
/*     */     } 
/* 329 */     log.debug("Model list End");
/* 330 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\jayswkru\Documents\mahima\Mos-Hostel-Management\WEB-INF\classes\com.jar!\com\hostel\mgt\model\VisitorModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */