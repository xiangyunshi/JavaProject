import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Create {

	public Create() {

	}

	public LinkedHashMap<String, ArrayList<Skill_Level>> Skill_Level() throws FileNotFoundException {
		int i = 0;
		int FileCount = 0;
		String[] Skill_Level_Info;
		File directory = new File("D:\\Program Files\\Game_dev2\\src\\Database\\Skills\\Effects");
		for (File f : directory.listFiles()) {
			if (f.getName().startsWith("Skill")) {
				FileCount++;
			}
		}
		LinkedHashMap<String, ArrayList<Skill_Level>> Map_Skill_Level = new LinkedHashMap<String, ArrayList<Skill_Level>>();
		while (i < FileCount) {
			ArrayList<Skill_Level> Skill_Level_List = new ArrayList<Skill_Level>();
			Scanner Skill_Level_Data = new Scanner(new File(
					"D:\\Program Files\\Game_dev2\\src\\Database\\Skills\\Effects\\Skill" + (i + 1) + "_l.csv"));

			String Skill_Name = Skill_Level_Data.nextLine();
			while (Skill_Level_Data.hasNext()) {
				Skill_Level_Info = Skill_Level_Data.nextLine().split(",");
				int Effect = Integer.parseInt(Skill_Level_Info[0]);
				int Effect2 = Integer.parseInt(Skill_Level_Info[1]);
				Skill_Level_List.add(new Skill_Level(Effect, Effect2));
			}
			Map_Skill_Level.put(Skill_Name, Skill_Level_List);
			i++;

		}
		return Map_Skill_Level;
	}

	public LinkedHashMap<String, Skill> Skill(LinkedHashMap<String, ArrayList<Skill_Level>> Map_Skill_Level)
			throws FileNotFoundException {
		String[] Skill_Info;
		LinkedHashMap<String, Skill> Map_Skill = new LinkedHashMap<String, Skill>();
		Scanner Skill_Info_Data = new Scanner(
				new File("D:\\Program Files\\Game_dev2\\src\\Database\\Skills\\Skill_Info.csv"));
		while (Skill_Info_Data.hasNext()) {
			Skill_Info = Skill_Info_Data.nextLine().split(",");
			String Name = Skill_Info[0];
			String Type = Skill_Info[1];
			String Desc = Skill_Info[2];
			int Manacost = Integer.parseInt(Skill_Info[3]);
			int Cooldown = Integer.parseInt(Skill_Info[4]);
			String Skill_From = Skill_Info[5];
			int Power_Gain = Integer.parseInt(Skill_Info[6]);

			Map_Skill.put(Name,
					new Skill(Name, Type, Desc, Map_Skill_Level.get(Name), Manacost, Cooldown, Skill_From, Power_Gain));

		}
		return Map_Skill;

	}

	public ArrayList<Monster> Monster(LinkedHashMap<String, Skill> Map_Skill) throws FileNotFoundException {
		String[] Monster_Info;
		ArrayList<Monster> Monster_List = new ArrayList<Monster>();
		Scanner Monster_Info_Data = new Scanner(
				new File("D:\\Program Files\\Game_dev2\\src\\Database\\Monsters\\Monster_Info.csv"));
		while (Monster_Info_Data.hasNextLine()) {
			Monster_Info = Monster_Info_Data.nextLine().split(",");
			String Name = Monster_Info[0];
			int Power_Level = Integer.parseInt(Monster_Info[1]);
			String Status = Monster_Info[2];
			int HP = Integer.parseInt(Monster_Info[3]);
			int MP = Integer.parseInt(Monster_Info[4]);
			int Damage = Integer.parseInt(Monster_Info[5]);
			int Power_Damage = Integer.parseInt(Monster_Info[6]);
			int Power_Damage_Cost = Integer.parseInt(Monster_Info[7]);
			String Skill_Name = (Monster_Info[8]);
			int Current_Skill_Level = Integer.parseInt(Monster_Info[9]);

			Monster_List.add(new Monster(Name, Power_Level, Status, HP, MP, Damage, Power_Damage, Power_Damage_Cost,
					Map_Skill.get(Skill_Name), Current_Skill_Level));

		}
		return Monster_List;

	}

	public ArrayList<Monster> Human_Boss(LinkedHashMap<String, Skill> Map_Skill) throws FileNotFoundException {
		String[] Human_Boss_Info;
		ArrayList<Monster> Human_Boss_List = new ArrayList<Monster>();
		Scanner Human_Boss_Info_Data = new Scanner(
				new File("D:\\Program Files\\Game_dev2\\src\\Database\\Human_Bosses\\Human_Boss_Info.csv"));
		while (Human_Boss_Info_Data.hasNextLine()) {
			Human_Boss_Info = Human_Boss_Info_Data.nextLine().split(",");
			String Name = Human_Boss_Info[0];
			String Boss_Rank = Human_Boss_Info[1];
			int Power_Level = Integer.parseInt(Human_Boss_Info[2]);
			String Status = Human_Boss_Info[3];
			int HP = Integer.parseInt(Human_Boss_Info[4]);
			int MP = Integer.parseInt(Human_Boss_Info[5]);
			int Damage = Integer.parseInt(Human_Boss_Info[6]);
			int Power_Damage = Integer.parseInt(Human_Boss_Info[7]);
			int Power_Damage_Cost = Integer.parseInt(Human_Boss_Info[8]);
			String Skill_Name = (Human_Boss_Info[9]);
			int Current_Skill_Level = Integer.parseInt(Human_Boss_Info[10]);

			Human_Boss_List.add(new Human_Boss(Name, Boss_Rank, Power_Level, Status, HP, MP, Damage, Power_Damage,
					Power_Damage_Cost, Map_Skill.get(Skill_Name), Current_Skill_Level));
		}
		return Human_Boss_List;

	}

	public ArrayList<Human_Habitat> Human_Habitat(ArrayList<Monster> Human_Boss_List) throws FileNotFoundException {
		int j = 0;
		String[] Human_Habitat_Info;
		ArrayList<Human_Habitat> Human_Habitat_List = new ArrayList<Human_Habitat>();
		Scanner Human_Habitat_Info_Data = new Scanner(
				new File("D:\\Program Files\\Game_dev2\\src\\Database\\Human_Habitats\\Human_Habitat_Info.csv"));
		while (Human_Habitat_Info_Data.hasNext()) {
			Human_Habitat_Info = Human_Habitat_Info_Data.nextLine().split(",");
			String Name = Human_Habitat_Info[0];
			String Status = Human_Habitat_Info[1];
			int Power_Requirement = Integer.parseInt(Human_Habitat_Info[2]);
			String Human_Boss = Human_Habitat_Info[3];
			Human_Habitat_List
					.add(new Human_Habitat(Name, Status, Power_Requirement, (Human_Boss) Human_Boss_List.get(j)));
			j++;

		}
		return Human_Habitat_List;

	}

	public ArrayList<Area> Area(ArrayList<Monster> Monster_List, ArrayList<Human_Habitat> Human_Habitat_List)
			throws FileNotFoundException {

		Monster[] Monster_List_Area1 = { Monster_List.get(0), Monster_List.get(1), Monster_List.get(1),
				Monster_List.get(1) };
		Monster[] Monster_List_Area2 = { Monster_List.get(0), Monster_List.get(0), Monster_List.get(2),
				Monster_List.get(2) };
		Monster[] Monster_List_Area3 = { Monster_List.get(3), Monster_List.get(3) };
		Monster[] Monster_List_Area4 = { Monster_List.get(2), Monster_List.get(2), Monster_List.get(2),
				Monster_List.get(1), Monster_List.get(0) };
		Monster[] Monster_List_Area5 = { Monster_List.get(4), Monster_List.get(3), Monster_List.get(4) };

		ArrayList<Monster[]> Monster_List_Area = new ArrayList<Monster[]>();
		Monster_List_Area.add(Monster_List_Area1);
		Monster_List_Area.add(Monster_List_Area2);
		Monster_List_Area.add(Monster_List_Area3);
		Monster_List_Area.add(Monster_List_Area4);
		Monster_List_Area.add(Monster_List_Area5);

		Human_Habitat[] Human_Habitat_Map1_Area1 = { Human_Habitat_List.get(0), Human_Habitat_List.get(1),
				Human_Habitat_List.get(2) };
		Human_Habitat[] Human_Habitat_Map1_Area2 = { Human_Habitat_List.get(3), Human_Habitat_List.get(4),
				Human_Habitat_List.get(5), Human_Habitat_List.get(6) };
		Human_Habitat[] Human_Habitat_Map1_Area3 = { Human_Habitat_List.get(7), Human_Habitat_List.get(8),
				Human_Habitat_List.get(9), Human_Habitat_List.get(10) };
		Human_Habitat[] Human_Habitat_Map1_Area4 = { Human_Habitat_List.get(11), Human_Habitat_List.get(12),
				Human_Habitat_List.get(13), Human_Habitat_List.get(14) };
		Human_Habitat Human_Habitat_Map1_Area5[] = {};

		LinkedHashMap<String, Human_Habitat[]> Human_Habitat_List_Area = new LinkedHashMap<String, Human_Habitat[]>();
		Human_Habitat_List_Area.put("Midnight Forest", Human_Habitat_Map1_Area1);
		Human_Habitat_List_Area.put("Howling Canyons", Human_Habitat_Map1_Area2);
		Human_Habitat_List_Area.put("Creepy Giant Oak Forest", Human_Habitat_Map1_Area3);
		Human_Habitat_List_Area.put("Black River", Human_Habitat_Map1_Area4);
		Human_Habitat_List_Area.put("Cave of Despair", Human_Habitat_Map1_Area5);

		int j = 0;
		String[] Map_Area_Info;
		ArrayList<Area> Map_Area_List = new ArrayList<Area>();
		Scanner Map_Area_Info_Data = new Scanner(
				new File("D:\\Program Files\\Game_dev2\\src\\Database\\Areas\\Map_Area_Info.csv"));
		while (Map_Area_Info_Data.hasNextLine()) {
			Map_Area_Info = Map_Area_Info_Data.nextLine().split(",");
			String Map_Name = Map_Area_Info[0];
			String Area_Name = Map_Area_Info[1];
			int Difficulty_Level = Integer.parseInt(Map_Area_Info[2]);

			Map_Area_List.add(new Area(Area_Name, Difficulty_Level, Monster_List_Area.get(j),
					Human_Habitat_List_Area.get(Area_Name)));
			j++;
		}
		return Map_Area_List;

	}

	public Map[] Map(ArrayList<Area> Map_Area_List) {
		Area[] Dark_Forest_Area = { Map_Area_List.get(0), Map_Area_List.get(1), Map_Area_List.get(2),
				Map_Area_List.get(3), Map_Area_List.get(4) };

		Map Dark_Forest = new Map("Dark Forest",
				"A dark forest covered by huge black trees filled with dangerous monsters", Dark_Forest_Area);

		Map[] Map_List = { Dark_Forest };

		return Map_List;

	}

	public Player Player() {
		Skill[] Skills = { null, null, null, null, null, null, null, null, null };
		int[] Skill_Levels = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };

		Player Player = new Player("", 20, "Healthy", 0, 0, 0, 0, 10, "Player tutorial area", Skills, Skill_Levels);
		return Player;

	}
}
