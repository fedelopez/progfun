package week4;

/**
 * @author fede
 */
public class ProblematicArrayJava {

    public static void main(String[] args) {
        Tiger[] tigers = new Tiger[]{new Tiger()};
        Panthera[] pantheras = tigers;
        pantheras[0] = new Panthera();
        Tiger tiger = tigers[0];
        System.out.println("panthera = " + tiger.getTemporalRange());
    }

    private static class Panthera {
        String getTemporalRange() {
            return "Late Miocene to Recent";
        }
    }

    private static class Tiger extends Panthera {
        String getTemporalRange() {
            return "Early Pleistocene to Recent";
        }
    }
}
