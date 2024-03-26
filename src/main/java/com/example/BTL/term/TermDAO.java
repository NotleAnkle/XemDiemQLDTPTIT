package com.example.BTL.term;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.BTL.DAO;

public class TermDAO extends DAO{
	public TermDAO() {
		super();
	}
	public Term getTermById(int id) {
		Term term = new Term();
		String sql = "select * from tblterm where id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
            	term.setId(id);
            	term.setTerm(rs.getString("term"));
            	term.setYear(rs.getString("year"));
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
		return term;
	}
}
