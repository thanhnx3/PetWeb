package vnua.fita.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import vnua.fita.bean.Pet;
import vnua.fita.service.PetService;

public class PetServiceImpl implements PetService {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection conn;

	public PetServiceImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public List<Pet> search(String keyword) {
		List<Pet> result = new LinkedList<Pet>();
		PreparedStatement pst = null;
		try {
			conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
			String sqlCommand = "Select * FROM tblpet ";

			if ((keyword != null && !"".equals(keyword))) {
				sqlCommand += "WHERE " + "name LIKE ? " + " OR " + "type LIKE ?";
			}

			pst = conn.prepareStatement(sqlCommand);
			if ((keyword != null && !"".equals(keyword))) {
				pst.setString(1, "%" + keyword.toLowerCase() + "%");
				pst.setString(2, "%" + keyword.toLowerCase() + "%");
			}

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt(1);
				String name = rs.getString(2);
				String preview = rs.getString(3);
				String description = rs.getString(4);
				Integer price = rs.getInt(5);
				String type = rs.getString(6);
				String male = rs.getString(7);
				String color = rs.getString(8);
				String size = rs.getString(9);
				Pet pet = new Pet(id, name, preview, description, price, type, male, color, size);
				result.add(pet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.closeConnect(conn);
			DBConnection.closeStatement(pst);
		}
		return result;
	}

	@Override
	public boolean insertPet(Pet pet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePet(Pet pet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePet(int petId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pet selectPet(int petId) {
		// TODO Auto-generated method stub
		return null;
	}

}
