package com.cms.domains;

public class Module {

	private String moduleCode;
	private String userID;
	private Boolean canView;
	private Boolean canEdit;
	private Boolean canAdd;
	private Boolean canDelete;
	
	public Module() {
		this.moduleCode = "";
		this.userID = "";
		this.canView = false;
		this.canEdit = false;
		this.canAdd = false;
		this.canDelete = false;
	}
	
	public Module(String moduleCode, String userID, Boolean canView, Boolean canAdd, Boolean canEdit, Boolean canDelete) {
		this.moduleCode = moduleCode;
		this.userID = userID;
		this.canView = canView;
		this.canEdit = canEdit;
		this.canAdd = canAdd;
		this.canDelete = canDelete;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public Boolean getCanView() {
		return canView;
	}

	public void setCanView(Boolean canView) {
		this.canView = canView;
	}

	public Boolean getCanEdit() {
		return canEdit;
	}

	public void setCanEdit(Boolean canEdit) {
		this.canEdit = canEdit;
	}

	public Boolean getCanAdd() {
		return canAdd;
	}

	public void setCanAdd(Boolean canAdd) {
		this.canAdd = canAdd;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
}
