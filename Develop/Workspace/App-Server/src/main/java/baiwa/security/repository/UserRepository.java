package baiwa.security.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import baiwa.security.domain.Role;
import baiwa.security.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by nydiarra on 06/05/17.
 */
//public interface UserRepository extends CrudRepository<User, Long> {
//	
//	
//    User findByUsername(String username);
//}
@Repository("userRepository")
public class UserRepository {

	private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public User findByUsername(String username)  {
		logger.debug("UserRepository => findByUsername username:"+ username );
		
		StringBuilder sql =new StringBuilder();
		sql.append(" select * from buckwa.buckwa_user WHERE is_delete = ? AND username = ? ");


		List<Object> paramList = new ArrayList<Object>();
		paramList.add("N");
		paramList.add(username);
		return jdbcTemplate.queryForObject(sql.toString(), paramList.toArray(), new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rownumber) throws SQLException {
				User userMapping = new User();
				userMapping.setIdUser(rs.getString("id_user"));
				userMapping.setUsername(rs.getString("username"));
				userMapping.setPassword(rs.getString("password"));
				userMapping.setEmail(rs.getString("email"));
				userMapping.setFirstName(rs.getString("firstname"));
				userMapping.setLastName(rs.getString("lastname"));
	
				return userMapping;
			}
		});
	}
	
	public List<Role> findRoleUsers(String username){
		logger.info("findAllUsers");

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT buckwa_role.role_name AS role_name  FROM buckwa_user  ");
		sql.append(" INNER JOIN  buckwa_map_user_role ON buckwa_user.id_user = buckwa_map_user_role.id_user ");
		sql.append(" INNER JOIN  buckwa_role ON buckwa_role.id_role = buckwa_map_user_role.id_role ");
		sql.append("  WHERE 1=1 AND username = ?  ");
		
//		params.add("N");
		params.add(username);

		logger.info("SQL=" + sql.toString());

		List<Role> RoleList = jdbcTemplate.query(sql.toString(), params.toArray(), new RowMapper<Role>() {

			@Override
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
				Role roleMap = new Role();
				roleMap.setRoleName(rs.getString("role_name"));

				return roleMap;
			}
		});

		return RoleList;

		
	}
	
	public List<User> findAllUsers(){
		logger.info("findAllUsers");

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT * FROM ( SELECT ROWNUM RNUM, A.*FROM (  ");
		sql.append(" SELECT * FROM MP_CONT_BANK_KEY ");
		sql.append(" WHERE 1=1 AND IS_DELETED = 'N'  ");
		

		logger.info("SQL=" + sql.toString());

		List<User> mpContBankKeyList = jdbcTemplate.query(sql.toString(), params.toArray(), new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User userMap = new User();
//				userMap.setMpId(rs.getInt("MP_ID"));
//				userMap.setSrcName(rs.getString("SRC_NAME"));
//				userMap.setLegCode(rs.getString("LEG_CODE"));
//				userMap.setSapCode(rs.getString("SAP_CODE"));
				return userMap;
			}
		});

		return mpContBankKeyList;

		
	}
}

