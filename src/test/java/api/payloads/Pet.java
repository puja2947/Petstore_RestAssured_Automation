package api.payloads;

public class Pet {
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category_Pet getCategory() {
		return category;
	}
	public void setCategory(Category_Pet category) {
		this.category = category;
	}
	public String[] getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(String[] photoUrls) {
		this.photoUrls = photoUrls;
	}
	public Tags_Pet[] getTags() {
		return tags;
	}
	public void setTags(Tags_Pet[] tags) {
		this.tags = tags;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	int id;
	String name;
	Category_Pet category;
	String[] photoUrls;
	Tags_Pet[] tags;
	String status;
	
	

}
