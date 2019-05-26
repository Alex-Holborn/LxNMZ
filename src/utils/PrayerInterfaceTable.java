package utils;

import java.util.HashMap;

public class PrayerInterfaceTable {

    private HashMap<Integer, HashMap<Integer, Integer>> interfaceCrossover;

    public enum PrayerNames {Thick_Skin, Burst_of_Strength, Clarity_of_Thought, Sharp_Eye, Mystic_will, Rock_Skin,
        Superhuman_Strength, Improved_Reflexes, Rapid_Restore, Rapid_Heal, Protect_Item, Hawk_Eye, Mystic_Lore,
        Steel_Skin, Ultimate_Strength, Incredible_Reflexes, Protect_from_Magic, Protect_from_Missiles,
        Protect_from_Melee, Eagle_Eye, Mystic_Might, Retribution, Redemption, Smite, Preserve, Chivalry, Piety,
        Rigour, Augury} //In order of position in prayer tab

    PrayerInterfaceTable(){
        interfaceCrossover = new HashMap<>();
        interfaceCrossover.put(0, new HashMap<>());
        interfaceCrossover.get(0).put(5,0);
        interfaceCrossover.put(1, new HashMap<>());
        interfaceCrossover.get(1).put(6,1);
        interfaceCrossover.put(2, new HashMap<>());
        interfaceCrossover.get(2).put(7,2);
        interfaceCrossover.put(3, new HashMap<>());
        interfaceCrossover.get(3).put(23,18);
        interfaceCrossover.put(4, new HashMap<>());
        interfaceCrossover.get(4).put(24,19);
        interfaceCrossover.put(5, new HashMap<>());
        interfaceCrossover.get(5).put(8,3);
        interfaceCrossover.put(6, new HashMap<>());
        interfaceCrossover.get(6).put(9,4);
        interfaceCrossover.put(7, new HashMap<>());
        interfaceCrossover.get(7).put(10,5);
        interfaceCrossover.put(8, new HashMap<>());
        interfaceCrossover.get(8).put(11,6);
        interfaceCrossover.put(9, new HashMap<>());
        interfaceCrossover.get(9).put(12,7);
        interfaceCrossover.put(10, new HashMap<>());
        interfaceCrossover.get(10).put(13,8);
        interfaceCrossover.put(11, new HashMap<>());
        interfaceCrossover.get(11).put(25,20);
        interfaceCrossover.put(12, new HashMap<>());
        interfaceCrossover.get(12).put(26,21);
        interfaceCrossover.put(13, new HashMap<>());
        interfaceCrossover.get(13).put(14,9);
        interfaceCrossover.put(14, new HashMap<>());
        interfaceCrossover.get(14).put(15,10);
        interfaceCrossover.put(15, new HashMap<>());
        interfaceCrossover.get(15).put(16,11);
        interfaceCrossover.put(16, new HashMap<>());
        interfaceCrossover.get(16).put(17,12);
        interfaceCrossover.put(17, new HashMap<>());
        interfaceCrossover.get(17).put(18,13);
        interfaceCrossover.put(18, new HashMap<>());
        interfaceCrossover.get(18).put(19,14);
        interfaceCrossover.put(19, new HashMap<>());
        interfaceCrossover.get(19).put(27,22);
        interfaceCrossover.put(20, new HashMap<>());
        interfaceCrossover.get(20).put(28,23);
        interfaceCrossover.put(21, new HashMap<>());
        interfaceCrossover.get(21).put(20,15);
        interfaceCrossover.put(22, new HashMap<>());
        interfaceCrossover.get(22).put(21,16);
        interfaceCrossover.put(23, new HashMap<>());
        interfaceCrossover.get(23).put(22,17);
        interfaceCrossover.put(24, new HashMap<>());
        interfaceCrossover.get(24).put(33,28);
        interfaceCrossover.put(25, new HashMap<>());
        interfaceCrossover.get(25).put(29,25);
        interfaceCrossover.put(26, new HashMap<>());
        interfaceCrossover.get(26).put(30,26);
        interfaceCrossover.put(27, new HashMap<>());
        interfaceCrossover.get(27).put(31,24);
        interfaceCrossover.put(28, new HashMap<>());
        interfaceCrossover.get(28).put(32,27);
    }

    private HashMap<Integer, HashMap<Integer, Integer>> getTable(){
        return interfaceCrossover;
    }


    HashMap<Integer, Integer> getCrossoverPairByName(PrayerNames _p){
        PrayerNames[] p = PrayerNames.values();
        for(int i = 0; i < p.length; i++){
            if(p[i].equals(_p)){
                return getTable().get(i);
            }
        }
        return null;
    }

    HashMap<Integer, Integer> getCrossoverPairByPrayerInterface(int interfaceID){
        for(int i = 0; i < getTable().size(); i++){
            HashMap<Integer, Integer> map = getTable().get(i);
            if(map.keySet().contains(interfaceID)){
                return map;
            }
        }
        return null;
    }

    HashMap<Integer, Integer> getCrossoverPairByQuickPrayerInterface(int interfaceID){
        for(int i = 0; i < getTable().size(); i++){
            HashMap<Integer, Integer> map = getTable().get(i);
            for(int _i : map.keySet()){
                if(map.get(_i) == interfaceID){
                    return map;
                }
            }
        }
        return null;
    }

    int getPrayerInterfaceID(HashMap<Integer, Integer> pair){
        for(Integer i:pair.keySet()){
            return i;
        }
        return 0;
    }

    int getQuickPrayerInterfaceID(HashMap<Integer, Integer> pair){
        for(Integer i:pair.keySet()){
            return pair.get(i);
        }
        return 0;
    }

}
