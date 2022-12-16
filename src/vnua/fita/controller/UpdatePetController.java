package vnua.fita.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class UpdatePetController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	@Wire
	Window modalDialog;
	@Wire
	Label msg;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		Integer petId = (Integer) Executions.getCurrent().getArg().get("petId");

		msg.setValue(petId.toString());

	}

	@Listen("onClick = #updatePetBtn")
	public void updateCar() {

		Messagebox.show("Update successfull!");
		modalDialog.detach();
		Executions.sendRedirect("searchMvc.zul");
	}
}
