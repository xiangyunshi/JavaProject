import java.util.Random;

import com.sun.javafx.tools.packager.Main;

import jdk.nashorn.api.tree.ForInLoopTree;

public class Area {

	private String Area_Name;
	private Monster[] Monster_List;
	private Human_Habitat[] Human_Habitat_List;
	private int Difficulty_Level;

	public Area(String Area_Name, int Difficulty_Level, Monster[] Monster_List, Human_Habitat[] Human_Habitat_List) {
		this.Area_Name = Area_Name;
		this.Monster_List = Monster_List;
		this.Human_Habitat_List = Human_Habitat_List;
		this.Difficulty_Level = Difficulty_Level;
	}

	public String getArea_Name() {
		return Area_Name;
	}

	public void setArea_Name(String area_Name) {
		Area_Name = area_Name;
	}

	public int getDifficulty_Level() {
		return Difficulty_Level;
	}

	public void setDifficulty_Level(int difficulty_Level) {
		Difficulty_Level = difficulty_Level;
	}

	public Monster[] getMonster_List() {
		return Monster_List;
	}

	public void setMonster_List(Monster[] monster_List) {
		Monster_List = monster_List;
	}

	public Human_Habitat[] getHuman_Habitat_List() {
		return Human_Habitat_List;
	}

	public void setHuman_Habitat_List(Human_Habitat[] human_Habitat_List) {
		Human_Habitat_List = human_Habitat_List;
	}

	public static void CheckGameStatus(Map[] Map) {
		boolean CheckGameStatus = false;
		String Status = "";
		for (int i = 0; i < Map.length; i++) {
			for (int j = 0; j < Map[i].getArea().length; j++) {
				for (int k = 0; k < Map[i].getArea()[j].getHuman_Habitat_List().length; k++) {
					Status += Map[i].getArea()[j].getHuman_Habitat_List()[k].getStatus();
				}
			}
		}
		if (!Status.contains("Populated by Human")) {
			CheckGameStatus = true;
		}
		if (CheckGameStatus == true) {
			System.out.println("Congratulation you have cleared the game !!!");
		}
	}

	public String toString() {
		String toreturn = "";
		for (int i = 0; i < Monster_List.length; i++) {
			toreturn += this.Monster_List[i].getName() + ", ";

		}
		String toreturn2 = "";
		for (int i = 0; i < Human_Habitat_List.length; i++) {
			try {
				toreturn2 += Human_Habitat_List[i].getName() + ", ";
			} catch (NullPointerException e) {

			}

		}
		return "Area name: " + this.Area_Name + "\nDifficulty level: " + this.Difficulty_Level + "\nMonster list: "
				+ toreturn + "\nHuman habitat in the area: \n" + toreturn2;
	}

}
