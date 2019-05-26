package utils;

import main.Main;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.input.mouse.WidgetDestination;
import org.osbot.rs07.utility.ConditionalSleep;

import java.util.ArrayList;

//This class takes an array of desired prayers by name and interacts with the quick prayer to setup to only select the
//correct prayers.

public class QuickPrayerSetup {

    private PrayerInterfaceTable table;
    private Main main;

    private Integer[] currentSetQuickPrayers; //the prayer interface values of the current set quick prayers
    private Integer[] desiredSetQuickPrayers;//prayer interface values of desired set quick prayers

    public QuickPrayerSetup(Main m, PrayerInterfaceTable.PrayerNames[] desiredPrayers){
        main = m;
        table = new PrayerInterfaceTable();
        Integer[] ids = new Integer[desiredPrayers.length];
        for(int i = 0; i < desiredPrayers.length; i++){
            ids[i] = table.getPrayerInterfaceID(table.getCrossoverPairByName(desiredPrayers[i]));
        }
        desiredSetQuickPrayers = ids;
        for(int i : desiredSetQuickPrayers){
            main.log(i);
        }
        updateCurrentSetQuickPrayers();
        switchQuickPrayers();
    }

    private void updateCurrentSetQuickPrayers(){
        //The only way to effectively check if a prayer is selected within quick prayers is to turn quickprayers on
        //and then remember which prayers come on as a result, prayer widgets start at 541, 5 and ascend to 541, 33.
        //the widgets are in order of release and as such do not have a strict order within the interface
        ArrayList<Integer> activePrayers = new ArrayList<>();

        if(!main.getTabs().isOpen(Tab.PRAYER)){
            main.getTabs().open(Tab.PRAYER);
        }
        new ConditionalSleep(5000){
            @Override
            public boolean condition() throws InterruptedException {
                return main.getTabs().isOpen(Tab.PRAYER);
            }
        }.sleep();
        //activate quick prayers
        if(!main.getPrayer().isQuickPrayerActive()){
            main.getWidgets().get(160, 14).interact();
        }
        new ConditionalSleep(2000){
            @Override
            public boolean condition() throws InterruptedException {
                return main.getPrayer().isQuickPrayerActive();
            }
        }.sleep();
        //cycle through and learn which are activated
        for(PrayerInterfaceTable.PrayerNames p : PrayerInterfaceTable.PrayerNames.values()){
            if(main.getWidgets().get(541, table.getPrayerInterfaceID(table.getCrossoverPairByName(p))).getInteractActions()[0].equals("Deactivate")){
                main.log(p.toString() + " is active");
                activePrayers.add(table.getPrayerInterfaceID(table.getCrossoverPairByName(p)));
            }
        }
        //deactivate
        if(main.getPrayer().isQuickPrayerActive()){
            main.getWidgets().get(160, 14).interact();
        }
        currentSetQuickPrayers = activePrayers.toArray(new Integer[activePrayers.size()]);
    }

    private void switchQuickPrayers(){
        if(main.getWidgets().get(77, 0) == null){
            main.getWidgets().get(160, 14).interact("Setup");
        }
        new ConditionalSleep(5000){
            @Override
            public boolean condition() throws InterruptedException {
                return main.getWidgets().get(77,0) != null;
            }
        }.sleep();
        for(int i:desiredSetQuickPrayers){
            if(!isPrayerAlreadySet(i)){
                main.log(table.getQuickPrayerInterfaceID(table.getCrossoverPairByPrayerInterface(i)));
                main.getMouse().click(new WidgetDestination(main.getBot(), main.getWidgets().get(77,4, table.getQuickPrayerInterfaceID(table.getCrossoverPairByPrayerInterface(i)))));
                //main.getWidgets().interact(77, 4, table.getQuickPrayerInterfaceID(table.getCrossoverPairByPrayerInterface(i)), "Toggle");
            }
        }
        for(int i :currentSetQuickPrayers){
            if(!isPrayerPendingSet(i)){
                main.getMouse().click(new WidgetDestination(main.getBot(), main.getWidgets().get(77,4, table.getQuickPrayerInterfaceID(table.getCrossoverPairByPrayerInterface(i)))));
            }
        }
        main.getMouse().click(new WidgetDestination(main.getBot(), main.getWidgets().get(77,5)));

    }

    private boolean isPrayerAlreadySet(int id){
        for(int i:currentSetQuickPrayers){
            if(id == i){
                return true;
            }
        }
        return false;
    }

    private boolean isPrayerPendingSet(int id){
        for(int i:desiredSetQuickPrayers){
            if(id == i){
                return true;
            }
        }
        return false;
    }

}
