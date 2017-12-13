package es.uca.gii.csi17.sith.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import es.uca.gii.csi17.sith.data.Cliente;

public class ClientesTableModel extends AbstractTableModel {
	List<Cliente> _aData;
	
	ClientesTableModel(List<Cliente> aData){
		_aData = aData;
	}
	
	public int getColumnCount() {
		return 2;
	}
	
	public int getRowCount() {
		return _aData.size();
	}
	
	public Object getValueAt(int iRow, int iCol) {
		switch(iCol) {
			case 0: return _aData.get(iRow).getId();
			case 1: return _aData.get(iRow).getNombre(); 
			default: return "Error";
		}				
	}
	
	public Cliente getData(int iRow) {
		return _aData.get(iRow);
	}
}
