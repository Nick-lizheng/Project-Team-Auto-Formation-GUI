package model;

import java.io.Serializable;

public class Company implements Serializable{
	private static final long serialVersionUID = 5011561343673747532L;
	private String id;
	private String name;
	private String abn;
	private String url;
	private String address;
	
		
	public Company(String id, String name, String abn, String url, String address) {
		super();
		this.id = id;
		this.name = name;
		this.abn = abn;//need to do some control in 11 digit and positive number
		this.url = url;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbn() {
		return abn;
	}
	public void setAbn(String abn) {
		this.abn = abn;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Id=" + id + ", Name=" + name + ", ABN=" + abn + ", Url=" + url + ", Address=" + address;
	}
	
	
	//override hashcode and equals method, because only this way to let the set.contains(object) effective.
	//the hashcode and equals method need to choose the element or object which you like to compare or contains.
	//so that the contians method can be return true or false.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
