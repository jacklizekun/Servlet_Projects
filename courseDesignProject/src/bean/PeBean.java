package bean;

public class PeBean {
	public PeBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PeBean(String name) {
		super();

		Name = name;
		}
	

	@Override
	public String toString() {
		return "PeBean [ Name=" + Name + "]";
	}


	String Name;


}