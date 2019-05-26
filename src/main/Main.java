package main;

import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.ConditionalSleep;
import utils.PrayerInterfaceTable;
import utils.QuickPrayerSetup;

import java.util.Arrays;

@ScriptManifest(author = "Alex", info = "NMZ Bot", logo = "", name = "LxNMZ", version = 0)
public class Main extends Script {

    private boolean inDream = false;

    private final int powerSurgeID = 26264;

    public void onStart(){
        setupQuickPrayers();
    }

    public int onLoop(){
        if(inDream){

        } else{

        }
        return 1;
    }

    private void drinkPotion(){
        getObjects().closest(26291).interact();
        new ConditionalSleep(10000){
            @Override
            public boolean condition(){
                return getWidgets().isVisible(129, 2);
            }
        }.sleep();
        log("Options visible");
        //For now we will just accept to start the dream. In the future, we will set optimal creatures via widgetdestinations
        if(getWidgets().get(129, 6, 9).interact()) {
            inDream = true;
        }
    }



    private void setupDream(){
        getNpcs().closest(1120).interact("Dream");
        new ConditionalSleep(10000){
            @Override
            public boolean condition(){
                return getWidgets().isVisible(219, 1);
            }
        }.sleep();
        if(getWidgets().get(219,1,4) != null){
            getWidgets().get(219, 1, 4).interact();
        } else {

        }
        new ConditionalSleep(10000){
            @Override
            public boolean condition(){
                return getDialogues().isPendingContinuation();
            }
        }.sleep();
        getDialogues().clickContinue();
        new ConditionalSleep(10000){
            @Override
            public boolean condition(){
                return getDialogues().isPendingOption();
            }
        }.sleep();
        getDialogues().selectOption(1);
    }

    private void setupQuickPrayers(){
        new QuickPrayerSetup(this, new PrayerInterfaceTable.PrayerNames[]{PrayerInterfaceTable.PrayerNames.Rapid_Heal, PrayerInterfaceTable.PrayerNames.Piety});
    }

    private boolean locateZapper(){
        log("locating zapper...");
        return false;
    }

    private void finishNMZ(){
        log("NMZ has finished");
    }

    @Override
    public void onMessage(Message m) throws InterruptedException {
        super.onMessage(m);
        if(m.getMessage().equals("You wake up feeling refreshed.")){
            finishNMZ();
        } else if(m.getMessage().equals("A power-up has spawned: Zapper")){
            if(locateZapper()){

            }
        }
    }
}
