import java.util.ArrayList;

public class Skill {

	private String Name;
	private String Type;
	private String Desc;
	private ArrayList<Skill_Level> Effects;
	private int Manacost;
	private int Cooldown;
	private String Skill_From;
	private int Power_Gain;
	private int CD;

	public Skill(String Name, String Type, String Desc, ArrayList<Skill_Level> Effects, int Manacost, int Cooldown,
			String Skill_From, int Power_Gain) {

		this.Name = Name;
		this.Type = Type;
		this.Desc = Desc;
		this.Effects = Effects;
		this.Manacost = Manacost;
		this.Cooldown = Cooldown;
		this.Skill_From = Skill_From;
		this.Power_Gain = Power_Gain;
	}

	// Getters and Setters
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	public ArrayList<Skill_Level> getEffects() {
		return Effects;
	}

	public void setEffects(ArrayList<Skill_Level> effects) {
		Effects = effects;
	}

	public int getManacost() {
		return Manacost;
	}

	public void setManacost(int manacost) {
		Manacost = manacost;
	}

	public int getCooldown() {
		return Cooldown;
	}

	public void setCooldown(int cooldown) {
		Cooldown = cooldown;
	}

	public String getSkill_From() {
		return Skill_From;
	}

	public void setSkill_From(String skill_From) {
		Skill_From = skill_From;
	}

	public int getPower_Gain() {
		return Power_Gain;
	}

	public void setPower_Gain(int power_Gain) {
		Power_Gain = power_Gain;
	}

	public int getCD() {
		return CD;
	}

	public void setCD(int cD) {
		CD = cD;
	}

	// Methods

	public void Effect_Upgrade(int Skill_Level) {
	}

	public void Power_Gain_Upgrade() {

	}

	public String toString() {
		return "Skill name: " + this.Name + "\nSkill type: " + this.Type + "\nSkill description: " + this.Desc
				+ "\nManacost: " + this.Manacost + "\nSkill cooldown: " + this.Cooldown;
	}

}
