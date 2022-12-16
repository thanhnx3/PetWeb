package vnua.fita.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zul.*;
import org.zkoss.zul.ext.Selectable;
import vnua.fita.bean.Pet;
import vnua.fita.model.PetServiceImpl;
import vnua.fita.service.PetService;

public class SearchController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	@Wire
	private Textbox keywordBox;
	@Wire
	private Listbox petListbox;
	@Wire
	private Label nameLabel;
	@Wire
	private Label typeLabel;
	@Wire
	private Label priceLabel;
	@Wire
	private Label descriptionLabel;
	@Wire
	private Label maleLabel;
	@Wire
	private Label colorLabel;
	@Wire
	private Label sizeLabel;
	@Wire
	private Image previewImage;
	private PetService petService = new PetServiceImpl("jdbc:mysql://localhost:3306/ngoxuanthanh_642925", "root", "xuanthanh");

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		List<Pet> result = petService.search(null);
		petListbox.setModel(new ListModelList<Pet>(result));
	}

	@Listen("onClick = #addButton")
	public void showAddModal() {
		Window window = (Window) Executions.createComponents("addPet.zul", null, null);
		window.doModal();
	}

	@Listen("onEdit=#petListbox")
	public void showUpdateModalDialog(ForwardEvent evt) {
		Button editBtn = (Button) evt.getOrigin().getTarget();
		String editBtnId = editBtn.getId();
		Integer petId = Integer.valueOf(editBtnId.substring(4));
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("petId", petId);
		Window window = (Window) Executions.createComponents("editPet.zul", null, map);
		window.doModal();
	}

	@Listen("onDelete=#petListbox")
	public void doDelete(ForwardEvent evt) {
		Messagebox.show("Are you sure to delete?", "Delete?", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
				new EventListener<Event>() {
					@Override
					public void onEvent(final Event confirmEvt) throws InterruptedException {
						if (Messagebox.ON_YES.equals(confirmEvt.getName())) {
							Button deleteBtn = (Button) evt.getOrigin().getTarget();
							String deleteBtnId = deleteBtn.getId();
							Integer petId = Integer.valueOf(deleteBtnId.substring(6));

							Messagebox.show("Pet Id: " + petId);

						} else {
							return;
						}
					}
				});
	}

	@Listen("onClick = #searchButton")
	public void search() {
		String keyword = keywordBox.getValue();
		List<Pet> result = petService.search(keyword);
		petListbox.setModel(new ListModelList<Pet>(result));
	}

	@Listen("onSelect = #petListbox")
	public void showDetail() {

		Set<Pet> selection = ((Selectable<Pet>) petListbox.getModel()).getSelection();
		if (selection != null && !selection.isEmpty()) {

			Pet selected = selection.iterator().next();

			previewImage.setSrc(selected.getPreview());
			nameLabel.setValue(selected.getName());
			descriptionLabel.setValue(selected.getDescription());
			priceLabel.setValue(selected.getPrice().toString());
			typeLabel.setValue(selected.getType());
			maleLabel.setValue(selected.getMale());
			colorLabel.setValue(selected.getColor());
			sizeLabel.setValue(selected.getSize());
		}
	}
}