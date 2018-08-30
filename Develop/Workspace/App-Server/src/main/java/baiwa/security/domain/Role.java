package baiwa.security.domain;

import javax.persistence.*;

/**
 * Created by nydiarra on 06/05/17.
 */

public class Role {
   
    private String idRole;
    private String roleName;
    private String description;




    public String getIdRole() {
		return idRole;
	}

	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
