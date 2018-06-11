package com.demo.infohub.webapp.selectablemodels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.demo.infohub.serviceapi.dto.EmployeeDTO;

public class EmployeeDTODataModel extends ListDataModel<EmployeeDTO>
		implements SelectableDataModel<EmployeeDTO>, Serializable {

	private static final long serialVersionUID = -1519555363649558350L;

	public EmployeeDTODataModel() {
		setWrappedData(new ArrayList<>());
	}

	public EmployeeDTODataModel(List<EmployeeDTO> data) {
		super(data);
	}

	@Override
	public EmployeeDTO getRowData(String rowKey) {

		List<EmployeeDTO> lItems = (List<EmployeeDTO>) getWrappedData();

		for (EmployeeDTO lItem : lItems) {
			if (lItem.getId().equals(rowKey))
				return lItem;
		}

		return null;
	}

	@Override
	public Object getRowKey(EmployeeDTO pModel) {
		return pModel.getId();
	}

	@Override
	public List<EmployeeDTO> getWrappedData() {
		Object lWrappedData = super.getWrappedData();
		if (lWrappedData == null) {
			setWrappedData(new ArrayList<>());
		}
		return (List<EmployeeDTO>) super.getWrappedData();
	}
}