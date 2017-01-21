/**
 * Created by MLH-Admin on 1/21/2017.
 */
public class county {
    public String name;
    public double population_density;
    public double householdIncome;
    public int row;

    public county(String name, double density, double householdIncome, int row){
        this.name = name;
        population_density = density;
        this.householdIncome = householdIncome;
        this.row = row;

    }



    public county(){
        row = -1;

    }




    public county(county copyFrom){
        name = copyFrom.name;
        population_density = copyFrom.population_density;
        householdIncome = copyFrom.population_density;
        row = copyFrom.row;
    }



    public double getField(String type){

        switch (type){
            case "density":
                return population_density;
            case "household.income":
                return householdIncome;

            default:
                return -1;


        }
    }






}
