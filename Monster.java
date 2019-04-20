import java.util.Random;

public class Monster {

	private String Name;
	private int Power_Level;
	private String Status;
	private int HP;
	private int MP;
	private int Damage;
	private int Power_Damage;
	private int Power_Damage_Cost;
	private Skill Skill;
	private int Current_Skill_Level;

	public Monster(String Name, int Power_Level, String Status, int HP, int MP, int Damage, int Power_Damage,
			int Power_Damage_Cost, Skill Skill, int Current_Skill_Level) {

		this.Name = Name;
		this.Power_Level = Power_Level;
		this.Status = Status;
		this.HP = HP;
		this.MP = MP;
		this.Damage = Damage;
		this.Power_Damage = Power_Damage;
		this.Power_Damage_Cost = Power_Damage_Cost;
		this.Skill = Skill;
		this.Current_Skill_Level = Current_Skill_Level;
	}

	public Monster(String Status, int HP, int MP) {
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

	public int getPower_level() {
		return Power_Level;
	}

	public void setPower_level(int power_Level) {
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

	public int getPower_Damage_Cost() {
		return Power_Damage_Cost;
	}

	public void setPower_Damage_Cost(int power_Damage_Cost) {
		Power_Damage_Cost = power_Damage_Cost;
	}

	public Skill getSkill() {
		return Skill;
	}

	public void setSkill(Skill skill) {
		Skill = skill;
	}

	public int getPower_Level() {
		return Power_Level;
	}

	public void setPower_Level(int power_Level) {
		Power_Level = power_Level;
	}

	public int getCurrent_Skill_Level() {
		return Current_Skill_Level;
	}

	public void setCurrent_Skill_Level(int current_Skill_Level) {
		Current_Skill_Level = current_Skill_Level;
	}

	// Methods
	public void Attack(Player Player) {
		if (this.getName().equalsIgnoreCase("Black Panther")) {
			if (this.Skill.getCD() == 0) {
				this.UseSkill(Player, this.Skill);
			} else {
				System.out.println(this.Name + " used normal attack and dealt " + this.Damage + " damage!! to you");
				Player.De_HP(this.Damage);
			}
		} else if (this.getName().equalsIgnoreCase("Green Cobra")) {
			System.out.println(this.Name + " used normal attack and dealt " + this.Damage + " damage!! to you");
			Player.De_HP(this.Damage);
			this.UseSkill(Player, this.Skill);
		} else {
			System.out.println(this.Name + " used normal attack and dealt " + this.Damage + " damage!! to you");
			Player.De_HP(this.Damage);
		}
	}

	public void Power_Attack(Player Player) {
		if (this.MP >= this.getPower_Damage_Cost()) {
			if (this.getName().equalsIgnoreCase("Black Panther")) {
				if (this.Skill.getCD() == 0) {
					this.UseSkill(Player, this.Skill);
				} else {
					System.out.println(
							this.Name + " used normal attack and dealt " + this.Power_Damage + " damage!! to you");
					Player.De_HP(this.Power_Damage);
					De_MP(this.Power_Damage_Cost);
				}
			} else if (this.getName().equalsIgnoreCase("Green Cobra")) {
				System.out
						.println(this.Name + " used normal attack and dealt " + this.Power_Damage + " damage!! to you");
				Player.De_HP(this.Power_Damage);
				De_MP(this.Power_Damage_Cost);
				this.UseSkill(Player, this.Skill);
			} else {
				System.out
						.println(this.Name + " used normal attack and dealt " + this.Power_Damage + " damage!! to you");
				Player.De_HP(this.Power_Damage);
				De_MP(this.Power_Damage_Cost);
			}
		} else {
			System.out.println(this.Name + " ran out of mana!!!");
		}
	}

	public void UseSkill(Player Player, Skill skill) {

		try {
			switch (skill.getName()) {
			case "Silver Bite": {
				if (this.MP >= skill.getManacost() && skill.getCD() <= 0) {
					int damage = skill.getEffects().get(this.getCurrent_Skill_Level() - 1).getEffect();
					int heal = (Player.getHP() * skill.getEffects().get(this.getCurrent_Skill_Level() - 1).getEffect2())
							/ 100;
					System.out.println(
							this.Name + " used skill 'Silver Bite' dealing " + damage + " damage and heal for " + heal);
					Player.De_HP(damage);
					this.setHP(this.getHP() + (heal));
					skill.setCD(skill.getCooldown());
					De_MP(skill.getManacost());
				} else {
					System.out.println(this.Name + " tried to use " + this.Skill.getName()
							+ " ran out of mana or skill on cooldown");
				}
				break;
			}
			case "Sneak Attack": {
				int damage = this.Damage
						* ((skill.getEffects().get(this.getCurrent_Skill_Level() - 1).getEffect() / 100));
				System.out.println("Skill 'Sneak Attack' triggered in " + this.Name + " first attack!!! dealing "
						+ damage + " damage");
				Player.De_HP(damage);
				skill.setCD(skill.getCooldown());
				break;
			}
			case "Venom Strike": {

				Player.setStatus("Poisoned");
				int damage = skill.getEffects().get(this.getCurrent_Skill_Level() - 1).getEffect();
				System.out.println(this.Name + " attack's trigged 'Venom strike' you will take extra " + damage
						+ " damage every turn");
				if (Player.getStatus().equalsIgnoreCase("Poisoned")) {
					System.out.println("You got damaged by " + damage + " from poison");
					Player.De_HP(damage);
				}
				break;
			}
			case "Troll Regen": {
				int heal = skill.getEffects().get(this.getCurrent_Skill_Level() - 1).getEffect();
				System.out.println(this.Name + " used 'Troll regen' and regenerated " + heal + " hp");
				this.setHP(this.getHP() + (heal));
				skill.setCD(skill.getCooldown());
				System.out.println("Skill " + skill.getName() + " placed on cooldown: " + skill.getCD() + " turn");
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
			setHP(getHP() - MinusHP);
			if (this.HP <= 0) {
				this.setStatus("Dead");
				System.out.println("\n" + this.getName() + " is dead");
			}
		} else {
			System.out.println("\nThe monster is dead");
		}
	}

	public void De_MP(int Mana_Cost) {

		this.setMP(this.getMP() - Mana_Cost);
	}

	public void AI(Monster Monster, Player Player) {
		int HP75per = ((Monster.getHP() * 75) / 100);
		int HP50per = ((Monster.getHP() * 50) / 100);
		int MP75per = ((Monster.getMP() * 75) / 100);
		Random rand = new Random();
		int n = rand.nextInt(2) + 1;

		if (Monster.getHP() <= HP50per) {
			if (Monster.getSkill().getCD() >= 0) {
				Monster.UseSkill(Player, Monster.getSkill());
			} else {
				if (n == 1) {
					Monster.Attack(Player);
				} else {
					if (Monster.getMP() >= MP75per) {
						Monster.Power_Attack(Player);
					} else {
						Monster.Attack(Player);
					}
				}
			}

		} else if (Monster.getHP() >= HP75per) {
			if (n == 1) {
				Monster.Attack(Player);
			} else {
				if (Monster.getMP() >= MP75per) {
					Monster.Power_Attack(Player);
				} else {
					Monster.Attack(Player);
				}
			}
		}
	}

	public Monster[] Backup(Monster[] Monster) {
		Monster[] Backup = new Monster[9];
		for (int i = 0; i < Monster.length; i++) {
			Backup[i] = new Monster(Monster[i].getStatus(), Monster[i].getHP(), Monster[i].getMP());
		}
		return Backup;
	}

	public void Reset(Monster[] Monster, Monster[] Backup) {
		for (int i = 0; i < Monster.length; i++) {
			Monster[i].setStatus(Backup[i].getStatus());
			Monster[i].setHP(Backup[i].getHP());
			Monster[i].setMP(Backup[i].getMP());
		}
	}

	public String toString() {
		try {
			return "Name: " + this.Name + "\nPower level: " + this.Power_Level + "\nHP: " + this.HP + "\nMP: " + this.MP
					+ "\nAttack damage: " + this.Damage + "\nPower attack damage: " + this.Power_Damage
					+ "\nPower attack cost: " + this.Power_Damage_Cost + "\nHas the ability called: "
					+ this.Skill.getName();
		} catch (NullPointerException e) {
			return "Name: " + this.Name + "\nPower level: " + this.Power_Level + "\nHP: " + this.HP + "\nMP: " + this.MP
					+ "\nAttack damage: " + this.Damage + "\nPower attack damage: " + this.Power_Damage
					+ "\nPower attack cost: " + this.Power_Damage_Cost + "\n" + this.Name
					+ " does not posses any skill";
		}
	}

}
