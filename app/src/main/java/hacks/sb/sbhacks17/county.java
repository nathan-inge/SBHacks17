/**
 * Created by MLH-Admin on 1/21/2017.
 */

package hacks.sb.sbhacks17;

public class county {
    public String name;
    public double population_density;
    public double householdIncome;
    public int row;
    public double lat, lon;

    public county(String name, double density, double householdIncomeToAdd, int row){
        this.name = name;
        population_density = density;
        this.householdIncome = householdIncomeToAdd;
        this.row = row;

    }



    public county(){
        row = -1;

    }




    public county(county copyFrom){
        name = copyFrom.name;
        population_density = copyFrom.population_density;
        householdIncome = copyFrom.householdIncome;
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
