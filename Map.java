
public class Map {

	private String Name;
	private String Desc;
	private Area[] Area;

	public Map(String Name, String Desc, Area[] Area) {
		this.Name = Name;
		this.Desc = Desc;
		this.Area = Area;

	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	public Area[] getArea() {
		return Area;
	}

	public void setArea(Area[] area) {
		Area = area;
	}

	public String toString() {
		String toreturn = "";
		for (int i = 0; i < Area.length; i++) {
			toreturn += Area[i].getArea_Name() + ",";
		}
		return "Map name: " + this.Name + "\nDescription: " + this.Desc + "\nArea: " + toreturn;

	}
}
