
public class Human_Habitat {

	private String Name;
	private String Status;
	private int Power_Level_Requirement;
	private Human_Boss Human_Boss;

	public Human_Habitat(String Name, String Status, int Power_Level_Requirement, Human_Boss Human_Boss) {
		this.Name = Name;
		this.Status = Status;
		this.Power_Level_Requirement = Power_Level_Requirement;
		this.Human_Boss = Human_Boss;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public int getPower_Level_Requirement() {
		return Power_Level_Requirement;
	}

	public void setPower_Level_Requirement(int power_Level_Requirement) {
		Power_Level_Requirement = power_Level_Requirement;
	}

	public Human_Boss getHuman_Boss() {
		return Human_Boss;
	}

	public void setHuman_Boss(Human_Boss human_Boss) {
		Human_Boss = human_Boss;
	}

	public String toString() {
		return "Human habitat: " + this.Name + "\nStatus: " + this.getStatus() + "\nPower level required to invade: "
				+ this.Power_Level_Requirement + "\nBoss of habitat: \n" + this.Human_Boss;
	}
}
