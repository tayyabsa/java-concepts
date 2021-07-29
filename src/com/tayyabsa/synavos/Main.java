package com.tayyabsa.synavos;// Start typing here

class ORMFactory {

    public static ORM createOrm(String type) {
        if(type.equals("Hibernate")) {
            return new Hibernate();
        } else if (type.equals("Batis")) {
            return new Batis();
        }
        return null;
    }
}


interface ORM {

    void save(String data);
}

class Hibernate implements ORM{

    public void save(String data){
        System.out.println("saved"+data);
    }

}

class Batis implements ORM{

    public void save(String data)
    {
        System.out.println("saved"+data);
    }

    public String loadNativeDllMemory()
    {
        System.out.println("Batis -> loadNativeDllMemory");
        return "";
    }


}

public class Main {
    public static void main(String ... args)
    {
/*        Integer i1 = 100;
        Integer i2 = 100;
        Integer j1 = 200;
        Integer j2 = 200;

        System.out.println("i1 == i2: " + (i1 == i2));
        System.out.println("j1 == j2: " + (j1 == j2));*/

        // String a = new String();
        // String b = a;

  /*       Batis a = new Batis();
         Batis b = a.clone();*/


         ORM orm =  ORMFactory.createOrm("Batis");


         orm.save("aa");
        ((Batis)orm).loadNativeDllMemory();
    }
}



