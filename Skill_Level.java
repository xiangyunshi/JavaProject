import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Skill_Level {

	private int Effect;
	private int Effect2;

	public Skill_Level(int Effect, int Effect2) {

		this.Effect = Effect;
		this.Effect2 = Effect2;
	}

	public int getEffect() {
		return Effect;
	}

	public void setEffect(int effect) {
		Effect = effect;
	}

	public int getEffect2() {
		return Effect2;
	}

	public void setEffect2(int effect2) {
		Effect2 = effect2;
	}

	public String toString() {
		return "Skill effects: " + Effect + ", " + Effect2;
	}

}
