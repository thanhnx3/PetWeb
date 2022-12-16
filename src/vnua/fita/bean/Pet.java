package vnua.fita.bean;

public class Pet {
	private Integer id;
	private String name;
	private String preview;
	private String description;
	private Integer price;
	private String type;
	private String male;
	private String color;
	private String size;

	public Pet() {
		
	}

	public Pet(Integer id, String name, String preview, String description, Integer price, String type, String male,
			String color, String size) {
		this.id = id;
		this.name = name;
		this.preview = preview;
		this.description = description;
		this.price = price;
		this.type = type;
		this.male = male;
		this.color = color;
		this.size = size;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMale() {
		return male;
	}

	public void setMale(String male) {
		this.male = male;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
