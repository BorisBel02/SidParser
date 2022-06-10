package proj;



public class Main {

    public static void main(String[] args) {
        if(args.length != 1){
            System.err.println("Wrong argument quantity");
            System.exit(22);
        }
        String fileName = args[0];
        try {
            Parser parser = new Parser(fileName);
        }catch (Exception e){
            System.err.println(e);
        }
    }
}
