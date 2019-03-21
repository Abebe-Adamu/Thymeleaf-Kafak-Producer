package model;

public class Menu {
	 private String food;
	    private String drink;
		public Menu(String food, String drink) {
			super();
			this.food = food;
			this.drink = drink;
		}
		public String getFood() {
			return food;
		}
		public void setFood(String food) {
			this.food = food;
		}
		public String getDrink() {
			return drink;
		}
		public void setDrink(String drink) {
			this.drink = drink;
		}
	    
}
