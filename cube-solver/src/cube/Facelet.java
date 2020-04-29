package cube;

public class Facelet {

	CubeColor color;
	String location;
	boolean displayLocation;
	
	public Facelet(CubeColor color, String location, boolean displayLocation) {
		this.color = color;
		this.location = location;
		this.displayLocation = displayLocation;
	}
	
	public Facelet(Facelet facelet) {
		this.color = facelet.color;
		this.location = facelet.location;
		this.displayLocation = facelet.displayLocation;
	}
	
	public CubeColor getColor() {
		return color;
	}

	public void setColor(CubeColor color) {
		this.color = color;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setDisplayLocation(boolean displayLocation) {
		this.displayLocation = displayLocation;
	}
	
	public boolean isDisplayLocation() {
		return this.displayLocation;
	}
	
	public String toString() {
		if(this.displayLocation){
			return this.location;
		}
		return this.color.toString();
	} 

}
