package com.opportunities.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.opportunities.dao.OpportunityDAOCustom;
import com.opportunities.model.LocationCount;
import com.opportunities.model.Opportunity;

public class OpportunityDAOCustomImpl implements OpportunityDAOCustom {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String GET_ALL = "SELECT * FROM opportunity";
	private final String INSERT_OPPORTUNITY = "INSERT INTO opportunity(contact_number,expected_duration,hiring_manager,location,manager_email,opportunity_name,skills,id) values(?,?,?,?,?,?,?,?)";
	private final String UPDATE_OPPORTUNITY = "UPDATE opportunity set contact_number=?,expected_duration=?,hiring_manager=?,location=?,manager_email=?,opportunity_name=?,skills=? WHERE id=?";
	private final String DELETE_OPPORTUNITY = "DELETE FROM opportunity WHERE id = ?";

	private final String CHECK_USER = "SELECT count(*) FROM user WHERE id = ?";

	public List<Opportunity> getAllOpportunities() {

		return jdbcTemplate.query("select * from opportunity",
				(rs, rowNum) -> new Opportunity(rs.getInt("id"), rs.getString("opportunity_name"),
						rs.getString("hiring_manager"), rs.getString("manager_email"), rs.getString("contact_number"),
						rs.getString("location"), rs.getString("skills"), rs.getString("expected_duration")));

	}

	public boolean updateOpportunity(Opportunity o) {
		if (jdbcTemplate.update(UPDATE_OPPORTUNITY, o.getContactNumber(), o.getExpectedDuration(), o.getHiringManager(),
				o.getLocation(), o.getManagerEmail(), o.getOpportunityName(), o.getSkills(), o.getId()) > 0)
			return true;
		else {
			return false;
		}
	}

	public boolean addOpportunity(Opportunity o) {

		if (jdbcTemplate.update(INSERT_OPPORTUNITY, o.getContactNumber(), o.getExpectedDuration(), o.getHiringManager(),
				o.getLocation(), o.getManagerEmail(), o.getOpportunityName(), o.getSkills(), o.getId()) > 0)
			return true;
		else {
			return false;
		}

	}

	public List<LocationCount> getLocation() {

		return jdbcTemplate.query("select location,count(*) as count from opportunity group by location",
				(rs, rowNum) -> new LocationCount(rs.getString("location"), rs.getInt("count"))

		);

	}

	public boolean checkUser(String token) {
		
		if (jdbcTemplate.queryForObject(CHECK_USER, new Object[] { token }, Integer.class) > 0) {
			return true;
		} else {
			return false;
		}
	}

}