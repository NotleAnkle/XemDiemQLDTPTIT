package com.example.BTL.term;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.BTL.DAO;
import com.example.BTL.score.Score;

public class TermDAO extends DAO{
	public TermDAO() {
		super();
	}
	public List<Term> getAllTerm(){
		List<Term> terms = new ArrayList<>();
		
		String sql = "select * from tblterm";
		try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String year = resultSet.getString("year");
				String term = resultSet.getString("term");
				terms.add(new Term(id, year,term));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return terms;
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
