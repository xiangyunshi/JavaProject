import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class GamePlay {

	public GamePlay() {

	}

	public static void GamePlayStart() throws FileNotFoundException {
		Create c1 = new Create();
		LinkedHashMap<String, ArrayList<Skill_Level>> Map_Skill_Level = c1.Skill_Level();
		LinkedHashMap<String, Skill> Map_Skill = c1.Skill(Map_Skill_Level);
		ArrayList<Monster> Monster_List = c1.Monster(Map_Skill);
		ArrayList<Monster> Human_Boss_List = c1.Human_Boss(Map_Skill);
		ArrayList<Human_Habitat> Human_Habitat_List = c1.Human_Habitat(Human_Boss_List);
		ArrayList<Area> Map_Area_List = c1.Area(Monster_List, Human_Habitat_List);
		Map[] Map_List = c1.Map(Map_Area_List);
		Player Player = c1.Player();

		Scanner console = new Scanner(System.in);
		String Startgame_yn = "";
		while (!Startgame_yn.equalsIgnoreCase("y")) {
			System.out.println("Start the game? (y/n)");
			Startgame_yn = console.nextLine();
			if (Startgame_yn.equalsIgnoreCase("y")) {
				NeworLoad(Player, Map_Skill);
				String Tutorial_yn = "";
				while (!Tutorial_yn.equalsIgnoreCase("y")) {
					System.out.println("Do you want to skip tutorials? (y/n)");
					Tutorial_yn = console.nextLine();
					if (Tutorial_yn.equalsIgnoreCase("y")) {
						System.out.println("You have chosen to skip the tutorials");
						System.out.println("---------------------------------------");
						String Story_yn = "";
						while (!Story_yn.equalsIgnoreCase("y")) {
							System.out.println("Do you want to skip the background story? (y/n)");
							Story_yn = console.nextLine();
							if (Story_yn.equalsIgnoreCase("y")) {
								// Gameplay
								System.out.println("You have chosen to skip background story");
								System.out.println("---------------------------------------");
								System.out.println("Game starting....\n");
								ChooseMap(Player, Map_List, Map_Skill);
							} else if (Story_yn.equalsIgnoreCase("n")) {
								B_Story(Player);
								System.out.println("Game starting....\n");
								ChooseMap(Player, Map_List, Map_Skill);
								Story_yn = "y";
								// Gameplay
							} else {
								System.out.println("You can only select (y/n)");
							}
						}
					} else if (Tutorial_yn.equalsIgnoreCase("n")) {
						Tutorial(Player, Map_Skill, Monster_List.get(0), Map_List);
						B_Story(Player);
						System.out.println("Game starting....\n");
						ChooseMap(Player, Map_List, Map_Skill);
						Tutorial_yn = "y";
					} else {
						System.out.println("You can only select (y/n)");
					}
				}
			} else if (Startgame_yn.equalsIgnoreCase("n")) {
				System.out.println("You have chosen to not start the game, game ended");
				Startgame_yn = "y";
			} else {
				System.out.println("You can only select (y/n)");
			}
		}

	}

	public static void NeworLoad(Player Player, LinkedHashMap Map_Skill) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		System.out.println("Game starting....\n");
		String NeworLoad = "";
		while (!NeworLoad.equalsIgnoreCase("new")) {
			System.out.println("Create new game or load game? (new/load)");
			NeworLoad = console.nextLine();
			if (NeworLoad.equalsIgnoreCase("new")) {
				CreateCharacter(Player);
				NeworLoad = "new";
			} else if (NeworLoad.equalsIgnoreCase("load")) {
				LoadCharacter(Player, Map_Skill);
				NeworLoad = "new";
			} else {
				System.out.println("Wrong input please try again");
			}
		}
	}

	public static void CreateCharacter(Player Player) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		int Stats = 20;
		System.out.println("You have chosen to create new game, please create your character\n");
		System.out.print("Character name: ");
		String Player_Name = console.nextLine();
		String Customize_yn = "";
		while (!Customize_yn.equalsIgnoreCase("C")) {
			System.out.println("Would you like to go with defualt or customize your own character?\n(D/C)");
			Customize_yn = console.nextLine();
			if (Customize_yn.equalsIgnoreCase("C")) {
				System.out.println("\nYou have 20 available stats point which you can allocate\n"
						+ "Strength=> increases Maximum HP\n"
						+ "Agility=> increases attack damage and power damage\n"
						+ "Intelligence=> increases maximum MP (affects power attack damage)");
				System.out.println("---------------------------------------");
				System.out.print("Strength: ");
				int Strength = console.nextInt();
				console.nextLine();
				Stats -= Strength;
				System.out.print("Agility: ");
				int Agility = console.nextInt();
				console.nextLine();
				Stats -= Agility;
				System.out.print("Intelligence: ");
				int Intelligence = console.nextInt();
				console.nextLine();
				Stats -= Intelligence;
				if (Stats < 0) {
					System.out.println(
							"You can allocate only 20 points, please try again\n" + "Going back to character cration");
					CreateCharacter(Player);
				}
				int HP = Strength * 50;
				int MP = Intelligence * 50;
				int Damage = Agility * 10;
				int Power_Damage = (Agility * 10) + (MP / 10);

				Player.setName(Player_Name);
				Player.setHP(HP);
				Player.setMP(MP);
				Player.setDamage(Damage);
				Player.setPower_Damage(Power_Damage);
			} else if (Customize_yn.equalsIgnoreCase("D")) {
				Player.setName(Player_Name);
				Player.setHP(500);
				Player.setMP(250);
				Player.setDamage(50);
				Player.setPower_Damage(75);
				Customize_yn = "C";
			} else {
				System.out.println("You can only select 'c' (customize) or 'd' (default)");
			}
		}

		System.out.println("---------------------------------------");
		System.out.println("Your current statistic is as follow: ");
		System.out.println(Player);
		System.out.println("---------------------------------------");
		System.out.println("\nDo you want to make any changes to your character? (y/n)");
		String CharacterCreation_yn = console.nextLine();
		if (CharacterCreation_yn.equalsIgnoreCase("y")) {
			CreateCharacter(Player);
		} else {
			PrintStream PlayerFile = new PrintStream(new File(Player.getName() + ".csv"));
			PlayerFile.print(Player.getName() + ",");
			PlayerFile.print(Player.getPower_Level() + ",");
			PlayerFile.print(Player.getStatus() + ",");
			PlayerFile.print(Player.getHP() + ",");
			PlayerFile.print(Player.getMP() + ",");
			PlayerFile.print(Player.getDamage() + ",");
			PlayerFile.print(Player.getPower_Damage() + ",");
			PlayerFile.print(Player.getPower_Mana_Cost() + ",");
			PlayerFile.println(Player.getCurrent_Location() + ", ");
			for (int i = 0; i < Player.getSkills().length; i++) {
				try {
					PlayerFile.print(Player.getSkills()[i].getName() + ",");

					PlayerFile.print("," + Player.getSkill_Levels()[i]);
					if (i < Player.getSkills().length - 1)
						PlayerFile.print("\n");
				} catch (NullPointerException e) {

				}
			}

		}
	}

	public static void LoadCharacter(Player Player, @SuppressWarnings("rawtypes") LinkedHashMap Map_Skill)
			throws FileNotFoundException {
		Scanner console = new Scanner(System.in);

		System.out.print("Input your character name: ");
		String Player_Name = console.nextLine();
		String Player_Name_yn = "";
		while (!Player_Name_yn.equalsIgnoreCase("y")) {
			System.out.println("Your character name is: " + Player_Name + "? (y/n)");
			Player_Name_yn = console.nextLine();
			if (Player_Name_yn.equalsIgnoreCase("y")) {
				String[] PlayerInfo;
				try {
					Scanner Player_Info = new Scanner(new File(Player_Name + ".csv"));
					PlayerInfo = Player_Info.nextLine().split(",");
					String Name = PlayerInfo[0];
					int Power_Level = Integer.parseInt(PlayerInfo[1]);
					String Status = PlayerInfo[2];
					int HP = Integer.parseInt(PlayerInfo[3]);
					int MP = Integer.parseInt(PlayerInfo[4]);
					int Damage = Integer.parseInt(PlayerInfo[5]);
					int Power_Damage = Integer.parseInt(PlayerInfo[6]);
					int Power_Mana_Cost = Integer.parseInt(PlayerInfo[7]);
					String Current_Location = PlayerInfo[8];
					String[] Skill_Name = new String[9];
					int[] Skill_Level = new int[9];
					String PrintSkill = "";
					int m = 0;
					while (Player_Info.hasNextLine()) {
						String[] newinfo = Player_Info.nextLine().split(",");
						Skill_Name[m] = newinfo[0];
						Skill_Level[m] = Integer.parseInt(newinfo[1]);
						m++;
					}
					for (int h = 0; h < Skill_Name.length; h++) {
						try {
							if (Skill_Name[h].equals(null)) {
								PrintSkill += "\n" + Skill_Name[h] + " level " + Player.getSkill_Levels()[h];
							}
						} catch (NullPointerException e) {
						}
					}
					System.out.println("---------------------------------------");
					System.out.println("Your character current's stats:");
					System.out.println("Hero name: " + Name + "\nCurrent power level: " + Power_Level + "\nStatus: "
							+ Status + "\nHP: " + HP + "\nMP: " + MP + "\nAttack damage: " + Damage
							+ "\nPower damage manacost: " + Power_Mana_Cost + "\nPower attack damage: " + Power_Damage
							+ "\nCurrent location: " + Current_Location + "\nYour acquired skills: " + PrintSkill);
					System.out.println("---------------------------------------");
					Player.setName(Name);
					Player.setPower_Level(Power_Level);
					Player.setStatus(Status);
					Player.setHP(HP);
					Player.setMP(MP);
					Player.setDamage(Damage);
					Player.setPower_Damage(Power_Damage);
					Player.setPower_Mana_Cost(Power_Mana_Cost);
					Player.setCurrent_Location(Current_Location);
					Skill[] Skills = new Skill[9];
					for (int h = 0; h < Skill_Name.length; h++) {
						Skills[h] = (Skill) Map_Skill.get(Skill_Name[0]);
					}
					Player.setSkills(Skills);
					Player.setSkill_Levels(Skill_Level);

				} catch (FileNotFoundException e) {
					System.out.print("There is no character named: " + Player_Name);
					System.out.println("\nProceed to character creation menu? (y/n)");
					String GotoCreate = console.nextLine();
					if (GotoCreate.equalsIgnoreCase("y")) {
						CreateCharacter(Player);
					} else {
						System.out.println("Sorry, you cannot play without a character please create a new one");
						CreateCharacter(Player);
					}
				}
			} else if (Player_Name_yn.equalsIgnoreCase("n")) {
				System.out.println("Going back to character selection...\n");
				LoadCharacter(Player, Map_Skill);
				Player_Name_yn = "y";
			} else {
				System.out.println("Please select only y/n");
			}
		}

	}

	public static String Command_Tutorial(String Command, Player Player, Monster Monster) {
		String OutCommand = "Check while loop";
		switch (Command) {
		case "view p": {
			System.out.println(Player);
			break;
		}
		case "view p.hp": {
			System.out.println(Player.getHP());
			break;
		}
		case "view e": {
			System.out.println(Monster);
			break;
		}
		case "attack": {
			Player.Attack(Monster);
			break;
		}
		case "power attack": {
			Player.Power_Attack(Monster);
			break;
		}
		case "u skill silver-b": {
			Player.UseSkill(Monster, Player.getSkill(0));
			break;
		}
		case "flee": {
			System.out.println("You fled from the monster");
			Player.setStatus("Scared");
			break;
		}
		default: {
			System.out.println("Invalid command, please try again");
			OutCommand = "";
		}
		}
		return OutCommand;
	}

	public static String Command_Combat(String Command, Player Player, Monster Monster) {
		String OutCommand = "check while loop";
		Command = Command.toLowerCase();
		switch (Command) {
		case "view p": {
			System.out.println(Player);
			OutCommand = "";
			break;
		}
		case "view p.name": {
			System.out.println(Player.getName());
			OutCommand = "";
			break;
		}
		case "view p.power-level": {
			System.out.println(Player.getPower_Level());
			OutCommand = "";
			break;
		}
		case "view p.status": {
			System.out.println(Player.getStatus());
			OutCommand = "";
			break;
		}
		case "view p.hp": {
			System.out.println(Player.getHP());
			OutCommand = "";
			break;
		}
		case "view p.mp": {
			System.out.println(Player.getMP());
			OutCommand = "";
			break;
		}
		case "view p.damage": {
			System.out.println(Player.getDamage());
			OutCommand = "";
			break;
		}
		case "view p.power-damage": {
			System.out.println(Player.getPower_Damage());
			OutCommand = "";
			break;
		}
		case "view p.power-manacost": {
			System.out.println(Player.getPower_Mana_Cost());
			OutCommand = "";
			break;
		}
		case "view p.location": {
			System.out.println(Player.getCurrent_Location());
			OutCommand = "";
			break;
		}
		case "view p.skill-all": {
			for (int i = 0; i < Player.getSkills().length; i++) {
				try {
					System.out.println(Player.getSkills()[i].getName() + " level: " + Player.getSkill_Levels()[i]);
				} catch (NullPointerException e) {
					System.out.println("You do not have this skill");
				}
			}
			OutCommand = "";
			break;
		}
		case "view e": {
			System.out.println(Monster);
			OutCommand = "";
			break;
		}
		case "attack": {
			Player.Attack(Monster);
			break;
		}
		case "power attack": {
			Player.Power_Attack(Monster);
			break;
		}
		case "u skill silver-b": {
			Player.UseSkill(Monster, Player.getSkill(0));
			break;
		}
		case "u skill sneak-a": {
			Player.UseSkill(Monster, Player.getSkill(1));
			break;
		}
		case "u skill venom-s": {
			Player.UseSkill(Monster, Player.getSkill(2));
			break;
		}
		case "u skill troll-r": {
			Player.UseSkill(Monster, Player.getSkill(3));
			break;
		}
		case "u skill thick-h": {
			Player.UseSkill(Monster, Player.getSkill(4));
			break;
		}
		case "u skill super-s": {
			Player.UseSkill(Monster, Player.getSkill(5));
			break;
		}
		case "u skill hypnotize": {
			Player.UseSkill(Monster, Player.getSkill(6));
			break;
		}
		case "u skill rainbow-b": {
			Player.UseSkill(Monster, Player.getSkill(7));
			break;
		}
		case "u skill metal-a": {
			Player.UseSkill(Monster, Player.getSkill(8));
			break;
		}
		case "flee": {
			System.out.println("You fled from the monster");
			Player.setStatus("Scared");
			break;
		}
		default: {
			System.out.println("Invalid command, please try again");
			OutCommand = "";
		}
		}
		return OutCommand;
	}

	public static String Command_Map(String Command, Player Player) {
		String OutCommand = "check while loop";
		Command = Command.toLowerCase();
		switch (Command) {
		case "go map dark-f": {
			Player.Travel("Dark Forest", Command);
			break;
		}
		case "go map flower-f": {
			Player.Travel("Flower Field", Command);
			break;
		}
		default: {
			System.out.println("Invalid command, please try again");
			OutCommand = "";
		}
		}
		return OutCommand;
	}

	public static String Command_Area(String Command, Player Player, Map[] Map, LinkedHashMap Map_Skill) {
		String OutCommand = "check while loop";
		Command = Command.toLowerCase();
		switch (Command) {
		case "go area midnight-f": {
			Player.Travel("Midnight Forest", Command);
			break;
		}
		case "go area howling-c": {
			Player.Travel("Howling Canyon", Command);
			break;
		}
		case "go area creepy-g-f": {
			Player.Travel("Creepy Giant Oak Forest", Command);
			break;
		}
		case "go area black-r": {
			Player.Travel("Black River", Command);
			break;
		}
		case "go area cave-d": {
			Player.Travel("Cave of Despair", Command);
			break;
		}
		case "back": {
			ChooseMap(Player, Map, Map_Skill);
		}
		default: {
			System.out.println("Invalid command, please try again");
			OutCommand = "";
		}
		}
		return OutCommand;
	}

	public static void Tutorial(Player Player, LinkedHashMap Map_Skill, Monster Monster, Map[] Map) {
		Scanner console = new Scanner(System.in);
		String Next = "";
		String Command = "";
		System.out.println("Begining tutorials....\n");
		System.out.println("Your objective in this tutorial is to learn the basic commands of the game\n"
				+ "(hit 'Enter' to go next)");
		Next = console.nextLine();
		System.out.println("---------------------------------------");
		Command = "";
		while (Command.equalsIgnoreCase("")) {
			System.out.println(
					"Begining travelling tutorial...." + "\n\nWhen you start the game, you will have to choose a map");
			System.out.println("\nYou can try to go to map Dark Forest by using the command 'go map dark-f'");
			Command = console.nextLine();
			Command = Command_Map(Command, Player);
		}
		System.out.println("---------------------------------------");
		Command = "";
		while (Command.equalsIgnoreCase("")) {
			System.out.println(
					"You can next try to go to different area in the Dark Forest" + "\nHere is a list of areas:");
			for (int h = 0; h < Map[0].getArea().length; h++) {
				System.out.println("-" + Map[0].getArea()[h].getArea_Name());
			}
			System.out.println("Try to use the command 'go area midnight-f', to go into Midnight Forest");
			Command = console.nextLine();
			Command = Command_Area(Command, Player, Map, Map_Skill);
			System.out.println("---------------------------------------");
		}
		System.out.println("Begining combat tutorial....\n");
		System.out.println("You are now going to try and fight the " + Monster.getName());
		System.out.println("\nDuring combat you have 6 options:\n" + "1. View enemey status\n2. View player status\n"
				+ "3. Use normal attack\n4. Use power attack\n" + "5. Use your skill\n6. Flee from the fight!!!"
				+ "\n(hit 'Enter' to go next)");
		Next = console.nextLine();
		System.out.println("---------------------------------------");
		Command = "";
		while (Command.equalsIgnoreCase("")) {
			System.out.println("To view enemy stats use command 'view e'");
			Command = console.nextLine();
			Command = Command_Tutorial(Command, Player, Monster);
		}
		System.out.println("---------------------------------------");
		Command = "";
		while (Command.equalsIgnoreCase("")) {
			System.out.println("To view your own stats use command 'view p'");
			Command = console.nextLine();
			Command = Command_Tutorial(Command, Player, Monster);
		}
		System.out.println("---------------------------------------");
		Command = "";
		while (Command.equalsIgnoreCase("")) {
			System.out.println(
					"You can also view specific status like your HP" + "\nUse command 'view p.hp' to view your own HP");
			Command = console.nextLine();
			Command = Command_Tutorial(Command, Player, Monster);
		}
		System.out.println("---------------------------------------");
		Command = "";
		while (Command.equalsIgnoreCase("")) {
			System.out.println("To attack the enemy use command 'attack'");
			Command = console.nextLine();
			Command = Command_Tutorial(Command, Player, Monster);
		}
		System.out.println("---------------------------------------");
		Command = "";
		while (Command.equalsIgnoreCase("")) {
			System.out.println("Now it will be the enemy's turn to attack");
			Monster.Attack(Player);
			System.out.println("---------------------------------------");
			System.out.println("Oh no! you got damaged\nYour current HP is at: " + Player.getHP());
			System.out.println("You need to fight back!!!\nUse your power attack");
			System.out.println("To  Power attack the enemy use command 'power attack'");
			Command = console.nextLine();
			Command = Command_Tutorial(Command, Player, Monster);
		}
		System.out.println("---------------------------------------");
		Command = "";
		while (Command.equalsIgnoreCase("")) {
			Monster.Attack(Player);
			System.out.println("Your current HP is at: " + Player.getHP());
			System.out.println("Fight back!!!\nUse your skill");
			System.out
					.println("You don't have any skill rightnow, skill 'Silver Bite' temporary added to your arsenal");
			Player.setSkill((Skill) Map_Skill.get("Silver Bite"), 0);
			Player.setSkill_Level(1, 0);
			System.out.println("To  use skill use command 'u skill silver-b'");
			Command = console.nextLine();
			Command = Command_Tutorial(Command, Player, Monster);
			System.out.println(Monster.getName() + "current HP: " + Monster.getHP());
			Player.setSkill((Skill) null, 0);
		}
		System.out.println("---------------------------------------");
		Command = "";
		while (Command.equalsIgnoreCase("")) {
			System.out.println("The enemy used a skill!!!");
			Monster.UseSkill(Player, Monster.getSkill());
			System.out.println("Your current HP is at: " + Player.getHP());
			System.out.println("It is better for you to flee from this monster"
					+ "\n\nWhen you flee you will go back to mode selecting menu");
			System.out.println("To flee use command 'flee'");
			Command = console.nextLine();
			Command = Command_Tutorial(Command, Player, Monster);
		}

		System.out.println("---------------------------------------");
		System.out.println("Begining Invasion tutorial....");
		System.out.println("\nYour main goal in the game is to destroy as many human habitats as you can"
				+ "\nThere are many types of human habitats, from small villages to big hunter's shelters"
				+ "\nTo start invading, you must first have enough powerlevel");
		System.out.println("In each human habitat there will also reside a human boss,"
				+ " which you need to defeat to sucessfully destroy the habitat");
		System.out.println("(Hit enter to finish tutorial)");
		Next = console.nextLine();
	}

	public static void B_Story(Player Player) {
		System.out.println("---------------------------------------");
		Scanner console = new Scanner(System.in);
		System.out.println("Your story of adventure begins like this...\n");
		System.out.println("A cute little slime named: \n" + Player.getName()
				+ " lives peacefully with its family in the slime village.\n" + "One day, " + Player.getName()
				+ " travelled to another village to see its friend.\n");
		System.out.println("Hit enter to go next ==>");
		String Next = console.nextLine();
		System.out.println(
				"Unfortunately, humans dicovered that slime's heart can be used for fast recovery and immortality.\n"
						+ "Many groups of human came to slime village. They killed all the slimes and took their hearts.\n");
		System.out.println("Hit enter to go next ==>");
		Next = console.nextLine();
		System.out.println("When " + Player.getName()
				+ " came back home, everyone has been killed and the village is full of dead slimes with their heart removed.\n"
				+ "The death of its family changed " + Player.getName()
				+ "'s thought about the outside world and the extinction of human became it's goal!!!");
		System.out.println("\nHit enter to finish background story !!!");
		Next = console.nextLine();
		System.out.println("---------------------------------------");
	}

	public static void ChooseMap(Player Player, Map[] Map, LinkedHashMap<String, Skill> Map_Skill) {
		Scanner console = new Scanner(System.in);
		String Check = "";
		while (Check.equalsIgnoreCase("")) {
			System.out.println("Which map do you want to go to?");
			for (int i = 0; i < Map.length; i++) {
				System.out.println("-" + Map[i].getName() + ": " + Map[i].getDesc());
			}
			String Command = console.nextLine();
			Check = Command_Map(Command, Player);
		}

		ChooseArea(Player, Map, Map_Skill);

	}

	public static void ChooseArea(Player Player, Map[] Map, LinkedHashMap<String, Skill> Map_Skill) {
		Scanner console = new Scanner(System.in);
		if (Player.getCurrent_Location().contains("@")) {
			String[] Location = new String[2];
			Location = Player.getCurrent_Location().split("@");
			String Map_Location = Location[0];
			Player.setCurrent_Location(Map_Location);
		}
		switch (Player.getCurrent_Location()) {
		case "Dark Forest": {
			String Check = "";
			while (Check.equalsIgnoreCase("")) {
				System.out.println("Select an area:");
				for (int h = 0; h < Map[0].getArea().length; h++) {
					System.out.println("-" + Map[0].getArea()[h].getArea_Name() + " || Difficulty level: "
							+ Map[0].getArea()[h].getDifficulty_Level());
				}
				System.out.println("-Back");
				String Command = console.nextLine();
				Check = Command_Area(Command, Player, Map, Map_Skill);
			}
			ChooseMode(Player, Map, Map_Skill);
			break;
		}
		default: {
			System.out.println("Invalid input");
		}
		}
	}

	public static void ChooseMode(Player Player, Map[] Map, LinkedHashMap<String, Skill> Map_Skill) {
		Scanner console = new Scanner(System.in);
		String HuntorInvade_yn = "";
		while (!HuntorInvade_yn.equalsIgnoreCase("hunting")) {
			System.out.println("Select Mode: \n-Hunting\n-Invasion\n-Back");
			HuntorInvade_yn = console.nextLine();
			if (HuntorInvade_yn.equalsIgnoreCase("hunting")) {
				System.out.println("---------------------------------------");
				GamePlay_Hunting(Player, Map, Map_Skill);
			} else if (HuntorInvade_yn.equalsIgnoreCase("invasion")) {
				GamePlay_Invasion(Player, Map, Map_Skill);
				System.out.println("---------------------------------------");
				HuntorInvade_yn = "hunting";
			} else if (HuntorInvade_yn.equalsIgnoreCase("back")) {
				System.out.println("---------------------------------------");
				ChooseArea(Player, Map, Map_Skill);
				HuntorInvade_yn = "hunting";
			} else {
				System.out.println("Please select ony 'hunting' or 'invasion' or 'Back'");
			}
		}
	}

	public static int MapCode(Player Player) {
		int Map_Code = 0;
		if (Player.getCurrent_Location().contains("Dark Forest")) {
			Map_Code = 0;
		} else if (Player.getCurrent_Location().contains("Flower Field")) {
			Map_Code = 1;
		}

		return Map_Code;

	}

	public static int AreaCode(Player Player) {
		int Area_Code = 0;
		String[] Location = new String[2];
		Location = Player.getCurrent_Location().split("@");
		String Map_Location = Location[0];
		String Area_Location = Location[1];
		switch (Area_Location) {
		case "Midnight Forest": {
			Area_Code = 0;
			break;
		}
		case "Howling Canyon": {
			Area_Code = 1;
			break;
		}
		case "Creepy Giant Oak Forest": {
			Area_Code = 2;
			break;
		}
		case "Black River": {
			Area_Code = 3;
			break;
		}
		case "Cave of Despair": {
			Area_Code = 4;
			break;
		}
		}
		return Area_Code;
	}

	public static int Position(Monster Monster) {
		int Position = 0;
		switch (Monster.getSkill().getName()) {
		case "Silver Bite": {
			Position = 0;
			break;
		}
		case "Sneak Attack": {
			Position = 1;
			break;
		}
		case "Venom Strike": {
			Position = 2;
			break;
		}
		case "Troll Regen": {
			Position = 3;
			break;
		}
		case "Thick Hide": {
			Position = 4;
			break;
		}
		}
		return Position;

	}

	public static void GamePlay_Hunting(Player Player, Map[] Map, LinkedHashMap<String, Skill> Map_Skill) {
		Scanner console = new Scanner(System.in);
		int Map_Code = MapCode(Player);
		int Area_Code = AreaCode(Player);
		Monster[] Backup_Monster = Map[Map_Code].getArea()[Area_Code].getMonster_List()[0]
				.Backup(Map[Map_Code].getArea()[Area_Code].getMonster_List());
		Player Backup = Player.Backup();
		System.out.println("You have entered hunting mode !!!");
		System.out.println("You are now in " + Map[Map_Code].getArea()[Area_Code].getArea_Name() + "\n");
		for (int i = 0; i < Map[Map_Code].getArea()[Area_Code].getMonster_List().length; i++) {
			if (!Player.getStatus().equalsIgnoreCase("Dead")) {
				System.out.println(
						"You have encountered " + Map[Map_Code].getArea()[Area_Code].getMonster_List()[i].getName());
				while (!Map[Map_Code].getArea()[Area_Code].getMonster_List()[i].getStatus().equalsIgnoreCase("Dead")
						&& !Player.getStatus().equalsIgnoreCase("Dead")) {
					String Check = "";
					while (Check.equalsIgnoreCase("")) {
						System.out.println("What do you choose to do?");
						String Command = console.nextLine();
						Check = Command_Combat(Command, Player,
								Map[Map_Code].getArea()[Area_Code].getMonster_List()[i]);
						if (Player.getStatus().equalsIgnoreCase("Scared")) {
							Player.setStatus("Healthy");
							Player.Reset(Player, Backup);
							Player.SkillReset();
							Map[Map_Code].getArea()[Area_Code].getMonster_List()[0]
									.Reset(Map[Map_Code].getArea()[Area_Code].getMonster_List(), Backup_Monster);
							ChooseMode(Player, Map, Map_Skill);
						}
					}
					if (!Map[Map_Code].getArea()[Area_Code].getMonster_List()[i].getStatus().equalsIgnoreCase("Dead")) {
						Map[Map_Code].getArea()[Area_Code].getMonster_List()[i]
								.AI(Map[Map_Code].getArea()[Area_Code].getMonster_List()[i], Player);
					}
					System.out.println("\n" + Player.getName() + "\nCurrent HP: " + Player.getHP() + "\nCurrent MP: "
							+ Player.getMP());
					System.out.println(Map[Map_Code].getArea()[Area_Code].getMonster_List()[i].getName() + "\nCurrent HP: "
							+ Map[Map_Code].getArea()[Area_Code].getMonster_List()[i].getHP() + "\nCurrent MP: "
							+ Map[Map_Code].getArea()[Area_Code].getMonster_List()[i].getMP());
					System.out.println("---------------------------------------");
					for (int j = 0; j < Player.getSkills().length; j++) {
						try {
							if (Player.getSkill(j).getCD() > 0) {
								Player.getSkill(j).setCD(Player.getSkill(j).getCD() - 1);
							}
						} catch (NullPointerException e) {

						}
					}
				}
				if (!Player.getStatus().equalsIgnoreCase("Dead")) {
					System.out.println(
							"You have beaten " + Map[Map_Code].getArea()[Area_Code].getMonster_List()[i].getName());
					System.out.println("---------------------------------------");
					if (Map[Map_Code].getArea()[Area_Code].getMonster_List()[i].getStatus().equalsIgnoreCase("Dead")) {
						boolean GotSkill = false;
						int Position = Position(Map[Map_Code].getArea()[Area_Code].getMonster_List()[i]);
						try {
							if (Player.getSkill(Position)
									.equals(Map[Map_Code].getArea()[Area_Code].getMonster_List()[i].getSkill())) {
								Player.setSkill_Level(Player.getSkill_Levels()[Position] + 1, Position);
								System.out.println("Your " + Player.getSkill(Position).getName() + " has leveled up");
								System.out.println("Current Skill level: " + Player.getSkill_Level(Position));
								GotSkill = true;
							}
						} catch (NullPointerException e) {
							Player.setSkill((Skill) Map_Skill
									.get(Map[Map_Code].getArea()[Area_Code].getMonster_List()[i].getSkill().getName()),
									Position);
							Player.setSkill_Level(1, Position);
							if (GotSkill == false) {
								System.out.println("You have aquired skill: \n" + Player.getSkill(Position));
								GotSkill = true;

							}
						}
						System.out.println("---------------------------------------");
					}
					Player.Reset(Player, Backup);
					Player.SkillReset();
					Map[Map_Code].getArea()[Area_Code].getMonster_List()[0]
							.Reset(Map[Map_Code].getArea()[Area_Code].getMonster_List(), Backup_Monster);
				}
			}
		}
		System.out.println("You have beaten all monsters in this area !!!");
		System.out.println("---------------------------------------");
		ChooseMode(Player, Map, Map_Skill);
	}

	public static void GamePlay_Invasion(Player Player, Map[] Map, LinkedHashMap<String, Skill> Map_Skill) {
		Scanner console = new Scanner(System.in);
		int Map_Code = MapCode(Player);
		int Area_Code = AreaCode(Player);
		// Human_Boss[] Backup_Monster = (Human_Boss[])
		// Map[Map_Code].getArea()[Area_Code].getMonster_List()[0]
		// .Backup(Map[Map_Code].getArea()[Area_Code].getMonster_List());
		Player Backup = Player.Backup();
		System.out.println("You have entered invasion mode !!!");
		System.out.println("You are now in " + Map[Map_Code].getArea()[Area_Code].getArea_Name() + "\n");
		System.out.println("These area the human habitats you need to destroy: ");
		for (int a = 0; a < Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List().length; a++) {
			System.out.println(Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List()[a].getName() + " Status: "
					+ Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List()[a].getStatus());
			System.out.println("Human Boss "
					+ Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List()[a].getHuman_Boss().getName()
					+ "Power Level: "
					+ Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List()[a].getHuman_Boss().getPower_level());
		}
		System.out.println("---------------------------------------");
		System.out.println("Destroy them to clear this area from pesky humans");
		System.out.println("---------------------------------------");
		for (int i = 0; i < Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List().length; i++) {
			while (!Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List()[i].getStatus()
					.equalsIgnoreCase("Destroyed")) {
				if (!Player.getStatus().equalsIgnoreCase("Dead")) {
					System.out.println("You have encountered "
							+ Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List()[i].getHuman_Boss().getName());
					while (!Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List()[i].getHuman_Boss().getStatus()
							.equalsIgnoreCase("Dead") && !Player.getStatus().equalsIgnoreCase("Dead")) {
						String Check = "";
						while (Check.equalsIgnoreCase("")) {
							System.out.println("What do you choose to do?");
							String Command = console.nextLine();
							Check = Command_Combat(Command, Player,
									Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List()[i].getHuman_Boss());
							if (Player.getStatus().equalsIgnoreCase("Scared")) {
								Player.setStatus("Healthy");
								Player.Reset(Player, Backup);
								Player.SkillReset();
								// Map[Map_Code].getArea()[Area_Code].getMonster_List()[0]
								// .Reset(Map[Map_Code].getArea()[Area_Code].getMonster_List(), Backup_Monster);
								ChooseMode(Player, Map, Map_Skill);
							}
						}
						if (!Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List()[i].getHuman_Boss().getStatus()
								.equalsIgnoreCase("Dead")) {
							Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List()[i].getHuman_Boss().AI(
									Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List()[i].getHuman_Boss(),
									Player);
						}
						System.out.println("\n" + Player.getName() + "\nCurrent HP: " + Player.getHP()
								+ "\nCurrent MP: " + Player.getMP());
						System.out.println(
								Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List()[i].getHuman_Boss().getName()
										+ "\nCurrent HP: "
										+ Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List()[i].getHuman_Boss()
												.getHP()
										+ "\nCurrent MP: "
										+ Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List()[i].getHuman_Boss()
												.getMP());
						System.out.println("---------------------------------------");
						for (int j = 0; j < Player.getSkills().length; j++) {
							try {
								if (Player.getSkill(j).getCD() > 0) {
									Player.getSkill(j).setCD(Player.getSkill(j).getCD() - 1);
								}
							} catch (NullPointerException e) {

							}
						}
					}
					if (!Player.getStatus().equalsIgnoreCase("Dead")) {
						System.out.println(
								"You have beaten " + Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List()[i]
										.getHuman_Boss().getName());
						System.out.println("---------------------------------------");
						Map[Map_Code].getArea()[Area_Code].getHuman_Habitat_List()[i].setStatus("Destroyed");
						Player.Reset(Player, Backup);
						Player.SkillReset();
					}
				}
			}
		}
		System.out.println("You have destroyed all human habitats in this area !!!");
		System.out.println("---------------------------------------");
		Area.CheckGameStatus(Map);
		ChooseMode(Player, Map, Map_Skill);

	}
}
