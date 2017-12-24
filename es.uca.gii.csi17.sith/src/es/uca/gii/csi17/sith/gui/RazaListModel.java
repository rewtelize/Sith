package es.uca.gii.csi17.sith.gui;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import es.uca.gii.csi17.sith.data.*;

public class RazaListModel  extends AbstractListModel<Raza>  implements ComboBoxModel<Raza> 
{

	private static final long serialVersionUID = 1L;
	private List<Raza> _aData;
	private Object _selection = null;
	
	public RazaListModel(List<Raza> aData)
	{
		_aData = aData;
	}
	
	public Raza getElementAt(int iIndex)
	{
		return _aData.get(iIndex);
	}
	
	public int getSize()
	{
		return _aData.size();
	}
	
	public Object getSelectedItem() {return _selection;}

	@Override
	public void setSelectedItem(Object o) {
		_selection = o;
	}
	
	
}
