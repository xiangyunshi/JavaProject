
public class Player {

	private String Name;
	private int Power_Level;
	private String Status;
	private int HP;
	private int MP;
	private int Damage;
	private int Power_Damage;
	private int Power_Mana_Cost;
	private String Current_Location;
	private Skill[] Skills;
	private int[] Skill_Levels;

	public Player(String Name, int Power_Level, String Status, int HP, int MP, int Damage, int Power_Damage,
			int Power_Mana_Cost, String Current_Location, Skill[] Skills, int[] Skill_Levels) {

		this.Name = Name;
		this.Power_Level = Power_Level;
		this.Status = Status;
		this.HP = HP;
		this.MP = MP;
		this.Damage = Damage;
		this.Power_Damage = Power_Damage;
		this.Power_Mana_Cost = Power_Mana_Cost;
		this.Current_Location = Current_Location;
		this.Skills = Skills;
		this.Skill_Levels = Skill_Levels;

	}

	public Player(String Status, int HP, int MP) {
		this.Status = Status;
		this.HP = HP;
		this.MP = MP;
	}

	// Getters and Setters
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getPower_Level() {
		return Power_Level;
	}

	public void setPower_Level(int power_Level) {
		Power_Level = power_Level;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getMP() {
		return MP;
	}

	public void setMP(int mP) {
		MP = mP;
	}

	public int getDamage() {
		return Damage;
	}

	public void setDamage(int damage) {
		Damage = damage;
	}

	public int getPower_Damage() {
		return Power_Damage;
	}

	public void setPower_Damage(int power_Damage) {
		Power_Damage = power_Damage;
	}

	public int getPower_Mana_Cost() {
		return Power_Mana_Cost;
	}

	public void setPower_Mana_Cost(int power_Mana_Cost) {
		Power_Mana_Cost = power_Mana_Cost;
	}

	public String getCurrent_Location() {
		return Current_Location;
	}

	public void setCurrent_Location(String current_Location) {
		Current_Location = current_Location;
	}

	public Skill[] getSkills() {
		return Skills;
	}

	public void setSkills(Skill[] skills) {
		Skills = skills;
	}

	public Skill getSkill(int a) {
		return Skills[a];
	}

	public void setSkill(Skill Skill, int a) {
		Skills[a] = Skill;
	}

	public int[] getSkill_Levels() {
		return Skill_Levels;
	}

	public void setSkill_Levels(int[] skill_Levels) {
		Skill_Levels = skill_Levels;
	}

	public int getSkill_Level(int a) {
		return Skill_Levels[a];
	}

	public void setSkill_Level(int Skill_Level, int a) {
		Skill_Levels[a] = Skill_Level;
	}

	// Methods
	public void Attack(Monster Monster) {
		try {
			if (this.getSkill(1).getName().equalsIgnoreCase("Sneak Attack")) {
				if (this.getSkill(1).getCD() == 0) {
					this.UseSkill(Monster, this.getSkill(1));
				} else {
					System.out.println(
							"You used normal attack and dealt " + this.Damage + " damage!! to " + Monster.getName());
					Monster.De_HP(this.Damage);
				}
			}
		} catch (NullPointerException e) {
			System.out.println("You used normal attack and dealt " + this.Damage + " damage!! to " + Monster.getName());
			Monster.De_HP(this.Damage);

		}
		try {
			if (this.getSkill(2).getName().equalsIgnoreCase("Venom Strike")) {
				System.out.println(
						"You used normal attack and dealt " + this.Damage + " damage!! to " + Monster.getName());
				this.UseSkill(Monster, this.getSkill(2));
				Monster.De_HP(this.Damage);
			}
		} catch (NullPointerException e) {
			System.out.println("You used normal attack and dealt " + this.Damage + " damage!! to " + Monster.getName());
			Monster.De_HP(this.Damage);

		}
	}

	public void Power_Attack(Monster Monster) {
		if (this.MP >= this.getPower_Mana_Cost()) {
			try {
				if (this.getSkill(2).getName().equalsIgnoreCase("Venom Strike")) {
					System.out.println("You used normal attack and dealt " + this.Power_Damage + " damage!! to "
							+ Monster.getName());
					this.UseSkill(Monster, this.getSkill(2));
					Monster.De_HP(this.Power_Damage);
					De_MP(this.Power_Mana_Cost);
				}
			} catch (NullPointerException e) {
				System.out.println("You attacked with power attack and dealt " + this.Power_Damage + " damage!!");
				System.out.println("You used " + Power_Mana_Cost + " MP");
				Monster.De_HP(this.Power_Damage);
				De_MP(this.Power_Mana_Cost);
			}
		} else {
			System.out.println("You ran out of mana!!!");
		}
	}

	public void UseSkill(Monster Monster, Skill skill) {
		try {
			switch (skill.getName()) {
			case "Silver Bite": {
				if (this.MP >= skill.getManacost()) {
					if (skill.getCD() == 0) {
						int damage = skill.getEffects().get(this.getSkill_Level(0) - 1).getEffect();
						int heal = (Monster.getHP() * skill.getEffects().get(this.getSkill_Level(0) - 1).getEffect2())
								/ 100;
						System.out.println(
								"You used skill 'Silver Bite' dealing " + damage + " damage and heal for " + heal);
						Monster.De_HP(damage);
						this.setHP(this.getHP() + (heal));
						skill.setCD(skill.getCooldown());
						System.out.println(
								"Skill " + skill.getName() + " placed on cooldown: " + skill.getCD() + " turn");
						De_MP(skill.getManacost());
					} else {
						System.out.println(skill.getName() + " is still on cooldown");
					}
				} else {
					System.out.println(this.Name + " ran out of mana");
				}
				break;
			}
			case "Sneak Attack": {
				int damage = this.Damage * ((skill.getEffects().get(this.getSkill_Level(1) - 1).getEffect() / 100));
				System.out.println(
						"Skill 'Sneak Attack' triggered in your first attack!!! dealing " + damage + " damage");
				Monster.De_HP(damage);
				skill.setCD(skill.getCooldown());
				break;
			}
			case "Venom Strike": {
				Monster.setStatus("Poisoned");
				int damage = skill.getEffects().get(this.getSkill_Level(2) - 1).getEffect();
				System.out.println(
						"Your attack trigged 'Venom strike' enemy will take extra " + damage + " damage every turn");
				if (Monster.getStatus().equalsIgnoreCase("Poisoned")) {
					System.out.println("Enemy got damaged by " + damage + " from poison");
					Monster.De_HP(damage);
				}
				break;
			}
			case "Troll Regen": {
				int heal = skill.getEffects().get(this.getSkill_Level(3) - 1).getEffect();
				System.out.println("You used 'Troll regen' and regenerated " + heal + " hp");
				this.setHP(this.getHP() + (heal));
				skill.setCD(skill.getCooldown());
				System.out.println("Skill " + skill.getName() + " placed on cooldown: " + skill.getCD() + " turn");
				break;
			}
			case "Thick Hide": {
				this.setStatus(this.getStatus() + " | Thick Hide Protection");
				System.out.println("Skill 'Thick Hide' provides you with extra "
						+ skill.getEffects().get(this.getSkill_Level(4) - 1).getEffect()
						+ " percent agints incoming damage");
				break;
			}

			default: {
				System.out.println("Wrong skill input!!! you lost your chance to attack");
			}
			}

		} catch (java.lang.NullPointerException e) {
			System.out.println("You do not have any skills");
		}
	}

	public void De_HP(int MinusHP) {

		if (this.HP > 0) {
			if (this.getStatus().contains(" | Thick Hide Protection")) {
				setHP(getHP() - MinusHP);
			} else {
				setHP(getHP() - MinusHP);
			}
			if (this.HP <= 0) {
				this.setStatus("Dead");
				System.out.println("\nPlayer is dead, Game Over !!!");
			}
		} else {
			System.out.println("\nPlayer is Dead");
		}
	}

	public void De_MP(int Mana_Cost) {

		this.setMP(this.getMP() - Mana_Cost);
	}

	public void Travel(String Destination, String Command) {
		System.out.println("You travelled to: " + Destination);
		if (Command.contains(" map ")) {
			this.setCurrent_Location(Destination);
		} else if (Command.contains(" area ")) {
			this.setCurrent_Location(this.Current_Location + "@" + Destination);
		}
		System.out.println("---------------------------------------");
	}

	public Player Backup() {
		Player Backup = new Player(this.getStatus(), this.getHP(), this.getMP());
		return Backup;
	}

	public void Reset(Player Player, Player Backup) {
		Player.setStatus(Backup.getStatus());
		Player.setHP(Backup.getHP());
		Player.setMP(Backup.getMP());
	}

	public void SkillReset() {
		try {
			for (int i = 0; i < this.getSkills().length; i++) {
				this.getSkill(i).setCD(0);
			}
		} catch (NullPointerException e) {

		}
	}

	public String toString() {
		String toreturn = "";
		for (int i = 0; i < Skills.length; i++) {

			try {
				toreturn += "\n" + Skills[i].getName() + " level " + this.Skill_Levels[i];
			} catch (NullPointerException e) {
			}
		}

		return "Character name: " + this.Name + "\nCurrent power level: " + this.Power_Level + "\nStatus: "
				+ this.Status + "\nHP: " + this.HP + "\nMP: " + this.MP + "\nAttack damage: " + this.Damage
				+ "\nPower damage manacost: " + this.Power_Mana_Cost + "\nPower attack damage: " + this.Power_Damage
				+ "\nCurrent location: " + this.Current_Location + "\nYour acquired skills: " + toreturn;

	}

}
