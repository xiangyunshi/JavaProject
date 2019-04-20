
public class Human_Boss extends Monster {
	private String Boss_Rank;

	public Human_Boss(String Name, String Boss_Rank, int Power_Level, String Status, int HP, int MP, int Damage,
			int Power_Damage, int Power_mana_Cost, Skill Skill, int Current_Skill_Level) {
		super(Name, Power_Level, Status, HP, MP, Damage, Power_Damage, Power_mana_Cost, Skill, Current_Skill_Level);
		this.Boss_Rank = Boss_Rank;
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "Boss rank: " + Boss_Rank + "\n" + super.toString();
	}

}
