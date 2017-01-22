/**
 * Created by MLH-Admin on 1/21/2017.
 */
package hacks.sb.sbhacks17;
/**
 * Created by MLH-Admin on 1/21/2017.
 */


/**
 * Created by MLH-Admin on 1/21/2017.
 */
import java.util.ArrayList;
import java.util.List;

public class countyList {
    public List <county> countyList;
    public boolean isNewList;



    public countyList(){
        countyList = new ArrayList<county>();
        isNewList = true;
    }






    public countyList(countyList list){
        countyList = new ArrayList<county>();
        int size = list.countyList.size();

        for (int count = 0; count < size; count ++)
            countyList.add(list.countyList.get(count));
    }




    public county element(int element){
        assert (element < countyList.size());
        return countyList.get(element);
    }






    public void removeElement(int element){

        countyList.remove(element);
    }







    public void addCounty (county newCounty){
        isNewList = false;
        countyList.add(newCounty);
    }



    public int size(){
        return countyList.<county>size();
    }



    public void print(){
        int size = countyList.<county>size();
        for (int count = 0; count < size; count++){
            System.out.println(countyList.<county>get(count).name + " | " + countyList.<county>get(count).population_density + " | " + countyList.<county>get(count).householdIncome + " | " + countyList.<county>get(count).educationalValue + " | " + countyList.<county>get(count).mortgageRate);

        }

    }
}
