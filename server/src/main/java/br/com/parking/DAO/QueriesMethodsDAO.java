package br.com.parking.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import br.com.parking.model.ParkingLot;


public class QueriesMethodsDAO<T> extends DAO{
	private String query;
	private Connection con;
	private PreparedStatement pst;
	
	public QueriesMethodsDAO<T> query(String query) throws SQLException {
		this.query = query;
		this.con = super.connect();
		this.pst = this.con.prepareStatement(this.query);
		
		return this;
	}
	
	public QueriesMethodsDAO<T> setIntParam(int pos, int param) throws SQLException {
		this.pst.setInt(pos, param);
		
		return this;
	}
	
	public QueriesMethodsDAO<T> setDecimalParam(int pos, BigDecimal param) throws SQLException {
		this.pst.setBigDecimal(pos, param);
		
		return this;
	}
	
	public QueriesMethodsDAO<T> setStringParam(int pos, String param) throws SQLException {
		this.pst.setString(pos, param);
		return this;
	}
	
	public QueriesMethodsDAO<T> setDateParam(int pos, Date param) throws SQLException {
		this.pst.setDate(pos, param);
		return this;
	}
	
	public CachedRowSet execute() throws SQLException {
		boolean isResultSet = this.pst.execute();
		ResultSet rs = null;

		RowSetFactory factory = RowSetProvider.newFactory();
		CachedRowSet rowset = factory.createCachedRowSet();
		
		if(isResultSet) {
			rs = pst.getResultSet();
			
			rowset.populate(rs);
		}
		
		super.closeConnection(con);
		return rowset;
	}

}
