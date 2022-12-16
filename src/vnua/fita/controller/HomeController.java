package vnua.fita.controller;

import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.ext.Selectable;

import vnua.fita.bean.Pet;
import vnua.fita.model.PetServiceImpl;
import vnua.fita.service.PetService;

public class HomeController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	@Wire
	private Label titleLabel;
	@Wire
	private Listbox petListbox;
	@Wire
	private Hlayout petDetail;
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
	@Wire
	private A back;

	private PetService petService = new PetServiceImpl("jdbc:mysql://localhost:3306/ngoxuanthanh_642925", "root",
			"xuanthanh");

	@Override
	public void doAfterCompose(Component comp) throws Exception {

		super.doAfterCompose(comp);

		String keyword = (String) Executions.getCurrent().getParameter("type");
		if (keyword != null) {
			titleLabel.setValue(keyword.toUpperCase() + " PET LIST:");
		} else {
			titleLabel.setValue("PET LIST:");
		}

		List<Pet> result = petService.search(keyword);

		petListbox.setModel(new ListModelList<Pet>(result));
	}

	@Listen("onSelect = #petListbox")
	public void showDetail() {

		Selectable<Pet> selectable = (Selectable<Pet>) petListbox.getModel();
		Set<Pet> selection = selectable.getSelection();
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
		titleLabel.setValue("PET DETAIL:");
		
		petListbox.setVisible(false);
		
		petDetail.setVisible(true);
	}

	@Listen("onClick = #back")
	public void backPetList() {
		petListbox.setVisible(true);
		
		petDetail.setVisible(false);
	}
}