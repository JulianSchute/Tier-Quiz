package objects;

public class Animal {

	private String name;
	private String scientificName;
	private String family;
	private String order; //Raubtier und co
	private int lifeExpectancy;//only max
	private int size; //in cm
	private int weight; //in gram
	private int population;
	private String habitat;

	public Animal(String name,String scientificName, String family, String order, int lifeExpectancy, int size, int weight, int population, String habitat) 
	{
		this.name = name;
		this.scientificName = scientificName;
		this.family = family;
		this.order = order; //Raubtier und co
		this.lifeExpectancy=   lifeExpectancy;//only max
		this.size = size; //in cm
		this.weight = weight; //in gram
		this.population = population;//
		this.habitat = habitat;
	}

	//Empty Animal for the Designer
	public Animal() {

	}
	
	public String toString() {
		return (name + ";" + scientificName + ";"+ family + ";"+ order+ ";"+lifeExpectancy+ ";"+size+ ";"+weight+ ";"+population+ ";"+habitat);
	}

	public String getName() {
		return name;
	}

	public String getScientificName() {
		return scientificName;
	}

	public String getFamily() {
		return family;
	}

	public String getOrder() {
		return order;
	}

	public int getLifeExpectancy() {
		return lifeExpectancy;
	}

	public int getSize() {
		return size;
	}

	public int getWeight() {
		return weight;
	}

	public int getPopulation() {
		return population;
	}

	public String getHabitat() {
		return habitat;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setLifeExpectancy(int lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}

	public String getValueIncluding(int i, int aditionalInt) {
		String returnValue = "";
		if(aditionalInt == 3) {
			if(i == 0) {
				returnValue = i +" Jahr";	
			}else {
				returnValue = i +" Jahre";
			}
		}
		if(aditionalInt == 4) {
			if(i<100) {
				returnValue = i + " cm";
			}else {				
				returnValue = (i/100) + "," + (i-((i/100)*100))+ " m" ; 
			}
		}
		if(aditionalInt == 5) {
			if(i<1000) {
				returnValue = i + " g";
			}
			if(i>999 && i<1000000) {			
				returnValue = (i/1000) + " kg";
			}
			if( i>=1000000) {
				returnValue = (i/1000000)+"," + ((i-((i/1000000)*1000000))/1000) + " Tonnen";

			}
		}
		if(aditionalInt == 6) {
			returnValue = i + " auf der Erde";
			if(i == 0) {
				returnValue = "Soviele das Niemand sie zählte";
			}
		}
		return returnValue;
	}

}
